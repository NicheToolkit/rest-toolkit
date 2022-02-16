package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataBatchInsertException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchInsertException extends RestErrorException {
    public DataBatchInsertException() {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED);
    }

    public DataBatchInsertException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchInsertException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataBatchInsertException(String resource) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_INSERT_ALL_FAILED));
    }

    public DataBatchInsertException(String resource, String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_INSERT_ALL_FAILED, message));
    }

    public DataBatchInsertException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_INSERT_ALL_FAILED, message));
    }

    @Override
    public DataBatchInsertException get() {
        return new DataBatchInsertException();
    }
}
