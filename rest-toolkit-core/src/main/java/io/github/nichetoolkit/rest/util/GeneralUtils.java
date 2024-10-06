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
     * {@link java.util.regex.Pattern} <p>The constant <code>LINE_PATTERN</code> field.</p>
     * @see java.util.regex.Pattern
     */
    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    /**
     * <code>CAMEL_PATTERN</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>CAMEL_PATTERN</code> field.</p>
     * @see java.util.regex.Pattern
     */
    private static final Pattern CAMEL_PATTERN = Pattern.compile("[a-z]");

    /**
     * <code>PASCAL_PATTERN</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>PASCAL_PATTERN</code> field.</p>
     * @see java.util.regex.Pattern
     */
    private static final Pattern PASCAL_PATTERN = Pattern.compile("[A-Z]");

    /**
     * <code>isNotEmpty</code>
     * <p>The not empty method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The not empty return object is <code>boolean</code> type.</p>
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
     * <p>The valid method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The valid return object is <code>boolean</code> type.</p>
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
     * <p>The usable method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The usable return object is <code>boolean</code> type.</p>
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
     * <p>The not null method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The not null return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     */
    public static boolean isNotNull(@Nullable Object object) {
        return !Objects.isNull(object);
    }

    /**
     * <code>isEmpty</code>
     * <p>The empty method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The empty return object is <code>boolean</code> type.</p>
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
     * <p>The invalid method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The invalid return object is <code>boolean</code> type.</p>
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
     * <p>The unusable method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The unusable return object is <code>boolean</code> type.</p>
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
     * <p>The null method.</p>
     * @param object {@link java.lang.Object} <p>The object parameter is <code>Object</code> type.</p>
     * @return boolean <p>The null return object is <code>boolean</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.Nullable
     */
    public static boolean isNull(@Nullable Object object) {
        return Objects.isNull(object);
    }

    /**
     * <code>printStackTrace</code>
     * <p>The stack trace method.</p>
     * @param exception {@link java.lang.Throwable} <p>The exception parameter is <code>Throwable</code> type.</p>
     * @return {@link java.lang.String} <p>The stack trace return object is <code>String</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.String
     */
    public static String printStackTrace(Throwable exception) {
        return printStackTrace(exception, false);
    }

    /**
     * <code>printStackTrace</code>
     * <p>The stack trace method.</p>
     * @param exception {@link java.lang.Throwable} <p>The exception parameter is <code>Throwable</code> type.</p>
     * @param isLogging boolean <p>The is logging parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.String} <p>The stack trace return object is <code>String</code> type.</p>
     * @see java.lang.Throwable
     * @see java.lang.String
     */
    public static String printStackTrace(Throwable exception, boolean isLogging) {
        return printStackTrace(log, exception, true);
    }

    /**
     * <code>printStackTrace</code>
     * <p>The stack trace method.</p>
     * @param logger    {@link org.slf4j.Logger} <p>The logger parameter is <code>Logger</code> type.</p>
     * @param exception {@link java.lang.Throwable} <p>The exception parameter is <code>Throwable</code> type.</p>
     * @param isLogging boolean <p>The is logging parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.String} <p>The stack trace return object is <code>String</code> type.</p>
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
     * <p>The hex method.</p>
     * @param size int <p>The size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The hex return object is <code>String</code> type.</p>
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
     * <p>The to camel method.</p>
     * @param line {@link java.lang.String} <p>The line parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The to camel return object is <code>String</code> type.</p>
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
     * <p>The to line method.</p>
     * @param camel {@link java.lang.String} <p>The camel parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The to line return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String camelToLine(String camel) {
        Matcher matcher = PASCAL_PATTERN.matcher(camel);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * <code>camelCase</code>
     * <p>The case method.</p>
     * @param pascalCase {@link java.lang.String} <p>The pascal case parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The case return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String camelCase(String pascalCase) {
        char firstCase = Character.toLowerCase(pascalCase.charAt(0));
        if (pascalCase.length() == 1) {
            return String.valueOf(firstCase);
        }
        return firstCase + pascalCase.substring(1);
    }

    /**
     * <code>pascalCase</code>
     * <p>The case method.</p>
     * @param camelCase {@link java.lang.String} <p>The camel case parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The case return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String pascalCase(String camelCase) {
        char firstCase = Character.toUpperCase(camelCase.charAt(0));
        if (camelCase.length() == 1) {
            return String.valueOf(firstCase);
        }
        return firstCase + camelCase.substring(1);
    }

    /**
     * <code>underline</code>
     * <p>The method.</p>
     * @param underline {@link java.lang.String} <p>The underline parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
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
     * <p>The method.</p>
     * @param abbreviate {@link java.lang.String} <p>The abbreviate parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String abbreviate(String abbreviate) {
        return abbreviate(abbreviate, 0, false);
    }

    /**
     * <code>abbreviate</code>
     * <p>The method.</p>
     * @param abbreviate  {@link java.lang.String} <p>The abbreviate parameter is <code>String</code> type.</p>
     * @param length      int <p>The length parameter is <code>int</code> type.</p>
     * @param isUnderline boolean <p>The is underline parameter is <code>boolean</code> type.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
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
     * <p>The method.</p>
     * @return {@link java.lang.String} <p>The return object is <code>String</code> type.</p>
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
     * <p>The method.</p>
     * @param val    long <p>The val parameter is <code>long</code> type.</p>
     * @param buf    byte <p>The buf parameter is <code>byte</code> type.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     * @param len    int <p>The len parameter is <code>int</code> type.</p>
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
