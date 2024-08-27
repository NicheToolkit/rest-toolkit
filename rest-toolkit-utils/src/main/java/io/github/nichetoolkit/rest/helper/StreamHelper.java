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
 * <code>StreamHelper</code>
 * <p>The type stream helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class StreamHelper {

    /**
     * <code>transfer</code>
     * <p>the method.</p>
     * @param multipartFile    {@link org.springframework.web.multipart.MultipartFile} <p>the multipart file parameter is <code>MultipartFile</code> type.</p>
     * @param transferFilePath {@link java.lang.String} <p>the transfer file path parameter is <code>String</code> type.</p>
     * @throws StreamTransferException {@link io.github.nichetoolkit.rest.error.often.StreamTransferException} <p>the stream transfer exception is <code>StreamTransferException</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.StreamTransferException
     */
    public static void transfer(MultipartFile multipartFile, String transferFilePath) throws StreamTransferException {
        File transferFile = new File(transferFilePath);
        transfer(multipartFile, transferFile);
    }

    /**
     * <code>transfer</code>
     * <p>the method.</p>
     * @param multipartFile {@link org.springframework.web.multipart.MultipartFile} <p>the multipart file parameter is <code>MultipartFile</code> type.</p>
     * @param transferFile  {@link java.io.File} <p>the transfer file parameter is <code>File</code> type.</p>
     * @throws StreamTransferException {@link io.github.nichetoolkit.rest.error.often.StreamTransferException} <p>the stream transfer exception is <code>StreamTransferException</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.StreamTransferException
     */
    public static void transfer(MultipartFile multipartFile, File transferFile) throws StreamTransferException {
        try {
            multipartFile.transferTo(transferFile);
        } catch (IOException exception) {
            throw new StreamTransferException(exception.getMessage());
        }
    }

    /**
     * <code>transfer</code>
     * <p>the method.</p>
     * @param inputStream  {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @param isClose      {@link boolean} <p>the is close parameter is <code>boolean</code> type.</p>
     * @throws StreamTransferException {@link io.github.nichetoolkit.rest.error.often.StreamTransferException} <p>the stream transfer exception is <code>StreamTransferException</code> type.</p>
     * @see java.io.InputStream
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.often.StreamTransferException
     */
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

    /**
     * <code>read</code>
     * <p>the method.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @throws StreamReadException {@link io.github.nichetoolkit.rest.error.often.StreamReadException} <p>the stream read exception is <code>StreamReadException</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.StreamReadException
     */
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

    /**
     * <code>bytes</code>
     * <p>the method.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @return {@link byte} <p>the return object is <code>byte</code> type.</p>
     * @throws StreamReadException {@link io.github.nichetoolkit.rest.error.often.StreamReadException} <p>the stream read exception is <code>StreamReadException</code> type.</p>
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.StreamReadException
     */
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

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @param json         {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
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

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param file     {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(HttpServletResponse response, File file) throws StreamWriteException {
        try (FileInputStream inputStream = new FileInputStream(file);
             ServletOutputStream outputStream = response.getOutputStream()) {
             write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param json     {@link java.lang.String} <p>the json parameter is <code>String</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(HttpServletResponse response, String json) throws StreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>the response parameter is <code>HttpServletResponse</code> type.</p>
     * @param data     {@link byte} <p>the data parameter is <code>byte</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see javax.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(HttpServletResponse response, byte[] data) throws StreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()){
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @param data         {@link byte} <p>the data parameter is <code>byte</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(OutputStream outputStream, byte[] data) throws StreamWriteException {
        InputStream inputStream = new ByteArrayInputStream(data);
        write(outputStream, inputStream);
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @param inputStream  {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
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

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param file        {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see java.io.File
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(File file, InputStream inputStream) throws StreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param filename    {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>the input stream parameter is <code>InputStream</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see java.lang.String
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(final String filename,InputStream inputStream) throws StreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            write(outputStream, inputStream);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param file {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @param data {@link byte} <p>the data parameter is <code>byte</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(File file, byte[] data) throws StreamWriteException {
        try ( OutputStream outputStream = Files.newOutputStream(file.toPath())){
            write(outputStream, data);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param data     {@link byte} <p>the data parameter is <code>byte</code> type.</p>
     * @throws StreamWriteException {@link io.github.nichetoolkit.rest.error.often.StreamWriteException} <p>the stream write exception is <code>StreamWriteException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.StreamWriteException
     */
    public static void write(final String filename, byte[] data) throws StreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            write(outputStream, data);
        } catch (IOException exception) {
            throw new StreamWriteException(exception.getMessage());
        }
    }
}
