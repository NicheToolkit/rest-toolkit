package io.github.nichetoolkit.rest.constant;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>DateConstants</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface DateConstants {
    ThreadLocal<Map<Integer, Boolean>> DATE_THREAD_LOCAL = new ThreadLocal<>();
    ThreadLocal<Map<String, SimpleDateFormat>> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<>();
    Pattern DATE_PATTERN_1 = Pattern.compile("^\\[\\$-.*?]");
    Pattern DATE_PATTERN_2 = Pattern.compile("^\\[[a-zA-Z]+]");
    Pattern DATE_PATTERN_3_A = Pattern.compile("[yYmMdDhHsS]");
    Pattern DATE_PATTERN_3_B = Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/年月日,. :\"\\\\]+0*[ampAMP/]*$");
    Pattern DATE_PATTERN_4 = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)]");
    Pattern DATE_PATTERN_6 = Pattern.compile("([年月日时分秒])+");
    Pattern DATE_PATTERN_5 = Pattern.compile("^\\[DBNum([123])]");
    String DATE_FORMAT_10 = "yyyy-MM-dd";
    String DATE_FORMAT_14 = "yyyyMMddHHmmss";
    String DATE_FORMAT_18 = "yyyy-MM-ddHH:mm:ss";
    String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT_17 = "yyyyMMdd HH:mm:ss";
    String DATE_FORMAT_19_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss";
    String DATE_FORMAT_23 = "yyyy-MM-dd HH:mm:ss.SSS";
    String DATE_FORMAT_23_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
    String MINUS = "-";
}
