package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <code>DefaultException</code>
 * <p>The type default exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.Exception
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see java.util.function.Supplier
 * @see java.io.Serializable
 * @see lombok.Data
 * @see lombok.EqualsAndHashCode
 * @see java.lang.SuppressWarnings
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("SameNameButDifferent")
@JsonIgnoreProperties(value = {"cause","stackTrace","localizedMessage","suppressed"})
public class DefaultException extends Exception implements RestStatus, Supplier<DefaultException>,Serializable {
    /**
     * <code>error</code>
     * {@link io.github.nichetoolkit.rest.RestError} <p>The <code>error</code> field.</p>
     * @see io.github.nichetoolkit.rest.RestError
     */
    private RestError error;
    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer status;

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     */
    protected DefaultException() {
        super(RestErrorStatus.MISTAKE.getMessage());
        this.error = RestError.error(RestErrorStatus.MISTAKE);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param supplier {@link java.util.function.Supplier} <p>The supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    protected DefaultException(Supplier<RestStatus> supplier) {
        super(supplier.get().getMessage());
        this.error = RestError.parser(supplier.get());
        this.status = supplier.get().getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    protected DefaultException(String message) {
        super(message);
        this.error = RestError.error(RestErrorStatus.MISTAKE);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    protected DefaultException(Integer status) {
        super(RestErrorStatus.MISTAKE.getMessage());
        this.error = RestError.error(RestErrorStatus.MISTAKE);
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    protected DefaultException(RestStatus status) {
        super(status.getMessage());
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param error {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     */
    protected DefaultException(RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = error.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    protected DefaultException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(cause);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    protected DefaultException(Integer status, String message) {
        super(message);
        this.error = RestError.error(status,message);
        this.status = status;
    }


    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status     {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    protected DefaultException(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestError
     */
    protected DefaultException(Integer status, RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(status,cause);
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    protected DefaultException(String message, RestStatus status) {
        super(message);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     */
    protected DefaultException(String message, RestError error) {
        super(message);
        this.error = error;
        this.status = error.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    protected DefaultException(RestStatus status, RestError error) {
        super(error.getMessage());
        this.status = status.getStatus();
        this.error = error;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    protected DefaultException(String message, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(message,cause);
    }


    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    protected DefaultException(RestStatus status, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     */
    protected DefaultException(Integer status, String message, RestError error) {
        super(message);
        this.status = status;
        this.error = error;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(status, message, cause);
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status     {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, RestStatus restStatus, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, RestError error, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = error;
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    protected DefaultException(String message, RestStatus status, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error   {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(String message, RestError error, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.status = error.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(RestStatus status, RestError error,  Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = error;
        this.status = status.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status     {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param error      {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, RestStatus restStatus, RestError error, Throwable cause) {
        super(cause.getMessage(), cause);
        this.status = status;
        this.error = error;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message            {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    protected DefaultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(message,cause);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }


    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    protected DefaultException(RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = error.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status             {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message            {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.error(status,message);
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status             {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param restStatus         {@link io.github.nichetoolkit.rest.RestStatus} <p>The rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, RestStatus restStatus, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status             {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(Integer status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = status;
    }


    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message            {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    protected DefaultException(String message, RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param message            {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(String message, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = error.getStatus();
    }

    /**
     * <code>DefaultException</code>
     * <p>Instantiates a new default exception.</p>
     * @param status             {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error              {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>The enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>The writable stack trace parameter is <code>boolean</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    protected DefaultException(RestStatus status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.status = status.getStatus();
        this.error = error;
    }

    /**
     * <code>buildResult</code>
     * <p>The result method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The result return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     */
    public final RestResult<?> buildResult() {
        return RestResult.defaultBuilder().status(this.status).message(getMessage()).data(this.error).build();
    }

    @Override
    public String name() {
        return Optional.ofNullable(this.error).map(RestError::getName).orElse(this.getClass().getSimpleName());
    }

    @Override
    public DefaultException get() {
        return new DefaultException();
    }
}
