package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>BiPredicateActuator</code>
 * <p>The type bi predicate actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface BiPredicateActuator<T, U> {

    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t {@link T} <p>the t parameter is <code>T</code> type.</p>
     * @param u {@link U} <p>the u parameter is <code>U</code> type.</p>
     * @return {@link boolean} <p>the return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean actuate(T t, U u) throws RestException;

    /**
     * <code>and</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>the other parameter is <code>BiPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>the return object is <code>BiPredicateActuator</code> type.</p>
     */
    default BiPredicateActuator<T, U> and(BiPredicateActuator<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> actuate(t, u) && other.actuate(t, u);
    }

    /**
     * <code>negate</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>the return object is <code>BiPredicateActuator</code> type.</p>
     */
    default BiPredicateActuator<T, U> negate() {
        return (T t, U u) -> !actuate(t, u);
    }

    /**
     * <code>or</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>the other parameter is <code>BiPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BiPredicateActuator} <p>the return object is <code>BiPredicateActuator</code> type.</p>
     */
    default BiPredicateActuator<T, U> or(BiPredicateActuator<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> actuate(t, u) || other.actuate(t, u);
    }
}
