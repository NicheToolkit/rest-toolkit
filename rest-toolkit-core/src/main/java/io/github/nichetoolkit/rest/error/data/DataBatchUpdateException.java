package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataBatchUpdateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchUpdateException extends RestErrorException {
    public DataBatchUpdateException() {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED);
    }

    public DataBatchUpdateException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchUpdateException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataBatchUpdateException(String resource) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_UPDATE_ALL_FAILED));
    }

    public DataBatchUpdateException(String resource, String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_UPDATE_ALL_FAILED, message));
    }

    public DataBatchUpdateException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_UPDATE_ALL_FAILED, message));
    }

    @Override
    public DataBatchUpdateException get() {
        return new DataBatchUpdateException();
    }
}
