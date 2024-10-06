package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IoStreamErrorException;

/**
 * <code>IoStreamTransferException</code>
 * <p>The type io stream transfer exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.IoStreamErrorException
 * @since Jdk1.8
 */
public class IoStreamTransferException extends IoStreamErrorException {
    /**
     * <code>IoStreamTransferException</code>
     * <p>Instantiates a new io stream transfer exception.</p>
     */
    public IoStreamTransferException() {
        super(RestErrorStatus.IO_STREAM_TRANSFER_ERROR);
    }

    /**
     * <code>IoStreamTransferException</code>
     * <p>Instantiates a new io stream transfer exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IoStreamTransferException(RestStatus status) {
        super(status);
    }

    /**
     * <code>IoStreamTransferException</code>
     * <p>Instantiates a new io stream transfer exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamTransferException(String message) {
        super(RestErrorStatus.IO_STREAM_TRANSFER_ERROR, message);
    }

    /**
     * <code>IoStreamTransferException</code>
     * <p>Instantiates a new io stream transfer exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
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
