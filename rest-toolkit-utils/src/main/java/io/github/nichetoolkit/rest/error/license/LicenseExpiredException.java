package io.github.nichetoolkit.rest.error.license;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>LicenseExpiredException</code>
 * <p>The license expired exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.license.LicenseErrorException
 * @since Jdk1.8
 */
public class LicenseExpiredException extends LicenseErrorException {
    /**
     * <code>LicenseExpiredException</code>
     * <p>Instantiates a new license expired exception.</p>
     */
    public LicenseExpiredException() {
        super(LicenseErrorStatus.LICENSE_EXPIRED_ERROR);
    }

    /**
     * <code>LicenseExpiredException</code>
     * <p>Instantiates a new license expired exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public LicenseExpiredException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>LicenseExpiredException</code>
     * <p>Instantiates a new license expired exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LicenseExpiredException(String message) {
        super(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, RestError.error(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, message));
    }

    /**
     * <code>LicenseExpiredException</code>
     * <p>Instantiates a new license expired exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LicenseExpiredException(String resource, String message) {
        super(LicenseErrorStatus.LICENSE_EXPIRED_ERROR, RestError.error(resource, LicenseErrorStatus.LICENSE_EXPIRED_ERROR, message));
    }

    @Override
    public LicenseExpiredException get() {
        return new LicenseExpiredException();
    }
}
