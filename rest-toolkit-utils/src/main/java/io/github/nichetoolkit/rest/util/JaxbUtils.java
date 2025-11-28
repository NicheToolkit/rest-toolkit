package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.often.JaxbXmlMarshalException;
import io.github.nichetoolkit.rest.error.often.JaxbXmlPropertyException;
import io.github.nichetoolkit.rest.error.often.JaxbXmlReadException;
import io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException;
import io.github.nichetoolkit.rest.helper.JaxbHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Path;

/**
 * <code>JaxbUtils</code>
 * <p>The jaxb utils class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class JaxbUtils {

    /**
     * <code>encode</code>
     * <p>The encode method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see  java.lang.String
     * @see  jakarta.servlet.http.HttpServletResponse
     */
    public static void encode(String filename, HttpServletResponse response) {
        JaxbHelper.encode(filename,response);
    }


    /**
     * <code>marshaller</code>
     * <p>The marshaller method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  jakarta.xml.bind.Marshaller
     * @return  {@link jakarta.xml.bind.Marshaller} <p>The marshaller return object is <code>Marshaller</code> type.</p>
     */
    public static <T> Marshaller marshaller(Class<T> clazz)  {
        try {
            return JaxbHelper.marshaller(clazz);
        } catch (JaxbXmlMarshalException exception) {
            log.error("It is failed when data marshaller with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>unmarshaller</code>
     * <p>The unmarshaller method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  jakarta.xml.bind.Unmarshaller
     * @return  {@link jakarta.xml.bind.Unmarshaller} <p>The unmarshaller return object is <code>Unmarshaller</code> type.</p>
     */
    public static <T> Unmarshaller unmarshaller(Class<T> clazz)  {
        try {
            return JaxbHelper.unmarshaller(clazz);
        } catch (JaxbXmlMarshalException exception) {
            log.error("It is failed when data unmarshaller with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlFile {@link org.springframework.web.multipart.MultipartFile} <p>The xml file parameter is <code>MultipartFile</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.lang.Class
     * @return T <p>The read return object is <code>T</code> type.</p>
     */
    public static <T> T read(MultipartFile xmlFile, Class<T> clazz) {
        try {
            return JaxbHelper.read(xmlFile, clazz);
        } catch (JaxbXmlReadException exception) {
            log.error("It is failed when xml read with file and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlFile {@link java.io.File} <p>The xml file parameter is <code>File</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.Class
     * @return T <p>The read return object is <code>T</code> type.</p>
     */
    public static <T> T read(File xmlFile, Class<T> clazz) {
        try {
            return JaxbHelper.read(xmlFile, clazz);
        } catch (JaxbXmlReadException exception) {
            log.error("It is failed when xml file read with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param unmarshaller {@link jakarta.xml.bind.Unmarshaller} <p>The unmarshaller parameter is <code>Unmarshaller</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  jakarta.xml.bind.Unmarshaller
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @return T <p>The read return object is <code>T</code> type.</p>
     */
    public static <T> T read(Unmarshaller unmarshaller, InputStream inputStream, Class<T> clazz) {
        try {
            return JaxbHelper.read(unmarshaller,inputStream, clazz);
        } catch (JaxbXmlReadException exception) {
            log.error("It is failed when unmarshaller input stream read with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.lang.Class
     * @return T <p>The read return object is <code>T</code> type.</p>
     */
    public static <T> T read(InputStream inputStream, Class<T> clazz) {
        try {
            return JaxbHelper.read(inputStream, clazz);
        } catch (JaxbXmlReadException exception) {
            log.error("It is failed when inputStream read! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see  jakarta.xml.bind.Marshaller
     * @see  java.lang.String
     * @see  jakarta.servlet.http.HttpServletResponse
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, String filename, HttpServletResponse response)  {
        try {
            JaxbHelper.write(marshaller, xmlObject, filename,response);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with xml file and filename! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see  jakarta.xml.bind.Marshaller
     * @see  jakarta.servlet.http.HttpServletResponse
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, HttpServletResponse response)  {
        try {
            JaxbHelper.write(marshaller, xmlObject, response);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with xml file and response! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see  jakarta.xml.bind.Marshaller
     * @see  java.io.OutputStream
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, OutputStream outputStream)  {
        try {
            JaxbHelper.write(marshaller, xmlObject, outputStream);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with xml file and output stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see  java.lang.String
     * @see  jakarta.servlet.http.HttpServletResponse
     */
    public static <T> void write(T xmlObject, String filename, HttpServletResponse response)  {
        try {
            JaxbHelper.write(xmlObject, filename, response);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with filename and response! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see  jakarta.servlet.http.HttpServletResponse
     */
    public static <T> void write(T xmlObject, HttpServletResponse response)  {
        try {
            JaxbHelper.write(xmlObject, response);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with file and response! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see  java.io.OutputStream
     */
    public static <T> void write(T xmlObject, OutputStream outputStream)  {
        JAXB.marshal(xmlObject,outputStream);
    }


    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param writer {@link java.io.Writer} <p>The writer parameter is <code>Writer</code> type.</p>
     * @see  jakarta.xml.bind.Marshaller
     * @see  java.io.Writer
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, Writer writer)  {
        try {
            JaxbHelper.write(marshaller, xmlObject, writer);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with xml file and writer! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }


    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param path {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @see  jakarta.xml.bind.Marshaller
     * @see  java.nio.file.Path
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, Path path)  {
        try {
            JaxbHelper.write(marshaller, xmlObject, path);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with xml file and path! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }


    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see  jakarta.xml.bind.Marshaller
     * @see  java.io.File
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, File file)  {
        try {
            JaxbHelper.write(marshaller, xmlObject, file);
        } catch (JaxbXmlWriteException exception) {
            log.error("It is failed when xml write with xml file and file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }
    
    /**
     * <code>property</code>
     * <p>The property method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see  jakarta.xml.bind.Marshaller
     * @see  java.lang.String
     * @see  java.lang.Object
     */
    public static <T> void property(Marshaller marshaller, String key, Object value)  {
        try {
            JaxbHelper.property(marshaller, key, value);
        } catch (JaxbXmlPropertyException exception) {
            log.error("It is failed when xml write set property! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }
}
