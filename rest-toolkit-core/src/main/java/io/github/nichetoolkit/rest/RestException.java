package io.github.nichetoolkit.rest;


import java.util.function.Supplier;

/**
 * <code>RestException</code>
 * <p>The type rest exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.DefaultException
 * @since Jdk1.8
 */
public class RestException extends DefaultException {

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     */
    public RestException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param supplier {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public RestException(Supplier<RestStatus> supplier) {
        super(supplier.get());
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param error {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     */
    public RestException(RestError error) {
        super((RestStatus)error);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestException(String message) {
        super(message);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public RestException(Integer status) {
        super(status);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestException(RestStatus status) {
        super(status);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public RestException(Throwable cause) {
        super(cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public RestException(Integer status, String message) {
        super(status, message);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestException(Integer status, RestStatus restStatus) {
        super(status, restStatus);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestError
     */
    public RestException(Integer status, RestError error) {
        super(status, error);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public RestException(Integer status, Throwable cause) {
        super(status, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestException(String message, RestStatus status) {
        super(message, status);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     */
    public RestException(String message, RestError error) {
        super(message, error);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public RestException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestException(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestException(Integer status, String message, Throwable cause) {
        super(status, message, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestException(Integer status, RestStatus restStatus, Throwable cause) {
        super(status, restStatus, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public RestException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestException(String message, RestStatus status, Throwable cause) {
        super(message, status, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public RestException(String message, RestError error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestException(RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status             {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestException(Integer status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status             {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus         {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestException(Integer status, RestStatus restStatus, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, restStatus, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestException(String message, RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, status, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestException</code>
     * Instantiates a new rest exception.
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>the error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public RestException(RestStatus status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, error, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public RestException get() {
        return new RestException();
    }

}
