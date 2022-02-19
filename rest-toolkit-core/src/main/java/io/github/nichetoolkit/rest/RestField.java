package io.github.nichetoolkit.rest;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>RestField</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestField extends RestValue<Integer,String> {

    String getField();

    @SuppressWarnings("Duplicates")
    static <T extends RestField> T parseField(Class<T> clazz, String field) {
        if (field != null && clazz.isEnum()) {
            Map<String,T> valueEnumMap =Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestField::getField, Function.identity()));
            return valueEnumMap.get(field);
        } else {
            return null;
        }
    }

}
