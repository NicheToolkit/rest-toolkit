package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * <code>BiPredicateActuator</code>
 * <p>The type bi predicate actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.function.BiPredicate
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface BiPredicateActuator<T, U> extends BiPredicate<T,U> {

    /**
     * <code>actuate</code>
     * <p>The method.</p>
     * @param t T <p>The t parameter is <code>T</code> type.</p>
     * @param u U <p>The u parameter is <code>U</code> type.</p>
     * @return boolean <p>The return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean actuate(T t, U u) throws RestException;

    @Override
    default boolean test(T t, U u) {
        try {
            return actuate(t,u);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>and</code>
     * <p>The method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>The other parameter is <code>BiPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>The return object is <code>BiPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default BiPredicateActuator<T, U> and(BiPredicateActuator<? super T, ? super U> other)  throws RestException  {
        Objects.requireNonNull(other);
        return (T t, U u) -> actuate(t, u) && other.actuate(t, u);
    }

    /**
     * <code>negates</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>The return object is <code>BiPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default BiPredicateActuator<T, U> negates() throws RestException  {
        return (T t, U u) -> !actuate(t, u);
    }

    /**
     * <code>or</code>
     * <p>The method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>The other parameter is <code>BiPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>The return object is <code>BiPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default BiPredicateActuator<T, U> or(BiPredicateActuator<? super T, ? super U> other) throws RestException {
        Objects.requireNonNull(other);
        return (T t, U u) -> actuate(t, u) || other.actuate(t, u);
    }
}
