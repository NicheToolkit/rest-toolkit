package io.github.nichetoolkit.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestMeant</code>
 * <p>The rest meant interface.</p>
 * @see  io.github.nichetoolkit.rest.RestValue
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public interface RestMeant extends RestValue<Integer, String> {

    /**
     * <code>getMeant</code>
     * <p>The get meant getter method.</p>
     * @return  {@link java.lang.String} <p>The get meant return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String getMeant();

    /**
     * <code>parseMeant</code>
     * <p>The parse meant method.</p>
     * @param <T>  {@link io.github.nichetoolkit.rest.RestMeant} <p>The generic parameter is <code>RestMeant</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param meant {@link java.lang.String} <p>The meant parameter is <code>String</code> type.</p>
     * @see  java.lang.Class
     * @see  java.lang.String
     * @see  java.lang.SuppressWarnings
     * @return T <p>The parse meant return object is <code>T</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestMeant> T parseMeant(Class<T> clazz, String meant) {
        if (meant != null && clazz.isEnum()) {
            Map<String, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestMeant::getMeant, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(meant);
        } else {
            return null;
        }
    }

    /**
     * <code>parseMeant</code>
     * <p>The parse meant method.</p>
     * @param <T>  {@link io.github.nichetoolkit.rest.RestMeant} <p>The generic parameter is <code>RestMeant</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param meant {@link java.lang.String} <p>The meant parameter is <code>String</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  java.lang.SuppressWarnings
     * @return T <p>The parse meant return object is <code>T</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestMeant> T parseMeant(Collection<T> values, String meant) {
        if (meant != null && values != null && !values.isEmpty()) {
            Map<String, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestMeant::getMeant, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(meant);
        } else {
            return null;
        }
    }

}
