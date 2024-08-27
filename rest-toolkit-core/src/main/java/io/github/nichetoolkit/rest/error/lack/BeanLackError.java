package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>BeanLackError</code>
 * <p>The type bean lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class BeanLackError extends RestError {

    /**
     * <code>BeanLackError</code>
     * Instantiates a new bean lack error.
     */
    public BeanLackError() {
        super(RestErrorStatus.BEAN_LACK_ERROR);
    }

    /**
     * <code>BeanLackError</code>
     * Instantiates a new bean lack error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public BeanLackError(Throwable cause) {
        super(RestErrorStatus.BEAN_LACK_ERROR, cause);
    }

    /**
     * <code>BeanLackError</code>
     * Instantiates a new bean lack error.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public BeanLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>BeanLackError</code>
     * Instantiates a new bean lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public BeanLackError(String error) {
        super(error, RestErrorStatus.BEAN_LACK_ERROR);
    }

    /**
     * <code>BeanLackError</code>
     * Instantiates a new bean lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public BeanLackError(String error, Throwable cause) {
        super(RestErrorStatus.BEAN_LACK_ERROR, error, cause);
    }

    /**
     * <code>BeanLackError</code>
     * Instantiates a new bean lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public BeanLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>BeanLackError</code>
     * Instantiates a new bean lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public BeanLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public BeanLackError get() {
        return new BeanLackError();
    }
}
