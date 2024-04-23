package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <p>DataCreateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataCreateException extends DataErrorException {
    public DataCreateException() {
        super(RestErrorStatus.DATA_CREATE_FAILED);
    }

    public DataCreateException(RestErrorStatus status) {
        super(status);
    }

    public DataCreateException(RestStatus status) {
        super(status);
    }

    public DataCreateException(String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, message);
    }

    public DataCreateException(String resource, String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, resource, message);
    }

    public DataCreateException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, resource, field, value, message);
    }

    @Override
    public DataCreateException get() {
        return new DataCreateException();
    }
}
