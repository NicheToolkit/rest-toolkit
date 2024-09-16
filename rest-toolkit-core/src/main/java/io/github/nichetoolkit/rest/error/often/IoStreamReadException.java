package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IoStreamErrorException;

/**
 * <code>StreamReadException</code>
 * <p>The type stream read exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see IoStreamErrorException
 * @since Jdk1.8
 */
public class IoStreamReadException extends IoStreamErrorException {
    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     */
    public IoStreamReadException() {
        super(RestErrorStatus.IO_STREAM_READ_ERROR);
    }

    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IoStreamReadException(RestStatus status) {
        super(status);
    }

    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamReadException(String message) {
        super(RestErrorStatus.IO_STREAM_READ_ERROR, message);
    }

    /**
     * <code>StreamReadException</code>
     * Instantiates a new stream read exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamReadException(String resource, String message) {
        super(RestErrorStatus.IO_STREAM_READ_ERROR, resource,  message);
    }

    @Override
    public IoStreamReadException get() {
        return new IoStreamReadException();
    }
}
