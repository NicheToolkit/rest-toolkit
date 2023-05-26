package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.StreamErrorException;

/**
 * <p>StreamTransferException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamTransferException extends StreamErrorException {
    public StreamTransferException() {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR);
    }

    public StreamTransferException(RestStatus status) {
        super(status);
    }

    public StreamTransferException(String message) {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR, message);
    }

    public StreamTransferException(String resource, String message) {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR, resource,  message);
    }

    @Override
    public StreamTransferException get() {
        return new StreamTransferException();
    }
}
