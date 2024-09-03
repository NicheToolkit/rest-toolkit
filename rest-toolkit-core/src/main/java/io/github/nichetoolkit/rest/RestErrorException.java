package io.github.nichetoolkit.rest;

import java.util.function.Supplier;

/**
 * <code>RestErrorException</code>
 * <p>The type rest error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestException
 * @since Jdk1.8
 */
public class RestErrorException extends RestException {

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     */
    public RestErrorException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public RestErrorException(Supplier<RestStatus> supplier) {
        super(supplier.get());
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorException(RestStatus status) {
        super(status);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public RestErrorException(RestErrorStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param error {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     */
    public RestErrorException(RestError error) {
        super((RestStatus) error);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     */
    public RestErrorException(Integer status, RestError error) {
        super(status, error);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see java.lang.String
     */
    public RestErrorException(String message, RestError error) {
        super(message, error);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param error {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public RestErrorException(RestError error, Throwable cause) {
        super(error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public RestErrorException(Integer status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestErrorException(String message, RestError error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Throwable
     */
    public RestErrorException(RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status             {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public RestErrorException(Integer status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, error, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestErrorException(String message, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, error, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestErrorException</code>
     * Instantiates a new rest error exception.
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestErrorException(RestStatus status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, error, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public RestErrorException get() {
        return new RestErrorException();
    }
}
