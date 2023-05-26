package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataBatchUpdateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchUpdateException extends DataErrorException {
    public DataBatchUpdateException() {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED);
    }

    public DataBatchUpdateException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchUpdateException(RestStatus status) {
        super(status);
    }

    public DataBatchUpdateException(String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, message);
    }

    public DataBatchUpdateException(String resource, String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, resource, message);
    }

    public DataBatchUpdateException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchUpdateException get() {
        return new DataBatchUpdateException();
    }
}
