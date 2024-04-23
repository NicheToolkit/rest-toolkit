package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataDeleteException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataDeleteException extends DataErrorException {
    public DataDeleteException() {
        super(RestErrorStatus.DATA_DELETE_FAILED);
    }

    public DataDeleteException(RestErrorStatus status) {
        super(status);
    }

    public DataDeleteException(RestStatus status) {
        super(status);
    }

    public DataDeleteException(String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, message);
    }

    public DataDeleteException(String resource, String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, resource, message);
    }

    public DataDeleteException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, resource, field, value, message);
    }


    @Override
    public DataDeleteException get() {
        return new DataDeleteException();
    }
}
