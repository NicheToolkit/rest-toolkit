package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.actuator.ComparatorActuator;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;


/**
 * <code>RestEntry</code>
 * <p>The type rest entry interface.</p>
 * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <V> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.Map.Entry
 * @since Jdk1.8
 */
public interface RestEntry<K, V> extends Map.Entry<K, V> {

    /**
     * <code>comparingByKey</code>
     * <p>the by key method.</p>
     * @param <K> {@link java.lang.Comparable} <p>the generic parameter is <code>Comparable</code> type.</p>
     * @param <V> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the by key return object is <code>ComparatorActuator</code> type.</p>
     * @see java.lang.Comparable
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     */
    static <K extends Comparable<? super K>, V> ComparatorActuator<RestEntry<K,V>> comparingByKey() {
        return (ComparatorActuator<RestEntry<K, V>> & Serializable)
                (c1, c2) -> c1.getKey().compareTo(c2.getKey());
    }

    /**
     * <code>comparingByValue</code>
     * <p>the by value method.</p>
     * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V> {@link java.lang.Comparable} <p>the generic parameter is <code>Comparable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the by value return object is <code>ComparatorActuator</code> type.</p>
     * @see java.lang.Comparable
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     */
    static <K, V extends Comparable<? super V>> ComparatorActuator<RestEntry<K,V>> comparingByValue() {
        return (ComparatorActuator<RestEntry<K, V>> & Serializable)
                (c1, c2) -> c1.getValue().compareTo(c2.getValue());
    }

    /**
     * <code>comparingByKey</code>
     * <p>the by key method.</p>
     * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param cmp {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the cmp parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link java.util.Comparator} <p>the by key return object is <code>Comparator</code> type.</p>
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
     * <p>the by value method.</p>
     * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param cmp {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>the cmp parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link java.util.Comparator} <p>the by value return object is <code>Comparator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see java.util.Comparator
     */
    static <K, V> Comparator<RestEntry<K, V>> comparingByValue(ComparatorActuator<? super V> cmp) {
        Objects.requireNonNull(cmp);
        return (ComparatorActuator<RestEntry<K, V>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
    }

}
