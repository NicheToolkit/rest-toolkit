package io.github.nichetoolkit.rest.util;

import com.google.common.collect.Lists;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>PartitionUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PartitionUtils {

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

  
    public static <K,T> List<T> query(Collection<K> idList, Integer partitionSize, FunctionActuator<Collection<K>, List<T>> function) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<T> entityList;
        if (idList.size() > partitionSize) {
            entityList = new ArrayList<>();
            List<List<K>> partitionList = Lists.partition(new ArrayList<>(idList), partitionSize);
            for (List<K> partition : partitionList) {
                entityList.addAll(function.actuate(partition));
            }
        } else {
            entityList = function.actuate(idList);
        }
        return entityList;
    }

    
    public static <T> Boolean save(Collection<T> dataList, Integer partitionSize, FunctionActuator<Collection<T>, Integer> function) throws RestException {
        if (GeneralUtils.isEmpty(dataList)) {
            return true;
        }
        Integer result = 0;
        if (dataList.size() > partitionSize) {
            List<List<T>> partitionList = Lists.partition(new ArrayList<>(dataList), partitionSize);
            for (List<T> partition : partitionList) {
                /** 每一批数据成功返回1  */
                result += function.actuate(partition);
            }
        } else {
            result = function.actuate(dataList);
        }
        return result == dataList.size();
    }


  
    public static <K> void delete(Collection<K> idList, Integer partitionSize, ConsumerActuator<Collection<K>> consumer) throws RestException {
        partition(idList,partitionSize,consumer);
    }

}
