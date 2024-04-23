package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataBatchQueryException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchQueryException extends DataErrorException {
    public DataBatchQueryException() {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED);
    }

    public DataBatchQueryException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchQueryException(RestStatus status) {
        super(status);
    }

    public DataBatchQueryException(String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, message);
    }

    public DataBatchQueryException(String resource, String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, resource, message);
    }

    public DataBatchQueryException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchQueryException get() {
        return new DataBatchQueryException();
    }
}
