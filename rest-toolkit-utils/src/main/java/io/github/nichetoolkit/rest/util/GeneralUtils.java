package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>GeneralUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class GeneralUtils {

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static boolean isNotEmpty(Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof Short) {
            return Short.valueOf(object.toString()) != 0;
        } else if (object instanceof Integer) {
            return Integer.valueOf(object.toString()) != 0;
        } else if (object instanceof Long) {
            return Long.valueOf(object.toString()) != 0L;
        } else if (object instanceof String) {
            return ((String) object).trim().length() != 0;
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().length() != 0;
        } else if (object instanceof Boolean) {
            return true;
        } else if (object instanceof BigInteger) {
            return ((BigInteger) object).compareTo(BigInteger.ZERO) != 0;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).compareTo(BigDecimal.ZERO) != 0;
        } else if (object instanceof List) {
            return !((List) object).isEmpty();
        } else if (object instanceof Set) {
            return !((Set) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).size() > 0;
        } else if (object instanceof Collection) {
            return !((Collection) object).isEmpty();
        } else if (object instanceof Iterator) {
            return ((Iterator) object).hasNext();
        } else if (object.getClass().isArray()) {
            return !Arrays.asList(object).isEmpty() && Array.getLength(object) > 0;
        } else {
            return true;
        }
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static boolean isValid(Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof Short) {
            return true;
        } else if (object instanceof Integer) {
            return true;
        } else if (object instanceof Long) {
            return true;
        } else if (object instanceof String) {
            return ((String) object).trim().length() != 0;
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().length() != 0;
        } else if (object instanceof Boolean) {
            return true;
        } else if (object instanceof BigInteger) {
            return true;
        } else if (object instanceof BigDecimal) {
            return true;
        } else if (object instanceof List) {
            return !((List) object).isEmpty();
        } else if (object instanceof Set) {
            return !((Set) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).size() > 0;
        } else if (object instanceof Collection) {
            return !((Collection) object).isEmpty();
        } else if (object instanceof Iterator) {
            return ((Iterator) object).hasNext();
        } else if (object.getClass().isArray()) {
            return !Arrays.asList(object).isEmpty() && Array.getLength(object) > 0;
        } else {
            return true;
        }
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Short) {
            return Short.valueOf(object.toString()) == 0;
        } else if (object instanceof Integer) {
            return Integer.valueOf(object.toString()) == 0;
        } else if (object instanceof Long) {
            return Long.valueOf(object.toString()) == 0L;
        } else if (object instanceof String) {
            return ((String) object).trim().length() == 0;
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().length() == 0;
        } else if (object instanceof Boolean) {
            return false;
        } else if (object instanceof BigInteger) {
            return ((BigInteger) object).compareTo(BigInteger.ZERO) == 0;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).compareTo(BigDecimal.ZERO) == 0;
        } else if (object instanceof List) {
            return ((List) object).isEmpty();
        } else if (object instanceof Set) {
            return ((Set) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).size() == 0;
        }  else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else if (object instanceof Iterator) {
            return !((Iterator) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Arrays.asList(object).isEmpty() || Array.getLength(object) == 0;
        } else {
            return false;
        }
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static boolean isInvalid(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Short) {
            return false;
        } else if (object instanceof Integer) {
            return false;
        } else if (object instanceof Long) {
            return false;
        } else if (object instanceof String) {
            return ((String) object).trim().length() == 0;
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().length() == 0;
        } else if (object instanceof Boolean) {
            return false;
        } else if (object instanceof BigInteger) {
            return false;
        } else if (object instanceof BigDecimal) {
            return false;
        } else if (object instanceof List) {
            return ((List) object).isEmpty();
        } else if (object instanceof Set) {
            return ((Set) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).size() == 0;
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else if (object instanceof Iterator) {
            return !((Iterator) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Arrays.asList(object).isEmpty() || Array.getLength(object) == 0;
        } else {
            return false;
        }
    }

    public static Double transforms(Long value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return value.doubleValue() / multiple.doubleValue();
        }
        return null;
    }

    public static Long transforms(Double value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return (long) (value * multiple);
        }
        return null;
    }

    public static BigDecimal transform(Long value) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value);
        }
        return null;
    }

    public static BigDecimal transform(Long value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value).divide(new BigDecimal(multiple),2, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }

    public static BigDecimal transform(Long value, Long multiple, Integer scale) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value).divide(new BigDecimal(multiple),scale, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }

    public static Long transform(BigDecimal value) {
        if (GeneralUtils.isValid(value)) {
            return (long) value.doubleValue();
        }
        return null;
    }

    public static Long transform(BigDecimal value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return (long) (value.doubleValue() * multiple);
        }
        return null;
    }

    public static Double toDouble(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isDouble(trim)) {
                return Double.valueOf(trim);
            }
        }
        return null;
    }


    public static BigInteger toBigInteger(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isBigInteger(trim)) {
                return new BigInteger(trim);
            }
        }
        return null;
    }

    public static BigDecimal toBigDecimal(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isBigDecimal(trim)) {
                return new BigDecimal(trim);
            }
        }
        return null;
    }

    public static Integer toInteger(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isInteger(trim)) {
                return Integer.valueOf(trim);
            }
        }
        return null;
    }

    public static Long toLong(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isLong(trim)) {
                return Long.valueOf(trim);
            }
        }
        return null;
    }

    public static Long toTime(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isLong(trim)) {
                return Long.valueOf(trim);
            } else {
                Date date = DateUtils.parseTime(trim);
                return Optional.ofNullable(date).map(Date::getTime).orElse(null);
            }
        }
        return null;
    }


    public static boolean isBigInteger(String value) {
        try {
            new BigInteger(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent BigInteger fail！", value);
            return false;
        }
    }

    public static boolean isBigDecimal(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent BigDecimal fail！", value);
            return false;
        }
    }

    public static boolean isInteger(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent integer fail！", value);
            return false;
        }
    }

    public static boolean isLong(String value) {
        try {
            Long.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent long fail！", value);
            return false;
        }
    }

    public static boolean isDouble(String value) {
        try {
            Double.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            log.debug(" {} to convent double fail！", value);
            return false;
        }
    }

    public static String randomHex(int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(Integer.toHexString(new SecureRandom().nextInt(16)));
        }
        return result.toString().toUpperCase();
    }

    public static String underline(String underline) {
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

    private static void format(long val, byte[] buf, int offset, int len) {
        int charPos = offset + len;
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[--charPos] = UtilConstants.UUID_DIGITS[((int) val) & mask];
            val >>>= 4;
        } while (charPos > offset);
    }
}
