package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IoStreamErrorException;

/**
 * <code>IoStreamReadException</code>
 * <p>The type io stream read exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.IoStreamErrorException
 * @since Jdk1.8
 */
public class IoStreamReadException extends IoStreamErrorException {
    /**
     * <code>IoStreamReadException</code>
     * Instantiates a new io stream read exception.
     */
    public IoStreamReadException() {
        super(RestErrorStatus.IO_STREAM_READ_ERROR);
    }

    /**
     * <code>IoStreamReadException</code>
     * Instantiates a new io stream read exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IoStreamReadException(RestStatus status) {
        super(status);
    }

    /**
     * <code>IoStreamReadException</code>
     * Instantiates a new io stream read exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamReadException(String message) {
        super(RestErrorStatus.IO_STREAM_READ_ERROR, message);
    }

    /**
     * <code>IoStreamReadException</code>
     * Instantiates a new io stream read exception.
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
