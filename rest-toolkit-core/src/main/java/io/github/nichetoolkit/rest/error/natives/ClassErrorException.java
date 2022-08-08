package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ClassErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ClassErrorException extends RestErrorException {

    public ClassErrorException() {
        super(RestErrorStatus.CLASS_ERROR);
    }

    public ClassErrorException(RestErrorStatus status) {
        super(status);
    }

    public ClassErrorException(String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(RestErrorStatus.CLASS_ERROR, error));
    }

    public ClassErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ClassErrorException(RestStatus status, RestError error) {
        super(status, error);
    }


    public ClassErrorException(RestStatus status, String resource) {
        super(status, RestError.error(resource, status));
    }

    public ClassErrorException(RestStatus status, String resource, Object value) {
        super(status, RestError.error(resource, value, status));
    }

    public ClassErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ClassErrorException(RestStatus status, String resource, Object value, String message) {
        super(status, RestError.error(resource, value, status, message));
    }

    public ClassErrorException(String filed, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(null, filed, null, RestErrorStatus.CLASS_ERROR, error));
    }

    public ClassErrorException(String filed, Object value) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(null, filed, value, RestErrorStatus.CLASS_ERROR));
    }

    public ClassErrorException(String filed, Object value, RestStatus status) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(null, filed, value, status));
    }

    public ClassErrorException(String filed, Object value, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(null, filed, value, RestErrorStatus.CLASS_ERROR, error));
    }

    public ClassErrorException(String filed, Object value, RestStatus status, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(null, filed, value, status, error));
    }

    public ClassErrorException(String resource, String filed, Object value) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CLASS_ERROR));
    }

    public ClassErrorException(String resource, String filed, Object value, RestStatus status) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, value, status));
    }

    public ClassErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CLASS_ERROR, error));
    }

    public ClassErrorException(String resource, String filed, Object value, RestStatus status, String error) {
        super(RestErrorStatus.CLASS_ERROR, RestError.error(resource, filed, value, status, error));
    }

    @Override
    public ClassErrorException get() {
        return new ClassErrorException();
    }
}
