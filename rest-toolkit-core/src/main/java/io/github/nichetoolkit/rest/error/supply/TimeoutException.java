package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>TimeoutException</code>
 * <p>The type timeout exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class TimeoutException extends RestErrorException {

    /**
     * <code>TimeoutException</code>
     * Instantiates a new timeout exception.
     */
    public TimeoutException() {
        super(RestErrorStatus.TIMEOUT);
    }

    /**
     * <code>TimeoutException</code>
     * Instantiates a new timeout exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public TimeoutException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>TimeoutException</code>
     * Instantiates a new timeout exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public TimeoutException(String message) {
        super(RestErrorStatus.TIMEOUT, RestError.error(RestErrorStatus.TIMEOUT,message));
    }

    @Override
    public TimeoutException get() {
        return new TimeoutException();
    }
}
