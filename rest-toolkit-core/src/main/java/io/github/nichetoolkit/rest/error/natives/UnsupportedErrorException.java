package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>UnsupportedErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class UnsupportedErrorException extends RestErrorException {

    public UnsupportedErrorException() {
        super(RestErrorStatus.UNSUPPORTED);
    }

    public UnsupportedErrorException(RestErrorStatus status) {
        super(status);
    }

    public UnsupportedErrorException(String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(RestErrorStatus.UNSUPPORTED, error));
    }

    public UnsupportedErrorException(String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(RestErrorStatus.UNSUPPORTED, error, cause), cause);
    }

    public UnsupportedErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public UnsupportedErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public UnsupportedErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public UnsupportedErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public UnsupportedErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public UnsupportedErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public UnsupportedErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public UnsupportedErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public UnsupportedErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public UnsupportedErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public UnsupportedErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public UnsupportedErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public UnsupportedErrorException(String resource, String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, RestErrorStatus.UNSUPPORTED, error));
    }

    public UnsupportedErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, RestErrorStatus.UNSUPPORTED, error, cause), cause);
    }

    public UnsupportedErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, RestErrorStatus.UNSUPPORTED, error));
    }

    public UnsupportedErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, RestErrorStatus.UNSUPPORTED, error, cause), cause);
    }

    public UnsupportedErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, value, RestErrorStatus.UNSUPPORTED, error));
    }

    public UnsupportedErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, value, RestErrorStatus.UNSUPPORTED, error, cause));
    }

    @Override
    public UnsupportedErrorException get() {
        return new UnsupportedErrorException();
    }
}
