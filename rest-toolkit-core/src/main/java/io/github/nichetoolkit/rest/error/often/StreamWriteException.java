package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.StreamErrorException;

/**
 * <p>StreamWriteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamWriteException extends StreamErrorException {
    public StreamWriteException() {
        super(RestErrorStatus.STREAM_WRITE_ERROR);
    }

    public StreamWriteException(RestStatus status) {
        super(status);
    }

    public StreamWriteException(String message) {
        super(RestErrorStatus.STREAM_WRITE_ERROR, message);
    }

    public StreamWriteException(String resource, String message) {
        super(RestErrorStatus.STREAM_WRITE_ERROR, resource,  message);
    }

    @Override
    public StreamWriteException get() {
        return new StreamWriteException();
    }
}
