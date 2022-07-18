package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataCreateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataDeleteException extends RestErrorException {
    public DataDeleteException() {
        super(RestErrorStatus.DATA_DELETE_FAILED);
    }

    public DataDeleteException(RestErrorStatus status) {
        super(status);
    }

    public DataDeleteException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataDeleteException(String resource) {
        super(RestErrorStatus.DATA_DELETE_FAILED, RestError.error(resource, RestErrorStatus.DATA_DELETE_FAILED));
    }

    public DataDeleteException(String resource, String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, RestError.error(resource, RestErrorStatus.DATA_DELETE_FAILED, message));
    }

    public DataDeleteException(String resource, Object value, String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, RestError.error(resource, value, RestErrorStatus.DATA_DELETE_FAILED, message));
    }

    @Override
    public DataDeleteException get() {
        return new DataDeleteException();
    }
}
