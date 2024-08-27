package io.github.nichetoolkit.rest.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.error.ClassUnsupportedException;
import io.github.nichetoolkit.rest.error.json.JsonParseBeanException;
import io.github.nichetoolkit.rest.error.json.JsonParseListException;
import io.github.nichetoolkit.rest.error.json.JsonParseMapException;
import io.github.nichetoolkit.rest.error.json.JsonParseSetException;
import io.github.nichetoolkit.rest.error.supply.JsonParseException;
import io.github.nichetoolkit.rest.helper.JsonHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <code>JsonUtils</code>
 * <p>The type json utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings({"TypeParameterUnusedInFormals","SameNameButDifferent"})
public class JsonUtils {

    /**
     * <code>parseJson</code>
     * <p>the json method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param target {@link T} <p>the target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>the json return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static <T> String parseJson(T target) {
        try {
            return JsonHelper.parseJson(target);
        } catch (JsonParseException exception) {
            log.error("It is failed during bean to parse as json! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseJson</code>
     * <p>the json method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param target        {@link T} <p>the target parameter is <code>T</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.lang.String} <p>the json return object is <code>String</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.lang.String
     */
    public static <T> String parseJson(T target, TypeReference<?> typeReference) {
        try {
            return JsonHelper.parseJson(target,typeReference);
        } catch (JsonParseException exception) {
            log.error("It is failed during bean to parse as json! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseJsonIgnoreNull</code>
     * <p>the json ignore null method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param target {@link T} <p>the target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>the json ignore null return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static <T> String parseJsonIgnoreNull(T target) {
        try {
            return JsonHelper.parseJsonIgnoreNull(target);
        } catch (JsonParseException exception) {
            log.error("It is failed during bean to parse as json with ignoring null! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>the bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link T} <p>the bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <T> T parseBean(String json, Class<T> clazz) {
        try {
            return JsonHelper.parseBean(json, clazz);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as bean! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }


    /**
     * <code>parseBean</code>
     * <p>the bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link T} <p>the bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T parseBean(String json, TypeReference<T> typeReference) {
        try {
            return JsonHelper.parseBean(json, typeReference);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as bean! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>the bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json     {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>the java type parameter is <code>JavaType</code> type.</p>
     * @return {@link T} <p>the bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     */
    public static <T> T parseBean(String json, JavaType javaType) {
        try {
            return JsonHelper.parseBean(json, javaType);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as bean! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>the bean method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <U>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json       {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>the inner clazz parameter is <code>Class</code> type.</p>
     * @return {@link T} <p>the bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <T, U> T parseBean(String json, Class<T> clazz, Class<U> innerClazz) {
        try {
            return JsonHelper.parseBean(json, clazz, innerClazz);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as bean<E>! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseList</code>
     * <p>the list method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json     {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>the list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     */
    public static <T> List<T> parseList(String json, CollectionType listType) {
        try {
            return JsonHelper.parseList(json, listType);
        } catch (JsonParseListException exception) {
            log.error("It is failed during json to parse as list of collection! {}", exception.getMessage());
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>the list method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     */
    public static <T> List<T> parseList(String json, TypeReference<List<T>> typeReference) {
        try {
            return JsonHelper.parseList(json, typeReference);
        } catch (JsonParseListException exception) {
            log.error("It is failed during json to parse as list of collection! {}", exception.getMessage());
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>the list method.</p>
     * @param <Z>        {@link java.util.List} <p>the generic parameter is <code>List</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json       {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>the parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> List<T> parseList(String json, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    /**
     * <code>parseList</code>
     * <p>the list method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return parseList(json, List.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>the set method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json    {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>the set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>the set return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(String json, CollectionType setType) {
        try {
            return JsonHelper.parseSet(json, setType);
        } catch (JsonParseSetException exception) {
            log.error("It is failed during json to parse as set of collection! {}", exception.getMessage());
            exception.printStackTrace();
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>the set method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>the set return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(String json, TypeReference<Set<T>> typeReference) {
        try {
            return JsonHelper.parseSet(json, typeReference);
        } catch (JsonParseSetException exception) {
            log.error("It is failed during json to parse as set of collection! {}", exception.getMessage());
            exception.printStackTrace();
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>the set method.</p>
     * @param <Z>        {@link java.util.Set} <p>the generic parameter is <code>Set</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json       {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>the parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>the set return object is <code>Set</code> type.</p>
     * @see java.util.Set
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(String json, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>the set method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>the set return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(String json, Class<T> clazz) {
        return parseSet(json, Set.class, clazz);
    }

    /**
     * <code>parseMap</code>
     * <p>the map method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json    {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>the map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>the map return object is <code>Map</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(String json, MapType mapType) {
        try {
            return JsonHelper.parseMap(json, mapType);
        } catch (JsonParseMapException exception) {
            log.error("It is failed during json to parse as map of bean! {}", exception.getMessage(),exception);
            exception.printStackTrace();
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>the map method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>the map return object is <code>Map</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(String json, TypeReference<Map<T, K>> typeReference) {
        try {
            return JsonHelper.parseMap(json, typeReference);
        } catch (JsonParseMapException exception) {
            log.error("It is failed during json to parse as map of bean! {}", exception.getMessage());
            exception.printStackTrace();
            return Collections.emptyMap();
        }
    }


    /**
     * <code>parseArray</code>
     * <p>the array method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json      {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>the array type parameter is <code>ArrayType</code> type.</p>
     * @return {@link T} <p>the array return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.ArrayType
     */
    public static <T> T[] parseArray(String json, ArrayType arrayType) {
        try {
            return JsonHelper.parseArray(json, arrayType);
        } catch (JsonParseListException exception) {
            log.error("It is failed during json to parse as array of bean! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>the array method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link T} <p>the array return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T[] parseArray(String json, TypeReference<T> typeReference) {
        try {
            return JsonHelper.parseArray(json, typeReference);
        } catch (JsonParseListException exception) {
            log.error("It is failed during json to parse as array of bean! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>the array method.</p>
     * @param <Z>   {@link java.util.List} <p>the generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link T} <p>the array return object is <code>T</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> T[] parseArray(String json, Class<T> clazz) {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseMap</code>
     * <p>the map method.</p>
     * @param <Z>        {@link java.util.Map} <p>the generic parameter is <code>Map</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json       {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>the parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>the key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>the value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>the map return object is <code>Map</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(String json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>the map method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json       {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>the key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>the value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>the map return object is <code>Map</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(String json, Class<T> keyClazz, Class<K> valueClazz) {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }


    /**
     * <code>parseMapList</code>
     * <p>the map list method.</p>
     * @param <H>            {@link java.util.List} <p>the generic parameter is <code>List</code> type.</p>
     * @param <Y>            {@link java.util.Map} <p>the generic parameter is <code>Map</code> type.</p>
     * @param <T>            {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>            {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json           {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>the parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz  {@link java.lang.Class} <p>the parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz       {@link java.lang.Class} <p>the key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz     {@link java.lang.Class} <p>the value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>the map list return object is <code>Map</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(String json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>the map list method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json       {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>the key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>the value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>the map list return object is <code>Map</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, List<K>> parseMapList(String json, Class<T> keyClazz, Class<K> valueClazz) {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseListMap</code>
     * <p>the list map method.</p>
     * @param <Z>               {@link java.util.List} <p>the generic parameter is <code>List</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>the generic parameter is <code>Map</code> type.</p>
     * @param <T>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json              {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>the wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>the content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>the content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>the content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the list map return object is <code>List</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(String json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>the list map method.</p>
     * @param <T>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json              {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>the content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>the content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>the list map return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T, K> List<Map<T, K>> parseListMap(String json, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>the map map method.</p>
     * @param <H>               {@link java.util.Map} <p>the generic parameter is <code>Map</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>the generic parameter is <code>Map</code> type.</p>
     * @param <Z>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json              {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param wrapMapClazz      {@link java.lang.Class} <p>the wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>the content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>the wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>the content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>the content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>the map map return object is <code>Map</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>the map map method.</p>
     * @param <Z>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json              {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>the wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>the content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>the content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>the map map return object is <code>Map</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }


    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     */
    public static <T> RestResult<T> parseResult(String json, TypeReference<T> typeReference) {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        JavaType parametricType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, javaType);
        try {
            return JsonHelper.parseResult(json, parametricType);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as type reference result of restResult! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json     {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>the java type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     */
    public static <T> RestResult<T> parseResult(String json, JavaType javaType) {
        try {
            return JsonHelper.parseResult(json, javaType);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as java type result of restResult! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     */
    public static <T> RestResult<T> parseResult(String json, Class<T> clazz) {
        try {
            return JsonHelper.parseResult(json, clazz);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as clazz result of restResult! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param json {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestResult
     */
    public static RestResult<String> parseResult(String json) {
        try {
            return JsonHelper.parseResult(json);
        } catch (JsonParseBeanException exception) {
            log.error("It is failed during json to parse as result of restResult! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>the convert method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link T} <p>the convert return object is <code>T</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     */
    public static <T> T parseConvert(Object value, Class<T> clazz) {
        try {
            return JsonHelper.parseConvert(value, clazz);
        } catch (ClassUnsupportedException exception) {
            log.error("It is failed during object to convert as clazz of bean! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }




}
