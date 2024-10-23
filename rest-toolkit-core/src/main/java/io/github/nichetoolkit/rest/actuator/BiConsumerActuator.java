package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * <code>BiConsumerActuator</code>
 * <p>The bi consumer actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.function.BiConsumer
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface BiConsumerActuator<T, U> extends BiConsumer<T, U> {

    /**
     * <code>actuate</code>
     * <p>The actuate method.</p>
     * @param t T <p>The t parameter is <code>T</code> type.</p>
     * @param u U <p>The u parameter is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void actuate(T t, U u) throws RestException;

    @Override
    default void accept(T t, U u) {
        try {
            actuate(t,u);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>andThen</code>
     * <p>The and then method.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The after parameter is <code>BiConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The and then return object is <code>BiConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default BiConsumerActuator<T, U> andThen(BiConsumerActuator<? super T, ? super U> after) throws RestException  {
        Objects.requireNonNull(after);
        return (l, r) -> {
            actuate(l, r);
            after.actuate(l, r);
        };
    }
}
