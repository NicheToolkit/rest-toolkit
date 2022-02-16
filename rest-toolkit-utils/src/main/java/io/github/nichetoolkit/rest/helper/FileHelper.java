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
 * <p>FileHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class FileHelper {

    public static File createFile(final String path) throws FileCreateException {
        return createFile(new File(path));
    }

    public static File createFile(final String path, final String name) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(name);
        return createFile(new File(filePath));
    }

    public static File createFile(final String path, final Map<String, String> nameMap) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(nameMap.get(NameUtils.NAME)).concat(nameMap.get(NameUtils.EXT));
        return createFile(new File(filePath));
    }

    public static File createFile(final String path, final String name, final String ext) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(name).concat(ext);
        return createFile(new File(filePath));
    }

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

    public static void copyFile(final File srcFile, final File targetFile) throws FileCopyException {
        try (FileChannel input = new FileInputStream(srcFile).getChannel();
             FileChannel output = new FileOutputStream(targetFile).getChannel()){
            output.transferFrom(input, 0, input.size());
        } catch (IOException exception) {
            throw new FileCopyException(exception.getMessage());
        }
    }

    public static void copyFile(final String srcPath, final String targetPath) throws FileCopyException {
        File srcFile = new File(srcPath);
        File targetFile = new File(targetPath);
        copyFile(srcFile, targetFile);
    }

    public static boolean deleteFile(final String path) {
        File file = new File(path);
        return deleteFile(file);
    }

    public static boolean deleteFile(final File file) {
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return true;
        }
    }

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
