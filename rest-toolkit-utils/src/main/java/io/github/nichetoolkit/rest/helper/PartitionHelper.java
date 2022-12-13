package io.github.nichetoolkit.rest.helper;


import com.google.common.collect.Lists;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>PartitionHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PartitionHelper {

    public static <T> void partition(Collection<T> dataList, Integer partitionSize, ConsumerActuator<Collection<T>> consumer) throws RestException {
        if (GeneralUtils.isEmpty(dataList)) {
            return;
        }
        if (dataList.size() > partitionSize) {
            List<List<T>> partitionList = Lists.partition(new ArrayList<>(dataList), partitionSize);
            for (List<T> partition : partitionList) {
                consumer.actuate(partition);
            }
        } else {
            consumer.actuate(dataList);
        }
    }
    

    public static <I,T> List<T> query(Collection<I> idList, Integer querySize, FunctionActuator<Collection<I>, List<T>> function) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<T> entityList;
        if (idList.size() > querySize) {
            entityList = new ArrayList<>();
            List<List<I>> partitionList = Lists.partition(new ArrayList<>(idList), querySize);
            for (List<I> partition : partitionList) {
                entityList.addAll(function.actuate(partition));
            }
        } else {
            entityList = function.actuate(idList);
        }
        return entityList;
    }

    public static <T> Boolean save(Collection<T> modelList, Integer saveSize, FunctionActuator<Collection<T>,Integer> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return true;
        }
        Integer resultSize = 0;
        if (modelList.size() > saveSize) {
            List<List<T>> partitionList = Lists.partition(new ArrayList<>(modelList), saveSize);
            for (List<T> partition : partitionList) {
                resultSize += function.actuate(partition);
            }
        } else {
            resultSize = function.actuate(modelList);
        }
        return resultSize == modelList.size();
    }

    public static <I> void delete(Collection<I> idList, Integer deleteSize, ConsumerActuator<Collection<I>> consumer) throws RestException {
        partition(idList,deleteSize,consumer);
    }


}
