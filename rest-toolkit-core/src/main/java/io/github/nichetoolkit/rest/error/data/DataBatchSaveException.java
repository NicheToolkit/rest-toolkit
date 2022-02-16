package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataBatchSaveException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchSaveException extends RestErrorException {
    public DataBatchSaveException() {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED);
    }

    public DataBatchSaveException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchSaveException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataBatchSaveException(String resource) {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_SAVE_ALL_FAILED));
    }

    public DataBatchSaveException(String resource, String message) {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_SAVE_ALL_FAILED, message));
    }

    public DataBatchSaveException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_SAVE_ALL_FAILED, message));
    }

    @Override
    public DataBatchSaveException get() {
        return new DataBatchSaveException();
    }
}