package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <p>MapPredicateActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface MapPredicateActuator<T, U, S> {

    @SuppressWarnings(value = "unchecked")
    boolean actuate(T t, U u, S... sArray) throws RestException;

    default MapPredicateActuator<T, U, S> and(MapPredicateActuator<? super T, ? super U, ? super S> other) {
        Objects.requireNonNull(other);
        return (T t, U u, S... sArray) -> actuate(t, u, sArray) && other.actuate(t, u, sArray);
    }

    default MapPredicateActuator<T, U, S> negate() {
        return (T t, U u, S... sArray) -> !actuate(t, u, sArray);
    }

    default MapPredicateActuator<T, U, S> or(MapPredicateActuator<? super T, ? super U, ? super S> other) {
        Objects.requireNonNull(other);
        return (T t, U u, S... sArray) -> actuate(t, u, sArray) || other.actuate(t, u, sArray);
    }
}
