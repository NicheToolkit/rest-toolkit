package io.github.nichetoolkit.rest.helper;


import io.github.nichetoolkit.rest.error.supply.XmlParseException;
import io.github.nichetoolkit.rest.error.xml.*;
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import io.github.nichetoolkit.rest.holder.XmlMapperHolder;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.type.ArrayType;
import tools.jackson.databind.type.CollectionType;
import tools.jackson.databind.type.MapType;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <code>JackxmlHelper</code>
 * <p>The jackxml helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk17
 */
@Slf4j
public class JackxmlHelper {

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param file   {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> void parseXml(T target, File file) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            XmlMapperHolder.jackxmlMapper().writeValue(file,target);
        } catch (JacksonException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>          {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target       T <p>The target parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> void parseXml(T target, OutputStream outputStream) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            XmlMapperHolder.jackxmlMapper().writeValue(outputStream,target);
        } catch (JacksonException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param writer {@link java.io.Writer} <p>The writer parameter is <code>Writer</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see java.io.Writer
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> void parseXml(T target, Writer writer) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return;
        }
        try {
            XmlMapperHolder.jackxmlMapper().writeValue(writer,target);
        } catch (JacksonException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>The parse xml return object is <code>String</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> String parseXml(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().writeValueAsString(target);
        } catch (JacksonException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseXmlAsBytes</code>
     * <p>The parse xml as bytes method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return byte <p>The parse xml as bytes return object is <code>byte</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> byte[] parseXmlAsBytes(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().writeValueAsBytes(target);
        } catch (JacksonException exception) {
            throw new XmlParseException("parseXmlAsBytes", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target        T <p>The target parameter is <code>T</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.lang.String} <p>The parse xml return object is <code>String</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see tools.jackson.core.type.TypeReference
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> String parseXml(T target, TypeReference<?> typeReference) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().writerFor(typeReference).writeValueAsString(target);
        } catch (JacksonException exception) {
            throw new XmlParseException("parseXml", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseXmlIgnoreNull</code>
     * <p>The parse xml ignore null method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>The parse xml ignore null return object is <code>String</code> type.</p>
     * @throws XmlParseException {@link io.github.nichetoolkit.rest.error.supply.XmlParseException} <p>The xml parse exception is <code>XmlParseException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.XmlParseException
     */
    public static <T> String parseXmlIgnoreNull(T target) throws XmlParseException {
        if (GeneralUtils.isEmpty(target)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().writeValueAsString(target);
        } catch (JacksonException exception) {
            throw new XmlParseException("parseXmlIgnoreNull", target.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(File xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, clazz);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(Reader xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, clazz);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(byte[] xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, clazz);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(InputStream xml, Class<T> clazz) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, clazz);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
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
            return XmlMapperHolder.jackxmlMapper().readValue(xml, clazz);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", clazz.getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(File xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(Reader xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(byte[] xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(InputStream xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(String xml, TypeReference<T> typeReference) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param javaType {@link tools.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(File xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, javaType);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param javaType {@link tools.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(Reader xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, javaType);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param javaType {@link tools.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see tools.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(byte[] xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, javaType);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param javaType {@link tools.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(InputStream xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, javaType);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param javaType {@link tools.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T> T parseBean(String xml, JavaType javaType) throws XmlParseBeanException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, javaType);
        } catch (JacksonException exception) {
            throw new XmlParseBeanException("parseBean", javaType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T, U> T parseBean(File xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = ObjectMapperHolder.typeFactory().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T, U> T parseBean(Reader xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = ObjectMapperHolder.typeFactory().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T, U> T parseBean(byte[] xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = ObjectMapperHolder.typeFactory().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <U>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @param innerClazz {@link java.lang.Class} <p>The inner clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @throws XmlParseBeanException {@link io.github.nichetoolkit.rest.error.xml.XmlParseBeanException} <p>The xml parse bean exception is <code>XmlParseBeanException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseBeanException
     */
    public static <T, U> T parseBean(InputStream xml, Class<T> clazz, Class<U> innerClazz) throws XmlParseBeanException {
        JavaType javaType = ObjectMapperHolder.typeFactory().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
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
        JavaType javaType = ObjectMapperHolder.typeFactory().constructParametricType(clazz, innerClazz);
        return parseBean(xml, javaType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param arrayType {@link tools.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.databind.type.ArrayType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(File xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, arrayType);
        } catch (JacksonException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param arrayType {@link tools.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.databind.type.ArrayType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(Reader xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, arrayType);
        } catch (JacksonException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param arrayType {@link tools.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see tools.jackson.databind.type.ArrayType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(byte[] xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, arrayType);
        } catch (JacksonException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param arrayType {@link tools.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.databind.type.ArrayType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(InputStream xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, arrayType);
        } catch (JacksonException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param arrayType {@link tools.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.databind.type.ArrayType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(String xml, ArrayType arrayType) throws XmlParseArrayException {
        if (GeneralUtils.isEmpty(xml)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, arrayType);
        } catch (JacksonException exception) {
            throw new XmlParseArrayException("parseArray", arrayType.getRawClass().getName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(File xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(Reader xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(byte[] xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(InputStream xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(String xml, Class<T> clazz) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(File xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(ObjectMapperHolder.typeFactory().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(Reader xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(ObjectMapperHolder.typeFactory().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(byte[] xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(ObjectMapperHolder.typeFactory().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(InputStream xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(ObjectMapperHolder.typeFactory().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @throws XmlParseArrayException {@link io.github.nichetoolkit.rest.error.xml.XmlParseArrayException} <p>The xml parse array exception is <code>XmlParseArrayException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseArrayException
     */
    public static <T> T[] parseArray(String xml, TypeReference<T> typeReference) throws XmlParseArrayException {
        ArrayType arrayType = ObjectMapperHolder.typeFactory().constructArrayType(ObjectMapperHolder.typeFactory().constructType(typeReference));
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param listType {@link tools.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(File xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, listType);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param listType {@link tools.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(Reader xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, listType);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param listType {@link tools.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(byte[] xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, listType);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param listType {@link tools.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(InputStream xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, listType);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param listType {@link tools.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(String xml, CollectionType listType) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, listType);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", listType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(File xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(Reader xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(byte[] xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(InputStream xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(String xml, TypeReference<List<T>> typeReference) throws XmlParseListException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyList();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseListException("parseList", typeReference.getType().getTypeName(), xml, exception.getMessage());
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>        {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, T> List<T> parseList(File xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>        {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, T> List<T> parseList(Reader xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>        {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, T> List<T> parseList(byte[] xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <Z>        {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, T> List<T> parseList(InputStream xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseListException {
        CollectionType listType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
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
        CollectionType listType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(File xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(Reader xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(byte[] xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T> List<T> parseList(InputStream xml, Class<T> clazz) throws XmlParseListException {
        return parseList(xml, List.class, clazz);
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
     * @param xml     {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param setType {@link tools.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(File xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, setType);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param setType {@link tools.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(Reader xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, setType);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param setType {@link tools.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(byte[] xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, setType);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param setType {@link tools.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(InputStream xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, setType);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param setType {@link tools.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.databind.type.CollectionType
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(String xml, CollectionType setType) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, setType);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",setType.getRawClass().getName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(File xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml) || !xml.exists()) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(Reader xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(byte[] xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(InputStream xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(String xml, TypeReference<Set<T>> typeReference) throws XmlParseSetException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptySet();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseSetException("parseSet",typeReference.getType().getTypeName(),xml,  exception.getMessage());
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>        {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.util.Set
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(File xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>        {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.util.Set
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(Reader xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>        {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.util.Set
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(byte[] xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <Z>        {@link java.util.Set} <p>The generic parameter is <code>Set</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param clazz      {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.util.Set
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(InputStream xml, Class<Z> parseClazz, Class<T> clazz) throws XmlParseSetException {
        CollectionType setType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
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
        CollectionType setType = ObjectMapperHolder.typeFactory().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(File xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(Reader xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(byte[] xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @throws XmlParseSetException {@link io.github.nichetoolkit.rest.error.xml.XmlParseSetException} <p>The xml parse set exception is <code>XmlParseSetException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Set
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseSetException
     */
    public static <T> Set<T> parseSet(InputStream xml, Class<T> clazz) throws XmlParseSetException {
        return parseSet(xml, Set.class, clazz);
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
     * @param xml     {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param mapType {@link tools.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.databind.type.MapType
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(File xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, mapType);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param mapType {@link tools.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.databind.type.MapType
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(Reader xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, mapType);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param mapType {@link tools.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see tools.jackson.databind.type.MapType
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(byte[] xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, mapType);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param mapType {@link tools.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.databind.type.MapType
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(InputStream xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, mapType);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param mapType {@link tools.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.databind.type.MapType
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String xml, MapType mapType) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, mapType);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", mapType.getRawClass().getName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.File
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(File xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.Reader
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(Reader xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(byte[] xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.InputStream
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(InputStream xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.String
     * @see tools.jackson.core.type.TypeReference
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(String xml, TypeReference<Map<T, K>> typeReference) throws XmlParseMapException {
        if (GeneralUtils.isEmpty(xml)) {
            return Collections.emptyMap();
        }
        try {
            return XmlMapperHolder.jackxmlMapper().readValue(xml, typeReference);
        } catch (JacksonException exception) {
            throw new XmlParseMapException("parseMap", typeReference.getType().getTypeName(),xml, exception.getMessage());
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>        {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(File xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>        {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(Reader xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>        {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(byte[] xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <Z>        {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param parseClazz {@link java.lang.Class} <p>The parse clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z extends Map<?,?>, T, K> Map<T, K> parseMap(InputStream xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
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
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseClazz, keyClazz, valueClazz);
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(File xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(Reader xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(byte[] xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, K> parseMap(InputStream xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMap(xml, Map.class, keyClazz, valueClazz);
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
     * @param xml            {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz  {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz       {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz     {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(File xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>            {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>            {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml            {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz  {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz       {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz     {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(Reader xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>            {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>            {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml            byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz  {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz       {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz     {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(byte[] xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <H>            {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>            {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml            {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param parseListClazz {@link java.lang.Class} <p>The parse list clazz parameter is <code>Class</code> type.</p>
     * @param parseMapClazz  {@link java.lang.Class} <p>The parse map clazz parameter is <code>Class</code> type.</p>
     * @param keyClazz       {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz     {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends List<?>, Y extends Map<?,?>, T, K> Map<T, List<K>> parseMapList(InputStream xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
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
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>               {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(File xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>               {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(Reader xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>               {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(byte[] xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <Z>               {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <Z extends List<?>, Y extends Map<?,?>, T, K> List<Map<T, K>> parseListMap(InputStream xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
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
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T, K> List<Map<T, K>> parseListMap(File xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T, K> List<Map<T, K>> parseListMap(Reader xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T, K> List<Map<T, K>> parseListMap(byte[] xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseListMap</code>
     * <p>The parse list map method.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list map return object is <code>List</code> type.</p>
     * @throws XmlParseListException {@link io.github.nichetoolkit.rest.error.xml.XmlParseListException} <p>The xml parse list exception is <code>XmlParseListException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.List
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseListException
     */
    public static <T, K> List<Map<T, K>> parseListMap(InputStream xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseListException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(List.class, contentType.getRawClass());
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
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = ObjectMapperHolder.typeFactory().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, List<K>> parseMapList(File xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, List<K>> parseMapList(Reader xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, List<K>> parseMapList(byte[] xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
    }

    /**
     * <code>parseMapList</code>
     * <p>The parse map list method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml        {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param keyClazz   {@link java.lang.Class} <p>The key clazz parameter is <code>Class</code> type.</p>
     * @param valueClazz {@link java.lang.Class} <p>The value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map list return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <T, K> Map<T, List<K>> parseMapList(InputStream xml, Class<T> keyClazz, Class<K> valueClazz) throws XmlParseMapException {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
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
     * @param xml               {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param wrapMapClazz      {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(File xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param wrapMapClazz      {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param wrapMapClazz      {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <H>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Y>               {@link java.util.Map} <p>The generic parameter is <code>Map</code> type.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param wrapMapClazz      {@link java.lang.Class} <p>The wrap map clazz parameter is <code>Class</code> type.</p>
     * @param contentMapClazz   {@link java.lang.Class} <p>The content map clazz parameter is <code>Class</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <H extends Map<?,?>, Y extends Map<?,?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
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
        MapType contentType = ObjectMapperHolder.typeFactory().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = ObjectMapperHolder.typeFactory().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
        return parseMap(xml, mapType);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(File xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseMapMap</code>
     * <p>The parse map map method.</p>
     * @param <Z>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>               {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml               {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @throws XmlParseMapException {@link io.github.nichetoolkit.rest.error.xml.XmlParseMapException} <p>The xml parse map exception is <code>XmlParseMapException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Map
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseMapException
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) throws XmlParseMapException {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
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

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @throws XmlParseConvertException {@link io.github.nichetoolkit.rest.error.xml.XmlParseConvertException} <p>The xml parse convert exception is <code>XmlParseConvertException</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseConvertException
     */
    public static <T> T parseConvert(Object value, Class<T> clazz) throws XmlParseConvertException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().convertValue(value, clazz);
        } catch (IllegalArgumentException exception) {
            throw new XmlParseConvertException("parseConvert", clazz.getName(), value.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value         {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param typeReference {@link tools.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @throws XmlParseConvertException {@link io.github.nichetoolkit.rest.error.xml.XmlParseConvertException} <p>The xml parse convert exception is <code>XmlParseConvertException</code> type.</p>
     * @see java.lang.Object
     * @see tools.jackson.core.type.TypeReference
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseConvertException
     */
    public static <T> T parseConvert(Object value, TypeReference<T> typeReference) throws XmlParseConvertException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().convertValue(value, typeReference);
        } catch (IllegalArgumentException exception) {
            throw new XmlParseConvertException("parseConvert", typeReference.getType().getTypeName(), value.getClass().getName(), exception.getMessage());
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param javaType {@link tools.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @throws XmlParseConvertException {@link io.github.nichetoolkit.rest.error.xml.XmlParseConvertException} <p>The xml parse convert exception is <code>XmlParseConvertException</code> type.</p>
     * @see java.lang.Object
     * @see tools.jackson.databind.JavaType
     * @see io.github.nichetoolkit.rest.error.xml.XmlParseConvertException
     */
    public static <T> T parseConvert(Object value, JavaType javaType) throws XmlParseConvertException {
        if (GeneralUtils.isEmpty(value)) {
            return null;
        }
        try {
            return XmlMapperHolder.jackxmlMapper().convertValue(value, javaType);
        } catch (IllegalArgumentException exception) {
            throw new XmlParseConvertException("parseConvert", javaType.getRawClass().getName(), value.getClass().getName(), exception.getMessage());
        }
    }

}
