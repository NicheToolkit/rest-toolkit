package io.github.nichetoolkit.rest.helper;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.nichetoolkit.rest.error.xml.XmlParseBeanException;
import io.github.nichetoolkit.rest.error.xml.XmlParseListException;
import io.github.nichetoolkit.rest.error.xml.XmlParseMapException;
import io.github.nichetoolkit.rest.error.xml.XmlParseSetException;
import io.github.nichetoolkit.rest.error.supply.XmlParseException;
import io.github.nichetoolkit.rest.holder.XmlMapperHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <code>XmlHelper</code>
 * <p>The xml helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class XmlHelper {

    /**
     * <code>parseJson</code>
     * <p>The parse json method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>The parse json return object is <code>String</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> String parseJson(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new XmlParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJson</code>
     * <p>The parse json method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target        T <p>The target parameter is <code>T</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.lang.String} <p>The parse json return object is <code>String</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> String parseJson(T target, TypeReference<?> typeReference) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().writerFor(typeReference).writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new XmlParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJsonIgnoreNull</code>
     * <p>The parse json ignore null method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>The parse json ignore null return object is <code>String</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> String parseJsonIgnoreNull(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new XmlParseException("parseJsonIgnoreNull", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(String xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, clazz);
        } catch (JsonProcessingException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(String xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (JsonProcessingException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(String xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, javaType);
        } catch (JsonProcessingException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T, U> T parseBean(String xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, javaType);
        } catch (JsonProcessingException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.ArrayType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> T[] parseArray(String xml, ArrayType arrayType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, arrayType);
        } catch (JsonProcessingException exception) {
            throw new XmlParseListException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }

    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <Z>   {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, T> T[] parseArray(String xml, Class<T> clazz) throws XmlParseListException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }


    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> T[] parseArray(String xml, TypeReference<T> typeReference) throws XmlParseListException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(String xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, listType);
        } catch (JsonProcessingException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(String xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (JsonProcessingException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>        {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, T> List<T> parseList(String xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(String xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(String xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, setType);
        } catch (JsonProcessingException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(String xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (JsonProcessingException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>        {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.util.Set
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(String xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(String xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, mapType);
        } catch (JsonProcessingException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (JsonProcessingException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>        {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(String xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }


    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>            {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>            {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml            {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz  {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz       {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz     {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(String xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>               {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(String xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T, K> List<Map<T, K>> parseListMap(String xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, List<K>> parseMapList(String xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param wrapMapClazz      {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

}
