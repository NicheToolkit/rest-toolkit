package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FieldErrorException;

/**
 * <code>FieldRepeatException</code>
 * <p>The type field repeat exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FieldErrorException
 * @since Jdk1.8
 */
public class FieldRepeatException extends FieldErrorException {
    /**
     * <code>FieldRepeatException</code>
     * Instantiates a new field repeat exception.
     */
    public FieldRepeatException() {
        super(RestErrorStatus.FIELD_REPEATED);
    }

    /**
     * <code>FieldRepeatException</code>
     * Instantiates a new field repeat exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public FieldRepeatException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>FieldRepeatException</code>
     * Instantiates a new field repeat exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FieldRepeatException(String message) {
        super(RestErrorStatus.FIELD_REPEATED, message);
    }


    /**
     * <code>FieldRepeatException</code>
     * Instantiates a new field repeat exception.
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FieldRepeatException(String field, String message) {
        super(RestErrorStatus.FIELD_REPEATED, "field", field, message);
    }

    /**
     * <code>FieldRepeatException</code>
     * Instantiates a new field repeat exception.
     * @param field   {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public FieldRepeatException(String field, Object value, String message) {
        super(RestErrorStatus.FIELD_REPEATED, "field", field, value, message);
    }

    @Override
    public FieldRepeatException get() {
        return new FieldRepeatException();
    }
}
