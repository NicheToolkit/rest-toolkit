package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>StreamErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class StreamErrorException  extends RestErrorException {

    public StreamErrorException() {
        super(RestErrorStatus.STREAM_ERROR);
    }

    public StreamErrorException(RestErrorStatus status) {
        super(status);
    }

    public StreamErrorException(String error) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(RestErrorStatus.STREAM_ERROR, error));
    }

    public StreamErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public StreamErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public StreamErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public StreamErrorException(String resource, String error) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error( resource, RestErrorStatus.STREAM_ERROR, error));
    }

    public StreamErrorException(String resource, RestStatus status) {
        super(status, RestError.error(resource, status));
    }

    public StreamErrorException(String service, RestStatus status, String error) {
        super(status, RestError.error( service, status, error));
    }

    public StreamErrorException(String resource, String service, RestStatus status) {
        super(status, RestError.error(resource, service, status));
    }

    public StreamErrorException(String resource, String service, String error) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(resource, service, RestErrorStatus.STREAM_ERROR, error));
    }

    public StreamErrorException(String resource, String service, RestStatus status, String error) {
        super(status, RestError.error(resource, service, status, error));
    }

    @Override
    public StreamErrorException get() {
        return new StreamErrorException();
    }
}

