package io.github.nichetoolkit.rest;

import org.springframework.lang.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestArithmetic</code>
 * <p>The type rest arithmetic interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public interface RestArithmetic extends RestValue<Long, String> {

    /**
     * <code>getArithmetic</code>
     * <p>the arithmetic getter method.</p>
     * @return {@link java.lang.Long} <p>the arithmetic return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    Long getArithmetic();

    /**
     * <code>parseArithmetic</code>
     * <p>the arithmetic method.</p>
     * @param <T>        {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param arithmetic {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @return {@link T} <p>the arithmetic return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestArithmetic> T parseArithmetic(Class<T> clazz, Long arithmetic) {
        if (arithmetic != null && clazz.isEnum()) {
            Map<Long, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestArithmetic::getArithmetic, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(arithmetic);
        } else {
            return null;
        }
    }

    /**
     * <code>parseArithmetic</code>
     * <p>the arithmetic method.</p>
     * @param <T>        {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param values     {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param arithmetic {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @return {@link T} <p>the arithmetic return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestArithmetic> T parseArithmetic(Collection<T> values, Long arithmetic) {
        if (arithmetic != null && values != null && !values.isEmpty()) {
            Map<Long, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestArithmetic::getArithmetic, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(arithmetic);
        } else {
            return null;
        }
    }

    /**
     * <code>reachKey</code>
     * <p>the key method.</p>
     * @param key            {@link java.lang.Long} <p>the key parameter is <code>Long</code> type.</p>
     * @param arithmeticType {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the arithmetic type parameter is <code>RestArithmetic</code> type.</p>
     * @return {@link boolean} <p>the key return object is <code>boolean</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    static boolean reachKey(@NonNull Long key, @NonNull RestArithmetic arithmeticType) {
        Long sourceKey = Optional.ofNullable(arithmeticType.getKey()).orElse(0L);
        return (key & sourceKey) == sourceKey;
    }

    /**
     * <code>reachLong</code>
     * <p>the long method.</p>
     * @param key        {@link java.lang.Long} <p>the key parameter is <code>Long</code> type.</p>
     * @param arithmetic {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @return {@link boolean} <p>the long return object is <code>boolean</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    static boolean reachLong(@NonNull Long key, Long arithmetic) {
        Long sourceKey = Optional.ofNullable(arithmetic).orElse(0L);
        return (key & sourceKey) == sourceKey;
    }

    /**
     * <code>annexKey</code>
     * <p>the key method.</p>
     * @param <T>             {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param arithmeticTypes {@link java.util.Collection} <p>the arithmetic types parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the key return object is <code>Long</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     */
    static <T extends RestArithmetic> Long annexKey(Collection<T> arithmeticTypes) {
        return annexKey(null, arithmeticTypes);
    }


    /**
     * <code>annexKey</code>
     * <p>the key method.</p>
     * @param <T>             {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param arithmeticTypes {@link T} <p>the arithmetic types parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Long} <p>the key return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestArithmetic> Long annexKey(T... arithmeticTypes) {
        return annexKey(null, arithmeticTypes);
    }

    /**
     * <code>annexKey</code>
     * <p>the key method.</p>
     * @param <T>             {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param arithmetic      {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @param arithmeticTypes {@link T} <p>the arithmetic types parameter is <code>T</code> type.</p>
     * @return {@link java.lang.Long} <p>the key return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    static <T extends RestArithmetic> Long annexKey(Long arithmetic, T... arithmeticTypes) {
        if (arithmeticTypes == null || arithmeticTypes.length == 0) {
            return arithmetic;
        }
        return annexKey(arithmetic, Arrays.asList(arithmeticTypes));
    }

    /**
     * <code>annexKey</code>
     * <p>the key method.</p>
     * @param <T>             {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param arithmetic      {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @param arithmeticTypes {@link java.util.Collection} <p>the arithmetic types parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the key return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     */
    static <T extends RestArithmetic> Long annexKey(Long arithmetic, Collection<T> arithmeticTypes) {
        long key = Optional.ofNullable(arithmetic).orElse(0L);
        if (arithmeticTypes != null && !arithmeticTypes.isEmpty()) {
            for (T arithmeticType : arithmeticTypes) {
                Long sourceKey = arithmeticType.getKey();
                key = key | sourceKey;
            }
        }
        return key == 0L ? null : key;
    }

    /**
     * <code>annexLong</code>
     * <p>the long method.</p>
     * @param arithmeticList {@link java.util.Collection} <p>the arithmetic list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     */
    static Long annexLong( Collection<Long> arithmeticList) {
        return annexLong(null, arithmeticList);
    }

    /**
     * <code>annexLong</code>
     * <p>the long method.</p>
     * @param arithmeticArray {@link java.lang.Long} <p>the arithmetic array parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    static  Long annexLong(Long... arithmeticArray) {
        return annexLong(null, Arrays.asList(arithmeticArray));
    }

    /**
     * <code>annexLong</code>
     * <p>the long method.</p>
     * @param arithmetic     {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @param arithmeticList {@link java.util.Collection} <p>the arithmetic list parameter is <code>Collection</code> type.</p>
     * @return {@link java.lang.Long} <p>the long return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     */
    static Long annexLong(Long arithmetic, Collection<Long> arithmeticList) {
        long key = Optional.ofNullable(arithmetic).orElse(0L);
        if (arithmeticList != null && !arithmeticList.isEmpty()) {
            for (Long sourceKey : arithmeticList) {
                key = key | sourceKey;
            }
        }
        return key == 0L ? null : key;
    }

    /**
     * <code>deconKey</code>
     * <p>the key method.</p>
     * @param <T>        {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param arithmetic {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @return {@link java.util.List} <p>the key return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Long
     * @see java.util.List
     */
    static <T extends RestArithmetic> List<T> deconKey(Class<T> clazz, Long arithmetic) {
        if (arithmetic == null || arithmetic == 0L || !clazz.isEnum()) {
            return null;
        }
        List<T> arithmeticTypes = new ArrayList<>();
        for (T arithmeticType : clazz.getEnumConstants()) {
            if (RestArithmetic.reachKey(arithmetic, arithmeticType)) {
                arithmeticTypes.add(arithmeticType);
            }
        }
        return arithmeticTypes;
    }

    /**
     * <code>deconKey</code>
     * <p>the key method.</p>
     * @param <T>        {@link io.github.nichetoolkit.rest.RestArithmetic} <p>the generic parameter is <code>RestArithmetic</code> type.</p>
     * @param values     {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param arithmetic {@link java.lang.Long} <p>the arithmetic parameter is <code>Long</code> type.</p>
     * @return {@link java.util.List} <p>the key return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Long
     * @see java.util.List
     */
    static <T extends RestArithmetic> List<T> deconKey(Collection<T> values, Long arithmetic) {
        if (arithmetic == null || arithmetic == 0L || values == null || values.isEmpty()) {
            return null;
        }
        List<T> arithmeticTypes = new ArrayList<>();
        for (T arithmeticType : values) {
            if (RestArithmetic.reachKey(arithmetic, arithmeticType)) {
                arithmeticTypes.add(arithmeticType);
            }
        }
        return arithmeticTypes;
    }

}
