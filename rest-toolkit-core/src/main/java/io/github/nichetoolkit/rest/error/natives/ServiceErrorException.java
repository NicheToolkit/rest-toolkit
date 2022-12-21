package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ServiceErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ServiceErrorException extends RestErrorException {
    
    public ServiceErrorException() {
        super(RestErrorStatus.SERVICE_ERROR);
    }

    public ServiceErrorException(RestErrorStatus status) {
        super(status);
    }

    public ServiceErrorException(String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(RestErrorStatus.SERVICE_ERROR, error));
    }

    public ServiceErrorException(String error,Throwable cause) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error( RestErrorStatus.SERVICE_ERROR, error, cause));
    }

    public ServiceErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ServiceErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ServiceErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public ServiceErrorException(RestStatus status, String error, Throwable cause) {
        super(status, RestError.error(status, error, cause));
    }

    public ServiceErrorException(String service, String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error( service, RestErrorStatus.SERVICE_ERROR, error));
    }
    
    public ServiceErrorException(String service, RestStatus status) {
        super(status, RestError.error(service, status));
    }

    public ServiceErrorException(String service, RestStatus status, String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error( service, status, error));
    }

    public ServiceErrorException(String service, RestStatus status, String error, Throwable cause) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error( service, status, error, cause));
    }

    public ServiceErrorException(String resource, String service, RestStatus status) {
        super(status, RestError.error(resource, service, status));
    }

    public ServiceErrorException(String resource, String service, String error) {
        super(RestErrorStatus.SERVICE_ERROR, RestError.error(resource, service, RestErrorStatus.SERVICE_ERROR, error));
    }

    public ServiceErrorException(String resource, String service, RestStatus status, String error) {
        super(status, RestError.error(resource, service, status, error));
    }

    public ServiceErrorException(String resource, String service, RestStatus status, String error,Throwable cause) {
        super(status, RestError.error(resource, service, status, error, cause));
    }

    @Override
    public ServiceErrorException get() {
        return new ServiceErrorException();
    }
}
