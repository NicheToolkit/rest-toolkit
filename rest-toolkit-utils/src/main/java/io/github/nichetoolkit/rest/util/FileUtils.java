package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.often.FileCopyException;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.helper.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;

/**
 * <code>FileUtils</code>
 * <p>The file utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class FileUtils {

    /**
     * <code>resource</code>
     * <p>The resource method.</p>
     * @param resourcePath {@link java.lang.String} <p>The resource path parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The resource return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String resource(String resourcePath) {
        try {
            File resourceFile = ResourceUtils.getFile(resourcePath);
            return resourceFile.getAbsolutePath();
        } catch (FileNotFoundException ignored) {
            return resourcePath;
        }
    }

    /**
     * <code>createTempFile</code>
     * <p>The create temp file method.</p>
     * @param path   {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param suffix {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The create temp file return object is <code>Path</code> type.</p>
     * @see java.nio.file.Path
     * @see java.lang.String
     */
    public static Path createTempFile(Path path, String prefix, String suffix) {
        try {
            return FileHelper.createTempFile(path,prefix,suffix);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating temp file!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>createTempFile</code>
     * <p>The create temp file method.</p>
     * @param prefix {@link java.lang.String} <p>The prefix parameter is <code>String</code> type.</p>
     * @param suffix {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.nio.file.Path} <p>The create temp file return object is <code>Path</code> type.</p>
     * @see java.lang.String
     * @see java.nio.file.Path
     */
    public static Path createTempFile(String prefix, String suffix) {
        try {
            return FileHelper.createTempFile(prefix,suffix);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating temp file!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     */
    public static File createFile(final String path) {
        try {
            return FileHelper.createFile(path);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @see java.nio.file.Path
     * @see java.io.File
     */
    public static File createFile(final Path path) {
        try {
            return FileHelper.createFile(path);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file with path!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     */
    public static File createFile(final String path, final String name) {
        try {
            return FileHelper.createFile(path, name);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file with name!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path    {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @param nameMap {@link java.util.Map} <p>The name map parameter is <code>Map</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.util.Map
     * @see java.io.File
     */
    public static File createFile(final String path, Map<String, String> nameMap) {
        try {
            return FileHelper.createFile(path, nameMap);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file with nameMap!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param ext  {@link java.lang.String} <p>The ext parameter is <code>String</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     */
    public static File createFile(final String path, final String name, final String ext) {
        try {
            return FileHelper.createFile(path, name, ext);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }

    /**
     * <code>createFile</code>
     * <p>The create file method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.io.File} <p>The create file return object is <code>File</code> type.</p>
     * @see java.io.File
     */
    public static File createFile(final File file) {
        try {
            return FileHelper.createFile(file);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating of file!", exception);
            GeneralUtils.printStackTrace(exception);
        }
        return null;
    }


    /**
     * <code>listFiles</code>
     * <p>The list files method.</p>
     * @param path           {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @param filenameFilter {@link java.io.FilenameFilter} <p>The filename filter parameter is <code>FilenameFilter</code> type.</p>
     * @return {@link java.util.List} <p>The list files return object is <code>List</code> type.</p>
     * @see java.nio.file.Path
     * @see java.io.FilenameFilter
     * @see java.util.List
     */
    public static List<File> listFiles(final Path path, FilenameFilter filenameFilter) {
        return listFiles(path.toFile(), filenameFilter);
    }

    /**
     * <code>listFiles</code>
     * <p>The list files method.</p>
     * @param path       {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @param fileFilter {@link java.io.FileFilter} <p>The file filter parameter is <code>FileFilter</code> type.</p>
     * @return {@link java.util.List} <p>The list files return object is <code>List</code> type.</p>
     * @see java.nio.file.Path
     * @see java.io.FileFilter
     * @see java.util.List
     */
    public static List<File> listFiles(final Path path, FileFilter fileFilter) {
        return listFiles(path.toFile(), fileFilter);
    }

    /**
     * <code>listFiles</code>
     * <p>The list files method.</p>
     * @param path {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @return {@link java.util.List} <p>The list files return object is <code>List</code> type.</p>
     * @see java.nio.file.Path
     * @see java.util.List
     */
    public static List<File> listFiles(final Path path) {
        return listFiles(path.toFile());
    }

    /**
     * <code>listFiles</code>
     * <p>The list files method.</p>
     * @param file           {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param filenameFilter {@link java.io.FilenameFilter} <p>The filename filter parameter is <code>FilenameFilter</code> type.</p>
     * @return {@link java.util.List} <p>The list files return object is <code>List</code> type.</p>
     * @see java.io.File
     * @see java.io.FilenameFilter
     * @see java.util.List
     */
    public static List<File> listFiles(final File file, FilenameFilter filenameFilter) {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        File[] files = file.listFiles(filenameFilter);
        if (GeneralUtils.isEmpty(files)) {
            return new ArrayList<>();
        }
        List<File> fileList = new ArrayList<>();
        Arrays.stream(files).forEach(innerFile -> {
            if (innerFile.isDirectory()) {
                List<File> innerFiles = listFiles(innerFile, filenameFilter);
                fileList.addAll(innerFiles);
            } else {
                fileList.add(innerFile);
            }
        });
        return fileList;
    }


    /**
     * <code>listFiles</code>
     * <p>The list files method.</p>
     * @param file       {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param fileFilter {@link java.io.FileFilter} <p>The file filter parameter is <code>FileFilter</code> type.</p>
     * @return {@link java.util.List} <p>The list files return object is <code>List</code> type.</p>
     * @see java.io.File
     * @see java.io.FileFilter
     * @see java.util.List
     */
    public static List<File> listFiles(final File file, FileFilter fileFilter) {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        File[] files = file.listFiles(fileFilter);
        if (GeneralUtils.isEmpty(files)) {
            return new ArrayList<>();
        }
        List<File> fileList = new ArrayList<>();
        Arrays.stream(files).forEach(innerFile -> {
            if (innerFile.isDirectory()) {
                List<File> innerFiles = listFiles(innerFile, fileFilter);
                fileList.addAll(innerFiles);
            } else {
                fileList.add(innerFile);
            }
        });
        return fileList;
    }

    /**
     * <code>listFiles</code>
     * <p>The list files method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.util.List} <p>The list files return object is <code>List</code> type.</p>
     * @see java.io.File
     * @see java.util.List
     */
    public static List<File> listFiles(final File file) {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        File[] files = file.listFiles();
        if (GeneralUtils.isEmpty(files)) {
            return new ArrayList<>();
        }
        List<File> fileList = new ArrayList<>();
        Arrays.stream(files).forEach(innerFile -> {
            if (innerFile.isDirectory()) {
                List<File> innerFiles = listFiles(innerFile);
                fileList.addAll(innerFiles);
            } else {
                fileList.add(innerFile);
            }
        });
        return fileList;
    }

    /**
     * <code>copyFile</code>
     * <p>The copy file method.</p>
     * @param srcFile    {@link java.io.File} <p>The src file parameter is <code>File</code> type.</p>
     * @param targetFile {@link java.io.File} <p>The target file parameter is <code>File</code> type.</p>
     * @see java.io.File
     */
    public static void copyFile(final File srcFile, final File targetFile) {
        try {
            FileHelper.copyFile(srcFile, targetFile);
        } catch (FileCopyException exception) {
            log.error("It is failed during copying of file!", exception);
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>copyFile</code>
     * <p>The copy file method.</p>
     * @param srcPath    {@link java.lang.String} <p>The src path parameter is <code>String</code> type.</p>
     * @param targetPath {@link java.lang.String} <p>The target path parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static void copyFile(final String srcPath, final String targetPath) {
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath);
        copyFile(srcFile, targetFile);
    }

    /**
     * <code>deleteFile</code>
     * <p>The delete file method.</p>
     * @param path {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The delete file return object is <code>Boolean</code> type.</p>
     * @see java.nio.file.Path
     * @see java.lang.Boolean
     */
    public static Boolean deleteFile(final Path path) {
        return FileHelper.deleteFile(path.toFile());
    }

    /**
     * <code>deleteFile</code>
     * <p>The delete file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The delete file return object is <code>Boolean</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     */
    public static Boolean deleteFile(final String path) {
        return FileHelper.deleteFile(path);
    }

    /**
     * <code>deleteFile</code>
     * <p>The delete file method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The delete file return object is <code>Boolean</code> type.</p>
     * @see java.io.File
     * @see java.lang.Boolean
     */
    public static Boolean deleteFile(final File file) {
        return FileHelper.deleteFile(file);
    }

    /**
     * <code>clearFile</code>
     * <p>The clear file method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Boolean} <p>The clear file return object is <code>Boolean</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Boolean
     */
    public static Boolean clearFile(final String path) {
        return FileHelper.clearFile(path);
    }

    /**
     * <code>cacheFile</code>
     * <p>The cache file method.</p>
     * @param cachePath {@link java.lang.String} <p>The cache path parameter is <code>String</code> type.</p>
     * @param file      {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @return {@link java.io.File} <p>The cache file return object is <code>File</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.web.multipart.MultipartFile
     * @see java.io.File
     */
    public static File cacheFile(final String cachePath, MultipartFile file) {
        createPath(cachePath);
        String originalFilename = file.getOriginalFilename();
        final String path = cachePath + File.separator + originalFilename;
        File cacheFile = new File(path);
        IoStreamUtils.transfer(file, cacheFile);
        return cacheFile;
    }

    /**
     * <code>createPath</code>
     * <p>The create path method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The create path return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String createPath(final String path) {
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        return filePath.getPath();
    }

    /**
     * <code>createPath</code>
     * <p>The create path method.</p>
     * @param path  {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @param child {@link java.lang.String} <p>The child parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The create path return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String createPath(final String path, final String child) {
        File allPath = new File(path, child);
        if (!allPath.exists()) {
            allPath.mkdirs();
        }
        return allPath.getPath();
    }

    /**
     * <code>clear</code>
     * <p>The clear method.</p>
     * @param root {@link java.io.File} <p>The root parameter is <code>File</code> type.</p>
     * @return boolean <p>The clear return object is <code>boolean</code> type.</p>
     * @see java.io.File
     */
    public static boolean clear(final File root) {
        if (root != null && root.exists()) {
            if (root.isDirectory()) {
                File[] children = root.listFiles();
                if (children != null) {
                    for (File child : children) {
                        clear(child);
                    }
                }
            }
            return root.delete();
        }
        return false;
    }

    /**
     * <code>clear</code>
     * <p>The clear method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @return boolean <p>The clear return object is <code>boolean</code> type.</p>
     * @see java.lang.String
     */
    public static boolean clear(final String path) {
        File file = new File(path);
        return clear(file);
    }

    /**
     * <code>clear</code>
     * <p>The clear method.</p>
     * @param path {@link java.nio.file.Path} <p>The path parameter is <code>Path</code> type.</p>
     * @return boolean <p>The clear return object is <code>boolean</code> type.</p>
     * @see java.nio.file.Path
     */
    public static boolean clear(final Path path) {
        return clear(path.toFile());
    }

    /**
     * <code>delete</code>
     * <p>The delete method.</p>
     * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void delete(final String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * <code>delete</code>
     * <p>The delete method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @see java.io.File
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void delete(final File file) {
        if (file.isFile()) {
            file.delete();
        }
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @param data byte <p>The data parameter is <code>byte</code> type.</p>
     * @see java.io.File
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void write(final File file, byte[] data) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(data);
            fileOutputStream.flush();
        } catch (IOException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error(exception.getMessage(), exception);
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return byte <p>The read return object is <code>byte</code> type.</p>
     * @see java.io.File
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static byte[] read(final File file) {
        long fileLength = file.length();
        byte[] fileContent = new byte[(int) fileLength];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileContent);
            return fileContent;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new byte[0];
        }
    }

    /**
     * <code>suffix</code>
     * <p>The suffix method.</p>
     * @param originalName {@link java.lang.String} <p>The original name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The suffix return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String suffix(final String originalName) {
        if (GeneralUtils.isEmpty(originalName)) {
            return "";
        }
        if (!originalName.contains(".")) {
            return "";
        }
        return originalName.substring(originalName.lastIndexOf(".") + 1);
    }

    /**
     * <code>mediaType</code>
     * <p>The media type method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @return {@link org.springframework.http.MediaType} <p>The media type return object is <code>MediaType</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.http.MediaType
     */
    public static MediaType mediaType(final String filename) {
        Optional<MediaType> mediaTypeOptional = MediaTypeFactory.getMediaType(filename);
        return mediaTypeOptional.orElse(MediaType.APPLICATION_OCTET_STREAM);
    }

    /**
     * <code>filename</code>
     * <p>The filename method.</p>
     * @param originalName {@link java.lang.String} <p>The original name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The filename return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String filename(final String originalName) {
        if (GeneralUtils.isEmpty(originalName)) {
            return "";
        }
        if (!originalName.contains(".")) {
            return originalName;
        }
        return originalName.substring(0, originalName.lastIndexOf("."));
    }

    /**
     * <code>fileSize</code>
     * <p>The file size method.</p>
     * @param fileSize {@link java.lang.Long} <p>The file size parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>The file size return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
    public static String fileSize(final Long fileSize) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String fileSizeString;
        String wrongSize = "0B";
        if (fileSize == 0) {
            return wrongSize;
        }
        if (fileSize < 1024) {
            fileSizeString = decimalFormat.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = decimalFormat.format((double) fileSize / 1024) + "KB";
        } else if (fileSize < 1073741824) {
            fileSizeString = decimalFormat.format((double) fileSize / 1048576) + "MB";
        } else {
            fileSizeString = decimalFormat.format((double) fileSize / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * <code>attachment</code>
     * <p>The attachment method.</p>
     * @param request  {@link jakarta.servlet.http.HttpServletRequest} <p>The request parameter is <code>HttpServletRequest</code> type.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param fileName {@link java.lang.String} <p>The file name parameter is <code>String</code> type.</p>
     * @see jakarta.servlet.http.HttpServletRequest
     * @see jakarta.servlet.http.HttpServletResponse
     * @see java.lang.String
     */
    public static void attachment(HttpServletRequest request, HttpServletResponse response, String fileName) {
        String browser;
        try {
            if (request != null && request.getHeader(UtilConstants.USER_AGENT_HEADER) != null) {
                browser = request.getHeader(UtilConstants.USER_AGENT_HEADER);
                if (browser.contains("MSIE 6.0") || browser.contains("MSIE 7.0")) {
                    // IE6, IE7
                    response.addHeader(UtilConstants.CONTENT_HEADER, UtilConstants.FILENAME_CONTENT + URLEncoder.encode(fileName, StandardCharsets.ISO_8859_1.name()));
                } else if (browser.contains("MSIE 8.0")) {
                    // IE8
                    response.addHeader(UtilConstants.CONTENT_HEADER, UtilConstants.FILENAME_CONTENT + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
                } else if (browser.contains("MSIE 9.0")) {
                    // IE9
                    response.addHeader(UtilConstants.CONTENT_HEADER, UtilConstants.FILENAME_CONTENT + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
                } else if (browser.contains("Chrome")) {
                    // Google
                    response.addHeader(UtilConstants.CONTENT_HEADER, UtilConstants.FILENAME_UTF_8_CONTENT + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
                } else if (browser.contains("Safari")) {
                    // Safari
                    response.addHeader(UtilConstants.CONTENT_HEADER, UtilConstants.FILENAME_CONTENT + URLEncoder.encode(fileName, StandardCharsets.ISO_8859_1.name()));
                } else {
                    // Firefox or other
                    response.addHeader(UtilConstants.CONTENT_HEADER, UtilConstants.FILENAME_UTF_8_CONTENT + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
                }
            } else {
                response.addHeader(UtilConstants.CONTENT_HEADER, UtilConstants.FILENAME_UTF_8_CONTENT + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
            }

        } catch (IOException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error(exception.getMessage(), exception);
        }
    }
}
