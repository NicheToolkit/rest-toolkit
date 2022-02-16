package io.github.nichetoolkit.rest.error.network;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>BeanLackErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class HttpConfigError extends RestError {

    public HttpConfigError() {
        super(RestErrorStatus.HTTP_CONFIG_ERROR);
    }

    public HttpConfigError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public HttpConfigError(String error) {
        super(error,RestErrorStatus.HTTP_CONFIG_ERROR);
    }

    public HttpConfigError( Throwable cause) {
        super(cause);
    }

    public HttpConfigError(String error, Throwable cause) {
        super(error,cause);
    }

    public HttpConfigError(RestStatus status) {
        super(status);
    }


    @Override
    public HttpConfigError get() {
        return new HttpConfigError();
    }
}
