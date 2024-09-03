package io.github.nichetoolkit.rest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestStatus</code>
 * <p>The type rest status interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface RestStatus {

    /**
     * <code>name</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String name();

    /**
     * <code>getStatus</code>
     * <p>the status getter method.</p>
     * @return {@link java.lang.Integer} <p>the status return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    Integer getStatus();

    /**
     * <code>getMessage</code>
     * <p>the message getter method.</p>
     * @return {@link java.lang.String} <p>the message return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String getMessage();

    /**
     * <code>entry</code>
     * <p>the method.</p>
     * @return {@link java.util.Map} <p>the return object is <code>Map</code> type.</p>
     * @see java.util.Map
     */
    default Map<Integer,String> entry() {
        return Collections.singletonMap(this.getStatus(),this.getMessage());
    }

    /**
     * <code>lists</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStatus} <p>the generic parameter is <code>RestStatus</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStatus> List<T> lists(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    /**
     * <code>entries</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStatus} <p>the generic parameter is <code>RestStatus</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStatus> List<Map<Integer,String>> entries(Class<T> clazz) {
        return lists(clazz).stream().map(RestStatus::entry).distinct().collect(Collectors.toList());
    }

    /**
     * <code>statuses</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStatus} <p>the generic parameter is <code>RestStatus</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStatus> List<Integer> statuses(Class<T> clazz) {
        return lists(clazz).stream().map(RestStatus::getStatus).distinct().collect(Collectors.toList());
    }

    /**
     * <code>messages</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStatus} <p>the generic parameter is <code>RestStatus</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStatus> List<String> messages(Class<T> clazz) {
        return lists(clazz).stream().map(RestStatus::getMessage).distinct().collect(Collectors.toList());
    }

    /**
     * <code>confirm</code>
     * <p>the method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestStatus} <p>the generic parameter is <code>RestStatus</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.Boolean} <p>the return object is <code>Boolean</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Integer
     * @see java.lang.Boolean
     */
    static <T extends RestStatus> Boolean confirm(Class<T> clazz, Integer status) {
        return Optional.ofNullable(parseStatus(clazz,status)).isPresent();
    }

    /**
     * <code>parseStatus</code>
     * <p>the status method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestStatus} <p>the generic parameter is <code>RestStatus</code> type.</p>
     * @param clazz  {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @return T <p>the status return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Integer
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestStatus> T parseStatus(Class<T> clazz, Integer status){
        if (status != null && clazz.isEnum()) {
            Map<Integer, T> errorMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestStatus::getStatus, Function.identity(),(oldValue,newValue) -> newValue, HashMap::new));
            return errorMap.get(status);
        }
        return null;
    }

}
