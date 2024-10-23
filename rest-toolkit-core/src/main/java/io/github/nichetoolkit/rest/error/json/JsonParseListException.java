package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseListException</code>
 * <p>The json parse list exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @since Jdk1.8
 */
public class JsonParseListException extends JsonParseErrorException {
    /**
     * <code>JsonParseListException</code>
     * <p>Instantiates a new json parse list exception.</p>
     */
    public JsonParseListException() {
        super(RestErrorStatus.JSON_PARSE_LIST);
    }

    /**
     * <code>JsonParseListException</code>
     * <p>Instantiates a new json parse list exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseListException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseListException</code>
     * <p>Instantiates a new json parse list exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseListException(String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, message);
    }

    /**
     * <code>JsonParseListException</code>
     * <p>Instantiates a new json parse list exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseListException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, resource, message);
    }

    /**
     * <code>JsonParseListException</code>
     * <p>Instantiates a new json parse list exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseListException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, resource, field, message);
    }

    /**
     * <code>JsonParseListException</code>
     * <p>Instantiates a new json parse list exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JsonParseListException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_LIST, resource, field, value, message);
    }

    @Override
    public JsonParseListException get() {
        return new JsonParseListException();
    }
}
