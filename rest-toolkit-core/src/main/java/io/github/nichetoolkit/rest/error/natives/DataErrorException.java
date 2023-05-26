package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataErrorException extends RestErrorException {

    public DataErrorException() {
        super(RestErrorStatus.DATA_ERROR);
    }

    public DataErrorException(RestErrorStatus status) {
        super(status);
    }

    public DataErrorException(String error) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(RestErrorStatus.DATA_ERROR, error));
    }

    public DataErrorException(String error, Throwable cause) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(RestErrorStatus.DATA_ERROR, error, cause), cause);
    }

    public DataErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public DataErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public DataErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public DataErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public DataErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public DataErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public DataErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public DataErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public DataErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public DataErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public DataErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public DataErrorException(String resource, String error) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(resource, RestErrorStatus.DATA_ERROR, error));
    }

    public DataErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(resource, RestErrorStatus.DATA_ERROR, error, cause), cause);
    }

    public DataErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(resource, filed, RestErrorStatus.DATA_ERROR, error));
    }

    public DataErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(resource, filed, RestErrorStatus.DATA_ERROR, error, cause), cause);
    }

    public DataErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(resource, filed, value, RestErrorStatus.DATA_ERROR, error));
    }

    public DataErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(resource, filed, value, RestErrorStatus.DATA_ERROR, error, cause));
    }
    
    @Override
    public DataErrorException get() {
        return new DataErrorException();
    }

}
