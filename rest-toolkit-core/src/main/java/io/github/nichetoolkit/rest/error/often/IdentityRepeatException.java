package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IdentityErrorException;

/**
 * <p>IdentityRepeatException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityRepeatException extends IdentityErrorException {

    public IdentityRepeatException() {
        super(RestErrorStatus.IDENTITY_REPEATED);
    }

    public IdentityRepeatException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public IdentityRepeatException(String message) {
        super(RestErrorStatus.IDENTITY_REPEATED, message);
    }


    public IdentityRepeatException(String field, String message) {
        super(RestErrorStatus.IDENTITY_REPEATED, "identity", field, message);
    }

    public IdentityRepeatException(String field, Object value, String message) {
        super(RestErrorStatus.IDENTITY_REPEATED, "identity", field, value, message);
    }

    @Override
    public IdentityRepeatException get() {
        return new IdentityRepeatException();
    }
}
