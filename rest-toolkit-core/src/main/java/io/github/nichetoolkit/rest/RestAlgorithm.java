package io.github.nichetoolkit.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>RestAlgorithm</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestAlgorithm<A> extends RestValue<Integer, String> {

    A getAlgorithm();

    static <A,T extends RestAlgorithm<A>> T parseAlgorithm(Class<T> clazz, A algorithm) {
        if (algorithm != null && clazz.isEnum()) {
            Map<A,T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestAlgorithm::getAlgorithm, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(algorithm);
        } else {
            return null;
        }
    }

}
