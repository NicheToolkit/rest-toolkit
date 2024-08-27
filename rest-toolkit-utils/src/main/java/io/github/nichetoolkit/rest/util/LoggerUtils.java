package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * <code>LoggerUtils</code>
 * <p>The type logger utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class LoggerUtils {
    /**
     * <code>DEFAULT_LOGGER</code>
     * {@link org.slf4j.Logger} <p>the constant <code>DEFAULT_LOGGER</code> field.</p>
     * @see org.slf4j.Logger
     */
    private static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger(LoggerUtils.class);

    /**
     * <code>info</code>
     * <p>the method.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param append  {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void info(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.info(format);
    }

    /**
     * <code>info</code>
     * <p>the method.</p>
     * @param message   {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @param append    {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void info(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append,throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.info(format,throwable);
    }

    /**
     * <code>info</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void info(RestStatus status) {
        DEFAULT_LOGGER.info(status.getMessage());
    }

    /**
     * <code>info</code>
     * <p>the method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void info(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.info(status.getMessage(),throwable);
    }

    /**
     * <code>debug</code>
     * <p>the method.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param append  {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void debug(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.debug(format);
    }

    /**
     * <code>debug</code>
     * <p>the method.</p>
     * @param message   {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @param append    {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void debug(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append,throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.debug(format,throwable);
    }

    /**
     * <code>debug</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void debug(RestStatus status) {
        DEFAULT_LOGGER.debug(status.getMessage());
    }

    /**
     * <code>debug</code>
     * <p>the method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void debug(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.debug(status.getMessage(),throwable);
    }

    /**
     * <code>warn</code>
     * <p>the method.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param append  {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void warn(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.warn(format);
    }

    /**
     * <code>warn</code>
     * <p>the method.</p>
     * @param message   {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @param append    {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void warn(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append,throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.warn(format,throwable);
    }

    /**
     * <code>warn</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void warn(RestStatus status) {
        DEFAULT_LOGGER.warn(status.getMessage());
    }

    /**
     * <code>warn</code>
     * <p>the method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void warn(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.warn(status.getMessage(),throwable);
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param append  {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void error(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.error(format);
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param message   {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @param append    {@link java.lang.Object} <p>the append parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void error(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append, throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.error(format,throwable);
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void error(RestStatus status) {
        DEFAULT_LOGGER.error(status.getMessage());
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>the throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void error(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.error(status.getMessage(),throwable);
    }
}
