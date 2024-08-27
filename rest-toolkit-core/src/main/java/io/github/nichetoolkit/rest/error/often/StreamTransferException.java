package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.StreamErrorException;

/**
 * <code>StreamTransferException</code>
 * <p>The type stream transfer exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.StreamErrorException
 * @since Jdk1.8
 */
public class StreamTransferException extends StreamErrorException {
    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     */
    public StreamTransferException() {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR);
    }

    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public StreamTransferException(RestStatus status) {
        super(status);
    }

    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public StreamTransferException(String message) {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR, message);
    }

    /**
     * <code>StreamTransferException</code>
     * Instantiates a new stream transfer exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public StreamTransferException(String resource, String message) {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR, resource,  message);
    }

    @Override
    public StreamTransferException get() {
        return new StreamTransferException();
    }
}
