package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>ConfigureLackError</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ConfigureLackError extends RestError {

    public ConfigureLackError() {
        super(RestErrorStatus.CONFIGURE_LACK_ERROR);
    }

    public ConfigureLackError(Throwable cause) {
        super(RestErrorStatus.CONFIGURE_LACK_ERROR, cause);
    }

    public ConfigureLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public ConfigureLackError(String error) {
        super(error, RestErrorStatus.CONFIGURE_LACK_ERROR);
    }

    public ConfigureLackError(String error, Throwable cause) {
        super(RestErrorStatus.CONFIGURE_LACK_ERROR, error, cause);
    }

    public ConfigureLackError(RestStatus status) {
        super(status);
    }

    public ConfigureLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public ConfigureLackError get() {
        return new ConfigureLackError();
    }
}
