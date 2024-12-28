package io.github.nichetoolkit.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <code>RestField</code>
 * <p>The rest field interface.</p>
 * @param <F>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @see  io.github.nichetoolkit.rest.RestValue
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public interface RestField<F> extends RestValue<String, F> {

    /**
     * <code>getAlias</code>
     * <p>The get alias getter method.</p>
     * @return  {@link java.lang.String} <p>The get alias return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String getAlias();

    /**
     * <code>getName</code>
     * <p>The get name getter method.</p>
     * @return  {@link java.lang.String} <p>The get name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String getName();

    /**
     * <code>getType</code>
     * <p>The get type getter method.</p>
     * @return  {@link io.github.nichetoolkit.rest.RestType} <p>The get type return object is <code>RestType</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestType
     */
    RestType getType();

    /**
     * <code>getComment</code>
     * <p>The get comment getter method.</p>
     * @return  {@link java.lang.String} <p>The get comment return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String getComment();


    @Override
    default String name() {
        return getName();
    }

    /**
     * <code>parseField</code>
     * <p>The parse field method.</p>
     * @param <T>  {@link io.github.nichetoolkit.rest.RestField} <p>The generic parameter is <code>RestField</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see  java.util.Collection
     * @see  java.lang.String
     * @see  java.lang.SuppressWarnings
     * @return T <p>The parse field return object is <code>T</code> type.</p>
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

}
