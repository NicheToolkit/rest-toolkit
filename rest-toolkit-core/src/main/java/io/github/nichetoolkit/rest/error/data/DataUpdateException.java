package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataUpdateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataUpdateException extends RestErrorException {
    public DataUpdateException() {
        super(RestErrorStatus.DATA_UPDATE_FAILED);
    }

    public DataUpdateException(RestErrorStatus status) {
        super(status);
    }

    public DataUpdateException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataUpdateException(String resource) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, RestError.error(resource, RestErrorStatus.DATA_UPDATE_FAILED));
    }

    public DataUpdateException(String resource, String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, RestError.error(resource, RestErrorStatus.DATA_UPDATE_FAILED, message));
    }

    public DataUpdateException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_UPDATE_FAILED, message));
    }

    @Override
    public DataUpdateException get() {
        return new DataUpdateException();
    }
}