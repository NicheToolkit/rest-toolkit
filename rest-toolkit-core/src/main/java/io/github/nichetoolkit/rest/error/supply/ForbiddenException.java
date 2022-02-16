package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>ForbiddenException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ForbiddenException extends RestErrorException {
    public ForbiddenException() {
        super(RestErrorStatus.AUTH_FORBIDDEN);
    }

    public ForbiddenException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public ForbiddenException(String user) {
        super(RestErrorStatus.AUTH_FORBIDDEN, RestError.error(null, user, RestErrorStatus.AUTH_FORBIDDEN));
    }

    public ForbiddenException(String resource, String user, String auth) {
        super(RestErrorStatus.AUTH_FORBIDDEN, RestError.error(resource, user, auth, RestErrorStatus.AUTH_FORBIDDEN));
    }

    @Override
    public ForbiddenException get() {
        return new ForbiddenException();
    }
}
