package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>ConfigInvalidException</code>
 * <p>The type config invalid exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ConfigInvalidException extends RestErrorException {

    /**
     * <code>ConfigInvalidException</code>
     * <p>Instantiates a new config invalid exception.</p>
     */
    public ConfigInvalidException() {
        super(RestErrorStatus.CONFIG_INVALID);
    }

    /**
     * <code>ConfigInvalidException</code>
     * <p>Instantiates a new config invalid exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ConfigInvalidException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ConfigInvalidException</code>
     * <p>Instantiates a new config invalid exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ConfigInvalidException(String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(RestErrorStatus.CONFIG_INVALID, message));
    }

    /**
     * <code>ConfigInvalidException</code>
     * <p>Instantiates a new config invalid exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ConfigInvalidException(String field, String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(field, RestErrorStatus.CONFIG_INVALID, message));
    }

    /**
     * <code>ConfigInvalidException</code>
     * <p>Instantiates a new config invalid exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ConfigInvalidException(String field, Object value, String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(field, value, RestErrorStatus.CONFIG_INVALID, message));
    }

    /**
     * <code>ConfigInvalidException</code>
     * <p>Instantiates a new config invalid exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ConfigInvalidException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(resource, field, value, RestErrorStatus.CONFIG_INVALID, message));
    }

    @Override
    public ConfigInvalidException get() {
        return new ConfigInvalidException();
    }
}
