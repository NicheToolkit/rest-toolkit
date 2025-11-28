package io.github.nichetoolkit.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestStamp</code>
 * <p>The rest stamp interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk17
 */
public interface RestStamp extends RestValue<Integer, String> {

    /**
     * <code>getStamp</code>
     * <p>The get stamp getter method.</p>
     * @return {@link java.lang.String} <p>The get stamp return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getStamp();

    /**
     * <code>parseStamp</code>
     * <p>The parse stamp method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStamp} <p>The generic parameter is <code>RestStamp</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param stamp {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p>
     * @return T <p>The parse stamp return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestStamp> T parseStamp(Class<T> clazz, String stamp) {
        if (stamp != null && clazz.isEnum()) {
            Map<String, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestStamp::getStamp, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(stamp);
        } else {
            return null;
        }
    }

    /**
     * <code>parseStamp</code>
     * <p>The parse stamp method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestStamp} <p>The generic parameter is <code>RestStamp</code> type.</p>
     * @param values {@link java.util.Collection} <p>The values parameter is <code>Collection</code> type.</p>
     * @param stamp  {@link java.lang.String} <p>The stamp parameter is <code>String</code> type.</p>
     * @return T <p>The parse stamp return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestStamp> T parseStamp(Collection<T> values, String stamp) {
        if (stamp != null && values != null && !values.isEmpty()) {
            Map<String, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestStamp::getStamp, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(stamp);
        } else {
            return null;
        }
    }

}
