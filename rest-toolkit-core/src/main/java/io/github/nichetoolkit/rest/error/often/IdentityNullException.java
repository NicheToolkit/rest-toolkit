package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IdentityErrorException;

/**
 * <code>IdentityNullException</code>
 * <p>The type identity null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.IdentityErrorException
 * @since Jdk1.8
 */
public class IdentityNullException extends IdentityErrorException {
    /**
     * <code>IdentityNullException</code>
     * Instantiates a new identity null exception.
     */
    public IdentityNullException() {
        super(RestErrorStatus.IDENTITY_IS_NULL);
    }

    /**
     * <code>IdentityNullException</code>
     * Instantiates a new identity null exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IdentityNullException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>IdentityNullException</code>
     * Instantiates a new identity null exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityNullException(String message) {
        super(RestErrorStatus.IDENTITY_IS_NULL, message);
    }

    /**
     * <code>IdentityNullException</code>
     * Instantiates a new identity null exception.
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityNullException(String field, String message) {
        super(RestErrorStatus.IDENTITY_IS_NULL, "identity", field, message);
    }

    @Override
    public IdentityNullException get() {
        return new IdentityNullException();
    }
}
