package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataBatchInsertException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchInsertException extends DataErrorException {
    public DataBatchInsertException() {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED);
    }

    public DataBatchInsertException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchInsertException(RestStatus status) {
        super(status);
    }

    public DataBatchInsertException(String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, message);
    }

    public DataBatchInsertException(String resource, String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, resource, message);
    }

    public DataBatchInsertException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchInsertException get() {
        return new DataBatchInsertException();
    }
}
