package io.github.nichetoolkit.rest.worker.img;

import io.github.nichetoolkit.rest.constant.UtilConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

public class ImageWorker {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static synchronized ImageVerify random() {
        return random(UtilConstants.DEFAULT_IMAGE_WIDTH, UtilConstants.DEFAULT_IMAGE_HEIGHT);
    }

    public static synchronized ImageVerify random(Integer width, Integer height) {
        return random(width, height, UtilConstants.DEFAULT_IMAGE_COLOR);
    }

    public static synchronized ImageVerify random(Integer width, Integer height, Color bgColor) {
        return random(width, height, BufferedImage.TYPE_INT_RGB, bgColor, 170, 200);
    }

    public static synchronized ImageVerify random(Integer width, Integer height, int type, Color bgColor, int fgStart, int fgEnd) {
        BufferedImage image = new BufferedImage(width, height, type);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, width, height);

        /* 干扰线条 */
        for (int i = 0; i < 150; ++i) {
            int startX = SECURE_RANDOM.nextInt(width);
            int startY = SECURE_RANDOM.nextInt(height);
            int endX = SECURE_RANDOM.nextInt(width);
            int endY = SECURE_RANDOM.nextInt(height);
            graphics.setColor(randomColor(fgStart, fgEnd));
            graphics.drawLine(startX, startY, endX, endY);
        }
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
        return new ImageVerify(contentBuilder.toString(), image);
    }

    public static Character randomChar() {
        int index = SECURE_RANDOM.nextInt(UtilConstants.BASE_STRING.length());
        return UtilConstants.BASE_STRING.charAt(index);
    }

    public static Color randomColor() {
        return randomColor(0, 150);
    }


    public static Color randomColor(Integer min, Integer max) {
        if (min > 255) {
            min = 255;
        }
        if (max > 255) {
            max = 255;
        }
        int red = SECURE_RANDOM.nextInt(max - min) + min;
        int green = SECURE_RANDOM.nextInt(max - min) + min;
        int blue = SECURE_RANDOM.nextInt(max - min) + min;
        return new Color(red, green, blue);
    }

    public static Font randomFont() {
        int index = SECURE_RANDOM.nextInt(UtilConstants.FONT_NAME_ARRAY.length);
        String fontName = UtilConstants.FONT_NAME_ARRAY[index];
        int style = SECURE_RANDOM.nextInt(4);
        int size = SECURE_RANDOM.nextInt(5) + 20;
        //noinspection MagicConstant
        return new Font(fontName, style, size);
    }

    public static Integer randomTransform() {
        int index = SECURE_RANDOM.nextInt(UtilConstants.AFFINE_TRANSFORM_ARRAY.length);
        return UtilConstants.AFFINE_TRANSFORM_ARRAY[index];
    }
}
