package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FileCopyException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FileCopyException extends RestErrorException {
    public FileCopyException() {
        super(RestErrorStatus.FILE_COPY_ERROR);
    }

    public FileCopyException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public FileCopyException(String message) {
        super(RestErrorStatus.FILE_COPY_ERROR, RestError.error(RestErrorStatus.FILE_COPY_ERROR, message));
    }

    public FileCopyException(String resource, String message) {
        super(RestErrorStatus.FILE_COPY_ERROR, RestError.error(resource, RestErrorStatus.FILE_COPY_ERROR, message));
    }

    @Override
    public FileCopyException get() {
        return new FileCopyException();
    }
}
