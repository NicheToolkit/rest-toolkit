package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <p>MapConsumerActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface MapConsumerActuator<T, U, S> {

    @SuppressWarnings(value = "unchecked")
    void actuate(T t, U u, S... sArray) throws RestException;

    default MapConsumerActuator<T, U, S> andThen(MapConsumerActuator<? super T, ? super U, ? super S> after) {
        Objects.requireNonNull(after);
        return (l, r ,s) -> {
            actuate(l, r, s);
            after.actuate(l, r, s);
        };
    }
}
