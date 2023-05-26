package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataQueryException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataQueryException extends DataErrorException {
    public DataQueryException() {
        super(RestErrorStatus.DATA_QUERY_FAILED);
    }

    public DataQueryException(RestErrorStatus status) {
        super(status);
    }

    public DataQueryException(RestStatus status) {
        super(status);
    }

    public DataQueryException(String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, message);
    }

    public DataQueryException(String resource, String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, resource, message);
    }

    public DataQueryException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, resource, field, value, message);
    }

    @Override
    public DataQueryException get() {
        return new DataQueryException();
    }
}
