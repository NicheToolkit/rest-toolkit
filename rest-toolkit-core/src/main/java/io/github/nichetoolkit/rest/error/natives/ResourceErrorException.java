package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ResourceErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ResourceErrorException extends RestErrorException {

    public ResourceErrorException() {
        super(RestErrorStatus.RESOURCE_ERROR);
    }

    public ResourceErrorException(RestErrorStatus status) {
        super(status);
    }

    public ResourceErrorException(String error) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(RestErrorStatus.RESOURCE_ERROR, error));
    }

    public ResourceErrorException(String error, Throwable cause) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(RestErrorStatus.RESOURCE_ERROR, error, cause), cause);
    }

    public ResourceErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ResourceErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public ResourceErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ResourceErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public ResourceErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public ResourceErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public ResourceErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ResourceErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public ResourceErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public ResourceErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public ResourceErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public ResourceErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public ResourceErrorException(String resource, String error) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(resource, RestErrorStatus.RESOURCE_ERROR, error));
    }

    public ResourceErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(resource, RestErrorStatus.RESOURCE_ERROR, error, cause), cause);
    }

    public ResourceErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(resource, filed, RestErrorStatus.RESOURCE_ERROR, error));
    }

    public ResourceErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(resource, filed, RestErrorStatus.RESOURCE_ERROR, error, cause), cause);
    }

    public ResourceErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(resource, filed, value, RestErrorStatus.RESOURCE_ERROR, error));
    }

    public ResourceErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(resource, filed, value, RestErrorStatus.RESOURCE_ERROR, error, cause));
    }
    
    @Override
    public ResourceErrorException get() {
        return new ResourceErrorException();
    }

}
