package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.often.JaxbXmlMarshalException;
import io.github.nichetoolkit.rest.error.often.JaxbXmlPropertyException;
import io.github.nichetoolkit.rest.error.often.JaxbXmlReadException;
import io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <code>JaxbHelper</code>
 * <p>The jaxb helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk17
 */
public class JaxbHelper {

    /**
     * <code>encode</code>
     * <p>The encode method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.String
     * @see jakarta.servlet.http.HttpServletResponse
     */
    public static void encode(String filename, HttpServletResponse response) {
        String fileName = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        String content = "attachment;filename=".concat(fileName);
        response.setHeader("Content-Disposition", content);
        response.setHeader("Content-Type", "application/xml;charset=UTF-8");
        response.setContentType("application/octet-stream");
    }

    /**
     * <code>unmarshaller</code>
     * <p>The unmarshaller method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link jakarta.xml.bind.Unmarshaller} <p>The unmarshaller return object is <code>Unmarshaller</code> type.</p>
     * @throws JaxbXmlMarshalException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlMarshalException} <p>The jaxb xml marshal exception is <code>JaxbXmlMarshalException</code> type.</p>
     * @see java.lang.Class
     * @see jakarta.xml.bind.Unmarshaller
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlMarshalException
     */
    public static <T> Unmarshaller unmarshaller(Class<T> clazz) throws JaxbXmlMarshalException {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            return context.createUnmarshaller();
        } catch (JAXBException exception) {
            throw new JaxbXmlMarshalException(exception.getMessage());
        }
    }


    /**
     * <code>marshaller</code>
     * <p>The marshaller method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return {@link jakarta.xml.bind.Marshaller} <p>The marshaller return object is <code>Marshaller</code> type.</p>
     * @throws JaxbXmlMarshalException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlMarshalException} <p>The jaxb xml marshal exception is <code>JaxbXmlMarshalException</code> type.</p>
     * @see java.lang.Class
     * @see jakarta.xml.bind.Marshaller
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlMarshalException
     */
    public static <T> Marshaller marshaller(Class<T> clazz) throws JaxbXmlMarshalException {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            return context.createMarshaller();
        } catch (JAXBException exception) {
            throw new JaxbXmlMarshalException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlFile {@link org.springframework.web.multipart.MultipartFile} <p>The xml file parameter is <code>MultipartFile</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @throws JaxbXmlReadException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlReadException} <p>The jaxb xml read exception is <code>JaxbXmlReadException</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlReadException
     */
    public static <T> T read(MultipartFile xmlFile, Class<T> clazz) throws JaxbXmlReadException {
        if (GeneralUtils.isEmpty(xmlFile)) {
            return null;
        }
        try (InputStream inputStream = xmlFile.getInputStream()) {
            return JAXB.unmarshal(inputStream, clazz);
        } catch (DataBindingException | IOException exception) {
            throw new JaxbXmlReadException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlFile {@link java.io.File} <p>The xml file parameter is <code>File</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @throws JaxbXmlReadException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlReadException} <p>The jaxb xml read exception is <code>JaxbXmlReadException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlReadException
     */
    public static <T> T read(File xmlFile, Class<T> clazz) throws JaxbXmlReadException {
        if (GeneralUtils.isEmpty(xmlFile) || !xmlFile.exists()) {
            return null;
        }
        try (InputStream inputStream = Files.newInputStream(xmlFile.toPath())) {
            return JAXB.unmarshal(inputStream, clazz);
        } catch (DataBindingException | IOException exception) {
            throw new JaxbXmlReadException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>          {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param unmarshaller {@link jakarta.xml.bind.Unmarshaller} <p>The unmarshaller parameter is <code>Unmarshaller</code> type.</p>
     * @param inputStream  {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz        {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @throws JaxbXmlReadException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlReadException} <p>The jaxb xml read exception is <code>JaxbXmlReadException</code> type.</p>
     * @see jakarta.xml.bind.Unmarshaller
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlReadException
     */
    public static <T> T read(Unmarshaller unmarshaller, InputStream inputStream, Class<T> clazz) throws JaxbXmlReadException {
        if (GeneralUtils.isEmpty(inputStream)) {
            return null;
        }
        try {
            JAXBElement<T> jaxbElement = unmarshaller.unmarshal(new StreamSource(inputStream), clazz);
            return jaxbElement.getValue();
        } catch (JAXBException | DataBindingException exception) {
            throw new JaxbXmlReadException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>The clazz parameter is <code>Class</code> type.</p>
     * @return T <p>The read return object is <code>T</code> type.</p>
     * @throws JaxbXmlReadException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlReadException} <p>The jaxb xml read exception is <code>JaxbXmlReadException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlReadException
     */
    public static <T> T read(InputStream inputStream, Class<T> clazz) throws JaxbXmlReadException {
        if (GeneralUtils.isEmpty(inputStream)) {
            return null;
        }
        try {
            return JAXB.unmarshal(inputStream, clazz);
        } catch (DataBindingException exception) {
            throw new JaxbXmlReadException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>The xml object parameter is <code>T</code> type.</p>
     * @param filename   {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response   {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see jakarta.xml.bind.Marshaller
     * @see java.lang.String
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, String filename, HttpServletResponse response) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            encode(filename, response);
            ServletOutputStream outputStream = response.getOutputStream();
            marshaller.marshal(xmlObject, outputStream);
        } catch (JAXBException | IOException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>The xml object parameter is <code>T</code> type.</p>
     * @param response   {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see jakarta.xml.bind.Marshaller
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, HttpServletResponse response) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            marshaller.marshal(xmlObject, outputStream);
        } catch (JAXBException | IOException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>          {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller   {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject    T <p>The xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see jakarta.xml.bind.Marshaller
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, OutputStream outputStream) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            marshaller.marshal(xmlObject, outputStream);
        } catch (JAXBException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param filename  {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see java.lang.String
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(T xmlObject, String filename, HttpServletResponse response) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            encode(filename, response);
            ServletOutputStream outputStream = response.getOutputStream();
            JAXB.marshal(xmlObject, outputStream);
        } catch (IOException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param xmlObject T <p>The xml object parameter is <code>T</code> type.</p>
     * @param response  {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(T xmlObject, HttpServletResponse response) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            JAXB.marshal(xmlObject, outputStream);
        } catch (IOException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>The xml object parameter is <code>T</code> type.</p>
     * @param writer     {@link java.io.Writer} <p>The writer parameter is <code>Writer</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see jakarta.xml.bind.Marshaller
     * @see java.io.Writer
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, Writer writer) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            marshaller.marshal(xmlObject, writer);
        } catch (JAXBException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>The xml object parameter is <code>T</code> type.</p>
     * @param path       {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see jakarta.xml.bind.Marshaller
     * @see java.nio.file.Path
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, Path path) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            marshaller.marshal(xmlObject, path.toFile());
        } catch (JAXBException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>The xml object parameter is <code>T</code> type.</p>
     * @param file       {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @throws JaxbXmlWriteException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException} <p>The jaxb xml write exception is <code>JaxbXmlWriteException</code> type.</p>
     * @see jakarta.xml.bind.Marshaller
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, File file) throws JaxbXmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            marshaller.marshal(xmlObject, file);
        } catch (JAXBException exception) {
            throw new JaxbXmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>property</code>
     * <p>The property method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param marshaller {@link jakarta.xml.bind.Marshaller} <p>The marshaller parameter is <code>Marshaller</code> type.</p>
     * @param key        {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @throws JaxbXmlPropertyException {@link io.github.nichetoolkit.rest.error.often.JaxbXmlPropertyException} <p>The jaxb xml property exception is <code>JaxbXmlPropertyException</code> type.</p>
     * @see jakarta.xml.bind.Marshaller
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.error.often.JaxbXmlPropertyException
     */
    public static <T> void property(Marshaller marshaller, String key, Object value) throws JaxbXmlPropertyException {
        if (GeneralUtils.isEmpty(key) || GeneralUtils.isEmpty(value)) {
            return;
        }
        try {
            marshaller.setProperty(key, value);
        } catch (JAXBException exception) {
            throw new JaxbXmlPropertyException(exception.getMessage());
        }
    }
}
