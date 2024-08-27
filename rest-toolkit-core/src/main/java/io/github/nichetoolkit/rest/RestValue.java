package io.github.nichetoolkit.rest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestValue</code>
 * <p>The type rest value interface.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <V> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestKey
 * @since Jdk1.8
 */
public interface RestValue<K, V> extends RestKey<K> {

    /**
     * <code>getValue</code>
     * <p>the value getter method.</p>
     * @return {@link V} <p>the value return object is <code>V</code> type.</p>
     */
    V getValue();

    /**
     * <code>entry</code>
     * <p>the method.</p>
     * @return {@link java.util.Map} <p>the return object is <code>Map</code> type.</p>
     * @see java.util.Map
     */
    default Map<K, V> entry() {
        return Collections.singletonMap(this.getKey(), this.getValue());
    }

    /**
     * <code>lists</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<T> lists(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    /**
     * <code>entries</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<Map<K, V>> entries(Class<T> clazz) {
        return lists(clazz).stream().map(RestValue::entry).distinct().collect(Collectors.toList());
    }


    /**
     * <code>mapKey</code>
     * <p>the key method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the key return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<RestEnum> mapKey(Class<T> clazz) {
        return lists(clazz).stream().map(RestEnum::mapKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>mapValue</code>
     * <p>the value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<RestEnum> mapValue(Class<T> clazz) {
        return lists(clazz).stream().map(RestEnum::mapValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>mapBean</code>
     * <p>the bean method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the bean return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<RestEnum> mapBean(Class<T> clazz) {
        return lists(clazz).stream().map(RestEnum::mapBean).distinct().collect(Collectors.toList());
    }

    /**
     * <code>values</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<V> values(Class<T> clazz) {
        return lists(clazz).stream().map(RestValue::getValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>confirm</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param key   {@link K} <p>the key parameter is <code>K</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the return object is <code>Boolean</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Boolean
     */
    static <T extends RestValue<K, V>, K, V> Boolean confirm(Class<T> clazz, K key) {
        return Optional.ofNullable(parseKey(clazz, key)).isPresent();
    }

    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param key   {@link K} <p>the key parameter is <code>K</code> type.</p>
     * @return {@link T} <p>the key return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseKey(Class<T> clazz, K key) {
        if (key != null && clazz.isEnum()) {
            Map<K, T> keyEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestValue::getKey, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return keyEnumMap.get(key);
        }
        return null;
    }

    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param values {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param key    {@link K} <p>the key parameter is <code>K</code> type.</p>
     * @return {@link T} <p>the key return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseKey(Collection<T> values, K key) {
        if (key != null && values != null && !values.isEmpty()) {
            Map<K, T> keyEnumMap = values.stream().collect(Collectors.toMap(RestKey::getKey, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return keyEnumMap.get(key);
        }
        return null;
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param value {@link V} <p>the value parameter is <code>V</code> type.</p>
     * @return {@link T} <p>the value return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseValue(Class<T> clazz, V value) {
        if (value != null && clazz.isEnum()) {
            Map<V, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestValue::getValue, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(value);
        }
        return null;
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestValue} <p>the generic parameter is <code>RestValue</code> type.</p>
     * @param <K>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param values {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param value  {@link V} <p>the value parameter is <code>V</code> type.</p>
     * @return {@link T} <p>the value return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseValue(Collection<T> values, V value) {
        if (value != null && values != null && !values.isEmpty()) {
            Map<V, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestValue::getValue, Function.identity()));
            return valueEnumMap.get(value);
        }
        return null;
    }

}
