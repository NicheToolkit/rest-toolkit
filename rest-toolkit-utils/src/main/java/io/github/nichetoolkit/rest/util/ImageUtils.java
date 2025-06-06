package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.error.image.ImageReadException;
import io.github.nichetoolkit.rest.error.image.ImageTransferException;
import io.github.nichetoolkit.rest.error.image.ImageWriteException;
import io.github.nichetoolkit.rest.helper.ImageHelper;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <code>ImageUtils</code>
 * <p>The image utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ImageUtils {

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param outputStream  {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.OutputStream
     */
    public static void writePng(BufferedImage bufferedImage, OutputStream outputStream) {
        write(bufferedImage, outputStream, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param outputStream  {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.OutputStream
     */
    public static void writeJpeg(BufferedImage bufferedImage, OutputStream outputStream) {
        write(bufferedImage, outputStream, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param outputStream  {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.OutputStream
     * @see java.lang.String
     */
    public static void write(BufferedImage bufferedImage, OutputStream outputStream, String suffix) {
        try {
            ImageHelper.write(bufferedImage, outputStream, suffix);
        } catch (ImageWriteException exception) {
            log.error("It has encountered an error during bufferedImage to write with outputStream.", exception);
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see javax.servlet.http.HttpServletResponse
     */
    public static void writePng(BufferedImage bufferedImage, HttpServletResponse response) {
        write(bufferedImage, response, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see javax.servlet.http.HttpServletResponse
     */
    public static void writeJpeg(BufferedImage bufferedImage, HttpServletResponse response) {
        write(bufferedImage, response, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param response      {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see javax.servlet.http.HttpServletResponse
     * @see java.lang.String
     */
    public static void write(BufferedImage bufferedImage, HttpServletResponse response, String suffix) {
        try {
            ImageHelper.write(bufferedImage, response, suffix);
        } catch (ImageWriteException exception) {
            log.error("It has encountered an error during bufferedImage to write with outputStream.", exception);
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imagePath     {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.nio.file.Path
     */
    public static void writePng(BufferedImage bufferedImage, Path imagePath) {
        write(bufferedImage, imagePath, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imagePath     {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.nio.file.Path
     */
    public static void writeJpeg(BufferedImage bufferedImage, Path imagePath) {
        write(bufferedImage, imagePath, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imagePath     {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.nio.file.Path
     * @see java.lang.String
     */
    public static void write(BufferedImage bufferedImage, Path imagePath, String suffix) {
        try {
            ImageHelper.write(bufferedImage, imagePath, suffix);
        } catch (ImageWriteException exception) {
            log.error("It has encountered an error during bufferedImage to write with file path!", exception);
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imageFile     {@link java.io.File} <p>The image file parameter is <code>File</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.File
     */
    public static void writePng(BufferedImage bufferedImage, File imageFile) {
        write(bufferedImage, imageFile, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imageFile     {@link java.io.File} <p>The image file parameter is <code>File</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.File
     */
    public static void writeJpeg(BufferedImage bufferedImage, File imageFile) {
        write(bufferedImage, imageFile, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param imageFile     {@link java.io.File} <p>The image file parameter is <code>File</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.File
     * @see java.lang.String
     */
    public static void write(BufferedImage bufferedImage, File imageFile, String suffix) {
        try {
            ImageHelper.write(bufferedImage, imageFile, suffix);
        } catch (ImageWriteException exception) {
            log.error("It has encountered an error during bufferedImage to write with file!", exception);
            GeneralUtils.printStackTrace(exception);
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The read return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage read(InputStream inputStream) {
        try {
            return ImageHelper.read(inputStream);
        } catch (ImageReadException exception) {
            log.error("It has encountered an error during inputStream to read as BufferedImage!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param imagePath {@link java.nio.file.Path} <p>The image path parameter is <code>Path</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The read return object is <code>BufferedImage</code> type.</p>
     * @see java.nio.file.Path
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage read(Path imagePath) {
        try {
            return ImageHelper.read(imagePath);
        } catch (ImageReadException exception) {
            log.error("It has encountered an error during file to read as BufferedImage!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }


    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The read return object is <code>BufferedImage</code> type.</p>
     * @see java.io.File
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage read(File file) {
        try {
            return ImageHelper.read(file);
        } catch (ImageReadException exception) {
            log.error("It has encountered an error during file to write as BufferedImage!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>readPng</code>
     * <p>The read png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return {@link java.io.InputStream} <p>The read png return object is <code>InputStream</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.InputStream
     */
    public static InputStream readPng(BufferedImage bufferedImage) {
        return read(bufferedImage, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>readJpeg</code>
     * <p>The read jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return {@link java.io.InputStream} <p>The read jpeg return object is <code>InputStream</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.io.InputStream
     */
    public static InputStream readJpeg(BufferedImage bufferedImage) {
        return read(bufferedImage, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.io.InputStream} <p>The read return object is <code>InputStream</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.String
     * @see java.io.InputStream
     */
    public static InputStream read(BufferedImage bufferedImage, String suffix) {
        try {
            return ImageHelper.read(bufferedImage, suffix);
        } catch (ImageTransferException exception) {
            log.error("It has encountered an error during bufferedImage to transfer as inputStream!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
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
        return bytes(bufferedImage, UtilConstants.PNG_IMAGE_SUFFIX);
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
        return bytes(bufferedImage, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>bytes</code>
     * <p>The bytes method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return byte <p>The bytes return object is <code>byte</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.String
     */
    public static byte[] bytes(BufferedImage bufferedImage, String suffix) {
        try {
            return ImageHelper.bytes(bufferedImage, suffix);
        } catch (ImageTransferException exception) {
            log.error("It has encountered an error during bufferedImage to transfer as inputStream!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>bytes</code>
     * <p>The bytes method.</p>
     * @param file {@link java.io.File} <p>The file parameter is <code>File</code> type.</p>
     * @return byte <p>The bytes return object is <code>byte</code> type.</p>
     * @see java.io.File
     */
    public static byte[] bytes(File file) {
        try {
            return IoStreamUtils.bytes(Files.newInputStream(file.toPath()));
        } catch (IOException exception) {
            log.error("It has encountered an error during file to transfer as byte!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>simplePng</code>
     * <p>The simple png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param scale         {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param quality       {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The simple png return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     */
    public static BufferedImage simplePng(BufferedImage bufferedImage, Double scale, Double quality) {
        return simple(bufferedImage, scale, quality, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>simpleJpeg</code>
     * <p>The simple jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param scale         {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param quality       {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The simple jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     */
    public static BufferedImage simpleJpeg(BufferedImage bufferedImage, Double scale, Double quality) {
        return simple(bufferedImage, scale, quality, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scalePng</code>
     * <p>The scale png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param scale         {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale png return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     */
    public static BufferedImage scalePng(BufferedImage bufferedImage, Double scale) {
        return scale(bufferedImage, scale, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleJpeg</code>
     * <p>The scale jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param scale         {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     */
    public static BufferedImage scaleJpeg(BufferedImage bufferedImage, Double scale) {
        return scale(bufferedImage, scale, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scale</code>
     * <p>The scale method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param scale         {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     * @see java.lang.String
     */
    public static BufferedImage scale(BufferedImage bufferedImage, Double scale, String suffix) {
        return simple(bufferedImage, scale, 1d, suffix);
    }

    /**
     * <code>qualityPng</code>
     * <p>The quality png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param quality       {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The quality png return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     */
    public static BufferedImage qualityPng(BufferedImage bufferedImage, Double quality) {
        return quality(bufferedImage, quality, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>qualityJpeg</code>
     * <p>The quality jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param quality       {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The quality jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     */
    public static BufferedImage qualityJpeg(BufferedImage bufferedImage, Double quality) {
        return quality(bufferedImage, quality, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>quality</code>
     * <p>The quality method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param quality       {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The quality return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     * @see java.lang.String
     */
    public static BufferedImage quality(BufferedImage bufferedImage, Double quality, String suffix) {
        return simple(bufferedImage, 1d, quality, suffix);
    }

    /**
     * <code>simple</code>
     * <p>The simple method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param scale         {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param quality       {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The simple return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Double
     * @see java.lang.String
     */
    public static BufferedImage simple(BufferedImage bufferedImage, Double scale, Double quality, String suffix) {
        try {
            return Thumbnails.of(bufferedImage).scale(scale).outputFormat(suffix).outputQuality(quality).asBufferedImage();
        } catch (IOException exception) {
            log.error("It has encountered an error during bufferedImage to scale as bufferedImage!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>simplePng</code>
     * <p>The simple png method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param scale       {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param quality     {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The simple png return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage simplePng(InputStream inputStream, Double scale, Double quality) {
        return simple(inputStream, scale, quality, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>simpleJpeg</code>
     * <p>The simple jpeg method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param scale       {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param quality     {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The simple jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage simpleJpeg(InputStream inputStream, Double scale, Double quality) {
        return simple(inputStream, scale, quality, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scalePng</code>
     * <p>The scale png method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param scale       {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale png return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scalePng(InputStream inputStream, Double scale) {
        return scale(inputStream, scale, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleJpeg</code>
     * <p>The scale jpeg method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param scale       {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleJpeg(InputStream inputStream, Double scale) {
        return scale(inputStream, scale, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scale</code>
     * <p>The scale method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param scale       {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param suffix      {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scale(InputStream inputStream, Double scale, String suffix) {
        return simple(inputStream, scale, 1d, suffix);
    }

    /**
     * <code>qualityPng</code>
     * <p>The quality png method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param quality     {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The quality png return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage qualityPng(InputStream inputStream, Double quality) {
        return quality(inputStream, quality, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>qualityJpeg</code>
     * <p>The quality jpeg method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param quality     {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The quality jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage qualityJpeg(InputStream inputStream, Double quality) {
        return quality(inputStream, quality, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>quality</code>
     * <p>The quality method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param quality     {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @param suffix      {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The quality return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage quality(InputStream inputStream, Double quality, String suffix) {
        return simple(inputStream, 1d, quality, suffix);
    }

    /**
     * <code>simple</code>
     * <p>The simple method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param scale       {@link java.lang.Double} <p>The scale parameter is <code>Double</code> type.</p>
     * @param quality     {@link java.lang.Double} <p>The quality parameter is <code>Double</code> type.</p>
     * @param suffix      {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The simple return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Double
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage simple(InputStream inputStream, Double scale, Double quality, String suffix) {
        try {
            return Thumbnails.of(inputStream).scale(scale).outputFormat(suffix).outputQuality(quality).asBufferedImage();
        } catch (IOException exception) {
            log.error("It has encountered an error during inputStream to scale as bufferedImage!", exception);
            GeneralUtils.printStackTrace(exception);
            return null;
        }
    }

    /**
     * <code>scalePng</code>
     * <p>The scale png method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param width       {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale png return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scalePng(InputStream inputStream, Integer width, Integer height) {
        return scale(inputStream, width, height, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleJpeg</code>
     * <p>The scale jpeg method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param width       {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleJpeg(InputStream inputStream, Integer width, Integer height) {
        return scale(inputStream, width, height, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scale</code>
     * <p>The scale method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param width       {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @param suffix      {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scale(InputStream inputStream, Integer width, Integer height, String suffix) {
        BufferedImage bufferedImage = read(inputStream);
        return scale(bufferedImage, width, height, suffix);
    }

    /**
     * <code>scalePng</code>
     * <p>The scale png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param width         {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height        {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale png return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     */
    public static BufferedImage scalePng(BufferedImage bufferedImage, Integer width, Integer height) {
        return scale(bufferedImage, width, height, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleJpeg</code>
     * <p>The scale jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param width         {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height        {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     */
    public static BufferedImage scaleJpeg(BufferedImage bufferedImage, Integer width, Integer height) {
        return scale(bufferedImage, width, height, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scale</code>
     * <p>The scale method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param width         {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height        {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static BufferedImage scale(BufferedImage bufferedImage, Integer width, Integer height, String suffix) {
        double scale = 1.0d;
        if (GeneralUtils.isNotEmpty(bufferedImage)) {
            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();
            if (GeneralUtils.isNotEmpty(width)) {
                scale = ((double) width / (double) imageWidth >= 1.0D) ? scale : ((double) width / (double) imageWidth);
                log.debug("The image keep width is {} to scale: {}", width, scale);
            } else if (GeneralUtils.isNotEmpty(height)) {
                scale = ((double) height / (double) imageHeight >= 1.0D) ? scale : ((double) height / (double) imageHeight);
                log.debug("The image keep height is {} to scale: {}", height, scale);
            }
            return scale(bufferedImage, scale, suffix);
        }
        return null;
    }

    /**
     * <code>scaleWidthPng</code>
     * <p>The scale width png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param width         {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale width png return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     */
    public static BufferedImage scaleWidthPng(BufferedImage bufferedImage, Integer width) {
        return scaleWidth(bufferedImage, width, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleWidthJpeg</code>
     * <p>The scale width jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param width         {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale width jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     */
    public static BufferedImage scaleWidthJpeg(BufferedImage bufferedImage, Integer width) {
        return scaleWidth(bufferedImage, width, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleWidth</code>
     * <p>The scale width method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param width         {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale width return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static BufferedImage scaleWidth(BufferedImage bufferedImage, Integer width, String suffix) {
        return scale(bufferedImage, width, null, suffix);
    }

    /**
     * <code>scaleHeightPng</code>
     * <p>The scale height png method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param height        {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale height png return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     */
    public static BufferedImage scaleHeightPng(BufferedImage bufferedImage, Integer height) {
        return scaleHeight(bufferedImage, height, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleHeightJpeg</code>
     * <p>The scale height jpeg method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param height        {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale height jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     */
    public static BufferedImage scaleHeightJpeg(BufferedImage bufferedImage, Integer height) {
        return scaleHeight(bufferedImage, height, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleHeight</code>
     * <p>The scale height method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param height        {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @param suffix        {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale height return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static BufferedImage scaleHeight(BufferedImage bufferedImage, Integer height, String suffix) {
        return scale(bufferedImage, null, height, suffix);
    }

    /**
     * <code>scaleWidthPng</code>
     * <p>The scale width png method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param width       {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale width png return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleWidthPng(InputStream inputStream, Integer width, Integer height) {
        return scaleWidth(inputStream, width, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleWidthJpeg</code>
     * <p>The scale width jpeg method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param width       {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale width jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleWidthJpeg(InputStream inputStream, Integer width, Integer height) {
        return scaleWidth(inputStream, width, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleWidth</code>
     * <p>The scale width method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param width       {@link java.lang.Integer} <p>The width parameter is <code>Integer</code> type.</p>
     * @param suffix      {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale width return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleWidth(InputStream inputStream, Integer width, String suffix) {
        return scale(inputStream, width, null, suffix);
    }

    /**
     * <code>scaleHeightPng</code>
     * <p>The scale height png method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale height png return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleHeightPng(InputStream inputStream, Integer height) {
        return scaleHeight(inputStream, height, UtilConstants.PNG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleHeightJpeg</code>
     * <p>The scale height jpeg method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale height jpeg return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleHeightJpeg(InputStream inputStream, Integer height) {
        return scaleHeight(inputStream, height, UtilConstants.JPEG_IMAGE_SUFFIX);
    }

    /**
     * <code>scaleHeight</code>
     * <p>The scale height method.</p>
     * @param inputStream {@link java.io.InputStream} <p>The input stream parameter is <code>InputStream</code> type.</p>
     * @param height      {@link java.lang.Integer} <p>The height parameter is <code>Integer</code> type.</p>
     * @param suffix      {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The scale height return object is <code>BufferedImage</code> type.</p>
     * @see java.io.InputStream
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage scaleHeight(InputStream inputStream, Integer height, String suffix) {
        return scale(inputStream, null, height, suffix);
    }

    /**
     * <code>rgbR</code>
     * <p>The rgb r method.</p>
     * @param rgb int <p>The rgb parameter is <code>int</code> type.</p>
     * @return int <p>The rgb r return object is <code>int</code> type.</p>
     */
    public static int rgbR(int rgb) {
        return (rgb & 0xff0000) >> 16;
    }

    /**
     * <code>rgbG</code>
     * <p>The rgb g method.</p>
     * @param rgb int <p>The rgb parameter is <code>int</code> type.</p>
     * @return int <p>The rgb g return object is <code>int</code> type.</p>
     */
    public static int rgbG(int rgb) {
        return (rgb & 0xff0000) >> 16;
    }

    /**
     * <code>rgbB</code>
     * <p>The rgb b method.</p>
     * @param rgb int <p>The rgb parameter is <code>int</code> type.</p>
     * @return int <p>The rgb b return object is <code>int</code> type.</p>
     */
    public static int rgbB(int rgb) {
        return (rgb & 0xff0000) >> 16;
    }

    /**
     * <code>typeImage</code>
     * <p>The type image method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param type          int <p>The type parameter is <code>int</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The type image return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage typeImage(BufferedImage bufferedImage, int type) {
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        BufferedImage typeImage = new BufferedImage(width, height, type);
        Graphics2D graphics2D = (Graphics2D) typeImage.getGraphics();
        graphics2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
        graphics2D.dispose();
        return typeImage;
    }

    /**
     * <code>rgbaImage</code>
     * <p>The rgba image method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The rgba image return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage rgbaImage(BufferedImage bufferedImage) {
        return typeImage(bufferedImage, BufferedImage.TYPE_4BYTE_ABGR);
    }

    /**
     * <code>binaryImage</code>
     * <p>The binary image method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The binary image return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage binaryImage(BufferedImage bufferedImage) {
        return typeImage(bufferedImage, BufferedImage.TYPE_BYTE_BINARY);
    }

    /**
     * <code>signature</code>
     * <p>The signature method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @param backgroundRgb int <p>The background rgb parameter is <code>int</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The signature return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage signature(BufferedImage bufferedImage, int backgroundRgb) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage rgbaImage = rgbaImage(bufferedImage);
        int alpha;
        List<Integer> xCoordinates = new ArrayList<>();
        List<Integer> yCoordinates = new ArrayList<>();
        for (int x = rgbaImage.getMinX(); x < width; x++) {
            for (int y = rgbaImage.getMinY(); y < height; y++) {
                int contentRgb = rgbaImage.getRGB(x, y);
                if (contentRgb != backgroundRgb) {
                    xCoordinates.add(x);
                    yCoordinates.add(y);
                }
                int contentR = rgbR(contentRgb);
                int contentG = rgbG(contentRgb);
                int contentB = rgbB(contentRgb);
                int backgroundR = rgbR(backgroundRgb);
                int backgroundG = rgbG(backgroundRgb);
                int backgroundB = rgbB(backgroundRgb);
                /* a为色差范围值，渐变色边缘处理，数值需要具体测试，50左右的效果比较可以 */
                int a = 45;
                if (Math.abs(backgroundR - contentR) < a && Math.abs(backgroundG - contentG) < a && Math.abs(backgroundB - contentB) < a) {
                    alpha = 0;
                } else {
                    alpha = 255;
                }
                contentRgb = (alpha << 24) | (contentRgb & 0x00ffffff);
                rgbaImage.setRGB(x, y, contentRgb);
            }
        }
        List<Integer> xCoordinateList = xCoordinates.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        List<Integer> yCoordinateList = yCoordinates.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        xCoordinateList.sort(Comparator.comparingInt(value -> value));
        yCoordinateList.sort(Comparator.comparingInt(value -> value));
        int minX = xCoordinateList.get(0);
        int maxX = xCoordinateList.get(xCoordinateList.size() - 1);
        int minY = yCoordinateList.get(0);
        int maxY = yCoordinateList.get(yCoordinateList.size() - 1);
        int subWidth = maxX - minX;
        int subHeight = maxY - minY;
        return rgbaImage.getSubimage(minX, minY, subWidth, subHeight);
    }

    /**
     * <code>signature</code>
     * <p>The signature method.</p>
     * @param bufferedImage {@link java.awt.image.BufferedImage} <p>The buffered image parameter is <code>BufferedImage</code> type.</p>
     * @return {@link java.awt.image.BufferedImage} <p>The signature return object is <code>BufferedImage</code> type.</p>
     * @see java.awt.image.BufferedImage
     */
    public static BufferedImage signature(BufferedImage bufferedImage) {
        return signature(bufferedImage, -1);
    }
}
