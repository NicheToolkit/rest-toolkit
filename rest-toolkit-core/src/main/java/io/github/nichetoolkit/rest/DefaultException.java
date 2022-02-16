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
@JsonIgnoreProperties(value = {"cause","stackTrace","localizedMessage","suppressed"})
class DefaultException extends Exception implements RestStatus, Supplier,Serializable {
    protected RestError error;
    protected Integer status;

    public DefaultException() {
        super(RestErrorStatus.FAILED.getMessage());
        this.error = RestError.error(RestErrorStatus.FAILED);
        this.status = RestErrorStatus.FAILED.getStatus();
    }

    public DefaultException(Supplier<RestStatus> supplier) {
        super(supplier.get().getMessage());
        this.error = RestError.parser(supplier.get());
        this.status = supplier.get().getStatus();
    }

    public DefaultException(String message) {
        super(message);
        this.error = RestError.error(RestErrorStatus.FAILED);
        this.status = RestErrorStatus.FAILED.getStatus();
    }

    public DefaultException(Integer status) {
        super(RestErrorStatus.FAILED.getMessage());
        this.error = RestError.error(RestErrorStatus.FAILED);
        this.status = status;
    }

    public DefaultException(RestStatus status) {
        super(status.getMessage());
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    public DefaultException(RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = error.getStatus();
    }

    public DefaultException(Throwable cause) {
        super(RestErrorStatus.FAILED.getMessage(), cause);
        this.error = RestError.parser(cause);
        this.status = RestErrorStatus.FAILED.getStatus();
    }

    public DefaultException(Integer status, String message) {
        super(message);
        this.error = RestError.error(status,message);
        this.status = status;
    }


    public DefaultException(Integer status, RestStatus restStatus) {
        super(restStatus.getMessage());
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }

    public DefaultException(Integer status, RestError error) {
        super(error.getMessage());
        this.error = error;
        this.status = status;
    }

    public DefaultException(Integer status, Throwable cause) {
        super(RestErrorStatus.FAILED.getMessage(), cause);
        this.error = RestError.parser(status,cause);
        this.status = status;
    }

    public DefaultException(String message, RestStatus status) {
        super(message);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    public DefaultException(String message, RestError error) {
        super(message);
        this.error = error;
        this.status = error.getStatus();
    }

    public DefaultException(RestStatus status, RestError error) {
        super(status.getMessage());
        this.status = status.getStatus();
        this.error = error;
    }

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(message,cause);
    }


    public DefaultException(RestStatus status, Throwable cause) {
        super(status.getMessage(), cause);
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    public DefaultException(Integer status, String message, RestError error) {
        super(message);
        this.status = status;
        this.error = error;
    }

    public DefaultException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(status, message, cause);
        this.status = status;
    }

    public DefaultException(Integer status, RestStatus restStatus, Throwable cause) {
        super(restStatus.getMessage(), cause);
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }

    public DefaultException(Integer status, RestError error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        this.status = status;
    }

    public DefaultException(String message, RestStatus status, Throwable cause) {
        super(message, cause);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    public DefaultException(String message, RestError error, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.status = error.getStatus();
    }

    public DefaultException(RestStatus status, RestError error,  Throwable cause) {
        super(status.getMessage(), cause);
        this.error = error;
        this.status = status.getStatus();
    }

    public DefaultException(Integer status, RestStatus restStatus, RestError error, Throwable cause) {
        super(restStatus.getMessage(), cause);
        this.status = status;
        this.error = error;
    }

    public DefaultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(message,cause);
        this.status = RestErrorStatus.FAILED.getStatus();
    }


    public DefaultException(RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(status);
        this.status = status.getStatus();
    }

    public DefaultException(RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = error.getStatus();
    }

    public DefaultException(Integer status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.error(status,message);
        this.status = status;
    }

    public DefaultException(Integer status, RestStatus restStatus, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(restStatus.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(status,restStatus);
        this.status = status;
    }
    public DefaultException(Integer status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error.getMessage(), cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = status;
    }


    public DefaultException(String message, RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = RestError.parser(message,status);
        this.status = status.getStatus();
    }

    public DefaultException(String message, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
        this.status = error.getStatus();
    }

    public DefaultException(RestStatus status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status.getMessage(), cause, enableSuppression, writableStackTrace);
        this.status = status.getStatus();
        this.error = error;
    }

    public final DefaultResult buildResult() {
        return DefaultResult.defaultBuilder().status(this.status).message(getMessage()).data(this.error).build();
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
