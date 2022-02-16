package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>StreamTransferException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamTransferException extends RestErrorException {
    public StreamTransferException() {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR);
    }

    public StreamTransferException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public StreamTransferException(String message) {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR, RestError.error(RestErrorStatus.STREAM_TRANSFER_ERROR, message));
    }

    public StreamTransferException(String resource, String message) {
        super(RestErrorStatus.STREAM_TRANSFER_ERROR, RestError.error(resource, RestErrorStatus.STREAM_TRANSFER_ERROR, message));
    }

    @Override
    public StreamTransferException get() {
        return new StreamTransferException();
    }
}
