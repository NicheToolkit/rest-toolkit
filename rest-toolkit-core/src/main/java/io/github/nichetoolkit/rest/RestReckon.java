package io.github.nichetoolkit.rest;

import org.springframework.lang.NonNull;

import java.util.*;

/**
 * <code>RestReckon</code>
 * <p>The rest reckon interface.</p>
 * @param <N> {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.Number
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public interface RestReckon<N extends Number> extends RestValue<String, N> {

    /**
     * <code>parseReckon</code>
     * <p>The parse reckon method.</p>
     * @param <N>       {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>       {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz     {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param reckonKey {@link java.lang.String} <p>The reckon key parameter is <code>String</code> type.</p>
     * @return T <p>The parse reckon return object is <code>T</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <N extends Number, T extends RestReckon<N>> T parseReckon(Class<T> clazz, String reckonKey) {
        return RestKey.parseKey(clazz, reckonKey);
    }

    /**
     * <code>parseReckon</code>
     * <p>The parse reckon method.</p>
     * @param <N>       {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>       {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons   {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @param reckonKey {@link java.lang.String} <p>The reckon key parameter is <code>String</code> type.</p>
     * @return T <p>The parse reckon return object is <code>T</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <N extends Number, T extends RestReckon<N>> T parseReckon(Collection<T> reckons, String reckonKey) {
        return RestKey.parseKey(reckons, reckonKey);
    }

    /**
     * <code>parseReckon</code>
     * <p>The parse reckon method.</p>
     * @param <N>         {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param reckonValue N <p>The reckon value parameter is <code>N</code> type.</p>
     * @return T <p>The parse reckon return object is <code>T</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <N extends Number, T extends RestReckon<N>> T parseReckon(Class<T> clazz, N reckonValue) {
        return RestValue.parseValue(clazz, reckonValue);
    }

    /**
     * <code>parseReckon</code>
     * <p>The parse reckon method.</p>
     * @param <N>         {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons     {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @param reckonValue N <p>The reckon value parameter is <code>N</code> type.</p>
     * @return T <p>The parse reckon return object is <code>T</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <N extends Number, T extends RestReckon<N>> T parseReckon(Collection<T> reckons, N reckonValue) {
        return RestValue.parseValue(reckons, reckonValue);
    }

    /**
     * <code>reachValue</code>
     * <p>The reach value method.</p>
     * @param <N>    {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param reckon {@link io.github.nichetoolkit.rest.RestReckon} <p>The reckon parameter is <code>RestReckon</code> type.</p>
     * @return boolean <p>The reach value return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     */
    static <N extends Number, T extends RestReckon<N>> boolean reachValue(Class<T> clazz, RestReckon<N> reckon) {
        Number sourceValue = Optional.ofNullable(reckon.getValue()).map(Number::longValue).orElse(0L);
        Number value = Optional.ofNullable(annexValue(clazz)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>The reach value method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @param reckon  {@link io.github.nichetoolkit.rest.RestReckon} <p>The reckon parameter is <code>RestReckon</code> type.</p>
     * @return boolean <p>The reach value return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static <N extends Number, T extends RestReckon<N>> boolean reachValue(Collection<T> reckons, RestReckon<N> reckon) {
        Number sourceValue = Optional.ofNullable(reckon.getValue()).map(Number::longValue).orElse(0L);
        Number value = Optional.ofNullable(annexValue(reckons)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>The reach value method.</p>
     * @param <N>         {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param reckonValue N <p>The reckon value parameter is <code>N</code> type.</p>
     * @return boolean <p>The reach value return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     */
    static <N extends Number, T extends RestReckon<N>> boolean reachValue(Class<T> clazz, N reckonValue) {
        Number sourceValue = Optional.ofNullable(reckonValue).map(Number::longValue).orElse(0L);
        Number value = Optional.ofNullable(annexValue(clazz)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>The reach value method.</p>
     * @param <N>         {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>         {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons     {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @param reckonValue N <p>The reckon value parameter is <code>N</code> type.</p>
     * @return boolean <p>The reach value return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static <N extends Number, T extends RestReckon<N>> boolean reachValue(Collection<T> reckons, N reckonValue) {
        Number sourceValue = Optional.ofNullable(reckonValue).map(Number::longValue).orElse(0L);
        Number value = Optional.ofNullable(annexValue(reckons)).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachValue</code>
     * <p>The reach value method.</p>
     * @param <N>    {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param value  N <p>The value parameter is <code>N</code> type.</p>
     * @param reckon {@link io.github.nichetoolkit.rest.RestReckon} <p>The reckon parameter is <code>RestReckon</code> type.</p>
     * @return boolean <p>The reach value return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see org.springframework.lang.NonNull
     */
    static <N extends Number> boolean reachValue(@NonNull N value, @NonNull RestReckon<N> reckon) {
        Number sourceValue = Optional.ofNullable(reckon.getValue()).map(Number::longValue).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>reachNumber</code>
     * <p>The reach number method.</p>
     * @param <N>         {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param value       N <p>The value parameter is <code>N</code> type.</p>
     * @param reckonValue N <p>The reckon value parameter is <code>N</code> type.</p>
     * @return boolean <p>The reach number return object is <code>boolean</code> type.</p>
     * @see java.lang.Number
     * @see org.springframework.lang.NonNull
     */
    static <N extends Number> boolean reachNumber(@NonNull N value, N reckonValue) {
        Number sourceValue = Optional.ofNullable(reckonValue).map(Number::longValue).orElse(0L);
        return (value.longValue() & sourceValue.longValue()) == sourceValue.longValue();
    }

    /**
     * <code>annexValue</code>
     * <p>The annex value method.</p>
     * @param <N>   {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     */
    static <N extends Number, T extends RestReckon<N>> Number annexValue(Class<T> clazz) {
        return annexValue(null, clazz);
    }

    /**
     * <code>annexValue</code>
     * <p>The annex value method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static <N extends Number, T extends RestReckon<N>> Number annexValue(Collection<T> reckons) {
        return annexValue(null, reckons);
    }

    /**
     * <code>annexValue</code>
     * <p>The annex value method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param reckons T <p>The reckons parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <N extends Number, T extends RestReckon<N>> Number annexValue(T... reckons) {
        return annexValue(null, reckons);
    }

    /**
     * <code>annexValue</code>
     * <p>The annex value method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param value   N <p>The value parameter is <code>N</code> type.</p>
     * @param reckons T <p>The reckons parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <N extends Number, T extends RestReckon<N>> Number annexValue(N value, T... reckons) {
        if (reckons == null || reckons.length == 0) {
            return value;
        }
        return annexValue(value, Arrays.asList(reckons));
    }

    /**
     * <code>annexValue</code>
     * <p>The annex value method.</p>
     * @param <N>   {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param value N <p>The value parameter is <code>N</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     */
    static <N extends Number, T extends RestReckon<N>> Number annexValue(N value, Class<T> clazz) {
        Number sourceValue = Optional.ofNullable(value).map(Number::longValue).orElse(0L);
        if (!clazz.isEnum()) {
            return sourceValue.longValue() == 0L ? null : sourceValue;
        }
        return annexValue(value, Arrays.asList(clazz.getEnumConstants()));
    }

    /**
     * <code>annexValue</code>
     * <p>The annex value method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param value   N <p>The value parameter is <code>N</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex value return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static <N extends Number, T extends RestReckon<N>> Number annexValue(N value, Collection<T> reckons) {
        Number sourceValue = Optional.ofNullable(value).map(Number::longValue).orElse(0L);
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
     * <p>The annex number method.</p>
     * @param <N>          {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param reckonValues {@link java.util.Collection} <p>The reckon values parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex number return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static <N extends Number> Number annexNumber(Collection<N> reckonValues) {
        return annexNumber(null, reckonValues);
    }

    /**
     * <code>annexNumber</code>
     * <p>The annex number method.</p>
     * @param <N>          {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param reckonValues N <p>The reckon values parameter is <code>N</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex number return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.SafeVarargs
     */
    @SafeVarargs
    static <N extends Number> Number annexNumber(N... reckonValues) {
        return annexNumber(null, Arrays.asList(reckonValues));
    }

    /**
     * <code>annexNumber</code>
     * <p>The annex number method.</p>
     * @param <N>          {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param value        N <p>The value parameter is <code>N</code> type.</p>
     * @param reckonValues {@link java.util.Collection} <p>The reckon values parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Number} <p>The annex number return object is <code>Number</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     */
    static <N extends Number> Number annexNumber(N value, Collection<N> reckonValues) {
        Number sourceValue = Optional.ofNullable(value).map(Number::longValue).orElse(0L);
        if (reckonValues != null && !reckonValues.isEmpty()) {
            for (N reckonValue : reckonValues) {
                sourceValue = sourceValue.longValue() | reckonValue.longValue();
            }
        }
        return sourceValue.longValue() == 0L ? null : sourceValue;
    }

    /**
     * <code>denexValue</code>
     * <p>The denex value method.</p>
     * @param <N>   {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param value N <p>The value parameter is <code>N</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The denex value return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.lang.Class
     * @see java.util.List
     */
    static <N extends Number, T extends RestReckon<N>> List<T> denexValue(N value, Class<T> clazz) {
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
     * <p>The denex value method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param value   N <p>The value parameter is <code>N</code> type.</p>
     * @param reckons T <p>The reckons parameter is <code>T</code> type.</p>
     * @return {@link java.util.List} <p>The denex value return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.List
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <N extends Number, T extends RestReckon<N>> List<T> denexValue(N value, T... reckons) {
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
     * <p>The denex value method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param <T>     {@link io.github.nichetoolkit.rest.RestReckon} <p>The generic parameter is <code>RestReckon</code> type.</p>
     * @param value   N <p>The value parameter is <code>N</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>The denex value return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     * @see java.util.List
     */
    static <N extends Number, T extends RestReckon<N>> List<T> denexValue(N value, Collection<T> reckons) {
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
     * <p>The denex number method.</p>
     * @param <N>   {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param value N <p>The value parameter is <code>N</code> type.</p>
     * @return {@link java.util.List} <p>The denex number return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.List
     */
    static <N extends Number> List<Number> denexNumber(N value) {
        if (value == null || value.longValue() == 0L) {
            return Collections.emptyList();
        }
        int length = Long.toBinaryString(value.longValue()).length();
        return denexNumber(value, length);
    }

    /**
     * <code>denexNumber</code>
     * <p>The denex number method.</p>
     * @param <N>    {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param value  N <p>The value parameter is <code>N</code> type.</p>
     * @param length N <p>The length parameter is <code>N</code> type.</p>
     * @return {@link java.util.List} <p>The denex number return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.List
     */
    static <N extends Number> List<Number> denexNumber(N value, N length) {
        if (value == null || value.longValue() == 0L || length == null || length.longValue() == 0L) {
            return Collections.emptyList();
        }
        List<Number> results = new ArrayList<>();
        for (long index = 0; index < length.longValue(); index++) {
            long indexValue = -(-1L << index);
            if (RestReckon.reachNumber(value, indexValue)) {
                results.add(index);
            }
        }
        return results;
    }

    /**
     * <code>denexNumber</code>
     * <p>The denex number method.</p>
     * @param <N>     {@link java.lang.Number} <p>The generic parameter is <code>Number</code> type.</p>
     * @param value   N <p>The value parameter is <code>N</code> type.</p>
     * @param reckons {@link java.util.Collection} <p>The reckons parameter is <code>Collection</code> type.</p>
     * @return {@link java.util.List} <p>The denex number return object is <code>List</code> type.</p>
     * @see java.lang.Number
     * @see java.util.Collection
     * @see java.util.List
     */
    static <N extends Number> List<Number> denexNumber(N value, Collection<N> reckons) {
        if (value == null || value.longValue() == 0L || reckons == null || reckons.isEmpty()) {
            return Collections.emptyList();
        }
        List<Number> results = new ArrayList<>();
        for (N reckon : reckons) {
            if (RestReckon.reachNumber(value, reckon)) {
                results.add(reckon);
            }
        }
        return results;
    }

}
