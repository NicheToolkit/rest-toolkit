package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>StreamWriteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ZipErrorException extends RestErrorException {
    public ZipErrorException() {
        super(RestErrorStatus.ZIP_ERROR);
    }

    public ZipErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ZipErrorException(String message) {
        super(RestErrorStatus.ZIP_ERROR, RestError.error(RestErrorStatus.ZIP_ERROR, message));
    }

    public ZipErrorException(String resource, String message) {
        super(RestErrorStatus.ZIP_ERROR, RestError.error(resource, RestErrorStatus.ZIP_ERROR, message));
    }

    @Override
    public ZipErrorException get() {
        return new ZipErrorException();
    }
}
