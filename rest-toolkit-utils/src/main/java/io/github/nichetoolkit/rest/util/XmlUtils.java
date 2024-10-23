package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.often.XmlMarshalException;
import io.github.nichetoolkit.rest.error.often.XmlReadException;
import io.github.nichetoolkit.rest.error.often.XmlWriteException;
import io.github.nichetoolkit.rest.helper.XmlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <code>XmlUtils</code>
 * <p>The xml utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class XmlUtils {

    /**
     * <code>encode</code>
     * <p>The encode method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     */
    public static void encode(String filename, HttpServletResponse response) {
        XmlHelper.encode(filename,response);
    }


    /**
     * <code>marshaller</code>
     * <p>The marshaller method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link javax.xml.bind.Marshaller} <p>The marshaller return object is <code>Marshaller</code> type.</p>
     * @see java.lang.Class
     * @see javax.xml.bind.Marshaller
     */
    public static <T> Marshaller marshaller(Class<T> clazz)  {
        try {
            return XmlHelper.marshaller(clazz);
        } catch (XmlMarshalException exception) {
            log.error("It is failed when data marshaller with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>unmarshaller</code>
     * <p>The unmarshaller method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link javax.xml.bind.Unmarshaller} <p>The unmarshaller return object is <code>Unmarshaller</code> type.</p>
     * @see java.lang.Class
     * @see javax.xml.bind.Unmarshaller
     */
    public static <T> Unmarshaller unmarshaller(Class<T> clazz)  {
        try {
            return XmlHelper.unmarshaller(clazz);
        } catch (XmlMarshalException exception) {
            log.error("It is failed when data unmarshaller with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlFile {@link org.springframework.web.multipart.MultipartFile} <p>The xml file parameter is <code>MultipartFile</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.lang.Class
     */
    public static <T> T read(MultipartFile xmlFile, Class<T> clazz) {
        try {
            return XmlHelper.read(xmlFile, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when xml read with file and class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlFile {@link java.io.File} <p>The xml file parameter is <code>File</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <T> T read(File xmlFile, Class<T> clazz) {
        try {
            return XmlHelper.read(xmlFile, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when xml file read with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>          {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param unmarshaller {@link javax.xml.bind.Unmarshaller} <p>The unmarshaller parameter is <code>Unmarshaller</code> type.</p>
     * @param inputStream  {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz        {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @see javax.xml.bind.Unmarshaller
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <T> T read(Unmarshaller unmarshaller, InputStream inputStream, Class<T> clazz) {
        try {
            return XmlHelper.read(unmarshaller,inputStream, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when unmarshaller input stream read with class type! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <T> T read(InputStream inputStream, Class<T> clazz) {
        try {
            return XmlHelper.read(inputStream, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when inputStream read! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link javax.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>The xml object parameter is <code>T</code> type.</p>
     * @param filename   {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response   {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, String filename, HttpServletResponse response)  {
        try {
            XmlHelper.write(marshaller, xmlObject, filename,response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml write with xml file and filename! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link javax.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>The xml object parameter is <code>T</code> type.</p>
     * @param response   {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, HttpServletResponse response)  {
        try {
            XmlHelper.write(marshaller, xmlObject, response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml write with xml file and response! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>          {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller   {@link javax.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject    T <p>The xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see java.io.OutputStream
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, OutputStream outputStream)  {
        try {
            XmlHelper.write(marshaller, xmlObject, outputStream);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml write with xml file and output stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param filename  {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(T xmlObject, String filename, HttpServletResponse response)  {
        try {
            XmlHelper.write(xmlObject, filename, response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml write with filename and response! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(T xmlObject, HttpServletResponse response)  {
        try {
            XmlHelper.write(xmlObject, response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml write with file and response! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>          {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject    T <p>The xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.io.OutputStream
     */
    public static <T> void write(T xmlObject, OutputStream outputStream)  {
        JAXB.marshal(xmlObject,outputStream);
    }
}
