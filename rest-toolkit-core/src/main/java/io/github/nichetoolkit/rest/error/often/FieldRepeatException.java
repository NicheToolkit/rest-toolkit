package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FieldRepeatException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FieldRepeatException extends RestErrorException {
    public FieldRepeatException() {
        super(RestErrorStatus.FIELD_REPEATED);
    }

    public FieldRepeatException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public FieldRepeatException(String message) {
        super(RestErrorStatus.FIELD_REPEATED, RestError.error(RestErrorStatus.FIELD_REPEATED, message));
    }

    public FieldRepeatException(String field, String message) {
        super(RestErrorStatus.FIELD_REPEATED, RestError.error(field, RestErrorStatus.FIELD_REPEATED, message));
    }

    public FieldRepeatException(String field, Object value, String message) {
        super(RestErrorStatus.FIELD_REPEATED, RestError.error(field, value, RestErrorStatus.FIELD_REPEATED, message));
    }

    public FieldRepeatException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.FIELD_REPEATED, RestError.error(resource, field, value, RestErrorStatus.FIELD_REPEATED, message));
    }

    @Override
    public FieldRepeatException get() {
        return new FieldRepeatException();
    }
}
