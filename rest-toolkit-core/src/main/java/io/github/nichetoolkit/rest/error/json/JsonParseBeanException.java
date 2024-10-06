package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseBeanException</code>
 * <p>The type json parse bean exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @since Jdk1.8
 */
public class JsonParseBeanException extends JsonParseErrorException {
    /**
     * <code>JsonParseBeanException</code>
     * <p>Instantiates a new json parse bean exception.</p>
     */
    public JsonParseBeanException() {
        super(RestErrorStatus.JSON_PARSE_BEAN);
    }

    /**
     * <code>JsonParseBeanException</code>
     * <p>Instantiates a new json parse bean exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseBeanException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseBeanException</code>
     * <p>Instantiates a new json parse bean exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseBeanException(String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, message);
    }

    /**
     * <code>JsonParseBeanException</code>
     * <p>Instantiates a new json parse bean exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseBeanException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, resource, message);
    }

    /**
     * <code>JsonParseBeanException</code>
     * <p>Instantiates a new json parse bean exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseBeanException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, resource, field, message);
    }

    /**
     * <code>JsonParseBeanException</code>
     * <p>Instantiates a new json parse bean exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JsonParseBeanException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_BEAN, resource, field, value, message);
    }

    @Override
    public JsonParseBeanException get() {
        return new JsonParseBeanException();
    }
}
