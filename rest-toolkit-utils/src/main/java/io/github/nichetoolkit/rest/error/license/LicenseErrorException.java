package io.github.nichetoolkit.rest.error.license;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>LicenseErrorException</code>
 * <p>The license error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class LicenseErrorException extends RestErrorException {

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     */
    public LicenseErrorException() {
        super(LicenseErrorStatus.LICENSE_ERROR);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.error.license.LicenseErrorStatus} <p>The status parameter is <code>LicenseErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.error.license.LicenseErrorStatus
     */
    public LicenseErrorException(LicenseErrorStatus status) {
        super(status);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LicenseErrorException(String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(LicenseErrorStatus.LICENSE_ERROR, error));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public LicenseErrorException(String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(LicenseErrorStatus.LICENSE_ERROR, error, cause), cause);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public LicenseErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public LicenseErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public LicenseErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public LicenseErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }


    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public LicenseErrorException(RestStatus status, String resource) {
        super(status, RestError.error(resource, status));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public LicenseErrorException(RestStatus status, String resource, Throwable cause) {
        super(status, RestError.error(resource, status, cause), cause);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public LicenseErrorException(RestStatus status, String resource, String filed) {
        super(status, RestError.error(resource, filed, status));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public LicenseErrorException(RestStatus status, String resource, String filed, Throwable cause) {
        super(status, RestError.error(resource, filed, status, cause), cause);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public LicenseErrorException(RestStatus status, String resource, String filed, Object value) {
        super(status, RestError.error(resource, filed, value, status));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public LicenseErrorException(RestStatus status, String resource, String filed, Object value, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, cause), cause);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LicenseErrorException(String resource, String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, LicenseErrorStatus.LICENSE_ERROR, error));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public LicenseErrorException(String resource, String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, LicenseErrorStatus.LICENSE_ERROR, error, cause), cause);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public LicenseErrorException(String resource, String filed, String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, LicenseErrorStatus.LICENSE_ERROR, error));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public LicenseErrorException(String resource, String filed, String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, LicenseErrorStatus.LICENSE_ERROR, error, cause), cause);
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public LicenseErrorException(String resource, String filed, Object value, String error) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, value, LicenseErrorStatus.LICENSE_ERROR, error));
    }

    /**
     * <code>LicenseErrorException</code>
     * <p>Instantiates a new license error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public LicenseErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(LicenseErrorStatus.LICENSE_ERROR, RestError.error(resource, filed, value, LicenseErrorStatus.LICENSE_ERROR, error, cause));
    }

    @Override
    public LicenseErrorException get() {
        return new LicenseErrorException();
    }
}
