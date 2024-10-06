package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataCreateException</code>
 * <p>The type data create exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataCreateException extends DataErrorException {
    /**
     * <code>DataCreateException</code>
     * <p>Instantiates a new data create exception.</p>
     */
    public DataCreateException() {
        super(RestErrorStatus.DATA_CREATE_FAILED);
    }

    /**
     * <code>DataCreateException</code>
     * <p>Instantiates a new data create exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataCreateException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataCreateException</code>
     * <p>Instantiates a new data create exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataCreateException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataCreateException</code>
     * <p>Instantiates a new data create exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataCreateException(String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, message);
    }

    /**
     * <code>DataCreateException</code>
     * <p>Instantiates a new data create exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataCreateException(String resource, String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, resource, message);
    }

    /**
     * <code>DataCreateException</code>
     * <p>Instantiates a new data create exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataCreateException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_CREATE_FAILED, resource, field, value, message);
    }

    @Override
    public DataCreateException get() {
        return new DataCreateException();
    }
}
