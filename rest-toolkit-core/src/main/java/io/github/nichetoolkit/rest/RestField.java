package io.github.nichetoolkit.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestField</code>
 * <p>The type rest field interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public interface RestField extends RestValue<Integer, String> {
    /**
     * <code>getField</code>
     * <p>the field getter method.</p>
     * @return {@link java.lang.String} <p>the field return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getField();

    /**
     * <code>parseField</code>
     * <p>the field method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestField} <p>the generic parameter is <code>RestField</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @return T <p>the field return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestField> T parseField(Class<T> clazz, String field) {
        if (field != null && clazz.isEnum()) {
            Map<String, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestField::getField, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(field);
        } else {
            return null;
        }
    }

    /**
     * <code>parseField</code>
     * <p>the field method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestField} <p>the generic parameter is <code>RestField</code> type.</p>
     * @param values {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @return T <p>the field return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestField> T parseField(Collection<T> values, String field) {
        if (field != null && values != null && !values.isEmpty()) {
            Map<String, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestField::getField, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(field);
        } else {
            return null;
        }
    }

}
