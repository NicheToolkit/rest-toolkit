package io.github.nichetoolkit.rest.helper;


import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.image.ImageReadException;
import io.github.nichetoolkit.rest.error.image.ImageTransferException;
import io.github.nichetoolkit.rest.error.image.ImageWriteException;

import jakarta.imageio.ImageIO;
import jakarta.imageio.stream.ImageOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;

/**
 * <code>ImageHelper</code>
 * <p>The image helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ImageHelper {

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param outputStream  {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writePng(BufferedImage bufferedImage, OutputStream outputStream) throws ImageWriteException {
        write(bufferedImage,outputStream,UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param outputStream  {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writeJpeg(BufferedImage bufferedImage, OutputStream outputStream) throws ImageWriteException {
        write(bufferedImage,outputStream,UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param outputStream  {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.OutputStream
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void write(BufferedImage bufferedImage, OutputStream outputStream, String suffix) throws ImageWriteException {
        try {
            ImageIO.write(bufferedImage, suffix, outputStream);
        } catch (IOException exception) {
            throw new ImageWriteException(exception.getMessage());
        }
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param response      {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writePng(BufferedImage bufferedImage, HttpServletResponse response) throws ImageWriteException {
        write(bufferedImage,response,UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param response      {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writeJpeg(BufferedImage bufferedImage, HttpServletResponse response) throws ImageWriteException {
        write(bufferedImage,response,UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param response      {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see jakarta.servlet.http.HttpServletResponse
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void write(BufferedImage bufferedImage, HttpServletResponse response, String suffix) throws ImageWriteException {
        try {
            ImageIO.write(bufferedImage, suffix, response.getOutputStream());
        } catch (IOException exception) {
            throw new ImageWriteException(exception.getMessage());
        }
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imagePath     {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.nio.file.Path
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writePng(BufferedImage bufferedImage, Path imagePath) throws ImageWriteException {
        write(bufferedImage,imagePath,UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imagePath     {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.nio.file.Path
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writeJpeg(BufferedImage bufferedImage, Path imagePath) throws ImageWriteException {
        write(bufferedImage,imagePath,UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imagePath     {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.nio.file.Path
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void write(BufferedImage bufferedImage, Path imagePath, String suffix) throws ImageWriteException {
        try {
            ImageIO.write(bufferedImage, suffix, imagePath.toFile());
        } catch (IOException exception) {
            throw new ImageWriteException(exception.getMessage());
        }
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imageFile     {@link java.io.File} <p>The image file parameter is <code>File</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writePng(BufferedImage bufferedImage, File imageFile) throws ImageWriteException {
        write(bufferedImage,imageFile,UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imageFile     {@link java.io.File} <p>The image file parameter is <code>File</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.File
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void writeJpeg(BufferedImage bufferedImage, File imageFile) throws ImageWriteException {
        write(bufferedImage,imageFile,UtilConstants.JPEG_IMAGE_SUFFIX);
    }


    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imageFile     {@link java.io.File} <p>The image file parameter is <code>File</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.File
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public static void write(BufferedImage bufferedImage, File imageFile, String suffix) throws ImageWriteException {
        try {
            ImageIO.write(bufferedImage, suffix, imageFile);
        } catch (IOException exception) {
            throw new ImageWriteException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The read return object is <code>BufferedImage</code> type.</p>
     * @throws ImageReadException {@link io.github.nichetoolkit.rest.error.image.ImageReadException} <p>The image read exception is <code>ImageReadException</code> type.</p>
     * @see java.io.InputStream
     * @see java.awt.image.BufferedImage
     * @see io.github.nichetoolkit.rest.error.image.ImageReadException
     */
    public static BufferedImage read(InputStream inputStream) throws ImageReadException {
        try {
            return ImageIO.read(inputStream);
        } catch (IOException exception) {
            throw new ImageReadException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param imagePath {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The read return object is <code>BufferedImage</code> type.</p>
     * @throws ImageReadException {@link io.github.nichetoolkit.rest.error.image.ImageReadException} <p>The image read exception is <code>ImageReadException</code> type.</p>
     * @see java.nio.file.Path
     * @see java.awt.image.BufferedImage
     * @see io.github.nichetoolkit.rest.error.image.ImageReadException
     */
    public static BufferedImage read(Path imagePath) throws ImageReadException {
        try {
            return ImageIO.read(imagePath.toFile());
        } catch (IOException exception) {
            throw new ImageReadException(exception.getMessage());
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The read return object is <code>BufferedImage</code> type.</p>
     * @throws ImageReadException {@link io.github.nichetoolkit.rest.error.image.ImageReadException} <p>The image read exception is <code>ImageReadException</code> type.</p>
     * @see java.io.File
     * @see java.awt.image.BufferedImage
     * @see io.github.nichetoolkit.rest.error.image.ImageReadException
     */
    public static BufferedImage read(File file) throws ImageReadException {
        try {
            return ImageIO.read(file);
        } catch (IOException exception) {
            throw new ImageReadException(exception.getMessage());
        }
    }

    /**
     * <code>readPng</code>
     * <p>The read png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return {@link java.io.InputStream} <p>The read png return object is <code>InputStream</code> type.</p>
     * @throws ImageTransferException {@link io.github.nichetoolkit.rest.error.image.ImageTransferException} <p>The image transfer exception is <code>ImageTransferException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.image.ImageTransferException
     */
    public static InputStream readPng(BufferedImage bufferedImage) throws ImageTransferException {
       return read(bufferedImage,UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>readJpeg</code>
     * <p>The read jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return {@link java.io.InputStream} <p>The read jpeg return object is <code>InputStream</code> type.</p>
     * @throws ImageTransferException {@link io.github.nichetoolkit.rest.error.image.ImageTransferException} <p>The image transfer exception is <code>ImageTransferException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.image.ImageTransferException
     */
    public static InputStream readJpeg(BufferedImage bufferedImage) throws ImageTransferException {
        return read(bufferedImage,UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.io.InputStream} <p>The read return object is <code>InputStream</code> type.</p>
     * @throws ImageTransferException {@link io.github.nichetoolkit.rest.error.image.ImageTransferException} <p>The image transfer exception is <code>ImageTransferException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.String
     * @see java.io.InputStream
     * @see io.github.nichetoolkit.rest.error.image.ImageTransferException
     */
    public static InputStream read(BufferedImage bufferedImage, String suffix) throws ImageTransferException {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageOutputStream imageOutputStream;
        try {
            imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
            ImageIO.write(bufferedImage, suffix, imageOutputStream);
            inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException exception) {
            throw new ImageTransferException(exception.getMessage());
        }
        return inputStream;
    }

    /**
     * <code>bytesPng</code>
     * <p>The bytes png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return byte <p>The bytes png return object is <code>byte</code> type.</p>
     * @throws ImageTransferException {@link io.github.nichetoolkit.rest.error.image.ImageTransferException} <p>The image transfer exception is <code>ImageTransferException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see io.github.nichetoolkit.rest.error.image.ImageTransferException
     */
    public static byte[] bytesPng(BufferedImage bufferedImage) throws ImageTransferException {
        return bytes(bufferedImage,UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>bytesJpeg</code>
     * <p>The bytes jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return byte <p>The bytes jpeg return object is <code>byte</code> type.</p>
     * @throws ImageTransferException {@link io.github.nichetoolkit.rest.error.image.ImageTransferException} <p>The image transfer exception is <code>ImageTransferException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see io.github.nichetoolkit.rest.error.image.ImageTransferException
     */
    public static byte[] bytesJpeg(BufferedImage bufferedImage) throws ImageTransferException {
        return bytes(bufferedImage,UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>bytes</code>
     * <p>The bytes method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return byte <p>The bytes return object is <code>byte</code> type.</p>
     * @throws ImageTransferException {@link io.github.nichetoolkit.rest.error.image.ImageTransferException} <p>The image transfer exception is <code>ImageTransferException</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.image.ImageTransferException
     */
    public static byte[] bytes(BufferedImage bufferedImage, String suffix) throws ImageTransferException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageOutputStream imageOutputStream;
        try {
            imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
            ImageIO.write(bufferedImage, suffix, imageOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException exception) {
            throw new ImageTransferException(exception.getMessage());
        }
    }

}
