package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * <code>BinaryOperatorActuator</code>
 * <p>The type binary operator actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
 * @see java.util.function.BinaryOperator
 * @since Jdk1.8
 */
public interface BinaryOperatorActuator<T> extends BiFunctionActuator<T,T,T>, BinaryOperator<T> {

    /**
     * <code>minBy</code>
     * <p>the by method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param comparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>the by return object is <code>BinaryOperatorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <T> BinaryOperatorActuator<T> minBy(ComparatorActuator<? super T> comparator)  throws RestException {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * <code>maxBy</code>
     * <p>the by method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param comparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>the by return object is <code>BinaryOperatorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <T> BinaryOperatorActuator<T> maxBy(ComparatorActuator<? super T> comparator) throws RestException  {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}
