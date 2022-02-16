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
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(RestErrorStatus.RESOURCE_ERROR,error));
    }

    public ResourceErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ResourceErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    public ResourceErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ResourceErrorException(String resource, String error) {
        super(RestErrorStatus.RESOURCE_ERROR, RestError.error(resource, RestErrorStatus.RESOURCE_ERROR, error));
    }

    public ResourceErrorException(String resource, RestStatus status) {
        super(status, RestError.error(resource, status));
    }

    public ResourceErrorException(String resource, RestStatus status, String error) {
        super(status, RestError.error(resource, status, error));
    }

    @Override
    public ResourceErrorException get() {
        return new ResourceErrorException();
    }

}
