package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.often.IoStreamReadException;
import io.github.nichetoolkit.rest.error.often.IoStreamTransferException;
import io.github.nichetoolkit.rest.error.often.IoStreamWriteException;
import io.github.nichetoolkit.rest.helper.IoStreamHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <code>IoStreamUtils</code>
 * <p>The type io stream utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class IoStreamUtils {

    /**
     * <code>transfer</code>
     * <p>the method.</p>
     * @param multipartFile    {@link org.springframework.web.multipart.MultipartFile} <p>the multipart file parameter is <code>MultipartFile</code> type.</p>
     * @param transferFilePath {@link java.lang.String} <p>the transfer file path parameter is <code>String</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.lang.String
     */
    public static void transfer(MultipartFile multipartFile, String transferFilePath) {
        try {
            IoStreamHelper.transfer(multipartFile, transferFilePath);
        } catch (IoStreamTransferException exception) {
            log.error("It is failed during transferring from multipart file to file path! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>transfer</code>
     * <p>the method.</p>
     * @param multipartFile {@link org.springframework.web.multipart.MultipartFile} <p>the multipart file parameter is <code>MultipartFile</code> type.</p>
     * @param transferFile  {@link java.io.File} <p>the transfer file parameter is <code>File</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.io.File
     */
    public static void transfer(MultipartFile multipartFile, File transferFile) {
        try {
            IoStreamHelper.transfer(multipartFile, transferFile);
        } catch (IoStreamTransferException exception) {
            log.error("It is failed during transferring from multipart file to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>transfer</code>
     * <p>the method.</p>
     * @param inputStream  {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.io.InputStream
     * @see java.io.OutputStream
     */
    public static void transfer(InputStream inputStream, OutputStream outputStream) {
        try {
            IoStreamHelper.transfer(inputStream, outputStream, false);
        } catch (IoStreamTransferException exception) {
            log.error("It is failed during transferring from inputStream to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.String
     */
    public static String read(InputStream inputStream) {
        try {
            return IoStreamHelper.read(inputStream);
        } catch (IoStreamReadException exception) {
            log.error("It is failed during reading of inputStream! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>bytes</code>
     * <p>the method.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @return byte <p>the return object is <code>byte</code> type.</p>
     * @see java.io.InputStream
     */
    public static byte[] bytes(InputStream inputStream) {
        try {
            return IoStreamHelper.bytes(inputStream);
        } catch (IoStreamReadException exception) {
            log.error("It is failed during reading of inputStream! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @param string       {@link java.lang.String} <p>the string parameter is <code>String</code> type.</p>
     * @see java.io.OutputStream
     * @see java.lang.String
     */
    public static void write(OutputStream outputStream, String string) {
        try {
            IoStreamHelper.write(outputStream, string);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when string write to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param file     {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.io.File
     */
    public static void write(HttpServletResponse response, File file) {
        try {
            IoStreamHelper.write(response, file);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when file write to response! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param json     {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.lang.String
     */
    public static void write(HttpServletResponse response, String json) {
        try {
            IoStreamHelper.write(response, json);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when json write to response! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param data     byte <p>the data parameter is <code>byte</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     */
    public static void write(HttpServletResponse response, byte[] data) {
        try {
            IoStreamHelper.write(response, data);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when data write to response! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param file     {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.io.File
     * @see java.lang.String
     */
    public static void write(HttpServletResponse response, File file, String filename) {
        String fileName = new String(filename.trim().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        try {
            fileName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ignored) {
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ";" + "filename*=utf-8''" + fileName);
        response.addHeader("Content-Length", "" + file.length());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(FileUtils.mediaType(filename).toString());
        write(response, file);
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @param data         byte <p>the data parameter is <code>byte</code> type.</p>
     * @see java.io.OutputStream
     */
    public static void write(OutputStream outputStream, byte[] data) {
        try {
            IoStreamHelper.write(outputStream, data);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when data write to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @param inputStream  {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @see java.io.OutputStream
     * @see java.io.InputStream
     */
    public static void write(OutputStream outputStream, InputStream inputStream) {
        try {
            IoStreamHelper.write(outputStream, inputStream);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when inputStream write to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param file        {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @see java.io.File
     * @see java.io.InputStream
     */
    public static void write(File file, InputStream inputStream) {
        try {
            IoStreamHelper.write(file, inputStream);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when inputStream write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param filename    {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @see java.lang.String
     * @see java.io.InputStream
     */
    public static void write(String filename, InputStream inputStream) {
        try {
            IoStreamHelper.write(filename, inputStream);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when inputStream write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param data     byte <p>the data parameter is <code>byte</code> type.</p>
     * @see java.lang.String
     */
    public static void write(String filename, byte[] data) {
        try {
            IoStreamHelper.write(filename, data);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when data write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }


    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param file {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @param data byte <p>the data parameter is <code>byte</code> type.</p>
     * @see java.io.File
     */
    public static void write(File file, byte[] data) {
        try {
            IoStreamHelper.write(file, data);
        } catch (IoStreamWriteException exception) {
            log.error("It is failed when bytes write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

}
