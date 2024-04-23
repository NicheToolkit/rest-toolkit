package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ServiceErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ServiceErrorException extends RestErrorException {
    
    public ServiceErrorException() {
        super(RestErrorStatus.SERVICE_ERROR);
    }

    public ServiceErrorException(RestErrorStatus status) {
        super(status);
    }

    public ServiceErrorException(String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(RestErrorStatus.SERVICE_ERROR, error));
    }

    public ServiceErrorException(String error, Throwable cause) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(RestErrorStatus.SERVICE_ERROR, error, cause), cause);
    }

    public ServiceErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ServiceErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public ServiceErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ServiceErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public ServiceErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public ServiceErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public ServiceErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ServiceErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public ServiceErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public ServiceErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public ServiceErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public ServiceErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public ServiceErrorException(String resource, String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(resource, RestErrorStatus.SERVICE_ERROR, error));
    }

    public ServiceErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(resource, RestErrorStatus.SERVICE_ERROR, error, cause), cause);
    }

    public ServiceErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(resource, filed, RestErrorStatus.SERVICE_ERROR, error));
    }

    public ServiceErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(resource, filed, RestErrorStatus.SERVICE_ERROR, error, cause), cause);
    }

    public ServiceErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(resource, filed, value, RestErrorStatus.SERVICE_ERROR, error));
    }

    public ServiceErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(resource, filed, value, RestErrorStatus.SERVICE_ERROR, error, cause));
    }

    @Override
    public ServiceErrorException get() {
        return new ServiceErrorException();
    }
}
