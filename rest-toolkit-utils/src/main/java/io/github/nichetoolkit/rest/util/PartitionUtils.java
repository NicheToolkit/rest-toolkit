package io.github.nichetoolkit.rest.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>PartitionUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PartitionUtils {
    
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


    public static <T> Boolean save(Collection<T> dataList, Integer saveSize, Function<Collection<T>, Integer> function) {
        return insert(dataList,saveSize,function);
    }

    
    public static <T> Boolean insert(Collection<T> dataList, Integer insertSize, Function<Collection<T>,Integer> function) {
        if (GeneralUtils.isEmpty(dataList)) {
            return true;
        }
        Integer resultSize = 0;
        if (dataList.size() > insertSize) {
            List<List<T>> partitionCollection = Lists.partition(new ArrayList<>(dataList), insertSize);
            for (Collection<T> partition : partitionCollection) {
                resultSize += function.apply(partition);
            }
        } else {
            resultSize = function.apply(dataList);
        }
        return resultSize == dataList.size();
    }


    public static <T> Boolean update(Collection<T> dataList, Integer updateSize, Function<Collection<T>, Integer> function) {
        if (GeneralUtils.isEmpty(dataList)) {
            return true;
        }
        boolean comparer;
        int size = dataList.size();
        if (size > updateSize) {
            List<List<T>> partitionCollection = Lists.partition(new ArrayList<>(dataList), updateSize);
            Integer resultSize = 0;
            for (Collection<T> partition : partitionCollection) {
                resultSize += function.apply(partition);
            }
            comparer = resultSize == partitionCollection.size();
        } else {
            Integer result = function.apply(dataList);
            comparer = result > 0;
        }
        return comparer;
    }

    public static <I> void delete(Collection<I> idList, Integer deleteSize, Consumer<Collection<I>> consumer) {
        partition(idList,deleteSize,consumer);
    }
}
