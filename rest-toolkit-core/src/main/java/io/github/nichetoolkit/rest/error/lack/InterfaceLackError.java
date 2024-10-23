package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>InterfaceLackError</code>
 * <p>The interface lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class InterfaceLackError extends RestError {

    /**
     * <code>InterfaceLackError</code>
     * <p>Instantiates a new interface lack error.</p>
     */
    public InterfaceLackError() {
        super(RestErrorStatus.INTERFACE_LACK_ERROR);
    }

    /**
     * <code>InterfaceLackError</code>
     * <p>Instantiates a new interface lack error.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public InterfaceLackError(Throwable cause) {
        super(RestErrorStatus.INTERFACE_LACK_ERROR, cause);
    }

    /**
     * <code>InterfaceLackError</code>
     * <p>Instantiates a new interface lack error.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public InterfaceLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>InterfaceLackError</code>
     * <p>Instantiates a new interface lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public InterfaceLackError(String error) {
        super(error, RestErrorStatus.INTERFACE_LACK_ERROR);
    }

    /**
     * <code>InterfaceLackError</code>
     * <p>Instantiates a new interface lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public InterfaceLackError(String error, Throwable cause) {
        super(RestErrorStatus.INTERFACE_LACK_ERROR, error, cause);
    }

    /**
     * <code>InterfaceLackError</code>
     * <p>Instantiates a new interface lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public InterfaceLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>InterfaceLackError</code>
     * <p>Instantiates a new interface lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public InterfaceLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public InterfaceLackError get() {
        return new InterfaceLackError();
    }
}
