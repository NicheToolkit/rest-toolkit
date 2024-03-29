package io.github.nichetoolkit.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>RestKey</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestKey<K> {
    /**
     * 获取名字
     * @return String
     */
    String name();

    /**
     * 获取编码
     * @return K
     */
    K getKey();

    /**
     * T 集合 <K>
     * @return Set<T>
     */
    static <T extends RestKey<K>,K> List<T> values(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    /**
     * K 集合 <K>
     * @return Set<K>
     */
    static <T extends RestKey<K>,K> List<K> keys(Class<T> clazz) {
        return values(clazz).stream().map(RestKey::getKey).distinct().collect(Collectors.toList());
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestKey<K>, K> T parseKey(Class<T> clazz, K key) {
        if (key != null && clazz.isEnum()) {
            Map<K, T> keyEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestKey::getKey, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return keyEnumMap.get(key);
        }
        return null;
    }

}
