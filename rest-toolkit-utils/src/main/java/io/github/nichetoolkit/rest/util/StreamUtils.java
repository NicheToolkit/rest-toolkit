package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.often.StreamReadException;
import io.github.nichetoolkit.rest.error.often.StreamTransferException;
import io.github.nichetoolkit.rest.error.often.StreamWriteException;
import io.github.nichetoolkit.rest.helper.StreamHelper;
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
 * <p>StreamUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class StreamUtils {

    public static void transfer(MultipartFile multipartFile, String transferFilePath) {
        try {
            StreamHelper.transfer(multipartFile, transferFilePath);
        } catch (StreamTransferException exception) {
            log.error("It is failed during transferring from multipart file to file path! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void transfer(MultipartFile multipartFile, File transferFile) {
        try {
            StreamHelper.transfer(multipartFile, transferFile);
        } catch (StreamTransferException exception) {
            log.error("It is failed during transferring from multipart file to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void transfer(InputStream inputStream, OutputStream outputStream) {
        try {
            StreamHelper.transfer(inputStream, outputStream, false);
        } catch (StreamTransferException exception) {
            log.error("It is failed during transferring from inputStream to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static String read(InputStream inputStream) {
        try {
            return StreamHelper.read(inputStream);
        } catch (StreamReadException exception) {
            log.error("It is failed during reading of inputStream! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public static byte[] bytes(InputStream inputStream) {
        try {
            return StreamHelper.bytes(inputStream);
        } catch (StreamReadException exception) {
            log.error("It is failed during reading of inputStream! {}", exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public static void write(OutputStream outputStream, String string) {
        try {
            StreamHelper.write(outputStream, string);
        } catch (StreamWriteException exception) {
            log.error("It is failed when string write to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void write(HttpServletResponse response, File file) {
        try {
            StreamHelper.write(response, file);
        } catch (StreamWriteException exception) {
            log.error("It is failed when file write to response! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void write(HttpServletResponse response, String json) {
        try {
            StreamHelper.write(response, json);
        } catch (StreamWriteException exception) {
            log.error("It is failed when json write to response! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void write(HttpServletResponse response, byte[] data) {
        try {
            StreamHelper.write(response, data);
        } catch (StreamWriteException exception) {
            log.error("It is failed when data write to response! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

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

    public static void write(OutputStream outputStream, byte[] data) {
        try {
            StreamHelper.write(outputStream, data);
        } catch (StreamWriteException exception) {
            log.error("It is failed when data write to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void write(OutputStream outputStream, InputStream inputStream) {
        try {
            StreamHelper.write(outputStream, inputStream);
        } catch (StreamWriteException exception) {
            log.error("It is failed when inputStream write to outputStream! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void write(File file, InputStream inputStream) {
        try {
            StreamHelper.write(file, inputStream);
        } catch (StreamWriteException exception) {
            log.error("It is failed when inputStream write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void write(String filename, InputStream inputStream) {
        try {
            StreamHelper.write(filename, inputStream);
        } catch (StreamWriteException exception) {
            log.error("It is failed when inputStream write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void write(String filename, byte[] data) {
        try {
            StreamHelper.write(filename, data);
        } catch (StreamWriteException exception) {
            log.error("It is failed when data write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }


    public static void write(File file, byte[] data) {
        try {
            StreamHelper.write(file, data);
        } catch (StreamWriteException exception) {
            log.error("It is failed when bytes write to file! {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

}
