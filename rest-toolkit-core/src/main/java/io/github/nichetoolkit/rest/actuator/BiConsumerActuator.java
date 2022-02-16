package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <p>BiConsumerActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface BiConsumerActuator<T, U> {

    void actuate(T t, U u) throws RestException;

    default BiConsumerActuator<T, U> andThen(BiConsumerActuator<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (l, r) -> {
            actuate(l, r);
            after.actuate(l, r);
        };
    }
}
