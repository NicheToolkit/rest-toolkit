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
 * <p>DateHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("unused")
public class DateHelper {

    public static String formatDate(Long date) throws ResourceNotFoundException {
        return formatDate(new Date(date));
    }

    public static String formatTime(Long time) throws ResourceNotFoundException {
        return formatTime(new Date(time));
    }

    public static String formatDate(Date date) throws ResourceNotFoundException {
        return format(date, DateConstants.DATE_FORMAT_10);
    }

    public static String formatTime(Date time) throws ResourceNotFoundException {
        return format(time, DateConstants.DATE_FORMAT_19);
    }


    public static String format(Date date, String format) throws ResourceNotFoundException {
        return cacheDateFormat(format).format(date);
    }

    public static Date parseDate(String date) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(date, DateConstants.DATE_FORMAT_10);
    }

    @Deprecated
    public static Date parseDateTime(String datetime) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(datetime, DateConstants.DATE_FORMAT_19);
    }

    public static Date parseTime(String time) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(time, DateConstants.DATE_FORMAT_19);
    }

    public static Date parse(String datetime, String format) throws ParseErrorException, ResourceNotFoundException {
        return parseFormat(datetime, format);
    }

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