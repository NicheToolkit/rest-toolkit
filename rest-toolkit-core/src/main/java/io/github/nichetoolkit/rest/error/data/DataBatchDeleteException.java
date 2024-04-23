package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataBatchDeleteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchDeleteException extends DataErrorException {
    public DataBatchDeleteException() {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED);
    }

    public DataBatchDeleteException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchDeleteException(RestStatus status) {
        super(status);
    }

    public DataBatchDeleteException(String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, message);
    }

    public DataBatchDeleteException(String resource, String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, resource, message);
    }

    public DataBatchDeleteException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchDeleteException get() {
        return new DataBatchDeleteException();
    }
}

