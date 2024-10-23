package io.github.nichetoolkit.rest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestKey</code>
 * <p>The rest key interface.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestKey<K> {
    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String name();

    /**
     * <code>getKey</code>
     * <p>The get key getter method.</p>
     * @return K <p>The get key return object is <code>K</code> type.</p>
     */
    K getKey();

    /**
     * <code>values</code>
     * <p>The values method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestKey} <p>The generic parameter is <code>RestKey</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The values return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestKey<K>,K> List<T> values(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    /**
     * <code>keys</code>
     * <p>The keys method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestKey} <p>The generic parameter is <code>RestKey</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The keys return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestKey<K>,K> List<K> keys(Class<T> clazz) {
        return values(clazz).stream().map(RestKey::getKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestKey} <p>The generic parameter is <code>RestKey</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param key   K <p>The key parameter is <code>K</code> type.</p>
     * @return T <p>The parse key return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestKey<K>, K> T parseKey(Class<T> clazz, K key) {
        if (key != null && clazz.isEnum()) {
            Map<K, T> keyEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestKey::getKey, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return keyEnumMap.get(key);
        }
        return null;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestKey} <p>The generic parameter is <code>RestKey</code> type.</p>
     * @param <K>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param key    K <p>The key parameter is <code>K</code> type.</p>
     * @return T <p>The parse key return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestKey<K>, K> T parseKey(Collection<T> values, K key) {
        if (key != null && values != null && !values.isEmpty()) {
            Map<K, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestKey::getKey, Function.identity()));
            return valueEnumMap.get(key);
        }
        return null;
    }

}
