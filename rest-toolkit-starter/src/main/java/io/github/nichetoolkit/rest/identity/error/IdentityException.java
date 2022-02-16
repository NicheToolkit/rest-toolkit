package io.github.nichetoolkit.rest.identity.error;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>IdentityException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityException extends RestException {
    public IdentityException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    public IdentityException(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public IdentityException(String message) {
        super(message);
    }

    public IdentityException(Integer status) {
        super(status);
    }

    public IdentityException(RestStatus status) {
        super(status);
    }

    public IdentityException(Integer status, String message) {
        super(status, message);
    }

    public IdentityException(Integer status, RestStatus restStatus) {
        super(status, restStatus);
    }

    public IdentityException(String message, RestStatus status) {
        super(message, status);
    }

    @Override
    public IdentityException get() {
        return new IdentityException();
    }
}
