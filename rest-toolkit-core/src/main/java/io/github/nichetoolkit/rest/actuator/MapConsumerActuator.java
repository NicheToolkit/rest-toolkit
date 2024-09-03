package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>MapConsumerActuator</code>
 * <p>The type map consumer actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <S> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface MapConsumerActuator<T, U, S> {

    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t      T <p>the t parameter is <code>T</code> type.</p>
     * @param u      U <p>the u parameter is <code>U</code> type.</p>
     * @param sArray S <p>the s array parameter is <code>S</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    void actuate(T t, U u, S... sArray) throws RestException;

    /**
     * <code>andThen</code>
     * <p>the then method.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.MapConsumerActuator} <p>the after parameter is <code>MapConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapConsumerActuator} <p>the then return object is <code>MapConsumerActuator</code> type.</p>
     */
    default MapConsumerActuator<T, U, S> andThen(MapConsumerActuator<? super T, ? super U, ? super S> after) {
        Objects.requireNonNull(after);
        return (l, r ,s) -> {
            actuate(l, r, s);
            after.actuate(l, r, s);
        };
    }
}
