package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>UnknownErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class UnknownErrorException extends RestErrorException {

    public UnknownErrorException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    public UnknownErrorException(RestErrorStatus status) {
        super(status);
    }

    public UnknownErrorException(String error) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(RestErrorStatus.UNKNOWN_ERROR, error));
    }

    public UnknownErrorException(String error, Throwable cause) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(RestErrorStatus.UNKNOWN_ERROR, error, cause), cause);
    }

    public UnknownErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public UnknownErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public UnknownErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public UnknownErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public UnknownErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public UnknownErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public UnknownErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public UnknownErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public UnknownErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public UnknownErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public UnknownErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public UnknownErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public UnknownErrorException(String resource, String error) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(resource, RestErrorStatus.UNKNOWN_ERROR, error));
    }

    public UnknownErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(resource, RestErrorStatus.UNKNOWN_ERROR, error, cause), cause);
    }

    public UnknownErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(resource, filed, RestErrorStatus.UNKNOWN_ERROR, error));
    }

    public UnknownErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(resource, filed, RestErrorStatus.UNKNOWN_ERROR, error, cause), cause);
    }

    public UnknownErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(resource, filed, value, RestErrorStatus.UNKNOWN_ERROR, error));
    }

    public UnknownErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(resource, filed, value, RestErrorStatus.UNKNOWN_ERROR, error, cause));
    }

    @Override
    public UnknownErrorException get() {
        return new UnknownErrorException();
    }
}
