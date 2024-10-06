package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FieldErrorException;

/**
 * <code>NameRepeatException</code>
 * <p>The type name repeat exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FieldErrorException
 * @since Jdk1.8
 */
public class NameRepeatException extends FieldErrorException {
    /**
     * <code>NameRepeatException</code>
     * <p>Instantiates a new name repeat exception.</p>
     */
    public NameRepeatException() {
        super(RestErrorStatus.NAME_REPEATED);
    }

    /**
     * <code>NameRepeatException</code>
     * <p>Instantiates a new name repeat exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public NameRepeatException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>NameRepeatException</code>
     * <p>Instantiates a new name repeat exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public NameRepeatException(String message) {
        super(RestErrorStatus.FIELD_REPEATED, message);
    }


    /**
     * <code>NameRepeatException</code>
     * <p>Instantiates a new name repeat exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public NameRepeatException(String field, String message) {
        super(RestErrorStatus.FIELD_REPEATED, "name", field, message);
    }

    /**
     * <code>NameRepeatException</code>
     * <p>Instantiates a new name repeat exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public NameRepeatException(String field, Object value, String message) {
        super(RestErrorStatus.FIELD_REPEATED, "name", field, value, message);
    }

    @Override
    public NameRepeatException get() {
        return new NameRepeatException();
    }
}
