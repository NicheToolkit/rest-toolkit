package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <code>FileCreateException</code>
 * <p>The type file create exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FileErrorException
 * @since Jdk1.8
 */
public class FileCreateException extends FileErrorException {
    /**
     * <code>FileCreateException</code>
     * Instantiates a new file create exception.
     */
    public FileCreateException() {
        super(RestErrorStatus.FILE_CREATE_ERROR);
    }

    /**
     * <code>FileCreateException</code>
     * Instantiates a new file create exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public FileCreateException(RestStatus status) {
        super(status);
    }

    /**
     * <code>FileCreateException</code>
     * Instantiates a new file create exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FileCreateException(String message) {
        super(RestErrorStatus.FILE_CREATE_ERROR, message);
    }

    /**
     * <code>FileCreateException</code>
     * Instantiates a new file create exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FileCreateException(String resource, String message) {
        super(RestErrorStatus.FILE_CREATE_ERROR, resource, message);
    }

    @Override
    public FileCreateException get() {
        return new FileCreateException();
    }
}
