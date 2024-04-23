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
public class StreamErrorException extends RestErrorException {

    public StreamErrorException() {
        super(RestErrorStatus.STREAM_ERROR);
    }

    public StreamErrorException(RestErrorStatus status) {
        super(status);
    }

    public StreamErrorException(String error) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(RestErrorStatus.STREAM_ERROR, error));
    }

    public StreamErrorException(String error, Throwable cause) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(RestErrorStatus.STREAM_ERROR, error, cause), cause);
    }

    public StreamErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public StreamErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public StreamErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public StreamErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public StreamErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public StreamErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public StreamErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public StreamErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public StreamErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public StreamErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public StreamErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public StreamErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public StreamErrorException(String resource, String error) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(resource, RestErrorStatus.STREAM_ERROR, error));
    }

    public StreamErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(resource, RestErrorStatus.STREAM_ERROR, error, cause), cause);
    }

    public StreamErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(resource, filed, RestErrorStatus.STREAM_ERROR, error));
    }

    public StreamErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(resource, filed, RestErrorStatus.STREAM_ERROR, error, cause), cause);
    }

    public StreamErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(resource, filed, value, RestErrorStatus.STREAM_ERROR, error));
    }

    public StreamErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.STREAM_ERROR, RestError.error(resource, filed, value, RestErrorStatus.STREAM_ERROR, error, cause));
    }

    @Override
    public StreamErrorException get() {
        return new StreamErrorException();
    }
}

