package io.github.nichetoolkit.rest.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * <code>ValueUtils</code>
 * <p>The value utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class ValueUtils {

    /**
     * <code>transforms</code>
     * <p>The transforms method.</p>
     * @param value    {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @param multiple {@link java.lang.Long} <p>The multiple parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Double} <p>The transforms return object is <code>Double</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.Double
     */
    public static Double transforms(Long value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return value.doubleValue() / multiple.doubleValue();
        }
        return null;
    }

    /**
     * <code>transforms</code>
     * <p>The transforms method.</p>
     * @param value    {@link java.lang.Double} <p>The value parameter is <code>Double</code> type.</p>
     * @param multiple {@link java.lang.Long} <p>The multiple parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>The transforms return object is <code>Long</code> type.</p>
     * @see java.lang.Double
     * @see java.lang.Long
     */
    public static Long transforms(Double value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return (long) (value * multiple);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>The transform method.</p>
     * @param value {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @return {@link java.math.BigDecimal} <p>The transform return object is <code>BigDecimal</code> type.</p>
     * @see java.lang.Long
     * @see java.math.BigDecimal
     */
    public static BigDecimal transform(Long value) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>The transform method.</p>
     * @param value    {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @param multiple {@link java.lang.Long} <p>The multiple parameter is <code>Long</code> type.</p>
     * @return {@link java.math.BigDecimal} <p>The transform return object is <code>BigDecimal</code> type.</p>
     * @see java.lang.Long
     * @see java.math.BigDecimal
     */
    public static BigDecimal transform(Long value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value).divide(new BigDecimal(multiple), 2, RoundingMode.HALF_UP);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>The transform method.</p>
     * @param value    {@link java.lang.Long} <p>The value parameter is <code>Long</code> type.</p>
     * @param multiple {@link java.lang.Long} <p>The multiple parameter is <code>Long</code> type.</p>
     * @param scale    {@link java.lang.Integer} <p>The scale parameter is <code>Integer</code> type.</p>
     * @return {@link java.math.BigDecimal} <p>The transform return object is <code>BigDecimal</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.Integer
     * @see java.math.BigDecimal
     */
    public static BigDecimal transform(Long value, Long multiple, Integer scale) {
        if (GeneralUtils.isValid(value)) {
            return BigDecimal.valueOf(value).divide(new BigDecimal(multiple), scale, RoundingMode.HALF_UP);
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>The transform method.</p>
     * @param value {@link java.math.BigDecimal} <p>The value parameter is <code>BigDecimal</code> type.</p>
     * @return {@link java.lang.Long} <p>The transform return object is <code>Long</code> type.</p>
     * @see java.math.BigDecimal
     * @see java.lang.Long
     */
    public static Long transform(BigDecimal value) {
        if (GeneralUtils.isValid(value)) {
            return (long) value.doubleValue();
        }
        return null;
    }

    /**
     * <code>transform</code>
     * <p>The transform method.</p>
     * @param value    {@link java.math.BigDecimal} <p>The value parameter is <code>BigDecimal</code> type.</p>
     * @param multiple {@link java.lang.Long} <p>The multiple parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>The transform return object is <code>Long</code> type.</p>
     * @see java.math.BigDecimal
     * @see java.lang.Long
     */
    public static Long transform(BigDecimal value, Long multiple) {
        if (GeneralUtils.isValid(value)) {
            return (long) (value.doubleValue() * multiple);
        }
        return null;
    }

    /**
     * <code>toDouble</code>
     * <p>The to double method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Double} <p>The to double return object is <code>Double</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Double
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
     * <p>The to big integer method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link java.math.BigInteger} <p>The to big integer return object is <code>BigInteger</code> type.</p>
     * @see java.lang.Object
     * @see java.math.BigInteger
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
     * <p>The to big decimal method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link java.math.BigDecimal} <p>The to big decimal return object is <code>BigDecimal</code> type.</p>
     * @see java.lang.Object
     * @see java.math.BigDecimal
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
     * <p>The to integer method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Integer} <p>The to integer return object is <code>Integer</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Integer
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
     * <p>The to long method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Long} <p>The to long return object is <code>Long</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Long
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
     * <p>The to time method.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @return {@link java.lang.Long} <p>The to time return object is <code>Long</code> type.</p>
     * @see java.lang.Object
     * @see java.lang.Long
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
     * <p>The is big integer method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return boolean <p>The is big integer return object is <code>boolean</code> type.</p>
     * @see java.lang.String
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
     * <p>The is big decimal method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return boolean <p>The is big decimal return object is <code>boolean</code> type.</p>
     * @see java.lang.String
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
     * <p>The is integer method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return boolean <p>The is integer return object is <code>boolean</code> type.</p>
     * @see java.lang.String
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
     * <p>The is long method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return boolean <p>The is long return object is <code>boolean</code> type.</p>
     * @see java.lang.String
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
     * <p>The is double method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return boolean <p>The is double return object is <code>boolean</code> type.</p>
     * @see java.lang.String
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
     * <p>The random hex method.</p>
     * @param size int <p>The size parameter is <code>int</code> type.</p>
     * @return {@link java.lang.String} <p>The random hex return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public static String randomHex(int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(Integer.toHexString(new SecureRandom().nextInt(16)));
        }
        return result.toString().toUpperCase();
    }

}
