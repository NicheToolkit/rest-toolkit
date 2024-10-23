package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <code>FileCopyException</code>
 * <p>The file copy exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FileErrorException
 * @since Jdk1.8
 */
public class FileCopyException extends FileErrorException {
    /**
     * <code>FileCopyException</code>
     * <p>Instantiates a new file copy exception.</p>
     */
    public FileCopyException() {
        super(RestErrorStatus.FILE_COPY_ERROR);
    }

    /**
     * <code>FileCopyException</code>
     * <p>Instantiates a new file copy exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public FileCopyException(RestStatus status) {
        super(status);
    }

    /**
     * <code>FileCopyException</code>
     * <p>Instantiates a new file copy exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FileCopyException(String message) {
        super(RestErrorStatus.FILE_COPY_ERROR, message);
    }

    /**
     * <code>FileCopyException</code>
     * <p>Instantiates a new file copy exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
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
