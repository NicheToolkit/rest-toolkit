package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>MethodLackErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class MethodLackError extends RestError {

    public MethodLackError() {
        super(RestErrorStatus.METHOD_LACK_ERROR);
    }

    public MethodLackError(Throwable cause) {
        super(RestErrorStatus.METHOD_LACK_ERROR, cause);
    }

    public MethodLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public MethodLackError(String error) {
        super(error, RestErrorStatus.METHOD_LACK_ERROR);
    }

    public MethodLackError(String error, Throwable cause) {
        super(RestErrorStatus.METHOD_LACK_ERROR, error, cause);
    }

    public MethodLackError(RestStatus status) {
        super(status);
    }

    public MethodLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public MethodLackError get() {
        return new MethodLackError();
    }
}
