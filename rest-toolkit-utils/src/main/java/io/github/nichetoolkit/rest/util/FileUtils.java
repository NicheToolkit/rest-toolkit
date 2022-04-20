package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.often.FileCopyException;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.helper.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * <p>FileUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class FileUtils {

    public static File createFile(final String path) {
        try {
            return FileHelper.createFile(path);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file !", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static File createFile(final String path, final String name) {
        try {
            return FileHelper.createFile(path,name);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static File createFile(final String path, Map<String, String> nameMap) {
        try {
            return FileHelper.createFile(path, nameMap);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file with nameMap!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static File createFile(final String path, final String name, final String ext) {
        try {
            return FileHelper.createFile(path,name,ext);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating file!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static File createFile(final File file) {
        try {
            return FileHelper.createFile(file);
        } catch (FileCreateException exception) {
            log.error("It is failed during creating of file!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static void copyFile(final File srcFile,final File targetFile) {
        try {
            FileHelper.copyFile(srcFile, targetFile);
        } catch (FileCopyException exception) {
            log.error("It is failed during copying of file!", exception);
            exception.printStackTrace();
        }
    }

    public static void copyFile(final String srcPath,final String targetPath) {
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath);
        copyFile(srcFile, targetFile);
    }

    public static Boolean deleteFile(final String path) {
        return FileHelper.deleteFile(path);
    }

    public static Boolean deleteFile(final File file) {
        return FileHelper.deleteFile(file);
    }

    public static Boolean clearFile(final String path) {
        return FileHelper.clearFile(path);
    }

    public static File cacheFile(final String cachePath, MultipartFile file) {
        createPath(cachePath);
        String originalFilename = file.getOriginalFilename();
        final String path = cachePath + originalFilename;
        File cacheFile = new File(path);
        StreamUtils.transfer(file,cacheFile);
        return cacheFile;
    }

    public static String createPath(final String path) {
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        return filePath.getPath();
    }

    public static String createPath(final String path,final String child) {
        File allPath = new File(path, child);
        if (!allPath.exists()) {
            allPath.mkdirs();
        }
        return allPath.getPath();
    }

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

    public static boolean clear(final String path) {
        File file = new File(path);
        return clear(file);
    }

    public static void delete(final String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void delete(final File file) {
        if (file.isFile()) {
            file.delete();
        }
    }

    public static void write(final File file, byte[] data) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(data);
            fileOutputStream.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage(), exception);
        }
    }

    public static byte[] read(final File file) {
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(fileContent);
            return fileContent;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new byte[0];
        }
    }

    public static String suffix(final String originalName){
        if(GeneralUtils.isEmpty(originalName)){
            return "";
        }
        if (!originalName.contains(".")) {
            return "";
        }
        return originalName.substring(originalName.lastIndexOf(".") + 1);
    }

    public static String filename(final String originalName){
        if(GeneralUtils.isEmpty(originalName)){
            return "";
        }
        if (!originalName.contains(".")) {
            return originalName;
        }
        return originalName.substring(0,originalName.lastIndexOf("."));
    }

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
            exception.printStackTrace();
            log.error(exception.getMessage(), exception);
        }
    }
}
