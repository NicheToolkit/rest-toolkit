package io.github.nichetoolkit.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestType</code>
 * <p>The rest type interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public interface RestType extends RestValue<Integer, String> {

    /**
     * <code>getType</code>
     * <p>The get type getter method.</p>
     * @return {@link java.lang.Class} <p>The get type return object is <code>Class</code> type.</p>
     * @see java.lang.Class
     */
    Class<?> getType();

    /**
     * <code>parseType</code>
     * <p>The parse type method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestType} <p>The generic parameter is <code>RestType</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param type  {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @return T <p>The parse type return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestType> T parseType(Class<T> clazz, Class<?> type) {
        if (type != null && clazz.isEnum()) {
            Map<Class<?>, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestType::getType, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(type);
        } else {
            return null;
        }
    }

    /**
     * <code>parseType</code>
     * <p>The parse type method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestType} <p>The generic parameter is <code>RestType</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param field  {@link java.lang.Class} <p>The field parameter is <code>Class</code> type.</p>
     * @return T <p>The parse type return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestType> T parseType(Collection<T> values, Class<?> field) {
        if (field != null && values != null && !values.isEmpty()) {
            Map<Class<?>, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestType::getType, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(field);
        } else {
            return null;
        }
    }

    static RestType of(Integer key, String value, Class<?> type) {
        return new OfRestType(key, value, type);
    }

    static RestType ofNull() {
        return new OfRestType(null, null, Object.class);
    }


    class OfRestType extends RestValue.OfRestValue<Integer, String> implements RestType {
        private final Class<?> type;

        public OfRestType(Integer key, String value, Class<?> type) {
            super(key, value);
            this.type = type;
        }

        @Override
        public Class<?> getType() {
            return this.type;
        }
    }

}
