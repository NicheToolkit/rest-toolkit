package io.github.nichetoolkit.rest.worker.img;

import io.github.nichetoolkit.rest.error.image.ImageWriteException;
import io.github.nichetoolkit.rest.helper.ImageHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * <code>ImageVerify</code>
 * <p>The image verify class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@Getter
@Setter
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class ImageVerify implements Serializable {
    /**
     * <code>content</code>
     * {@link java.lang.String} <p>The <code>content</code> field.</p>
     * @see java.lang.String
     */
    private String content;
    /**
     * <code>image</code>
     * {@link java.awt.image.BufferedImage} <p>The <code>image</code> field.</p>
     * @see java.awt.image.BufferedImage
     */
    private BufferedImage image;

    /**
     * <code>ImageVerify</code>
     * <p>Instantiates a new image verify.</p>
     */
    public ImageVerify() {
    }

    /**
     * <code>ImageVerify</code>
     * <p>Instantiates a new image verify.</p>
     * @param content {@link java.lang.String} <p>The content parameter is <code>String</code> type.</p>
     * @param image   {@link java.awt.image.BufferedImage} <p>The image parameter is <code>BufferedImage</code> type.</p>
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public ImageVerify(String content, BufferedImage image) {
        this.content = content;
        this.image = image;
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public void writePng(OutputStream outputStream) throws ImageWriteException {
        ImageHelper.writePng(image, outputStream);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public void writeJpeg(OutputStream outputStream) throws ImageWriteException {
        ImageHelper.writeJpeg(image, outputStream);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>The output stream parameter is <code>OutputStream</code> type.</p>
     * @param suffix       {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see java.io.OutputStream
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public void write(OutputStream outputStream, String suffix) throws ImageWriteException {
        ImageHelper.write(image, outputStream, suffix);
    }

    /**
     * <code>writePng</code>
     * <p>The write png method.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public void writePng(HttpServletResponse response) throws ImageWriteException {
        ImageHelper.writePng(image, response);
    }

    /**
     * <code>writeJpeg</code>
     * <p>The write jpeg method.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see jakarta.servlet.http.HttpServletResponse
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public void writeJpeg(HttpServletResponse response) throws ImageWriteException {
        ImageHelper.writeJpeg(image, response);
    }

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param response {@link jakarta.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @param suffix   {@link java.lang.String} <p>The suffix parameter is <code>String</code> type.</p>
     * @throws ImageWriteException {@link io.github.nichetoolkit.rest.error.image.ImageWriteException} <p>The image write exception is <code>ImageWriteException</code> type.</p>
     * @see jakarta.servlet.http.HttpServletResponse
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.image.ImageWriteException
     */
    public void write(HttpServletResponse response, String suffix) throws ImageWriteException {
        ImageHelper.write(image, response, suffix);
    }

}
