package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataBatchDeleteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchDeleteException extends RestErrorException {
    public DataBatchDeleteException() {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED);
    }

    public DataBatchDeleteException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchDeleteException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataBatchDeleteException(String resource) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_DELETE_ALL_FAILED));
    }

    public DataBatchDeleteException(String resource, String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_DELETE_ALL_FAILED, message));
    }

    public DataBatchDeleteException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_DELETE_ALL_FAILED, message));
    }

    @Override
    public DataBatchDeleteException get() {
        return new DataBatchDeleteException();
    }
}

