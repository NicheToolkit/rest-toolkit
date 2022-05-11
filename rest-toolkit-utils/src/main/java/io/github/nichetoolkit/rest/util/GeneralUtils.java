package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.constant.UtilConstants;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>GeneralUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class GeneralUtils {

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static boolean isNotEmpty(Object object) {
        if (object == null) {
            return false;
        } else if (object instanceof Integer) {
            return Integer.valueOf(object.toString()) > 0;
        } else if (object instanceof Long) {
            return Long.valueOf(object.toString()) > 0L;
        } else if (object instanceof String) {
            return ((String) object).trim().length() > 0;
        } else if (object instanceof StringBuffer) {
            return ((StringBuffer) object).toString().trim().length() > 0;
        } else if (object instanceof Boolean) {
            return true;
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).doubleValue() > 0d;
        } else if (object instanceof List) {
            return !((List) object).isEmpty();
        } else if (object instanceof Set) {
            return !((Set) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).size() > 0;
        } else if (object instanceof Iterator) {
            return ((Iterator) object).hasNext();
        } else if (object.getClass().isArray()) {
            return !Arrays.asList(object).isEmpty();
        } else {
            return true;
        }
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
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
        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).doubleValue() == 0d;
        } else if (object instanceof List) {
            return ((List) object).isEmpty();
        } else if (object instanceof Set) {
            return ((Set) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).size() == 0;
        } else if (object instanceof Iterator) {
            return !((Iterator) object).hasNext();
        } else if (object.getClass().isArray()) {
            return Arrays.asList(object).isEmpty();
        } else {
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
