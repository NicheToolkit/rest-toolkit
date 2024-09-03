package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>PredicateActuator</code>
 * <p>The type predicate actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface PredicateActuator<T> {
    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param m T <p>the m parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the return object is <code>Boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Boolean
     * @see io.github.nichetoolkit.rest.RestException
     */
    Boolean actuate(T m) throws RestException;


    /**
     * <code>and</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the other parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the return object is <code>PredicateActuator</code> type.</p>
     */
    default PredicateActuator<T> and(PredicateActuator<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> actuate(t) && other.actuate(t);
    }

    /**
     * <code>negate</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the return object is <code>PredicateActuator</code> type.</p>
     */
    default PredicateActuator<T> negate() {
        return (t) -> !actuate(t);
    }


    /**
     * <code>or</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the other parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the return object is <code>PredicateActuator</code> type.</p>
     */
    default PredicateActuator<T> or(PredicateActuator<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> actuate(t) || other.actuate(t);
    }

    /**
     * <code>isEqual</code>
     * <p>the equal method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param targetRef {@link java.lang.Object} <p>the target ref parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the equal return object is <code>PredicateActuator</code> type.</p>
     * @see java.lang.Object
     */
    static <T> PredicateActuator<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : targetRef::equals;
    }
}
