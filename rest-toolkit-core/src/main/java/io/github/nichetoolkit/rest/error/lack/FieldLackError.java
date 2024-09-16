package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>AccessibleLackError</code>
 * <p>The type accessible lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see RestError
 * @since Jdk1.8
 */
public class FieldLackError extends RestError {

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     */
    public FieldLackError() {
        super(RestErrorStatus.FIELD_LACK_ERROR);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param cause {@link Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see Throwable
     */
    public FieldLackError(Throwable cause) {
        super(RestErrorStatus.FIELD_LACK_ERROR, cause);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param supplier {@link Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see Supplier
     */
    public FieldLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param error {@link String} <p>the error parameter is <code>String</code> type.</p>
     * @see String
     */
    public FieldLackError(String error) {
        super(error, RestErrorStatus.FIELD_LACK_ERROR);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param error {@link String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see String
     * @see Throwable
     */
    public FieldLackError(String error, Throwable cause) {
        super(RestErrorStatus.FIELD_LACK_ERROR, error, cause);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param status {@link RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see RestStatus
     */
    public FieldLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>AccessibleLackError</code>
     * Instantiates a new accessible lack error.
     * @param status {@link RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see RestStatus
     * @see Throwable
     */
    public FieldLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public FieldLackError get() {
        return new FieldLackError();
    }
}
