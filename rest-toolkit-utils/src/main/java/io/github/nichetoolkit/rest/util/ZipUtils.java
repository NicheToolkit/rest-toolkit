package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.error.often.ZipErrorException;
import io.github.nichetoolkit.rest.helper.ZipHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
     * <code>zipFile</code>
     * <p>The zip file method.</p>
     * @param zipPath  {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param file     {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.io.File} <p>The zip file return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     */
    public static File zipFile(String zipPath, String filename, File file) {
        try {
            return ZipHelper.zipFile(zipPath,filename,file);
        } catch (ZipErrorException | FileCreateException exception) {
            log.error("It is failed during handle zip file with filename and file path! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>zipFiles</code>
     * <p>The zip files method.</p>
     * @param zipPath  {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @return {@link java.io.File} <p>The zip files return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     * @see java.io.File
     */
    public static File zipFiles(String zipPath, String filename, List<File> zipFiles) {
        try {
            return ZipHelper.zipFiles(zipPath,filename,zipFiles);
        } catch (ZipErrorException | FileCreateException exception) {
            log.error("It is failed during handle zip files! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
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
        return unzip(file,unzipPath,GeneralUtils.uuid());
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
        return unzip(inputStream,unzipPath,GeneralUtils.uuid());
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
        return unzip(inputStream,unzipPath,GeneralUtils.uuid());
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
        return unzip(file,unzipPath,GeneralUtils.uuid());
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
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            return unzip(fileInputStream,unzipPath,filename);
        } catch (IOException exception) {
            log.error("It is failed during handle file! {}", exception.getMessage());
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
            return ZipHelper.unzip(inputStream,unzipPath,filename);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle unzip file! {}", exception.getMessage());
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }
}
