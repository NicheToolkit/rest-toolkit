package io.github.nichetoolkit.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestAlgorithm</code>
 * <p>The type rest algorithm interface.</p>
 * @param <A> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public interface RestAlgorithm<A> extends RestValue<Integer, String> {

    /**
     * <code>getAlgorithm</code>
     * <p>the algorithm getter method.</p>
     * @return A <p>the algorithm return object is <code>A</code> type.</p>
     */
    A getAlgorithm();

    /**
     * <code>parseAlgorithm</code>
     * <p>the algorithm method.</p>
     * @param <A>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>       {@link io.github.nichetoolkit.rest.RestAlgorithm} <p>the generic parameter is <code>RestAlgorithm</code> type.</p>
     * @param clazz     {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param algorithm A <p>the algorithm parameter is <code>A</code> type.</p>
     * @return T <p>the algorithm return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    @SuppressWarnings("Duplicates")
    static <A,T extends RestAlgorithm<A>> T parseAlgorithm(Class<T> clazz, A algorithm) {
        if (algorithm != null && clazz.isEnum()) {
            Map<A,T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestAlgorithm::getAlgorithm, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(algorithm);
        } else {
            return null;
        }
    }

}
