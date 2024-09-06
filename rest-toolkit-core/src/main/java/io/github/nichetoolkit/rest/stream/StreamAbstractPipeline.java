package io.github.nichetoolkit.rest.stream;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.BaseStream;

abstract class StreamAbstractPipeline<E_IN, E_OUT, S extends BaseStream<E_OUT, S>>
        extends DefaultPipelineHelper<E_OUT> implements BaseStream<E_OUT, S> {
    private static final String MSG_STREAM_LINKED = "stream has already been operated upon or closed";
    private static final String MSG_CONSUMED = "source already consumed or closed";

    @SuppressWarnings("rawtypes")
    private final StreamAbstractPipeline sourceStage;

    @SuppressWarnings("rawtypes")
    private final StreamAbstractPipeline previousStage;

    protected final int sourceOrOpFlags;

    @SuppressWarnings("rawtypes")
    private StreamAbstractPipeline nextStage;

    private int depth;

    private int combinedFlags;

    private Spliterator<?> sourceSpliterator;

    private Supplier<? extends Spliterator<?>> sourceSupplier;

    private boolean linkedOrConsumed;

    private boolean sourceAnyStateful;

    private Runnable sourceCloseAction;

    private boolean parallel;

    StreamAbstractPipeline(Supplier<? extends Spliterator<?>> source,
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

    StreamAbstractPipeline(Spliterator<?> source,
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

    StreamAbstractPipeline(StreamAbstractPipeline<?, E_IN, ?> previousStage, int opFlags) {
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


    // Terminal evaluation methods

    final <R> R evaluate(DefaultTerminalOp<E_OUT, R> terminalOp) {
        assert getOutputShape() == terminalOp.inputShape();
        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        return isParallel()
               ? terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()))
               : terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
    }

    @SuppressWarnings("unchecked")
    final DefaultNode<E_OUT> evaluateToArrayNode(IntFunction<E_OUT[]> generator) {
        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        // If the last intermediate operation is stateful then
        // evaluate directly to avoid an extra collection step
        if (isParallel() && previousStage != null && opIsStateful()) {
            // Set the depth of this, last, pipeline stage to zero to slice the
            // pipeline such that this operation will not be included in the
            // upstream slice and upstream operations will not be included
            // in this slice
            depth = 0;
            return opEvaluateParallel(previousStage, previousStage.sourceSpliterator(0), generator);
        }
        else {
            return evaluate(sourceSpliterator(0), true, generator);
        }
    }

    @SuppressWarnings("unchecked")
    final Spliterator<E_OUT> sourceStageSpliterator() {
        if (this != sourceStage)
            throw new IllegalStateException();

        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        if (sourceStage.sourceSpliterator != null) {
            @SuppressWarnings("unchecked")
            Spliterator<E_OUT> s = sourceStage.sourceSpliterator;
            sourceStage.sourceSpliterator = null;
            return s;
        }
        else if (sourceStage.sourceSupplier != null) {
            @SuppressWarnings("unchecked")
            Spliterator<E_OUT> s = (Spliterator<E_OUT>) sourceStage.sourceSupplier.get();
            sourceStage.sourceSupplier = null;
            return s;
        }
        else {
            throw new IllegalStateException(MSG_CONSUMED);
        }
    }

    // BaseStream

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public final S sequential() {
        sourceStage.parallel = false;
        return (S) this;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public final S parallel() {
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

    // Primitive specialization use co-variant overrides, hence is not final

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public Spliterator<E_OUT> spliterator() {
        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        if (this == sourceStage) {
            if (sourceStage.sourceSpliterator != null) {
                @SuppressWarnings("unchecked")
                Spliterator<E_OUT> s = (Spliterator<E_OUT>) sourceStage.sourceSpliterator;
                sourceStage.sourceSpliterator = null;
                return s;
            }
            else if (sourceStage.sourceSupplier != null) {
                @SuppressWarnings("unchecked")
                Supplier<Spliterator<E_OUT>> s = (Supplier<Spliterator<E_OUT>>) sourceStage.sourceSupplier;
                sourceStage.sourceSupplier = null;
                return lazySpliterator(s);
            }
            else {
                throw new IllegalStateException(MSG_CONSUMED);
            }
        }
        else {
            return wrap(this, () -> sourceSpliterator(0), isParallel());
        }
    }

    @Override
    public final boolean isParallel() {
        return sourceStage.parallel;
    }


    final int getStreamFlags() {
        return DefaultStreamOpFlag.toStreamFlags(combinedFlags);
    }

    @SuppressWarnings("unchecked")
    private Spliterator<?> sourceSpliterator(int terminalFlags) {
        // Get the source spliterator of the pipeline
        Spliterator<?> spliterator;
        if (sourceStage.sourceSpliterator != null) {
            spliterator = sourceStage.sourceSpliterator;
            sourceStage.sourceSpliterator = null;
        }
        else if (sourceStage.sourceSupplier != null) {
            spliterator = (Spliterator<?>) sourceStage.sourceSupplier.get();
            sourceStage.sourceSupplier = null;
        }
        else {
            throw new IllegalStateException(MSG_CONSUMED);
        }

        if (isParallel() && sourceStage.sourceAnyStateful) {
            // Adapt the source spliterator, evaluating each stateful op
            // in the pipeline up to and including this pipeline stage.
            // The depth and flags of each pipeline stage are adjusted accordingly.
            int depth = 1;
            for (@SuppressWarnings("rawtypes") StreamAbstractPipeline u = sourceStage, p = sourceStage.nextStage, e = this;
                 u != e;
                 u = p, p = p.nextStage) {

                int thisOpFlags = p.sourceOrOpFlags;
                if (p.opIsStateful()) {
                    depth = 0;

                    if (DefaultStreamOpFlag.SHORT_CIRCUIT.isKnown(thisOpFlags)) {
                        // Clear the short circuit flag for next pipeline stage
                        // This stage encapsulates short-circuiting, the next
                        // stage may not have any short-circuit operations, and
                        // if so spliterator.forEachRemaining should be used
                        // for traversal
                        thisOpFlags = thisOpFlags & ~DefaultStreamOpFlag.IS_SHORT_CIRCUIT;
                    }

                    spliterator = p.opEvaluateParallelLazy(u, spliterator);

                    // Inject or clear SIZED on the source pipeline stage
                    // based on the stage's spliterator
                    thisOpFlags = spliterator.hasCharacteristics(Spliterator.SIZED)
                            ? (thisOpFlags & ~DefaultStreamOpFlag.NOT_SIZED) | DefaultStreamOpFlag.IS_SIZED
                            : (thisOpFlags & ~DefaultStreamOpFlag.IS_SIZED) | DefaultStreamOpFlag.NOT_SIZED;
                }
                p.depth = depth++;
                p.combinedFlags = DefaultStreamOpFlag.combineOpFlags(thisOpFlags, u.combinedFlags);
            }
        }

        if (terminalFlags != 0)  {
            // Apply flags from the terminal operation to last pipeline stage
            combinedFlags = DefaultStreamOpFlag.combineOpFlags(terminalFlags, combinedFlags);
        }

        return spliterator;
    }

    // DefaultPipelineHelper

    @Override
    final DefaultStreamShape getSourceShape() {
        @SuppressWarnings("rawtypes")
        StreamAbstractPipeline p = StreamAbstractPipeline.this;
        while (p.depth > 0) {
            p = p.previousStage;
        }
        return p.getOutputShape();
    }

    @Override
    final <P_IN> long exactOutputSizeIfKnown(Spliterator<P_IN> spliterator) {
        return DefaultStreamOpFlag.SIZED.isKnown(getStreamAndOpFlags()) ? spliterator.getExactSizeIfKnown() : -1;
    }

    @Override
    final <P_IN, S extends DefaultSink<E_OUT>> S wrapAndCopyInto(S sink, Spliterator<P_IN> spliterator) {
        copyInto(wrapSink(Objects.requireNonNull(sink)), spliterator);
        return sink;
    }

    @Override
    final <P_IN> void copyInto(DefaultSink<P_IN> wrappedDefaultSink, Spliterator<P_IN> spliterator) {
        Objects.requireNonNull(wrappedDefaultSink);

        if (!DefaultStreamOpFlag.SHORT_CIRCUIT.isKnown(getStreamAndOpFlags())) {
            wrappedDefaultSink.begin(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(wrappedDefaultSink);
            wrappedDefaultSink.end();
        }
        else {
            copyIntoWithCancel(wrappedDefaultSink, spliterator);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    final <P_IN> void copyIntoWithCancel(DefaultSink<P_IN> wrappedDefaultSink, Spliterator<P_IN> spliterator) {
        @SuppressWarnings({"rawtypes"})
        StreamAbstractPipeline p = StreamAbstractPipeline.this;
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
        return DefaultStreamOpFlag.ORDERED.isKnown(combinedFlags);
    }

    @Override
    @SuppressWarnings("unchecked")
    final <P_IN> DefaultSink<P_IN> wrapSink(DefaultSink<E_OUT> sink) {
        Objects.requireNonNull(sink);

        for (@SuppressWarnings("rawtypes") StreamAbstractPipeline p = StreamAbstractPipeline.this; p.depth > 0; p=p.previousStage) {
            sink = p.opWrapSink(p.previousStage.combinedFlags, sink);
        }
        return (DefaultSink<P_IN>) sink;
    }

    @Override
    @SuppressWarnings("unchecked")
    final <P_IN> Spliterator<E_OUT> wrapSpliterator(Spliterator<P_IN> sourceSpliterator) {
        if (depth == 0) {
            return (Spliterator<E_OUT>) sourceSpliterator;
        }
        else {
            return wrap(this, () -> sourceSpliterator, isParallel());
        }
    }

    @Override
    final <P_IN> DefaultNode<E_OUT> evaluate(Spliterator<P_IN> spliterator,
                                      boolean flatten,
                                      IntFunction<E_OUT[]> generator) {
        if (isParallel()) {
            // @@@ Optimize if op of this pipeline stage is a stateful op
            return evaluateToNode(this, spliterator, flatten, generator);
        }
        else {
            DefaultNode.Builder<E_OUT> nb = makeNodeBuilder(
                    exactOutputSizeIfKnown(spliterator), generator);
            return wrapAndCopyInto(nb, spliterator).build();
        }
    }


    // Shape-specific abstract methods, implemented by XxxPipeline classes

    abstract DefaultStreamShape getOutputShape();

    abstract <P_IN> DefaultNode<E_OUT> evaluateToNode(DefaultPipelineHelper<E_OUT> helper,
                                               Spliterator<P_IN> spliterator,
                                               boolean flattenTree,
                                               IntFunction<E_OUT[]> generator);

    abstract <P_IN> Spliterator<E_OUT> wrap(DefaultPipelineHelper<E_OUT> ph,
                                            Supplier<Spliterator<P_IN>> supplier,
                                            boolean isParallel);

    abstract Spliterator<E_OUT> lazySpliterator(Supplier<? extends Spliterator<E_OUT>> supplier);

    abstract void forEachWithCancel(Spliterator<E_OUT> spliterator, DefaultSink<E_OUT> sink);

    @Override
    abstract DefaultNode.Builder<E_OUT> makeNodeBuilder(long exactSizeIfKnown,
                                                 IntFunction<E_OUT[]> generator);


    // Op-specific abstract methods, implemented by the operation class

    abstract boolean opIsStateful();

    abstract DefaultSink<E_IN> opWrapSink(int flags, DefaultSink<E_OUT> sink);

    <P_IN> DefaultNode<E_OUT> opEvaluateParallel(DefaultPipelineHelper<E_OUT> helper,
                                          Spliterator<P_IN> spliterator,
                                          IntFunction<E_OUT[]> generator) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    @SuppressWarnings("unchecked")
    <P_IN> Spliterator<E_OUT> opEvaluateParallelLazy(DefaultPipelineHelper<E_OUT> helper,
                                                     Spliterator<P_IN> spliterator) {
        return opEvaluateParallel(helper, spliterator, i -> (E_OUT[]) new Object[i]).spliterator();
    }
}
