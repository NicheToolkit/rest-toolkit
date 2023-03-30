package io.github.nichetoolkit.rest.error.lack;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <p>AccessibleLackError</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class AccessibleLackError extends RestError {

    public AccessibleLackError() {
        super(RestErrorStatus.ACCESSIBLE_LACK_ERROR);
    }

    public AccessibleLackError(Supplier<RestStatus> supplier) {
        super(supplier);
    }

    public AccessibleLackError(String error) {
        super(error,RestErrorStatus.ACCESSIBLE_LACK_ERROR);
    }

    public AccessibleLackError(RestStatus status) {
        super(status);
    }

    @Override
    public AccessibleLackError get() {
        return new AccessibleLackError();
    }
}
