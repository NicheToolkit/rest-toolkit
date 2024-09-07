package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.function.IntFunction;

abstract class DefaultAbstractPipeline<E_IN, E_OUT, S extends DefaultBaseStream<E_OUT, S>>
        extends DefaultPipelineHelper<E_OUT> implements DefaultBaseStream<E_OUT, S> {
    private static final String MSG_STREAM_LINKED = "stream has already been operated upon or closed";
    private static final String MSG_CONSUMED = "source already consumed or closed";

    @SuppressWarnings("rawtypes")
    private final DefaultAbstractPipeline sourceStage;

    @SuppressWarnings("rawtypes")
    private final DefaultAbstractPipeline previousStage;

    protected final int sourceOrOpFlags;

    @SuppressWarnings("rawtypes")
    private DefaultAbstractPipeline nextStage;

    private int depth;

    private int combinedFlags;

    private DefaultSpliterator<?> sourceSpliterator;

    private SupplierActuator<? extends DefaultSpliterator<?>> sourceSupplier;

    private boolean linkedOrConsumed;

    private boolean sourceAnyStateful;

    private Runnable sourceCloseAction;

    private boolean parallel;

    DefaultAbstractPipeline(SupplierActuator<? extends DefaultSpliterator<?>> source,
                            int sourceFlags, boolean parallel) {
        this.previousStage = null;
        this.sourceSupplier = source;
        this.sourceStage = this;
        this.sourceOrOpFlags = sourceFlags & DefaultStreamOpFlag.STREAM_MASK;
        // The following is an optimization of:
        // DefaultStreamOpFlag.combineOpFlags(sourceOrOpFlags, DefaultStreamOpFlag.INITIAL_OPS_VALUE);
        this.combinedFlags = (~(sourceOrOpFlags << 1)) & DefaultStreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = parallel;
    }

    DefaultAbstractPipeline(DefaultSpliterator<?> source,
                            int sourceFlags, boolean parallel) {
        this.previousStage = null;
        this.sourceSpliterator = source;
        this.sourceStage = this;
        this.sourceOrOpFlags = sourceFlags & DefaultStreamOpFlag.STREAM_MASK;
        // The following is an optimization of:
        // DefaultStreamOpFlag.combineOpFlags(sourceOrOpFlags, DefaultStreamOpFlag.INITIAL_OPS_VALUE);
        this.combinedFlags = (~(sourceOrOpFlags << 1)) & DefaultStreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = parallel;
    }

    DefaultAbstractPipeline(DefaultAbstractPipeline<?, E_IN, ?> previousStage, int opFlags) {
        if (previousStage.linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        previousStage.linkedOrConsumed = true;
        previousStage.nextStage = this;

        this.previousStage = previousStage;
        this.sourceOrOpFlags = opFlags & DefaultStreamOpFlag.OP_MASK;
        this.combinedFlags = DefaultStreamOpFlag.combineOpFlags(opFlags, previousStage.combinedFlags);
        this.sourceStage = previousStage.sourceStage;
        if (opIsStateful())
            sourceStage.sourceAnyStateful = true;
        this.depth = previousStage.depth + 1;
    }

    final <R> R evaluate(DefaultTerminalOp<E_OUT, R> terminalOp) throws RestException {
        assert getOutputShape() == terminalOp.inputShape();
        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        return isParallel()
                ? terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()))
                : terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
    }

    @SuppressWarnings("unchecked")
    final DefaultNode<E_OUT> evaluateToArrayNode(IntFunction<E_OUT[]> generator) throws RestException {
        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        if (isParallel() && previousStage != null && opIsStateful()) {
            depth = 0;
            return opEvaluateParallel(previousStage, previousStage.sourceSpliterator(0), generator);
        } else {
            return evaluate(sourceSpliterator(0), true, generator);
        }
    }

    @SuppressWarnings("unchecked")
    final DefaultSpliterator<E_OUT> sourceStageSpliterator() throws RestException {
        if (this != sourceStage)
            throw new IllegalStateException();

        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        if (sourceStage.sourceSpliterator != null) {
            @SuppressWarnings("unchecked")
            DefaultSpliterator<E_OUT> s = sourceStage.sourceSpliterator;
            sourceStage.sourceSpliterator = null;
            return s;
        } else if (sourceStage.sourceSupplier != null) {
            @SuppressWarnings("unchecked")
            DefaultSpliterator<E_OUT> s = (DefaultSpliterator<E_OUT>) sourceStage.sourceSupplier.actuate();
            sourceStage.sourceSupplier = null;
            return s;
        } else {
            throw new IllegalStateException(MSG_CONSUMED);
        }
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public final S sequential() throws RestException {
        sourceStage.parallel = false;
        return (S) this;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public final S parallel() throws RestException {
        sourceStage.parallel = true;
        return (S) this;
    }

    @Override
    public void close() {
        linkedOrConsumed = true;
        sourceSupplier = null;
        sourceSpliterator = null;
        if (sourceStage.sourceCloseAction != null) {
            Runnable closeAction = sourceStage.sourceCloseAction;
            sourceStage.sourceCloseAction = null;
            closeAction.run();
        }
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public S onClose(@NonNull Runnable closeHandler) {
        Objects.requireNonNull(closeHandler);
        Runnable existingHandler = sourceStage.sourceCloseAction;
        sourceStage.sourceCloseAction =
                (existingHandler == null)
                        ? closeHandler
                        : DefaultStreams.composeWithExceptions(existingHandler, closeHandler);
        return (S) this;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public DefaultSpliterator<E_OUT> spliterator() throws RestException {
        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        if (this == sourceStage) {
            if (sourceStage.sourceSpliterator != null) {
                @SuppressWarnings("unchecked")
                DefaultSpliterator<E_OUT> s = (DefaultSpliterator<E_OUT>) sourceStage.sourceSpliterator;
                sourceStage.sourceSpliterator = null;
                return s;
            } else if (sourceStage.sourceSupplier != null) {
                @SuppressWarnings("unchecked")
                SupplierActuator<DefaultSpliterator<E_OUT>> s = (SupplierActuator<DefaultSpliterator<E_OUT>>) sourceStage.sourceSupplier;
                sourceStage.sourceSupplier = null;
                return lazySpliterator(s);
            } else {
                throw new IllegalStateException(MSG_CONSUMED);
            }
        } else {
            return wrap(this, () -> sourceSpliterator(0), isParallel());
        }
    }

    @Override
    public final boolean isParallel() {
        return sourceStage.parallel;
    }


    final int getStreamFlags() throws RestException {
        return DefaultStreamOpFlag.toStreamFlags(combinedFlags);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private DefaultSpliterator<?> sourceSpliterator(int terminalFlags) throws RestException {
        // Get the source spliterator of the pipeline
        DefaultSpliterator<?> spliterator;
        if (sourceStage.sourceSpliterator != null) {
            spliterator = sourceStage.sourceSpliterator;
            sourceStage.sourceSpliterator = null;
        } else if (sourceStage.sourceSupplier != null) {
            spliterator = (DefaultSpliterator<?>) sourceStage.sourceSupplier.actuate();
            sourceStage.sourceSupplier = null;
        } else {
            throw new IllegalStateException(MSG_CONSUMED);
        }

        if (isParallel() && sourceStage.sourceAnyStateful) {
            int depth = 1;
            for (DefaultAbstractPipeline u = sourceStage, p = sourceStage.nextStage, e = this;
                 u != e;
                 u = p, p = p.nextStage) {

                int thisOpFlags = p.sourceOrOpFlags;
                if (p.opIsStateful()) {
                    depth = 0;

                    if (DefaultStreamOpFlag.SHORT_CIRCUIT.isKnown(thisOpFlags)) {
                        thisOpFlags = thisOpFlags & ~DefaultStreamOpFlag.IS_SHORT_CIRCUIT;
                    }
                    spliterator = p.opEvaluateParallelLazy(u, spliterator);
                    thisOpFlags = spliterator.hasCharacteristics(DefaultSpliterator.SIZED)
                            ? (thisOpFlags & ~DefaultStreamOpFlag.NOT_SIZED) | DefaultStreamOpFlag.IS_SIZED
                            : (thisOpFlags & ~DefaultStreamOpFlag.IS_SIZED) | DefaultStreamOpFlag.NOT_SIZED;
                }
                p.depth = depth++;
                p.combinedFlags = DefaultStreamOpFlag.combineOpFlags(thisOpFlags, u.combinedFlags);
            }
        }

        if (terminalFlags != 0) {
            combinedFlags = DefaultStreamOpFlag.combineOpFlags(terminalFlags, combinedFlags);
        }

        return spliterator;
    }

    @Override
    final DefaultStreamShape getSourceShape() {
        @SuppressWarnings("rawtypes")
        DefaultAbstractPipeline p = DefaultAbstractPipeline.this;
        while (p.depth > 0) {
            p = p.previousStage;
        }
        return p.getOutputShape();
    }

    @Override
    final <P_IN> long exactOutputSizeIfKnown(DefaultSpliterator<P_IN> spliterator) throws RestException {
        return DefaultStreamOpFlag.SIZED.isKnown(getStreamAndOpFlags()) ? spliterator.getExactSizeIfKnown() : -1;
    }

    @Override
    final <P_IN, SS extends DefaultSink<E_OUT>> SS wrapAndCopyInto(SS sink, DefaultSpliterator<P_IN> spliterator) throws RestException {
        copyInto(wrapSink(Objects.requireNonNull(sink)), spliterator);
        return sink;
    }

    @Override
    final <P_IN> void copyInto(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException {
        Objects.requireNonNull(wrappedDefaultSink);

        if (!DefaultStreamOpFlag.SHORT_CIRCUIT.isKnown(getStreamAndOpFlags())) {
            wrappedDefaultSink.begin(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(wrappedDefaultSink);
            wrappedDefaultSink.end();
        } else {
            copyIntoWithCancel(wrappedDefaultSink, spliterator);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    final <P_IN> void copyIntoWithCancel(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException {
        @SuppressWarnings({"rawtypes"})
        DefaultAbstractPipeline p = DefaultAbstractPipeline.this;
        while (p.depth > 0) {
            p = p.previousStage;
        }
        wrappedDefaultSink.begin(spliterator.getExactSizeIfKnown());
        p.forEachWithCancel(spliterator, wrappedDefaultSink);
        wrappedDefaultSink.end();
    }

    @Override
    final int getStreamAndOpFlags() {
        return combinedFlags;
    }

    final boolean isOrdered() {
        return !DefaultStreamOpFlag.ORDERED.isKnown(combinedFlags);
    }

    @Override
    @SuppressWarnings("unchecked")
    final <P_IN> DefaultSink<P_IN> wrapSink(DefaultSink<E_OUT> sink) throws RestException {
        Objects.requireNonNull(sink);

        for (@SuppressWarnings("rawtypes") DefaultAbstractPipeline p = DefaultAbstractPipeline.this; p.depth > 0; p = p.previousStage) {
            sink = p.opWrapSink(p.previousStage.combinedFlags, sink);
        }
        return (DefaultSink<P_IN>) sink;
    }

    @Override
    @SuppressWarnings("unchecked")
    final <P_IN> DefaultSpliterator<E_OUT> wrapSpliterator(DefaultSpliterator<P_IN> sourceSpliterator) throws RestException {
        if (depth == 0) {
            return (DefaultSpliterator<E_OUT>) sourceSpliterator;
        } else {
            return wrap(this, () -> sourceSpliterator, isParallel());
        }
    }

    @Override
    final <P_IN> DefaultNode<E_OUT> evaluate(DefaultSpliterator<P_IN> spliterator,
                                             boolean flatten,
                                             IntFunction<E_OUT[]> generator) throws RestException {
        if (isParallel()) {
            // @@@ Optimize if op of this pipeline stage is a stateful op
            return evaluateToNode(this, spliterator, flatten, generator);
        } else {
            DefaultNode.Builder<E_OUT> nb = makeNodeBuilder(
                    exactOutputSizeIfKnown(spliterator), generator);
            return wrapAndCopyInto(nb, spliterator).build();
        }
    }


    // Shape-specific abstract methods, implemented by XxxPipeline classes

    abstract DefaultStreamShape getOutputShape();

    abstract <P_IN> DefaultNode<E_OUT> evaluateToNode(DefaultPipelineHelper<E_OUT> helper,
                                                      DefaultSpliterator<P_IN> spliterator,
                                                      boolean flattenTree,
                                                      IntFunction<E_OUT[]> generator) throws RestException;

    abstract <P_IN> DefaultSpliterator<E_OUT> wrap(DefaultPipelineHelper<E_OUT> ph,
                                                   SupplierActuator<DefaultSpliterator<P_IN>> supplier,
                                                   boolean isParallel) throws RestException;

    abstract DefaultSpliterator<E_OUT> lazySpliterator(SupplierActuator<? extends DefaultSpliterator<E_OUT>> supplier) throws RestException;

    abstract void forEachWithCancel(DefaultSpliterator<E_OUT> spliterator, DefaultSink<E_OUT> sink) throws RestException;

    @Override
    abstract DefaultNode.Builder<E_OUT> makeNodeBuilder(long exactSizeIfKnown,
                                                        IntFunction<E_OUT[]> generator);


    // Op-specific abstract methods, implemented by the operation class

    abstract boolean opIsStateful();

    abstract DefaultSink<E_IN> opWrapSink(int flags, DefaultSink<E_OUT> sink) throws RestException;

    <P_IN> DefaultNode<E_OUT> opEvaluateParallel(DefaultPipelineHelper<E_OUT> helper,
                                                 DefaultSpliterator<P_IN> spliterator,
                                                 IntFunction<E_OUT[]> generator) throws RestException {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    @SuppressWarnings("unchecked")
    <P_IN> DefaultSpliterator<E_OUT> opEvaluateParallelLazy(DefaultPipelineHelper<E_OUT> helper,
                                                            DefaultSpliterator<P_IN> spliterator) throws RestException {
        return opEvaluateParallel(helper, spliterator, i -> (E_OUT[]) new Object[i]).spliterator();
    }
}
