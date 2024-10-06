package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseResultException</code>
 * <p>The type json parse result exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @since Jdk1.8
 */
public class JsonParseResultException extends JsonParseErrorException {
    /**
     * <code>JsonParseResultException</code>
     * <p>Instantiates a new json parse result exception.</p>
     */
    public JsonParseResultException() {
        super(RestErrorStatus.JSON_PARSE_RESULT);
    }

    /**
     * <code>JsonParseResultException</code>
     * <p>Instantiates a new json parse result exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseResultException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseResultException</code>
     * <p>Instantiates a new json parse result exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseResultException(String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, message);
    }

    /**
     * <code>JsonParseResultException</code>
     * <p>Instantiates a new json parse result exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseResultException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, message);
    }

    /**
     * <code>JsonParseResultException</code>
     * <p>Instantiates a new json parse result exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseResultException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, field, message);
    }

    /**
     * <code>JsonParseResultException</code>
     * <p>Instantiates a new json parse result exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JsonParseResultException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_RESULT, resource, field, value, message);
    }

    @Override
    public JsonParseResultException get() {
        return new JsonParseResultException();
    }
}
