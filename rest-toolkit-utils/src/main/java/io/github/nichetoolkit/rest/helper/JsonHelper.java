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
import io.github.nichetoolkit.rest.error.json.*;
import io.github.nichetoolkit.rest.error.supply.JsonParseException;
import io.github.nichetoolkit.rest.error.supply.XmlParseException;
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import io.github.nichetoolkit.rest.holder.XmlMapperHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class JsonHelper {

    public static <T> void parseJson(T target, File file) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            ObjectMapperHolder.objectMapper().writeValue(file, target);
        } catch (IOException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> void parseJson(T target, OutputStream outputStream) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            ObjectMapperHolder.objectMapper().writeValue(outputStream, target);
        } catch (IOException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> void parseJson(T target, Writer writer) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            ObjectMapperHolder.objectMapper().writeValue(writer, target);
        } catch (IOException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

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

    public static <T> byte[] parseJsonAsBytes(T target) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().writeValueAsBytes(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJsonAsBytes", target.getClass().getName(), exception.getMessage());
        }
    }

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

    public static <T> T parseBean(File json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(Reader json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(byte[] json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(InputStream json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

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

    public static <T> T parseBean(File json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(Reader json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(byte[] json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(InputStream json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

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

    public static <T> T parseBean(File json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(Reader json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(byte[] json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T parseBean(InputStream json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

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

    public static <T, U> T parseBean(File json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    public static <T, U> T parseBean(Reader json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    public static <T, U> T parseBean(byte[] json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    public static <T, U> T parseBean(InputStream json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    public static <T, U> T parseBean(String json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    public static <T> T[] parseArray(File json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(Reader json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(byte[] json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(InputStream json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(String json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, arrayType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(File json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(Reader json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(byte[] json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(InputStream json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(String json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(File json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(Reader json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(byte[] json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(InputStream json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    public static <T> T[] parseArray(String json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    public static <T> List<T> parseList(File json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(Reader json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(byte[] json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(InputStream json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(String json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, listType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(File json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(Reader json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(byte[] json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(InputStream json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

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

    public static <Z extends List<?>, T> List<T> parseList(File json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(Reader json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(byte[] json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(InputStream json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(String json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    public static <T> List<T> parseList(File json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    public static <T> List<T> parseList(Reader json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    public static <T> List<T> parseList(byte[] json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    public static <T> List<T> parseList(InputStream json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    public static <T> Set<T> parseSet(File json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(Reader json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(byte[] json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(InputStream json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(String json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, setType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(File json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(Reader json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(byte[] json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(InputStream json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(String json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(File json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(Reader json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(byte[] json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(InputStream json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(String json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    public static <T> Set<T> parseSet(File json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(Reader json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(byte[] json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(InputStream json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(String json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    public static <T, K> Map<T, K> parseMap(File json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(Reader json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(byte[] json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(InputStream json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(String json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, mapType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(File json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(Reader json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(byte[] json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(InputStream json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(String json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.objectMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(File json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(Reader json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(byte[] json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(InputStream json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(String json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }


    public static <T, K> Map<T, K> parseMap(File json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(Reader json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(byte[] json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(InputStream json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(String json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(File json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(Reader json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(byte[] json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(InputStream json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(String json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(File json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(Reader json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(byte[] json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(InputStream json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(String json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(File json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(Reader json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(byte[] json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(InputStream json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(String json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    public static <T, K> Map<T, List<K>> parseMapList(File json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(Reader json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(byte[] json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(InputStream json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(String json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(File json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(File json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <T> RestResult<T> parseResult(File json, TypeReference<RestResult<T>> typeReference) throws JsonParseBeanException {
        return parseBean(json, typeReference);
    }

    public static <T> RestResult<T> parseResult(Reader json, TypeReference<RestResult<T>> typeReference) throws JsonParseBeanException {
        return parseBean(json, typeReference);
    }

    public static <T> RestResult<T> parseResult(byte[] json, TypeReference<RestResult<T>> typeReference) throws JsonParseBeanException {
        return parseBean(json, typeReference);
    }

    public static <T> RestResult<T> parseResult(InputStream json, TypeReference<RestResult<T>> typeReference) throws JsonParseBeanException {
        return parseBean(json, typeReference);
    }

    public static <T> RestResult<T> parseResult(String json, TypeReference<RestResult<T>> typeReference) throws JsonParseBeanException {
        return parseBean(json, typeReference);
    }

    public static <T> RestResult<T> parseResult(File json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(Reader json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(byte[] json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(InputStream json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(String json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(File json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(Reader json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(byte[] json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(InputStream json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    public static <T> RestResult<T> parseResult(String json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    public static RestResult<String> parseResult(File json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.objectMapper().readTree(json);
           return parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    public static RestResult<String> parseResult(Reader json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.objectMapper().readTree(json);
            return parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    public static RestResult<String> parseResult(byte[] json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.objectMapper().readTree(json);
            return parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    public static RestResult<String> parseResult(InputStream json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.objectMapper().readTree(json);
            return parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    public static RestResult<String> parseResult(String json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.objectMapper().readTree(json);
            return parseResult(jsonNode);
        } catch (JsonProcessingException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    public static RestResult<String> parseResult(JsonNode jsonNode) {
        if (GeneralUtils.isEmpty(jsonNode)) {
            return null;
        }
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

    public static <T> T parseConvert(Object value, Class<T> clazz) throws ClassUnsupportedException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            if (value.getClass().equals(clazz)) {
                return ObjectMapperHolder.objectMapper().convertValue(value, clazz);
            } else {
                throw new ClassUnsupportedException("parseConvert", clazz.getName(), value.getClass().getName(), clazz.getName());
            }
        } catch (Exception exception) {
            throw new ClassUnsupportedException(value.getClass().getName(), clazz.getName(), exception.getMessage());
        }
    }

}
