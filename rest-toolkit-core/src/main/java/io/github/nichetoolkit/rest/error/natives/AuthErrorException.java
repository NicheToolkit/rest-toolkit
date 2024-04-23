package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>AuthErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class AuthErrorException extends RestErrorException {

    public AuthErrorException() {
        super(RestErrorStatus.AUTH_ERROR);
    }

    public AuthErrorException(RestErrorStatus status) {
        super(status);
    }

    public AuthErrorException(String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(RestErrorStatus.AUTH_ERROR, error));
    }

    public AuthErrorException(String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(RestErrorStatus.AUTH_ERROR, error, cause), cause);
    }

    public AuthErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public AuthErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public AuthErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public AuthErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public AuthErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public AuthErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public AuthErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public AuthErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public AuthErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public AuthErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public AuthErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public AuthErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public AuthErrorException(String resource, String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, RestErrorStatus.AUTH_ERROR, error));
    }

    public AuthErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, RestErrorStatus.AUTH_ERROR, error, cause), cause);
    }

    public AuthErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, RestErrorStatus.AUTH_ERROR, error));
    }

    public AuthErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, RestErrorStatus.AUTH_ERROR, error, cause), cause);
    }

    public AuthErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, value, RestErrorStatus.AUTH_ERROR, error));
    }

    public AuthErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, value, RestErrorStatus.AUTH_ERROR, error, cause));
    }

    @Override
    public AuthErrorException get() {
        return new AuthErrorException();
    }
}
