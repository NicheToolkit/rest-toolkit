package io.github.nichetoolkit.rest.error.supply;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>ForbiddenException</code>
 * <p>The forbidden exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class ForbiddenException extends RestErrorException {
    /**
     * <code>ForbiddenException</code>
     * <p>Instantiates a new forbidden exception.</p>
     */
    public ForbiddenException() {
        super(RestErrorStatus.AUTH_FORBIDDEN);
    }

    /**
     * <code>ForbiddenException</code>
     * <p>Instantiates a new forbidden exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ForbiddenException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ForbiddenException</code>
     * <p>Instantiates a new forbidden exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ForbiddenException(String message) {
        super(RestErrorStatus.AUTH_FORBIDDEN, RestError.error(RestErrorStatus.AUTH_FORBIDDEN, message));
    }

    /**
     * <code>ForbiddenException</code>
     * <p>Instantiates a new forbidden exception.</p>
     * @param user    {@link java.lang.String} <p>The user parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ForbiddenException(String user, String message) {
        super(RestErrorStatus.AUTH_FORBIDDEN, RestError.error(null, user, RestErrorStatus.AUTH_FORBIDDEN, message));
    }

    /**
     * <code>ForbiddenException</code>
     * <p>Instantiates a new forbidden exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param user     {@link java.lang.String} <p>The user parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ForbiddenException(String resource, String user, String message) {
        super(RestErrorStatus.AUTH_FORBIDDEN, RestError.error(resource, user, RestErrorStatus.AUTH_FORBIDDEN, message));
    }

    @Override
    public ForbiddenException get() {
        return new ForbiddenException();
    }
}
