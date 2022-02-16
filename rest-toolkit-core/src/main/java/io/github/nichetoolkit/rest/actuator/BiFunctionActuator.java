package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <p>BiFunctionActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface BiFunctionActuator<T, U, R> {
    R actuate(T t, U u) throws RestException;

    default <V> BiFunctionActuator<T, U, V> andThen(FunctionActuator<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.actuate(actuate(t, u));
    }
}
