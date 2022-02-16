package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>HttpException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class HttpErrorException extends RestErrorException {
    public HttpErrorException() {
        super(RestErrorStatus.HTTP_ERROR);
    }

    public HttpErrorException(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public HttpErrorException(Throwable cause) {
        super(RestError.error(RestErrorStatus.HTTP_ERROR), cause);
    }

    public HttpErrorException(String message) {
        super(message, RestError.error(RestErrorStatus.HTTP_ERROR, message));
    }

    public HttpErrorException(String resource, String message) {
        super(message, RestError.error(resource, RestErrorStatus.HTTP_ERROR, message));
    }

    public HttpErrorException(String message, Throwable cause) {
        super(message, RestError.error(RestErrorStatus.HTTP_ERROR, message), cause);
    }

    public HttpErrorException(String resource, String message, Throwable cause) {
        super(message, RestError.error(resource, RestErrorStatus.HTTP_ERROR, message), cause);
    }

    public HttpErrorException(Integer status) {
        super(status, RestError.error(status, RestErrorStatus.HTTP_ERROR));
    }

    public HttpErrorException(Integer status, Throwable cause) {
        super(status, RestError.error(status, RestErrorStatus.HTTP_ERROR, cause));
    }

    public HttpErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public HttpErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause));
    }

    public HttpErrorException(Integer status, String message) {
        super(status, RestError.error(status, message));
    }

    public HttpErrorException(Integer status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public HttpErrorException(Integer status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause));
    }

    public HttpErrorException(Integer status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause));
    }

    public HttpErrorException(Integer status, RestStatus restStatus) {
        super(status, RestError.error(status, restStatus));
    }

    public HttpErrorException(Integer status, RestStatus restStatus, Throwable cause) {
        super(status, RestError.error(status, restStatus, cause), cause);
    }

    public HttpErrorException(String message, RestStatus status) {
        super(message, RestError.error(status, message));
    }

    public HttpErrorException(String resource, String message, RestStatus status) {
        super(message, RestError.error(resource, status, message));
    }

    public HttpErrorException(String message, RestStatus status, Throwable cause) {
        super(message, RestError.error(status, message, cause), cause);
    }

    public HttpErrorException(String resource, String message, RestStatus status, Throwable cause) {
        super(message, RestError.error(resource, status, message, cause), cause);
    }

    @Override
    public HttpErrorException get() {
        return new HttpErrorException();
    }
}
