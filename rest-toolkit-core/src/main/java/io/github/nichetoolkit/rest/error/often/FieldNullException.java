package io.github.nichetoolkit.rest.error.often;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FieldErrorException;

/**
 * <code>FieldNullException</code>
 * <p>The type field null exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FieldErrorException
 * @since Jdk1.8
 */
public class FieldNullException extends FieldErrorException {

    /**
     * <code>FieldNullException</code>
     * <p>Instantiates a new field null exception.</p>
     */
    public FieldNullException() {
        super(RestErrorStatus.FIELD_IS_NULL);
    }

    /**
     * <code>FieldNullException</code>
     * <p>Instantiates a new field null exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public FieldNullException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>FieldNullException</code>
     * <p>Instantiates a new field null exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FieldNullException(String message) {
        super(RestErrorStatus.FIELD_IS_NULL, message);
    }

    /**
     * <code>FieldNullException</code>
     * <p>Instantiates a new field null exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public FieldNullException(String field, String message) {
        super(RestErrorStatus.FIELD_IS_NULL, "field", field, message);
    }

    @Override
    public FieldNullException get() {
        return new FieldNullException();
    }
}
