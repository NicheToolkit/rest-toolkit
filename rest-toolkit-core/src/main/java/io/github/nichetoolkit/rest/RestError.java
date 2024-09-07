package io.github.nichetoolkit.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Supplier;

/**
 * <code>RestError</code>
 * <p>The type rest error class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.DefaultError
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see java.util.function.Supplier
 * @see lombok.Data
 * @see lombok.EqualsAndHashCode
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("SameNameButDifferent")
public class RestError extends DefaultError implements RestStatus, Supplier<RestError> {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>the <code>name</code> field.</p>
     * @see java.lang.String
     */
    private String name;
    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>the <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer status;

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     */
    public RestError() {
        super(RestErrorStatus.UNKNOWN_ERROR.getMessage());
        this.name = RestErrorStatus.UNKNOWN_ERROR.name();
        this.status = RestErrorStatus.UNKNOWN_ERROR.getStatus();
    }

    public RestError(Supplier<RestStatus> supplier) {
        super(supplier.get().getMessage());
        this.name = supplier.get().name();
        this.status = supplier.get().getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param exception {@link java.util.function.Supplier} <p>the supplier parameter is <code>Supplier</code> type.</p>
     * @see java.util.function.Supplier
     */
    public RestError(RestException exception) {
        super(exception.getMessage());
        this.name = exception.name();
        this.status = exception.getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestError(RestStatus status) {
        super(status.getMessage());
        this.name = status.name();
        this.status = status.getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RestError(String message) {
        super(message);
        this.name = RestErrorStatus.UNKNOWN_ERROR.name();
        this.status = RestErrorStatus.UNKNOWN_ERROR.getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public RestError(Integer status) {
        super(RestErrorStatus.UNKNOWN_ERROR.getMessage());
        this.name = RestErrorStatus.UNKNOWN_ERROR.name();
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public RestError(Throwable cause) {
        super(cause.getMessage(), cause);
        this.name = cause.getClass().getName();
        this.status = RestErrorStatus.UNKNOWN_ERROR.getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public RestError(Integer status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestError(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.name = restStatus.name();
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public RestError(String message, RestStatus status) {
        super(message);
        this.name = status.name();
        this.status = status.getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param name    {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param domain  {@link java.lang.Integer} <p>the domain parameter is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public RestError(Integer status, String message, String name, Integer domain) {
        super(message, domain);
        this.name = name;
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param name    {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public RestError(Integer status, String message, String name) {
        super(message);
        this.name = name;
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public RestError(RestStatus status, Throwable cause) {
        super(status.getMessage(), cause);
        this.name = status.name();
        this.status = status.getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestError(RestStatus status, String message, Throwable cause) {
        super(message, cause);
        this.name = status.name();
        this.status = status.getStatus();
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param name    {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestError(Integer status, String message, String name, Throwable cause) {
        super(message, cause);
        this.name = name;
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status             {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param name               {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestError(Integer status, String message, String name, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.name = name;
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param name    {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param domain  {@link java.lang.Integer} <p>the domain parameter is <code>Integer</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestError(Integer status, String message, String name, Integer domain, Throwable cause) {
        super(message, domain, cause);
        this.name = name;
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @param name               {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param domain             {@link java.lang.Integer} <p>the domain parameter is <code>Integer</code> type.</p>
     * @param status             {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     * @see java.lang.Integer
     */
    public RestError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String name, Integer domain, Integer status) {
        super(message, cause, enableSuppression, writableStackTrace, name, domain);
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestError(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param status             {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message            {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause              {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @param enableSuppression  boolean <p>the enable suppression parameter is <code>boolean</code> type.</p>
     * @param writableStackTrace boolean <p>the writable stack trace parameter is <code>boolean</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public RestError(Integer status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    /**
     * <code>RestError</code>
     * Instantiates a new rest error.
     * @param builder {@link io.github.nichetoolkit.rest.RestError.Builder} <p>the builder parameter is <code>Builder</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestError.Builder
     */
    public RestError(RestError.Builder builder) {
        super(builder);
        this.name = builder.name;
        this.status = builder.status;
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError parser(RestStatus status) {
        if (status instanceof RestError) {
            return (RestError) status;
        } else {
            return RestError.error(status);
        }
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError parser(String message, RestStatus status) {
        if (status instanceof RestError) {
            return (RestError) status;
        } else {
            return RestError.error(message, status);
        }
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError parser(Integer status, RestStatus restStatus) {
        if (restStatus instanceof RestError) {
            return (RestError) restStatus;
        } else {
            return RestError.error(status, restStatus);
        }
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message    {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError parser(Integer status, String message, RestStatus restStatus) {
        if (restStatus instanceof RestError) {
            return (RestError) restStatus;
        } else {
            return RestError.error(status, message);
        }
    }


    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Throwable
     */
    public static RestError parser(Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error();
        }
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public static RestError parser(String message, Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error(message);
        }
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static RestError parser(Integer status, Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error(status, cause.getMessage());
        }
    }

    /**
     * <code>parser</code>
     * <p>the method.</p>
     * @param status  {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public static RestError parser(Integer status, String message, Throwable cause) {
        if (cause instanceof RestError) {
            return (RestError) cause;
        } else if (cause instanceof RestException) {
            return RestError.error((RestStatus) cause);
        } else {
            return RestError.error(status, message);
        }
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     */
    public static RestError error() {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).add(new RestErrorIssue(RestErrorStatus.UNKNOWN_ERROR)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Throwable
     */
    public static RestError error(Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).message(cause).add(new RestErrorIssue(RestErrorStatus.UNKNOWN_ERROR)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     */
    public static RestError error(String field) {
        return (new Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public static RestError error(String field, Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(cause).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     */
    public static RestError error(String field, String error) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public static RestError error(String field, String error, Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, RestErrorStatus.UNKNOWN_ERROR, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public static RestError error(String field, Object value, String error) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, value, RestErrorStatus.UNKNOWN_ERROR, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param error {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public static RestError error(String field, Object value, String error, Throwable cause) {
        return (new RestError.Builder(RestErrorStatus.UNKNOWN_ERROR)).resource(field).message(error).add(new RestErrorIssue(field, value, RestErrorStatus.UNKNOWN_ERROR, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(RestStatus status) {
        return (new RestError.Builder(status)).add(new RestErrorIssue(status)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(RestStatus status, Throwable cause) {
        return (new RestError.Builder(status)).message(cause).add(new RestErrorIssue(status)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String field, RestStatus status) {
        return (new RestError.Builder(status)).resource(field).add(new RestErrorIssue(field, status)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String field, RestStatus status, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(cause).add(new RestErrorIssue(field, status)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    public static RestError error(Integer status, String error) {
        return (new Builder()).status(status).message(error).add(new RestErrorIssue(status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public static RestError error(Integer status, String error, Throwable cause) {
        return (new Builder()).status(status).message(error).add(new RestErrorIssue(status, error)).cause(cause).build();
    }


    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(Integer status, RestStatus restStatus) {
        return (new Builder(restStatus)).status(status).add(new RestErrorIssue(status, restStatus)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.Integer
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(Integer status, RestStatus restStatus, Throwable cause) {
        return (new Builder(restStatus)).status(status).message(cause).add(new RestErrorIssue(status, restStatus)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public static RestError error(RestStatus status, String error) {
        return (new RestError.Builder(status)).message(error).add(new RestErrorIssue(status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public static RestError error(RestStatus status, String error, Throwable cause) {
        return (new RestError.Builder(status)).message(error).add(new RestErrorIssue(status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    public static RestError error(String field, Integer status, String error) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static RestError error(String field, Integer status, String error, Throwable cause) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Integer
     */
    public static RestError error(String field, Object value, Integer status, String error) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static RestError error(String field, Object value, Integer status, String error, Throwable cause) {
        return (new RestError.Builder()).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String field, RestStatus status, String error) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String field, RestStatus status, String error, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     */
    public static RestError error(String field, RestStatus restStatus, Integer status, String error) {
        return (new Builder(restStatus)).status(status).message(error).resource(field).add(new RestErrorIssue(field, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static RestError error(String field, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new Builder(restStatus)).status(status).message(error).resource(field).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String field, Object value, RestStatus status) {
        return (new RestError.Builder(status)).resource(field).add(new RestErrorIssue(field, value, status)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String field, Object value, RestStatus status, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(cause).add(new RestErrorIssue(field, value, status)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String field, Object value, RestStatus status, String error) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field  {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value  {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String field, Object value, RestStatus status, String error, Throwable cause) {
        return (new RestError.Builder(status)).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     */
    public static RestError error(String field, Object value, RestStatus restStatus, Integer status, String error) {
        return (new Builder(restStatus)).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static RestError error(String field, Object value, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new Builder(restStatus)).status(status).resource(field).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String resource, String field, RestStatus restStatus) {
        return (new RestError.Builder(restStatus)).resource(resource).add(new RestErrorIssue(field, restStatus)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String resource, String field, RestStatus restStatus, Throwable cause) {
        return (new RestError.Builder(restStatus)).resource(resource).message(cause).add(new RestErrorIssue(field, restStatus)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String resource, String field, RestStatus restStatus, String error) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, restStatus, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String resource, String field, RestStatus restStatus, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, restStatus, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String resource, String field, Object value, RestStatus restStatus) {
        return (new RestError.Builder(restStatus)).resource(resource).add(new RestErrorIssue(field, value, restStatus)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String resource, String field, Object value, RestStatus restStatus, Throwable cause) {
        return (new Builder(restStatus)).resource(resource).message(cause).add(new RestErrorIssue(field, value, restStatus)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public static RestError error(String resource, String field, Object value, RestStatus restStatus, String error) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, value, restStatus, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public static RestError error(String resource, String field, Object value, RestStatus restStatus, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).resource(resource).message(error).add(new RestErrorIssue(field, value, restStatus, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     */
    public static RestError error(String resource, String field, RestStatus restStatus, Integer status, String error) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static RestError error(String resource, String field, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, status, error)).cause(cause).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     */
    public static RestError error(String resource, String field, Object value, RestStatus restStatus, Integer status, String error) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, value, status, error)).build();
    }

    /**
     * <code>error</code>
     * <p>the method.</p>
     * @param resource   {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field      {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value      {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param restStatus {@link io.github.nichetoolkit.rest.RestStatus} <p>the rest status parameter is <code>RestStatus</code> type.</p>
     * @param status     {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
     * @param error      {@link java.lang.String} <p>the error parameter is <code>String</code> type.</p>
     * @param cause      {@link java.lang.Throwable} <p>the cause parameter is <code>Throwable</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestError} <p>the return object is <code>RestError</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Integer
     * @see java.lang.Throwable
     */
    public static RestError error(String resource, String field, Object value, RestStatus restStatus, Integer status, String error, Throwable cause) {
        return (new RestError.Builder(restStatus)).status(status).resource(resource).message(error).add(new RestErrorIssue(field, value, status, error)).cause(cause).build();
    }

    @Override
    public String name() {
        return this.getName();
    }

    @Override
    public RestError get() {
        return new RestError();
    }


    /**
     * <code>Builder</code>
     * <p>The type builder class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.DefaultError.Builder
     * @since Jdk1.8
     */
    public static class Builder extends DefaultError.Builder {
        /**
         * <code>name</code>
         * {@link java.lang.String} <p>the <code>name</code> field.</p>
         * @see java.lang.String
         */
        protected String name;
        /**
         * <code>status</code>
         * {@link java.lang.Integer} <p>the <code>status</code> field.</p>
         * @see java.lang.Integer
         */
        protected Integer status;

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         */
        public Builder() {
            super();
        }

        /**
         * <code>Builder</code>
         * Instantiates a new builder.
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        private Builder(RestStatus status) {
            super(status);
            this.name = status.name();
            this.status = status.getStatus();
        }

        /**
         * <code>name</code>
         * <p>the method.</p>
         * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.RestError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.String
         */
        public RestError.Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * <code>name</code>
         * <p>the method.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.RestError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public RestError.Builder name(RestStatus status) {
            this.name = status.name();
            return this;
        }

        /**
         * <code>status</code>
         * <p>the method.</p>
         * @param status {@link java.lang.Integer} <p>the status parameter is <code>Integer</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.RestError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see java.lang.Integer
         */
        public RestError.Builder status(Integer status) {
            this.status = status;
            return this;
        }

        /**
         * <code>status</code>
         * <p>the method.</p>
         * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.RestError.Builder} <p>the return object is <code>Builder</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestStatus
         */
        public RestError.Builder status(RestStatus status) {
            this.status = status.getStatus();
            return this;
        }

        @Override
        public RestError.Builder message(String message) {
            super.message(message);
            return this;
        }

        @Override
        public RestError.Builder message(RestStatus status) {
            super.message(status.getMessage());
            return this;
        }

        @Override
        public RestError.Builder message(Throwable cause) {
            super.message(cause);
            return this;
        }

        @Override
        public RestError.Builder debug(String debug) {
            super.debug(debug);
            return this;
        }

        @Override
        public RestError.Builder resource(String resource) {
            super.resource(resource);
            return this;
        }

        @Override
        public RestError.Builder add(RestErrorIssue issue) {
            super.add(issue);
            return this;
        }

        @Override
        public RestError.Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        @Override
        public RestError build() {
            return new RestError(this);
        }
    }
}
