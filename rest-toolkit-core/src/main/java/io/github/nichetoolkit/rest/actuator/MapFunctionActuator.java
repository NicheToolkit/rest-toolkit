package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <p>MapFunctionActuator</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface MapFunctionActuator<T, U, S, R> {

    @SuppressWarnings(value = "unchecked")
    R actuate(T t, U u, S... sArray) throws RestException;

    default <V> MapFunctionActuator<T, U, S, V> andThen(FunctionActuator<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, S... sArray) -> after.actuate(actuate(t, u, sArray));
    }
}
