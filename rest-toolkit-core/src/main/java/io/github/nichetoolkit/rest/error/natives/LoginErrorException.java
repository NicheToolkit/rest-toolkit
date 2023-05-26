package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>LoginErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class LoginErrorException extends RestErrorException {

    public LoginErrorException() {
        super(RestErrorStatus.LOGIN_ERROR);
    }

    public LoginErrorException(RestErrorStatus status) {
        super(status);
    }

    public LoginErrorException(String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(String error, Throwable cause) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(RestErrorStatus.LOGIN_ERROR, error, cause), cause);
    }

    public LoginErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public LoginErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public LoginErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public LoginErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public LoginErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public LoginErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public LoginErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public LoginErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public LoginErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public LoginErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public LoginErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public LoginErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public LoginErrorException(String resource, String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, RestErrorStatus.LOGIN_ERROR, error, cause), cause);
    }

    public LoginErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, filed, RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, filed, RestErrorStatus.LOGIN_ERROR, error, cause), cause);
    }

    public LoginErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, filed, value, RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, filed, value, RestErrorStatus.LOGIN_ERROR, error, cause));
    }
    
    @Override
    public LoginErrorException get() {
        return new LoginErrorException();
    }
}
