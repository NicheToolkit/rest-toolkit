package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * <code>MapPredicateActuator</code>
 * <p>The type map predicate actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <U> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <S> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface MapPredicateActuator<T, U, S> {

    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t      T <p>the t parameter is <code>T</code> type.</p>
     * @param u      U <p>the u parameter is <code>U</code> type.</p>
     * @param sArray S <p>the s array parameter is <code>S</code> type.</p>
     * @return boolean <p>the return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings(value = "unchecked")
    boolean actuate(T t, U u, S... sArray) throws RestException;

    /**
     * <code>and</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>the other parameter is <code>MapPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>the return object is <code>MapPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default MapPredicateActuator<T, U, S> and(MapPredicateActuator<? super T, ? super U, ? super S> other) throws RestException  {
        Objects.requireNonNull(other);
        return (T t, U u, S... sArray) -> actuate(t, u, sArray) && other.actuate(t, u, sArray);
    }

    /**
     * <code>negates</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>the return object is <code>MapPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default MapPredicateActuator<T, U, S> negates() throws RestException  {
        return (T t, U u, S... sArray) -> !actuate(t, u, sArray);
    }

    /**
     * <code>or</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>the other parameter is <code>MapPredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.MapPredicateActuator} <p>the return object is <code>MapPredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default MapPredicateActuator<T, U, S> or(MapPredicateActuator<? super T, ? super U, ? super S> other) throws RestException  {
        Objects.requireNonNull(other);
        return (T t, U u, S... sArray) -> actuate(t, u, sArray) || other.actuate(t, u, sArray);
    }
}
