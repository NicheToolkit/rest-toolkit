package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.function.IntFunction;

/**
 * <code>DefaultPipelineHelper</code>
 * <p>The type default pipeline helper class.</p>
 * @param <P_OUT> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
abstract class DefaultPipelineHelper<P_OUT> {

    /**
     * <code>getSourceShape</code>
     * <p>the source shape getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>the source shape return object is <code>DefaultStreamShape</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
     */
    abstract DefaultStreamShape getSourceShape();

    /**
     * <code>getStreamAndOpFlags</code>
     * <p>the stream and op flags getter method.</p>
     * @return int <p>the stream and op flags return object is <code>int</code> type.</p>
     */
    abstract int getStreamAndOpFlags();

    /**
     * <code>exactOutputSizeIfKnown</code>
     * <p>the output size if known method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return long <p>the output size if known return object is <code>long</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> long exactOutputSizeIfKnown(DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>wrapAndCopyInto</code>
     * <p>the and copy into method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <S>         {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the generic parameter is <code>DefaultSink</code> type.</p>
     * @param sink        S <p>the sink parameter is <code>S</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return S <p>the and copy into return object is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN, S extends DefaultSink<P_OUT>> S wrapAndCopyInto(S sink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>copyInto</code>
     * <p>the into method.</p>
     * @param <P_IN>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param wrappedDefaultSink {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the wrapped default sink parameter is <code>DefaultSink</code> type.</p>
     * @param spliterator        {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> void copyInto(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>copyIntoWithCancel</code>
     * <p>the into with cancel method.</p>
     * @param <P_IN>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param wrappedDefaultSink {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the wrapped default sink parameter is <code>DefaultSink</code> type.</p>
     * @param spliterator        {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> void copyIntoWithCancel(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>wrapSink</code>
     * <p>the sink method.</p>
     * @param <P_IN> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param sink   {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the sink parameter is <code>DefaultSink</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the sink return object is <code>DefaultSink</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultSink<P_IN> wrapSink(DefaultSink<P_OUT> sink) throws RestException;

    /**
     * <code>wrapSpliterator</code>
     * <p>the spliterator method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultSpliterator<P_OUT> wrapSpliterator(DefaultSpliterator<P_IN> spliterator) throws RestException;

    /**
     * <code>makeNodeBuilder</code>
     * <p>the node builder method.</p>
     * @param exactSizeIfKnown long <p>the exact size if known parameter is <code>long</code> type.</p>
     * @param generator        {@link java.util.function.IntFunction} <p>the generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode.Builder} <p>the node builder return object is <code>Builder</code> type.</p>
     * @see java.util.function.IntFunction
     * @see io.github.nichetoolkit.rest.stream.DefaultNode.Builder
     */
    abstract DefaultNode.Builder<P_OUT> makeNodeBuilder(long exactSizeIfKnown,
                                                        IntFunction<P_OUT[]> generator);

    /**
     * <code>evaluate</code>
     * <p>the method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @param flatten     boolean <p>the flatten parameter is <code>boolean</code> type.</p>
     * @param generator   {@link java.util.function.IntFunction} <p>the generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>the return object is <code>DefaultNode</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see java.util.function.IntFunction
     * @see io.github.nichetoolkit.rest.stream.DefaultNode
     * @see io.github.nichetoolkit.rest.RestException
     */
    abstract <P_IN> DefaultNode<P_OUT> evaluate(DefaultSpliterator<P_IN> spliterator,
                                                boolean flatten,
                                                IntFunction<P_OUT[]> generator) throws RestException;
}
