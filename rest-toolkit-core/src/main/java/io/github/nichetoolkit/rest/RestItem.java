package io.github.nichetoolkit.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestItem</code>
 * <p>The rest item interface.</p>
 * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <V>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rest.RestValue
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public interface RestItem<K,V,I> extends RestValue<K,V> {

    /**
     * <code>getItem</code>
     * <p>The get item getter method.</p>
     * @return I <p>The get item return object is <code>I</code> type.</p>
     */
    I getItem();

    /**
     * <code>parseItem</code>
     * <p>The parse item method.</p>
     * @param <T>  {@link io.github.nichetoolkit.rest.RestItem} <p>The generic parameter is <code>RestItem</code> type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param item {@link java.lang.String} <p>The item parameter is <code>String</code> type.</p>
     * @see  java.lang.Class
     * @see  java.lang.String
     * @see  java.lang.SuppressWarnings
     * @return T <p>The parse item return object is <code>T</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestItem<K,V,I>,K,V,I> T parseItem(Class<T> clazz, String item) {
        if (item != null && clazz.isEnum()) {
            Map<I, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestItem::getItem, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(item);
        } else {
            return null;
        }
    }

    /**
     * <code>parseItem</code>
     * <p>The parse item method.</p>
     * @param <T>  {@link io.github.nichetoolkit.rest.RestItem} <p>The generic parameter is <code>RestItem</code> type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <V>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <I>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param item {@link java.lang.String} <p>The item parameter is <code>String</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  java.lang.SuppressWarnings
     * @return T <p>The parse item return object is <code>T</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestItem<K,V,I>,K,V,I> T parseItem(Collection<T> values, String item) {
        if (item != null && values != null && !values.isEmpty()) {
            Map<I, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestItem::getItem, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(item);
        } else {
            return null;
        }
    }

}
