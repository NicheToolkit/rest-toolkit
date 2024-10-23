package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>InstanceLackError</code>
 * <p>The instance lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class InstanceLackError extends RestError {

    /**
     * <code>InstanceLackError</code>
     * <p>Instantiates a new instance lack error.</p>
     */
    public InstanceLackError() {
        super(RestErrorStatus.INSTANCE_LACK_ERROR);
    }

    /**
     * <code>InstanceLackError</code>
     * <p>Instantiates a new instance lack error.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public InstanceLackError(Throwable cause) {
        super(RestErrorStatus.INSTANCE_LACK_ERROR, cause);
    }

    /**
     * <code>InstanceLackError</code>
     * <p>Instantiates a new instance lack error.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public InstanceLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>InstanceLackError</code>
     * <p>Instantiates a new instance lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public InstanceLackError(String error) {
        super(error, RestErrorStatus.INSTANCE_LACK_ERROR);
    }

    /**
     * <code>InstanceLackError</code>
     * <p>Instantiates a new instance lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public InstanceLackError(String error, Throwable cause) {
        super(RestErrorStatus.INSTANCE_LACK_ERROR, error, cause);
    }

    /**
     * <code>InstanceLackError</code>
     * <p>Instantiates a new instance lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public InstanceLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>InstanceLackError</code>
     * <p>Instantiates a new instance lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
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
