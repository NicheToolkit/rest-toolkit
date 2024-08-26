package io.github.nichetoolkit.rest;

import org.springframework.lang.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>RestArithmetic</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestArithmetic extends RestValue<Long, String> {

    Long getArithmetic();

    @SuppressWarnings("Duplicates")
    static <T extends RestArithmetic> T parseArithmetic(Class<T> clazz, Long arithmetic) {
        if (arithmetic != null && clazz.isEnum()) {
            Map<Long, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestArithmetic::getArithmetic, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(arithmetic);
        } else {
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestArithmetic> T parseArithmetic(Collection<T> values, Long arithmetic) {
        if (arithmetic != null && values != null && !values.isEmpty()) {
            Map<Long, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestArithmetic::getArithmetic, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(arithmetic);
        } else {
            return null;
        }
    }

    static boolean reachKey(@NonNull Long key, @NonNull RestArithmetic arithmeticType) {
        Long sourceKey = Optional.ofNullable(arithmeticType.getKey()).orElse(0L);
        return (key & sourceKey) == sourceKey;
    }

    static boolean reachLong(@NonNull Long key, Long arithmetic) {
        Long sourceKey = Optional.ofNullable(arithmetic).orElse(0L);
        return (key & sourceKey) == sourceKey;
    }

    static <T extends RestArithmetic> Long annexKey(Collection<T> arithmeticTypes) {
        return annexKey(null, arithmeticTypes);
    }


    @SuppressWarnings("unchecked")
    static <T extends RestArithmetic> Long annexKey(T... arithmeticTypes) {
        return annexKey(null, arithmeticTypes);
    }

    @SuppressWarnings("unchecked")
    static <T extends RestArithmetic> Long annexKey(Long arithmetic, T... arithmeticTypes) {
        if (arithmeticTypes == null || arithmeticTypes.length == 0) {
            return arithmetic;
        }
        return annexKey(arithmetic, Arrays.asList(arithmeticTypes));
    }

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

    static Long annexLong( Collection<Long> arithmeticList) {
        return annexLong(null, arithmeticList);
    }

    static  Long annexLong(Long... arithmeticArray) {
        return annexLong(null, Arrays.asList(arithmeticArray));
    }

    static Long annexLong(Long arithmetic, Collection<Long> arithmeticList) {
        long key = Optional.ofNullable(arithmetic).orElse(0L);
        if (arithmeticList != null && !arithmeticList.isEmpty()) {
            for (Long sourceKey : arithmeticList) {
                key = key | sourceKey;
            }
        }
        return key == 0L ? null : key;
    }

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
