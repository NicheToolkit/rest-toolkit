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
import io.github.nichetoolkit.rest.error.xml.*;
import io.github.nichetoolkit.rest.error.supply.XmlParseException;
import io.github.nichetoolkit.rest.holder.XmlMapperHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class XmlHelper {

    public static <T> void parseXml(T target, File file) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            XmlMapperHolder.xmlMapper().writeValue(file,target);
        } catch (IOException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> void parseXml(T target, OutputStream outputStream) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            XmlMapperHolder.xmlMapper().writeValue(outputStream,target);
        } catch (IOException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> void parseXml(T target, Writer writer) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            XmlMapperHolder.xmlMapper().writeValue(writer,target);
        } catch (IOException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> String parseXml(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> byte[] parseXmlAsBytes(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().writeValueAsBytes(target);
        } catch (JsonProcessingException exception) {
            throw new XmlParseException("parseXmlAsBytes", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> String parseXml(T target, TypeReference<?> typeReference) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().writerFor(typeReference).writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> String parseXmlIgnoreNull(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException exception) {
            throw new XmlParseException("parseXmlIgnoreNull", target.getClass().getName(), exception.getMessage());
        }
    }

    public static <T> T parseBean(File xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, clazz);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(Reader xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, clazz);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(byte[] xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, clazz);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(InputStream xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, clazz);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
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

    public static <T> T parseBean(File xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(Reader xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(byte[] xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(InputStream xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
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

    public static <T> T parseBean(File xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, javaType);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(Reader xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, javaType);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(byte[] xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, javaType);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T parseBean(InputStream xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, javaType);
        } catch (IOException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
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

    public static <T, U> T parseBean(File xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    public static <T, U> T parseBean(Reader xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    public static <T, U> T parseBean(byte[] xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    public static <T, U> T parseBean(InputStream xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    public static <T, U> T parseBean(String xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    public static <T> T[] parseArray(File xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, arrayType);
        } catch (IOException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(Reader xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, arrayType);
        } catch (IOException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(byte[] xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, arrayType);
        } catch (IOException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(InputStream xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, arrayType);
        } catch (IOException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(String xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, arrayType);
        } catch (JsonProcessingException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    public static <T> T[] parseArray(File xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(Reader xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(byte[] xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(InputStream xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(String xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(File xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(Reader xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(byte[] xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(InputStream xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    public static <T> T[] parseArray(String xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    public static <T> List<T> parseList(File xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, listType);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(Reader xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, listType);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(byte[] xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, listType);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(InputStream xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, listType);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
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

    public static <T> List<T> parseList(File xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(Reader xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(byte[] xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    public static <T> List<T> parseList(InputStream xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
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

    public static <Z extends List<?>, T> List<T> parseList(File xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(Reader xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(byte[] xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(InputStream xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    public static <Z extends List<?>, T> List<T> parseList(String xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    public static <T> List<T> parseList(File xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    public static <T> List<T> parseList(Reader xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    public static <T> List<T> parseList(byte[] xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    public static <T> List<T> parseList(InputStream xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    public static <T> List<T> parseList(String xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    public static <T> Set<T> parseSet(File xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, setType);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(Reader xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, setType);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(byte[] xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, setType);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(InputStream xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, setType);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
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

    public static <T> Set<T> parseSet(File xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(Reader xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(byte[] xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    public static <T> Set<T> parseSet(InputStream xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
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

    public static <Z extends Set<?>, T> Set<T> parseSet(File xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(Reader xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(byte[] xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(InputStream xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    public static <Z extends Set<?>, T> Set<T> parseSet(String xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    public static <T> Set<T> parseSet(File xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(Reader xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(byte[] xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(InputStream xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    public static <T> Set<T> parseSet(String xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    public static <T, K> Map<T, K> parseMap(File xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, mapType);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(Reader xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, mapType);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(byte[] xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, mapType);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(InputStream xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, mapType);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
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

    public static <T, K> Map<T, K> parseMap(File xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(Reader xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(byte[] xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    public static <T, K> Map<T, K> parseMap(InputStream xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.xmlMapper().readValue(xml, typeReference);
        } catch (IOException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
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

    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(File xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(Reader xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(byte[] xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(InputStream xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(String xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    public static <T, K> Map<T, K> parseMap(File xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(Reader xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(byte[] xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(InputStream xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, K> parseMap(String xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(File xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(Reader xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(byte[] xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(InputStream xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(String xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(File xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(Reader xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(byte[] xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(InputStream xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(String xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(File xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(Reader xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(byte[] xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(InputStream xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> List<Map<T, K>> parseListMap(String xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    public static <T, K> Map<T, List<K>> parseMapList(File xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(Reader xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(byte[] xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(InputStream xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <T, K> Map<T, List<K>> parseMapList(String xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(File xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(File xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

}
