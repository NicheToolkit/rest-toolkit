package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.StreamErrorException;

/**
 * <p>StreamReadException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamReadException extends StreamErrorException {
    public StreamReadException() {
        super(RestErrorStatus.STREAM_READ_ERROR);
    }

    public StreamReadException(RestStatus status) {
        super(status);
    }

    public StreamReadException(String message) {
        super(RestErrorStatus.STREAM_READ_ERROR, message);
    }

    public StreamReadException(String resource, String message) {
        super(RestErrorStatus.STREAM_READ_ERROR, resource,  message);
    }

    @Override
    public StreamReadException get() {
        return new StreamReadException();
    }
}
