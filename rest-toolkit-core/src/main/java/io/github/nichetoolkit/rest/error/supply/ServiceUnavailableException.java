package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ServiceUnavailableException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ServiceUnavailableException extends RestErrorException {
    public ServiceUnavailableException() {
        super(RestErrorStatus.SERVICE_UNAVAILABLE);
    }

    public ServiceUnavailableException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ServiceUnavailableException(String service) {
        super(RestErrorStatus.SERVICE_UNAVAILABLE, RestError.error(service, RestErrorStatus.SERVICE_UNAVAILABLE));
    }

    public ServiceUnavailableException(String resource, String service, String error) {
        super(RestErrorStatus.SERVICE_UNAVAILABLE, RestError.error(resource, service, null, RestErrorStatus.SERVICE_UNAVAILABLE, error));
    }

    @Override
    public ServiceUnavailableException get() {
        return new ServiceUnavailableException();
    }
}
