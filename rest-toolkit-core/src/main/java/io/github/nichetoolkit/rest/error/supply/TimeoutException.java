package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>TimeoutException</code>
 * <p>The timeout exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class TimeoutException extends RestErrorException {

    /**
     * <code>TimeoutException</code>
     * <p>Instantiates a new timeout exception.</p>
     */
    public TimeoutException() {
        super(RestErrorStatus.TIMEOUT);
    }

    /**
     * <code>TimeoutException</code>
     * <p>Instantiates a new timeout exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public TimeoutException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>TimeoutException</code>
     * <p>Instantiates a new timeout exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
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
