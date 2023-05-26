package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>IdentityErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityErrorException extends RestErrorException {
    public IdentityErrorException() {
        super(RestErrorStatus.IDENTITY_ERROR);
    }

    public IdentityErrorException(RestErrorStatus status) {
        super(status);
    }
    
    public IdentityErrorException(String error) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(RestErrorStatus.IDENTITY_ERROR, error));
    }

    public IdentityErrorException(String error, Throwable cause) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(RestErrorStatus.IDENTITY_ERROR, error, cause), cause);
    }

    public IdentityErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public IdentityErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public IdentityErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public IdentityErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public IdentityErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public IdentityErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public IdentityErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public IdentityErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public IdentityErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public IdentityErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public IdentityErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public IdentityErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public IdentityErrorException(String resource, String error) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(resource, RestErrorStatus.IDENTITY_ERROR, error));
    }

    public IdentityErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(resource, RestErrorStatus.IDENTITY_ERROR, error, cause), cause);
    }

    public IdentityErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(resource, filed, RestErrorStatus.IDENTITY_ERROR, error));
    }

    public IdentityErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(resource, filed, RestErrorStatus.IDENTITY_ERROR, error, cause), cause);
    }

    public IdentityErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(resource, filed, value, RestErrorStatus.IDENTITY_ERROR, error));
    }

    public IdentityErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(resource, filed, value, RestErrorStatus.IDENTITY_ERROR, error, cause));
    }

    @Override
    public IdentityErrorException get() {
        return new IdentityErrorException();
    }
}
