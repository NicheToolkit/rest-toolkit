package io.github.nichetoolkit.rest.helper;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <code>JsonHelper</code>
 * <p>The type json helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class JsonHelper {

    /**
     * <code>parseJson</code>
     * <p>the json method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param target T <p>the target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>the json return object is <code>String</code> type.</p>
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>the json parse exception is <code>JsonParseException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.JsonParseException
     */
    public static <T> String parseJson(T target) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJson</code>
     * <p>the json method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param target        T <p>the target parameter is <code>T</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.lang.String} <p>the json return object is <code>String</code> type.</p>
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>the json parse exception is <code>JsonParseException</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.JsonParseException
     */
    public static <T> String parseJson(T target, TypeReference<?> typeReference) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().writerFor(typeReference).writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJsonIgnoreNull</code>
     * <p>the json ignore null method.</p>
     * @param <T>    {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param target T <p>the target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>the json ignore null return object is <code>String</code> type.</p>
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>the json parse exception is <code>JsonParseException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.JsonParseException
     */
    public static <T> String parseJsonIgnoreNull(T target) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJsonIgnoreNull", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>the bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static <T> T parseBean(String json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, clazz);
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>the bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static <T> T parseBean(String json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>the bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json     {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>the java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static <T> T parseBean(String json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, javaType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
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
     * @return T <p>the bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static <T, U> T parseBean(String json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, javaType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>the array method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json      {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>the array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>the array return object is <code>T</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.ArrayType
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <T> T[] parseArray(String json, ArrayType arrayType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, arrayType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseListException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }

    }

    /**
     * <code>parseArray</code>
     * <p>the array method.</p>
     * @param <Z>   {@link java.util.List} <p>the generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the array return object is <code>T</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <Z extends List<?>, T> T[] parseArray(String json, Class<T> clazz) throws JsonParseListException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }


    /**
     * <code>parseArray</code>
     * <p>the array method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>the array return object is <code>T</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <T> T[] parseArray(String json, TypeReference<T> typeReference) throws JsonParseListException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseList</code>
     * <p>the list method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json     {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>the list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <T> List<T> parseList(String json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, listType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(),json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>the list method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>the list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <T> List<T> parseList(String json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
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
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <Z extends List<?>, T> List<T> parseList(String json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
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
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>the set method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json    {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>the set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>the set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>the json parse set exception is <code>JsonParseSetException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.json.JsonParseSetException
     */
    public static <T> Set<T> parseSet(String json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, setType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseSetException("parseSet",setType.getRawClass().getName(),json,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>the set method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>the set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>the json parse set exception is <code>JsonParseSetException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.json.JsonParseSetException
     */
    public static <T> Set<T> parseSet(String json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseSetException("parseSet",typeReference.getType().getTypeName(),json,  exception.getMessage());
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
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>the json parse set exception is <code>JsonParseSetException</code> type.</p>
     * @see java.util.Set
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseSetException
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(String json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
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
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>the json parse set exception is <code>JsonParseSetException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.json.JsonParseSetException
     */
    public static <T> Set<T> parseSet(String json, Class<T> clazz) throws JsonParseSetException {
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, mapType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(),json, exception.getMessage());
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(),json, exception.getMessage());
        }
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(String json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(String json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
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
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(String json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
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
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>the json parse list exception is <code>JsonParseListException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.json.JsonParseListException
     */
    public static <T, K> List<Map<T, K>> parseListMap(String json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <T, K> Map<T, List<K>> parseMapList(String json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
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
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>the json parse map exception is <code>JsonParseMapException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.json.JsonParseMapException
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param <T>           {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json          {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>the type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static <T> RestResult<T> parseResult(String json, TypeReference<RestResult<T>> typeReference) throws JsonParseBeanException {
        return parseBean(json, typeReference);
    }


    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json  {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static <T> RestResult<T> parseResult(String json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }


    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param json      {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @param innerType {@link com.fasterxml.jackson.databind.JavaType} <p>the inner type parameter is <code>JavaType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static <T> RestResult<T> parseResult(String json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>the result method.</p>
     * @param json {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>the result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>the json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestResult
     * @see io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     */
    public static RestResult<String> parseResult(String json) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.objectMapper().readTree(json);
            if (GeneralUtils.isNotEmpty(jsonNode)) {
                RestResult<String> restResult = new RestResult<>();
                JsonNode status = jsonNode.get(RestResult.STATUS_NAME);
                if (GeneralUtils.isNotEmpty(status)) {
                    restResult.setStatus(status.asInt());
                }
                JsonNode message = jsonNode.get(RestResult.MESSAGE_NAME);
                if (GeneralUtils.isNotEmpty(message)) {
                    restResult.setMessage(message.toString());
                }
                JsonNode data = jsonNode.get(RestResult.DATA_NAME);
                if (GeneralUtils.isNotEmpty(data)) {
                    restResult.setData(data.toString());
                }
                return restResult;
            }
            return null;
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>the convert method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the convert return object is <code>T</code> type.</p>
     * @throws ClassUnsupportedException {@link io.github.nichetoolkit.rest.error.ClassUnsupportedException} <p>the class unsupported exception is <code>ClassUnsupportedException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.ClassUnsupportedException
     */
    public static <T> T parseConvert(Object value, Class<T> clazz) throws ClassUnsupportedException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            if (value.getClass().equals(clazz)) {
                return ObjectMapperHolder.objectMapper().convertValue(value, clazz);
            } else {
                throw new ClassUnsupportedException("parseConvert",clazz.getName(),value.getClass().getName(), clazz.getName());
            }
        } catch (Exception exception) {
            throw new ClassUnsupportedException(value.getClass().getName(), clazz.getName(), exception.getMessage());
        }
    }

}
