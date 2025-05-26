package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.error.often.ZipErrorException;
import io.github.nichetoolkit.rest.util.FileUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.IoStreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.*;

/**
 * <code>ZipHelper</code>
 * <p>The zip helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ZipHelper {

    /**
     * <code>zipFile</code>
     * <p>The zip file method.</p>
     * @param zipPath {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see  java.lang.String
     * @see  java.io.File
     * @see  java.lang.SuppressWarnings
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @see  io.github.nichetoolkit.rest.error.often.FileCreateException
     * @return  {@link java.io.File} <p>The zip file return object is <code>File</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public static File zipFile(String zipPath, String filename, File file) throws ZipErrorException, FileCreateException {
        String zipFilePath = zipPath.concat(File.separator).concat(filename)
                .concat(UtilConstants.SUFFIX_REGEX).concat(UtilConstants.ZIP_SUFFIX);
        if (filename.endsWith(UtilConstants.SUFFIX_REGEX.concat(UtilConstants.ZIP_SUFFIX))) {
            zipFilePath = zipPath.concat(File.separator).concat(filename);
        }
        File zipFile = FileHelper.createFile(zipFilePath);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            zipOutputStream.setComment(filename);
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                int temp;
                while ((temp = inputStream.read()) != -1) {
                    zipOutputStream.write(temp);
                }
            }
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return zipFile;
    }

    /**
     * <code>outputStream</code>
     * <p>The output stream method.</p>
     * @param zipPath {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @see  java.nio.file.Path
     * @see  java.util.zip.ZipOutputStream
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @see  io.github.nichetoolkit.rest.error.often.FileCreateException
     * @return  {@link java.util.zip.ZipOutputStream} <p>The output stream return object is <code>ZipOutputStream</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     */
    public static ZipOutputStream outputStream(Path zipPath) throws ZipErrorException, FileCreateException {
        try {
            File zipFile = zipPath.toFile();
            if (!zipFile.exists()) {
                FileHelper.createFile(zipPath);
            }
            return new ZipOutputStream(Files.newOutputStream(zipPath));
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
    }

    /**
     * <code>entry</code>
     * <p>The entry method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipOutputStream {@link java.util.zip.ZipOutputStream} <p>The zip output stream parameter is <code>ZipOutputStream</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.zip.ZipOutputStream
     * @see  java.util.zip.ZipEntry
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.util.zip.ZipEntry} <p>The entry return object is <code>ZipEntry</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static ZipEntry entry(String filename, ZipOutputStream zipOutputStream) throws ZipErrorException {
        try {
            ZipEntry zipEntry = new ZipEntry(filename);
            zipOutputStream.putNextEntry(zipEntry);
            return zipEntry;
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
    }

    /**
     * <code>flushAndClose</code>
     * <p>The flush and close method.</p>
     * @param zipOutputStream {@link java.util.zip.ZipOutputStream} <p>The zip output stream parameter is <code>ZipOutputStream</code> type.</p>
     * @see  java.util.zip.ZipOutputStream
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static void flushAndClose(ZipOutputStream zipOutputStream) throws ZipErrorException {
        try {
            zipOutputStream.flush();
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        } finally {
            CloseableHelper.close(zipOutputStream);
        }
    }


    /**
     * <code>zipFiles</code>
     * <p>The zip files method.</p>
     * @param zipPath {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.List
     * @see  java.io.File
     * @see  java.lang.SuppressWarnings
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @see  io.github.nichetoolkit.rest.error.often.FileCreateException
     * @return  {@link java.io.File} <p>The zip files return object is <code>File</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     */
    @SuppressWarnings("Duplicates")
    public static File zipFiles(String zipPath, String filename, List<File> zipFiles) throws ZipErrorException, FileCreateException {
        if (zipFiles.size() == 1) {
            return zipFile(zipPath, filename, zipFiles.stream().findFirst().get());
        }
        String zipFilePath = zipPath.concat(File.separator).concat(filename)
                .concat(UtilConstants.SUFFIX_REGEX).concat(UtilConstants.ZIP_SUFFIX);
        if (filename.endsWith(UtilConstants.SUFFIX_REGEX.concat(UtilConstants.ZIP_SUFFIX))) {
            zipFilePath = zipPath.concat(File.separator).concat(filename);
        }
        File zipFile = FileHelper.createFile(zipFilePath);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            zipOutputStream.setComment(filename);
            for (File file : zipFiles) {
                try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    int temp;
                    while ((temp = inputStream.read()) != -1) {
                        zipOutputStream.write(temp);
                    }
                }
            }
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return zipFile;
    }

    /**
     * <code>gzip</code>
     * <p>The gzip method.</p>
     * @param data byte <p>The data parameter is <code>byte</code> type.</p>
     * @return byte <p>The gzip return object is <code>byte</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     */
    public static byte[] gzip(byte[] data) throws ZipErrorException {
        byte[] bytes;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return bytes;
    }

    /**
     * <code>ungzip</code>
     * <p>The ungzip method.</p>
     * @param data byte <p>The data parameter is <code>byte</code> type.</p>
     * @return byte <p>The ungzip return object is <code>byte</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     */
    public static byte[] ungzip(byte[] data) throws ZipErrorException {
        byte[] bytes;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int num;
            while ((num = gzipInputStream.read(buf, 0, buf.length)) != -1) {
                byteArrayOutputStream.write(buf, 0, num);
            }
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return bytes;
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  java.nio.file.Path
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(File file) throws ZipErrorException {
        Path unzipPath = Paths.get(System.getProperty(UtilConstants.TEMP_SYSTEM_PROPERTY));
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.nio.file.Path
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(MultipartFile file) throws ZipErrorException {
        Path unzipPath = Paths.get(System.getProperty(UtilConstants.TEMP_SYSTEM_PROPERTY));
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.nio.file.Path
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(InputStream inputStream) throws ZipErrorException {
        Path unzipPath = Paths.get(System.getProperty(UtilConstants.TEMP_SYSTEM_PROPERTY));
        return unzip(inputStream, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.nio.file.Path
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(InputStream inputStream, Path unzipPath) throws ZipErrorException {
        return unzip(inputStream, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @see  java.io.File
     * @see  java.nio.file.Path
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(File file, Path unzipPath) throws ZipErrorException {
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.nio.file.Path
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(MultipartFile file, Path unzipPath) throws ZipErrorException {
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.nio.file.Path
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(MultipartFile file, Path unzipPath, String filename) throws ZipErrorException {
        try (InputStream inputStream = file.getInputStream()) {
            return unzip(inputStream, unzipPath, filename);
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @see  java.io.File
     * @see  java.nio.file.Path
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(File file, Path unzipPath, String filename) throws ZipErrorException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return unzip(fileInputStream, unzipPath, filename);
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @see  java.io.InputStream
     * @see  java.nio.file.Path
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @return  {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>The zip error exception is <code>ZipErrorException</code> type.</p>
     */
    public static Path unzip(InputStream inputStream, Path unzipPath, String filename) throws ZipErrorException {
        FileUtils.createFile(unzipPath.toFile());
        Path zipDirectory = Paths.get(unzipPath.toString(), filename);
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry = zipInputStream.getNextEntry();
            while (GeneralUtils.isNotEmpty(entry)) {
                Path filePath = Paths.get(zipDirectory.toString(), entry.getName());
                if (!entry.isDirectory()) {
                    Path parent = filePath.getParent();
                    if (!parent.toFile().exists()) {
                        Files.createDirectories(parent);
                    }
                    IoStreamUtils.writeOfUnclose(filePath, zipInputStream);
                } else {
                    Files.createDirectories(filePath);
                }
                zipInputStream.closeEntry();
                entry = zipInputStream.getNextEntry();
            }
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return zipDirectory;
    }

}
