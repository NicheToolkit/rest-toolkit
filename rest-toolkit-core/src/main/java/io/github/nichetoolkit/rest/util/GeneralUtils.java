package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <code>GeneralUtils</code>
 * <p>The type general utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class GeneralUtils {

    /**
     * <code>LINE_PATTERN</code>
     * {@link java.util.regex.Pattern} <p>the constant <code>LINE_PATTERN</code> field.</p>
     * @see java.util.regex.Pattern
     */
    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    /**
     * <code>CAMEL_PATTERN</code>
     * {@link java.util.regex.Pattern} <p>the constant <code>CAMEL_PATTERN</code> field.</p>
     * @see java.util.regex.Pattern
     */
    private static final Pattern CAMEL_PATTERN = Pattern.compile("[A-Z]");

    /**
     * <code>isNotEmpty</code>
     * <p>the not empty method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the not empty return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public static boolean isNotEmpty(@Nullable Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof Character) {
            return ((Character) object) != Character.MIN_VALUE;
        } else if (object instanceof Byte) {
            return Byte.parseByte(object.toString()) != 0;
        } else if (object instanceof Short) {
            return Short.parseShort(object.toString()) != 0;
        } else if (object instanceof Integer) {
            return Integer.parseInt(object.toString()) != 0;
        } else if (object instanceof Float) {
            return Float.parseFloat(object.toString()) != 0F;
        } else if (object instanceof Double) {
            return Double.parseDouble(object.toString()) != 0D;
        } else if (object instanceof Long) {
            return Long.parseLong(object.toString()) != 0L;
        } else if (object instanceof BigInteger) {
            return ((BigInteger) object).compareTo(BigInteger.ZERO) != 0;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).compareTo(BigDecimal.ZERO) != 0;
        } else if (object instanceof Number) {
            return ((Number) object).longValue() != 0L;
        } else if (object instanceof Boolean) {
            return true;
        } else if (object instanceof String) {
            return !((String) object).trim().isEmpty();
        } else if (object instanceof StringBuffer) {
            return !((StringBuffer) object).toString().trim().isEmpty();
        } else if (object instanceof StringBuilder) {
            return !((StringBuilder) object).toString().trim().isEmpty();
        } else if (object instanceof List) {
            return !((List<?>) object).isEmpty();
        } else if (object instanceof Set) {
            return !((Set<?>) object).isEmpty();
        } else if (object instanceof Map) {
            return !((Map<?, ?>) object).isEmpty();
        } else if (object instanceof Collection) {
            return !((Collection<?>) object).isEmpty();
        } else if (object instanceof Iterator) {
            return ((Iterator<?>) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) > 0;
        } else {
            return true;
        }
    }

    /**
     * <code>isValid</code>
     * <p>the valid method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the valid return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public static boolean isValid(@Nullable Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof Character) {
            return ((Character) object) > 0;
        } else if (object instanceof Byte) {
            return Byte.parseByte(object.toString()) > 0;
        } else if (object instanceof Short) {
            return Short.parseShort(object.toString()) > 0;
        } else if (object instanceof Integer) {
            return Integer.parseInt(object.toString()) > 0;
        } else if (object instanceof Float) {
            return Float.parseFloat(object.toString()) > 0F;
        } else if (object instanceof Double) {
            return Double.parseDouble(object.toString()) > 0D;
        } else if (object instanceof Long) {
            return Long.parseLong(object.toString()) > 0L;
        } else if (object instanceof BigInteger) {
            return ((BigInteger) object).compareTo(BigInteger.ZERO) > 0;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).compareTo(BigDecimal.ZERO) > 0;
        } else if (object instanceof Number) {
            return ((Number) object).longValue() > 0L;
        } else if (object instanceof Boolean) {
            return true;
        } else if (object instanceof String) {
            return !((String) object).trim().isEmpty();
        } else if (object instanceof StringBuffer) {
            return !((StringBuffer) object).toString().trim().isEmpty();
        } else if (object instanceof StringBuilder) {
            return !((StringBuilder) object).toString().trim().isEmpty();
        } else if (object instanceof List) {
            return !((List<?>) object).isEmpty();
        } else if (object instanceof Set) {
            return !((Set<?>) object).isEmpty();
        } else if (object instanceof Map) {
            return !((Map<?, ?>) object).isEmpty();
        } else if (object instanceof Collection) {
            return !((Collection<?>) object).isEmpty();
        } else if (object instanceof Iterator) {
            return ((Iterator<?>) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) > 0;
        } else {
            return true;
        }
    }

    /**
     * <code>isUsable</code>
     * <p>the usable method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the usable return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public static boolean isUsable(@Nullable Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof String) {
            return !((String) object).trim().isEmpty();
        } else if (object instanceof StringBuffer) {
            return !((StringBuffer) object).toString().trim().isEmpty();
        } else if (object instanceof StringBuilder) {
            return !((StringBuilder) object).toString().trim().isEmpty();
        } else if (object instanceof List) {
            return !((List<?>) object).isEmpty();
        } else if (object instanceof Set) {
            return !((Set<?>) object).isEmpty();
        } else if (object instanceof Map) {
            return !((Map<?, ?>) object).isEmpty();
        } else if (object instanceof Collection) {
            return !((Collection<?>) object).isEmpty();
        } else if (object instanceof Iterator) {
            return ((Iterator<?>) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) > 0;
        } else {
            return true;
        }
    }

    /**
     * <code>isNotNull</code>
     * <p>the not null method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the not null return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     */
    public static boolean isNotNull(@Nullable Object object) {
        return !Objects.isNull(object);
    }

    /**
     * <code>isEmpty</code>
     * <p>the empty method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the empty return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public static boolean isEmpty(@Nullable Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Character) {
            return ((Character) object) == 0;
        } else if (object instanceof Byte) {
            return Byte.parseByte(object.toString()) == 0;
        } else if (object instanceof Short) {
            return Short.parseShort(object.toString()) == 0;
        } else if (object instanceof Integer) {
            return Integer.parseInt(object.toString()) == 0;
        } else if (object instanceof Float) {
            return Float.parseFloat(object.toString()) == 0F;
        } else if (object instanceof Double) {
            return Double.parseDouble(object.toString()) == 0D;
        } else if (object instanceof Long) {
            return Long.parseLong(object.toString()) == 0L;
        } else if (object instanceof BigInteger) {
            return ((BigInteger) object).compareTo(BigInteger.ZERO) == 0;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).compareTo(BigDecimal.ZERO) == 0;
        } else if (object instanceof Number) {
            return ((Number) object).longValue() == 0L;
        } else if (object instanceof Boolean) {
            return false;
        } else if (object instanceof String) {
            return ((String) object).trim().isEmpty();
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().isEmpty();
        } else if (object instanceof StringBuilder) {
            return ((StringBuilder) object).toString().trim().isEmpty();
        } else if (object instanceof List) {
            return ((List<?>) object).isEmpty();
        } else if (object instanceof Set) {
            return ((Set<?>) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map<?, ?>) object).isEmpty();
        } else if (object instanceof Collection) {
            return ((Collection<?>) object).isEmpty();
        } else if (object instanceof Iterator) {
            return !((Iterator<?>) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else {
            return false;
        }
    }

    /**
     * <code>isInvalid</code>
     * <p>the invalid method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the invalid return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public static boolean isInvalid(@Nullable Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Character) {
            return ((Character) object) <= 0;
        } else if (object instanceof Byte) {
            return Byte.parseByte(object.toString()) <= 0;
        } else if (object instanceof Short) {
            return Short.parseShort(object.toString()) <= 0;
        } else if (object instanceof Integer) {
            return Integer.parseInt(object.toString()) <= 0;
        } else if (object instanceof Float) {
            return Float.parseFloat(object.toString()) <= 0F;
        } else if (object instanceof Double) {
            return Double.parseDouble(object.toString()) <= 0D;
        } else if (object instanceof Long) {
            return Long.parseLong(object.toString()) <= 0L;
        } else if (object instanceof BigInteger) {
            return ((BigInteger) object).compareTo(BigInteger.ZERO) <= 0;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).compareTo(BigDecimal.ZERO) <= 0;
        } else if (object instanceof Number) {
            return ((Number) object).longValue() <= 0L;
        } else if (object instanceof Boolean) {
            return false;
        } else if (object instanceof String) {
            return ((String) object).trim().isEmpty();
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().isEmpty();
        } else if (object instanceof StringBuilder) {
            return ((StringBuilder) object).toString().trim().isEmpty();
        } else if (object instanceof List) {
            return ((List<?>) object).isEmpty();
        } else if (object instanceof Set) {
            return ((Set<?>) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map<?, ?>) object).isEmpty();
        } else if (object instanceof Collection) {
            return ((Collection<?>) object).isEmpty();
        } else if (object instanceof Iterator) {
            return !((Iterator<?>) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else {
            return false;
        }
    }

    /**
     * <code>isUnusable</code>
     * <p>the unusable method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the unusable return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("Duplicates")
    public static boolean isUnusable(@Nullable Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {
            return ((String) object).trim().isEmpty();
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().isEmpty();
        } else if (object instanceof StringBuilder) {
            return ((StringBuilder) object).toString().trim().isEmpty();
        } else if (object instanceof List) {
            return ((List<?>) object).isEmpty();
        } else if (object instanceof Set) {
            return ((Set<?>) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map<?, ?>) object).isEmpty();
        } else if (object instanceof Collection) {
            return ((Collection<?>) object).isEmpty();
        } else if (object instanceof Iterator) {
            return !((Iterator<?>) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else {
            return false;
        }
    }

    /**
     * <code>isNull</code>
     * <p>the null method.</p>
     * @param object {@link java.lang.Object} <p>the object parameter is <code>Object</code> type.</p>
     * @return boolean <p>the null return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     */
    public static boolean isNull(@Nullable Object object) {
        return Objects.isNull(object);
    }

    /**
     * <code>printStackTrace</code>
     * <p>the stack trace method.</p>
     * @param exception {@link java.lang.Throwable} <p>the exception parameter is <code>Throwable</code> type.</p>
     * @return {@link java.lang.String} <p>the stack trace return object is <code>String</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.String
     */
    public static String printStackTrace(Throwable exception) {
        return printStackTrace(exception, false);
    }

    /**
     * <code>printStackTrace</code>
     * <p>the stack trace method.</p>
     * @param exception {@link java.lang.Throwable} <p>the exception parameter is <code>Throwable</code> type.</p>
     * @param isLogging boolean <p>the is logging parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.String} <p>the stack trace return object is <code>String</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.String
     */
    public static String printStackTrace(Throwable exception, boolean isLogging) {
        return printStackTrace(log, exception, true);
    }

    /**
     * <code>printStackTrace</code>
     * <p>the stack trace method.</p>
     * @param logger    {@link org.slf4j.Logger} <p>the logger parameter is <code>Logger</code> type.</p>
     * @param exception {@link java.lang.Throwable} <p>the exception parameter is <code>Throwable</code> type.</p>
     * @param isLogging boolean <p>the is logging parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.String} <p>the stack trace return object is <code>String</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.Throwable
     * @see java.lang.String
     */
    public static String printStackTrace(Logger logger, Throwable exception, boolean isLogging) {
        StackTraceElement stackTraceElement = exception.getStackTrace()[0];
        Integer line = stackTraceElement.getLineNumber();
        String resource = stackTraceElement.getClassName();
        String errorClass = exception.getClass().getName();
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            exception.printStackTrace(printWriter);
            String stackTrace = stringWriter.toString();
            if (isLogging) {
                logger.error("{} [{}] {}: {} \n{}", resource, line, errorClass, exception.getMessage(), stackTrace);
            }
            return String.format("%s [%s] %s: %s \n%s", resource, line, errorClass, exception.getMessage(), stackTrace);
        } catch (IOException ignored) {
            if (isLogging) {
                logger.error("{} [{}] {}: {}", resource, line, errorClass, exception.getMessage());
            }
            return String.format("%s [%s] %s: %s", resource, line, errorClass, exception.getMessage());
        }
    }

    /**
     * <code>randomHex</code>
     * <p>the hex method.</p>
     * @param size int <p>the size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>the hex return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String randomHex(int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(Integer.toHexString(new SecureRandom().nextInt(16)));
        }
        return result.toString().toUpperCase();
    }

    /**
     * <code>lineToCamel</code>
     * <p>the to camel method.</p>
     * @param line {@link java.lang.String} <p>the line parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the to camel return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String lineToCamel(String line) {
        line = line.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(line);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


    /**
     * <code>camelToLine</code>
     * <p>the to line method.</p>
     * @param camel {@link java.lang.String} <p>the camel parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the to line return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String camelToLine(String camel) {
        Matcher matcher = CAMEL_PATTERN.matcher(camel);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * <code>underline</code>
     * <p>the method.</p>
     * @param underline {@link java.lang.String} <p>the underline parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String underline(String underline) {
        if (isEmpty(underline)) return underline;
        final int size;
        final char[] chars;
        final StringBuilder stringBuilder = new StringBuilder(
                (size = (chars = underline.toCharArray()).length) * 3 / 2 + 1);
        char character;
        for (int i = 0; i < size; i++) {
            character = chars[i];
            if (Character.isUpperCase(character)) {
                stringBuilder.append('_').append(Character.toLowerCase(character));
            } else {
                stringBuilder.append(character);
            }
        }
        return stringBuilder.charAt(0) == '_' ? stringBuilder.substring(1) : stringBuilder.toString();
    }

    /**
     * <code>abbreviate</code>
     * <p>the method.</p>
     * @param abbreviate {@link java.lang.String} <p>the abbreviate parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String abbreviate(String abbreviate) {
        return abbreviate(abbreviate, 0, false);
    }

    /**
     * <code>abbreviate</code>
     * <p>the method.</p>
     * @param abbreviate  {@link java.lang.String} <p>the abbreviate parameter is <code>String</code> type.</p>
     * @param length      int <p>the length parameter is <code>int</code> type.</p>
     * @param isUnderline boolean <p>the is underline parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String abbreviate(String abbreviate, int length, boolean isUnderline) {
        if (isEmpty(abbreviate)) return abbreviate;
        if (length <= 0) length = abbreviate.length();
        if (abbreviate.contains("_")) {
            abbreviate = lineToCamel(abbreviate);
        }
        String firstCase = abbreviate.substring(0, 1);
        String otherCase = abbreviate.substring(1);
        abbreviate = firstCase.toUpperCase().concat(otherCase);
        final int size;
        final char[] chars;
        final StringBuilder stringBuilder = new StringBuilder(
                (size = (chars = abbreviate.toCharArray()).length) * 3 / 2 + 1);
        char character;
        int index = 0;
        for (int i = 0; i < size && index != length; i++) {
            character = chars[i];
            if (Character.isUpperCase(character)) {
                index++;
                if (isUnderline) {
                    stringBuilder.append('_').append(Character.toLowerCase(character));
                } else {
                    stringBuilder.append(Character.toLowerCase(character));
                }
            }
        }
        return stringBuilder.charAt(0) == '_' ? stringBuilder.substring(1) : stringBuilder.toString();
    }

    /**
     * <code>uuid</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String uuid() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long lsb = random.nextLong();
        long msb = random.nextLong();
        byte[] buf = new byte[32];
        format(lsb, buf, 20, 12);
        format(lsb >>> 48, buf, 16, 4);
        format(msb, buf, 12, 4);
        format(msb >>> 16, buf, 8, 4);
        format(msb >>> 32, buf, 0, 8);
        return new String(buf, StandardCharsets.UTF_8);
    }

    /**
     * <code>format</code>
     * <p>the method.</p>
     * @param val    long <p>the val parameter is <code>long</code> type.</p>
     * @param buf    byte <p>the buf parameter is <code>byte</code> type.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     * @param len    int <p>the len parameter is <code>int</code> type.</p>
     */
    private static void format(long val, byte[] buf, int offset, int len) {
        int charPos = offset + len;
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[--charPos] = SystemConstants.DIGITS[((int) val) & mask];
            val >>>= 4;
        } while (charPos > offset);
    }
}
