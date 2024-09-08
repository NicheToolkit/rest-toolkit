package io.github.nichetoolkit.rest;

import io.github.nichetoolkit.rest.stereotype.StereoEnum;
import io.github.nichetoolkit.rest.stereotype.StereoValue;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <code>RestStereo</code>
 * <p>The type rest stereo interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @see io.github.nichetoolkit.rest.stereotype.StereoEnum
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@SuppressWarnings("all")
public interface RestStereo extends RestValue<String,Long>, StereoEnum {

    /**
     * <code>key</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    default String key() {
        return getKey();
    }

    /**
     * <code>type</code>
     * <p>the method.</p>
     * @return int <p>the return object is <code>int</code> type.</p>
     */
    default int type() {
        return ordinal();
    }

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    default long value() {
        return getValue();
    }

    @Override
    default Class<? extends Annotation> annotationType() {
        return StereoValue.class;
    }

    /**
     * <code>names</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<String> names(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestStereo::name).distinct().collect(Collectors.toList());
    }

    /**
     * <code>keys</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<String> keys(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestStereo::key).distinct().collect(Collectors.toList());
    }

    /**
     * <code>types</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<Integer> types(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestStereo::type).distinct().collect(Collectors.toList());
    }

    /**
     * <code>values</code>
     * <p>the method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<Long> values(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestStereo::value).distinct().collect(Collectors.toList());
    }

    /**
     * <code>nameType</code>
     * <p>the type method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the type return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<RestPack> nameType(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestPack::nameType).distinct().collect(Collectors.toList());
    }

    /**
     * <code>typeName</code>
     * <p>the name method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the name return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<RestPack> typeName(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestPack::typeName).distinct().collect(Collectors.toList());
    }

    /**
     * <code>typeKey</code>
     * <p>the key method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the key return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<RestPack> typeKey(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestPack::typeKey).distinct().collect(Collectors.toList());
    }

    /**
     * <code>keyType</code>
     * <p>the type method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the type return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<RestPack> keyType(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestPack::keyType).distinct().collect(Collectors.toList());
    }

    /**
     * <code>typeValue</code>
     * <p>the value method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the value return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<RestPack> typeValue(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestPack::typeValue).distinct().collect(Collectors.toList());
    }

    /**
     * <code>valueType</code>
     * <p>the type method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the type return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<RestPack> valueType(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestPack::valueType).distinct().collect(Collectors.toList());
    }

    /**
     * <code>packEnum</code>
     * <p>the enum method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the enum return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    static <T extends RestStereo> List<RestEnum> packEnum(Class<T> clazz) {
        return RestValue.lists(clazz).stream().map(RestEnum::fromStereo).distinct().collect(Collectors.toList());
    }

    /**
     * <code>parseName</code>
     * <p>the name method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @return T <p>the name return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestStereo> T parseName(Class<T> clazz, String name) {
        if (name != null && clazz.isEnum()) {
            Map<String, T> typeEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestStereo::name, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return typeEnumMap.get(name);
        } else {
            return null;
        }
    }

    /**
     * <code>parseName</code>
     * <p>the name method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param values {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param name   {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @return T <p>the name return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    static <T extends RestStereo> T parseName(Collection<T> values, String name) {
        if (name != null && values != null && !values.isEmpty()) {
            Map<String, T> valueEnumMap = values.stream().collect(Collectors.toMap(RestStereo::name, Function.identity(),(oldValue, newValue) -> newValue, HashMap::new));
            return valueEnumMap.get(name);
        } else {
            return null;
        }
    }

    /**
     * <code>parseType</code>
     * <p>the type method.</p>
     * @param <T>   {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param type  {@link java.lang.Integer} <p>the type parameter is <code>Integer</code> type.</p>
     * @return T <p>the type return object is <code>T</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.Integer
     */
    static <T extends RestStereo> T parseType(Class<T> clazz, Integer type) {
        if (type != null && clazz.isEnum()) {
            Map<Integer, T> typeEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestStereo::ordinal, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return typeEnumMap.get(type);
        } else {
            return null;
        }
    }

    /**
     * <code>parseType</code>
     * <p>the type method.</p>
     * @param <T>    {@link io.github.nichetoolkit.rest.RestStereo} <p>the generic parameter is <code>RestStereo</code> type.</p>
     * @param values {@link java.util.Collection} <p>the values parameter is <code>Collection</code> type.</p>
     * @param type   {@link java.lang.Integer} <p>the type parameter is <code>Integer</code> type.</p>
     * @return T <p>the type return object is <code>T</code> type.</p>
     * @see java.util.Collection
     * @see java.lang.Integer
     */
    static <T extends RestStereo> T parseType(Collection<T> values, Integer type) {
        if (type != null && values != null && !values.isEmpty()) {
            Map<Integer, T> typeEnumMap = values.stream().collect(Collectors.toMap(RestStereo::ordinal, Function.identity(), (oldValue, newValue) -> newValue, HashMap::new));
            return typeEnumMap.get(type);
        } else {
            return null;
        }
    }
}
