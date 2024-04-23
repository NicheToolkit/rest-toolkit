package io.github.nichetoolkit.rest.util;


import io.github.nichetoolkit.rest.constant.DateConstants;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.helper.DateHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>DateUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("unused")
public class DateUtils {

    public static String formatDate(Long date) {
        try {
            return DateHelper.formatDate(date);
        } catch (ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date format date has error！date: {}，format: {}，error: {}", date.toString(), DateConstants.DATE_FORMAT_10, exception.getMessage());
            return null;
        }
    }

    public static String formatTime(Long time) {
        try {
            return DateHelper.formatTime(time);
        } catch (ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date format time has error！time: {}，format: {}，error: {}", time.toString(), DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    public static String formatDate(Date date) {
        try {
            return DateHelper.formatDate(date);
        } catch (ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date format date has error! date: {}，format: {}，error: {}", date.toString(), DateConstants.DATE_FORMAT_10, exception.getMessage());
            return null;
        }
    }


    public static String formatTime(Date time) {
        try {
            return DateHelper.formatTime(time);
        } catch (ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date format time has error! time: {}，format: {}，error: {}", time.toString(), DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    public static String format(Date date, String format) {
        try {
            return DateHelper.format(date, format);
        } catch (ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date format has error！date: {}，format: {}，error: {}", date.toString(), format, exception.getMessage());
            return null;
        }
    }

    public static Date parseDate(String date) {
        try {
            return DateHelper.parseDate(date);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date parse date has error！date: {}，format: {}，error: {}", date, DateConstants.DATE_FORMAT_10, exception.getMessage());
            return null;
        }
    }

    @Deprecated
    public static Date parseDateTime(String datetime) {
        try {
            return DateHelper.parseDateTime(datetime);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date parse date time has error！time: {}，format: {}，error: {}", datetime, DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    public static Date parseTime(String time) {
        try {
            return DateHelper.parseTime(time);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date parse time has error！time: {}，format: {}，error: {}", time, DateConstants.DATE_FORMAT_19, exception.getMessage());
            return null;
        }
    }

    public static Date parse(String datetime, String format) {
        try {
            return DateHelper.parse(datetime, format);
        } catch (ParseErrorException | ResourceNotFoundException exception) {
            exception.printStackTrace();
            log.error("date parse has error！date time: {}，format: {}，error: {}", datetime, format, exception.getMessage());
            return null;
        }
    }

    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        clear(calendar);
        return calendar.getTime();
    }

    public static Date getDay(final Date date) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        clear(calendar);
        return calendar.getTime();
    }

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

    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    private static void clear(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

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