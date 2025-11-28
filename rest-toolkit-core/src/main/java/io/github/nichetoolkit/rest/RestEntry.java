package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.ComparatorActuator;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;


/**
 * <code>RestEntry</code>
 * <p>The rest entry interface.</p>
 * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.Map.Entry
 * @since Jdk17
 */
public interface RestEntry<K, V> extends Map.Entry<K, V> {

    @Override
    default V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    /**
     * <code>comparingByKey</code>
     * <p>The comparing by key method.</p>
     * @param <K> {@link java.lang.Comparable} <p>The generic parameter is <code>Comparable</code> type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The comparing by key return object is <code>ComparatorActuator</code> type.</p>
     * @see java.lang.Comparable
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     */
    static <K extends Comparable<? super K>, V> ComparatorActuator<RestEntry<K,V>> comparingByKey() {
        return (ComparatorActuator<RestEntry<K, V>> & Serializable)
                (c1, c2) -> c1.getKey().compareTo(c2.getKey());
    }

    /**
     * <code>comparingByValue</code>
     * <p>The comparing by value method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Comparable} <p>The generic parameter is <code>Comparable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The comparing by value return object is <code>ComparatorActuator</code> type.</p>
     * @see java.lang.Comparable
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     */
    static <K, V extends Comparable<? super V>> ComparatorActuator<RestEntry<K,V>> comparingByValue() {
        return (ComparatorActuator<RestEntry<K, V>> & Serializable)
                (c1, c2) -> c1.getValue().compareTo(c2.getValue());
    }

    /**
     * <code>comparingByKey</code>
     * <p>The comparing by key method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param cmp {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The cmp parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link java.util.Comparator} <p>The comparing by key return object is <code>Comparator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see java.util.Comparator
     */
    static <K, V> Comparator<RestEntry<K, V>> comparingByKey(ComparatorActuator<? super K> cmp) {
        Objects.requireNonNull(cmp);
        return (ComparatorActuator<RestEntry<K, V>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
    }

    /**
     * <code>comparingByValue</code>
     * <p>The comparing by value method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param cmp {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The cmp parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link java.util.Comparator} <p>The comparing by value return object is <code>Comparator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see java.util.Comparator
     */
    static <K, V> Comparator<RestEntry<K, V>> comparingByValue(ComparatorActuator<? super V> cmp) {
        Objects.requireNonNull(cmp);
        return (ComparatorActuator<RestEntry<K, V>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param key   K <p>The key parameter is <code>K</code> type.</p>
     * @param value V <p>The value parameter is <code>V</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEntry} <p>The of return object is <code>RestEntry</code> type.</p>
     */
    static <K, V> RestEntry<K, V> of(K key, V value) {
        return new RestEntry.OfRestEntry<>(key, value);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <K>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestEntry} <p>The entry parameter is <code>RestEntry</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEntry} <p>The of return object is <code>RestEntry</code> type.</p>
     */
    static <K, V> RestEntry<K, V> of(RestEntry<K, V> entry) {
        return new RestEntry.OfRestEntry<>(entry);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEntry} <p>The of null return object is <code>RestEntry</code> type.</p>
     */
    static <K, V> RestEntry<K, V> ofNull() {
        return new RestEntry.OfRestEntry<>();
    }

    /**
     * <code>OfRestEntry</code>
     * <p>The of rest entry class.</p>
     * @param <K> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestValue.OfRestValue
     * @since Jdk17
     */
    class OfRestEntry<K, V> extends RestValue.OfRestValue<K, V> implements RestEntry<K, V> {

        /**
         * <code>OfRestEntry</code>
         * <p>Instantiates a new of rest entry.</p>
         */
        public OfRestEntry() {
        }

        /**
         * <code>OfRestEntry</code>
         * <p>Instantiates a new of rest entry.</p>
         * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
         * @see java.util.Map.Entry
         */
        public OfRestEntry(Map.Entry<K, V> entry) {
            super(entry);
        }

        /**
         * <code>OfRestEntry</code>
         * <p>Instantiates a new of rest entry.</p>
         * @param key   K <p>The key parameter is <code>K</code> type.</p>
         * @param value V <p>The value parameter is <code>V</code> type.</p>
         */
        public OfRestEntry(K key, V value) {
            super(key,value);
        }

    }

}
