package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>ConfigureLackError</code>
 * <p>The type configure lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk1.8
 */
public class ConfigureLackError extends RestError {

    /**
     * <code>ConfigureLackError</code>
     * Instantiates a new configure lack error.
     */
    public ConfigureLackError() {
        super(RestErrorStatus.CONFIGURE_LACK_ERROR);
    }

    /**
     * <code>ConfigureLackError</code>
     * Instantiates a new configure lack error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ConfigureLackError(Throwable cause) {
        super(RestErrorStatus.CONFIGURE_LACK_ERROR, cause);
    }

    /**
     * <code>ConfigureLackError</code>
     * Instantiates a new configure lack error.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public ConfigureLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>ConfigureLackError</code>
     * Instantiates a new configure lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ConfigureLackError(String error) {
        super(error, RestErrorStatus.CONFIGURE_LACK_ERROR);
    }

    /**
     * <code>ConfigureLackError</code>
     * Instantiates a new configure lack error.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ConfigureLackError(String error, Throwable cause) {
        super(RestErrorStatus.CONFIGURE_LACK_ERROR, error, cause);
    }

    /**
     * <code>ConfigureLackError</code>
     * Instantiates a new configure lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ConfigureLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>ConfigureLackError</code>
     * Instantiates a new configure lack error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public ConfigureLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public ConfigureLackError get() {
        return new ConfigureLackError();
    }
}
