package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.often.StreamReadException;
import io.github.nichetoolkit.rest.error.often.StreamTransferException;
import io.github.nichetoolkit.rest.error.often.StreamWriteException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>StreamHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamHelper {

    public static void transfer(MultipartFile multipartFile, String transferFilePath) throws StreamTransferException {
        File transferFile = new File(transferFilePath);
        transfer(multipartFile, transferFile);
    }

    public static void transfer(MultipartFile multipartFile, File transferFile) throws StreamTransferException {
        try {
            multipartFile.transferTo(transferFile);
        } catch (IOException exception) {
            throw new StreamTransferException(exception.getMessage());
        }
    }

    public static void transfer(InputStream inputStream, OutputStream outputStream, boolean isClose) throws StreamTransferException {
        try {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
                outputStream.flush();
            }
        } catch (IOException exception) {
            throw new StreamTransferException(exception.getMessage());
        } finally {
            if (isClose) {
                CloseableHelper.close(inputStream, outputStream);
            }
        }
    }

    public static String read(InputStream inputStream) throws StreamReadException {

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                stringBuilder.append(tempString);
            }
            return stringBuilder.toString();
        } catch (IOException exception) {
            throw new StreamReadException(exception.getMessage());
        }

    }

    public static byte[] bytes(InputStream inputStream) throws StreamReadException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException exception) {
            throw new StreamReadException(exception.getMessage());
        }

    }

    public static void write(OutputStream outputStream, String json) throws StreamWriteException {
        try {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        } finally {
            CloseableHelper.close(outputStream);
        }

    }

    public static void write(HttpServletResponse response, File file) throws StreamWriteException {
        try (FileInputStream inputStream = new FileInputStream(file);
             ServletOutputStream outputStream = response.getOutputStream()) {
             write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    public static void write(HttpServletResponse response, String json) throws StreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    public static void write(HttpServletResponse response, byte[] data) throws StreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()){
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    public static void write(OutputStream outputStream, byte[] data) throws StreamWriteException {
        InputStream inputStream = new ByteArrayInputStream(data);
        write(outputStream, inputStream);
    }

    public static void write(OutputStream outputStream, InputStream inputStream) throws StreamWriteException {
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        } finally {
            CloseableHelper.close(outputStream, inputStream);
        }
    }

    public static void write(File file, InputStream inputStream) throws StreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    public static void write(final String filename,InputStream inputStream) throws StreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    public static void write(File file, byte[] data) throws StreamWriteException {
        try ( OutputStream outputStream = Files.newOutputStream(file.toPath())){
            write(outputStream, data);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    public static void write(final String filename, byte[] data) throws StreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            write(outputStream, data);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }
}
