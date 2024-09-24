package io.github.nichetoolkit.rest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestArithmetic</code>
 * <p>The type rest arithmetic interface.</p>
 * @param <A> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public interface RestArithmetic<A> extends RestValue<Long, String> {

    /**
     * <code>getArithmetic</code>
     * <p>the arithmetic getter method.</p>
     * @return A <p>the arithmetic return object is <code>A</code> type.</p>
     */
    A getArithmetic();

    /**
     * <code>parseArithmetic</code>
     * <p>the arithmetic method.</p>
     * @param <A>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>        {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param arithmetic A <p>the arithmetic parameter is <code>A</code> type.</p>
     * @return T <p>the arithmetic return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    @SuppressWarnings("Duplicates")
    static <A,T extends RestArithmetic<A>> T parseArithmetic(Class<T> clazz, A arithmetic) {
        if (arithmetic != null && clazz.isEnum()) {
            Map<A,T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestArithmetic::getArithmetic, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(arithmetic);
        } else {
            return null;
        }
    }

}
