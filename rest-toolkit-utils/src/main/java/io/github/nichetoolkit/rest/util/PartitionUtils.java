package io.github.nichetoolkit.rest.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <code>PartitionUtils</code>
 * <p>The partition utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class PartitionUtils {

    /**
     * <code>partition</code>
     * <p>The partition method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param dataList      {@link java.util.Collection} <p>The data list parameter is <code>Collection</code> type.</p>
     * @param partitionSize {@link java.lang.Integer} <p>The partition size parameter is <code>Integer</code> type.</p>
     * @param consumer      {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see java.util.function.Consumer
     */
    public static <T> void partition(Collection<T> dataList, Integer partitionSize, Consumer<Collection<T>> consumer) {
        if (GeneralUtils.isEmpty(dataList)) {
            return;
        }
        if (dataList.size() > partitionSize) {
            List<List<T>> partitionList = Lists.partition(new ArrayList<>(dataList), partitionSize);
            partitionList.forEach(consumer);
        } else {
            consumer.accept(dataList);
        }
    }

    /**
     * <code>query</code>
     * <p>The query method.</p>
     * @param <I>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param idList    {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param querySize {@link java.lang.Integer} <p>The query size parameter is <code>Integer</code> type.</p>
     * @param function  {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return {@link java.util.List} <p>The query return object is <code>List</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see java.util.function.Function
     * @see java.util.List
     */
    public static <I,T> List<T> query(Collection<I> idList, Integer querySize, Function<Collection<I>, List<T>> function) {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<T> entityList;
        if (idList.size() > querySize) {
            entityList = new ArrayList<>();
            List<List<I>> partitionList = Lists.partition(new ArrayList<>(idList), querySize);
            partitionList.stream().map(function).forEach(entityList::addAll);
        } else {
            entityList = function.apply(idList);
        }
        return entityList;
    }

    /**
     * <code>save</code>
     * <p>The save method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param dataList {@link java.util.Collection} <p>The data list parameter is <code>Collection</code> type.</p>
     * @param saveSize {@link java.lang.Integer} <p>The save size parameter is <code>Integer</code> type.</p>
     * @param function {@link java.util.function.Function} <p>The function parameter is <code>Function</code> type.</p>
     * @return {@link java.lang.Integer} <p>The save return object is <code>Integer</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see java.util.function.Function
     */
    public static <T> Integer save(Collection<T> dataList, Integer saveSize, Function<Collection<T>,Integer> function) {
        if (GeneralUtils.isEmpty(dataList)) {
            return 0;
        }
        Integer result = 0;
        if (dataList.size() > saveSize) {
            List<List<T>> partitionCollection = Lists.partition(new ArrayList<>(dataList), saveSize);
            for (Collection<T> partition : partitionCollection) {
                result += function.apply(partition);
            }
        } else {
            result = function.apply(dataList);
        }
        return result;
    }

    /**
     * <code>delete</code>
     * <p>The delete method.</p>
     * @param <I>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param idList     {@link java.util.Collection} <p>The id list parameter is <code>Collection</code> type.</p>
     * @param deleteSize {@link java.lang.Integer} <p>The delete size parameter is <code>Integer</code> type.</p>
     * @param consumer   {@link java.util.function.Consumer} <p>The consumer parameter is <code>Consumer</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see java.util.function.Consumer
     */
    public static <I> void delete(Collection<I> idList, Integer deleteSize, Consumer<Collection<I>> consumer) {
        partition(idList,deleteSize,consumer);
    }
}
