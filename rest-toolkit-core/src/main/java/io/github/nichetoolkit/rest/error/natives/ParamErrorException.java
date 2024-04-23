package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>ParamException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParamErrorException extends RestErrorException {

    public ParamErrorException() {
        super(RestErrorStatus.PARAM_ERROR);
    }

    public ParamErrorException(RestErrorStatus status) {
        super(status);
    }

    public ParamErrorException(String error) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(RestErrorStatus.PARAM_ERROR, error));
    }

    public ParamErrorException(String error, Throwable cause) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(RestErrorStatus.PARAM_ERROR, error, cause), cause);
    }

    public ParamErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ParamErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public ParamErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ParamErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public ParamErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public ParamErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public ParamErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ParamErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public ParamErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public ParamErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public ParamErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public ParamErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public ParamErrorException(String resource, String error) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(resource, RestErrorStatus.PARAM_ERROR, error));
    }

    public ParamErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(resource, RestErrorStatus.PARAM_ERROR, error, cause), cause);
    }

    public ParamErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(resource, filed, RestErrorStatus.PARAM_ERROR, error));
    }

    public ParamErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(resource, filed, RestErrorStatus.PARAM_ERROR, error, cause), cause);
    }

    public ParamErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(resource, filed, value, RestErrorStatus.PARAM_ERROR, error));
    }

    public ParamErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.PARAM_ERROR, RestError.error(resource, filed, value, RestErrorStatus.PARAM_ERROR, error, cause));
    }

    @Override
    public ParamErrorException get() {
        return new ParamErrorException();
    }

}
