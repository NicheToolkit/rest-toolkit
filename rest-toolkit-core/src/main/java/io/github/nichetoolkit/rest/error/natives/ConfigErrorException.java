package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>ConfigureErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ConfigErrorException extends RestErrorException {

    public ConfigErrorException() {
        super(RestErrorStatus.CLASS_ERROR);
    }

    public ConfigErrorException(RestErrorStatus status) {
        super(status);
    }

    public ConfigErrorException(String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(RestErrorStatus.CLASS_ERROR, error));
    }

    public ConfigErrorException(String error, Throwable cause) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(RestErrorStatus.CLASS_ERROR, error, cause), cause);
    }

    public ConfigErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ConfigErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public ConfigErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ConfigErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public ConfigErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public ConfigErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public ConfigErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ConfigErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public ConfigErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public ConfigErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public ConfigErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public ConfigErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public ConfigErrorException(String resource, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, RestErrorStatus.CLASS_ERROR, error));
    }

    public ConfigErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, RestErrorStatus.CLASS_ERROR, error, cause), cause);
    }

    public ConfigErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, RestErrorStatus.CLASS_ERROR, error));
    }

    public ConfigErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, RestErrorStatus.CLASS_ERROR, error, cause), cause);
    }

    public ConfigErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CLASS_ERROR, error));
    }

    public ConfigErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CLASS_ERROR, error, cause));
    }

    @Override
    public ConfigErrorException get() {
        return new ConfigErrorException();
    }
}
