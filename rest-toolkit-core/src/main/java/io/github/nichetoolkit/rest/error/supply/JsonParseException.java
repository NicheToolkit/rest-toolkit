package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JsonParseException</code>
 * <p>The json parse exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class JsonParseException extends RestErrorException {
    /**
     * <code>JsonParseException</code>
     * <p>Instantiates a new json parse exception.</p>
     */
    public JsonParseException() {
        super(RestErrorStatus.JSON_PARSE_ERROR);
    }

    /**
     * <code>JsonParseException</code>
     * <p>Instantiates a new json parse exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>JsonParseException</code>
     * <p>Instantiates a new json parse exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseException(String resource, String message) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(resource, RestErrorStatus.JSON_PARSE_ERROR,message));
    }

    /**
     * <code>JsonParseException</code>
     * <p>Instantiates a new json parse exception.</p>
     * @param resource  {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param className {@link java.lang.String} <p>The class name parameter is <code>String</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseException(String resource, String className, String message) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(resource, className, RestErrorStatus.JSON_PARSE_ERROR, message));
    }

    /**
     * <code>JsonParseException</code>
     * <p>Instantiates a new json parse exception.</p>
     * @param resource  {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param className {@link java.lang.String} <p>The class name parameter is <code>String</code> type.</p>
     * @param json      {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public JsonParseException(String resource, String className, String json, String message) {
        super(RestErrorStatus.JSON_PARSE_ERROR, RestError.error(resource, className, json, RestErrorStatus.JSON_PARSE_ERROR, message));
    }

    /**
     * <code>JsonParseException</code>
     * <p>Instantiates a new json parse exception.</p>
     * @param resource  {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param className {@link java.lang.String} <p>The class name parameter is <code>String</code> type.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseException(String resource, String className, RestStatus status, String message) {
        super(status, RestError.error(resource, className, status, message));
    }

    /**
     * <code>JsonParseException</code>
     * <p>Instantiates a new json parse exception.</p>
     * @param resource  {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param className {@link java.lang.String} <p>The class name parameter is <code>String</code> type.</p>
     * @param json      {@link java.lang.String} <p>The json parameter is <code>String</code> type.</p>
     * @param status    {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message   {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public JsonParseException(String resource, String className, String json, RestStatus status, String message) {
        super(status, RestError.error(resource,  className, json, status, message));
    }

    @Override
    public JsonParseException get() {
        return new JsonParseException();
    }
}
