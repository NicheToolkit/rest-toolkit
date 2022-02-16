package io.github.nichetoolkit.rest.identity.error;


import io.github.nichetoolkit.rest.identity.IdentityErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>IdentityWorkerError</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class IdentityWorkerError extends RestError {

    public IdentityWorkerError() {
        super(IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    public IdentityWorkerError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public IdentityWorkerError(String error) {
        super(error,IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    public IdentityWorkerError(RestStatus status) {
        super(status);
    }


    @Override
    public IdentityWorkerError get() {
        return new IdentityWorkerError();
    }
}
