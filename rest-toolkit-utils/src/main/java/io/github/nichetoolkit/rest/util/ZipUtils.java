package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.error.often.ZipErrorException;
import io.github.nichetoolkit.rest.helper.ZipHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * <code>ZipUtils</code>
 * <p>The type zip utils class.</p>
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
     * <p>the file method.</p>
     * @param zipPath  {@link java.lang.String} <p>the zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param file     {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @return {@link java.io.File} <p>the file return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     */
    public static File zipFile(String zipPath, String filename, File file) {
        try {
            return ZipHelper.zipFile(zipPath,filename,file);
        } catch (ZipErrorException | FileCreateException exception) {
            log.error("It is failed during handle zip file! {}", exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * <code>zipFiles</code>
     * <p>the files method.</p>
     * @param zipPath  {@link java.lang.String} <p>the zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>the zip files parameter is <code>List</code> type.</p>
     * @return {@link java.io.File} <p>the files return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     * @see java.io.File
     */
    public static File zipFiles(String zipPath, String filename, List<File> zipFiles) {
        try {
            return ZipHelper.zipFiles(zipPath,filename,zipFiles);
        } catch (ZipErrorException | FileCreateException exception) {
            log.error("It is failed during handle zip files! {}", exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * <code>gzip</code>
     * <p>the method.</p>
     * @param bytes {@link byte} <p>the bytes parameter is <code>byte</code> type.</p>
     * @return {@link byte} <p>the return object is <code>byte</code> type.</p>
     */
    public static byte[] gzip(byte[] bytes) {
        try {
            return ZipHelper.gzip(bytes);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle zip file! {}", exception.getMessage());
            exception.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * <code>ungzip</code>
     * <p>the method.</p>
     * @param bytes {@link byte} <p>the bytes parameter is <code>byte</code> type.</p>
     * @return {@link byte} <p>the return object is <code>byte</code> type.</p>
     */
    public static byte[] ungzip(byte[] bytes) {
        try {
            return ZipHelper.ungzip(bytes);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle zip file! {}", exception.getMessage());
            exception.printStackTrace();
        }
        return new byte[0];
    }
}
