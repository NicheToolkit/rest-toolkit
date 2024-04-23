package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataUpdateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataUpdateException extends DataErrorException {
    public DataUpdateException() {
        super(RestErrorStatus.DATA_UPDATE_FAILED);
    }

    public DataUpdateException(RestErrorStatus status) {
        super(status);
    }

    public DataUpdateException(RestStatus status) {
        super(status);
    }

    public DataUpdateException(String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, message);
    }

    public DataUpdateException(String resource, String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, resource, message);
    }

    public DataUpdateException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, resource, field, value, message);
    }

    @Override
    public DataUpdateException get() {
        return new DataUpdateException();
    }
}