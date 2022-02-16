package io.github.nichetoolkit.rest.worker.image;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * <p>ImageVerifyWorker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class ImageVerifyWorker {

    private Integer width;
    private Integer height;
    private Color color;

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

    @Autowired
    public ImageVerifyWorker() {
        this.width = UtilConstants.DEFAULT_IMAGE_WIDTH;
        this.height = UtilConstants.DEFAULT_IMAGE_HEIGHT;
        this.color = UtilConstants.DEFAULT_IMAGE_COLOR;
    }

    public ImageVerifyWorker(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.color = UtilConstants.DEFAULT_IMAGE_COLOR;
    }

    public ImageVerifyWorker(Integer width, Integer height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public ImageVerify random() {
       return randoms(this.width,this.height,this.color);
    }

    public static synchronized ImageVerify randoms() {
        return randoms(UtilConstants.DEFAULT_IMAGE_WIDTH,UtilConstants.DEFAULT_IMAGE_HEIGHT,UtilConstants.DEFAULT_IMAGE_COLOR);
    }

    public static synchronized ImageVerify randoms(Integer width, Integer height) {
        if (GeneralUtils.isNotEmpty(width) && GeneralUtils.isNotEmpty(height)) {
            return randoms(width,height,UtilConstants.DEFAULT_IMAGE_COLOR);
        } else {
            return randoms(UtilConstants.DEFAULT_IMAGE_WIDTH,UtilConstants.DEFAULT_IMAGE_HEIGHT,UtilConstants.DEFAULT_IMAGE_COLOR);
        }
    }

    public static synchronized ImageVerify randoms(Integer width, Integer height, Color color) {
        Integer x = Optional.ofNullable(width).orElse(UtilConstants.DEFAULT_IMAGE_WIDTH);
        Integer y = Optional.ofNullable(height).orElse(UtilConstants.DEFAULT_IMAGE_HEIGHT);
        Color c = Optional.ofNullable(color).orElse(UtilConstants.DEFAULT_IMAGE_COLOR);
        ImageVerify.Builder builder = new ImageVerify.Builder();
        builder.width(x).height(y).color(c);
        /* 渲染图片 */
        BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(c);
        graphics.fillRect(0,0,x, y);
        /* 干扰线条 */
        for (int i = 0; i < 150; ++i) {
            int startX = UtilConstants.SECURE_RANDOM.nextInt(x);
            int startY = UtilConstants.SECURE_RANDOM.nextInt(y);
            int endX = UtilConstants.SECURE_RANDOM.nextInt(x);
            int endY = UtilConstants.SECURE_RANDOM.nextInt(y);
            graphics.setColor(randomColor(170, 200));
            graphics.drawLine(startX, startY, endX, endY);
        }
        /* 验证码 */
        StringBuilder contentBuilder = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            String character = String.valueOf(randomChar());
            contentBuilder.append(character);
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.rotate(randomTransform() * 3.14 / 180, 15 * i + 3.5, 5);
            graphics.setTransform(affineTransform);
            graphics.setFont(randomFont());
            graphics.setColor(randomColor());
            graphics.drawString(character, 15 * i + 7, 16);
        }
        builder.content(contentBuilder.toString()).image(image);
        return new ImageVerify(builder);
    }

    protected static synchronized Character randomChar() {
        int index = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.BASE_STRING.length());
        return UtilConstants.BASE_STRING.charAt(index);
    }

    protected static synchronized Color randomColor() {
        return randomColor(0,150);
    }


    protected static synchronized Color randomColor(Integer min, Integer max) {
        if (min > 255) {
            min = 255;
        }
        if (max > 255) {
            max = 255;
        }
        int red = UtilConstants.SECURE_RANDOM.nextInt(max - min) + min;
        int green = UtilConstants.SECURE_RANDOM.nextInt(max - min) + min;
        int blue = UtilConstants.SECURE_RANDOM.nextInt(max - min) + min;
        return new Color(red, green, blue);
    }

    protected static synchronized Font randomFont() {
        int index = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.FONT_NAME_ARRAY.length);
        String fontName = UtilConstants.FONT_NAME_ARRAY[index];
        int style = UtilConstants.SECURE_RANDOM.nextInt(4);
        int size = UtilConstants.SECURE_RANDOM.nextInt(5) + 20;
        //noinspection MagicConstant
        return new Font(fontName, style, size);
    }

    protected static synchronized Integer randomTransform() {
        int index = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.AFFINE_TRANSFORM_ARRAY.length);
        return UtilConstants.AFFINE_TRANSFORM_ARRAY[index];
    }
}
