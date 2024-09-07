package io.github.nichetoolkit.rest.stream;


import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Objects;


/**
 * <code>DefaultSink</code>
 * <p>The type default sink interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
 * @since Jdk1.8
 */
public interface DefaultSink<T> extends ConsumerActuator<T> {
    /**
     * <code>begin</code>
     * <p>the method.</p>
     * @param size long <p>the size parameter is <code>long</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void begin(long size) throws RestException {}

    /**
     * <code>end</code>
     * <p>the method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void end() throws RestException {}

    /**
     * <code>cancellationRequested</code>
     * <p>the requested method.</p>
     * @return boolean <p>the requested return object is <code>boolean</code> type.</p>
     */
    default boolean cancellationRequested() {
        return false;
    }

    /**
     * <code>ChainedReference</code>
     * <p>The type chained reference class.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <E_OUT> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    abstract class ChainedReference<T, E_OUT> implements DefaultSink<T> {
        /**
         * <code>downstream</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the <code>downstream</code> field.</p>
         */
        protected final DefaultSink<? super E_OUT> downstream;

        /**
         * <code>ChainedReference</code>
         * Instantiates a new chained reference.
         * @param downstream {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the downstream parameter is <code>DefaultSink</code> type.</p>
         */
        public ChainedReference(DefaultSink<? super E_OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }

        @Override
        public void begin(long size) throws RestException {
            downstream.begin(size);
        }

        @Override
        public void end() throws RestException {
            downstream.end();
        }

        @Override
        public boolean cancellationRequested() {
            return downstream.cancellationRequested();
        }
    }
}
