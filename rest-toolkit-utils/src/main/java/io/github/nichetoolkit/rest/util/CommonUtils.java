package io.github.nichetoolkit.rest.util;

/**
 * <code>CommonUtils</code>
 * <p>The common utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class CommonUtils {

    /**
     * <code>substring</code>
     * <p>The substring method.</p>
     * @param content {@link java.lang.String} <p>The content parameter is <code>String</code> type.</p>
     * @param limit   {@link java.lang.Integer} <p>The limit parameter is <code>Integer</code> type.</p>
     * @return {@link java.lang.String} <p>The substring return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
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
