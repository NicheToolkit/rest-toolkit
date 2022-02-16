package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>NameNullException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class NameNullException extends RestErrorException {
    public NameNullException() {
        super(RestErrorStatus.NAME_IS_NULL, RestError.error("Name", RestErrorStatus.NAME_IS_NULL));
    }

    public NameNullException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public NameNullException(String message) {
        super(RestErrorStatus.NAME_IS_NULL, RestError.error("Name", RestErrorStatus.NAME_IS_NULL,message));
    }

    public NameNullException(String field, String message) {
        super(RestErrorStatus.NAME_IS_NULL, RestError.error(field, RestErrorStatus.NAME_IS_NULL,message));
    }

    @Override
    public NameNullException get() {
        return new NameNullException();
    }
}
