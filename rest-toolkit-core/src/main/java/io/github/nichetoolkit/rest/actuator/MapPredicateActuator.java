package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>MapPredicateActuator</code>
 * <p>The map predicate actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface MapPredicateActuator<T, U, S> {

    /**
     * <code>actuate</code>
     * <p>The actuate method.</p>
     * @param t      T <p>The t parameter is <code>T</code> type.</p>
     * @param u      U <p>The u parameter is <code>U</code> type.</p>
     * @param sArray S <p>The s array parameter is <code>S</code> type.</p>
     * @return boolean <p>The actuate return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    boolean actuate(T t, U u, S... sArray) throws RestException;

    /**
     * <code>and</code>
     * <p>The and method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>The other parameter is <code>MapPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>The and return object is <code>MapPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default MapPredicateActuator<T, U, S> and(MapPredicateActuator<? super T, ? super U, ? super S> other) throws RestException  {
        Objects.requireNonNull(other);
        return (T t, U u, S... sArray) -> actuate(t, u, sArray) && other.actuate(t, u, sArray);
    }

    /**
     * <code>negates</code>
     * <p>The negates method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>The negates return object is <code>MapPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default MapPredicateActuator<T, U, S> negates() throws RestException  {
        return (T t, U u, S... sArray) -> !actuate(t, u, sArray);
    }

    /**
     * <code>or</code>
     * <p>The or method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>The other parameter is <code>MapPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>The or return object is <code>MapPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default MapPredicateActuator<T, U, S> or(MapPredicateActuator<? super T, ? super U, ? super S> other) throws RestException  {
        Objects.requireNonNull(other);
        return (T t, U u, S... sArray) -> actuate(t, u, sArray) || other.actuate(t, u, sArray);
    }
}
