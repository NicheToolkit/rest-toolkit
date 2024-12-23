package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.IdentityErrorException;

/**
 * <code>IdentityRepeatException</code>
 * <p>The identity repeat exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.IdentityErrorException
 * @since Jdk1.8
 */
public class IdentityRepeatException extends IdentityErrorException {

    /**
     * <code>IdentityRepeatException</code>
     * <p>Instantiates a new identity repeat exception.</p>
     */
    public IdentityRepeatException() {
        super(RestErrorStatus.IDENTITY_REPEATED);
    }

    /**
     * <code>IdentityRepeatException</code>
     * <p>Instantiates a new identity repeat exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IdentityRepeatException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>IdentityRepeatException</code>
     * <p>Instantiates a new identity repeat exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityRepeatException(String message) {
        super(RestErrorStatus.IDENTITY_REPEATED, message);
    }


    /**
     * <code>IdentityRepeatException</code>
     * <p>Instantiates a new identity repeat exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityRepeatException(String field, String message) {
        super(RestErrorStatus.IDENTITY_REPEATED, "identity", field, message);
    }

    /**
     * <code>IdentityRepeatException</code>
     * <p>Instantiates a new identity repeat exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public IdentityRepeatException(String field, Object value, String message) {
        super(RestErrorStatus.IDENTITY_REPEATED, "identity", field, value, message);
    }

    @Override
    public IdentityRepeatException get() {
        return new IdentityRepeatException();
    }
}
