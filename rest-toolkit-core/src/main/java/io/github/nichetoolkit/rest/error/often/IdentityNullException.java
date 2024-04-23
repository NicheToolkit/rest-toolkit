package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IdentityErrorException;

/**
 * <p>IdentityNullException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityNullException extends IdentityErrorException {
    public IdentityNullException() {
        super(RestErrorStatus.IDENTITY_IS_NULL);
    }

    public IdentityNullException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public IdentityNullException(String message) {
        super(RestErrorStatus.IDENTITY_IS_NULL, message);
    }

    public IdentityNullException(String field, String message) {
        super(RestErrorStatus.IDENTITY_IS_NULL, "identity", field, message);
    }

    @Override
    public IdentityNullException get() {
        return new IdentityNullException();
    }
}
