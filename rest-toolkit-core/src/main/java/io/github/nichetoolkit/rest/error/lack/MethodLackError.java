package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>MethodLackError</code>
 * <p>The type method lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class MethodLackError extends RestError {

    /**
     * <code>MethodLackError</code>
     * <p>Instantiates a new method lack error.</p>
     */
    public MethodLackError() {
        super(RestErrorStatus.METHOD_LACK_ERROR);
    }

    /**
     * <code>MethodLackError</code>
     * <p>Instantiates a new method lack error.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public MethodLackError(Throwable cause) {
        super(RestErrorStatus.METHOD_LACK_ERROR, cause);
    }

    /**
     * <code>MethodLackError</code>
     * <p>Instantiates a new method lack error.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public MethodLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>MethodLackError</code>
     * <p>Instantiates a new method lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public MethodLackError(String error) {
        super(error, RestErrorStatus.METHOD_LACK_ERROR);
    }

    /**
     * <code>MethodLackError</code>
     * <p>Instantiates a new method lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public MethodLackError(String error, Throwable cause) {
        super(RestErrorStatus.METHOD_LACK_ERROR, error, cause);
    }

    /**
     * <code>MethodLackError</code>
     * <p>Instantiates a new method lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public MethodLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>MethodLackError</code>
     * <p>Instantiates a new method lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public MethodLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public MethodLackError get() {
        return new MethodLackError();
    }
}
