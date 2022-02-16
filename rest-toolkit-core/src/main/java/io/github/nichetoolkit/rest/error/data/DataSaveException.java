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
public class DataSaveException extends RestErrorException {
    public DataSaveException() {
        super(RestErrorStatus.DATA_SAVE_FAILED);
    }

    public DataSaveException(RestErrorStatus status) {
        super(status);
    }

    public DataSaveException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public DataSaveException(String resource) {
        super(RestErrorStatus.DATA_SAVE_FAILED, RestError.error(resource, RestErrorStatus.DATA_SAVE_FAILED));
    }

    public DataSaveException(String resource, String message) {
        super(RestErrorStatus.DATA_SAVE_FAILED, RestError.error(resource, RestErrorStatus.DATA_SAVE_FAILED, message));
    }

    @Override
    public DataSaveException get() {
        return new DataSaveException();
    }
}
