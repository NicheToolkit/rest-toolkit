package io.github.nichetoolkit.rest.helper;


import io.github.nichetoolkit.rest.error.often.FileCopyException;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.util.NameUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Map;

/**
 * <code>FileHelper</code>
 * <p>The type file helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class FileHelper {

    /**
     * <code>createFile</code>
     * <p>the file method.</p>
     * @param path {@link java.lang.String} <p>the path parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>the file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>the file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path) throws FileCreateException {
        return createFile(new File(path));
    }

    /**
     * <code>createFile</code>
     * <p>the file method.</p>
     * @param path {@link java.lang.String} <p>the path parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>the file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>the file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path, final String name) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(name);
        return createFile(new File(filePath));
    }

    /**
     * <code>createFile</code>
     * <p>the file method.</p>
     * @param path    {@link java.lang.String} <p>the path parameter is <code>String</code> type.</p>
     * @param nameMap {@link java.util.Map} <p>the name map parameter is <code>Map</code> type.</p>
     * @return {@link java.io.File} <p>the file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>the file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path, final Map<String, String> nameMap) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(nameMap.get(NameUtils.NAME)).concat(nameMap.get(NameUtils.EXT));
        return createFile(new File(filePath));
    }

    /**
     * <code>createFile</code>
     * <p>the file method.</p>
     * @param path {@link java.lang.String} <p>the path parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param ext  {@link java.lang.String} <p>the ext parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>the file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>the file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path, final String name, final String ext) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(name).concat(ext);
        return createFile(new File(filePath));
    }

    /**
     * <code>createFile</code>
     * <p>the file method.</p>
     * @param file {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @return {@link java.io.File} <p>the file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>the file create exception is <code>FileCreateException</code> type.</p>
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final File file) throws FileCreateException {
        if (file.exists()) {
            return file;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException exception) {
            throw new FileCreateException(exception.getMessage());
        }
        return file;
    }

    /**
     * <code>copyFile</code>
     * <p>the file method.</p>
     * @param srcFile    {@link java.io.File} <p>the src file parameter is <code>File</code> type.</p>
     * @param targetFile {@link java.io.File} <p>the target file parameter is <code>File</code> type.</p>
     * @throws FileCopyException {@link io.github.nichetoolkit.rest.error.often.FileCopyException} <p>the file copy exception is <code>FileCopyException</code> type.</p>
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCopyException
     */
    public static void copyFile(final File srcFile, final File targetFile) throws FileCopyException {
        try (
                FileInputStream fileInputStream = new FileInputStream(srcFile);
                FileChannel input = fileInputStream.getChannel();
                FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
                FileChannel output = fileOutputStream.getChannel()) {
            output.transferFrom(input, 0, input.size());
        } catch (IOException exception) {
            throw new FileCopyException(exception.getMessage());
        }
    }

    /**
     * <code>copyFile</code>
     * <p>the file method.</p>
     * @param srcPath    {@link java.lang.String} <p>the src path parameter is <code>String</code> type.</p>
     * @param targetPath {@link java.lang.String} <p>the target path parameter is <code>String</code> type.</p>
     * @throws FileCopyException {@link io.github.nichetoolkit.rest.error.often.FileCopyException} <p>the file copy exception is <code>FileCopyException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.FileCopyException
     */
    public static void copyFile(final String srcPath, final String targetPath) throws FileCopyException {
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath);
        copyFile(srcFile, targetFile);
    }

    /**
     * <code>deleteFile</code>
     * <p>the file method.</p>
     * @param path {@link java.lang.String} <p>the path parameter is <code>String</code> type.</p>
     * @return {@link boolean} <p>the file return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    public static boolean deleteFile(final String path) {
        File file = new File(path);
        return deleteFile(file);
    }

    /**
     * <code>deleteFile</code>
     * <p>the file method.</p>
     * @param file {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @return {@link boolean} <p>the file return object is <code>boolean</code> type.</p>
     * @see java.io.File
     */
    public static boolean deleteFile(final File file) {
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return true;
        }
    }

    /**
     * <code>clearFile</code>
     * <p>the file method.</p>
     * @param path {@link java.lang.String} <p>the path parameter is <code>String</code> type.</p>
     * @return {@link boolean} <p>the file return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    public static boolean clearFile(final String path) {
        boolean flag = true;
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        String[] tempList = file.list();
        String tempPath;
        for (int i = 0; tempList != null && i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                tempPath = path.concat(tempList[i]);
            } else {
                tempPath = path.concat(File.separator).concat(tempList[i]);
            }
            File temp = new File(tempPath);
            if (temp.isFile()) {
                flag = flag && temp.delete();
            } else if (temp.isDirectory()) {
                String subPath = path.concat(File.separator).concat(tempList[i]);
                flag = flag && clearFile(subPath);
            }
        }
        return flag && file.delete();
    }
}
