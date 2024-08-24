package io.github.nichetoolkit.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <p>DefaultException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("SameNameButDifferent")
@JsonIgnoreProperties(value = {"cause","stackTrace","localizedMessage","suppressed"})
class DefaultException extends Exception implements RestStatus, Supplier<DefaultException>,Serializable {
    private RestError error;
    private Integer status;

    protected DefaultException() {
        super(RestErrorStatus.MISTAKE.getMessage());
        this.error = RestError.error(RestErrorStatus.MISTAKE);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }

    protected DefaultException(Supplier<RestStatus> supplier) {
        super(supplier.get().getMessage());
        this.error = RestError.parser(supplier.get());
        this.status = supplier.get().getStatus();
    }

    protected DefaultException(String message) {
        super(message);
        this.error = RestError.error(RestErrorStatus.MISTAKE);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }

    protected DefaultException(Integer status) {
        super(RestErrorStatus.MISTAKE.getMessage());
        this.error = RestError.error(RestErrorStatus.MISTAKE);
        this.status = status;
    }

    protected DefaultException(RestStatus status) {
        super(status.getMessage());
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    protected DefaultException(RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = error.getStatus();
    }

    protected DefaultException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(cause);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }

    protected DefaultException(Integer status, String message) {
        super(message);
        this.error = RestError.error(status,message);
        this.status = status;
    }


    protected DefaultException(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }

    protected DefaultException(Integer status, RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = status;
    }

    protected DefaultException(Integer status, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(status,cause);
        this.status = status;
    }

    protected DefaultException(String message, RestStatus status) {
        super(message);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    protected DefaultException(String message, RestError error) {
        super(message);
        this.error = error;
        this.status = error.getStatus();
    }

    protected DefaultException(RestStatus status, RestError error) {
        super(error.getMessage());
        this.status = status.getStatus();
        this.error = error;
    }

    protected DefaultException(String message, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(message,cause);
    }


    protected DefaultException(RestStatus status, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    protected DefaultException(Integer status, String message, RestError error) {
        super(message);
        this.status = status;
        this.error = error;
    }

    protected DefaultException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(status, message, cause);
        this.status = status;
    }

    protected DefaultException(Integer status, RestStatus restStatus, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }

    protected DefaultException(Integer status, RestError error, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = error;
        this.status = status;
    }

    protected DefaultException(String message, RestStatus status, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    protected DefaultException(String message, RestError error, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.status = error.getStatus();
    }

    protected DefaultException(RestStatus status, RestError error,  Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = error;
        this.status = status.getStatus();
    }

    protected DefaultException(Integer status, RestStatus restStatus, RestError error, Throwable cause) {
        super(cause.getMessage(), cause);
        this.status = status;
        this.error = error;
    }

    protected DefaultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(message,cause);
        this.status = RestErrorStatus.MISTAKE.getStatus();
    }


    protected DefaultException(RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    protected DefaultException(RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = error.getStatus();
    }

    protected DefaultException(Integer status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.error(status,message);
        this.status = status;
    }

    protected DefaultException(Integer status, RestStatus restStatus, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }
    protected DefaultException(Integer status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = status;
    }


    protected DefaultException(String message, RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    protected DefaultException(String message, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = error.getStatus();
    }

    protected DefaultException(RestStatus status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(cause.getMessage(), cause, enableSuppression, writableStackTrace);
        this.status = status.getStatus();
        this.error = error;
    }

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
