package io.github.nichetoolkit.rest.helper;


import com.google.common.collect.Lists;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.util.GeneralUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>PartitionHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PartitionHelper {

    /**
     * queryAll的分段处理
     * @param idList id集合
     * @param maxSize 分段大小
     * @param function 执行器（伪函数指针）
     * @param <T> 注册实体类型
     * @return List<T> 实体集合
     */
    public static <K,T> List<T> query(List<K> idList, Integer maxSize, FunctionActuator<List<K>, List<T>> function) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        List<T> entityList;
        if (idList.size() > maxSize) {
            entityList = new ArrayList<>();
            List<List<K>> partitionList = Lists.partition(idList, maxSize);
            for (List<K> partition : partitionList) {
                entityList.addAll(function.actuate(partition));
            }
        } else {
            entityList = function.actuate(idList);
        }
        return entityList;
    }

    /**
     * insertAll的分段处理
     * @param modelList 模型集合
     * @param maxSize 分段大小
     * @param function 执行器（伪函数指针）
     * @param <T> 模型类型
     * @return Integer 数量
     */
    public static <T> Boolean insert(List<T> modelList, Integer maxSize, FunctionActuator<List<T>,Integer> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return true;
        }
        Integer resultSize = 0;
        if (modelList.size() > maxSize) {
            List<List<T>> partitionList = Lists.partition(modelList, maxSize);
            for (List<T> partition : partitionList) {
                resultSize += function.actuate(partition);
            }
        } else {
            resultSize = function.actuate(modelList);
        }
        return resultSize == modelList.size();
    }


    /**
     * saveAll的分段处理
     * @param modelList 模型集合
     * @param maxSize 分段大小
     * @param function 执行器（伪函数指针）
     * @param <T> 模型类型
     * @return Boolean 是否成功
     */
    public static <T> Boolean save(List<T> modelList, Integer maxSize, FunctionActuator<List<T>, Integer> function) throws RestException {
        return insert(modelList,maxSize,function);
    }


    /**
     * updateAll的分段处理
     * @param modelList 模型集合
     * @param maxSize 分段大小
     * @param function 执行器（伪函数指针）
     * @param <T> 模型类型
     * @return Integer 数量
     */
    public static <T> Boolean update(List<T> modelList, Integer maxSize, FunctionActuator<List<T>, Integer> function) throws RestException {
        if (GeneralUtils.isEmpty(modelList)) {
            return true;
        }
        /* 数据分段处理 */
        boolean comparer;
        int size = modelList.size();
        if (size > maxSize) {
            List<List<T>> partitionList = Lists.partition(modelList, maxSize);
            Integer resultSize = 0;
            for (List<T> partition : partitionList) {
                resultSize += function.actuate(partition);
            }
            comparer = resultSize == partitionList.size();
        } else {
            Integer result = function.actuate(modelList);
            comparer = result > 0;
        }
        return comparer;
    }

    /**
     * deleteAll的分段处理
     * @param idList id集合
     * @param maxSize 分段大小
     * @param consumer 执行器（伪函数指针）
     * @throws RestException SDM模块异常
     */
    public static <K> void delete(List<K> idList, Integer maxSize, ConsumerActuator<List<K>> consumer) throws RestException {
        if (GeneralUtils.isEmpty(idList)) {
            return;
        }
        if (idList.size() > maxSize) {
            List<List<K>> partitionList = Lists.partition(idList, maxSize);
            for (List<K> partition : partitionList) {
                consumer.actuate(partition);
            }
        } else {
            consumer.actuate(idList);
        }
    }


}
