package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseSetException</code>
 * <p>The type json parse set exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @since Jdk1.8
 */
public class JsonParseSetException extends JsonParseErrorException {
    /**
     * <code>JsonParseSetException</code>
     * Instantiates a new json parse set exception.
     */
    public JsonParseSetException() {
        super(RestErrorStatus.JSON_PARSE_SET);
    }

    /**
     * <code>JsonParseSetException</code>
     * Instantiates a new json parse set exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseSetException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseSetException</code>
     * Instantiates a new json parse set exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseSetException(String message) {
        super(RestErrorStatus.JSON_PARSE_SET, message);
    }

    /**
     * <code>JsonParseSetException</code>
     * Instantiates a new json parse set exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseSetException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, message);
    }

    /**
     * <code>JsonParseSetException</code>
     * Instantiates a new json parse set exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseSetException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, field, message);
    }

    /**
     * <code>JsonParseSetException</code>
     * Instantiates a new json parse set exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JsonParseSetException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_SET, resource, field, value, message);
    }

    @Override
    public JsonParseSetException get() {
        return new JsonParseSetException();
    }
}
