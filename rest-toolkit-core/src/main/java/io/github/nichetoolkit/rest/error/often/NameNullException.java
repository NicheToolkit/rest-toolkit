package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FieldErrorException;

/**
 * <code>NameNullException</code>
 * <p>The name null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FieldErrorException
 * @since Jdk1.8
 */
public class NameNullException extends FieldErrorException {
    /**
     * <code>NameNullException</code>
     * <p>Instantiates a new name null exception.</p>
     */
    public NameNullException() {
        super(RestErrorStatus.NAME_IS_NULL);
    }

    /**
     * <code>NameNullException</code>
     * <p>Instantiates a new name null exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public NameNullException(RestStatus status) {
        super(status);
    }

    /**
     * <code>NameNullException</code>
     * <p>Instantiates a new name null exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public NameNullException(String message) {
        super(RestErrorStatus.NAME_IS_NULL, message);
    }

    /**
     * <code>NameNullException</code>
     * <p>Instantiates a new name null exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
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
