package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * <code>ComparatorActuator</code>
 * <p>The type comparator actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface ComparatorActuator<T> extends Comparator<T> {

    /**
     * <code>compare</code>
     * <p>the method.</p>
     * @param o1 T <p>the o 1 parameter is <code>T</code> type.</p>
     * @param o2 T <p>the o 2 parameter is <code>T</code> type.</p>
     * @return int <p>the return object is <code>int</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    int actuate(T o1, T o2) throws RestException;

    @Override
    default int compare(T o1, T o2) {
        try {
            return actuate(o1,o2);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    @Override
    boolean equals(Object obj);


    /**
     * <code>thenComparing</code>
     * <p>the comparing method.</p>
     * @param other {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the other parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the comparing return object is <code>ComparatorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default ComparatorActuator<T> thenComparing(ComparatorActuator<? super T> other) throws RestException  {
        Objects.requireNonNull(other);
        return (ComparatorActuator<T> & Serializable) (c1, c2) -> {
            int res = compare(c1, c2);
            return (res != 0) ? res : other.compare(c1, c2);
        };
    }

    /**
     * <code>thenComparing</code>
     * <p>the comparing method.</p>
     * @param <U>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param keyExtractor  {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the key extractor parameter is <code>FunctionActuator</code> type.</p>
     * @param keyComparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the key comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the comparing return object is <code>ComparatorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default <U> ComparatorActuator<T> thenComparing(
            FunctionActuator<? super T, ? extends U> keyExtractor,
            ComparatorActuator<? super U> keyComparator) throws RestException {
        return thenComparing(comparing(keyExtractor, keyComparator));
    }


    /**
     * <code>comparing</code>
     * <p>the method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <U>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param keyExtractor  {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the key extractor parameter is <code>FunctionActuator</code> type.</p>
     * @param keyComparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the key comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the return object is <code>ComparatorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <T, U> ComparatorActuator<T> comparing(
            FunctionActuator<? super T, ? extends U> keyExtractor,
            ComparatorActuator<? super U> keyComparator) throws RestException
    {
        Objects.requireNonNull(keyExtractor);
        Objects.requireNonNull(keyComparator);
        return (ComparatorActuator<T> & Serializable)
                (c1, c2) -> keyComparator.compare(keyExtractor.actuate(c1),
                        keyExtractor.actuate(c2));
    }
}
