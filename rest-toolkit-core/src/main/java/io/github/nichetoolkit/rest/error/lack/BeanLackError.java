package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>BeanLackErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class BeanLackError extends RestError {

    public BeanLackError() {
        super(RestErrorStatus.BEAN_LACK_ERROR);
    }

    public BeanLackError(Throwable cause) {
        super(RestErrorStatus.BEAN_LACK_ERROR, cause);
    }

    public BeanLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public BeanLackError(String error) {
        super(error, RestErrorStatus.BEAN_LACK_ERROR);
    }

    public BeanLackError(String error, Throwable cause) {
        super(RestErrorStatus.BEAN_LACK_ERROR, error, cause);
    }

    public BeanLackError(RestStatus status) {
        super(status);
    }

    public BeanLackError(RestStatus status, Throwable cause) {
        super(status, cause);
    }

    @Override
    public BeanLackError get() {
        return new BeanLackError();
    }
}
