package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>IdentityErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityErrorException extends RestErrorException {
    public IdentityErrorException() {
        super(RestErrorStatus.IDENTITY_ERROR);
    }

    public IdentityErrorException(RestErrorStatus status) {
        super(status);
    }

    public IdentityErrorException(String error) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(RestErrorStatus.IDENTITY_ERROR,error));
    }

    public IdentityErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public IdentityErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public IdentityErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    public IdentityErrorException(String field, Object value) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(field, value, RestErrorStatus.IDENTITY_ERROR));
    }

    public IdentityErrorException(String field, String error) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(field, RestErrorStatus.IDENTITY_ERROR, error));
    }

    public IdentityErrorException(String field, Object value, String error) {
        super(RestErrorStatus.IDENTITY_ERROR, RestError.error(field, value, RestErrorStatus.IDENTITY_ERROR, error));
    }

    public IdentityErrorException(String field, RestStatus status) {
        super(status, RestError.error(field, status));
    }

    public IdentityErrorException(String field, Object value, RestStatus status) {
        super(status, RestError.error(field, value, status));
    }

    public IdentityErrorException(String field, RestStatus status, String error) {
        super(status, RestError.error(field, status, error));
    }

    public IdentityErrorException(String field, Object value, RestStatus status, String error) {
        super(status, RestError.error(field, value, status, error));
    }


    @Override
    public IdentityErrorException get() {
        return new IdentityErrorException();
    }
}
