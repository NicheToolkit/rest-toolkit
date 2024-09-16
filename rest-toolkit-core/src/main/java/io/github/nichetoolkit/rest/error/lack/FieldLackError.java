package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>FieldLackError</code>
 * <p>The type field lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class FieldLackError extends RestError {

    /**
     * <code>FieldLackError</code>
     * Instantiates a new field lack error.
     */
    public FieldLackError() {
        super(RestErrorStatus.FIELD_LACK_ERROR);
    }

    /**
     * <code>FieldLackError</code>
     * Instantiates a new field lack error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public FieldLackError(Throwable cause) {
        super(RestErrorStatus.FIELD_LACK_ERROR, cause);
    }

    /**
     * <code>FieldLackError</code>
     * Instantiates a new field lack error.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public FieldLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>FieldLackError</code>
     * Instantiates a new field lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FieldLackError(String error) {
        super(error, RestErrorStatus.FIELD_LACK_ERROR);
    }

    /**
     * <code>FieldLackError</code>
     * Instantiates a new field lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public FieldLackError(String error, Throwable cause) {
        super(RestErrorStatus.FIELD_LACK_ERROR, error, cause);
    }

    /**
     * <code>FieldLackError</code>
     * Instantiates a new field lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public FieldLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>FieldLackError</code>
     * Instantiates a new field lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public FieldLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public FieldLackError get() {
        return new FieldLackError();
    }
}
