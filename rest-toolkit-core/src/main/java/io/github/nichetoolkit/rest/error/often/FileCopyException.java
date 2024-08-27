package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <code>FileCopyException</code>
 * <p>The type file copy exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FileErrorException
 * @since Jdk1.8
 */
public class FileCopyException extends FileErrorException {
    /**
     * <code>FileCopyException</code>
     * Instantiates a new file copy exception.
     */
    public FileCopyException() {
        super(RestErrorStatus.FILE_COPY_ERROR);
    }

    /**
     * <code>FileCopyException</code>
     * Instantiates a new file copy exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public FileCopyException(RestStatus status) {
        super(status);
    }

    /**
     * <code>FileCopyException</code>
     * Instantiates a new file copy exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FileCopyException(String message) {
        super(RestErrorStatus.FILE_COPY_ERROR, message);
    }

    /**
     * <code>FileCopyException</code>
     * Instantiates a new file copy exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FileCopyException(String resource, String message) {
        super(RestErrorStatus.FILE_COPY_ERROR, resource, message);
    }

    @Override
    public FileCopyException get() {
        return new FileCopyException();
    }
}
