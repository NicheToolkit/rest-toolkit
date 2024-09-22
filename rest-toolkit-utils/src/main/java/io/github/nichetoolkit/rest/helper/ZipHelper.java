package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.often.FileCreateException;
import io.github.nichetoolkit.rest.error.often.ZipErrorException;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <code>ZipHelper</code>
 * <p>The type zip helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ZipHelper {

    /**
     * <code>zipFile</code>
     * <p>the file method.</p>
     * @param zipPath  {@link java.lang.String} <p>the zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param file     {@link java.io.File} <p>the file parameter is <code>File</code> type.</p>
     * @return {@link java.io.File} <p>the file return object is <code>File</code> type.</p>
     * @throws ZipErrorException   {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>the zip error exception is <code>ZipErrorException</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>the file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File zipFile(String zipPath, String filename, File file) throws ZipErrorException, FileCreateException {
        String zipFilePath = zipPath.concat(File.separator).concat(filename)
                .concat(UtilConstants.SUFFIX_REGEX).concat(UtilConstants.ZIP_SUFFIX);
        if (filename.endsWith(UtilConstants.SUFFIX_REGEX.concat(UtilConstants.ZIP_SUFFIX))) {
            zipFilePath = zipPath.concat(File.separator).concat(filename);
        }
        File zipFile = FileHelper.createFile(zipFilePath);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            zipOutputStream.setComment(filename);
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                int temp;
                while ((temp = inputStream.read()) != -1) {
                    zipOutputStream.write(temp);
                }
            }
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return zipFile;
    }

    /**
     * <code>zipFiles</code>
     * <p>the files method.</p>
     * @param zipPath  {@link java.lang.String} <p>the zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>the filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>the zip files parameter is <code>List</code> type.</p>
     * @return {@link java.io.File} <p>the files return object is <code>File</code> type.</p>
     * @throws ZipErrorException   {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>the zip error exception is <code>ZipErrorException</code> type.</p>
     * @throws FileCreateException {@link io.github.nichetoolkit.rest.error.often.FileCreateException} <p>the file create exception is <code>FileCreateException</code> type.</p>
     * @see java.lang.String
     * @see java.util.List
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.often.ZipErrorException
     * @see io.github.nichetoolkit.rest.error.often.FileCreateException
     */
    public static File zipFiles(String zipPath, String filename, List<File> zipFiles) throws ZipErrorException, FileCreateException {
        if (zipFiles.size() == 1) {
            return zipFile(zipPath,filename,zipFiles.stream().findFirst().get());
        }
        String zipFilePath = zipPath.concat(File.separator).concat(filename)
                .concat(UtilConstants.SUFFIX_REGEX).concat(UtilConstants.ZIP_SUFFIX);
        if (filename.endsWith(UtilConstants.SUFFIX_REGEX.concat(UtilConstants.ZIP_SUFFIX))) {
            zipFilePath = zipPath.concat(File.separator).concat(filename);
        }
        File zipFile = FileHelper.createFile(zipFilePath);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            zipOutputStream.setComment(filename);
            for (File file : zipFiles) {
                try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    int temp;
                    while ((temp = inputStream.read()) != -1) {
                        zipOutputStream.write(temp);
                    }
                }
            }
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return zipFile;
    }

    /**
     * <code>gzip</code>
     * <p>the method.</p>
     * @param data byte <p>the data parameter is <code>byte</code> type.</p>
     * @return byte <p>the return object is <code>byte</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>the zip error exception is <code>ZipErrorException</code> type.</p>
     * @see io.github.nichetoolkit.rest.error.often.ZipErrorException
     */
    public static byte[] gzip(byte[] data) throws ZipErrorException {
        byte[] bytes;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return bytes;
    }

    /**
     * <code>ungzip</code>
     * <p>the method.</p>
     * @param data byte <p>the data parameter is <code>byte</code> type.</p>
     * @return byte <p>the return object is <code>byte</code> type.</p>
     * @throws ZipErrorException {@link io.github.nichetoolkit.rest.error.often.ZipErrorException} <p>the zip error exception is <code>ZipErrorException</code> type.</p>
     * @see io.github.nichetoolkit.rest.error.often.ZipErrorException
     */
    public static byte[] ungzip(byte[] data) throws ZipErrorException {
        byte[] bytes;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int num;
            while ((num = gzipInputStream.read(buf, 0, buf.length)) != -1) {
                byteArrayOutputStream.write(buf, 0, num);
            }
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
        } catch (IOException exception) {
            throw new ZipErrorException(exception.getMessage());
        }
        return bytes;
    }
}
