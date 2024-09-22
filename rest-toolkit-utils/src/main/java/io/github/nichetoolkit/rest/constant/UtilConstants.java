package io.github.nichetoolkit.rest.constant;

import java.awt.*;
import java.security.SecureRandom;

/**
 * <code>UtilConstants</code>
 * <p>The type util constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface UtilConstants {

    /**
     * <code>ROOT_PREFIX</code>
     * {@link java.lang.String} <p>the constant <code>ROOT_PREFIX</code> field.</p>
     * @see java.lang.String
     */
    String ROOT_PREFIX = "classpath:";

    /**
     * <code>STATIC_PREFIX</code>
     * {@link java.lang.String} <p>the constant <code>STATIC_PREFIX</code> field.</p>
     * @see java.lang.String
     */
    String STATIC_PREFIX = "static";

    /**
     * <code>EMPTY_PREFIX</code>
     * {@link java.lang.String} <p>the constant <code>EMPTY_PREFIX</code> field.</p>
     * @see java.lang.String
     */
    String EMPTY_PREFIX = "";

    /**
     * <code>SECURE_RANDOM</code>
     * {@link java.security.SecureRandom} <p>the constant <code>SECURE_RANDOM</code> field.</p>
     * @see java.security.SecureRandom
     */
    SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * <code>BASE_SYMBOL</code>
     * {@link java.lang.String} <p>the constant <code>BASE_SYMBOL</code> field.</p>
     * @see java.lang.String
     */
    String BASE_SYMBOL = "!@#$%^&*()[]{}\";:+~·=-/,.<>?\\|";

    /**
     * <code>BASE_STRING_UPPER</code>
     * {@link java.lang.String} <p>the constant <code>BASE_STRING_UPPER</code> field.</p>
     * @see java.lang.String
     */
    String BASE_STRING_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * <code>BASE_STRING_LOWER</code>
     * {@link java.lang.String} <p>the constant <code>BASE_STRING_LOWER</code> field.</p>
     * @see java.lang.String
     */
    String BASE_STRING_LOWER = BASE_STRING_UPPER.toLowerCase();

    /**
     * <code>BASE_REGEX</code>
     * {@link java.lang.String} <p>the constant <code>BASE_REGEX</code> field.</p>
     * @see java.lang.String
     */
    String BASE_REGEX = "_";

    /**
     * <code>BASE_NUMBER</code>
     * {@link java.lang.String} <p>the constant <code>BASE_NUMBER</code> field.</p>
     * @see java.lang.String
     */
    String BASE_NUMBER = "0123456789";

    /**
     * <code>BASE_STRING</code>
     * {@link java.lang.String} <p>the constant <code>BASE_STRING</code> field.</p>
     * @see java.lang.String
     */
    String BASE_STRING = BASE_NUMBER.concat(BASE_STRING_LOWER).concat(BASE_STRING_UPPER);

    /**
     * <code>MIN_LENGTH</code>
     * {@link java.lang.Integer} <p>the constant <code>MIN_LENGTH</code> field.</p>
     * @see java.lang.Integer
     */
    Integer MIN_LENGTH = 3;

    /**
     * <code>DEFAULT_IMAGE_WIDTH</code>
     * {@link java.lang.Integer} <p>the constant <code>DEFAULT_IMAGE_WIDTH</code> field.</p>
     * @see java.lang.Integer
     */
    Integer DEFAULT_IMAGE_WIDTH = 70;

    /**
     * <code>DEFAULT_IMAGE_HEIGHT</code>
     * {@link java.lang.Integer} <p>the constant <code>DEFAULT_IMAGE_HEIGHT</code> field.</p>
     * @see java.lang.Integer
     */
    Integer DEFAULT_IMAGE_HEIGHT = 25;

    /**
     * <code>DEFAULT_IMAGE_COLOR</code>
     * {@link java.awt.Color} <p>the constant <code>DEFAULT_IMAGE_COLOR</code> field.</p>
     * @see java.awt.Color
     */
    Color DEFAULT_IMAGE_COLOR = Color.WHITE;

    /**
     * <code>FONT_NAME_ARRAY</code>
     * {@link java.lang.String} <p>the constant <code>FONT_NAME_ARRAY</code> field.</p>
     * @see java.lang.String
     */
    String[] FONT_NAME_ARRAY = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312", "Arial" };

    /**
     * <code>AFFINE_TRANSFORM_ARRAY</code>
     * {@link java.lang.Integer} <p>the constant <code>AFFINE_TRANSFORM_ARRAY</code> field.</p>
     * @see java.lang.Integer
     */
    Integer[] AFFINE_TRANSFORM_ARRAY = { 0, 15, 20, 30, 35, 40 };

    /**
     * <code>STYLE_ARRAY</code>
     * <p>the constant <code>STYLE_ARRAY</code> field.</p>
     */
    int[] STYLE_ARRAY = { Font.PLAIN, Font.BOLD, Font.ITALIC};

    /**
     * <code>LOCALHOST_IPV4</code>
     * {@link java.lang.String} <p>the constant <code>LOCALHOST_IPV4</code> field.</p>
     * @see java.lang.String
     */
    String LOCALHOST_IPV4 = "127.0.0.1";

    /**
     * <code>LOCALHOST_IPV6</code>
     * {@link java.lang.String} <p>the constant <code>LOCALHOST_IPV6</code> field.</p>
     * @see java.lang.String
     */
    String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    /**
     * <code>IP_REGEX</code>
     * {@link java.lang.String} <p>the constant <code>IP_REGEX</code> field.</p>
     * @see java.lang.String
     */
    String IP_REGEX = ",";

    /**
     * <code>SUFFIX_REGEX</code>
     * {@link java.lang.String} <p>the constant <code>SUFFIX_REGEX</code> field.</p>
     * @see java.lang.String
     */
    String SUFFIX_REGEX = ".";

    /**
     * <code>ZIP_SUFFIX</code>
     * {@link java.lang.String} <p>the constant <code>ZIP_SUFFIX</code> field.</p>
     * @see java.lang.String
     */
    String ZIP_SUFFIX = "zip";

    /**
     * <code>IMAGE_SUFFIX</code>
     * {@link java.lang.String} <p>the constant <code>IMAGE_SUFFIX</code> field.</p>
     * @see java.lang.String
     */
    String IMAGE_SUFFIX = "jpeg";

    /**
     * <code>UNKNOWN_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>UNKNOWN_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String UNKNOWN_HEADER = "unknown";

    /**
     * <code>X_REAL_IP_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>X_REAL_IP_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String X_REAL_IP_HEADER = "X-Real-IP";

    /**
     * <code>X_FORWARDED_FOR_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>X_FORWARDED_FOR_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";

    /**
     * <code>PROXY_CLIENT_IP_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>PROXY_CLIENT_IP_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String PROXY_CLIENT_IP_HEADER = "Proxy-Client-IP";

    /**
     * <code>WL_PROXY_CLIENT_IP_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>WL_PROXY_CLIENT_IP_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String WL_PROXY_CLIENT_IP_HEADER = "WL-Proxy-Client-IP";

    /**
     * <code>HTTP_CLIENT_IP_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>HTTP_CLIENT_IP_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String HTTP_CLIENT_IP_HEADER = "HTTP_CLIENT_IP";

    /**
     * <code>HTTP_X_FORWARDED_FOR_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>HTTP_X_FORWARDED_FOR_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String HTTP_X_FORWARDED_FOR_HEADER = "HTTP_X_FORWARDED_FOR";

    /**
     * <code>CONTENT_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>CONTENT_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String CONTENT_HEADER = "content-disposition";

    /**
     * <code>USER_AGENT_HEADER</code>
     * {@link java.lang.String} <p>the constant <code>USER_AGENT_HEADER</code> field.</p>
     * @see java.lang.String
     */
    String USER_AGENT_HEADER = "User-Agent";

    /**
     * <code>FILENAME_CONTENT</code>
     * {@link java.lang.String} <p>the constant <code>FILENAME_CONTENT</code> field.</p>
     * @see java.lang.String
     */
    String FILENAME_CONTENT = "attachment;filename=";

    /**
     * <code>FILENAME_UTF_8_CONTENT</code>
     * {@link java.lang.String} <p>the constant <code>FILENAME_UTF_8_CONTENT</code> field.</p>
     * @see java.lang.String
     */
    String FILENAME_UTF_8_CONTENT= "attachment;filename*=UTF-8''";
}
