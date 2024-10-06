package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IoStreamErrorException;

/**
 * <code>IoStreamWriteException</code>
 * <p>The type io stream write exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.IoStreamErrorException
 * @since Jdk1.8
 */
public class IoStreamWriteException extends IoStreamErrorException {
    /**
     * <code>IoStreamWriteException</code>
     * <p>Instantiates a new io stream write exception.</p>
     */
    public IoStreamWriteException() {
        super(RestErrorStatus.IO_STREAM_WRITE_ERROR);
    }

    /**
     * <code>IoStreamWriteException</code>
     * <p>Instantiates a new io stream write exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IoStreamWriteException(RestStatus status) {
        super(status);
    }

    /**
     * <code>IoStreamWriteException</code>
     * <p>Instantiates a new io stream write exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamWriteException(String message) {
        super(RestErrorStatus.IO_STREAM_WRITE_ERROR, message);
    }

    /**
     * <code>IoStreamWriteException</code>
     * <p>Instantiates a new io stream write exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
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
