package io.github.nichetoolkit.rest.image;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import lombok.Builder;
import org.springframework.lang.NonNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * <p>ImageVerify</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ImageVerify implements Serializable {
    private String content;
    private BufferedImage image;

    public ImageVerify() {
    }

    public ImageVerify(String content, BufferedImage image) {
        this.content = content;
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void write(OutputStream outputStream) {
        write(UtilConstants.IMAGE_SUFFIX,outputStream);
    }

    public void write(String formatName,OutputStream outputStream) {
        try {
            ImageIO.write(image, formatName, outputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
