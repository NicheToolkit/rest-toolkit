package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Setter;

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

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The of return object is <code>RestType</code> type.</p>
     * @see java.lang.Integer
     */
    static RestType of(Integer key) {
        return new OfRestType(key);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The of return object is <code>RestType</code> type.</p>
     * @see java.lang.String
     */
    static RestType of(String value) {
        return new OfRestType(value);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param type {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The of return object is <code>RestType</code> type.</p>
     * @see java.lang.Class
     */
    static RestType of(Class<?> type) {
        return new OfRestType(type);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The of return object is <code>RestType</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    static RestType of(Integer key, String value) {
        return new OfRestType(key, value);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key  {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param type {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The of return object is <code>RestType</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Class
     */
    static RestType of(Integer key, Class<?> type) {
        return new OfRestType(key, type);
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param type  {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The of return object is <code>RestType</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Class
     */
    static RestType of(Integer key, String value, Class<?> type) {
        return new OfRestType(key, value, type);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The of null return object is <code>RestType</code> type.</p>
     */
    static RestType ofNull() {
        return new OfRestType(null, null, Object.class);
    }


    /**
     * <code>OfRestType</code>
     * <p>The of rest type class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestValue.OfRestValue
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Setter
    class OfRestType extends RestValue.OfRestValue<Integer, String> implements RestType {
        /**
         * <code>type</code>
         * {@link java.lang.Class} <p>The <code>type</code> field.</p>
         * @see java.lang.Class
         */
        private Class<?> type;

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         */
        public OfRestType() {
        }

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
         * @see java.lang.Integer
         */
        public OfRestType(Integer key) {
            super(key, null);
        }

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public OfRestType(String value) {
            super(null, value);
            if (GeneralUtils.isNotEmpty(value)) {
                this.type = value.getClass();
            }
        }

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         * @param type {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
         * @see java.lang.Class
         */
        public OfRestType(Class<?> type) {
            this.type = type;
        }

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
         * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
         * @see java.lang.Integer
         * @see java.lang.String
         */
        public OfRestType(Integer key, String value) {
            super(key, value);
            if (GeneralUtils.isNotEmpty(value)) {
                this.type = value.getClass();
            }
        }

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         * @param key  {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
         * @param type {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
         * @see java.lang.Integer
         * @see java.lang.Class
         */
        public OfRestType(Integer key, Class<?> type) {
            super(key, null);
            this.type = type;
        }

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         * @param entry {@link java.util.Map.Entry} <p>The entry parameter is <code>Entry</code> type.</p>
         * @see java.util.Map.Entry
         */
        public OfRestType(Map.Entry<Integer, String> entry) {
            super(entry);
            if (GeneralUtils.isNotEmpty(entry.getValue())) {
                this.type = entry.getValue().getClass();
            }
        }

        /**
         * <code>OfRestType</code>
         * <p>Instantiates a new of rest type.</p>
         * @param key   {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
         * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
         * @param type  {@link java.lang.Class} <p>The type parameter is <code>Class</code> type.</p>
         * @see java.lang.Integer
         * @see java.lang.String
         * @see java.lang.Class
         */
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
