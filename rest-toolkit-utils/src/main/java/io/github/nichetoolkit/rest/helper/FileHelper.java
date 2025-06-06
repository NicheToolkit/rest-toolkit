package io.github.nichetoolkit.rest.helper;


import io.github.nichetoolkit.rest.error.often.FileCopyException;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.util.NameUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * <code>FileHelper</code>
 * <p>The file helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class FileHelper {

    /**
     * <code>createTempFile</code>
     * <p>The create temp file method.</p>
     * @param path   {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param suffix {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The create temp file return object is <code>Path</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.nio.file.Path
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static Path createTempFile(Path path, String prefix, String suffix) throws FileCreateException {
        try {
            return Files.createTempFile(path, prefix, suffix);
        } catch (IOException exception) {
            throw new FileCreateException(exception.getMessage());
        }
    }

    /**
     * <code>createTempFile</code>
     * <p>The create temp file method.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param suffix {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The create temp file return object is <code>Path</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.nio.file.Path
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static Path createTempFile(String prefix, String suffix) throws FileCreateException {
        try {
            return Files.createTempFile(prefix, suffix);
        } catch (IOException exception) {
            throw new FileCreateException(exception.getMessage());
        }
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path) throws FileCreateException {
        return createFile(Paths.get(path));
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.nio.file.Path
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final Path path) throws FileCreateException {
        return createFile(path.toFile());
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path, final String name) throws FileCreateException {
        return createFile(Paths.get(path, name));
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path    {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @param nameMap {@link java.util.Map} <p>The name map parameter is <code>Map</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path, final Map<String, String> nameMap) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(nameMap.get(NameUtils.NAME)).concat(nameMap.get(NameUtils.EXT));
        return createFile(Paths.get(filePath));
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param ext  {@link java.lang.String} <p>The ext parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File createFile(final String path, final String name, final String ext) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(name).concat(ext);
        return createFile(Paths.get(filePath));
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>The file create exception is <code>FileCreateException</code> type.</p>
     * @see java.io.File
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
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
     * <p>The copy file method.</p>
     * @param srcFile    {@link java.io.File} <p>The src file parameter is <code>File</code> type.</p>
     * @param targetFile {@link java.io.File} <p>The target file parameter is <code>File</code> type.</p>
     * @throws FileCopyException {@link io.github.nichetoolkit.rest.error.often.FileCopyException} <p>The file copy exception is <code>FileCopyException</code> type.</p>
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
     * <p>The copy file method.</p>
     * @param srcPath    {@link java.lang.String} <p>The src path parameter is <code>String</code> type.</p>
     * @param targetPath {@link java.lang.String} <p>The target path parameter is <code>String</code> type.</p>
     * @throws FileCopyException {@link io.github.nichetoolkit.rest.error.often.FileCopyException} <p>The file copy exception is <code>FileCopyException</code> type.</p>
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
     * <p>The delete file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return boolean <p>The delete file return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    public static boolean deleteFile(final String path) {
        File file = new File(path);
        return deleteFile(file);
    }

    /**
     * <code>deleteFile</code>
     * <p>The delete file method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return boolean <p>The delete file return object is <code>boolean</code> type.</p>
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
     * <p>The clear file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return boolean <p>The clear file return object is <code>boolean</code> type.</p>
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
