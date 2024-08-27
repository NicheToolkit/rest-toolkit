package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>BiConsumerActuator</code>
 * <p>The type bi consumer actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface BiConsumerActuator<T, U> {

    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t {@link T} <p>the t parameter is <code>T</code> type.</p>
     * @param u {@link U} <p>the u parameter is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void actuate(T t, U u) throws RestException;

    /**
     * <code>andThen</code>
     * <p>the then method.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the after parameter is <code>BiConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the then return object is <code>BiConsumerActuator</code> type.</p>
     */
    default BiConsumerActuator<T, U> andThen(BiConsumerActuator<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (l, r) -> {
            actuate(l, r);
            after.actuate(l, r);
        };
    }
}
