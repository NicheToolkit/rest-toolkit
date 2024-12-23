package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.error.often.IoStreamReadException;
import io.github.nichetoolkit.rest.error.often.IoStreamTransferException;
import io.github.nichetoolkit.rest.error.often.IoStreamWriteException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <code>IoStreamHelper</code>
 * <p>The io stream helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class IoStreamHelper {

    /**
     * <code>transfer</code>
     * <p>The transfer method.</p>
     * @param multipartFile    {@link org.springframework.web.multipart.MultipartFile} <p>The multipart file parameter is <code>MultipartFile</code> type.</p>
     * @param transferFilePath {@link java.lang.String} <p>The transfer file path parameter is <code>String</code> type.</p>
     * @throws IoStreamTransferException {@link io.github.nichetoolkit.rest.error.often.IoStreamTransferException} <p>The io stream transfer exception is <code>IoStreamTransferException</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.IoStreamTransferException
     */
    public static void transfer(MultipartFile multipartFile, String transferFilePath) throws IoStreamTransferException {
        File transferFile = new File(transferFilePath);
        transfer(multipartFile, transferFile);
    }

    /**
     * <code>transfer</code>
     * <p>The transfer method.</p>
     * @param multipartFile {@link org.springframework.web.multipart.MultipartFile} <p>The multipart file parameter is <code>MultipartFile</code> type.</p>
     * @param transferFile  {@link java.io.File} <p>The transfer file parameter is <code>File</code> type.</p>
     * @throws IoStreamTransferException {@link io.github.nichetoolkit.rest.error.often.IoStreamTransferException} <p>The io stream transfer exception is <code>IoStreamTransferException</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.IoStreamTransferException
     */
    public static void transfer(MultipartFile multipartFile, File transferFile) throws IoStreamTransferException {
        try {
            multipartFile.transferTo(transferFile);
        } catch (IOException exception) {
            throw new IoStreamTransferException(exception.getMessage());
        }
    }

    /**
     * <code>transfer</code>
     * <p>The transfer method.</p>
     * @param inputStream  {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @param isClose      boolean <p>The is close parameter is <code>boolean</code> type.</p>
     * @throws IoStreamTransferException {@link io.github.nichetoolkit.rest.error.often.IoStreamTransferException} <p>The io stream transfer exception is <code>IoStreamTransferException</code> type.</p>
     * @see java.io.InputStream
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.often.IoStreamTransferException
     */
    public static void transfer(InputStream inputStream, OutputStream outputStream, boolean isClose) throws IoStreamTransferException {
        try {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
                outputStream.flush();
            }
        } catch (IOException exception) {
            throw new IoStreamTransferException(exception.getMessage());
        } finally {
            if (isClose) {
                CloseableHelper.close(inputStream, outputStream);
            }
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @return {@link java.lang.String} <p>The read return object is <code>String</code> type.</p>
     * @throws IoStreamReadException {@link io.github.nichetoolkit.rest.error.often.IoStreamReadException} <p>The io stream read exception is <code>IoStreamReadException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.IoStreamReadException
     */
    public static String read(InputStream inputStream) throws IoStreamReadException {

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                stringBuilder.append(tempString);
            }
            return stringBuilder.toString();
        } catch (IOException exception) {
            throw new IoStreamReadException(exception.getMessage());
        }

    }

    /**
     * <code>bytes</code>
     * <p>The bytes method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @return byte <p>The bytes return object is <code>byte</code> type.</p>
     * @throws IoStreamReadException {@link io.github.nichetoolkit.rest.error.often.IoStreamReadException} <p>The io stream read exception is <code>IoStreamReadException</code> type.</p>
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.IoStreamReadException
     */
    public static byte[] bytes(InputStream inputStream) throws IoStreamReadException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException exception) {
            throw new IoStreamReadException(exception.getMessage());
        }

    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @param json         {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(OutputStream outputStream, String json) throws IoStreamWriteException {
        try {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        } finally {
            CloseableHelper.close(outputStream);
        }

    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param file     {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(HttpServletResponse response, File file) throws IoStreamWriteException {
        try (FileInputStream inputStream = new FileInputStream(file);
             ServletOutputStream outputStream = response.getOutputStream()) {
             write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param json     {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(HttpServletResponse response, String json) throws IoStreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param data     byte <p>The data parameter is <code>byte</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(HttpServletResponse response, byte[] data) throws IoStreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()){
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @param data         byte <p>The data parameter is <code>byte</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(OutputStream outputStream, byte[] data) throws IoStreamWriteException {
        InputStream inputStream = new ByteArrayInputStream(data);
        write(outputStream, inputStream);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @param inputStream  {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(OutputStream outputStream, InputStream inputStream) throws IoStreamWriteException {
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        } finally {
            CloseableHelper.close(outputStream, inputStream);
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param file        {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see java.io.File
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(File file, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param filename    {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see java.lang.String
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(final String filename,InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param data byte <p>The data parameter is <code>byte</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(File file, byte[] data) throws IoStreamWriteException {
        try ( OutputStream outputStream = Files.newOutputStream(file.toPath())){
            write(outputStream, data);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param data     byte <p>The data parameter is <code>byte</code> type.</p>
     * @throws IoStreamWriteException {@link io.github.nichetoolkit.rest.error.often.IoStreamWriteException} <p>The io stream write exception is <code>IoStreamWriteException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.IoStreamWriteException
     */
    public static void write(final String filename, byte[] data) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            write(outputStream, data);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }
}
