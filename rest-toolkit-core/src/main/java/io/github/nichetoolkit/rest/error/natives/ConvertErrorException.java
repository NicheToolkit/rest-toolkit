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

    public ConvertErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ConvertErrorException(RestStatus status, RestError error) {
        super(status, error);
    }


    public ConvertErrorException(RestStatus status, String resource) {
        super(status, RestError.error(resource, status));
    }

    public ConvertErrorException(RestStatus status, String resource, Object value) {
        super(status, RestError.error(resource, value, status));
    }

    public ConvertErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ConvertErrorException(RestStatus status, String resource, Object value, String message) {
        super(status, RestError.error(resource, value, status, message));
    }

    public ConvertErrorException(String filed, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(null, filed, null, RestErrorStatus.CONVERT_ERROR, error));
    }

    public ConvertErrorException(String filed, Object value) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(null, filed, value, RestErrorStatus.CONVERT_ERROR));
    }

    public ConvertErrorException(String filed, Object value, RestStatus status) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(null, filed, value, status));
    }

    public ConvertErrorException(String filed, Object value, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(null, filed, value, RestErrorStatus.CONVERT_ERROR, error));
    }

    public ConvertErrorException(String filed, Object value, RestStatus status, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(null, filed, value, status, error));
    }

    public ConvertErrorException(String resource, String filed, Object value) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONVERT_ERROR));
    }

    public ConvertErrorException(String resource, String filed, Object value, RestStatus status) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, status));
    }

    public ConvertErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONVERT_ERROR, error));
    }

    public ConvertErrorException(String resource, String filed, Object value, RestStatus status, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, status, error));
    }

    @Override
    public ConvertErrorException get() {
        return new ConvertErrorException();
    }
}
