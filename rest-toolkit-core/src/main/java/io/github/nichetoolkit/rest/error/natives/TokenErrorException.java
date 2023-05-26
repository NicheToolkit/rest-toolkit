package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>TokenErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class TokenErrorException extends RestErrorException {

    public TokenErrorException() {
        super(RestErrorStatus.TOKEN_ERROR);
    }

    public TokenErrorException(RestErrorStatus status) {
        super(status);
    }

    public TokenErrorException(String error) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(RestErrorStatus.TOKEN_ERROR, error));
    }

    public TokenErrorException(String error, Throwable cause) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(RestErrorStatus.TOKEN_ERROR, error, cause), cause);
    }

    public TokenErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public TokenErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    public TokenErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public TokenErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    public TokenErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    public TokenErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    public TokenErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    public TokenErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    public TokenErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    public TokenErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    public TokenErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    public TokenErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    public TokenErrorException(String resource, String error) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, RestErrorStatus.TOKEN_ERROR, error));
    }

    public TokenErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, RestErrorStatus.TOKEN_ERROR, error, cause), cause);
    }

    public TokenErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, filed, RestErrorStatus.TOKEN_ERROR, error));
    }

    public TokenErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, filed, RestErrorStatus.TOKEN_ERROR, error, cause), cause);
    }

    public TokenErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, filed, value, RestErrorStatus.TOKEN_ERROR, error));
    }

    public TokenErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, filed, value, RestErrorStatus.TOKEN_ERROR, error, cause));
    }

    @Override
    public TokenErrorException get() {
        return new TokenErrorException();
    }
}
