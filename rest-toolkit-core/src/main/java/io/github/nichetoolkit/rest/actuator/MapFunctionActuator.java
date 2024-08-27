package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>MapFunctionActuator</code>
 * <p>The type map function actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <S> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <R> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface MapFunctionActuator<T, U, S, R> {

    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t      {@link T} <p>the t parameter is <code>T</code> type.</p>
     * @param u      {@link U} <p>the u parameter is <code>U</code> type.</p>
     * @param sArray {@link S} <p>the s array parameter is <code>S</code> type.</p>
     * @return {@link R} <p>the return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    R actuate(T t, U u, S... sArray) throws RestException;

    /**
     * <code>andThen</code>
     * <p>the then method.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the after parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapFunctionActuator} <p>the then return object is <code>MapFunctionActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     */
    default <V> MapFunctionActuator<T, U, S, V> andThen(FunctionActuator<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, S... sArray) -> after.actuate(actuate(t, u, sArray));
    }
}
