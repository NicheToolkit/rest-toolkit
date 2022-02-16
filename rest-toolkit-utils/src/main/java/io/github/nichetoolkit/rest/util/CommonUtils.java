package io.github.nichetoolkit.rest.util;

/**
 * <p>CommonUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class CommonUtils {

    public static String substring(String content, Integer limit) {
        if (GeneralUtils.isEmpty(content)) {
            return null;
        }
        String result;
        String contentTrim = content.replaceAll("\r", "")
                .replaceAll("\n", "")
//                .replaceAll(" ", "")
                .trim();
        if (contentTrim.length() > limit) {
            result = contentTrim.substring(0, limit).concat("...");
        } else {
            result = contentTrim;
        }
        return result;
    }
}
