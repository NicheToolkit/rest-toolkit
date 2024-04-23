package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <p>FileCopyException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FileCopyException extends FileErrorException {
    public FileCopyException() {
        super(RestErrorStatus.FILE_COPY_ERROR);
    }

    public FileCopyException(RestStatus status) {
        super(status);
    }

    public FileCopyException(String message) {
        super(RestErrorStatus.FILE_COPY_ERROR, message);
    }

    public FileCopyException(String resource, String message) {
        super(RestErrorStatus.FILE_COPY_ERROR, resource, message);
    }

    @Override
    public FileCopyException get() {
        return new FileCopyException();
    }
}
