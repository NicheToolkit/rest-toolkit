package io.github.nichetoolkit.rest.worker.image;

import org.springframework.lang.NonNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * <p>ImageVerify</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */

public class ImageVerify implements Serializable {
    private String content;
    private BufferedImage image;
    private Integer width;
    private Integer height;
    private Color color;

    public ImageVerify() {
    }

    public ImageVerify(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public ImageVerify(Integer width, Integer height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public ImageVerify(ImageVerify.Builder builder) {
        this.content = builder.content;
        this.image = builder.image;
        this.width = builder.width;
        this.height = builder.height;
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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static class Builder {
        protected String content;
        protected BufferedImage image;
        protected Integer width;
        protected Integer height;
        protected Color color;

        public Builder() {
        }

        public ImageVerify.Builder content(@NonNull String content) {
            this.content = content;
            return this;
        }

        public ImageVerify.Builder image(BufferedImage image) {
            this.image = image;
            return this;
        }

        public ImageVerify.Builder width(Integer width) {
            this.width = width;
            return this;
        }

        public ImageVerify.Builder height(Integer height) {
            this.height = height;
            return this;
        }

        public ImageVerify.Builder color(Color color) {
            this.color = color;
            return this;
        }

        public ImageVerify.Builder color(Integer R, Integer G, Integer B) {
            this.color = new Color(R,G,B);
            return this;
        }

        public ImageVerify build() {
            return new ImageVerify(this);
        }
    }

}
