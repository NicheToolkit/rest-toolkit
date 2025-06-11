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
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoStreamHelper {

    public static void transfer(MultipartFile multipartFile, String transferFilePath) throws IoStreamTransferException {
        File transferFile = new File(transferFilePath);
        transfer(multipartFile, transferFile);
    }

    public static void transfer(MultipartFile multipartFile, File transferFile) throws IoStreamTransferException {
        try {
            multipartFile.transferTo(transferFile);
        } catch (IOException exception) {
            throw new IoStreamTransferException(exception.getMessage());
        }
    }

    public static void transfer(InputStream inputStream, OutputStream outputStream, boolean isClose) throws IoStreamTransferException {
        try {
            transferOfUnclose(inputStream, outputStream);
        } finally {
            if (isClose) {
                CloseableHelper.close(inputStream, outputStream);
            }
        }
    }

    public static void transferOfCloseInput(InputStream inputStream, OutputStream outputStream) throws IoStreamTransferException {
        try {
            transferOfUnclose(inputStream, outputStream);
        } finally {
            CloseableHelper.close(inputStream);
        }
    }

    public static void transferOfCloseOutput(InputStream inputStream, OutputStream outputStream) throws IoStreamTransferException {
        try {
            transferOfUnclose(inputStream, outputStream);
        } finally {
            CloseableHelper.close(outputStream);
        }
    }

    public static void transferOfCloseAll(InputStream inputStream, OutputStream outputStream) throws IoStreamTransferException {
        try {
            transferOfUnclose(inputStream, outputStream);
        } finally {
            CloseableHelper.close(inputStream, outputStream);
        }
    }

    public static void transferOfUnclose(InputStream inputStream, OutputStream outputStream) throws IoStreamTransferException {
        try {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
                outputStream.flush();
            }
        } catch (IOException exception) {
            throw new IoStreamTransferException(exception.getMessage());
        }
    }

    public static String read(InputStream inputStream) throws IoStreamReadException {
        return readOfCloseInput(inputStream);
    }

    public static String readOfCloseInput(InputStream inputStream) throws IoStreamReadException {
        try {
            return readOfUnclose(inputStream);
        } finally {
            CloseableHelper.close(inputStream);
        }
    }

    public static String readOfUnclose(InputStream inputStream) throws IoStreamReadException {
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

    public static byte[] bytes(MultipartFile file) throws IoStreamReadException {
        try {
            return file.getBytes();
        } catch (IOException exception) {
            throw new IoStreamReadException(exception.getMessage());
        }
    }


    public static byte[] bytes(File file) throws IoStreamReadException {
        try {
          return Files.readAllBytes(file.toPath());
        } catch (IOException exception) {
            throw new IoStreamReadException(exception.getMessage());
        }
    }

    public static byte[] bytes(Path filePath) throws IoStreamReadException {
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException exception) {
            throw new IoStreamReadException(exception.getMessage());
        }
    }

    public static byte[] bytes(InputStream inputStream) throws IoStreamReadException {
        return bytesOfUnclose(inputStream);
    }

    public static byte[] bytesOfCloseInput(InputStream inputStream) throws IoStreamReadException {
        try {
            return bytesOfUnclose(inputStream);
        } finally {
            CloseableHelper.close(inputStream);
        }
    }

    public static byte[] bytesOfUnclose(InputStream inputStream) throws IoStreamReadException {
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

    public static void write(OutputStream outputStream, String json) throws IoStreamWriteException {
        writeOfCloseOutput(outputStream, json);
    }

    public static void writeOfCloseOutput(OutputStream outputStream, String json) throws IoStreamWriteException {
        try {
            writeOfUnclose(outputStream, json);
        } finally {
            CloseableHelper.close(outputStream);
        }
    }

    public static void writeOfUnclose(OutputStream outputStream, String json) throws IoStreamWriteException {
        try {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(HttpServletResponse response, File file) throws IoStreamWriteException {
        try (FileInputStream inputStream = new FileInputStream(file);
             ServletOutputStream outputStream = response.getOutputStream()) {
            writeOfCloseAll(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfCloseResponse(HttpServletResponse response, File file) throws IoStreamWriteException {
        try (FileInputStream inputStream = new FileInputStream(file);
             ServletOutputStream outputStream = response.getOutputStream()) {
            writeOfUnclose(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfUnclose(HttpServletResponse response, File file) throws IoStreamWriteException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            ServletOutputStream outputStream = response.getOutputStream();
            writeOfUnclose(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(HttpServletResponse response, String json) throws IoStreamWriteException {
        writeOfCloseResponse(response, json);
    }

    public static void writeOfCloseResponse(HttpServletResponse response, String json) throws IoStreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfUnclose(HttpServletResponse response, String json) throws IoStreamWriteException {
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(HttpServletResponse response, byte[] data) throws IoStreamWriteException {
        writeOfCloseResponse(response, data);
    }

    public static void writeOfCloseResponse(HttpServletResponse response, byte[] data) throws IoStreamWriteException {
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfUnclose(HttpServletResponse response, byte[] data) throws IoStreamWriteException {
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(OutputStream outputStream, byte[] data) throws IoStreamWriteException {
        writeOfCloseOutput(outputStream, data);
    }

    public static void writeOfCloseOutput(OutputStream outputStream, byte[] data) throws IoStreamWriteException {
        InputStream inputStream = new ByteArrayInputStream(data);
        writeOfCloseAll(outputStream, inputStream);
    }

    public static void writeOfUnclose(OutputStream outputStream, byte[] data) throws IoStreamWriteException {
        InputStream inputStream = new ByteArrayInputStream(data);
        writeOfCloseInput(outputStream, inputStream);
    }

    public static void write(File file, InputStream inputStream) throws IoStreamWriteException {
        writeOfCloseInput(file, inputStream);
    }

    public static void writeOfCloseInput(File file, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            writeOfCloseInput(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfUnclose(File file, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            writeOfUnclose(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(final String filename, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            writeOfCloseAll(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfCloseInput(final Path filePath, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            writeOfCloseInput(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfCloseInput(final String filename, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            writeOfCloseInput(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfUnclose(final Path filePath, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            writeOfUnclose(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void writeOfUnclose(final String filename, InputStream inputStream) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            writeOfUnclose(outputStream, inputStream);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(File file, byte[] data) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
            write(outputStream, data);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(Path filePath, byte[] data) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            writeOfCloseOutput(outputStream, data);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(final String filename, byte[] data) throws IoStreamWriteException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(filename))) {
            write(outputStream, data);
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }

    public static void write(OutputStream outputStream, InputStream inputStream) throws IoStreamWriteException {
        try {
            writeOfUnclose(outputStream, inputStream);
        } finally {
            CloseableHelper.close(inputStream);
        }
    }

    public static void writeOfCloseOutput(OutputStream outputStream, InputStream inputStream) throws IoStreamWriteException {
        writeOfCloseAll(outputStream, inputStream);
    }

    public static void writeOfCloseInput(OutputStream outputStream, InputStream inputStream) throws IoStreamWriteException {
        try {
            writeOfUnclose(outputStream, inputStream);
        } finally {
            CloseableHelper.close(inputStream);
        }
    }

    public static void writeOfCloseAll(OutputStream outputStream, InputStream inputStream) throws IoStreamWriteException {
        try {
            writeOfUnclose(outputStream, inputStream);
        } finally {
            CloseableHelper.close(outputStream, inputStream);
        }
    }

    public static void writeOfUnclose(OutputStream outputStream, InputStream inputStream) throws IoStreamWriteException {
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (IOException exception) {
            throw new IoStreamWriteException(exception.getMessage());
        }
    }
}
