package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>TokenErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class LoginErrorException extends RestErrorException {

    public LoginErrorException() {
        super(RestErrorStatus.LOGIN_ERROR);
    }

    public LoginErrorException(RestErrorStatus status) {
        super(status);
    }

    public LoginErrorException(String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public LoginErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public LoginErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public LoginErrorException(String auth, String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(null, auth, null, RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(String auth, Object token) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(null, auth, token, RestErrorStatus.LOGIN_ERROR));
    }

    public LoginErrorException(String auth, Object token, RestStatus status) {
        super(status, RestError.error(null, auth, token, status));
    }

    public LoginErrorException(String auth, Object token, String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(null, auth, token, RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(String auth, Object token, RestStatus status, String error) {
        super(status, RestError.error(null, auth, token, status, error));
    }

    public LoginErrorException(String resource, String auth, Object token) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, auth, token, RestErrorStatus.LOGIN_ERROR));
    }

    public LoginErrorException(String resource, String auth, Object token, RestStatus status) {
        super(status, RestError.error(resource, auth, token, status));
    }

    public LoginErrorException(String resource, String auth, Object token, String error) {
        super(RestErrorStatus.LOGIN_ERROR, RestError.error(resource, auth, token, RestErrorStatus.LOGIN_ERROR, error));
    }

    public LoginErrorException(String resource, String auth, Object token, RestStatus status, String error) {
        super(status, RestError.error(resource, auth, token, status, error));
    }

    @Override
    public LoginErrorException get() {
        return new LoginErrorException();
    }
}
