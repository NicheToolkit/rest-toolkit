package io.github.nichetoolkit.rest.util;

import io.github.nichetoolkit.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * <p>LoggerUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class LoggerUtils {
    private static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger(LoggerUtils.class);

    public static void info(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.info(format);
    }

    public static void info(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append,throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.info(format,throwable);
    }

    public static void info(RestStatus status) {
        DEFAULT_LOGGER.info(status.getMessage());
    }

    public static void info(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.info(status.getMessage(),throwable);
    }

    public static void debug(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.debug(format);
    }

    public static void debug(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append,throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.debug(format,throwable);
    }

    public static void debug(RestStatus status) {
        DEFAULT_LOGGER.debug(status.getMessage());
    }

    public static void debug(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.debug(status.getMessage(),throwable);
    }

    public static void warn(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.warn(format);
    }

    public static void warn(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append,throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.warn(format,throwable);
    }

    public static void warn(RestStatus status) {
        DEFAULT_LOGGER.warn(status.getMessage());
    }

    public static void warn(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.warn(status.getMessage(),throwable);
    }

    public static void error(String message, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.error(format);
    }

    public static void error(String message, Throwable throwable, Object ... append) {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, append, throwable);
        String format = formattingTuple.getMessage();
        DEFAULT_LOGGER.error(format,throwable);
    }

    public static void error(RestStatus status) {
        DEFAULT_LOGGER.error(status.getMessage());
    }

    public static void error(RestStatus status, Throwable throwable) {
        DEFAULT_LOGGER.error(status.getMessage(),throwable);
    }
}
