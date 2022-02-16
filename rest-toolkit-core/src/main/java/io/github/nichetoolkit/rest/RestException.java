package io.github.nichetoolkit.rest;


import java.util.function.Supplier;

/**
 * <p>RestException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestException extends DefaultException {

    public RestException() {
        super(RestErrorStatus.UNKNOWN_ERROR);
    }

    public RestException(Supplier<RestStatus> supplier) {
        super(supplier.get());
    }

    public RestException(RestError error) {
        super((RestStatus)error);
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(Integer status) {
        super(status);
    }

    public RestException(RestStatus status) {
        super(status);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public RestException(Integer status, String message) {
        super(status, message);
    }

    public RestException(Integer status, RestStatus restStatus) {
        super(status, restStatus);
    }

    public RestException(Integer status, RestError error) {
        super(status, error);
    }

    public RestException(Integer status, Throwable cause) {
        super(status, cause);
    }

    public RestException(String message, RestStatus status) {
        super(message, status);
    }

    public RestException(String message, RestError error) {
        super(message, error);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(RestStatus status, RestError error) {
        super(status, error);
    }

    public RestException(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    public RestException(Integer status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public RestException(Integer status, RestStatus restStatus, Throwable cause) {
        super(status, restStatus, cause);
    }

    public RestException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public RestException(String message, RestStatus status, Throwable cause) {
        super(message, status, cause);
    }

    public RestException(String message, RestError error, Throwable cause) {
        super(message, error, cause);
    }

    public RestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RestException(RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, cause, enableSuppression, writableStackTrace);
    }

    public RestException(Integer status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, message, cause, enableSuppression, writableStackTrace);
    }

    public RestException(Integer status, RestStatus restStatus, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, restStatus, cause, enableSuppression, writableStackTrace);
    }

    public RestException(String message, RestStatus status, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, status, cause, enableSuppression, writableStackTrace);
    }

    public RestException(RestStatus status, RestError error, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, error, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public RestException get() {
        return new RestException();
    }

}
