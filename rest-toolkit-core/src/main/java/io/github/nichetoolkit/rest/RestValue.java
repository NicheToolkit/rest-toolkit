package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.pack.EnumPack;
import io.github.nichetoolkit.rest.pack.ViewPack;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestValue</code>
 * <p>The rest value interface.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestKey
 * @since Jdk1.8
 */
public interface RestValue<K, V> extends RestKey<K> {

    /**
     * <code>getValue</code>
     * <p>The get value getter method.</p>
     * @return V <p>The get value return object is <code>V</code> type.</p>
     */
    V getValue();

    /**
     * <code>entry</code>
     * <p>The entry method.</p>
     * @return {@link java.util.Map} <p>The entry return object is <code>Map</code> type.</p>
     * @see java.util.Map
     */
    default Map<K, V> entry() {
        return Collections.singletonMap(this.getKey(), this.getValue());
    }

    /**
     * <code>lists</code>
     * <p>The lists method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The lists return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<T> lists(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    /**
     * <code>entries</code>
     * <p>The entries method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The entries return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<Map<K, V>> entries(Class<T> clazz) {
        return lists(clazz).stream().map(RestValue::entry).distinct().collect(Collectors.toList());
    }

    /**
     * <code>nameKey</code>
     * <p>The name key method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The name key return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<ViewPack> nameKey(Class<T> clazz) {
        return lists(clazz).stream().map(ViewPack::nameKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>nameValue</code>
     * <p>The name value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The name value return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<ViewPack> nameValue(Class<T> clazz) {
        return lists(clazz).stream().map(ViewPack::nameValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>keyValue</code>
     * <p>The key value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The key value return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<ViewPack> keyValue(Class<T> clazz) {
        return lists(clazz).stream().map(ViewPack::keyValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>valueKey</code>
     * <p>The value key method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The value key return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<ViewPack> valueKey(Class<T> clazz) {
        return lists(clazz).stream().map(ViewPack::valueKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>packEnum</code>
     * <p>The pack enum method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The pack enum return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<EnumPack> packEnum(Class<T> clazz) {
        return lists(clazz).stream().map(EnumPack::fromValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>values</code>
     * <p>The values method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The values return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<V> values(Class<T> clazz) {
        return lists(clazz).stream().map(RestValue::getValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>values</code>
     * <p>The values method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param values T <p>The values parameter is <code>T</code> type.</p>
     * @return {@link java.util.List} <p>The values return object is <code>List</code> type.</p>
     * @see java.util.List
     * @see java.lang.SafeVarargs
     */
    @SafeVarargs
    static <T extends RestValue<K, V>, K, V> List<V> values(T... values) {
        if (GeneralUtils.isEmpty(values)) {
            return Collections.emptyList();
        }
        return Arrays.stream(values).map(RestValue::getValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>values</code>
     * <p>The values method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>The values return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see java.util.List
     */
    static <T extends RestValue<K, V>, K, V> List<V> values(Collection<T> values) {
        if (GeneralUtils.isEmpty(values)) {
            return Collections.emptyList();
        }
        return values.stream().map(RestValue::getValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>confirm</code>
     * <p>The confirm method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param key   K <p>The key parameter is <code>K</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The confirm return object is <code>Boolean</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Boolean
     */
    static <T extends RestValue<K, V>, K, V> Boolean confirm(Class<T> clazz, K key) {
        return Optional.ofNullable(RestKey.parseKey(clazz, key)).isPresent();
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param value V <p>The value parameter is <code>V</code> type.</p>
     * @return T <p>The parse value return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestValue<K, V>, K, V> T parseValue(Class<T> clazz, V value) {
        if (value != null && clazz.isEnum()) {
            Map<V, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestValue::getValue, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(value);
        }
        return null;
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestValue} <p>The generic parameter is <code>RestValue</code> type.</p>
     * @param <K>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param value  V <p>The value parameter is <code>V</code> type.</p>
     * @return T <p>The parse value return object is <code>T</code> type.</p>
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

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key   K <p>The key parameter is <code>K</code> type.</p>
     * @param value V <p>The value parameter is <code>V</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestValue} <p>The of return object is <code>RestValue</code> type.</p>
     */
    static <K, V> RestValue<K, V> of(K key, V value) {
        return new OfRestValue<>(key, value);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestEntry} <p>The entry parameter is <code>RestEntry</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestValue} <p>The of return object is <code>RestValue</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestEntry
     */
    static <K, V> RestValue<K, V> of(RestEntry<K, V> entry) {
        return new OfRestValue<>(entry.getKey(), entry.getValue());
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestValue} <p>The of null return object is <code>RestValue</code> type.</p>
     */
    static <K, V> RestValue<K, V> ofNull() {
        return new OfRestValue<>();
    }

    /**
     * <code>OfRestValue</code>
     * <p>The of rest value class.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestKey.OfRestKey
     * @since Jdk1.8
     */
    class OfRestValue<K, V> extends RestKey.OfRestKey<K> implements RestValue<K, V> {
        /**
         * <code>value</code>
         * <p>The <code>value</code> field.</p>
         */
        private V value;

        /**
         * <code>OfRestValue</code>
         * <p>Instantiates a new of rest value.</p>
         */
        public OfRestValue() {
        }

        /**
         * <code>OfRestValue</code>
         * <p>Instantiates a new of rest value.</p>
         * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
         * @see java.util.Map.Entry
         */
        public OfRestValue(Map.Entry<K, V> entry) {
            super(entry.getKey());
            this.value = entry.getValue();
        }

        /**
         * <code>OfRestValue</code>
         * <p>Instantiates a new of rest value.</p>
         * @param key   K <p>The key parameter is <code>K</code> type.</p>
         * @param value V <p>The value parameter is <code>V</code> type.</p>
         */
        public OfRestValue(K key, V value) {
            super(key);
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * <code>setValue</code>
         * <p>The set value setter method.</p>
         * @param value V <p>The value parameter is <code>V</code> type.</p>
         * @return V <p>The set value return object is <code>V</code> type.</p>
         */
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            OfRestValue<?, ?> that = (OfRestValue<?, ?>) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), value);
        }
    }


}
