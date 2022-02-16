package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>StreamWriteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamWriteException extends RestErrorException {
    public StreamWriteException() {
        super(RestErrorStatus.STREAM_WRITE_ERROR);
    }

    public StreamWriteException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public StreamWriteException(String message) {
        super(RestErrorStatus.STREAM_WRITE_ERROR, RestError.error(RestErrorStatus.STREAM_WRITE_ERROR, message));
    }

    public StreamWriteException(String resource, String message) {
        super(RestErrorStatus.STREAM_WRITE_ERROR, RestError.error(resource, RestErrorStatus.STREAM_WRITE_ERROR, message));
    }

    @Override
    public StreamWriteException get() {
        return new StreamWriteException();
    }
}
