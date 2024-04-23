package io.github.nichetoolkit.rest.util;

import java.util.*;

/**
 * <p>ShuntUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ShuntUtils {

    public static <T> void shunt(boolean key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        if (key) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }
    }
    
    public static <K, T> void shunt(K key, T data, Map<K, List<T>> shuntMap) {
        Optional.ofNullable(key).ifPresent(value -> {
            if (shuntMap.containsKey(value)) {
                shuntMap.get(value).add(data);
            } else {
                List<T> cacheList = new ArrayList<>();
                cacheList.add(data);
                shuntMap.put(value, cacheList);
            }
        });
    }

    public static <K, T> void shunt(K key, Collection<T> dataCollection, Map<K, List<T>> shuntMap) {
        Optional.ofNullable(key).ifPresent(value -> {
            if (shuntMap.containsKey(value)) {
                shuntMap.get(value).addAll(dataCollection);
            } else {
                List<T> cacheCollection = new ArrayList<>(dataCollection);
                shuntMap.put(value, cacheCollection);
            }
        });
    }


    public static <K, T> void shuntNull(K key, T data, Collection<T> falseCollection, Map<K, List<T>> trueMap) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            shunt(key, data, trueMap);
        } else {
            falseCollection.add(data);
        }
    }


    public static <K, T> void shuntNull(K key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }
    }


    public static <T> void shuntContrast(Long srcKey, Long targetKey, T data, Collection<T> equalCollection, Map<Long, List<T>> unequalMap) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            shunt(targetKey, data, unequalMap);
        }
    }


    public static <K, T> void shuntContrast(K srcKey, K targetKey, T data, Collection<T> equalCollection, Collection<T> unequalCollection) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            unequalCollection.add(data);
        }
    }

    public static <K, T> void shuntContain(K srcKey, Collection<K> targetKeyCollection, T data, Map<K, List<T>> shuntMap) {
        if (targetKeyCollection.contains(srcKey)) {
            shunt(srcKey,data,shuntMap);
        }
    }
}
