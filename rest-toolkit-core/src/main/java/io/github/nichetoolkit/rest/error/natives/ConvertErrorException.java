package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>ConvertErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ConvertErrorException extends RestErrorException {

    public ConvertErrorException() {
        super(RestErrorStatus.CONVERT_ERROR);
    }

    public ConvertErrorException(RestErrorStatus status) {
        super(status);
    }

    public ConvertErrorException(String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(RestErrorStatus.CONVERT_ERROR, error));
    }

    public ConvertErrorException(String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(RestErrorStatus.CONVERT_ERROR, error, cause), cause);
    }

    public ConvertErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ConvertErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public ConvertErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ConvertErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public ConvertErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public ConvertErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public ConvertErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ConvertErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public ConvertErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public ConvertErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public ConvertErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public ConvertErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public ConvertErrorException(String resource, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, RestErrorStatus.CONVERT_ERROR, error));
    }

    public ConvertErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, RestErrorStatus.CONVERT_ERROR, error, cause), cause);
    }

    public ConvertErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, RestErrorStatus.CONVERT_ERROR, error));
    }

    public ConvertErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, RestErrorStatus.CONVERT_ERROR, error, cause), cause);
    }

    public ConvertErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONVERT_ERROR, error));
    }

    public ConvertErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONVERT_ERROR, error, cause));
    }

    @Override
    public ConvertErrorException get() {
        return new ConvertErrorException();
    }
}
