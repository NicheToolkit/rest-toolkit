package io.github.nichetoolkit.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <code>RestField</code>
 * <p>The rest field interface.</p>
 * @param <F> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public interface RestField<F> extends RestValue<String, F> {

    /**
     * <code>getAlias</code>
     * <p>The get alias getter method.</p>
     * @return {@link java.lang.String} <p>The get alias return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String getAlias() {
        return getName();
    }

    /**
     * <code>getName</code>
     * <p>The get name getter method.</p>
     * @return {@link java.lang.String} <p>The get name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getName();

    /**
     * <code>getType</code>
     * <p>The get type getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestType} <p>The get type return object is <code>RestType</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestType
     */
    RestType getType();

    /**
     * <code>getComment</code>
     * <p>The get comment getter method.</p>
     * @return {@link java.lang.String} <p>The get comment return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String getComment() {
        return getName();
    }

    /**
     * <code>notNull</code>
     * <p>The not null method.</p>
     * @return {@link java.lang.Boolean} <p>The not null return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    default Boolean notNull() {
        return false;
    }

    /**
     * <code>defaultValue</code>
     * <p>The default value method.</p>
     * @return F <p>The default value return object is <code>F</code> type.</p>
     */
    default F defaultValue() {
        return null;
    }

    @Override
    default String name() {
        return getName();
    }

    /**
     * <code>parseField</code>
     * <p>The parse field method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestField} <p>The generic parameter is <code>RestField</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param name   {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @return T <p>The parse field return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestField> T parseField(Collection<T> values, String name) {
        if (name != null && values != null && !values.isEmpty()) {
            Map<String, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestField::getName, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(name);
        } else {
            return null;
        }
    }

    /**
     * <code>of</code>
     * <p>The of method.</p>
     * @param <F>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param type {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestField} <p>The of return object is <code>RestField</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestType
     */
    static <F> RestField<F> of(String name, RestType type) {
        return new OfRestField<>(name, type);
    }

    /**
     * <code>ofNull</code>
     * <p>The of null method.</p>
     * @param <F> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestField} <p>The of null return object is <code>RestField</code> type.</p>
     */
    static <F> RestField<F> ofNull() {
        return new OfRestField<>(null);
    }

    /**
     * <code>OfRestField</code>
     * <p>The of rest field class.</p>
     * @param <F> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.RestValue.OfRestValue
     * @since Jdk1.8
     */
    class OfRestField<F> extends RestValue.OfRestValue<String, F> implements RestField<F> {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>The <code>name</code> field.</p>
         * @see java.lang.String
         */
        private final String name;
        /**
         * <code>type</code>
         * {@link io.github.nichetoolkit.rest.RestType} <p>The <code>type</code> field.</p>
         * @see io.github.nichetoolkit.rest.RestType
         */
        private final RestType type;

        /**
         * <code>OfRestField</code>
         * <p>Instantiates a new of rest field.</p>
         * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @see java.lang.String
         */
        public OfRestField(String name) {
            super(name, null);
            this.name = name;
            this.type = RestType.ofNull();
        }

        /**
         * <code>OfRestField</code>
         * <p>Instantiates a new of rest field.</p>
         * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
         * @param type {@link io.github.nichetoolkit.rest.RestType} <p>The type parameter is <code>RestType</code> type.</p>
         * @see java.lang.String
         * @see io.github.nichetoolkit.rest.RestType
         */
        public OfRestField(String name, RestType type) {
            super(name, null);
            this.name = name;
            this.type = type;
        }

        @Override
        public String getName() {
            return "";
        }

        @Override
        public RestType getType() {
            return null;
        }
    }

}
