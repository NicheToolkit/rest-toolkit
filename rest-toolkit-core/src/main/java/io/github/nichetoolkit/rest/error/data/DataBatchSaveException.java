package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataBatchSaveException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchSaveException extends DataErrorException {
    public DataBatchSaveException() {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED);
    }

    public DataBatchSaveException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchSaveException(RestStatus status) {
        super(status);
    }

    public DataBatchSaveException(String message) {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED, message);
    }

    public DataBatchSaveException(String resource, String message) {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED, resource, message);
    }

    public DataBatchSaveException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_SAVE_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchSaveException get() {
        return new DataBatchSaveException();
    }
}