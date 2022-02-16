package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>DataErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataErrorException extends RestErrorException {

    public DataErrorException() {
        super(RestErrorStatus.DATA_ERROR);
    }

    public DataErrorException(RestErrorStatus status) {
        super(status);
    }

    public DataErrorException(String error) {
        super(RestErrorStatus.DATA_ERROR, RestError.error(RestErrorStatus.DATA_ERROR, error));
    }

    public DataErrorException(RestStatus status) {
        super(status,RestError.error(status));
    }

    public DataErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public DataErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    @Override
    public DataErrorException get() {
        return new DataErrorException();
    }

}
