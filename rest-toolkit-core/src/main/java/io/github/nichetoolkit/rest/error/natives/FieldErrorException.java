package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FieldErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FieldErrorException extends RestErrorException {
    public FieldErrorException() {
        super(RestErrorStatus.FIELD_ERROR);
    }

    public FieldErrorException(RestErrorStatus status) {
        super(status);
    }

    public FieldErrorException(String error) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(RestErrorStatus.FIELD_ERROR, error));
    }

    public FieldErrorException(String error, Throwable cause) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(RestErrorStatus.FIELD_ERROR, error, cause), cause);
    }

    public FieldErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public FieldErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public FieldErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public FieldErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public FieldErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public FieldErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public FieldErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public FieldErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public FieldErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public FieldErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public FieldErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public FieldErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public FieldErrorException(String resource, String error) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(resource, RestErrorStatus.FIELD_ERROR, error));
    }

    public FieldErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(resource, RestErrorStatus.FIELD_ERROR, error, cause), cause);
    }

    public FieldErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(resource, filed, RestErrorStatus.FIELD_ERROR, error));
    }

    public FieldErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(resource, filed, RestErrorStatus.FIELD_ERROR, error, cause), cause);
    }

    public FieldErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(resource, filed, value, RestErrorStatus.FIELD_ERROR, error));
    }

    public FieldErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(resource, filed, value, RestErrorStatus.FIELD_ERROR, error, cause));
    }

    @Override
    public FieldErrorException get() {
        return new FieldErrorException();
    }
}
