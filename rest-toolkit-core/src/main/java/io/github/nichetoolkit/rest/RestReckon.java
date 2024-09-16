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
public interface RestReckon extends RestValue<String, Long> {

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
     * @param reckonValue {@link java.lang.Long} <p>the reckon value parameter is <code>Long</code> type.</p>
     * @return T <p>the reckon return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Class<T> clazz, Long reckonValue) {
        return RestValue.parseValue(clazz, reckonValue);
    }

    /**
     * <code>parseReckon</code>
     * <p>the reckon method.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons     {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @param reckonValue {@link java.lang.Long} <p>the reckon value parameter is <code>Long</code> type.</p>
     * @return T <p>the reckon return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Collection<T> reckons, Long reckonValue) {
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
        Long sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(clazz)).orElse(0L);
        return (value & sourceValue) == sourceValue;
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
        Long sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(reckons)).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param reckonValue {@link java.lang.Long} <p>the reckon value parameter is <code>Long</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Long
     */
    static <T extends RestReckon> boolean reachValue(Class<T> clazz, Long reckonValue) {
        Long sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(clazz)).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons     {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @param reckonValue {@link java.lang.Long} <p>the reckon value parameter is <code>Long</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     */
    static <T extends RestReckon> boolean reachValue(Collection<T> reckons, Long reckonValue) {
        Long sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(reckons)).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param value  {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param reckon {@link io.github.nichetoolkit.rest.RestReckon} <p>the reckon parameter is <code>RestReckon</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    static boolean reachValue(@NonNull Long value, @NonNull RestReckon reckon) {
        Long sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    /**
     * <code>reachValue</code>
     * <p>the value method.</p>
     * @param value       {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param reckonValue {@link java.lang.Long} <p>the reckon value parameter is <code>Long</code> type.</p>
     * @return boolean <p>the value return object is <code>boolean</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    static boolean reachValue(@NonNull Long value, Long reckonValue) {
        Long sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    /**
     * <code>annexReckon</code>
     * <p>the reckon method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Long} <p>the reckon return object is <code>Long</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Long
     */
    static <T extends RestReckon> Long annexReckon(Class<T> clazz) {
        return annexReckon(null, clazz);
    }

    /**
     * <code>annexReckon</code>
     * <p>the reckon method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the reckon return object is <code>Long</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     */
    static <T extends RestReckon> Long annexReckon(Collection<T> reckons) {
        return annexReckon(null, reckons);
    }

    /**
     * <code>annexReckon</code>
     * <p>the reckon method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons T <p>the reckons parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Long} <p>the reckon return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestReckon> Long annexReckon(T... reckons) {
        return annexReckon(null, reckons);
    }

    /**
     * <code>annexReckon</code>
     * <p>the reckon method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value   {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param reckons T <p>the reckons parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Long} <p>the reckon return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestReckon> Long annexReckon(Long value, T... reckons) {
        if (reckons == null || reckons.length == 0) {
            return value;
        }
        return annexReckon(value, Arrays.asList(reckons));
    }

    /**
     * <code>annexReckon</code>
     * <p>the reckon method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Long} <p>the reckon return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.Class
     */
    static <T extends RestReckon> Long annexReckon(Long value, Class<T> clazz) {
        long sourceValue = Optional.ofNullable(value).orElse(0L);
        if (!clazz.isEnum()) {
            return sourceValue == 0L ? null : sourceValue;
        }
        return annexReckon(value, Arrays.asList(clazz.getEnumConstants()));
    }

    /**
     * <code>annexReckon</code>
     * <p>the reckon method.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value   {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the reckon return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     */
    static <T extends RestReckon> Long annexReckon(Long value, Collection<T> reckons) {
        long sourceValue = Optional.ofNullable(value).orElse(0L);
        if (reckons != null && !reckons.isEmpty()) {
            for (T reckon : reckons) {
                Long reckonValue = reckon.getValue();
                sourceValue = sourceValue | reckonValue;
            }
        }
        return sourceValue == 0L ? null : sourceValue;
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param reckonValues {@link java.util.Collection} <p>the reckon values parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the value return object is <code>Long</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     */
    static Long annexValue(Collection<Long> reckonValues) {
        return annexValue(null, reckonValues);
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param reckonValues {@link java.lang.Long} <p>the reckon values parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>the value return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    static Long annexValue(Long... reckonValues) {
        return annexValue(null, Arrays.asList(reckonValues));
    }

    /**
     * <code>annexValue</code>
     * <p>the value method.</p>
     * @param value        {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param reckonValues {@link java.util.Collection} <p>the reckon values parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the value return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     */
    static Long annexValue(Long value, Collection<Long> reckonValues) {
        long sourceValue = Optional.ofNullable(value).orElse(0L);
        if (reckonValues != null && !reckonValues.isEmpty()) {
            for (Long reckonValue : reckonValues) {
                sourceValue = sourceValue | reckonValue;
            }
        }
        return sourceValue == 0L ? null : sourceValue;
    }

    /**
     * <code>denexValue</code>
     * <p>the value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>the generic parameter is <code>RestReckon</code> type.</p>
     * @param value {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestReckon> List<T> denexValue(Long value, Class<T> clazz) {
        if (value == null || value == 0L || !clazz.isEnum()) {
            return null;
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
     * @param value   {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param reckons T <p>the reckons parameter is <code>T</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Long
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestReckon> List<T> denexValue(Long value, T... reckons) {
        if (value == null || value == 0L || reckons == null || reckons.length == 0) {
            return null;
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
     * @param value   {@link java.lang.Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>the reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     * @see java.util.List
     */
    static <T extends RestReckon> List<T> denexValue(Long value, Collection<T> reckons) {
        if (value == null || value == 0L || reckons == null || reckons.isEmpty()) {
            return null;
        }
        List<T> results = new ArrayList<>();
        for (T reckon : reckons) {
            if (RestReckon.reachValue(value, reckon)) {
                results.add(reckon);
            }
        }
        return results;
    }

}
