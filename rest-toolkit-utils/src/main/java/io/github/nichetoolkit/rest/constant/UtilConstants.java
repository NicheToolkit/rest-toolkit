package io.github.nichetoolkit.rest.constant;

import java.awt.*;
import java.security.SecureRandom;

/**
 * <p>UtilConstants</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface UtilConstants {

    String ROOT_PREFIX = "classpath:";
    
    String STATIC_PREFIX = "static";
    
    String EMPTY_PREFIX = "";

    SecureRandom SECURE_RANDOM = new SecureRandom();

    String BASE_SYMBOL = "!@#$%^&*()[]{}\";:+~·=-/,.<>?\\|";

    String BASE_STRING_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    String BASE_STRING_LOWER = BASE_STRING_UPPER.toLowerCase();

    String BASE_REGEX = "_";

    String BASE_NUMBER = "0123456789";

    String BASE_STRING = BASE_NUMBER.concat(BASE_STRING_LOWER).concat(BASE_STRING_UPPER);

    Integer MIN_LENGTH = 3;

    Integer DEFAULT_IMAGE_WIDTH = 70;

    Integer DEFAULT_IMAGE_HEIGHT = 25;

    Color DEFAULT_IMAGE_COLOR = Color.WHITE;

    String[] FONT_NAME_ARRAY = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312", "Arial" };

    Integer[] AFFINE_TRANSFORM_ARRAY = { 0, 15, 20, 30, 35, 40 };

    int[] STYLE_ARRAY = { Font.PLAIN, Font.BOLD, Font.ITALIC};

    String LOCALHOST_IPV4 = "127.0.0.1";

    String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    String IP_REGEX = ",";

    String SUFFIX_REGEX = ".";

    String ZIP_SUFFIX = "zip";

    String UNKNOWN_HEADER = "unknown";

    String X_REAL_IP_HEADER = "X-Real-IP";

    String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";

    String PROXY_CLIENT_IP_HEADER = "Proxy-Client-IP";

    String WL_PROXY_CLIENT_IP_HEADER = "WL-Proxy-Client-IP";

    String HTTP_CLIENT_IP_HEADER = "HTTP_CLIENT_IP";

    String HTTP_X_FORWARDED_FOR_HEADER = "HTTP_X_FORWARDED_FOR";

    String CONTENT_HEADER = "content-disposition";
    
    String USER_AGENT_HEADER = "User-Agent";
    
    String FILENAME_CONTENT = "attachment;filename=";
    
    String FILENAME_UTF_8_CONTENT= "attachment;filename*=UTF-8''";

    byte[] UUID_DIGITS = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };

    Character[] N_RADIX_DIGITS = {
            'q', 'w', 'e', '8', 'a', 's', 
            '2', 'd', 'z', 'x', '9', 'c', 
            '7', 'p', '5', 'i', 'k', '3', 
            'm', 'j', 'u', 'f', 'r', '4', 
            'v', 'y', 'l', 't', 'n', '6', 
            'b', 'g', 'h'
    };

    Character[] HEX_DIGITS = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F'
    };

    Character N_RADIX_SUPPLY_DIGIT = 'o';
    
    
    Integer N_RADIX_MIN_LENGTH = 6;
}
