package io.github.nichetoolkit.rest.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * <code>GeneralUtils</code>
 * <p>The type general utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ValueUtils {

    /**
     * <code>transforms</code>
     * <p>the method.</p>
     * @param value    {@link Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param multiple {@link Long} <p>the multiple parameter is <code>Long</code> type.</p>
     * @return {@link Double} <p>the return object is <code>Double</code> type.</p>
     * @see Long
     * @see Double
     */
    public static Double transforms(Long value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return value.doubleValue() / multiple.doubleValue();
        }
        return null;
    }

    /**
     * <code>transforms</code>
     * <p>the method.</p>
     * @param value    {@link Double} <p>the value parameter is <code>Double</code> type.</p>
     * @param multiple {@link Long} <p>the multiple parameter is <code>Long</code> type.</p>
     * @return {@link Long} <p>the return object is <code>Long</code> type.</p>
     * @see Double
     * @see Long
     */
    public static Long transforms(Double value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return (long) (value * multiple);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>the method.</p>
     * @param value {@link Long} <p>the value parameter is <code>Long</code> type.</p>
     * @return {@link BigDecimal} <p>the return object is <code>BigDecimal</code> type.</p>
     * @see Long
     * @see BigDecimal
     */
    public static BigDecimal transform(Long value) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>the method.</p>
     * @param value    {@link Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param multiple {@link Long} <p>the multiple parameter is <code>Long</code> type.</p>
     * @return {@link BigDecimal} <p>the return object is <code>BigDecimal</code> type.</p>
     * @see Long
     * @see BigDecimal
     */
    public static BigDecimal transform(Long value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value).divide(new BigDecimal(multiple), 2, RoundingMode.HALF_UP);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>the method.</p>
     * @param value    {@link Long} <p>the value parameter is <code>Long</code> type.</p>
     * @param multiple {@link Long} <p>the multiple parameter is <code>Long</code> type.</p>
     * @param scale    {@link Integer} <p>the scale parameter is <code>Integer</code> type.</p>
     * @return {@link BigDecimal} <p>the return object is <code>BigDecimal</code> type.</p>
     * @see Long
     * @see Integer
     * @see BigDecimal
     */
    public static BigDecimal transform(Long value, Long multiple, Integer scale) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value).divide(new BigDecimal(multiple), scale, RoundingMode.HALF_UP);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>the method.</p>
     * @param value {@link BigDecimal} <p>the value parameter is <code>BigDecimal</code> type.</p>
     * @return {@link Long} <p>the return object is <code>Long</code> type.</p>
     * @see BigDecimal
     * @see Long
     */
    public static Long transform(BigDecimal value) {
        if (GeneralUtils.isValid(value)) {
            return (long) value.doubleValue();
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>the method.</p>
     * @param value    {@link BigDecimal} <p>the value parameter is <code>BigDecimal</code> type.</p>
     * @param multiple {@link Long} <p>the multiple parameter is <code>Long</code> type.</p>
     * @return {@link Long} <p>the return object is <code>Long</code> type.</p>
     * @see BigDecimal
     * @see Long
     */
    public static Long transform(BigDecimal value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return (long) (value.doubleValue() * multiple);
        }
        return null;
    }

    /**
     * <code>toDouble</code>
     * <p>the double method.</p>
     * @param value {@link Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link Double} <p>the double return object is <code>Double</code> type.</p>
     * @see Object
     * @see Double
     */
    public static Double toDouble(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isDouble(trim)) {
                return Double.valueOf(trim);
            }
        }
        return null;
    }


    /**
     * <code>toBigInteger</code>
     * <p>the big integer method.</p>
     * @param value {@link Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link BigInteger} <p>the big integer return object is <code>BigInteger</code> type.</p>
     * @see Object
     * @see BigInteger
     */
    public static BigInteger toBigInteger(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isBigInteger(trim)) {
                return new BigInteger(trim);
            }
        }
        return null;
    }

    /**
     * <code>toBigDecimal</code>
     * <p>the big decimal method.</p>
     * @param value {@link Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link BigDecimal} <p>the big decimal return object is <code>BigDecimal</code> type.</p>
     * @see Object
     * @see BigDecimal
     */
    public static BigDecimal toBigDecimal(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isBigDecimal(trim)) {
                return new BigDecimal(trim);
            }
        }
        return null;
    }

    /**
     * <code>toInteger</code>
     * <p>the integer method.</p>
     * @param value {@link Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link Integer} <p>the integer return object is <code>Integer</code> type.</p>
     * @see Object
     * @see Integer
     */
    public static Integer toInteger(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isInteger(trim)) {
                return Integer.valueOf(trim);
            }
        }
        return null;
    }

    /**
     * <code>toLong</code>
     * <p>the long method.</p>
     * @param value {@link Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link Long} <p>the long return object is <code>Long</code> type.</p>
     * @see Object
     * @see Long
     */
    public static Long toLong(Object value) {
        if (GeneralUtils.isValid(value)) {
            String trim = value.toString().trim();
            if (isLong(trim)) {
                return Long.valueOf(trim);
            }
        }
        return null;
    }

    /**
     * <code>toTime</code>
     * <p>the time method.</p>
     * @param value {@link Object} <p>the value parameter is <code>Object</code> type.</p>
     * @return {@link Long} <p>the time return object is <code>Long</code> type.</p>
     * @see Object
     * @see Long
     */
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


    /**
     * <code>isBigInteger</code>
     * <p>the big integer method.</p>
     * @param value {@link String} <p>the value parameter is <code>String</code> type.</p>
     * @return boolean <p>the big integer return object is <code>boolean</code> type.</p>
     * @see String
     */
    public static boolean isBigInteger(String value) {
        try {
            new BigInteger(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * <code>isBigDecimal</code>
     * <p>the big decimal method.</p>
     * @param value {@link String} <p>the value parameter is <code>String</code> type.</p>
     * @return boolean <p>the big decimal return object is <code>boolean</code> type.</p>
     * @see String
     */
    public static boolean isBigDecimal(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * <code>isInteger</code>
     * <p>the integer method.</p>
     * @param value {@link String} <p>the value parameter is <code>String</code> type.</p>
     * @return boolean <p>the integer return object is <code>boolean</code> type.</p>
     * @see String
     */
    public static boolean isInteger(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * <code>isLong</code>
     * <p>the long method.</p>
     * @param value {@link String} <p>the value parameter is <code>String</code> type.</p>
     * @return boolean <p>the long return object is <code>boolean</code> type.</p>
     * @see String
     */
    public static boolean isLong(String value) {
        try {
            Long.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * <code>isDouble</code>
     * <p>the double method.</p>
     * @param value {@link String} <p>the value parameter is <code>String</code> type.</p>
     * @return boolean <p>the double return object is <code>boolean</code> type.</p>
     * @see String
     */
    public static boolean isDouble(String value) {
        try {
            Double.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * <code>randomHex</code>
     * <p>the hex method.</p>
     * @param size int <p>the size parameter is <code>int</code> type.</p>
     * @return {@link String} <p>the hex return object is <code>String</code> type.</p>
     * @see String
     */
    public static String randomHex(int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(Integer.toHexString(new SecureRandom().nextInt(16)));
        }
        return result.toString().toUpperCase();
    }

}
