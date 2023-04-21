package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.often.XmlMarshalException;
import io.github.nichetoolkit.rest.error.often.XmlReadException;
import io.github.nichetoolkit.rest.error.often.XmlWriteException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.*;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>XmlHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class XmlHelper {

    public static void encode(String filename, HttpServletResponse response) {
        String fileName = new String(filename.trim().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        try {
            fileName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ignored) {
        }
        String content = "attachment;filename=".concat(fileName);
        response.setHeader("Content-Disposition", content);
        response.setHeader("ContentType", "application/xml;charset=UTF-8");
        response.setContentType("application/octet-stream");
    }


    public static <T> Marshaller marshal(Class<T> clazz) throws XmlMarshalException {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            return context.createMarshaller();
        } catch (JAXBException exception) {
            throw new XmlMarshalException(exception.getMessage());
        }
    }

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

    public static <T> T read(File xmlFile, Class<T> clazz) throws XmlReadException {
        if (GeneralUtils.isEmpty(xmlFile) || !xmlFile.exists()) {
            return null;
        }
        try (InputStream inputStream = new FileInputStream(xmlFile)) {
            return JAXB.unmarshal(inputStream, clazz);
        } catch (DataBindingException | IOException exception) {
            throw new XmlReadException(exception.getMessage());
        }
    }

    public static <T> T read(InputStream inputStream, Class<T> clazz, boolean closable) throws XmlReadException {
        if (GeneralUtils.isEmpty(inputStream)) {
            return null;
        }
        try {
            return JAXB.unmarshal(inputStream, clazz);
        } catch ( DataBindingException exception) {
            throw new XmlReadException(exception.getMessage());
        } finally {
            if (closable && GeneralUtils.isNotEmpty(inputStream)) {
                CloseableHelper.close(inputStream);
            }
        }
    }

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
}
