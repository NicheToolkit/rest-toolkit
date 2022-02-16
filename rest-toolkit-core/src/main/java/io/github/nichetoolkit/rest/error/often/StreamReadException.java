package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>StreamReadException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamReadException extends RestErrorException {
    public StreamReadException() {
        super(RestErrorStatus.STREAM_READ_ERROR);
    }

    public StreamReadException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public StreamReadException(String message) {
        super(RestErrorStatus.STREAM_READ_ERROR, RestError.error(RestErrorStatus.STREAM_READ_ERROR, message));
    }

    public StreamReadException(String resource, String message) {
        super(RestErrorStatus.STREAM_READ_ERROR, RestError.error(resource, RestErrorStatus.STREAM_READ_ERROR, message));
    }

    @Override
    public StreamReadException get() {
        return new StreamReadException();
    }
}
