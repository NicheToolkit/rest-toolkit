package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <code>AuthErrorException</code>
 * <p>The type auth error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class AuthErrorException extends RestErrorException {

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     */
    public AuthErrorException() {
        super(RestErrorStatus.AUTH_ERROR);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public AuthErrorException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public AuthErrorException(String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(RestErrorStatus.AUTH_ERROR, error));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public AuthErrorException(String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(RestErrorStatus.AUTH_ERROR, error, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public AuthErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public AuthErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public AuthErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public AuthErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public AuthErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public AuthErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public AuthErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public AuthErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public AuthErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public AuthErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public AuthErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
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
    public AuthErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public AuthErrorException(String resource, String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, RestErrorStatus.AUTH_ERROR, error));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public AuthErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, RestErrorStatus.AUTH_ERROR, error, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public AuthErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, RestErrorStatus.AUTH_ERROR, error));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public AuthErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, RestErrorStatus.AUTH_ERROR, error, cause), cause);
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public AuthErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, value, RestErrorStatus.AUTH_ERROR, error));
    }

    /**
     * <code>AuthErrorException</code>
     * Instantiates a new auth error exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>the filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public AuthErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.AUTH_ERROR, RestError.error(resource, filed, value, RestErrorStatus.AUTH_ERROR, error, cause));
    }

    @Override
    public AuthErrorException get() {
        return new AuthErrorException();
    }
}
