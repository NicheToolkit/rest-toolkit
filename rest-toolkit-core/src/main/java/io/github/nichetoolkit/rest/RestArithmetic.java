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
            Map<Long, T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestArithmetic::getArithmetic, Function.identity()));
            return valueEnumMap.get(arithmetic);
        } else {
            return null;
        }
    }

    static boolean reachKey(@NonNull Long key, @NonNull RestArithmetic arithmeticType) {
        Long sourceKey = arithmeticType.getKey();
        return (key & sourceKey) != 0;
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
        Long key = Optional.ofNullable(arithmetic).orElse(0L);
        if (arithmeticTypes != null && arithmeticTypes.size() > 0) {
            for (T arithmeticType : arithmeticTypes) {
                Long sourceKey = arithmeticType.getKey();
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

}
