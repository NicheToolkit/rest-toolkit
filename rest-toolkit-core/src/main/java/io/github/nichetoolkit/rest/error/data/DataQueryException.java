package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataQueryException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataQueryException extends RestErrorException {
    public DataQueryException() {
        super(RestErrorStatus.DATA_QUERY_FAILED);
    }

    public DataQueryException(RestErrorStatus status) {
        super(status);
    }

    public DataQueryException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataQueryException(String resource) {
        super(RestErrorStatus.DATA_QUERY_FAILED, RestError.error(resource, RestErrorStatus.DATA_QUERY_FAILED));
    }

    public DataQueryException(String resource, String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, RestError.error(resource, RestErrorStatus.DATA_QUERY_FAILED,message));
    }

    public DataQueryException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, RestError.error(resource,value, RestErrorStatus.DATA_QUERY_FAILED,message));
    }

    @Override
    public DataQueryException get() {
        return new DataQueryException();
    }
}
