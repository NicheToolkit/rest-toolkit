package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>HttpConfigError</code>
 * <p>The type http config error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class HttpConfigError extends RestError {

    /**
     * <code>HttpConfigError</code>
     * Instantiates a new http config error.
     */
    public HttpConfigError() {
        super(RestErrorStatus.HTTP_CONFIG_ERROR);
    }

    /**
     * <code>HttpConfigError</code>
     * Instantiates a new http config error.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public HttpConfigError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>HttpConfigError</code>
     * Instantiates a new http config error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public HttpConfigError(String error) {
        super(error,RestErrorStatus.HTTP_CONFIG_ERROR);
    }

    /**
     * <code>HttpConfigError</code>
     * Instantiates a new http config error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public HttpConfigError( Throwable cause) {
        super(cause);
    }

    /**
     * <code>HttpConfigError</code>
     * Instantiates a new http config error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public HttpConfigError(String error, Throwable cause) {
        super(error,cause);
    }

    /**
     * <code>HttpConfigError</code>
     * Instantiates a new http config error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public HttpConfigError(RestStatus status) {
        super(status);
    }


    @Override
    public HttpConfigError get() {
        return new HttpConfigError();
    }
}
