package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseMapException</code>
 * <p>The json parse map exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @since Jdk1.8
 */
public class JsonParseMapException extends JsonParseErrorException {
    /**
     * <code>JsonParseMapException</code>
     * <p>Instantiates a new json parse map exception.</p>
     */
    public JsonParseMapException() {
        super(RestErrorStatus.JSON_PARSE_MAP);
    }

    /**
     * <code>JsonParseMapException</code>
     * <p>Instantiates a new json parse map exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseMapException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseMapException</code>
     * <p>Instantiates a new json parse map exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseMapException(String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, message);
    }

    /**
     * <code>JsonParseMapException</code>
     * <p>Instantiates a new json parse map exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseMapException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, message);
    }

    /**
     * <code>JsonParseMapException</code>
     * <p>Instantiates a new json parse map exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseMapException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_MAP, resource, field, message);
    }

    /**
     * <code>JsonParseMapException</code>
     * <p>Instantiates a new json parse map exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
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
