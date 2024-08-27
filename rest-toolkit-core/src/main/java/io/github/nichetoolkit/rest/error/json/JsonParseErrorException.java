package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseErrorException</code>
 * <p>The type json parse error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ParseErrorException
 * @since Jdk1.8
 */
public class JsonParseErrorException extends ParseErrorException {

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     */
    public JsonParseErrorException() {
        super(RestErrorStatus.JSON_PARSE_ERROR);
    }

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public JsonParseErrorException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseErrorException(String error) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(RestErrorStatus.JSON_PARSE_ERROR, error));
    }

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public JsonParseErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public JsonParseErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public JsonParseErrorException(RestStatus status, String resource, String field, String message) {
        super(status, RestError.error(resource, field, status, message));
    }

    /**
     * <code>JsonParseErrorException</code>
     * Instantiates a new json parse error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JsonParseErrorException(RestStatus status, String resource, String field, Object value, String message) {
        super(status, RestError.error(resource, field, value, status, message));
    }

    @Override
    public JsonParseErrorException get() {
        return new JsonParseErrorException();
    }
}
