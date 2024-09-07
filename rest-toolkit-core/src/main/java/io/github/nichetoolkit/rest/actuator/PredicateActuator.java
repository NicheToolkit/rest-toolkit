package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * <code>PredicateActuator</code>
 * <p>The type predicate actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.function.Predicate
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface PredicateActuator<T> extends Predicate<T> {
    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @param t T <p>the t parameter is <code>T</code> type.</p>
     * @return boolean <p>the return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean actuate(T t) throws RestException;

    @Override
    default boolean test(T t) {
        try {
            return actuate(t);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>and</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the other parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the return object is <code>PredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default PredicateActuator<T> and(PredicateActuator<? super T> other) throws RestException  {
        Objects.requireNonNull(other);
        return (t) -> actuate(t) && other.actuate(t);
    }

    /**
     * <code>negates</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the return object is <code>PredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default PredicateActuator<T> negates() throws RestException  {
        return (t) -> !actuate(t);
    }


    /**
     * <code>or</code>
     * <p>the method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the other parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the return object is <code>PredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default PredicateActuator<T> or(PredicateActuator<? super T> other) throws RestException  {
        Objects.requireNonNull(other);
        return (t) -> actuate(t) || other.actuate(t);
    }

    /**
     * <code>isEqual</code>
     * <p>the equal method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param targetRef {@link java.lang.Object} <p>the target ref parameter is <code>Object</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>the equal return object is <code>PredicateActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <T> PredicateActuator<T> isEqual(Object targetRef) throws RestException  {
        return (null == targetRef)
                ? Objects::isNull
                : targetRef::equals;
    }
}
