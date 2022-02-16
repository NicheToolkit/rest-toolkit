package io.github.nichetoolkit.rest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>RiceStatus</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestStatus {

    String name();

    Integer getStatus();

    String getMessage();

    default Map<Integer,String> entry() {
        return Collections.singletonMap(this.getStatus(),this.getMessage());
    }

    static <T extends RestStatus> List<T> lists(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    static <T extends RestStatus> List<Map<Integer,String>> entries(Class<T> clazz) {
        return lists(clazz).stream().map(RestStatus::entry).distinct().collect(Collectors.toList());
    }

    static <T extends RestStatus> List<Integer> statuses(Class<T> clazz) {
        return lists(clazz).stream().map(RestStatus::getStatus).distinct().collect(Collectors.toList());
    }

    static <T extends RestStatus> List<String> messages(Class<T> clazz) {
        return lists(clazz).stream().map(RestStatus::getMessage).distinct().collect(Collectors.toList());
    }

    static <T extends RestStatus> Boolean confirm(Class<T> clazz, Integer status) {
        return Optional.ofNullable(parseStatus(clazz,status)).isPresent();
    }

    @SuppressWarnings("Duplicates")
    static <T extends RestStatus> T parseStatus(Class<T> clazz, Integer status){
        if (status != null && clazz.isEnum()) {
            Map<Integer, T> errorMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestStatus::getStatus, Function.identity()));
            return errorMap.get(status);
        }
        return null;
    }

}
