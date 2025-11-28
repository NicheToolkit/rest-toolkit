package io.github.nichetoolkit.rest.error.json;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseArrayException</code>
 * <p>The json parse array exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.json.JsonParseErrorException
 * @since Jdk17
 */
public class JsonParseArrayException extends JsonParseErrorException {
    /**
     * <code>JsonParseArrayException</code>
     * <p>Instantiates a new json parse array exception.</p>
     */
    public JsonParseArrayException() {
        super(RestErrorStatus.JSON_PARSE_ARRAY);
    }

    /**
     * <code>JsonParseArrayException</code>
     * <p>Instantiates a new json parse array exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseArrayException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JsonParseArrayException</code>
     * <p>Instantiates a new json parse array exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseArrayException(String message) {
        super(RestErrorStatus.JSON_PARSE_ARRAY, message);
    }

    /**
     * <code>JsonParseArrayException</code>
     * <p>Instantiates a new json parse array exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseArrayException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_ARRAY, resource, message);
    }

    /**
     * <code>JsonParseArrayException</code>
     * <p>Instantiates a new json parse array exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseArrayException(String resource, String field, String message) {
        super(RestErrorStatus.JSON_PARSE_ARRAY, resource, field, message);
    }

    /**
     * <code>JsonParseArrayException</code>
     * <p>Instantiates a new json parse array exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public JsonParseArrayException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.JSON_PARSE_ARRAY, resource, field, value, message);
    }

    @Override
    public JsonParseArrayException get() {
        return new JsonParseArrayException();
    }
}
