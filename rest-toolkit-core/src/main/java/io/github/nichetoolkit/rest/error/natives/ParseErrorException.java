package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ParseErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParseErrorException extends RestErrorException {

    public ParseErrorException() {
        super(RestErrorStatus.PARSE_ERROR);
    }

    public ParseErrorException(RestErrorStatus status) {
        super(status);
    }

    public ParseErrorException(String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String error, Throwable cause) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(RestErrorStatus.PARSE_ERROR, error, cause), cause);
    }

    public ParseErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ParseErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public ParseErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ParseErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public ParseErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public ParseErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public ParseErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public ParseErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public ParseErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public ParseErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public ParseErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public ParseErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public ParseErrorException(String resource, String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, RestErrorStatus.PARSE_ERROR, error, cause), cause);
    }

    public ParseErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, filed, RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, filed, RestErrorStatus.PARSE_ERROR, error, cause), cause);
    }

    public ParseErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, filed, value, RestErrorStatus.PARSE_ERROR, error));
    }

    public ParseErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.PARSE_ERROR, RestError.error(resource, filed, value, RestErrorStatus.PARSE_ERROR, error, cause));
    }

    @Override
    public ParseErrorException get() {
        return new ParseErrorException();
    }
}
