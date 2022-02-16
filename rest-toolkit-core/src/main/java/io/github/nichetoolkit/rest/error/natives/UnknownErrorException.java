package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>UnknownErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class UnknownErrorException extends RestErrorException {

    public UnknownErrorException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    public UnknownErrorException(RestErrorStatus status) {
        super(status);
    }

    public UnknownErrorException(String error) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(RestErrorStatus.UNKNOWN_ERROR, error));
    }

    public UnknownErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public UnknownErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public UnknownErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public UnknownErrorException(String target, String error) {
        super(RestErrorStatus.UNKNOWN_ERROR, RestError.error(target, RestErrorStatus.UNKNOWN_ERROR, error));
    }

    public UnknownErrorException(String target, RestStatus status) {
        super(status, RestError.error(target, status));
    }

    @Override
    public UnknownErrorException get() {
        return new UnknownErrorException();
    }
}
