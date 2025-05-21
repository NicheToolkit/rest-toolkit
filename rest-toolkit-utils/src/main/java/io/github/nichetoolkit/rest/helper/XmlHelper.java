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

@Slf4j
public class XmlHelper {

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

    public static <Z extends List<?>, T> T[] parseArray(String xml, Class<T> clazz) throws XmlParseListException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }


    public static <T> T[] parseArray(String xml, TypeReference<T> typeReference) throws XmlParseListException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

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

    public static <Z extends List<?>, T> List<T> parseList(String xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    public static <T> List<T> parseList(String xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

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

    public static <Z extends Set<?>, T> Set<T> parseSet(String xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    public static <T> Set<T> parseSet(String xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

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

    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(String xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    public static <T, K> Map<T, K> parseMap(String xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }


    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(String xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(String xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(String xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> Map<T, List<K>> parseMapList(String xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

}
