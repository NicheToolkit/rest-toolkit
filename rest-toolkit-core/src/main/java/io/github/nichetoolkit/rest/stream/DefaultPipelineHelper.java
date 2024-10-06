package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.function.IntFunction;

/**
 * <code>DefaultPipelineHelper</code>
 * <p>The type default pipeline helper class.</p>
 * @param <P_OUT> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
abstract class DefaultPipelineHelper<P_OUT> {

    /**
     * <code>getSourceShape</code>
     * <p>The source shape getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>The source shape return object is <code>DefaultStreamShape</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
     */
    abstract DefaultStreamShape getSourceShape();

    /**
     * <code>getStreamAndOpFlags</code>
     * <p>The stream and op flags getter method.</p>
     * @return int <p>The stream and op flags return object is <code>int</code> type.</p>
     */
    abstract int getStreamAndOpFlags();

    /**
     * <code>exactOutputSizeIfKnown</code>
     * <p>The output size if known method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return long <p>The output size if known return object is <code>long</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> long exactOutputSizeIfKnown(DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>wrapAndCopyInto</code>
     * <p>The and copy into method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S>         {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The generic parameter is <code>DefaultSink</code> type.</p>
     * @param sink        S <p>The sink parameter is <code>S</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return S <p>The and copy into return object is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN, S extends DefaultSink<P_OUT>> S wrapAndCopyInto(S sink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>copyInto</code>
     * <p>The into method.</p>
     * @param <P_IN>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param wrappedDefaultSink {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The wrapped default sink parameter is <code>DefaultSink</code> type.</p>
     * @param spliterator        {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> void copyInto(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>copyIntoWithCancel</code>
     * <p>The into with cancel method.</p>
     * @param <P_IN>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param wrappedDefaultSink {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The wrapped default sink parameter is <code>DefaultSink</code> type.</p>
     * @param spliterator        {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> void copyIntoWithCancel(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>wrapSink</code>
     * <p>The sink method.</p>
     * @param <P_IN> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param sink   {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The sink parameter is <code>DefaultSink</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The sink return object is <code>DefaultSink</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultSink<P_IN> wrapSink(DefaultSink<P_OUT> sink) throws RestException;

    /**
     * <code>wrapSpliterator</code>
     * <p>The spliterator method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultSpliterator<P_OUT> wrapSpliterator(DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>makeNodeBuilder</code>
     * <p>The node builder method.</p>
     * @param exactSizeIfKnown long <p>The exact size if known parameter is <code>long</code> type.</p>
     * @param generator        {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode.Builder} <p>The node builder return object is <code>Builder</code> type.</p>
     * @see java.util.function.IntFunction
     * @see io.github.nichetoolkit.rest.stream.DefaultNode.Builder
     */
    abstract DefaultNode.Builder<P_OUT> makeNodeBuilder(long exactSizeIfKnown,
                                                        IntFunction<P_OUT[]> generator);

    /**
     * <code>evaluate</code>
     * <p>The method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @param flatten     boolean <p>The flatten parameter is <code>boolean</code> type.</p>
     * @param generator   {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The return object is <code>DefaultNode</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see java.util.function.IntFunction
     * @see io.github.nichetoolkit.rest.stream.DefaultNode
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultNode<P_OUT> evaluate(DefaultSpliterator<P_IN> spliterator,
                                                boolean flatten,
                                                IntFunction<P_OUT[]> generator) throws RestException;
}
