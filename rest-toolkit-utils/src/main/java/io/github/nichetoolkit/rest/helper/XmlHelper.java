package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.often.XmlMarshalException;
import io.github.nichetoolkit.rest.error.often.XmlReadException;
import io.github.nichetoolkit.rest.error.often.XmlWriteException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * <code>XmlHelper</code>
 * <p>The type xml helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class XmlHelper {

    /**
     * <code>encode</code>
     * <p>the method.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     */
    public static void encode(String filename, HttpServletResponse response) {
        String fileName = new String(filename.trim().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        try {
            fileName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ignored) {
        }
        String content = "attachment;filename=".concat(fileName);
        response.setHeader("Content-Disposition", content);
        response.setHeader("Content-Type", "application/xml;charset=UTF-8");
        response.setContentType("application/octet-stream");
    }

    /**
     * <code>unmarshaller</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link javax.xml.bind.Unmarshaller} <p>the return object is <code>Unmarshaller</code> type.</p>
     * @throws XmlMarshalException {@link io.github.nichetoolkit.rest.error.often.XmlMarshalException} <p>the xml marshal exception is <code>XmlMarshalException</code> type.</p>
     * @see java.lang.Class
     * @see javax.xml.bind.Unmarshaller
     * @see io.github.nichetoolkit.rest.error.often.XmlMarshalException
     */
    public static <T> Unmarshaller unmarshaller (Class<T> clazz) throws XmlMarshalException {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            return context.createUnmarshaller();
        } catch (JAXBException exception) {
            throw new XmlMarshalException(exception.getMessage());
        }
    }


    /**
     * <code>marshaller</code>
     * <p>the method.</p>
     * @param <T>   {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param clazz {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return {@link javax.xml.bind.Marshaller} <p>the return object is <code>Marshaller</code> type.</p>
     * @throws XmlMarshalException {@link io.github.nichetoolkit.rest.error.often.XmlMarshalException} <p>the xml marshal exception is <code>XmlMarshalException</code> type.</p>
     * @see java.lang.Class
     * @see javax.xml.bind.Marshaller
     * @see io.github.nichetoolkit.rest.error.often.XmlMarshalException
     */
    public static <T> Marshaller marshaller(Class<T> clazz) throws XmlMarshalException {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            return context.createMarshaller();
        } catch (JAXBException exception) {
            throw new XmlMarshalException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlFile {@link org.springframework.web.multipart.MultipartFile} <p>the xml file parameter is <code>MultipartFile</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @throws XmlReadException {@link io.github.nichetoolkit.rest.error.often.XmlReadException} <p>the xml read exception is <code>XmlReadException</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.XmlReadException
     */
    public static <T> T read(MultipartFile xmlFile, Class<T> clazz) throws XmlReadException {
        if (GeneralUtils.isEmpty(xmlFile)) {
            return null;
        }
        try (InputStream inputStream = xmlFile.getInputStream()) {
            return JAXB.unmarshal(inputStream, clazz);
        } catch (DataBindingException | IOException exception) {
            throw new XmlReadException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param <T>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlFile {@link java.io.File} <p>the xml file parameter is <code>File</code> type.</p>
     * @param clazz   {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @throws XmlReadException {@link io.github.nichetoolkit.rest.error.often.XmlReadException} <p>the xml read exception is <code>XmlReadException</code> type.</p>
     * @see java.io.File
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.XmlReadException
     */
    public static <T> T read(File xmlFile, Class<T> clazz) throws XmlReadException {
        if (GeneralUtils.isEmpty(xmlFile) || !xmlFile.exists()) {
            return null;
        }
        try (InputStream inputStream = Files.newInputStream(xmlFile.toPath())) {
            return JAXB.unmarshal(inputStream, clazz);
        } catch (DataBindingException | IOException exception) {
            throw new XmlReadException(exception.getMessage());
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
     * @throws XmlReadException {@link io.github.nichetoolkit.rest.error.often.XmlReadException} <p>the xml read exception is <code>XmlReadException</code> type.</p>
     * @see javax.xml.bind.Unmarshaller
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.XmlReadException
     */
    public static <T> T read(Unmarshaller unmarshaller, InputStream inputStream, Class<T> clazz) throws XmlReadException {
        if (GeneralUtils.isEmpty(inputStream)) {
            return null;
        }
        try {
            JAXBElement<T> jaxbElement = unmarshaller.unmarshal(new StreamSource(inputStream), clazz);
            return jaxbElement.getValue();
        } catch ( JAXBException | DataBindingException exception) {
            throw new XmlReadException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param <T>         {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @param clazz       {@link java.lang.Class} <p>the clazz parameter is <code>Class</code> type.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @throws XmlReadException {@link io.github.nichetoolkit.rest.error.often.XmlReadException} <p>the xml read exception is <code>XmlReadException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Class
     * @see io.github.nichetoolkit.rest.error.often.XmlReadException
     */
    public static <T> T read(InputStream inputStream, Class<T> clazz) throws XmlReadException {
        if (GeneralUtils.isEmpty(inputStream)) {
            return null;
        }
        try {
            return JAXB.unmarshal(inputStream, clazz);
        } catch ( DataBindingException exception) {
            throw new XmlReadException(exception.getMessage());
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
     * @throws XmlWriteException {@link io.github.nichetoolkit.rest.error.often.XmlWriteException} <p>the xml write exception is <code>XmlWriteException</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.XmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, String filename, HttpServletResponse response) throws XmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            encode(filename, response);
            ServletOutputStream outputStream = response.getOutputStream();
            marshaller.marshal(xmlObject,outputStream);
        } catch (JAXBException| IOException exception) {
            throw new XmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param marshaller {@link javax.xml.bind.Marshaller} <p>the marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject  T <p>the xml object parameter is <code>T</code> type.</p>
     * @param response   {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws XmlWriteException {@link io.github.nichetoolkit.rest.error.often.XmlWriteException} <p>the xml write exception is <code>XmlWriteException</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see javax.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.XmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, HttpServletResponse response) throws XmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            marshaller.marshal(xmlObject,outputStream);
        } catch (JAXBException| IOException exception) {
            throw new XmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>          {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param marshaller   {@link javax.xml.bind.Marshaller} <p>the marshaller parameter is <code>Marshaller</code> type.</p>
     * @param xmlObject    T <p>the xml object parameter is <code>T</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @throws XmlWriteException {@link io.github.nichetoolkit.rest.error.often.XmlWriteException} <p>the xml write exception is <code>XmlWriteException</code> type.</p>
     * @see javax.xml.bind.Marshaller
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.often.XmlWriteException
     */
    public static <T> void write(Marshaller marshaller, T xmlObject, OutputStream outputStream) throws XmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            marshaller.marshal(xmlObject,outputStream);
        } catch (JAXBException exception) {
            throw new XmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlObject T <p>the xml object parameter is <code>T</code> type.</p>
     * @param filename  {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws XmlWriteException {@link io.github.nichetoolkit.rest.error.often.XmlWriteException} <p>the xml write exception is <code>XmlWriteException</code> type.</p>
     * @see java.lang.String
     * @see javax.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.XmlWriteException
     */
    public static <T> void write(T xmlObject, String filename, HttpServletResponse response) throws XmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            encode(filename,response);
            ServletOutputStream outputStream = response.getOutputStream();
            JAXB.marshal(xmlObject,outputStream);
        } catch (IOException exception) {
            throw new XmlWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param xmlObject T <p>the xml object parameter is <code>T</code> type.</p>
     * @param response  {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws XmlWriteException {@link io.github.nichetoolkit.rest.error.often.XmlWriteException} <p>the xml write exception is <code>XmlWriteException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.XmlWriteException
     */
    public static <T> void write(T xmlObject, HttpServletResponse response) throws XmlWriteException {
        if (GeneralUtils.isEmpty(xmlObject)) {
            return;
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            JAXB.marshal(xmlObject,outputStream);
        } catch (IOException exception) {
            throw new XmlWriteException(exception.getMessage());
        }
    }
}
