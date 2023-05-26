package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>HttpException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class HttpErrorException extends RestErrorException {
    public HttpErrorException() {
        super(RestErrorStatus.HTTP_ERROR);
    }

    public HttpErrorException(Integer status) {
        super(status, RestError.error(status, RestErrorStatus.HTTP_ERROR));
    }

    public HttpErrorException(Integer status, Throwable cause) {
        super(status, RestError.error(status, RestErrorStatus.HTTP_ERROR, cause));
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

    public HttpErrorException(RestErrorStatus status) {
        super(status);
    }

    public HttpErrorException(String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(RestErrorStatus.HTTP_ERROR, error));
    }

    public HttpErrorException(String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(RestErrorStatus.HTTP_ERROR, error, cause), cause);
    }

    public HttpErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public HttpErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public HttpErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public HttpErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public HttpErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public HttpErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public HttpErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public HttpErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public HttpErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public HttpErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public HttpErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public HttpErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public HttpErrorException(String resource, String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, RestErrorStatus.HTTP_ERROR, error));
    }

    public HttpErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, RestErrorStatus.HTTP_ERROR, error, cause), cause);
    }

    public HttpErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, RestErrorStatus.HTTP_ERROR, error));
    }

    public HttpErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, RestErrorStatus.HTTP_ERROR, error, cause), cause);
    }

    public HttpErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, value, RestErrorStatus.HTTP_ERROR, error));
    }

    public HttpErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.HTTP_ERROR, RestError.error(resource, filed, value, RestErrorStatus.HTTP_ERROR, error, cause));
    }

    @Override
    public HttpErrorException get() {
        return new HttpErrorException();
    }
}
