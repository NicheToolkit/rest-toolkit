package io.github.nichetoolkit.rest.error.license;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>LicenseLackError</code>
 * <p>The license lack error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestError
 * @since Jdk17
 */
public class LicenseLackError extends RestError {

    /**
     * <code>LicenseLackError</code>
     * <p>Instantiates a new license lack error.</p>
     */
    public LicenseLackError() {
        super(LicenseErrorStatus.LICENSE_LACK);
    }

    /**
     * <code>LicenseLackError</code>
     * <p>Instantiates a new license lack error.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public LicenseLackError(Throwable cause) {
        super(LicenseErrorStatus.LICENSE_LACK, cause);
    }

    /**
     * <code>LicenseLackError</code>
     * <p>Instantiates a new license lack error.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public LicenseLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    /**
     * <code>LicenseLackError</code>
     * <p>Instantiates a new license lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LicenseLackError(String error) {
        super(error, LicenseErrorStatus.LICENSE_LACK);
    }

    /**
     * <code>LicenseLackError</code>
     * <p>Instantiates a new license lack error.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public LicenseLackError(String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_LACK, error, cause);
    }

    /**
     * <code>LicenseLackError</code>
     * <p>Instantiates a new license lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public LicenseLackError(RestStatus status) {
        super(status);
    }

    /**
     * <code>LicenseLackError</code>
     * <p>Instantiates a new license lack error.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public LicenseLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public LicenseLackError get() {
        return new LicenseLackError();
    }
}
