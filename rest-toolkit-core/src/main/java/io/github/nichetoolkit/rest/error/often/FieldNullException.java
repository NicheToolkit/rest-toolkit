package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FieldNullException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FieldNullException extends RestErrorException {

    public FieldNullException() {
        super(RestErrorStatus.FIELD_IS_NULL, RestError.error("Field", RestErrorStatus.FIELD_IS_NULL));
    }

    public FieldNullException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public FieldNullException(String message) {
        super(RestErrorStatus.FIELD_IS_NULL, RestError.error("Field", RestErrorStatus.FIELD_IS_NULL,message));
    }

    public FieldNullException(String field, String message) {
        super(RestErrorStatus.FIELD_IS_NULL, RestError.error(field, RestErrorStatus.FIELD_IS_NULL,message));
    }

    @Override
    public FieldNullException get() {
        return new FieldNullException();
    }
}
