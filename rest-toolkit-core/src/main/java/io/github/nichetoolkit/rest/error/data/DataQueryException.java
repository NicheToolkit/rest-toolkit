package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataQueryException</code>
 * <p>The type data query exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataQueryException extends DataErrorException {
    /**
     * <code>DataQueryException</code>
     * Instantiates a new data query exception.
     */
    public DataQueryException() {
        super(RestErrorStatus.DATA_QUERY_FAILED);
    }

    /**
     * <code>DataQueryException</code>
     * Instantiates a new data query exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataQueryException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataQueryException</code>
     * Instantiates a new data query exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataQueryException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataQueryException</code>
     * Instantiates a new data query exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataQueryException(String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, message);
    }

    /**
     * <code>DataQueryException</code>
     * Instantiates a new data query exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataQueryException(String resource, String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, resource, message);
    }

    /**
     * <code>DataQueryException</code>
     * Instantiates a new data query exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataQueryException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_QUERY_FAILED, resource, field, value, message);
    }

    @Override
    public DataQueryException get() {
        return new DataQueryException();
    }
}
