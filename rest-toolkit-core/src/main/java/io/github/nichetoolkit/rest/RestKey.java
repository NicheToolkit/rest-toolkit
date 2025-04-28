package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestKey</code>
 * <p>The rest key interface.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @since Jdk1.8
 */
public interface RestKey<K> extends Serializable {
    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String name() {
        if (getKey() == null) {
            return null;
        }
        return this.getKey().toString();
    }

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
    static <T extends RestKey<K>, K> List<T> values(Class<T> clazz) {
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
    static <T extends RestKey<K>, K> List<K> keys(Class<T> clazz) {
        return values(clazz).stream().map(RestKey::getKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>keys</code>
     * <p>The keys method.</p>
     * @param <T>  {@link io.github.nichetoolkit.rest.RestKey} <p>The generic parameter is <code>RestKey</code> type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param keys T <p>The keys parameter is <code>T</code> type.</p>
     * @return {@link java.util.List} <p>The keys return object is <code>List</code> type.</p>
     * @see java.util.List
     * @see java.lang.SafeVarargs
     */
    @SafeVarargs
    static <T extends RestKey<K>, K> List<K> keys(T... keys) {
        if (GeneralUtils.isEmpty(keys)) {
            return Collections.emptyList();
        }
        return Arrays.stream(keys).map(RestKey::getKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>keys</code>
     * <p>The keys method.</p>
     * @param <T>  {@link io.github.nichetoolkit.rest.RestKey} <p>The generic parameter is <code>RestKey</code> type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param keys {@link java.util.Collection} <p>The keys parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>The keys return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     */
    static <T extends RestKey<K>, K> List<K> keys(Collection<T> keys) {
        if (GeneralUtils.isEmpty(keys)) {
            return Collections.emptyList();
        }
        return keys.stream().map(RestKey::getKey).distinct().collect(Collectors.toList());
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
            Map<K, T> keyEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestKey::getKey, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
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


    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key K <p>The key parameter is <code>K</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestKey} <p>The of return object is <code>RestKey</code> type.</p>
     */
    static <K> RestKey<K> of(K key) {
        return new OfRestKey<>(key);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestKey} <p>The of null return object is <code>RestKey</code> type.</p>
     */
    static <K> RestKey<K> ofNull() {
        return new OfRestKey<>(null);
    }

    /**
     * <code>OfRestKey</code>
     * <p>The of rest key class.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Setter
    class OfRestKey<K> implements RestKey<K> {
        /**
         * <code>key</code>
         * <p>The <code>key</code> field.</p>
         */
        private K key;

        /**
         * <code>OfRestKey</code>
         * <p>Instantiates a new of rest key.</p>
         * @param key {@link io.github.nichetoolkit.rest.RestKey} <p>The key parameter is <code>RestKey</code> type.</p>
         */
        public OfRestKey(RestKey<K> key) {
            this.key = key.getKey();
        }

        /**
         * <code>OfRestKey</code>
         * <p>Instantiates a new of rest key.</p>
         * @param key K <p>The key parameter is <code>K</code> type.</p>
         */
        public OfRestKey(K key) {
            this.key = key;
        }

        @Override
        public K getKey() {
            return this.key;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OfRestKey<?> ofRestKey = (OfRestKey<?>) o;
            return Objects.equals(key, ofRestKey.key);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }


}
