package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.error.often.IoStreamWriteException;
import io.github.nichetoolkit.rest.error.often.ZipErrorException;
import io.github.nichetoolkit.rest.helper.ZipHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <code>ZipUtils</code>
 * <p>The zip utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class ZipUtils {

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipPath     {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename    {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @see java.lang.String
     * @see java.io.InputStream
     */
    public static void zip(String zipPath, String filename, InputStream inputStream) {
        try {
            ZipHelper.zip(zipPath, filename, inputStream);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to path with filename and input stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipFile     {@link java.io.File} <p>The zip file parameter is <code>File</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @see java.io.File
     * @see java.io.InputStream
     */
    public static void zip(File zipFile, InputStream inputStream) {
        try {
            ZipHelper.zip(zipFile, inputStream);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip file and input stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipFile     {@link java.io.File} <p>The zip file parameter is <code>File</code> type.</p>
     * @param filename    {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @see java.io.File
     * @see java.lang.String
     * @see java.io.InputStream
     */
    public static void zip(File zipFile, String filename, InputStream inputStream) {
        try {
            ZipHelper.zip(zipFile, filename, inputStream);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip file with filename and input stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipPath     {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @see java.nio.file.Path
     * @see java.io.InputStream
     */
    public static void zip(Path zipPath, InputStream inputStream) {
        try {
            ZipHelper.zip(zipPath, inputStream);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip path with input stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipPath     {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @param filename    {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @see java.nio.file.Path
     * @see java.lang.String
     * @see java.io.InputStream
     */
    public static void zip(Path zipPath, String filename, InputStream inputStream) {
        try {
            ZipHelper.zip(zipPath, filename, inputStream);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip path with filename and input stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }


    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipFile {@link java.io.File} <p>The zip file parameter is <code>File</code> type.</p>
     * @param file    {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see java.io.File
     */
    public static void zip(File zipFile, File file) {
        try {
            ZipHelper.zip(zipFile, file);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip file with file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipPath  {@link java.io.File} <p>The zip path parameter is <code>File</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param file     {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see java.io.File
     * @see java.lang.String
     */
    public static void zip(File zipPath, String filename, File file) {
        try {
            ZipHelper.zip(zipPath, filename, file);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip file with filename and file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipPath {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @param file    {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see java.nio.file.Path
     * @see java.io.File
     */
    public static void zip(Path zipPath, File file) {
        try {
            ZipHelper.zip(zipPath, file);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip path with file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipPath  {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param file     {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see java.nio.file.Path
     * @see java.lang.String
     * @see java.io.File
     */
    public static void zip(Path zipPath, String filename, File file) {
        try {
            ZipHelper.zip(zipPath, filename, file);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip path with filename and file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipPath  {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param file     {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     */
    public static void zip(String zipPath, String filename, File file) {
        try {
            ZipHelper.zip(zipPath, filename, file);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to path with filename and file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zip</code>
     * <p>The zip method.</p>
     * @param zipOutputStream {@link java.util.zip.ZipOutputStream} <p>The zip output stream parameter is <code>ZipOutputStream</code> type.</p>
     * @param filename        {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param inputStream     {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @see java.util.zip.ZipOutputStream
     * @see java.lang.String
     * @see java.io.InputStream
     */
    public static void zip(ZipOutputStream zipOutputStream, String filename, InputStream inputStream) {
        try {
            ZipHelper.zip(zipOutputStream, filename, inputStream);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip output stream with filename and input stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zips</code>
     * <p>The zips method.</p>
     * @param zipPath  {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     */
    public static void zips(String zipPath, String filename, List<File> zipFiles) {
        try {
            ZipHelper.zips(zipPath, filename, zipFiles);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to path with filename and zip files! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zips</code>
     * <p>The zips method.</p>
     * @param zipPath  {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @see java.nio.file.Path
     * @see java.util.List
     */
    public static void zips(Path zipPath, List<File> zipFiles) {
        try {
            ZipHelper.zips(zipPath, zipFiles);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip path with zip files! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zips</code>
     * <p>The zips method.</p>
     * @param zipPath  {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @see java.nio.file.Path
     * @see java.lang.String
     * @see java.util.List
     */
    public static void zips(Path zipPath, String filename, List<File> zipFiles) {
        try {
            ZipHelper.zips(zipPath, filename, zipFiles);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip path with filename and zip files! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zips</code>
     * <p>The zips method.</p>
     * @param zipFile  {@link java.io.File} <p>The zip file parameter is <code>File</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @see java.io.File
     * @see java.util.List
     */
    public static void zips(File zipFile, List<File> zipFiles) {
        try {
            ZipHelper.zips(zipFile, zipFiles);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip file with zip files! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>zips</code>
     * <p>The zips method.</p>
     * @param zipFile  {@link java.io.File} <p>The zip file parameter is <code>File</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @see java.io.File
     * @see java.lang.String
     * @see java.util.List
     */
    public static void zips(File zipFile, String filename, List<File> zipFiles) {
        try {
            ZipHelper.zips(zipFile, filename, zipFiles);
        } catch (ZipErrorException | IoStreamWriteException exception) {
            log.error("It is failed during handle file to zip file with filename and zip files! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>outputStream</code>
     * <p>The output stream method.</p>
     * @param zipPath {@link java.nio.file.Path} <p>The zip path parameter is <code>Path</code> type.</p>
     * @return {@link java.util.zip.ZipOutputStream} <p>The output stream return object is <code>ZipOutputStream</code> type.</p>
     * @see java.nio.file.Path
     * @see java.util.zip.ZipOutputStream
     */
    public static ZipOutputStream outputStream(Path zipPath) {
        try {
            return ZipHelper.outputStream(zipPath);
        } catch (ZipErrorException | FileCreateException exception) {
            log.error("It is failed during handle output stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>entry</code>
     * <p>The entry method.</p>
     * @param filename        {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipOutputStream {@link java.util.zip.ZipOutputStream} <p>The zip output stream parameter is <code>ZipOutputStream</code> type.</p>
     * @return {@link java.util.zip.ZipEntry} <p>The entry return object is <code>ZipEntry</code> type.</p>
     * @see java.lang.String
     * @see java.util.zip.ZipOutputStream
     * @see java.util.zip.ZipEntry
     */
    public static ZipEntry entry(String filename, ZipOutputStream zipOutputStream) {
        try {
            return ZipHelper.entry(filename, zipOutputStream);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle zip entry! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>flushAndClose</code>
     * <p>The flush and close method.</p>
     * @param outputStream {@link java.util.zip.ZipOutputStream} <p>The output stream parameter is <code>ZipOutputStream</code> type.</p>
     * @see java.util.zip.ZipOutputStream
     */
    public static void flushAndClose(ZipOutputStream outputStream) {
        try {
            ZipHelper.flushAndClose(outputStream);
        } catch (ZipErrorException exception) {
            log.error("It is failed during flush and close output stream! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>gzip</code>
     * <p>The gzip method.</p>
     * @param bytes byte <p>The bytes parameter is <code>byte</code> type.</p>
     * @return byte <p>The gzip return object is <code>byte</code> type.</p>
     */
    public static byte[] gzip(byte[] bytes) {
        try {
            return ZipHelper.gzip(bytes);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle zip file with file bytes! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return new byte[0];
    }

    /**
     * <code>ungzip</code>
     * <p>The ungzip method.</p>
     * @param bytes byte <p>The bytes parameter is <code>byte</code> type.</p>
     * @return byte <p>The ungzip return object is <code>byte</code> type.</p>
     */
    public static byte[] ungzip(byte[] bytes) {
        try {
            return ZipHelper.ungzip(bytes);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle unzip file with file bytes! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return new byte[0];
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see java.io.File
     * @see java.nio.file.Path
     */
    public static Path unzip(File file) {
        Path unzipPath = Paths.get(System.getProperty(UtilConstants.TEMP_SYSTEM_PROPERTY));
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.nio.file.Path
     */
    public static Path unzip(MultipartFile file) {
        Path unzipPath = Paths.get(System.getProperty(UtilConstants.TEMP_SYSTEM_PROPERTY));
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see java.io.InputStream
     * @see java.nio.file.Path
     */
    public static Path unzip(InputStream inputStream) {
        Path unzipPath = Paths.get(System.getProperty(UtilConstants.TEMP_SYSTEM_PROPERTY));
        return unzip(inputStream, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param unzipPath   {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see java.io.InputStream
     * @see java.nio.file.Path
     */
    public static Path unzip(InputStream inputStream, Path unzipPath) {
        return unzip(inputStream, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file      {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see java.io.File
     * @see java.nio.file.Path
     */
    public static Path unzip(File file, Path unzipPath) {
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file      {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.nio.file.Path
     */
    public static Path unzip(MultipartFile file, Path unzipPath) {
        return unzip(file, unzipPath, GeneralUtils.uuid());
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file      {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @param filename  {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see java.io.File
     * @see java.nio.file.Path
     * @see java.lang.String
     */
    public static Path unzip(File file, Path unzipPath, String filename) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return unzip(fileInputStream, unzipPath, filename);
        } catch (IOException exception) {
            log.error("It is failed during handle file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param file      {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @param unzipPath {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @param filename  {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.nio.file.Path
     * @see java.lang.String
     */
    public static Path unzip(MultipartFile file, Path unzipPath, String filename) {
        try (InputStream inputStream = file.getInputStream()) {
            return unzip(inputStream, unzipPath, filename);
        } catch (IOException exception) {
            log.error("It is failed during handle multipart file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>unzip</code>
     * <p>The unzip method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param unzipPath   {@link java.nio.file.Path} <p>The unzip path parameter is <code>Path</code> type.</p>
     * @param filename    {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The unzip return object is <code>Path</code> type.</p>
     * @see java.io.InputStream
     * @see java.nio.file.Path
     * @see java.lang.String
     */
    public static Path unzip(InputStream inputStream, Path unzipPath, String filename) {
        try {
            return ZipHelper.unzip(inputStream, unzipPath, filename);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle unzip file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }
}
