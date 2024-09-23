package io.github.nichetoolkit.rest;

import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>RestReckon</code>
 * <p>The type rest reckon interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public interface RestReckon extends RestValue<String, Number> {

    /**
     * <code>parseReckon</code>
     * <p>the reckon method.</p>
     * @param <T>       {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz     {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param reckonKey {@link java.lang.String} <p>the reckon key parameter is <code>String</code> type.</p>
     * @return T <p>the reckon return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Class<T> clazz, String reckonKey) {
        return RestKey.parseKey(clazz, reckonKey);
    }

    /**
     * <code>parseReckon</code>
     * <p>the reckon method.</p>
     * @param <T>       {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons   {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @param reckonKey {@link java.lang.String} <p>the reckon key parameter is <code>String</code> type.</p>
     * @return T <p>the reckon return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Collection<T> reckons, String reckonKey) {
        return RestKey.parseKey(reckons, reckonKey);
    }

    /**
     * <code>parseReckon</code>
     * <p>the reckon method.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param reckonValue {@link java.lang.Number} <p>the reckon value parameter is <code>Number</code> type.</p>
     * @return T <p>the reckon return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Number
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Class<T> clazz, Number reckonValue) {
        return RestValue.parseValue(clazz, reckonValue);
    }

    /**
     * <code>parseReckon</code>
     * <p>the reckon method.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons     {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @param reckonValue {@link java.lang.Number} <p>the reckon value parameter is <code>Number</code> type.</p>
     * @return T <p>the reckon return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Number
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Collection<T> reckons, Number reckonValue) {
        return RestValue.parseValue(reckons, reckonValue);
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param reckon {@link io.github.nichetoolkit.rest.RestReckon} <p>the reckon parameter is <code>RestReckon</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.lang.Class
     */
    static <T extends RestReckon> boolean reachValue(Class<T> clazz, RestReckon reckon) {
        Number sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        Number value = Optional.ofNullable(annexValue(clazz)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @param reckon  {@link io.github.nichetoolkit.rest.RestReckon} <p>the reckon parameter is <code>RestReckon</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.util.Collection
     */
    static <T extends RestReckon> boolean reachValue(Collection<T> reckons, RestReckon reckon) {
        Number sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        Number value = Optional.ofNullable(annexValue(reckons)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param reckonValue {@link java.lang.Number} <p>the reckon value parameter is <code>Number</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Number
     */
    static <T extends RestReckon> boolean reachValue(Class<T> clazz, Number reckonValue) {
        Number sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        Number value = Optional.ofNullable(annexValue(clazz)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons     {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @param reckonValue {@link java.lang.Number} <p>the reckon value parameter is <code>Number</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Number
     */
    static <T extends RestReckon> boolean reachValue(Collection<T> reckons, Number reckonValue) {
        Number sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        Number value = Optional.ofNullable(annexValue(reckons)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param value  {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckon {@link io.github.nichetoolkit.rest.RestReckon} <p>the reckon parameter is <code>RestReckon</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see org.springframework.lang.NonNull
     */
    static boolean reachValue(@NonNull Number value, @NonNull RestReckon reckon) {
        Number sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachNumber</code>
     * <p>the number method.</p>
     * @param value       {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckonValue {@link java.lang.Number} <p>the reckon value parameter is <code>Number</code> type.</p>
     * @return boolean <p>the number return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see org.springframework.lang.NonNull
     */
    static boolean reachNumber(@NonNull Number value, Number reckonValue) {
        Number sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Number} <p>the value return object is <code>Number</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Number
     */
    static <T extends RestReckon> Number annexValue(Class<T> clazz) {
        return annexValue(null, clazz);
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>the value return object is <code>Number</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Number
     */
    static <T extends RestReckon> Number annexValue(Collection<T> reckons) {
        return annexValue(null, reckons);
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons T <p>the reckons parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Number} <p>the value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestReckon> Number annexValue(T... reckons) {
        return annexValue(null, reckons);
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value   {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckons T <p>the reckons parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Number} <p>the value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestReckon> Number annexValue(Number value, T... reckons) {
        if (reckons == null || reckons.length == 0) {
            return value;
        }
        return annexValue(value, Arrays.asList(reckons));
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Number} <p>the value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     */
    static <T extends RestReckon> Number annexValue(Number value, Class<T> clazz) {
        Number sourceValue = Optional.ofNullable(value).orElse(0L);
        if (!clazz.isEnum()) {
            return sourceValue.longValue() == 0L ? null : sourceValue;
        }
        return annexValue(value, Arrays.asList(clazz.getEnumConstants()));
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value   {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>the value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static <T extends RestReckon> Number annexValue(Number value, Collection<T> reckons) {
        Number sourceValue = Optional.ofNullable(value).orElse(0L);
        if (reckons != null && !reckons.isEmpty()) {
            for (T reckon : reckons) {
                Number reckonValue = reckon.getValue();
                sourceValue = sourceValue.longValue() | reckonValue.longValue();
            }
        }
        return sourceValue.longValue() == 0L ? null : sourceValue;
    }

    /**
     * <code>annexNumber</code>
     * <p>the number method.</p>
     * @param reckonValues {@link java.util.Collection} <p>the reckon values parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>the number return object is <code>Number</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Number
     */
    static Number annexNumber(Collection<Number> reckonValues) {
        return annexNumber(null, reckonValues);
    }

    /**
     * <code>annexNumber</code>
     * <p>the number method.</p>
     * @param reckonValues {@link java.lang.Number} <p>the reckon values parameter is <code>Number</code> type.</p>
     * @return {@link java.lang.Number} <p>the number return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     */
    static Number annexNumber(Number... reckonValues) {
        return annexNumber(null, Arrays.asList(reckonValues));
    }

    /**
     * <code>annexNumber</code>
     * <p>the number method.</p>
     * @param value        {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckonValues {@link java.util.Collection} <p>the reckon values parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>the number return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static Number annexNumber(Number value, Collection<Number> reckonValues) {
        Number sourceValue = Optional.ofNullable(value).orElse(0L);
        if (reckonValues != null && !reckonValues.isEmpty()) {
            for (Number reckonValue : reckonValues) {
                sourceValue = sourceValue.longValue() | reckonValue.longValue();
            }
        }
        return sourceValue.longValue() == 0L ? null : sourceValue;
    }

    /**
     * <code>denexValue</code>
     * <p>the value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestReckon> List<T> denexValue(Number value, Class<T> clazz) {
        if (value == null || value.longValue() == 0L || !clazz.isEnum()) {
            return Collections.emptyList();
        }
        List<T> results = new ArrayList<>();
        for (T reckon : clazz.getEnumConstants()) {
            if (RestReckon.reachValue(value, reckon)) {
                results.add(reckon);
            }
        }
        return results;
    }

    /**
     * <code>denexValue</code>
     * <p>the value method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value   {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckons T <p>the reckons parameter is <code>T</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestReckon> List<T> denexValue(Number value, T... reckons) {
        if (value == null || value.longValue() == 0L || reckons == null || reckons.length == 0) {
            return Collections.emptyList();
        }
        List<T> results = new ArrayList<>();
        for (T reckon : reckons) {
            if (RestReckon.reachValue(value, reckon)) {
                results.add(reckon);
            }
        }
        return results;
    }

    /**
     * <code>denexValue</code>
     * <p>the value method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value   {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     * @see java.util.List
     */
    static <T extends RestReckon> List<T> denexValue(Number value, Collection<T> reckons) {
        if (value == null || value.longValue() == 0L || reckons == null || reckons.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> results = new ArrayList<>();
        for (T reckon : reckons) {
            if (RestReckon.reachValue(value, reckon)) {
                results.add(reckon);
            }
        }
        return results;
    }

    /**
     * <code>denexNumber</code>
     * <p>the number method.</p>
     * @param value {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @return {@link java.util.List} <p>the number return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.List
     */
    static List<Number> denexNumber(Number value) {
        if (value == null || value.longValue() == 0L) {
            return Collections.emptyList();
        }
        int length = Long.toBinaryString(value.longValue()).length();
        return denexNumber(value, length);
    }

    /**
     * <code>denexNumber</code>
     * <p>the number method.</p>
     * @param value  {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param length {@link java.lang.Number} <p>the length parameter is <code>Number</code> type.</p>
     * @return {@link java.util.List} <p>the number return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.List
     */
    static List<Number> denexNumber(Number value, Number length) {
        if (value == null || value.longValue() == 0L || length == null || length.longValue() == 0L) {
            return Collections.emptyList();
        }
        List<Number> results = new ArrayList<>();
        for (long index = 0; index < length.longValue(); index++) {
            Number indexValue = -(-1 << index);
            if (RestReckon.reachNumber(value, indexValue)) {
                results.add(index);
            }
        }
        return results;
    }

    /**
     * <code>denexNumber</code>
     * <p>the number method.</p>
     * @param value   {@link java.lang.Number} <p>the value parameter is <code>Number</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the number return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     * @see java.util.List
     */
    static List<Number> denexNumber(Number value, Collection<Number> reckons) {
        if (value == null || value.longValue() == 0L || reckons == null || reckons.isEmpty()) {
            return Collections.emptyList();
        }
        List<Number> results = new ArrayList<>();
        for (Number reckon : reckons) {
            if (RestReckon.reachNumber(value, reckon)) {
                results.add(reckon);
            }
        }
        return results;
    }

}
