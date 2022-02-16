package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>TimeoutException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TimeoutErrorException extends RestErrorException {

    public TimeoutErrorException() {
        super(RestErrorStatus.TIME_OUT);
    }

    public TimeoutErrorException(RestErrorStatus status) {
        super(status);
    }

    public TimeoutErrorException(String error) {
        super(RestErrorStatus.TIME_OUT, RestError.error(error));
    }

    public TimeoutErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public TimeoutErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public TimeoutErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public TimeoutErrorException(String resource, String error) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource,error));
    }

    public TimeoutErrorException(String resource, RestStatus status) {
        super(status, RestError.error(resource, status));
    }

    public TimeoutErrorException(String resource, RestStatus status, String error) {
        super(status, RestError.error(resource, status, error));
    }

    @Override
    public TimeoutErrorException get() {
        return new TimeoutErrorException();
    }

}
