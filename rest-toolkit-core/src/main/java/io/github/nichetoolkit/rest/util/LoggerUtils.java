package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * <code>LoggerUtils</code>
 * <p>The logger utils class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class LoggerUtils {
    /**
     * <code>log</code>
     * {@link org.slf4j.Logger} <p>The constant <code>log</code> field.</p>
     * @see org.slf4j.Logger
     */
    private static final Logger log = LoggerFactory.getLogger(LoggerUtils.class);

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void trace(String message, Object... appends) {
        trace(log, message, appends);
    }

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param log     {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void trace(Logger log,String message, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends);
        String format = formattingTuple.getMessage();
        log.trace(format);
    }

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void trace(String message, Throwable throwable, Object... appends) {
        trace(log, message, throwable,appends);
    }

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void trace(Logger log,String message, Throwable throwable, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends, throwable);
        String format = formattingTuple.getMessage();
        log.trace(format, throwable);
    }

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void trace(RestStatus status) {
        log.trace(status.getMessage());
    }

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param log    {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void trace(Logger log,RestStatus status) {
        log.trace(status.getMessage());
    }

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void trace(RestStatus status, Throwable throwable) {
        log.trace(status.getMessage(), throwable);
    }

    /**
     * <code>trace</code>
     * <p>The trace method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void trace(Logger log,RestStatus status, Throwable throwable) {
        log.trace(status.getMessage(), throwable);
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void debug(String message, Object... appends) {
        debug(log, message, appends);
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param log     {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void debug(Logger log,String message, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends);
        String format = formattingTuple.getMessage();
        log.debug(format);
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void debug(String message, Throwable throwable, Object... appends) {
        debug(log, message, throwable,appends);
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void debug(Logger log,String message, Throwable throwable, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends, throwable);
        String format = formattingTuple.getMessage();
        log.debug(format, throwable);
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void debug(RestStatus status) {
        log.debug(status.getMessage());
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param log    {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void debug(Logger log,RestStatus status) {
        log.debug(status.getMessage());
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void debug(RestStatus status, Throwable throwable) {
        log.debug(status.getMessage(), throwable);
    }

    /**
     * <code>debug</code>
     * <p>The debug method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void debug(Logger log,RestStatus status, Throwable throwable) {
        log.debug(status.getMessage(), throwable);
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void info(String message, Object... appends) {
        info(log, message, appends);
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param log     {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void info(Logger log, String message, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends);
        String format = formattingTuple.getMessage();
        log.info(format);
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void info(String message, Throwable throwable, Object... appends) {
        info(log, message, throwable, appends);
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void info(Logger log, String message, Throwable throwable, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends, throwable);
        String format = formattingTuple.getMessage();
        log.info(format, throwable);
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void info(RestStatus status) {
        log.info(status.getMessage());
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param log    {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void info(Logger log, RestStatus status) {
        log.info(status.getMessage());
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void info(RestStatus status, Throwable throwable) {
        log.info(status.getMessage(), throwable);
    }

    /**
     * <code>info</code>
     * <p>The info method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void info(Logger log,RestStatus status, Throwable throwable) {
        log.info(status.getMessage(), throwable);
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void warn(String message, Object... appends) {
        warn(log, message, appends);
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param log     {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void warn(Logger log,String message, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends);
        String format = formattingTuple.getMessage();
        log.warn(format);
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void warn(String message, Throwable throwable, Object... appends) {
        warn(log, message, throwable,appends);
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void warn(Logger log,String message, Throwable throwable, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends, throwable);
        String format = formattingTuple.getMessage();
        log.warn(format, throwable);
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void warn(RestStatus status) {
        log.warn(status.getMessage());
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param log    {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void warn(Logger log,RestStatus status) {
        log.warn(status.getMessage());
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void warn(RestStatus status, Throwable throwable) {
        log.warn(status.getMessage(), throwable);
    }

    /**
     * <code>warn</code>
     * <p>The warn method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void warn(Logger log,RestStatus status, Throwable throwable) {
        log.warn(status.getMessage(), throwable);
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void error(String message, Object... appends) {
        error(log, message, appends);
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param log     {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param appends {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static void error(Logger log,String message, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends);
        String format = formattingTuple.getMessage();
        log.error(format);
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void error(String message, Throwable throwable, Object... appends) {
        error(log, message, throwable,appends);
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @param appends   {@link java.lang.Object} <p>The appends parameter is <code>Object</code> type.</p>
     * @see org.slf4j.Logger
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Object
     */
    public static void error(Logger log,String message, Throwable throwable, Object... appends) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, appends, throwable);
        String format = formattingTuple.getMessage();
        log.error(format, throwable);
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void error(RestStatus status) {
        log.error(status.getMessage());
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param log    {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static void error(Logger log,RestStatus status) {
        log.error(status.getMessage());
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void error(RestStatus status, Throwable throwable) {
        log.error(status.getMessage(), throwable);
    }

    /**
     * <code>error</code>
     * <p>The error method.</p>
     * @param log       {@link org.slf4j.Logger} <p>The log parameter is <code>Logger</code> type.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param throwable {@link java.lang.Throwable} <p>The throwable parameter is <code>Throwable</code> type.</p>
     * @see org.slf4j.Logger
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static void error(Logger log,RestStatus status, Throwable throwable) {
        log.error(status.getMessage(), throwable);
    }
}
