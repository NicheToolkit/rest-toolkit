package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.often.XmlMarshalException;
import io.github.nichetoolkit.rest.error.often.XmlReadException;
import io.github.nichetoolkit.rest.error.often.XmlWriteException;
import io.github.nichetoolkit.rest.helper.XmlHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;

/**
 * <p>XmlUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class XmlUtils {

    public static void encode(String filename, HttpServletResponse response) {
        XmlHelper.encode(filename,response);
    }


    public static <T> Marshaller marshal(Class<T> clazz)  {
        try {
            return XmlHelper.marshal(clazz);
        } catch (XmlMarshalException exception) {
            log.error("It is failed when data write to create marshal!", exception);
            exception.printStackTrace();
            return null;
        }
    }

    public static <T> T read(MultipartFile xmlFile, Class<T> clazz) {
        try {
            return XmlHelper.read(xmlFile, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when xml read!", exception);
            exception.printStackTrace();
            return null;
        }
    }

    public static <T> T read(File xmlFile, Class<T> clazz) {
        try {
            return XmlHelper.read(xmlFile, clazz);
        } catch (XmlReadException exception) {
            log.error("It is failed when xml read!", exception);
            exception.printStackTrace();
            return null;
        }
    }

    public static <T> T read(InputStream inputStream, Class<T> clazz, boolean closable) {
        try {
            return XmlHelper.read(inputStream, clazz, closable);
        } catch (XmlReadException exception) {
            log.error("It is failed when xml read!", exception);
            exception.printStackTrace();
            return null;
        }
    }

    public static <T> void write(Marshaller marshaller, T xmlObject, String filename, HttpServletResponse response)  {
        try {
            XmlHelper.write(marshaller, xmlObject, filename,response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml read!", exception);
            exception.printStackTrace();
        }
    }

    public static <T> void write(T xmlObject, String filename, HttpServletResponse response)  {
        try {
            XmlHelper.write(xmlObject, filename, response);
        } catch (XmlWriteException exception) {
            log.error("It is failed when xml read!", exception);
            exception.printStackTrace();
        }
    }
}
