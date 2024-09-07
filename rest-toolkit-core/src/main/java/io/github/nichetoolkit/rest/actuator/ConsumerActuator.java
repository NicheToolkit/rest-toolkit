package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * <code>ConsumerActuator</code>
 * <p>The type consumer actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface ConsumerActuator<T> extends Consumer<T> {
    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t T <p>the t parameter is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void actuate(T t) throws RestException;

    @Override
    default void accept(T t) {
        try {
            actuate(t);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>andThen</code>
     * <p>the then method.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the after parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the then return object is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default ConsumerActuator<T> andThen(ConsumerActuator<? super T> after)  throws RestException  {
        Objects.requireNonNull(after);
        return (T t) -> { actuate(t); after.actuate(t); };
    }
}
