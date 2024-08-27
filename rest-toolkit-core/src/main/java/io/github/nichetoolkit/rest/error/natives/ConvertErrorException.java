package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <code>ConvertErrorException</code>
 * <p>The type convert error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ConvertErrorException extends RestErrorException {

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     */
    public ConvertErrorException() {
        super(RestErrorStatus.CONVERT_ERROR);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public ConvertErrorException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ConvertErrorException(String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(RestErrorStatus.CONVERT_ERROR, error));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ConvertErrorException(String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(RestErrorStatus.CONVERT_ERROR, error, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ConvertErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public ConvertErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public ConvertErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public ConvertErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public ConvertErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ConvertErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public ConvertErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ConvertErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public ConvertErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ConvertErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ConvertErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public ConvertErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ConvertErrorException(String resource, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, RestErrorStatus.CONVERT_ERROR, error));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ConvertErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, RestErrorStatus.CONVERT_ERROR, error, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ConvertErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, RestErrorStatus.CONVERT_ERROR, error));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ConvertErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, RestErrorStatus.CONVERT_ERROR, error, cause), cause);
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ConvertErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONVERT_ERROR, error));
    }

    /**
     * <code>ConvertErrorException</code>
     * Instantiates a new convert error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public ConvertErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.CONVERT_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONVERT_ERROR, error, cause));
    }

    @Override
    public ConvertErrorException get() {
        return new ConvertErrorException();
    }
}
