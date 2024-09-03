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
 * <p>The type xml utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class XmlUtils {

    /**
     * <code>encode</code>
     * <p>the method.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     */
    public static void encode(String filename, HttpServletResponse response) {
        XmlHelper.encode(filename,response);
    }


    /**
     * <code>marshaller</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link javax.xml.bind.Marshaller} <p>the return object is <code>Marshaller</code> type.</p>
     * @see java.lang.Class
     * @see javax.xml.bind.Marshaller
     */
    public static <T> Marshaller marshaller(Class<T> clazz)  {
        try {
            return XmlHelper.marshaller(clazz);
        } catch (XmlMarshalException exception) {
            log.error("It is failed when data write to create marshal! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>unmarshaller</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link javax.xml.bind.Unmarshaller} <p>the return object is <code>Unmarshaller</code> type.</p>
     * @see java.lang.Class
     * @see javax.xml.bind.Unmarshaller
     */
    public static <T> Unmarshaller unmarshaller(Class<T> clazz)  {
        try {
            return XmlHelper.unmarshaller(clazz);
        } catch (XmlMarshalException exception) {
            log.error("It is failed when data write to create marshal! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlFile {@link org.springframework.web.multipart.MultipartFile} <p>the xml file parameter is <code>MultipartFile</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.lang.Class
     */
    public static <T> T read(MultipartFile xmlFile, Class<T> clazz) {
        try {
            return XmlHelper.read(xmlFile, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when xml read! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlFile {@link java.io.File} <p>the xml file parameter is <code>File</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     */
    public static <T> T read(File xmlFile, Class<T> clazz) {
        try {
            return XmlHelper.read(xmlFile, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when xml file read! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param <T>          {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param unmarshaller {@link javax.xml.bind.Unmarshaller} <p>the unmarshaller parameter is <code>Unmarshaller</code> type.</p>
     * @param inputStream  {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz        {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @see javax.xml.bind.Unmarshaller
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <T> T read(Unmarshaller unmarshaller, InputStream inputStream, Class<T> clazz) {
        try {
            return XmlHelper.read(unmarshaller,inputStream, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when unmarshaller inputStream read! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param <T>         {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     */
    public static <T> T read(InputStream inputStream, Class<T> clazz) {
        try {
            return XmlHelper.read(inputStream, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when inputStream read! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param marshaller {@link javax.xml.bind.Marshaller} <p>the marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>the xml object parameter is <code>T</code> type.</p>
     * @param filename   {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param response   {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, String filename, HttpServletResponse response)  {
        try {
            XmlHelper.write(marshaller, xmlObject, filename,response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml read! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param marshaller {@link javax.xml.bind.Marshaller} <p>the marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>the xml object parameter is <code>T</code> type.</p>
     * @param response   {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, HttpServletResponse response)  {
        try {
            XmlHelper.write(marshaller, xmlObject, response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml read! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>          {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param marshaller   {@link javax.xml.bind.Marshaller} <p>the marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject    T <p>the xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see java.io.OutputStream
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, OutputStream outputStream)  {
        try {
            XmlHelper.write(marshaller, xmlObject, outputStream);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml read! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlObject T <p>the xml object parameter is <code>T</code> type.</p>
     * @param filename  {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(T xmlObject, String filename, HttpServletResponse response)  {
        try {
            XmlHelper.write(xmlObject, filename, response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml read! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlObject T <p>the xml object parameter is <code>T</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     */
    public static <T> void write(T xmlObject, HttpServletResponse response)  {
        try {
            XmlHelper.write(xmlObject, response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml read! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>          {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlObject    T <p>the xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.io.OutputStream
     */
    public static <T> void write(T xmlObject, OutputStream outputStream)  {
        JAXB.marshal(xmlObject,outputStream);
    }
}
