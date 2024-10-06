package io.github.nichetoolkit.rest.constant;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <code>DateConstants</code>
 * <p>The type date constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface DateConstants {
    /**
     * <code>DATE_THREAD_LOCAL</code>
     * {@link java.lang.ThreadLocal} <p>The constant <code>DATE_THREAD_LOCAL</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    ThreadLocal<Map<Integer, Boolean>> DATE_THREAD_LOCAL = new ThreadLocal<>();
    /**
     * <code>DATE_FORMAT_THREAD_LOCAL</code>
     * {@link java.lang.ThreadLocal} <p>The constant <code>DATE_FORMAT_THREAD_LOCAL</code> field.</p>
     * @see java.lang.ThreadLocal
     */
    ThreadLocal<Map<String, SimpleDateFormat>> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<>();
    /**
     * <code>DATE_PATTERN_1</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>DATE_PATTERN_1</code> field.</p>
     * @see java.util.regex.Pattern
     */
    Pattern DATE_PATTERN_1 = Pattern.compile("^\\[\\$-.*?]");
    /**
     * <code>DATE_PATTERN_2</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>DATE_PATTERN_2</code> field.</p>
     * @see java.util.regex.Pattern
     */
    Pattern DATE_PATTERN_2 = Pattern.compile("^\\[[a-zA-Z]+]");
    /**
     * <code>DATE_PATTERN_3_A</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>DATE_PATTERN_3_A</code> field.</p>
     * @see java.util.regex.Pattern
     */
    Pattern DATE_PATTERN_3_A = Pattern.compile("[yYmMdDhHsS]");
    /**
     * <code>DATE_PATTERN_3_B</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>DATE_PATTERN_3_B</code> field.</p>
     * @see java.util.regex.Pattern
     */
    Pattern DATE_PATTERN_3_B = Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/年月日,. :\"\\\\]+0*[ampAMP/]*$");
    /**
     * <code>DATE_PATTERN_4</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>DATE_PATTERN_4</code> field.</p>
     * @see java.util.regex.Pattern
     */
    Pattern DATE_PATTERN_4 = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)]");
    /**
     * <code>DATE_PATTERN_6</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>DATE_PATTERN_6</code> field.</p>
     * @see java.util.regex.Pattern
     */
    Pattern DATE_PATTERN_6 = Pattern.compile("([年月日时分秒])+");
    /**
     * <code>DATE_PATTERN_5</code>
     * {@link java.util.regex.Pattern} <p>The constant <code>DATE_PATTERN_5</code> field.</p>
     * @see java.util.regex.Pattern
     */
    Pattern DATE_PATTERN_5 = Pattern.compile("^\\[DBNum([123])]");
    /**
     * <code>DATE_FORMAT_10</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_10</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_10 = "yyyy-MM-dd";
    /**
     * <code>DATE_FORMAT_14</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_14</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_14 = "yyyyMMddHHmmss";
    /**
     * <code>DATE_FORMAT_18</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_18</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_18 = "yyyy-MM-ddHH:mm:ss";
    /**
     * <code>DATE_FORMAT_19</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_19</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";
    /**
     * <code>DATE_FORMAT_17</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_17</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_17 = "yyyyMMdd HH:mm:ss";
    /**
     * <code>DATE_FORMAT_19_FORWARD_SLASH</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_19_FORWARD_SLASH</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_19_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss";
    /**
     * <code>DATE_FORMAT_23</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_23</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_23 = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * <code>DATE_FORMAT_23_FORWARD_SLASH</code>
     * {@link java.lang.String} <p>The constant <code>DATE_FORMAT_23_FORWARD_SLASH</code> field.</p>
     * @see java.lang.String
     */
    String DATE_FORMAT_23_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
    /**
     * <code>MINUS</code>
     * {@link java.lang.String} <p>The constant <code>MINUS</code> field.</p>
     * @see java.lang.String
     */
    String MINUS = "-";
}
