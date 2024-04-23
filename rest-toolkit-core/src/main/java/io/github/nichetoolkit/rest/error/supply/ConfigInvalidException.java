package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ConfigInvalidException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ConfigInvalidException extends RestErrorException {

    public ConfigInvalidException() {
        super(RestErrorStatus.CONFIG_INVALID);
    }

    public ConfigInvalidException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ConfigInvalidException(String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(RestErrorStatus.CONFIG_INVALID, message));
    }

    public ConfigInvalidException(String field, String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(field, RestErrorStatus.CONFIG_INVALID, message));
    }

    public ConfigInvalidException(String field, Object value, String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(field, value, RestErrorStatus.CONFIG_INVALID, message));
    }

    public ConfigInvalidException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.CONFIG_INVALID, RestError.error(resource, field, value, RestErrorStatus.CONFIG_INVALID, message));
    }

    @Override
    public ConfigInvalidException get() {
        return new ConfigInvalidException();
    }
}
