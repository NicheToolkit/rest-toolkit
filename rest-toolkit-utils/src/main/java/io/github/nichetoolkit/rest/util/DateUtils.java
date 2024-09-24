package io.github.nichetoolkit.rest.util;


import io.github.nichetoolkit.rest.constant.DateConstants;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.helper.DateHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

/**
 * <code>DateUtils</code>
 * <p>The type date utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("unused")
public class DateUtils {

    /**
     * <code>formatDate</code>
     * <p>the date method.</p>
     * @param date {@link java.lang.Long} <p>the date parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>the date return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
    public static String formatDate(Long date) {
        try {
            return DateHelper.formatDate(date);
        } catch (ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date format date has error！date: {}，format: {}，error: {}", date.toString(), DateConstants.DATE_FORMAT_10, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>formatTime</code>
     * <p>the time method.</p>
     * @param time {@link java.lang.Long} <p>the time parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.String} <p>the time return object is <code>String</code> type.</p>
     * @see java.lang.Long
     * @see java.lang.String
     */
    public static String formatTime(Long time) {
        try {
            return DateHelper.formatTime(time);
        } catch (ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date format time has error！time: {}，format: {}，error: {}", time.toString(), DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>formatDate</code>
     * <p>the date method.</p>
     * @param date {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @return {@link java.lang.String} <p>the date return object is <code>String</code> type.</p>
     * @see java.util.Date
     * @see java.lang.String
     */
    public static String formatDate(Date date) {
        try {
            return DateHelper.formatDate(date);
        } catch (ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date format date has error! date: {}，format: {}，error: {}", date.toString(), DateConstants.DATE_FORMAT_10, exception.getMessage());
            return null;
        }
    }


    /**
     * <code>formatTime</code>
     * <p>the time method.</p>
     * @param time {@link java.util.Date} <p>the time parameter is <code>Date</code> type.</p>
     * @return {@link java.lang.String} <p>the time return object is <code>String</code> type.</p>
     * @see java.util.Date
     * @see java.lang.String
     */
    public static String formatTime(Date time) {
        try {
            return DateHelper.formatTime(time);
        } catch (ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date format time has error! time: {}，format: {}，error: {}", time.toString(), DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>format</code>
     * <p>the method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param format {@link java.lang.String} <p>the format parameter is <code>String</code> type.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.util.Date
     * @see java.lang.String
     */
    public static String format(Date date, String format) {
        try {
            return DateHelper.format(date, format);
        } catch (ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date format has error！date: {}，format: {}，error: {}", date.toString(), format, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>parseDate</code>
     * <p>the date method.</p>
     * @param date {@link java.lang.String} <p>the date parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>the date return object is <code>Date</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     */
    public static Date parseDate(String date) {
        try {
            return DateHelper.parseDate(date);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date parse date has error！date: {}，format: {}，error: {}", date, DateConstants.DATE_FORMAT_10, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>parseDateTime</code>
     * <p>the date time method.</p>
     * @param datetime {@link java.lang.String} <p>the datetime parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>the date time return object is <code>Date</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     * @see java.lang.Deprecated
     * @deprecated <p>the parseDateTime method has be deprecated.</p>
     */
    @Deprecated
    public static Date parseDateTime(String datetime) {
        try {
            return DateHelper.parseDateTime(datetime);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date parse date time has error！time: {}，format: {}，error: {}", datetime, DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>parseTime</code>
     * <p>the time method.</p>
     * @param time {@link java.lang.String} <p>the time parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>the time return object is <code>Date</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     */
    public static Date parseTime(String time) {
        try {
            return DateHelper.parseTime(time);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date parse time has error！time: {}，format: {}，error: {}", time, DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>parse</code>
     * <p>the method.</p>
     * @param datetime {@link java.lang.String} <p>the datetime parameter is <code>String</code> type.</p>
     * @param format   {@link java.lang.String} <p>the format parameter is <code>String</code> type.</p>
     * @return {@link java.util.Date} <p>the return object is <code>Date</code> type.</p>
     * @see java.lang.String
     * @see java.util.Date
     */
    public static Date parse(String datetime, String format) {
        try {
            return DateHelper.parse(datetime, format);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            GeneralUtils.printStackTrace(exception);
            log.error("date parse has error！date time: {}，format: {}，error: {}", datetime, format, exception.getMessage());
            return null;
        }
    }

    /**
     * <code>today</code>
     * <p>the method.</p>
     * @return {@link java.util.Date} <p>the return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        clear(calendar);
        return calendar.getTime();
    }

    /**
     * <code>getDay</code>
     * <p>the day getter method.</p>
     * @param date {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @return {@link java.util.Date} <p>the day return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date getDay(final Date date) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clear(calendar);
        return calendar.getTime();
    }

    /**
     * <code>getMonth</code>
     * <p>the month getter method.</p>
     * @param date {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @return {@link java.util.Date} <p>the month return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date getMonth(final Date date) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clear(calendar);
        return calendar.getTime();
    }

    /**
     * <code>getYear</code>
     * <p>the year getter method.</p>
     * @param date {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @return {@link java.util.Date} <p>the year return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date getYear(final Date date) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        clear(calendar);
        return calendar.getTime();
    }

    /**
     * <code>getDateInt</code>
     * <p>the date int getter method.</p>
     * @param date  {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param field int <p>the field parameter is <code>int</code> type.</p>
     * @return {@link java.lang.Integer} <p>the date int return object is <code>Integer</code> type.</p>
     * @see java.util.Date
     * @see java.lang.Integer
     */
    public static Integer getDateInt(final Date date, int field) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (field == Calendar.MONTH) {
            return calendar.get(field) + 1;
        } else {
            return calendar.get(field);
        }
    }

    /**
     * <code>addYears</code>
     * <p>the years method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param amount int <p>the amount parameter is <code>int</code> type.</p>
     * @return {@link java.util.Date} <p>the years return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * <code>addMonths</code>
     * <p>the months method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param amount int <p>the amount parameter is <code>int</code> type.</p>
     * @return {@link java.util.Date} <p>the months return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * <code>addDays</code>
     * <p>the days method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param amount int <p>the amount parameter is <code>int</code> type.</p>
     * @return {@link java.util.Date} <p>the days return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * <code>addHours</code>
     * <p>the hours method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param amount int <p>the amount parameter is <code>int</code> type.</p>
     * @return {@link java.util.Date} <p>the hours return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * <code>addMinutes</code>
     * <p>the minutes method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param amount int <p>the amount parameter is <code>int</code> type.</p>
     * @return {@link java.util.Date} <p>the minutes return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * <code>addSeconds</code>
     * <p>the seconds method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param amount int <p>the amount parameter is <code>int</code> type.</p>
     * @return {@link java.util.Date} <p>the seconds return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * <code>clear</code>
     * <p>the method.</p>
     * @param calendar {@link java.util.Calendar} <p>the calendar parameter is <code>Calendar</code> type.</p>
     * @see java.util.Calendar
     */
    private static void clear(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * <code>add</code>
     * <p>the method.</p>
     * @param date   {@link java.util.Date} <p>the date parameter is <code>Date</code> type.</p>
     * @param field  int <p>the field parameter is <code>int</code> type.</p>
     * @param amount int <p>the amount parameter is <code>int</code> type.</p>
     * @return {@link java.util.Date} <p>the return object is <code>Date</code> type.</p>
     * @see java.util.Date
     */
    private static Date add(final Date date, final int field, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

}