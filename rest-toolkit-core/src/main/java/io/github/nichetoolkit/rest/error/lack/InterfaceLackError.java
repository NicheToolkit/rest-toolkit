package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>interfaceLackErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class InterfaceLackError extends RestError {

    public InterfaceLackError() {
        super(RestErrorStatus.INTERFACE_LACK_ERROR);
    }

    public InterfaceLackError(Throwable cause) {
        super(RestErrorStatus.INTERFACE_LACK_ERROR, cause);
    }

    public InterfaceLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public InterfaceLackError(String error) {
        super(error, RestErrorStatus.INTERFACE_LACK_ERROR);
    }

    public InterfaceLackError(String error, Throwable cause) {
        super(RestErrorStatus.INTERFACE_LACK_ERROR, error, cause);
    }

    public InterfaceLackError(RestStatus status) {
        super(status);
    }

    public InterfaceLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public InterfaceLackError get() {
        return new InterfaceLackError();
    }
}
