package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>TimeoutException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TimeoutException extends RestErrorException {

    public TimeoutException() {
        super(RestErrorStatus.TIME_OUT);
    }

    public TimeoutException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public TimeoutException(String resource) {
        super(RestErrorStatus.TIME_OUT, RestError.error(resource, RestErrorStatus.TIME_OUT));
    }

    @Override
    public TimeoutException get() {
        return new TimeoutException();
    }
}
