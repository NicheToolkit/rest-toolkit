package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataCreateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataCreateException extends RestErrorException {
    public DataCreateException() {
        super(RestErrorStatus.DATA_CREATE_FAILED);
    }

    public DataCreateException(RestErrorStatus status) {
        super(status);
    }

    public DataCreateException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataCreateException(String resource) {
        super(RestErrorStatus.DATA_CREATE_FAILED, RestError.error(resource, RestErrorStatus.DATA_CREATE_FAILED));
    }

    public DataCreateException(String resource, String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, RestError.error(resource, RestErrorStatus.DATA_CREATE_FAILED, message));
    }

    public DataCreateException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_CREATE_FAILED, message));
    }

    @Override
    public DataCreateException get() {
        return new DataCreateException();
    }
}
