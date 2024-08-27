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
 * <code>PartitionHelper</code>
 * <p>The type partition helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class PartitionHelper {

    /**
     * <code>partition</code>
     * <p>the method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param dataList      {@link java.util.Collection} <p>the data list parameter is <code>Collection</code> type.</p>
     * @param partitionSize {@link java.lang.Integer} <p>the partition size parameter is <code>Integer</code> type.</p>
     * @param consumer      {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
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


    /**
     * <code>query</code>
     * <p>the method.</p>
     * @param <I>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param idList    {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param querySize {@link java.lang.Integer} <p>the query size parameter is <code>Integer</code> type.</p>
     * @param function  {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.RestException
     */
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

    /**
     * <code>save</code>
     * <p>the method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param dataList {@link java.util.Collection} <p>the data list parameter is <code>Collection</code> type.</p>
     * @param saveSize {@link java.lang.Integer} <p>the save size parameter is <code>Integer</code> type.</p>
     * @param function {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>the function parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link java.lang.Integer} <p>the return object is <code>Integer</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> Integer save(Collection<T> dataList, Integer saveSize, FunctionActuator<Collection<T>,Integer> function) throws RestException {
        if (GeneralUtils.isEmpty(dataList)) {
            return 0;
        }
        Integer result = 0;
        if (dataList.size() > saveSize) {
            List<List<T>> partitionList = Lists.partition(new ArrayList<>(dataList), saveSize);
            for (List<T> partition : partitionList) {
                result += function.actuate(partition);
            }
        } else {
            result = function.actuate(dataList);
        }
        return result;
    }

    /**
     * <code>delete</code>
     * <p>the method.</p>
     * @param <I>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param idList     {@link java.util.Collection} <p>the id list parameter is <code>Collection</code> type.</p>
     * @param deleteSize {@link java.lang.Integer} <p>the delete size parameter is <code>Integer</code> type.</p>
     * @param consumer   {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>the consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <I> void delete(Collection<I> idList, Integer deleteSize, ConsumerActuator<Collection<I>> consumer) throws RestException {
        partition(idList,deleteSize,consumer);
    }


}
