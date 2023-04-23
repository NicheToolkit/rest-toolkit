package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.error.often.ZipErrorException;
import io.github.nichetoolkit.rest.helper.ZipHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * <p>ZipUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class ZipUtils {

    public static File zipFile(String zipPath, String filename, File file) {
        try {
            return ZipHelper.zipFile(zipPath,filename,file);
        } catch (ZipErrorException | FileCreateException exception) {
            log.error("It is failed during handle zip file! {}", exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public static File zipFiles(String zipPath, String filename, List<File> zipFiles) {
        try {
            return ZipHelper.zipFiles(zipPath,filename,zipFiles);
        } catch (ZipErrorException | FileCreateException exception) {
            log.error("It is failed during handle zip files! {}", exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }

    public static byte[] gzip(byte[] bytes) {
        try {
            return ZipHelper.gzip(bytes);
        } catch (ZipErrorException exception) {
            log.error("It is failed during handle zip file! {}", exception.getMessage());
            exception.printStackTrace();
        }
        return new byte[0];
    }

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
