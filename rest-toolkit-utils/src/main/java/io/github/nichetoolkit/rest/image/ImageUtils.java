package io.github.nichetoolkit.rest.image;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * <p>ImageVerifyWorker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class ImageUtils {


    public static synchronized ImageVerify randoms() {

        /* 渲染图片 */
        BufferedImage image = new BufferedImage(UtilConstants.DEFAULT_IMAGE_WIDTH, UtilConstants.DEFAULT_IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(UtilConstants.DEFAULT_IMAGE_COLOR);
        graphics.fillRect(0,0,UtilConstants.DEFAULT_IMAGE_WIDTH, UtilConstants.DEFAULT_IMAGE_HEIGHT);

        /* 干扰线条 */
        for (int i = 0; i < 150; ++i) {
            int startX = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.DEFAULT_IMAGE_WIDTH);
            int startY = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.DEFAULT_IMAGE_HEIGHT);
            int endX = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.DEFAULT_IMAGE_WIDTH);
            int endY = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.DEFAULT_IMAGE_HEIGHT);
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
        return ImageVerify.builder().content(contentBuilder.toString()).image(image).build();
    }

    protected static Character randomChar() {
        int index = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.BASE_STRING.length());
        return UtilConstants.BASE_STRING.charAt(index);
    }

    protected static Color randomColor() {
        return randomColor(0,150);
    }


    protected static Color randomColor(Integer min, Integer max) {
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

    protected static Font randomFont() {
        int index = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.FONT_NAME_ARRAY.length);
        String fontName = UtilConstants.FONT_NAME_ARRAY[index];
        int style = UtilConstants.SECURE_RANDOM.nextInt(4);
        int size = UtilConstants.SECURE_RANDOM.nextInt(5) + 20;
        //noinspection MagicConstant
        return new Font(fontName, style, size);
    }

    protected static Integer randomTransform() {
        int index = UtilConstants.SECURE_RANDOM.nextInt(UtilConstants.AFFINE_TRANSFORM_ARRAY.length);
        return UtilConstants.AFFINE_TRANSFORM_ARRAY[index];
    }
}
