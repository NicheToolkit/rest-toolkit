package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>ConfigureErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ConfigErrorException extends RestErrorException {

    public ConfigErrorException() {
        super(RestErrorStatus.CONFIG_ERROR);
    }

    public ConfigErrorException(RestErrorStatus status) {
        super(status);
    }

    public ConfigErrorException(String error) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(RestErrorStatus.CONFIG_ERROR, error));
    }

    public ConfigErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ConfigErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public ConfigErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    public ConfigErrorException(String filed, String error) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(null, filed, null, RestErrorStatus.CONFIG_ERROR, error));
    }

    public ConfigErrorException(String filed, Object value) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(null, filed, value, RestErrorStatus.CONFIG_ERROR));
    }

    public ConfigErrorException(String filed, Object value, RestStatus status) {
        super(status, RestError.error(null, filed, value, status));
    }

    public ConfigErrorException(String filed, Object value, String error) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(null, filed, value, RestErrorStatus.CONFIG_ERROR, error));
    }

    public ConfigErrorException(String filed, Object value, RestStatus status, String error) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(null, filed, value, status, error));
    }

    public ConfigErrorException(String resource, String filed, Object value) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONFIG_ERROR));
    }

    public ConfigErrorException(String resource, String filed, Object value, RestStatus status) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(resource, filed, value, status));
    }

    public ConfigErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(resource, filed, value, RestErrorStatus.CONFIG_ERROR, error));
    }

    public ConfigErrorException(String resource, String filed, Object value, RestStatus status, String error) {
        super(RestErrorStatus.CONFIG_ERROR, RestError.error(resource, filed, value, status, error));
    }

    @Override
    public ConfigErrorException get() {
        return new ConfigErrorException();
    }
}
