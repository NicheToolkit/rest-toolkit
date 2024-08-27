package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FieldErrorException;

/**
 * <code>NameNullException</code>
 * <p>The type name null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FieldErrorException
 * @since Jdk1.8
 */
public class NameNullException extends FieldErrorException {
    /**
     * <code>NameNullException</code>
     * Instantiates a new name null exception.
     */
    public NameNullException() {
        super(RestErrorStatus.NAME_IS_NULL);
    }

    /**
     * <code>NameNullException</code>
     * Instantiates a new name null exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public NameNullException(RestStatus status) {
        super(status);
    }

    /**
     * <code>NameNullException</code>
     * Instantiates a new name null exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public NameNullException(String message) {
        super(RestErrorStatus.NAME_IS_NULL, message);
    }

    /**
     * <code>NameNullException</code>
     * Instantiates a new name null exception.
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public NameNullException(String field, String message) {
        super(RestErrorStatus.NAME_IS_NULL,"name", field, message);
    }

    @Override
    public NameNullException get() {
        return new NameNullException();
    }
}
