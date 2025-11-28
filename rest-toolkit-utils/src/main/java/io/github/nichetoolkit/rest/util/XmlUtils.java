package io.github.nichetoolkit.rest.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.nichetoolkit.rest.error.xml.*;
import io.github.nichetoolkit.rest.error.supply.XmlParseException;
import io.github.nichetoolkit.rest.helper.XmlHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <code>XmlUtils</code>
 * <p>The xml utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@Slf4j
@SuppressWarnings({"TypeParameterUnusedInFormals", "SameNameButDifferent"})
public class XmlUtils {


    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param file   {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see java.io.File
     */
    public static <T> void parseXml(T target, File file) {
        try {
            XmlHelper.parseXml(target, file);
        } catch (XmlParseException exception) {
            log.error("It is failed during bean to parse as xml with file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>          {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target       T <p>The target parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.io.OutputStream
     */
    public static <T> void parseXml(T target, OutputStream outputStream) {
        try {
            XmlHelper.parseXml(target, outputStream);
        } catch (XmlParseException exception) {
            log.error("It is failed during bean to parse as xml with output stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @param writer {@link java.io.Writer} <p>The writer parameter is <code>Writer</code> type.</p>
     * @see java.io.Writer
     */
    public static <T> void parseXml(T target, Writer writer) {
        try {
            XmlHelper.parseXml(target, writer);
        } catch (XmlParseException exception) {
            log.error("It is failed during bean to parse as xml with writer! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>The parse xml return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static <T> String parseXml(T target) {
        try {
            return XmlHelper.parseXml(target);
        } catch (XmlParseException exception) {
            log.error("It is failed during bean to parse as xml! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseXmlAsBytes</code>
     * <p>The parse xml as bytes method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return byte <p>The parse xml as bytes return object is <code>byte</code> type.</p>
     */
    public static <T> byte[] parseXmlAsBytes(T target) {
        try {
            return XmlHelper.parseXmlAsBytes(target);
        } catch (XmlParseException exception) {
            log.error("It is failed during bean to parse as xml bytes! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseXml</code>
     * <p>The parse xml method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target        T <p>The target parameter is <code>T</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.lang.String} <p>The parse xml return object is <code>String</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.lang.String
     */
    public static <T> String parseXml(T target, TypeReference<?> typeReference) {
        try {
            return XmlHelper.parseXml(target, typeReference);
        } catch (XmlParseException exception) {
            log.error("It is failed during bean to parse as xml with type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseXmlIgnoreNull</code>
     * <p>The parse xml ignore null method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param target T <p>The target parameter is <code>T</code> type.</p>
     * @return {@link java.lang.String} <p>The parse xml ignore null return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static <T> String parseXmlIgnoreNull(T target) {
        try {
            return XmlHelper.parseXmlIgnoreNull(target);
        } catch (XmlParseException exception) {
            log.error("It is failed during bean to parse as xml with ignoring null! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <T> T parseBean(File xml, Class<T> clazz) {
        try {
            return XmlHelper.parseBean(xml, clazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with file and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <T> T parseBean(Reader xml, Class<T> clazz) {
        try {
            return XmlHelper.parseBean(xml, clazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with reader and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.lang.Class
     */
    public static <T> T parseBean(byte[] xml, Class<T> clazz) {
        try {
            return XmlHelper.parseBean(xml, clazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with bytes and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <T> T parseBean(InputStream xml, Class<T> clazz) {
        try {
            return XmlHelper.parseBean(xml, clazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with input stream and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <T> T parseBean(String xml, Class<T> clazz) {
        try {
            return XmlHelper.parseBean(xml, clazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T parseBean(File xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseBean(xml, typeReference);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with file and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T parseBean(Reader xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseBean(xml, typeReference);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with reader and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T parseBean(byte[] xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseBean(xml, typeReference);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with bytes and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T parseBean(InputStream xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseBean(xml, typeReference);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with input stream and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T parseBean(String xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseBean(xml, typeReference);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.databind.JavaType
     */
    public static <T> T parseBean(File xml, JavaType javaType) {
        try {
            return XmlHelper.parseBean(xml, javaType);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with file and java type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.databind.JavaType
     */
    public static <T> T parseBean(Reader xml, JavaType javaType) {
        try {
            return XmlHelper.parseBean(xml, javaType);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with reader and java type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see com.fasterxml.jackson.databind.JavaType
     */
    public static <T> T parseBean(byte[] xml, JavaType javaType) {
        try {
            return XmlHelper.parseBean(xml, javaType);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with bytes and java type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.databind.JavaType
     */
    public static <T> T parseBean(InputStream xml, JavaType javaType) {
        try {
            return XmlHelper.parseBean(xml, javaType);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with input stream and java type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseBean</code>
     * <p>The parse bean method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse bean return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.JavaType
     */
    public static <T> T parseBean(String xml, JavaType javaType) {
        try {
            return XmlHelper.parseBean(xml, javaType);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as bean with java type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
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
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <T, U> T parseBean(File xml, Class<T> clazz, Class<U> innerClazz) {
        try {
            return XmlHelper.parseBean(xml, clazz, innerClazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as inner type bean with file and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
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
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <T, U> T parseBean(Reader xml, Class<T> clazz, Class<U> innerClazz) {
        try {
            return XmlHelper.parseBean(xml, clazz, innerClazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as inner type bean with reader and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
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
     * @see java.lang.Class
     */
    public static <T, U> T parseBean(byte[] xml, Class<T> clazz, Class<U> innerClazz) {
        try {
            return XmlHelper.parseBean(xml, clazz, innerClazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as inner type bean with bytes and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
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
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <T, U> T parseBean(InputStream xml, Class<T> clazz, Class<U> innerClazz) {
        try {
            return XmlHelper.parseBean(xml, clazz, innerClazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as inner type bean with input stream and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
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
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <T, U> T parseBean(String xml, Class<T> clazz, Class<U> innerClazz) {
        try {
            return XmlHelper.parseBean(xml, clazz, innerClazz);
        } catch (XmlParseBeanException exception) {
            log.error("It is failed during xml to parse as inner type bean with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     */
    public static <T> List<T> parseList(File xml, CollectionType listType) {
        try {
            return XmlHelper.parseList(xml, listType);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with file and collection type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     */
    public static <T> List<T> parseList(Reader xml, CollectionType listType) {
        try {
            return XmlHelper.parseList(xml, listType);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with reader and collection type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     */
    public static <T> List<T> parseList(byte[] xml, CollectionType listType) {
        try {
            return XmlHelper.parseList(xml, listType);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with bytes and collection type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     */
    public static <T> List<T> parseList(InputStream xml, CollectionType listType) {
        try {
            return XmlHelper.parseList(xml, listType);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with input stream and collection type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml      {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param listType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The list type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.List
     */
    public static <T> List<T> parseList(String xml, CollectionType listType) {
        try {
            return XmlHelper.parseList(xml, listType);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with collection type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     */
    public static <T> List<T> parseList(File xml, TypeReference<List<T>> typeReference) {
        try {
            return XmlHelper.parseList(xml, typeReference);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with file and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     */
    public static <T> List<T> parseList(Reader xml, TypeReference<List<T>> typeReference) {
        try {
            return XmlHelper.parseList(xml, typeReference);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with reader and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     */
    public static <T> List<T> parseList(byte[] xml, TypeReference<List<T>> typeReference) {
        try {
            return XmlHelper.parseList(xml, typeReference);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with bytes and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     */
    public static <T> List<T> parseList(InputStream xml, TypeReference<List<T>> typeReference) {
        try {
            return XmlHelper.parseList(xml, typeReference);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with input stream and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
        }
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.List
     */
    public static <T> List<T> parseList(String xml, TypeReference<List<T>> typeReference) {
        try {
            return XmlHelper.parseList(xml, typeReference);
        } catch (XmlParseListException exception) {
            log.error("It is failed during xml to parse as list of collection with type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyList();
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
     * @see java.util.List
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> List<T> parseList(File xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.List
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> List<T> parseList(Reader xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.List
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> List<T> parseList(byte[] xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.List
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> List<T> parseList(InputStream xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> List<T> parseList(String xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseList(xml, listType);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> parseList(File xml, Class<T> clazz) {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> parseList(Reader xml, Class<T> clazz) {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> parseList(byte[] xml, Class<T> clazz) {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> parseList(InputStream xml, Class<T> clazz) {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseList</code>
     * <p>The parse list method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.List} <p>The parse list return object is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T> List<T> parseList(String xml, Class<T> clazz) {
        return parseList(xml, List.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(File xml, CollectionType setType) {
        try {
            return XmlHelper.parseSet(xml, setType);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with file and set type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(Reader xml, CollectionType setType) {
        try {
            return XmlHelper.parseSet(xml, setType);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with reader and set type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(byte[] xml, CollectionType setType) {
        try {
            return XmlHelper.parseSet(xml, setType);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with bytes and set type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(InputStream xml, CollectionType setType) {
        try {
            return XmlHelper.parseSet(xml, setType);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with input stream and set type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param setType {@link com.fasterxml.jackson.databind.type.CollectionType} <p>The set type parameter is <code>CollectionType</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.CollectionType
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(String xml, CollectionType setType) {
        try {
            return XmlHelper.parseSet(xml, setType);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with set type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(File xml, TypeReference<Set<T>> typeReference) {
        try {
            return XmlHelper.parseSet(xml, typeReference);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with file and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(Reader xml, TypeReference<Set<T>> typeReference) {
        try {
            return XmlHelper.parseSet(xml, typeReference);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with reader and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(byte[] xml, TypeReference<Set<T>> typeReference) {
        try {
            return XmlHelper.parseSet(xml, typeReference);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with bytes and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(InputStream xml, TypeReference<Set<T>> typeReference) {
        try {
            return XmlHelper.parseSet(xml, typeReference);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with input stream and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
        }
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(String xml, TypeReference<Set<T>> typeReference) {
        try {
            return XmlHelper.parseSet(xml, typeReference);
        } catch (XmlParseSetException exception) {
            log.error("It is failed during xml to parse as set of collection with type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptySet();
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
     * @see java.util.Set
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(File xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.Set
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(Reader xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.Set
     * @see java.lang.Class
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(byte[] xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.Set
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(InputStream xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
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
     * @see java.util.Set
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends Set<?>, T> Set<T> parseSet(String xml, Class<Z> parseClazz, Class<T> clazz) {
        CollectionType setType = TypeFactory.defaultInstance().constructCollectionType(parseClazz, clazz);
        return parseSet(xml, setType);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(File xml, Class<T> clazz) {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(Reader xml, Class<T> clazz) {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.lang.Class
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(byte[] xml, Class<T> clazz) {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(InputStream xml, Class<T> clazz) {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseSet</code>
     * <p>The parse set method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Set} <p>The parse set return object is <code>Set</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Set
     */
    public static <T> Set<T> parseSet(String xml, Class<T> clazz) {
        return parseSet(xml, Set.class, clazz);
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(File xml, MapType mapType) {
        try {
            return XmlHelper.parseMap(xml, mapType);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with file and map type! {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(Reader xml, MapType mapType) {
        try {
            return XmlHelper.parseMap(xml, mapType);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with reader and map type! {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(byte[] xml, MapType mapType) {
        try {
            return XmlHelper.parseMap(xml, mapType);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with bytes and map type! {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(InputStream xml, MapType mapType) {
        try {
            return XmlHelper.parseMap(xml, mapType);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with input stream and map type! {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml     {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param mapType {@link com.fasterxml.jackson.databind.type.MapType} <p>The map type parameter is <code>MapType</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.MapType
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(String xml, MapType mapType) {
        try {
            return XmlHelper.parseMap(xml, mapType);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with map type! {}", exception.getMessage(), exception);
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(File xml, TypeReference<Map<T, K>> typeReference) {
        try {
            return XmlHelper.parseMap(xml, typeReference);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with file and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(Reader xml, TypeReference<Map<T, K>> typeReference) {
        try {
            return XmlHelper.parseMap(xml, typeReference);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with reader and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(byte[] xml, TypeReference<Map<T, K>> typeReference) {
        try {
            return XmlHelper.parseMap(xml, typeReference);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with bytes and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseMap</code>
     * <p>The parse map method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <K>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map return object is <code>Map</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(InputStream xml, TypeReference<Map<T, K>> typeReference) {
        try {
            return XmlHelper.parseMap(xml, typeReference);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with input stream and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
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
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(String xml, TypeReference<Map<T, K>> typeReference) {
        try {
            return XmlHelper.parseMap(xml, typeReference);
        } catch (XmlParseMapException exception) {
            log.error("It is failed during xml to parse as map of bean with type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return Collections.emptyMap();
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.databind.type.ArrayType
     */
    public static <T> T[] parseArray(File xml, ArrayType arrayType) {
        try {
            return XmlHelper.parseArray(xml, arrayType);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with file and array type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.databind.type.ArrayType
     */
    public static <T> T[] parseArray(Reader xml, ArrayType arrayType) {
        try {
            return XmlHelper.parseArray(xml, arrayType);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with reader and array type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see com.fasterxml.jackson.databind.type.ArrayType
     */
    public static <T> T[] parseArray(byte[] xml, ArrayType arrayType) {
        try {
            return XmlHelper.parseArray(xml, arrayType);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with bytes and array type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.databind.type.ArrayType
     */
    public static <T> T[] parseArray(InputStream xml, ArrayType arrayType) {
        try {
            return XmlHelper.parseArray(xml, arrayType);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with input stream and array type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml       {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param arrayType {@link com.fasterxml.jackson.databind.type.ArrayType} <p>The array type parameter is <code>ArrayType</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.databind.type.ArrayType
     */
    public static <T> T[] parseArray(String xml, ArrayType arrayType) {
        try {
            return XmlHelper.parseArray(xml, arrayType);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with array type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.io.File
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T[] parseArray(File xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseArray(xml, typeReference);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with file and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.io.Reader
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T[] parseArray(Reader xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseArray(xml, typeReference);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with reader and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T[] parseArray(byte[] xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseArray(xml, typeReference);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with bytes and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.io.InputStream
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T[] parseArray(InputStream xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseArray(xml, typeReference);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with input stream and type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml           {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T[] parseArray(String xml, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseArray(xml, typeReference);
        } catch (XmlParseArrayException exception) {
            log.error("It is failed during xml to parse as array of bean with type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <Z>   {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.util.List
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> T[] parseArray(File xml, Class<T> clazz) {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <Z>   {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.Reader} <p>The xml parameter is <code>Reader</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.util.List
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> T[] parseArray(Reader xml, Class<T> clazz) {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <Z>   {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   byte <p>The xml parameter is <code>byte</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.util.List
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> T[] parseArray(byte[] xml, Class<T> clazz) {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <Z>   {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.io.InputStream} <p>The xml parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.util.List
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> T[] parseArray(InputStream xml, Class<T> clazz) {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
    }

    /**
     * <code>parseArray</code>
     * <p>The parse array method.</p>
     * @param <Z>   {@link java.util.List} <p>The generic parameter is <code>List</code> type.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xml   {@link java.lang.String} <p>The xml parameter is <code>String</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse array return object is <code>T</code> type.</p>
     * @see java.util.List
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends List<?>, T> T[] parseArray(String xml, Class<T> clazz) {
        ArrayType arrayType = TypeFactory.defaultInstance().constructArrayType(clazz);
        return parseArray(xml, arrayType);
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
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(File xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
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
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(Reader xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
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
     * @see java.util.Map
     * @see java.lang.Class
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(byte[] xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
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
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(InputStream xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
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
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends Map<?, ?>, T, K> Map<T, K> parseMap(String xml, Class<Z> parseClazz, Class<T> keyClazz, Class<K> valueClazz) {
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseClazz, keyClazz, valueClazz);
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
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(File xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(Reader xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(byte[] xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(InputStream xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, K> parseMap(String xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(File xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(Reader xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.Class
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(byte[] xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(InputStream xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <H extends List<?>, Y extends Map<?, ?>, T, K> Map<T, List<K>> parseMapList(String xml, Class<H> parseListClazz, Class<Y> parseMapClazz, Class<T> keyClazz, Class<K> valueClazz) {
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(parseListClazz, valueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(parseMapClazz, keyClazz, collectionType.getRawClass());
        return parseMap(xml, mapType);
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
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, List<K>> parseMapList(File xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, List<K>> parseMapList(Reader xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, List<K>> parseMapList(byte[] xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, List<K>> parseMapList(InputStream xml, Class<T> keyClazz, Class<K> valueClazz) {
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
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <T, K> Map<T, List<K>> parseMapList(String xml, Class<T> keyClazz, Class<K> valueClazz) {
        return parseMapList(xml, List.class, Map.class, keyClazz, valueClazz);
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(File xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(Reader xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.Class
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(byte[] xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(InputStream xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.List
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <Z extends List<?>, Y extends Map<?, ?>, T, K> List<Map<T, K>> parseListMap(String xml, Class<Z> wrapKeyClazz, Class<Y> contentMapClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(wrapKeyClazz, contentType.getRawClass());
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
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T, K> List<Map<T, K>> parseListMap(File xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
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
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T, K> List<Map<T, K>> parseListMap(Reader xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
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
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T, K> List<Map<T, K>> parseListMap(byte[] xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
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
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T, K> List<Map<T, K>> parseListMap(InputStream xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
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
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.List
     */
    public static <T, K> List<Map<T, K>> parseListMap(String xml, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(Map.class, contentKeyClazz, contentValueClazz);
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, contentType.getRawClass());
        return parseList(xml, collectionType);
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
     * @see java.util.Map
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(File xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.Map
     * @see java.io.Reader
     * @see java.lang.Class
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.Map
     * @see java.lang.Class
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.Map
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        MapType contentType = TypeFactory.defaultInstance().constructMapType(contentMapClazz, contentKeyClazz, contentValueClazz);
        MapType mapType = TypeFactory.defaultInstance().constructMapType(wrapMapClazz, wrapKeyClazz, contentType.getRawClass());
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
     * @see java.util.Map
     * @see java.lang.String
     * @see java.lang.Class
     */
    public static <H extends Map<?, ?>, Y extends Map<?, ?>, Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<H> wrapMapClazz, Class<Y> contentMapClazz, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
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
     * @param xml               {@link java.io.File} <p>The xml parameter is <code>File</code> type.</p>
     * @param wrapKeyClazz      {@link java.lang.Class} <p>The wrap key clazz parameter is <code>Class</code> type.</p>
     * @param contentKeyClazz   {@link java.lang.Class} <p>The content key clazz parameter is <code>Class</code> type.</p>
     * @param contentValueClazz {@link java.lang.Class} <p>The content value clazz parameter is <code>Class</code> type.</p>
     * @return {@link java.util.Map} <p>The parse map map return object is <code>Map</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(File xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
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
     * @see java.io.Reader
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(Reader xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
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
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(byte[] xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
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
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(InputStream xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
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
     * @see java.lang.String
     * @see java.lang.Class
     * @see java.util.Map
     */
    public static <Z, T, K> Map<Z, Map<T, K>> parseMapMap(String xml, Class<Z> wrapKeyClazz, Class<T> contentKeyClazz, Class<K> contentValueClazz) {
        return parseMapMap(xml, Map.class, Map.class, wrapKeyClazz, contentKeyClazz, contentValueClazz);
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Class
     */
    public static <T> T parseConvert(Object value, Class<T> clazz) {
        try {
            return XmlHelper.parseConvert(value, clazz);
        } catch (XmlParseConvertException exception) {
            log.error("It is failed during object to convert as bean with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value         {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param typeReference {@link com.fasterxml.jackson.core.type.TypeReference} <p>The type reference parameter is <code>TypeReference</code> type.</p>
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @see java.lang.Object
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T parseConvert(Object value, TypeReference<T> typeReference) {
        try {
            return XmlHelper.parseConvert(value, typeReference);
        } catch (XmlParseConvertException exception) {
            log.error("It is failed during object to convert as bean with type reference! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>parseConvert</code>
     * <p>The parse convert method.</p>
     * @param <T>      {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param javaType {@link com.fasterxml.jackson.databind.JavaType} <p>The java type parameter is <code>JavaType</code> type.</p>
     * @return T <p>The parse convert return object is <code>T</code> type.</p>
     * @see java.lang.Object
     * @see com.fasterxml.jackson.databind.JavaType
     */
    public static <T> T parseConvert(Object value, JavaType javaType) {
        try {
            return XmlHelper.parseConvert(value, javaType);
        } catch (XmlParseConvertException exception) {
            log.error("It is failed during object to convert as bean with java type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

}
