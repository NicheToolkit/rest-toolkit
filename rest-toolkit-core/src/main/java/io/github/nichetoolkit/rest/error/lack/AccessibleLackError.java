package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>AccessibleLackError</code>
 * <p>The type accessible lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class AccessibleLackError extends RestError {

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     */
    public AccessibleLackError() {
        super(RestErrorStatus.ACCESSIBLE_LACK_ERROR);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public AccessibleLackError(Throwable cause) {
        super(RestErrorStatus.ACCESSIBLE_LACK_ERROR, cause);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public AccessibleLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public AccessibleLackError(String error) {
        super(error, RestErrorStatus.ACCESSIBLE_LACK_ERROR);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public AccessibleLackError(String error, Throwable cause) {
        super(RestErrorStatus.ACCESSIBLE_LACK_ERROR, error, cause);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public AccessibleLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public AccessibleLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public AccessibleLackError get() {
        return new AccessibleLackError();
    }
}
