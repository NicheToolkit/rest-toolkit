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

    public static <I> void delete(Collection<I> idList, Integer deleteSize, Consumer<Collection<I>> consumer) {
        partition(idList,deleteSize,consumer);
    }
}
