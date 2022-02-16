package io.github.nichetoolkit.rest.identity.error;

import io.github.nichetoolkit.rest.identity.IdentityErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>IdentityWorkerException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityWorkerException extends IdentityException {

    public IdentityWorkerException() {
        super(IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    public IdentityWorkerException(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public IdentityWorkerException(String error) {
        super(error,IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    public IdentityWorkerException(RestStatus status) {
        super(status);
    }

    @Override
    public IdentityWorkerException get() {
        return new IdentityWorkerException();
    }
}
