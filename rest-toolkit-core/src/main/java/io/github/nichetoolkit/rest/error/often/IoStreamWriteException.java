package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IoStreamErrorException;

/**
 * <code>StreamWriteException</code>
 * <p>The type stream write exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see IoStreamErrorException
 * @since Jdk1.8
 */
public class IoStreamWriteException extends IoStreamErrorException {
    /**
     * <code>StreamWriteException</code>
     * Instantiates a new stream write exception.
     */
    public IoStreamWriteException() {
        super(RestErrorStatus.IO_STREAM_WRITE_ERROR);
    }

    /**
     * <code>StreamWriteException</code>
     * Instantiates a new stream write exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IoStreamWriteException(RestStatus status) {
        super(status);
    }

    /**
     * <code>StreamWriteException</code>
     * Instantiates a new stream write exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamWriteException(String message) {
        super(RestErrorStatus.IO_STREAM_WRITE_ERROR, message);
    }

    /**
     * <code>StreamWriteException</code>
     * Instantiates a new stream write exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamWriteException(String resource, String message) {
        super(RestErrorStatus.IO_STREAM_WRITE_ERROR, resource,  message);
    }

    @Override
    public IoStreamWriteException get() {
        return new IoStreamWriteException();
    }
}