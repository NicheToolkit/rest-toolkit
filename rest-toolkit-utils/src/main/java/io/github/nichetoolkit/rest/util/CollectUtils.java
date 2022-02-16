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

    public static <K,V> Map<K,List<V>> collect(List<V> srcList, FunctionActuator<V,K> function) throws RestException {
        Map<K,List<V>> map = new HashMap<>();
        for (V src : srcList) {
            K key = function.actuate(src);
            collect(key,src,map);
        }
        return map;
    }

    public static <K,V> Map<K,List<V>> collect(List<V> srcList, Function<V,K> function) {
        Map<K,List<V>> map = new HashMap<>();
        for (V src : srcList) {
            K key = function.apply(src);
            collect(key,src,map);
        }
        return map;
    }

    public static <K,V> void collect(K key,V src, Map<K,List<V>> map){
        if (map.containsKey(key)) {
            map.get(key).add(src);
        } else {
            List<V> wrapList = new ArrayList<>();
            wrapList.add(src);
            map.put(key, wrapList);
        }
    }

    public static <T> void collect(boolean key, T data, List<T> falseList, List<T> trueList) {
        if (key) {
            trueList.add(data);
        } else {
            falseList.add(data);
        }

    }

    public static <K, T> void collect(K key, List<T> dataList, Map<K, List<T>> dataMap) {
        Optional.ofNullable(key).ifPresent(value -> {
            if (dataMap.containsKey(value)) {
                dataMap.get(value).addAll(dataList);
            } else {
                List<T> tempList = new ArrayList<>(dataList);
                dataMap.put(value, tempList);
            }
        });
    }

    public static <K, T> void collect(K key, T data, List<T> falseList, Map<K, List<T>> trueMap) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            collect(key, data, trueMap);
        } else {
            falseList.add(data);
        }
    }

    public static <K, T> void collect(K key, T data, List<T> falseList, List<T> trueList) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            trueList.add(data);
        } else {
            falseList.add(data);
        }
    }

    public static <T> void collect(Long srcKey, Long targetKey, T data, List<T> equalList, Map<Long, List<T>> unequalMap) {
        if (srcKey.equals(targetKey)) {
            equalList.add(data);
        } else {
            collect(targetKey, data, unequalMap);
        }
    }

    public static <K, T> void collect(K srcKey, K targetKey, T data, List<T> equalList, List<T> unequalList) {
        if (srcKey.equals(targetKey)) {
            equalList.add(data);
        } else {
            unequalList.add(data);
        }
    }

    public static <K, T> void collect(K srcKey, List<K> targetKeyList, T data, Map<K, List<T>> dataMap) {
        if (targetKeyList.contains(srcKey)) {
            collect(srcKey,data,dataMap);
        }
    }




}
