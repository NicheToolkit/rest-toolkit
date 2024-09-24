package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Iterator;
import java.util.Objects;

/**
 * <code>DefaultIterable</code>
 * <p>The type default iterable interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
interface DefaultIterable<T> {

    /**
     * <code>iterator</code>
     * <p>the method.</p>
     * @return {@link java.util.Iterator} <p>the return object is <code>Iterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Iterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    Iterator<T> iterator() throws RestException;

    /**
     * <code>forEach</code>
     * <p>the each method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the action parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default void forEach(ConsumerActuator<? super T> action) throws RestException {
        Objects.requireNonNull(action);
        for (Iterator<T> it = iterator(); it.hasNext(); ) {
            T t = it.next();
            action.actuate(t);
        }
    }

    /**
     * <code>spliterator</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default DefaultSpliterator<T> spliterator() throws RestException {
        return DefaultSpliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
