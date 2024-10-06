package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>ResourceNotFoundException</code>
 * <p>The type resource not found exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ResourceNotFoundException extends RestErrorException {
    /**
     * <code>ResourceNotFoundException</code>
     * <p>Instantiates a new resource not found exception.</p>
     */
    public ResourceNotFoundException() {
        super(RestErrorStatus.RESOURCE_NOT_FOUND);
    }

    /**
     * <code>ResourceNotFoundException</code>
     * <p>Instantiates a new resource not found exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ResourceNotFoundException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ResourceNotFoundException</code>
     * <p>Instantiates a new resource not found exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ResourceNotFoundException(String message) {
        super(RestErrorStatus.RESOURCE_NOT_FOUND, RestError.error(RestErrorStatus.RESOURCE_NOT_FOUND,message));
    }

    /**
     * <code>ResourceNotFoundException</code>
     * <p>Instantiates a new resource not found exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ResourceNotFoundException(String resource, String message) {
        super(RestErrorStatus.RESOURCE_NOT_FOUND, RestError.error(resource, RestErrorStatus.RESOURCE_NOT_FOUND,message));
    }

    @Override
    public ResourceNotFoundException get() {
        return new ResourceNotFoundException();
    }
}