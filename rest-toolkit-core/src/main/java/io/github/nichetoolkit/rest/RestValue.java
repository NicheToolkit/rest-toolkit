package io.github.nichetoolkit.rest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>RiceValue</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestValue<K, V> extends RestKey<K> {

    V getValue();

    default Map<K, V> entry() {
        return Collections.singletonMap(this.getKey(), this.getValue());
    }

    static <T extends RestValue<K, V>, K, V> List<T> lists(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    static <T extends RestValue<K, V>, K, V> List<Map<K, V>> entries(Class<T> clazz) {
        return lists(clazz).stream().map(RestValue::entry).distinct().collect(Collectors.toList());
    }


    static <T extends RestValue<K, V>, K, V> List<RestEnum> mapKey(Class<T> clazz) {
        return lists(clazz).stream().map(RestEnum::mapKey).distinct().collect(Collectors.toList());
    }

    static <T extends RestValue<K, V>, K, V> List<RestEnum> mapValue(Class<T> clazz) {
        return lists(clazz).stream().map(RestEnum::mapValue).distinct().collect(Collectors.toList());
    }

    static <T extends RestValue<K, V>, K, V> List<RestEnum> mapBean(Class<T> clazz) {
        return lists(clazz).stream().map(RestEnum::mapBean).distinct().collect(Collectors.toList());
    }

    static <T extends RestValue<K, V>, K, V> List<V> values(Class<T> clazz) {
        return lists(clazz).stream().map(RestValue::getValue).distinct().collect(Collectors.toList());
    }

    static <T extends RestValue<K, V>, K, V> Boolean confirm(Class<T> clazz, K key) {
        return Optional.ofNullable(parseKey(clazz, key)).isPresent();
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseKey(Class<T> clazz, K key) {
        if (key != null && clazz.isEnum()) {
            Map<K, T> keyEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestValue::getKey, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return keyEnumMap.get(key);
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseKey(Collection<T> values, K key) {
        if (key != null && values != null && values.size() > 0) {
            Map<K, T> keyEnumMap = values.stream().collect(Collectors.toMap(RestKey::getKey, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return keyEnumMap.get(key);
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseValue(Class<T> clazz, V value) {
        if (value != null && clazz.isEnum()) {
            Map<V, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestValue::getValue, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(value);
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseValue(Collection<T> values, V value) {
        if (value != null && values != null && values.size() > 0) {
            Map<V, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestValue::getValue, Function.identity()));
            return valueEnumMap.get(value);
        }
        return null;
    }

}
