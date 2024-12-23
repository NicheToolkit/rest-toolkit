package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>ServiceUnavailableException</code>
 * <p>The service unavailable exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ServiceUnavailableException extends RestErrorException {
    /**
     * <code>ServiceUnavailableException</code>
     * <p>Instantiates a new service unavailable exception.</p>
     */
    public ServiceUnavailableException() {
        super(RestErrorStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * <code>ServiceUnavailableException</code>
     * <p>Instantiates a new service unavailable exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ServiceUnavailableException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ServiceUnavailableException</code>
     * <p>Instantiates a new service unavailable exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnavailableException(String message) {
        super(RestErrorStatus.SERVICE_UNAVAILABLE, RestError.error(RestErrorStatus.SERVICE_UNAVAILABLE, message));
    }

    /**
     * <code>ServiceUnavailableException</code>
     * <p>Instantiates a new service unavailable exception.</p>
     * @param service {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnavailableException(String service, String message) {
        super(RestErrorStatus.SERVICE_UNAVAILABLE, RestError.error(service, RestErrorStatus.SERVICE_UNAVAILABLE, message));
    }

    /**
     * <code>ServiceUnavailableException</code>
     * <p>Instantiates a new service unavailable exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param service  {@link java.lang.String} <p>The service parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServiceUnavailableException(String resource, String service, String message) {
        super(RestErrorStatus.SERVICE_UNAVAILABLE, RestError.error(resource, service, RestErrorStatus.SERVICE_UNAVAILABLE, message));
    }

    @Override
    public ServiceUnavailableException get() {
        return new ServiceUnavailableException();
    }
}
