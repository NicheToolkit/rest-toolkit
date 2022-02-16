package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FieldErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FieldErrorException extends RestErrorException {
    public FieldErrorException() {
        super(RestErrorStatus.FIELD_ERROR);
    }

    public FieldErrorException(RestErrorStatus status) {
        super(status);
    }

    public FieldErrorException(String error) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(RestErrorStatus.FIELD_ERROR, error));
    }

    public FieldErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public FieldErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public FieldErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    public FieldErrorException(String filed, String error) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(filed, RestErrorStatus.FIELD_ERROR, error));
    }

    public FieldErrorException(String filed, Object value, String error) {
        super(RestErrorStatus.FIELD_ERROR, RestError.error(filed, value, RestErrorStatus.FIELD_ERROR, error));
    }

    public FieldErrorException(String filed, RestStatus status) {
        super(status, RestError.error(filed, status));
    }

    public FieldErrorException(String filed, Object value, RestStatus status) {
        super(status, RestError.error(filed, value, status));
    }

    @Override
    public FieldErrorException get() {
        return new FieldErrorException();
    }
}
