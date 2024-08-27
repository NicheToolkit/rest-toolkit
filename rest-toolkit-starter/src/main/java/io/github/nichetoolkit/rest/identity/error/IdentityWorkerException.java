package io.github.nichetoolkit.rest.identity.error;

import io.github.nichetoolkit.rest.identity.IdentityErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>IdentityWorkerException</code>
 * <p>The type identity worker exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.identity.error.IdentityException
 * @since Jdk1.8
 */
public class IdentityWorkerException extends IdentityException {

    /**
     * <code>IdentityWorkerException</code>
     * Instantiates a new identity worker exception.
     */
    public IdentityWorkerException() {
        super(IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    /**
     * <code>IdentityWorkerException</code>
     * Instantiates a new identity worker exception.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public IdentityWorkerException(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>IdentityWorkerException</code>
     * Instantiates a new identity worker exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityWorkerException(String error) {
        super(error,IdentityErrorStatus.IDENTITY_WORKER_ERROR);
    }

    /**
     * <code>IdentityWorkerException</code>
     * Instantiates a new identity worker exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IdentityWorkerException(RestStatus status) {
        super(status);
    }

    @Override
    public IdentityWorkerException get() {
        return new IdentityWorkerException();
    }
}
