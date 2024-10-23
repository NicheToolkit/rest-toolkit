package io.github.nichetoolkit.rest.helper;


import io.github.nichetoolkit.rest.constant.DateConstants;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <code>DateHelper</code>
 * <p>The date helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("unused")
public class DateHelper {

    /**
     * <code>formatDate</code>
     * <p>The format date method.</p>
     * @param date {@link java.lang.Long} <p>The date parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>The format date return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static String formatDate(Long date) throws ResourceNotFoundException {
        return formatDate(new Date(date));
    }

    /**
     * <code>formatTime</code>
     * <p>The format time method.</p>
     * @param time {@link java.lang.Long} <p>The time parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>The format time return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static String formatTime(Long time) throws ResourceNotFoundException {
        return formatTime(new Date(time));
    }

    /**
     * <code>formatDate</code>
     * <p>The format date method.</p>
     * @param date {@link java.util.Date} <p>The date parameter is <code>Date</code> type.</p>
     * @return {@link java.lang.String} <p>The format date return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.util.Date
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static String formatDate(Date date) throws ResourceNotFoundException {
        return format(date, DateConstants.DATE_FORMAT_10);
    }

    /**
     * <code>formatTime</code>
     * <p>The format time method.</p>
     * @param time {@link java.util.Date} <p>The time parameter is <code>Date</code> type.</p>
     * @return {@link java.lang.String} <p>The format time return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.util.Date
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static String formatTime(Date time) throws ResourceNotFoundException {
        return format(time, DateConstants.DATE_FORMAT_19);
    }


    /**
     * <code>format</code>
     * <p>The format method.</p>
     * @param date   {@link java.util.Date} <p>The date parameter is <code>Date</code> type.</p>
     * @param format {@link java.lang.String} <p>The format parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The format return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.util.Date
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static String format(Date date, String format) throws ResourceNotFoundException {
        return cacheDateFormat(format).format(date);
    }

    /**
     * <code>parseDate</code>
     * <p>The parse date method.</p>
     * @param date {@link java.lang.String} <p>The date parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>The parse date return object is <code>Date</code> type.</p>
     * @throws ParseErrorException       {@link io.github.nichetoolkit.rest.error.natives.ParseErrorException} <p>The parse error exception is <code>ParseErrorException</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     * @see io.github.nichetoolkit.rest.error.natives.ParseErrorException
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static Date parseDate(String date) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(date, DateConstants.DATE_FORMAT_10);
    }

    /**
     * <code>parseDateTime</code>
     * <p>The parse date time method.</p>
     * @param datetime {@link java.lang.String} <p>The datetime parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>The parse date time return object is <code>Date</code> type.</p>
     * @throws ParseErrorException       {@link io.github.nichetoolkit.rest.error.natives.ParseErrorException} <p>The parse error exception is <code>ParseErrorException</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     * @see java.lang.Deprecated
     * @see io.github.nichetoolkit.rest.error.natives.ParseErrorException
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     * @deprecated <p>The parse date time method has be deprecated.</p>
     */
    @Deprecated
    public static Date parseDateTime(String datetime) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(datetime, DateConstants.DATE_FORMAT_19);
    }

    /**
     * <code>parseTime</code>
     * <p>The parse time method.</p>
     * @param time {@link java.lang.String} <p>The time parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>The parse time return object is <code>Date</code> type.</p>
     * @throws ParseErrorException       {@link io.github.nichetoolkit.rest.error.natives.ParseErrorException} <p>The parse error exception is <code>ParseErrorException</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     * @see io.github.nichetoolkit.rest.error.natives.ParseErrorException
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static Date parseTime(String time) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(time, DateConstants.DATE_FORMAT_19);
    }

    /**
     * <code>parse</code>
     * <p>The parse method.</p>
     * @param datetime {@link java.lang.String} <p>The datetime parameter is <code>String</code> type.</p>
     * @param format   {@link java.lang.String} <p>The format parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>The parse return object is <code>Date</code> type.</p>
     * @throws ParseErrorException       {@link io.github.nichetoolkit.rest.error.natives.ParseErrorException} <p>The parse error exception is <code>ParseErrorException</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     * @see io.github.nichetoolkit.rest.error.natives.ParseErrorException
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    public static Date parse(String datetime, String format) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(datetime, format);
    }

    /**
     * <code>parseFormat</code>
     * <p>The parse format method.</p>
     * @param datetime {@link java.lang.String} <p>The datetime parameter is <code>String</code> type.</p>
     * @param format   {@link java.lang.String} <p>The format parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>The parse format return object is <code>Date</code> type.</p>
     * @throws ParseErrorException       {@link io.github.nichetoolkit.rest.error.natives.ParseErrorException} <p>The parse error exception is <code>ParseErrorException</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     * @see io.github.nichetoolkit.rest.error.natives.ParseErrorException
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    private static Date parseFormat(String datetime, String format) throws ParseErrorException, ResourceNotFoundException {
        if (GeneralUtils.isEmpty(format)) {
            format = switchDateFormat(datetime);
        }
        try {
            return cacheDateFormat(format).parse(datetime);
        } catch (Exception exception) {
            throw new ParseErrorException(exception.getMessage());
        }
    }

    /**
     * <code>switchDateFormat</code>
     * <p>The switch date format method.</p>
     * @param datetime {@link java.lang.String} <p>The datetime parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>The switch date format return object is <code>String</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    private static String switchDateFormat(String datetime) throws ResourceNotFoundException {
        int length = datetime.length();
        switch (length) {
            case 10:
                return DateConstants.DATE_FORMAT_10;
            case 14:
                return DateConstants.DATE_FORMAT_14;
            case 17:
                return DateConstants.DATE_FORMAT_17;
            case 19:
                return datetime.contains(DateConstants.MINUS) ? DateConstants.DATE_FORMAT_19 : DateConstants.DATE_FORMAT_19_FORWARD_SLASH;
            case 23:
                return datetime.contains(DateConstants.MINUS) ? DateConstants.DATE_FORMAT_23 : DateConstants.DATE_FORMAT_23_FORWARD_SLASH;
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 18:
                return DateConstants.DATE_FORMAT_18;
            default:
                throw new ResourceNotFoundException(datetime, "can not find date format pattern!");
        }
    }

    /**
     * <code>cacheDateFormat</code>
     * <p>The cache date format method.</p>
     * @param format {@link java.lang.String} <p>The format parameter is <code>String</code> type.</p>
     * @return {@link java.text.DateFormat} <p>The cache date format return object is <code>DateFormat</code> type.</p>
     * @throws ResourceNotFoundException {@link io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException} <p>The resource not found exception is <code>ResourceNotFoundException</code> type.</p>
     * @see java.lang.String
     * @see java.text.DateFormat
     * @see io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException
     */
    private static DateFormat cacheDateFormat(String format) throws ResourceNotFoundException {
        if (GeneralUtils.isEmpty(format)) {
            throw new ResourceNotFoundException("format", "format is not null！");
        }
        Map<String, SimpleDateFormat> formatMap = DateConstants.DATE_FORMAT_THREAD_LOCAL.get();
        SimpleDateFormat formatCached;
        if (formatMap == null) {
            formatMap = new HashMap<>();
            DateConstants.DATE_FORMAT_THREAD_LOCAL.set(formatMap);
        } else {
            formatCached = (formatMap).get(format);
            if (formatCached != null) {
                return formatCached;
            }
        }
        formatCached = new SimpleDateFormat(format);
        formatMap.put(format, formatCached);
        return formatCached;
    }

}