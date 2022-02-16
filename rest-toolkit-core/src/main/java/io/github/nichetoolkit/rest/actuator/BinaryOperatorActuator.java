package io.github.nichetoolkit.rest.actuator;

import java.util.Objects;

/**
 * <p>BinaryOperatorActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface BinaryOperatorActuator<T> extends BiFunctionActuator<T,T,T>{

    static <T> BinaryOperatorActuator<T> minBy(ComparatorActuator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    static <T> BinaryOperatorActuator<T> maxBy(ComparatorActuator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
}
