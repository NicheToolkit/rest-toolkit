package io.github.nichetoolkit.rest.helper;


import io.github.nichetoolkit.rest.error.often.FileCopyException;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.NameUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class FileHelper {

    public static File createFile(final String path) throws FileCreateException {
        return createFile(Paths.get(path));
    }

    public static File createFile(final Path path) throws FileCreateException {
        return createFile(path.toFile());
    }

    public static File createFile(final String path, final String name) throws FileCreateException {
        return createFile(Paths.get(path,name));
    }

    public static File createFile(final String path, final Map<String, String> nameMap) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(nameMap.get(NameUtils.NAME)).concat(nameMap.get(NameUtils.EXT));
        return createFile(Paths.get(filePath));
    }

    public static File createFile(final String path, final String name, final String ext) throws FileCreateException {
        String filePath = path.concat(File.separator).concat(name).concat(ext);
        return createFile(Paths.get(filePath));
    }

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
