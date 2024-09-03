package io.github.nichetoolkit.rest.util;

import java.util.*;

/**
 * <code>ShuntUtils</code>
 * <p>The type shunt utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ShuntUtils {

    /**
     * <code>shunt</code>
     * <p>the method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key             boolean <p>the key parameter is <code>boolean</code> type.</p>
     * @param data            T <p>the data parameter is <code>T</code> type.</p>
     * @param falseCollection {@link java.util.Collection} <p>the false collection parameter is <code>Collection</code> type.</p>
     * @param trueCollection  {@link java.util.Collection} <p>the true collection parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public static <T> void shunt(boolean key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        if (key) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }
    }

    /**
     * <code>shunt</code>
     * <p>the method.</p>
     * @param <K>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key      K <p>the key parameter is <code>K</code> type.</p>
     * @param data     T <p>the data parameter is <code>T</code> type.</p>
     * @param shuntMap {@link java.util.Map} <p>the shunt map parameter is <code>Map</code> type.</p>
     * @see java.util.Map
     */
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

    /**
     * <code>shunt</code>
     * <p>the method.</p>
     * @param <K>            {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>            {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key            K <p>the key parameter is <code>K</code> type.</p>
     * @param dataCollection {@link java.util.Collection} <p>the data collection parameter is <code>Collection</code> type.</p>
     * @param shuntMap       {@link java.util.Map} <p>the shunt map parameter is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.Map
     */
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


    /**
     * <code>shuntNull</code>
     * <p>the null method.</p>
     * @param <K>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key             K <p>the key parameter is <code>K</code> type.</p>
     * @param data            T <p>the data parameter is <code>T</code> type.</p>
     * @param falseCollection {@link java.util.Collection} <p>the false collection parameter is <code>Collection</code> type.</p>
     * @param trueMap         {@link java.util.Map} <p>the true map parameter is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.Map
     */
    public static <K, T> void shuntNull(K key, T data, Collection<T> falseCollection, Map<K, List<T>> trueMap) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            shunt(key, data, trueMap);
        } else {
            falseCollection.add(data);
        }
    }


    /**
     * <code>shuntNull</code>
     * <p>the null method.</p>
     * @param <K>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param key             K <p>the key parameter is <code>K</code> type.</p>
     * @param data            T <p>the data parameter is <code>T</code> type.</p>
     * @param falseCollection {@link java.util.Collection} <p>the false collection parameter is <code>Collection</code> type.</p>
     * @param trueCollection  {@link java.util.Collection} <p>the true collection parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public static <K, T> void shuntNull(K key, T data, Collection<T> falseCollection, Collection<T> trueCollection) {
        Optional<K> optional = Optional.ofNullable(key);
        if (optional.isPresent()) {
            trueCollection.add(data);
        } else {
            falseCollection.add(data);
        }
    }


    /**
     * <code>shuntContrast</code>
     * <p>the contrast method.</p>
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
    public static <T> void shuntContrast(Long srcKey, Long targetKey, T data, Collection<T> equalCollection, Map<Long, List<T>> unequalMap) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            shunt(targetKey, data, unequalMap);
        }
    }


    /**
     * <code>shuntContrast</code>
     * <p>the contrast method.</p>
     * @param <K>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param srcKey            K <p>the src key parameter is <code>K</code> type.</p>
     * @param targetKey         K <p>the target key parameter is <code>K</code> type.</p>
     * @param data              T <p>the data parameter is <code>T</code> type.</p>
     * @param equalCollection   {@link java.util.Collection} <p>the equal collection parameter is <code>Collection</code> type.</p>
     * @param unequalCollection {@link java.util.Collection} <p>the unequal collection parameter is <code>Collection</code> type.</p>
     * @see java.util.Collection
     */
    public static <K, T> void shuntContrast(K srcKey, K targetKey, T data, Collection<T> equalCollection, Collection<T> unequalCollection) {
        if (srcKey.equals(targetKey)) {
            equalCollection.add(data);
        } else {
            unequalCollection.add(data);
        }
    }

    /**
     * <code>shuntContain</code>
     * <p>the contain method.</p>
     * @param <K>                 {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>                 {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param srcKey              K <p>the src key parameter is <code>K</code> type.</p>
     * @param targetKeyCollection {@link java.util.Collection} <p>the target key collection parameter is <code>Collection</code> type.</p>
     * @param data                T <p>the data parameter is <code>T</code> type.</p>
     * @param shuntMap            {@link java.util.Map} <p>the shunt map parameter is <code>Map</code> type.</p>
     * @see java.util.Collection
     * @see java.util.Map
     */
    public static <K, T> void shuntContain(K srcKey, Collection<K> targetKeyCollection, T data, Map<K, List<T>> shuntMap) {
        if (targetKeyCollection.contains(srcKey)) {
            shunt(srcKey,data,shuntMap);
        }
    }
}
