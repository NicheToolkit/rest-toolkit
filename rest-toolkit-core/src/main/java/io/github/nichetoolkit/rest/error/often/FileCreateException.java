package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <p>FileCreateException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FileCreateException extends FileErrorException {
    public FileCreateException() {
        super(RestErrorStatus.FILE_CREATE_ERROR);
    }

    public FileCreateException(RestStatus status) {
        super(status);
    }

    public FileCreateException(String message) {
        super(RestErrorStatus.FILE_CREATE_ERROR, message);
    }

    public FileCreateException(String resource, String message) {
        super(RestErrorStatus.FILE_CREATE_ERROR, resource, message);
    }

    @Override
    public FileCreateException get() {
        return new FileCreateException();
    }
}
