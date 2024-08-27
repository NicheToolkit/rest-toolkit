package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.StreamErrorException;

/**
 * <code>StreamReadException</code>
 * <p>The type stream read exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.StreamErrorException
 * @since Jdk1.8
 */
public class StreamReadException extends StreamErrorException {
    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     */
    public StreamReadException() {
        super(RestErrorStatus.STREAM_READ_ERROR);
    }

    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public StreamReadException(RestStatus status) {
        super(status);
    }

    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public StreamReadException(String message) {
        super(RestErrorStatus.STREAM_READ_ERROR, message);
    }

    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public StreamReadException(String resource, String message) {
        super(RestErrorStatus.STREAM_READ_ERROR, resource,  message);
    }

    @Override
    public StreamReadException get() {
        return new StreamReadException();
    }
}
