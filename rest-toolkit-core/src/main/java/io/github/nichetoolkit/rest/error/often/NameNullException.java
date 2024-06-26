package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FieldErrorException;

/**
 * <p>NameNullException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class NameNullException extends FieldErrorException {
    public NameNullException() {
        super(RestErrorStatus.NAME_IS_NULL);
    }

    public NameNullException(RestStatus status) {
        super(status);
    }

    public NameNullException(String message) {
        super(RestErrorStatus.NAME_IS_NULL, message);
    }

    public NameNullException(String field, String message) {
        super(RestErrorStatus.NAME_IS_NULL,"name", field, message);
    }

    @Override
    public NameNullException get() {
        return new NameNullException();
    }
}
