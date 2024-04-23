package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>TimeoutException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TimeoutErrorException extends RestErrorException {

    public TimeoutErrorException() {
        super(RestErrorStatus.TIME_OUT);
    }

    public TimeoutErrorException(RestErrorStatus status) {
        super(status);
    }

    public TimeoutErrorException(String error) {
        super(RestErrorStatus.TIME_OUT, RestError.error(RestErrorStatus.TIME_OUT, error));
    }

    public TimeoutErrorException(String error, Throwable cause) {
        super(RestErrorStatus.TIME_OUT, RestError.error(RestErrorStatus.TIME_OUT, error, cause), cause);
    }

    public TimeoutErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public TimeoutErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public TimeoutErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public TimeoutErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public TimeoutErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public TimeoutErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public TimeoutErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public TimeoutErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public TimeoutErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public TimeoutErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public TimeoutErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public TimeoutErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public TimeoutErrorException(String resource, String error) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource, RestErrorStatus.TIME_OUT, error));
    }

    public TimeoutErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource, RestErrorStatus.TIME_OUT, error, cause), cause);
    }

    public TimeoutErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource, filed, RestErrorStatus.TIME_OUT, error));
    }

    public TimeoutErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource, filed, RestErrorStatus.TIME_OUT, error, cause), cause);
    }

    public TimeoutErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource, filed, value, RestErrorStatus.TIME_OUT, error));
    }

    public TimeoutErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource, filed, value, RestErrorStatus.TIME_OUT, error, cause));
    }

    @Override
    public TimeoutErrorException get() {
        return new TimeoutErrorException();
    }

}
