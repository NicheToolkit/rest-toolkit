package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * <code>PathUtils</code>
 * <p>The type path utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class PathUtils {

    /**
     * <code>getRootPath</code>
     * <p>the root path getter method.</p>
     * @return {@link java.lang.String} <p>the root path return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
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

    /**
     * <code>getChildPath</code>
     * <p>the child path getter method.</p>
     * @param childName {@link java.lang.String} <p>the child name parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the child path return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
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
