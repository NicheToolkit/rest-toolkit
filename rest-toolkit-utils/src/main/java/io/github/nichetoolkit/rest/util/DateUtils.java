package io.github.nichetoolkit.rest.util;


import io.github.nichetoolkit.rest.error.natives.ParseErrorException;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>DateUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("unused")
public class DateUtils {

    public static final ThreadLocal<Map<Integer, Boolean>> DATE_THREAD_LOCAL = new ThreadLocal<>();
    public static final ThreadLocal<Map<String, SimpleDateFormat>> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<>();
    public static final Pattern DATE_PATTERN_1 = Pattern.compile("^\\[\\$-.*?]");
    public static final Pattern DATE_PATTERN_2 = Pattern.compile("^\\[[a-zA-Z]+]");
    public static final Pattern DATE_PATTERN_3_A = Pattern.compile("[yYmMdDhHsS]");
    public static final Pattern DATE_PATTERN_3_B = Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/年月日,. :\"\\\\]+0*[ampAMP/]*$");
    public static final Pattern DATE_PATTERN_4 = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)]");
    public static final Pattern DATE_PATTERN_5 = Pattern.compile("^\\[DBNum([123])]");
    public static final Pattern DATE_PATTERN_6 = Pattern.compile("([年月日时分秒])+");
    public static final String DATE_FORMAT_10 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_14 = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_17 = "yyyyMMdd HH:mm:ss";
    public static final String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_19_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_23 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_23_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String MINUS = "-";

    /**
     * 格式化 日期 如：yyyy-MM-dd
     * @param date 日期时间
     * @return String 日期字符串
     */
    public static String formatDate(Long date) {
        return formatDate(new Date(date));
    }

    /**
     * 格式化时间 如：yyyy-MM-dd HH:mm:ss
     * @param datetime 日期时间
     * @return String 时间字符串
     */
    public static String formatTime(Long datetime) {
        return formatTime(new Date(datetime));
    }


    /**
     * 格式化 日期 如：yyyy-MM-dd
     * @param date 日期时间
     * @return String 日期字符串
     */
    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT_10);
    }

    /**
     * 格式化时间 如：yyyy-MM-dd HH:mm:ss
     * @param datetime 日期时间
     * @return String 时间字符串
     */
    public static String formatTime(Date datetime) {
        return format(datetime, DATE_FORMAT_19);
    }

    /**
     * 格式化日期时间
     * @param date 日期时间
     * @param dateFormat 格式化字符串
     * @return String 格式结果
     */
    public static String format(Date date, String dateFormat) {
        return getCacheDateFormat(dateFormat).format(date);
    }

    /**
     * 解析日期 如：yyyy-MM-dd
     * @param dateString 日期字符串
     * @return Date 日期
     */
    public static Date parseDate(String dateString){
        try {
            return parse(dateString, DATE_FORMAT_10);
        } catch (ParseErrorException e) {
            e.printStackTrace();
            log.error("date string parse error！ dateString = {}，pattern = {}，error = {}",dateString, DATE_FORMAT_10,e.getMessage());
            return null;
        }
    }

    /**
     * 解析时间 如：yyyy-MM-dd HH:mm:ss
     * @param timeString 时间字符串
     * @return Date 时间
     */
    public static Date parseDateTime(String timeString) {
        try {
            return parse(timeString, DATE_FORMAT_19);
        } catch (ParseErrorException e) {
            e.printStackTrace();
            log.error("date string parse error！ timeString = {}，pattern = {}，error = {}",timeString, DATE_FORMAT_19,e.getMessage());
            return null;
        }
    }

    /**
     * 解析日期时间
     * @param dateString 日期时间字符串
     * @param dateFormat 格式化字符串
     * @return Date 时间
     */
    public static Date parse(String dateString, String dateFormat) throws ParseErrorException {
        if (GeneralUtils.isEmpty(dateFormat)) {
            dateFormat = switchDateFormat(dateString);
        }
        try {
            return getCacheDateFormat(dateFormat).parse(dateString);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ParseErrorException(exception.getMessage());
        }
    }

    private static String switchDateFormat(String dateString) {
        int length = dateString.length();
        switch(length) {
            case 10:
                return DATE_FORMAT_10;
            case 14:
                return DATE_FORMAT_14;
            case 17:
                return DATE_FORMAT_17;
            case 19:
                return dateString.contains("-") ? DATE_FORMAT_19 : DATE_FORMAT_19_FORWARD_SLASH;
            case 23:
                return dateString.contains("-") ? DATE_FORMAT_23 : DATE_FORMAT_23_FORWARD_SLASH;
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 18:
            default:
                throw new IllegalArgumentException("can not find date format for：" + dateString);
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

    private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarField, amount);
        return calendar.getTime();
    }

    private static DateFormat getCacheDateFormat(String dateFormat) {
        if (GeneralUtils.isEmpty(dateFormat)) {
            throw new IllegalArgumentException("pattern is not null！");
        }
        Map<String,SimpleDateFormat> dateFormatMap = DATE_FORMAT_THREAD_LOCAL.get();
        SimpleDateFormat dateFormatCached;
        if (dateFormatMap == null) {
            dateFormatMap = new HashMap<>();
            DATE_FORMAT_THREAD_LOCAL.set(dateFormatMap);
        } else {
            dateFormatCached = (dateFormatMap).get(dateFormat);
            if (dateFormatCached != null) {
                return dateFormatCached;
            }
        }

        dateFormatCached = new SimpleDateFormat(dateFormat);
        dateFormatMap.put(dateFormat, dateFormatCached);
        return dateFormatCached;
    }

}