package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Iterator;
import java.util.Objects;

/**
 * <code>DefaultIterable</code>
 * <p>The default iterable interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
interface DefaultIterable<T> {

    /**
     * <code>iterator</code>
     * <p>The iterator method.</p>
     * @return {@link java.util.Iterator} <p>The iterator return object is <code>Iterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Iterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    Iterator<T> iterator() throws RestException;

    /**
     * <code>forEach</code>
     * <p>The for each method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The spliterator method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default DefaultSpliterator<T> spliterator() throws RestException {
        return DefaultSpliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
