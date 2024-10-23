package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>ZipErrorException</code>
 * <p>The zip error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ZipErrorException extends RestErrorException {
    /**
     * <code>ZipErrorException</code>
     * <p>Instantiates a new zip error exception.</p>
     */
    public ZipErrorException() {
        super(RestErrorStatus.ZIP_ERROR);
    }

    /**
     * <code>ZipErrorException</code>
     * <p>Instantiates a new zip error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ZipErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ZipErrorException</code>
     * <p>Instantiates a new zip error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ZipErrorException(String message) {
        super(RestErrorStatus.ZIP_ERROR, RestError.error(RestErrorStatus.ZIP_ERROR, message));
    }

    /**
     * <code>ZipErrorException</code>
     * <p>Instantiates a new zip error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ZipErrorException(String resource, String message) {
        super(RestErrorStatus.ZIP_ERROR, RestError.error(resource, RestErrorStatus.ZIP_ERROR, message));
    }

    @Override
    public ZipErrorException get() {
        return new ZipErrorException();
    }
}
