package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseMapException</code>
 * <p>The type json parse map exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @since Jdk1.8
 */
public class JsonParseMapException extends JsonParseErrorException {
    /**
     * <code>JsonParseMapException</code>
     * Instantiates a new json parse map exception.
     */
    public JsonParseMapException() {
        super(RestErrorStatus.JSON_PARSE_MAP);
    }

    /**
     * <code>JsonParseMapException</code>
     * Instantiates a new json parse map exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseMapException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseMapException</code>
     * Instantiates a new json parse map exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseMapException(String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, message);
    }

    /**
     * <code>JsonParseMapException</code>
     * Instantiates a new json parse map exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseMapException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, message);
    }

    /**
     * <code>JsonParseMapException</code>
     * Instantiates a new json parse map exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseMapException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, field, message);
    }

    /**
     * <code>JsonParseMapException</code>
     * Instantiates a new json parse map exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JsonParseMapException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, field, value, message);
    }

    @Override
    public JsonParseMapException get() {
        return new JsonParseMapException();
    }
}
