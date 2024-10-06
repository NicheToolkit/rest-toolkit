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
     * <p>Instantiates a new rest error exception.</p>
     */
    public RestErrorException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public RestErrorException(Supplier<RestStatus> supplier) {
        super(supplier.get());
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorException(RestStatus status) {
        super(status);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public RestErrorException(RestErrorStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param error {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     */
    public RestErrorException(RestError error) {
        super((RestStatus) error);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     */
    public RestErrorException(Integer status, RestError error) {
        super(status, error);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.String
     */
    public RestErrorException(String message, RestError error) {
        super(message, error);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param error {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public RestErrorException(RestError error, Throwable cause) {
        super(error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public RestErrorException(Integer status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestErrorException(String message, RestError error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Throwable
     */
    public RestErrorException(RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status             {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public RestErrorException(Integer status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, error, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param message            {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestErrorException(String message, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, error, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <code>RestErrorException</code>
     * <p>Instantiates a new rest error exception.</p>
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
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
