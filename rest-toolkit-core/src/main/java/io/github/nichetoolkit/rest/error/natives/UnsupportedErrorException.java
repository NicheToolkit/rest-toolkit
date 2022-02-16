package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.RestErrorException;

/**
 * <p>UnsupportedErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class UnsupportedErrorException extends RestErrorException {

    public UnsupportedErrorException() {
        super(RestErrorStatus.UNSUPPORTED);
    }

    public UnsupportedErrorException(RestErrorStatus status) {
        super(status);
    }

    public UnsupportedErrorException(String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(RestErrorStatus.UNSUPPORTED, error));
    }

    public UnsupportedErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public UnsupportedErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public UnsupportedErrorException(RestStatus status, String error) {
        super(status, RestError.error(status, error));
    }

    public UnsupportedErrorException(String target, String error) {
        super(RestErrorStatus.UNSUPPORTED, RestError.error(target, RestErrorStatus.UNSUPPORTED, error));
    }

    public UnsupportedErrorException(String target, RestStatus status) {
        super(status, RestError.error(target, status));
    }

    @Override
    public UnsupportedErrorException get() {
        return new UnsupportedErrorException();
    }
}
