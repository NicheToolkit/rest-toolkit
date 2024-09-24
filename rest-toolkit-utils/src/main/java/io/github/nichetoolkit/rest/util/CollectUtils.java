package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;

import java.util.*;
import java.util.function.Function;

/**
 * <code>CollectUtils</code>
 * <p>The type collect utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class CollectUtils {

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param srcCollection {@link java.util.Collection} <p>the src collection parameter is <code>Collection</code> type.</p>
     * @param function      {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.Map} <p>the return object is <code>Map</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <K,V> Map<K,List<V>> collect(Collection<V> srcCollection, FunctionActuator<V,K> function) throws RestException {
        Map<K,List<V>> map = new HashMap<>();
        for (V src : srcCollection) {
            K key = function.actuate(src);
            collect(key,src,map);
        }
        return map;
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param srcCollection {@link java.util.Collection} <p>the src collection parameter is <code>Collection</code> type.</p>
     * @param function      {@link java.util.function.Function} <p>the function parameter is <code>Function</code> type.</p>
     * @return {@link java.util.Map} <p>the return object is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.function.Function
     * @see java.util.Map
     */
    public static <K,V> Map<K,List<V>> collect(Collection<V> srcCollection, Function<V,K> function) {
        Map<K,List<V>> map = new HashMap<>();
        for (V src : srcCollection) {
            K key = function.apply(src);
            collect(key,src,map);
        }
        return map;
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <V> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key K <p>the key parameter is <code>K</code> type.</p>
     * @param src V <p>the src parameter is <code>V</code> type.</p>
     * @param map {@link java.util.Map} <p>the map parameter is <code>Map</code> type.</p>
     * @see java.util.Map
     */
    public static <K,V> void collect(K key,V src, Map<K,List<V>> map) {
        if (map.containsKey(key)) {
            map.get(key).add(src);
        } else {
            List<V> wrapList = new ArrayList<>();
            wrapList.add(src);
            map.put(key, wrapList);
        }
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key             boolean <p>the key parameter is <code>boolean</code> type.</p>
     * @param data            T <p>the data parameter is <code>T</code> type.</p>
     * @param falseCollection {@link java.util.Collection} <p>the false collection parameter is <code>Collection</code> type.</p>
     * @param trueCollection  {@link java.util.Collection} <p>the true collection parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public static <T> void collect(boolean key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        if (key) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }

    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K>            {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>            {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key            K <p>the key parameter is <code>K</code> type.</p>
     * @param dataCollection {@link java.util.Collection} <p>the data collection parameter is <code>Collection</code> type.</p>
     * @param dataMap        {@link java.util.Map} <p>the data map parameter is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.Map
     */
    @SuppressWarnings("Duplicates")
    public static <K, T> void collect(K key, Collection<T> dataCollection, Map<K, List<T>> dataMap) {
        Optional.ofNullable(key).ifPresent(value -> {
            if (dataMap.containsKey(value)) {
                dataMap.get(value).addAll(dataCollection);
            } else {
                List<T> tempList = new ArrayList<>(dataCollection);
                dataMap.put(value, tempList);
            }
        });
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key             K <p>the key parameter is <code>K</code> type.</p>
     * @param data            T <p>the data parameter is <code>T</code> type.</p>
     * @param falseCollection {@link java.util.Collection} <p>the false collection parameter is <code>Collection</code> type.</p>
     * @param trueMap         {@link java.util.Map} <p>the true map parameter is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.Map
     */
    public static <K, T> void collect(K key, T data, Collection<T> falseCollection, Map<K, List<T>> trueMap) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            collect(key, data, trueMap);
        } else {
            falseCollection.add(data);
        }
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key             K <p>the key parameter is <code>K</code> type.</p>
     * @param data            T <p>the data parameter is <code>T</code> type.</p>
     * @param falseCollection {@link java.util.Collection} <p>the false collection parameter is <code>Collection</code> type.</p>
     * @param trueCollection  {@link java.util.Collection} <p>the true collection parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public static <K, T> void collect(K key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param srcKey          {@link java.lang.Long} <p>the src key parameter is <code>Long</code> type.</p>
     * @param targetKey       {@link java.lang.Long} <p>the target key parameter is <code>Long</code> type.</p>
     * @param data            T <p>the data parameter is <code>T</code> type.</p>
     * @param equalCollection {@link java.util.Collection} <p>the equal collection parameter is <code>Collection</code> type.</p>
     * @param unequalMap      {@link java.util.Map} <p>the unequal map parameter is <code>Map</code> type.</p>
     * @see java.lang.Long
     * @see java.util.Collection
     * @see java.util.Map
     */
    public static <T> void collect(Long srcKey, Long targetKey, T data, Collection<T> equalCollection, Map<Long, List<T>> unequalMap) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            collect(targetKey, data, unequalMap);
        }
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param srcKey            K <p>the src key parameter is <code>K</code> type.</p>
     * @param targetKey         K <p>the target key parameter is <code>K</code> type.</p>
     * @param data              T <p>the data parameter is <code>T</code> type.</p>
     * @param equalCollection   {@link java.util.Collection} <p>the equal collection parameter is <code>Collection</code> type.</p>
     * @param unequalCollection {@link java.util.Collection} <p>the unequal collection parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public static <K, T> void collect(K srcKey, K targetKey, T data, Collection<T> equalCollection, Collection<T> unequalCollection) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            unequalCollection.add(data);
        }
    }

    /**
     * <code>collect</code>
     * <p>the method.</p>
     * @param <K>                 {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>                 {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param srcKey              K <p>the src key parameter is <code>K</code> type.</p>
     * @param targetKeyCollection {@link java.util.Collection} <p>the target key collection parameter is <code>Collection</code> type.</p>
     * @param data                T <p>the data parameter is <code>T</code> type.</p>
     * @param dataMap             {@link java.util.Map} <p>the data map parameter is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.Map
     */
    public static <K, T> void collect(K srcKey, Collection<K> targetKeyCollection, T data, Map<K, List<T>> dataMap) {
        if (targetKeyCollection.contains(srcKey)) {
            collect(srcKey,data,dataMap);
        }
    }




}
