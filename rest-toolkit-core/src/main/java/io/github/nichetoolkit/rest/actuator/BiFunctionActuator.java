package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * <code>BiFunctionActuator</code>
 * <p>The type bi function actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <R> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.function.BiFunction
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface BiFunctionActuator<T, U, R> extends BiFunction<T, U, R> {
    /**
     * <code>actuate</code>
     * <p>The method.</p>
     * @param t T <p>The t parameter is <code>T</code> type.</p>
     * @param u U <p>The u parameter is <code>U</code> type.</p>
     * @return R <p>The return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    R actuate(T t, U u) throws RestException;

    @Override
    default R apply(T t, U u) {
        try {
            return actuate(t,u);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>andThen</code>
     * <p>The then method.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The after parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The then return object is <code>BiFunctionActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default <V> BiFunctionActuator<T, U, V> andThen(FunctionActuator<? super R, ? extends V> after) throws RestException {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.actuate(actuate(t, u));
    }
}
