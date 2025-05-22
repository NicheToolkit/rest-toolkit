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
import io.github.nichetoolkit.rest.error.json.*;
import io.github.nichetoolkit.rest.error.supply.JsonParseException;
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <code>JsonPurityHelper</code>
 * <p>The json purity helper class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class JsonPurityHelper {

    /**
     * <code>parseJson</code>
     * <p>The parse json method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.error.supply.JsonParseException
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>The json parse exception is <code>JsonParseException</code> type.</p>
     */
    public static <T> void parseJson(T target, File file) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            ObjectMapperHolder.purityMapper().writeValue(file, target);
        } catch (IOException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJson</code>
     * <p>The parse json method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see  java.io.OutputStream
     * @see  io.github.nichetoolkit.rest.error.supply.JsonParseException
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>The json parse exception is <code>JsonParseException</code> type.</p>
     */
    public static <T> void parseJson(T target, OutputStream outputStream) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            ObjectMapperHolder.purityMapper().writeValue(outputStream, target);
        } catch (IOException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJson</code>
     * <p>The parse json method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param writer {@link java.io.Writer} <p>The writer parameter is <code>Writer</code> type.</p>
     * @see  java.io.Writer
     * @see  io.github.nichetoolkit.rest.error.supply.JsonParseException
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>The json parse exception is <code>JsonParseException</code> type.</p>
     */
    public static <T> void parseJson(T target, Writer writer) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            ObjectMapperHolder.purityMapper().writeValue(writer, target);
        } catch (IOException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJson</code>
     * <p>The parse json method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return  {@link java.lang.String} <p>The parse json return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.error.supply.JsonParseException
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>The json parse exception is <code>JsonParseException</code> type.</p>
     */
    public static <T> String parseJson(T target) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJsonAsBytes</code>
     * <p>The parse json as bytes method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return byte <p>The parse json as bytes return object is <code>byte</code> type.</p>
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>The json parse exception is <code>JsonParseException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.error.supply.JsonParseException
     */
    public static <T> byte[] parseJsonAsBytes(T target) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().writeValueAsBytes(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJsonAsBytes", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJson</code>
     * <p>The parse json method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.error.supply.JsonParseException
     * @return  {@link java.lang.String} <p>The parse json return object is <code>String</code> type.</p>
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>The json parse exception is <code>JsonParseException</code> type.</p>
     */
    public static <T> String parseJson(T target, TypeReference<?> typeReference) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().writerFor(typeReference).writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJson", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseJsonIgnoreNull</code>
     * <p>The parse json ignore null method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return  {@link java.lang.String} <p>The parse json ignore null return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.error.supply.JsonParseException
     * @throws JsonParseException {@link io.github.nichetoolkit.rest.error.supply.JsonParseException} <p>The json parse exception is <code>JsonParseException</code> type.</p>
     */
    public static <T> String parseJsonIgnoreNull(T target) throws JsonParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            ObjectMapper mapper = ObjectMapperHolder.ofPurityMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new JsonParseException("parseJsonIgnoreNull", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(File json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(Reader json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(byte[] json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(InputStream json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, clazz);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(String json, Class<T> clazz) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, clazz);
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseBean", clazz.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(File json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(Reader json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(byte[] json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(InputStream json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(String json, TypeReference<T> typeReference) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseBean", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(File json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(Reader json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(byte[] json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(InputStream json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, javaType);
        } catch (IOException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> T parseBean(String json, JavaType javaType) throws JsonParseBeanException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, javaType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseBeanException("parseBean", javaType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T, U> T parseBean(File json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T, U> T parseBean(Reader json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T, U> T parseBean(byte[] json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T, U> T parseBean(InputStream json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T, U> T parseBean(String json, Class<T> clazz, Class<U> innerClazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.databind.type.ArrayType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(File json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.databind.type.ArrayType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(Reader json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.type.ArrayType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(byte[] json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.databind.type.ArrayType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(InputStream json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, arrayType);
        } catch (IOException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.databind.type.ArrayType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(String json, ArrayType arrayType) throws JsonParseArrayException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, arrayType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseArrayException("parseArray", arrayType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(File json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(Reader json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(byte[] json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(InputStream json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(String json, Class<T> clazz) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(File json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(Reader json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(byte[] json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(InputStream json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseArrayException
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws JsonParseArrayException {@link io.github.nichetoolkit.rest.error.json.JsonParseArrayException} <p>The json parse array exception is <code>JsonParseArrayException</code> type.</p>
     */
    public static <T> T[] parseArray(String json, TypeReference<T> typeReference) throws JsonParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(json, arrayType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(File json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(Reader json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(byte[] json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(InputStream json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, listType);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(String json, CollectionType listType) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, listType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseListException("parseList", listType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(File json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(Reader json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(byte[] json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(InputStream json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(String json, TypeReference<List<T>> typeReference) throws JsonParseListException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseListException("parseList", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, T> List<T> parseList(File json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, T> List<T> parseList(Reader json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, T> List<T> parseList(byte[] json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, T> List<T> parseList(InputStream json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, T> List<T> parseList(String json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(json, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(File json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(Reader json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(byte[] json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(InputStream json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) throws JsonParseListException {
        return parseList(json, List.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(File json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(Reader json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(byte[] json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(InputStream json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, setType);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.databind.type.CollectionType
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(String json, CollectionType setType) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, setType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseSetException("parseSet", setType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(File json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(Reader json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(byte[] json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(InputStream json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(String json, TypeReference<Set<T>> typeReference) throws JsonParseSetException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptySet();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseSetException("parseSet", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>  {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Set
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(File json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>  {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Set
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(Reader json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>  {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Set
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(byte[] json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>  {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Set
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(InputStream json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>  {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Set
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(String json, Class<Z> parseClazz, Class<T> clazz) throws JsonParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(json, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(File json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(Reader json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(byte[] json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(InputStream json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.util.Set
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseSetException
     * @return  {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws JsonParseSetException {@link io.github.nichetoolkit.rest.error.json.JsonParseSetException} <p>The json parse set exception is <code>JsonParseSetException</code> type.</p>
     */
    public static <T> Set<T> parseSet(String json, Class<T> clazz) throws JsonParseSetException {
        return parseSet(json, Set.class, clazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.databind.type.MapType
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(File json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.databind.type.MapType
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(Reader json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.type.MapType
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(byte[] json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.databind.type.MapType
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(InputStream json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, mapType);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.databind.type.MapType
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(String json, MapType mapType) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, mapType);
        } catch (JsonProcessingException exception) {
            throw new JsonParseMapException("parseMap", mapType.getRawClass().getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(File json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(Reader json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(byte[] json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(InputStream json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (IOException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(String json, TypeReference<Map<T, K>> typeReference) throws JsonParseMapException {
        if (GeneralUtils.isEmpty(json)) {
            return Collections.emptyMap();
        }
        try {
            return ObjectMapperHolder.purityMapper().readValue(json, typeReference);
        } catch (JsonProcessingException exception) {
            throw new JsonParseMapException("parseMap", typeReference.getType().getTypeName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(File json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(Reader json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(byte[] json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(InputStream json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(String json, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(json, mapType);
    }


    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(File json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(Reader json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(byte[] json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(InputStream json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, K> parseMap(String json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMap(json, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(File json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(Reader json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(byte[] json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(InputStream json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(String json, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(File json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(Reader json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(byte[] json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(InputStream json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>  {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.List
     * @see  java.util.Map
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(String json, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T, K> List<Map<T, K>> parseListMap(File json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T, K> List<Map<T, K>> parseListMap(Reader json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T, K> List<Map<T, K>> parseListMap(byte[] json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T, K> List<Map<T, K>> parseListMap(InputStream json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseListException
     * @return  {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws JsonParseListException {@link io.github.nichetoolkit.rest.error.json.JsonParseListException} <p>The json parse list exception is <code>JsonParseListException</code> type.</p>
     */
    public static <T, K> List<Map<T, K>> parseListMap(String json, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(json, collectionType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, List<K>> parseMapList(File json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, List<K>> parseMapList(Reader json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, List<K>> parseMapList(byte[] json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, List<K>> parseMapList(InputStream json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param keyClazz {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <T, K> Map<T, List<K>> parseMapList(String json, Class<T> keyClazz, Class<K> valueClazz) throws JsonParseMapException {
        return parseMapList(json, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param wrapMapClazz {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(File json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param wrapMapClazz {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param wrapMapClazz {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param wrapMapClazz {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>  {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param wrapMapClazz {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.util.Map
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(json, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(File json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param wrapKeyClazz {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseMapException
     * @return  {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws JsonParseMapException {@link io.github.nichetoolkit.rest.error.json.JsonParseMapException} <p>The json parse map exception is <code>JsonParseMapException</code> type.</p>
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String json, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws JsonParseMapException {
        return parseMapMap(json, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(File json, TypeReference<T> typeReference) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return parseResult(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(Reader json, TypeReference<T> typeReference) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return parseResult(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(byte[] json, TypeReference<T> typeReference) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return parseResult(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(InputStream json, TypeReference<T> typeReference) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return parseResult(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(String json, TypeReference<T> typeReference) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructType(typeReference);
        return parseResult(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(File json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.Reader
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(Reader json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(byte[] json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(InputStream json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(String json, Class<T> clazz) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, clazz);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @param innerType {@link com.fasterxml.jackson.databind.JavaType} <p>The inner type parameter is <code>JavaType</code> type.</p>
     * @see  java.io.File
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(File json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @param innerType {@link com.fasterxml.jackson.databind.JavaType} <p>The inner type parameter is <code>JavaType</code> type.</p>
     * @see  java.io.Reader
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(Reader json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @param innerType {@link com.fasterxml.jackson.databind.JavaType} <p>The inner type parameter is <code>JavaType</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(byte[] json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @param innerType {@link com.fasterxml.jackson.databind.JavaType} <p>The inner type parameter is <code>JavaType</code> type.</p>
     * @see  java.io.InputStream
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(InputStream json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param innerType {@link com.fasterxml.jackson.databind.JavaType} <p>The inner type parameter is <code>JavaType</code> type.</p>
     * @see  java.lang.String
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseBeanException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseBeanException {@link io.github.nichetoolkit.rest.error.json.JsonParseBeanException} <p>The json parse bean exception is <code>JsonParseBeanException</code> type.</p>
     */
    public static <T> RestResult<T> parseResult(String json, JavaType innerType) throws JsonParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(RestResult.class, innerType);
        return parseBean(json, javaType);
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param json {@link java.io.File} <p>The json parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseResultException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseResultException {@link io.github.nichetoolkit.rest.error.json.JsonParseResultException} <p>The json parse result exception is <code>JsonParseResultException</code> type.</p>
     */
    public static RestResult<String> parseResult(File json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json) || !json.exists()) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.purityMapper().readTree(json);
           return JsonHelper.parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param json {@link java.io.Reader} <p>The json parameter is <code>Reader</code> type.</p>
     * @see  java.io.Reader
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseResultException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseResultException {@link io.github.nichetoolkit.rest.error.json.JsonParseResultException} <p>The json parse result exception is <code>JsonParseResultException</code> type.</p>
     */
    public static RestResult<String> parseResult(Reader json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.purityMapper().readTree(json);
            return JsonHelper.parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param json byte <p>The json parameter is <code>byte</code> type.</p>
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseResultException
     * @throws JsonParseResultException {@link io.github.nichetoolkit.rest.error.json.JsonParseResultException} <p>The json parse result exception is <code>JsonParseResultException</code> type.</p>
     */
    public static RestResult<String> parseResult(byte[] json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.purityMapper().readTree(json);
            return JsonHelper.parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param json {@link java.io.InputStream} <p>The json parameter is <code>InputStream</code> type.</p>
     * @see  java.io.InputStream
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseResultException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseResultException {@link io.github.nichetoolkit.rest.error.json.JsonParseResultException} <p>The json parse result exception is <code>JsonParseResultException</code> type.</p>
     */
    public static RestResult<String> parseResult(InputStream json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.purityMapper().readTree(json);
            return JsonHelper.parseResult(jsonNode);
        } catch (IOException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseResult</code>
     * <p>The parse result method.</p>
     * @param json {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseResultException
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The parse result return object is <code>RestResult</code> type.</p>
     * @throws JsonParseResultException {@link io.github.nichetoolkit.rest.error.json.JsonParseResultException} <p>The json parse result exception is <code>JsonParseResultException</code> type.</p>
     */
    public static RestResult<String> parseResult(String json) throws JsonParseResultException {
        if (GeneralUtils.isEmpty(json)) {
            return null;
        }
        try {
            JsonNode jsonNode = ObjectMapperHolder.purityMapper().readTree(json);
            return JsonHelper.parseResult(jsonNode);
        } catch (JsonProcessingException exception) {
            throw new JsonParseResultException("parseResult", RestResult.class.getName(), json, exception.getMessage());
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Object
     * @see  java.lang.Class
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseConvertException
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @throws JsonParseConvertException {@link io.github.nichetoolkit.rest.error.json.JsonParseConvertException} <p>The json parse convert exception is <code>JsonParseConvertException</code> type.</p>
     */
    public static <T> T parseConvert(Object value, Class<T> clazz) throws JsonParseConvertException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().convertValue(value, clazz);
        } catch (IllegalArgumentException exception) {
            throw new JsonParseConvertException("parseConvert", clazz.getName(), value.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @see  java.lang.Object
     * @see  com.fasterxml.jackson.core.type.TypeReference
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseConvertException
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @throws JsonParseConvertException {@link io.github.nichetoolkit.rest.error.json.JsonParseConvertException} <p>The json parse convert exception is <code>JsonParseConvertException</code> type.</p>
     */
    public static <T> T parseConvert(Object value, TypeReference<T> typeReference) throws JsonParseConvertException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().convertValue(value, typeReference);
        } catch (IllegalArgumentException exception) {
            throw new JsonParseConvertException("parseConvert", typeReference.getType().getTypeName(), value.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @see  java.lang.Object
     * @see  com.fasterxml.jackson.databind.JavaType
     * @see  io.github.nichetoolkit.rest.error.json.JsonParseConvertException
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @throws JsonParseConvertException {@link io.github.nichetoolkit.rest.error.json.JsonParseConvertException} <p>The json parse convert exception is <code>JsonParseConvertException</code> type.</p>
     */
    public static <T> T parseConvert(Object value, JavaType javaType) throws JsonParseConvertException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            return ObjectMapperHolder.purityMapper().convertValue(value, javaType);
        } catch (IllegalArgumentException exception) {
            throw new JsonParseConvertException("parseConvert", javaType.getRawClass().getName(), value.getClass().getName(), exception.getMessage());
        }
    }

}
