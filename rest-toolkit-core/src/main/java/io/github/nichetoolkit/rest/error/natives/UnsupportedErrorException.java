package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <code>UnsupportedErrorException</code>
 * <p>The unsupported error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class UnsupportedErrorException extends RestErrorException {

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     */
    public UnsupportedErrorException() {
        super(RestErrorStatus.UNSUPPORTED);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public UnsupportedErrorException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UnsupportedErrorException(String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(RestErrorStatus.UNSUPPORTED, error));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(RestErrorStatus.UNSUPPORTED, error, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public UnsupportedErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public UnsupportedErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public UnsupportedErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public UnsupportedErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public UnsupportedErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public UnsupportedErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UnsupportedErrorException(String resource, String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, RestErrorStatus.UNSUPPORTED, error));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, RestErrorStatus.UNSUPPORTED, error, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public UnsupportedErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, RestErrorStatus.UNSUPPORTED, error));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, RestErrorStatus.UNSUPPORTED, error, cause), cause);
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public UnsupportedErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, value, RestErrorStatus.UNSUPPORTED, error));
    }

    /**
     * <code>UnsupportedErrorException</code>
     * <p>Instantiates a new unsupported error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public UnsupportedErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(resource, filed, value, RestErrorStatus.UNSUPPORTED, error, cause));
    }

    @Override
    public UnsupportedErrorException get() {
        return new UnsupportedErrorException();
    }
}
