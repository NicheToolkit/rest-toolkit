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

    public TokenErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public TokenErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public TokenErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public TokenErrorException(String auth, String error) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(null, auth, null, RestErrorStatus.TOKEN_ERROR, error));
    }

    public TokenErrorException(String auth, Object token) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(null, auth, token, RestErrorStatus.TOKEN_ERROR));
    }

    public TokenErrorException(String auth, Object token, RestStatus status) {
        super(status, RestError.error(null, auth, token, status));
    }

    public TokenErrorException(String auth, Object token, String error) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(null, auth, token, RestErrorStatus.TOKEN_ERROR, error));
    }

    public TokenErrorException(String auth, Object token, RestStatus status, String error) {
        super(status, RestError.error(null, auth, token, status, error));
    }

    public TokenErrorException(String resource, String auth, Object token) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, auth, token, RestErrorStatus.TOKEN_ERROR));
    }

    public TokenErrorException(String resource, String auth, Object token, RestStatus status) {
        super(status, RestError.error(resource, auth, token, status));
    }

    public TokenErrorException(String resource, String auth, Object token, String error) {
        super(RestErrorStatus.TOKEN_ERROR, RestError.error(resource, auth, token, RestErrorStatus.TOKEN_ERROR, error));
    }

    public TokenErrorException(String resource, String auth, Object token, RestStatus status, String error) {
        super(status, RestError.error(resource, auth, token, status, error));
    }

    @Override
    public TokenErrorException get() {
        return new TokenErrorException();
    }
}
