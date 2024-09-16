package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>ClassLackError</code>
 * <p>The type class lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class ClassLackError extends RestError {

    /**
     * <code>ClassLackError</code>
     * Instantiates a new class lack error.
     */
    public ClassLackError() {
        super(RestErrorStatus.CLASS_LACK_ERROR);
    }

    /**
     * <code>ClassLackError</code>
     * Instantiates a new class lack error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ClassLackError(Throwable cause) {
        super(RestErrorStatus.CLASS_LACK_ERROR, cause);
    }

    /**
     * <code>ClassLackError</code>
     * Instantiates a new class lack error.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public ClassLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>ClassLackError</code>
     * Instantiates a new class lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ClassLackError(String error) {
        super(error, RestErrorStatus.CLASS_LACK_ERROR);
    }

    /**
     * <code>ClassLackError</code>
     * Instantiates a new class lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ClassLackError(String error, Throwable cause) {
        super(RestErrorStatus.CLASS_LACK_ERROR, error, cause);
    }

    /**
     * <code>ClassLackError</code>
     * Instantiates a new class lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ClassLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>ClassLackError</code>
     * Instantiates a new class lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public ClassLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public ClassLackError get() {
        return new ClassLackError();
    }
}