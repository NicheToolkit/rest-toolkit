package io.github.nichetoolkit.rest.identity.error;

import io.github.nichetoolkit.rest.identity.IdentityErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>IdentityWorkerError</code>
 * <p>The type identity worker error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class IdentityWorkerError extends RestError {

    /**
     * <code>IdentityWorkerError</code>
     * <p>Instantiates a new identity worker error.</p>
     */
    public IdentityWorkerError() {
        super(IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    /**
     * <code>IdentityWorkerError</code>
     * <p>Instantiates a new identity worker error.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public IdentityWorkerError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>IdentityWorkerError</code>
     * <p>Instantiates a new identity worker error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityWorkerError(String error) {
        super(error,IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    /**
     * <code>IdentityWorkerError</code>
     * <p>Instantiates a new identity worker error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IdentityWorkerError(RestStatus status) {
        super(status);
    }


    @Override
    public IdentityWorkerError get() {
        return new IdentityWorkerError();
    }
}
