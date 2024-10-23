package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.function.IntFunction;

/**
 * <code>DefaultAbstractPipeline</code>
 * <p>The default abstract pipeline class.</p>
 * @param <E_IN>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <E_OUT> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <S>     {@link io.github.nichetoolkit.rest.stream.DefaultBaseStream} <p>The generic parameter is <code>DefaultBaseStream</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.stream.DefaultBaseStream
 * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
 * @since Jdk1.8
 */
abstract class DefaultAbstractPipeline<E_IN, E_OUT, S extends DefaultBaseStream<E_OUT, S>>
        extends DefaultPipelineHelper<E_OUT> implements DefaultBaseStream<E_OUT, S> {
    /**
     * <code>MSG_STREAM_LINKED</code>
     * {@link java.lang.String} <p>The constant <code>MSG_STREAM_LINKED</code> field.</p>
     * @see java.lang.String
     */
    private static final String MSG_STREAM_LINKED = "stream has already been operated upon or closed";
    /**
     * <code>MSG_CONSUMED</code>
     * {@link java.lang.String} <p>The constant <code>MSG_CONSUMED</code> field.</p>
     * @see java.lang.String
     */
    private static final String MSG_CONSUMED = "source already consumed or closed";

    /**
     * <code>sourceStage</code>
     * {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>The <code>sourceStage</code> field.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("rawtypes")
    private final DefaultAbstractPipeline sourceStage;

    /**
     * <code>previousStage</code>
     * {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>The <code>previousStage</code> field.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("rawtypes")
    private final DefaultAbstractPipeline previousStage;

    /**
     * <code>sourceOrOpFlags</code>
     * <p>The <code>sourceOrOpFlags</code> field.</p>
     */
    protected final int sourceOrOpFlags;

    /**
     * <code>nextStage</code>
     * {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>The <code>nextStage</code> field.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("rawtypes")
    private DefaultAbstractPipeline nextStage;

    /**
     * <code>depth</code>
     * <p>The <code>depth</code> field.</p>
     */
    private int depth;

    /**
     * <code>combinedFlags</code>
     * <p>The <code>combinedFlags</code> field.</p>
     */
    private int combinedFlags;

    /**
     * <code>sourceSpliterator</code>
     * {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The <code>sourceSpliterator</code> field.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    private DefaultSpliterator<?> sourceSpliterator;

    /**
     * <code>sourceSupplier</code>
     * {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The <code>sourceSupplier</code> field.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
    private SupplierActuator<? extends DefaultSpliterator<?>> sourceSupplier;

    /**
     * <code>linkedOrConsumed</code>
     * <p>The <code>linkedOrConsumed</code> field.</p>
     */
    private boolean linkedOrConsumed;

    /**
     * <code>sourceAnyStateful</code>
     * <p>The <code>sourceAnyStateful</code> field.</p>
     */
    private boolean sourceAnyStateful;

    /**
     * <code>sourceCloseAction</code>
     * {@link java.lang.Runnable} <p>The <code>sourceCloseAction</code> field.</p>
     * @see java.lang.Runnable
     */
    private Runnable sourceCloseAction;

    /**
     * <code>parallel</code>
     * <p>The <code>parallel</code> field.</p>
     */
    private boolean parallel;

    /**
     * <code>DefaultAbstractPipeline</code>
     * <p>Instantiates a new default abstract pipeline.</p>
     * @param source      {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The source parameter is <code>SupplierActuator</code> type.</p>
     * @param sourceFlags int <p>The source flags parameter is <code>int</code> type.</p>
     * @param parallel    boolean <p>The parallel parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     */
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

    /**
     * <code>DefaultAbstractPipeline</code>
     * <p>Instantiates a new default abstract pipeline.</p>
     * @param source      {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The source parameter is <code>DefaultSpliterator</code> type.</p>
     * @param sourceFlags int <p>The source flags parameter is <code>int</code> type.</p>
     * @param parallel    boolean <p>The parallel parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
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

    /**
     * <code>DefaultAbstractPipeline</code>
     * <p>Instantiates a new default abstract pipeline.</p>
     * @param previousStage {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>The previous stage parameter is <code>DefaultAbstractPipeline</code> type.</p>
     * @param opFlags       int <p>The op flags parameter is <code>int</code> type.</p>
     */
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

    /**
     * <code>evaluate</code>
     * <p>The evaluate method.</p>
     * @param <R>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param terminalOp {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>The terminal op parameter is <code>DefaultTerminalOp</code> type.</p>
     * @return R <p>The evaluate return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     * @see io.github.nichetoolkit.rest.RestException
     */
    final <R> R evaluate(DefaultTerminalOp<E_OUT, R> terminalOp) throws RestException {
        assert getOutputShape() == terminalOp.inputShape();
        if (linkedOrConsumed)
            throw new IllegalStateException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        return isParallel()
                ? terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()))
                : terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
    }

    /**
     * <code>evaluateToArrayNode</code>
     * <p>The evaluate to array node method.</p>
     * @param generator {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The evaluate to array node return object is <code>DefaultNode</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.IntFunction
     * @see io.github.nichetoolkit.rest.stream.DefaultNode
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>sourceStageSpliterator</code>
     * <p>The source stage spliterator method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The source stage spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
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


    /**
     * <code>getStreamFlags</code>
     * <p>The get stream flags getter method.</p>
     * @return int <p>The get stream flags return object is <code>int</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    final int getStreamFlags() throws RestException {
        return DefaultStreamOpFlag.toStreamFlags(combinedFlags);
    }

    /**
     * <code>sourceSpliterator</code>
     * <p>The source spliterator method.</p>
     * @param terminalFlags int <p>The terminal flags parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The source spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>isOrdered</code>
     * <p>The is ordered method.</p>
     * @return boolean <p>The is ordered return object is <code>boolean</code> type.</p>
     */
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

    /**
     * <code>getOutputShape</code>
     * <p>The get output shape getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>The get output shape return object is <code>DefaultStreamShape</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
     */
    abstract DefaultStreamShape getOutputShape();

    /**
     * <code>evaluateToNode</code>
     * <p>The evaluate to node method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @param flattenTree boolean <p>The flatten tree parameter is <code>boolean</code> type.</p>
     * @param generator   {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The evaluate to node return object is <code>DefaultNode</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see java.util.function.IntFunction
     * @see io.github.nichetoolkit.rest.stream.DefaultNode
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultNode<E_OUT> evaluateToNode(DefaultPipelineHelper<E_OUT> helper,
                                                      DefaultSpliterator<P_IN> spliterator,
                                                      boolean flattenTree,
                                                      IntFunction<E_OUT[]> generator) throws RestException;

    /**
     * <code>wrap</code>
     * <p>The wrap method.</p>
     * @param <P_IN>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param ph         {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The ph parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param supplier   {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param isParallel boolean <p>The is parallel parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The wrap return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultSpliterator<E_OUT> wrap(DefaultPipelineHelper<E_OUT> ph,
                                                   SupplierActuator<DefaultSpliterator<P_IN>> supplier,
                                                   boolean isParallel) throws RestException;

    /**
     * <code>lazySpliterator</code>
     * <p>The lazy spliterator method.</p>
     * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The lazy spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract DefaultSpliterator<E_OUT> lazySpliterator(SupplierActuator<? extends DefaultSpliterator<E_OUT>> supplier) throws RestException;

    /**
     * <code>forEachWithCancel</code>
     * <p>The for each with cancel method.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @param sink        {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The sink parameter is <code>DefaultSink</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract void forEachWithCancel(DefaultSpliterator<E_OUT> spliterator, DefaultSink<E_OUT> sink) throws RestException;

    @Override
    abstract DefaultNode.Builder<E_OUT> makeNodeBuilder(long exactSizeIfKnown,
                                                        IntFunction<E_OUT[]> generator);


    // Op-specific abstract methods, implemented by the operation class

    /**
     * <code>opIsStateful</code>
     * <p>The op is stateful method.</p>
     * @return boolean <p>The op is stateful return object is <code>boolean</code> type.</p>
     */
    abstract boolean opIsStateful();

    /**
     * <code>opWrapSink</code>
     * <p>The op wrap sink method.</p>
     * @param flags int <p>The flags parameter is <code>int</code> type.</p>
     * @param sink  {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The sink parameter is <code>DefaultSink</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The op wrap sink return object is <code>DefaultSink</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract DefaultSink<E_IN> opWrapSink(int flags, DefaultSink<E_OUT> sink) throws RestException;

    /**
     * <code>opEvaluateParallel</code>
     * <p>The op evaluate parallel method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @param generator   {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The op evaluate parallel return object is <code>DefaultNode</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see java.util.function.IntFunction
     * @see io.github.nichetoolkit.rest.stream.DefaultNode
     * @see io.github.nichetoolkit.rest.RestException
     */
    <P_IN> DefaultNode<E_OUT> opEvaluateParallel(DefaultPipelineHelper<E_OUT> helper,
                                                 DefaultSpliterator<P_IN> spliterator,
                                                 IntFunction<E_OUT[]> generator) throws RestException {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    /**
     * <code>opEvaluateParallelLazy</code>
     * <p>The op evaluate parallel lazy method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The op evaluate parallel lazy return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("unchecked")
    <P_IN> DefaultSpliterator<E_OUT> opEvaluateParallelLazy(DefaultPipelineHelper<E_OUT> helper,
                                                            DefaultSpliterator<P_IN> spliterator) throws RestException {
        return opEvaluateParallel(helper, spliterator, i -> (E_OUT[]) new Object[i]).spliterator();
    }
}
