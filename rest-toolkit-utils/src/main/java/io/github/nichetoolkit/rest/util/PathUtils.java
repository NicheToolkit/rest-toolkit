package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * <p>PathUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PathUtils {

    public static String getRootPath() {
        File file;
        try {
            file = new File(ResourceUtils.getURL(UtilConstants.ROOT_PREFIX).getPath());
            if(!file.exists()) {
                file = new File(UtilConstants.EMPTY_PREFIX);
            }
        } catch (FileNotFoundException ignoredException) {
            file = new File(UtilConstants.EMPTY_PREFIX);
        }
        return file.getAbsolutePath();
    }

    public static String getChildPath(final String childName) {
        String rootPath = getRootPath();
        String staticPath = UtilConstants.STATIC_PREFIX.concat(File.separator).concat(childName).concat(File.separator);
        File file = new File(rootPath, staticPath);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
}
