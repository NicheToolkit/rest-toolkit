package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ResourceNotFoundException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ResourceNotFoundException extends RestErrorException {
    public ResourceNotFoundException() {
        super(RestErrorStatus.RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ResourceNotFoundException(String message) {
        super(RestErrorStatus.RESOURCE_NOT_FOUND, RestError.error(RestErrorStatus.RESOURCE_NOT_FOUND,message));
    }

    public ResourceNotFoundException(String resource, String message) {
        super(RestErrorStatus.RESOURCE_NOT_FOUND, RestError.error(resource, RestErrorStatus.RESOURCE_NOT_FOUND,message));
    }

    @Override
    public ResourceNotFoundException get() {
        return new ResourceNotFoundException();
    }
}