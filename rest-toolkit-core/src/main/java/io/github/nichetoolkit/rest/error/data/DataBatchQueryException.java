package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataBatchQueryException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataBatchQueryException extends RestErrorException {
    public DataBatchQueryException() {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED);
    }

    public DataBatchQueryException(RestErrorStatus status) {
        super(status);
    }

    public DataBatchQueryException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataBatchQueryException(String resource) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_QUERY_ALL_FAILED));
    }

    public DataBatchQueryException(String resource, String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, RestError.error(resource, RestErrorStatus.DATA_QUERY_ALL_FAILED, message));
    }

    public DataBatchQueryException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_QUERY_ALL_FAILED, message));
    }

    @Override
    public DataBatchQueryException get() {
        return new DataBatchQueryException();
    }
}
