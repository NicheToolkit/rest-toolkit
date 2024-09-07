package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

/**
 * <code>DefaultTerminalOp</code>
 * <p>The type default terminal op interface.</p>
 * @param <E_IN> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <R>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
interface DefaultTerminalOp<E_IN, R> {
    /**
     * <code>inputShape</code>
     * <p>the shape method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>the shape return object is <code>DefaultStreamShape</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
     */
    default DefaultStreamShape inputShape() {
        return DefaultStreamShape.REFERENCE;
    }

    /**
     * <code>getOpFlags</code>
     * <p>the op flags getter method.</p>
     * @return int <p>the op flags return object is <code>int</code> type.</p>
     */
    default int getOpFlags() {
        return 0;
    }

    /**
     * <code>evaluateParallel</code>
     * <p>the parallel method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>the helper parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return R <p>the parallel return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default <P_IN> R evaluateParallel(DefaultPipelineHelper<E_IN> helper,
                                      DefaultSpliterator<P_IN> spliterator) throws RestException {
        if (DefaultTripwire.ENABLED)
            DefaultTripwire.trip(getClass(), "{0} triggering TerminalOp.evaluateParallel serial default");
        return evaluateSequential(helper, spliterator);
    }

    /**
     * <code>evaluateSequential</code>
     * <p>the sequential method.</p>
     * @param <P_IN>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>the helper parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return R <p>the sequential return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    <P_IN> R evaluateSequential(DefaultPipelineHelper<E_IN> helper,
                                DefaultSpliterator<P_IN> spliterator) throws RestException;
}
