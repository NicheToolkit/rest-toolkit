package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>InstanceLackError</code>
 * <p>The type instance lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class InstanceLackError extends RestError {

    /**
     * <code>InstanceLackError</code>
     * Instantiates a new instance lack error.
     */
    public InstanceLackError() {
        super(RestErrorStatus.INSTANCE_LACK_ERROR);
    }

    /**
     * <code>InstanceLackError</code>
     * Instantiates a new instance lack error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public InstanceLackError(Throwable cause) {
        super(RestErrorStatus.INSTANCE_LACK_ERROR, cause);
    }

    /**
     * <code>InstanceLackError</code>
     * Instantiates a new instance lack error.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public InstanceLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>InstanceLackError</code>
     * Instantiates a new instance lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public InstanceLackError(String error) {
        super(error, RestErrorStatus.INSTANCE_LACK_ERROR);
    }

    /**
     * <code>InstanceLackError</code>
     * Instantiates a new instance lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public InstanceLackError(String error, Throwable cause) {
        super(RestErrorStatus.INSTANCE_LACK_ERROR, error, cause);
    }

    /**
     * <code>InstanceLackError</code>
     * Instantiates a new instance lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public InstanceLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>InstanceLackError</code>
     * Instantiates a new instance lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public InstanceLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public InstanceLackError get() {
        return new InstanceLackError();
    }
}
