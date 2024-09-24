package io.github.nichetoolkit.rest.image;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * <code>ImageVerify</code>
 * <p>The type image verify class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Data
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Data
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class ImageVerify implements Serializable {
    /**
     * <code>content</code>
     * {@link java.lang.String} <p>the <code>content</code> field.</p>
     * @see java.lang.String
     */
    private String content;
    /**
     * <code>image</code>
     * {@link java.awt.image.BufferedImage} <p>the <code>image</code> field.</p>
     * @see java.awt.image.BufferedImage
     */
    private BufferedImage image;

    /**
     * <code>ImageVerify</code>
     * Instantiates a new image verify.
     */
    public ImageVerify() {
    }

    /**
     * <code>ImageVerify</code>
     * Instantiates a new image verify.
     * @param content {@link java.lang.String} <p>the content parameter is <code>String</code> type.</p>
     * @param image   {@link java.awt.image.BufferedImage} <p>the image parameter is <code>BufferedImage</code> type.</p>
     * @see java.lang.String
     * @see java.awt.image.BufferedImage
     */
    public ImageVerify(String content, BufferedImage image) {
        this.content = content;
        this.image = image;
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.io.OutputStream
     */
    public void write(OutputStream outputStream) {
        write(UtilConstants.IMAGE_SUFFIX,outputStream);
    }

    /**
     * <code>write</code>
     * <p>the method.</p>
     * @param formatName   {@link java.lang.String} <p>the format name parameter is <code>String</code> type.</p>
     * @param outputStream {@link java.io.OutputStream} <p>the output stream parameter is <code>OutputStream</code> type.</p>
     * @see java.lang.String
     * @see java.io.OutputStream
     */
    public void write(String formatName,OutputStream outputStream) {
        try {
            ImageIO.write(image, formatName, outputStream);
        } catch (IOException exception) {
            log.error("It is failed during image writing to output stream!", exception);
            GeneralUtils.printStackTrace(exception);
        }
    }

}
