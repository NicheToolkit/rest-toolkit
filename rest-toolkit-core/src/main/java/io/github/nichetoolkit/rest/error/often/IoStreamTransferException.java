package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IoStreamErrorException;

/**
 * <code>StreamTransferException</code>
 * <p>The type stream transfer exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see IoStreamErrorException
 * @since Jdk1.8
 */
public class IoStreamTransferException extends IoStreamErrorException {
    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     */
    public IoStreamTransferException() {
        super(RestErrorStatus.IO_STREAM_TRANSFER_ERROR);
    }

    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IoStreamTransferException(RestStatus status) {
        super(status);
    }

    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamTransferException(String message) {
        super(RestErrorStatus.IO_STREAM_TRANSFER_ERROR, message);
    }

    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamTransferException(String resource, String message) {
        super(RestErrorStatus.IO_STREAM_TRANSFER_ERROR, resource,  message);
    }

    @Override
    public IoStreamTransferException get() {
        return new IoStreamTransferException();
    }
}
