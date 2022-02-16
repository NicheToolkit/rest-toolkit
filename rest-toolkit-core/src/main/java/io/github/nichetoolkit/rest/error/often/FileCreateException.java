package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FileCreateException</p>
 *
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FileCreateException extends RestErrorException {
    public FileCreateException() {
        super(RestErrorStatus.FILE_CREATE_ERROR);
    }

    public FileCreateException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public FileCreateException(String message) {
        super(RestErrorStatus.FILE_CREATE_ERROR, RestError.error(RestErrorStatus.FILE_CREATE_ERROR, message));
    }

    public FileCreateException(String resource, String message) {
        super(RestErrorStatus.FILE_CREATE_ERROR, RestError.error(resource, RestErrorStatus.FILE_CREATE_ERROR, message));
    }

    @Override
    public FileCreateException get() {
        return new FileCreateException();
    }
}
