package io.github.nichetoolkit.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

}
