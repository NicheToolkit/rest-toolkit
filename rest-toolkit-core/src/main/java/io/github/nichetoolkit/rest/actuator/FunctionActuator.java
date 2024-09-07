package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.Function;

/**
 * <code>FunctionActuator</code>
 * <p>The type function actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <R> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.function.Function
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface FunctionActuator<T,R> extends Function<T,R> {
    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t T <p>the t parameter is <code>T</code> type.</p>
     * @return R <p>the return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    R actuate(T t) throws RestException;

    @Override
    default R apply(T t) {
        try {
            return actuate(t);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>compose</code>
     * <p>the method.</p>
     * @param <V>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param before {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the before parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the return object is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default <V> FunctionActuator<V, R> compose(FunctionActuator<? super V, ? extends T> before) throws RestException  {
        Objects.requireNonNull(before);
        return (V v) -> actuate(before.actuate(v));
    }

    /**
     * <code>andThen</code>
     * <p>the then method.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the after parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the then return object is <code>FunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default <V> FunctionActuator<T, V> andThen(FunctionActuator<? super R, ? extends V> after) throws RestException  {
        Objects.requireNonNull(after);
        return (T t) -> after.actuate(actuate(t));
    }

    /**
     * <code>identity</code>
     * <p>the method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the return object is <code>FunctionActuator</code> type.</p>
     */
    static <T> FunctionActuator<T, T> identity() {
        return t -> t;
    }

}
