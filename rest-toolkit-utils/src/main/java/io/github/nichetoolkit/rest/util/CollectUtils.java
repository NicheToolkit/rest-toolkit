package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;

import java.util.*;
import java.util.function.Function;

/**
 * <p>CollectUtils</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class CollectUtils {

    public static <K,V> Map<K,Collection<V>> collect(Collection<V> srcCollection, FunctionActuator<V,K> function) throws RestException {
        Map<K,Collection<V>> map = new HashMap<>();
        for (V src : srcCollection) {
            K key = function.actuate(src);
            collect(key,src,map);
        }
        return map;
    }

    public static <K,V> Map<K,Collection<V>> collect(Collection<V> srcCollection, Function<V,K> function) {
        Map<K,Collection<V>> map = new HashMap<>();
        for (V src : srcCollection) {
            K key = function.apply(src);
            collect(key,src,map);
        }
        return map;
    }

    public static <K,V> void collect(K key,V src, Map<K,Collection<V>> map) {
        if (map.containsKey(key)) {
            map.get(key).add(src);
        } else {
            Collection<V> wrapCollection = new ArrayList<>();
            wrapCollection.add(src);
            map.put(key, wrapCollection);
        }
    }

    public static <T> void collect(boolean key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        if (key) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }

    }

    public static <K, T> void collect(K key, Collection<T> dataCollection, Map<K, Collection<T>> dataMap) {
        Optional.ofNullable(key).ifPresent(value -> {
            if (dataMap.containsKey(value)) {
                dataMap.get(value).addAll(dataCollection);
            } else {
                Collection<T> tempCollection = new ArrayList<>(dataCollection);
                dataMap.put(value, tempCollection);
            }
        });
    }

    public static <K, T> void collect(K key, T data, Collection<T> falseCollection, Map<K, Collection<T>> trueMap) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            collect(key, data, trueMap);
        } else {
            falseCollection.add(data);
        }
    }

    public static <K, T> void collect(K key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }
    }

    public static <T> void collect(Long srcKey, Long targetKey, T data, Collection<T> equalCollection, Map<Long, Collection<T>> unequalMap) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            collect(targetKey, data, unequalMap);
        }
    }

    public static <K, T> void collect(K srcKey, K targetKey, T data, Collection<T> equalCollection, Collection<T> unequalCollection) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            unequalCollection.add(data);
        }
    }

    public static <K, T> void collect(K srcKey, Collection<K> targetKeyCollection, T data, Map<K, Collection<T>> dataMap) {
        if (targetKeyCollection.contains(srcKey)) {
            collect(srcKey,data,dataMap);
        }
    }




}
