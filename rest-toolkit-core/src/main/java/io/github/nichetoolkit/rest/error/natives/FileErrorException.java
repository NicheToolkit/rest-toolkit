package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>FileErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FileErrorException extends RestErrorException {

    public FileErrorException() {
        super(RestErrorStatus.FILE_ERROR);
    }

    public FileErrorException(RestErrorStatus status) {
        super(status);
    }

    public FileErrorException(String error) {
        super(RestErrorStatus.FILE_ERROR, RestError.error(RestErrorStatus.FILE_ERROR,error));
    }

    public FileErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    public FileErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    public FileErrorException(RestStatus status, String error) {
        super(status,RestError.error(status, error));
    }

    public FileErrorException(String file, Object value) {
        super(RestErrorStatus.FILE_ERROR, RestError.error(file, value, RestErrorStatus.FILE_ERROR));
    }

    public FileErrorException(String file, String error) {
        super(RestErrorStatus.FILE_ERROR, RestError.error(file, RestErrorStatus.FILE_ERROR, error));
    }

    public FileErrorException(String file, Object value, String error) {
        super(RestErrorStatus.FILE_ERROR, RestError.error(file, value, RestErrorStatus.FILE_ERROR, error));
    }

    public FileErrorException(String file, RestStatus status) {
        super(status, RestError.error(file, status));
    }

    public FileErrorException(String file, Object value, RestStatus status) {
        super(status, RestError.error(file, value, status));
    }

    public FileErrorException(String file, RestStatus status, String error) {
        super(status, RestError.error(file, status, error));
    }

    public FileErrorException(String file, Object value, RestStatus status, String error) {
        super(status, RestError.error(file, value, status, error));
    }


    @Override
    public FileErrorException get() {
        return new FileErrorException();
    }
}
