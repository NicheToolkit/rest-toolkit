package io.github.nichetoolkit.rest;

import org.springframework.lang.NonNull;

import java.util.*;

public interface RestReckon extends RestValue<String, Long> {

    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Class<T> clazz, String reckonKey) {
        return RestKey.parseKey(clazz, reckonKey);
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Collection<T> reckons, String reckonKey) {
        return RestKey.parseKey(reckons, reckonKey);
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Class<T> clazz, Long reckonValue) {
        return RestValue.parseValue(clazz, reckonValue);
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestReckon> T parseReckon(Collection<T> reckons, Long reckonValue) {
        return RestValue.parseValue(reckons, reckonValue);
    }

    static <T extends RestReckon> boolean reachValue(Class<T> clazz, RestReckon reckon) {
        Long sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(clazz)).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    static <T extends RestReckon> boolean reachValue(Collection<T> reckons, RestReckon reckon) {
        Long sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(reckons)).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    static <T extends RestReckon> boolean reachValue(Class<T> clazz, Long reckonValue) {
        Long sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(clazz)).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    static <T extends RestReckon> boolean reachValue(Collection<T> reckons, Long reckonValue) {
        Long sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        Long value = Optional.ofNullable(annexReckon(reckons)).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    static boolean reachValue(@NonNull Long value, @NonNull RestReckon reckon) {
        Long sourceValue = Optional.ofNullable(reckon.getValue()).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    static boolean reachValue(@NonNull Long value, Long reckonValue) {
        Long sourceValue = Optional.ofNullable(reckonValue).orElse(0L);
        return (value & sourceValue) == sourceValue;
    }

    static <T extends RestReckon> Long annexReckon(Class<T> clazz) {
        return annexReckon(null, clazz);
    }

    static <T extends RestReckon> Long annexReckon(Collection<T> reckons) {
        return annexReckon(null, reckons);
    }

    @SuppressWarnings("unchecked")
    static <T extends RestReckon> Long annexReckon(T... reckons) {
        return annexReckon(null, reckons);
    }

    @SuppressWarnings("unchecked")
    static <T extends RestReckon> Long annexReckon(Long value, T... reckons) {
        if (reckons == null || reckons.length == 0) {
            return value;
        }
        return annexReckon(value, Arrays.asList(reckons));
    }

    static <T extends RestReckon> Long annexReckon(Long value, Class<T> clazz) {
        long sourceValue = Optional.ofNullable(value).orElse(0L);
        if (!clazz.isEnum()) {
            return sourceValue == 0L ? null : sourceValue;
        }
        return annexReckon(value, Arrays.asList(clazz.getEnumConstants()));
    }

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

    static Long annexValue(Collection<Long> reckonValues) {
        return annexValue(null, reckonValues);
    }

    static Long annexValue(Long... reckonValues) {
        return annexValue(null, Arrays.asList(reckonValues));
    }

    static Long annexValue(Long value, Collection<Long> reckonValues) {
        long sourceValue = Optional.ofNullable(value).orElse(0L);
        if (reckonValues != null && !reckonValues.isEmpty()) {
            for (Long reckonValue : reckonValues) {
                sourceValue = sourceValue | reckonValue;
            }
        }
        return sourceValue == 0L ? null : sourceValue;
    }

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
