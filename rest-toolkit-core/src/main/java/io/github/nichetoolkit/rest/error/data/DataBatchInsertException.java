package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataBatchInsertException</code>
 * <p>The type data batch insert exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataBatchInsertException extends DataErrorException {
    /**
     * <code>DataBatchInsertException</code>
     * Instantiates a new data batch insert exception.
     */
    public DataBatchInsertException() {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED);
    }

    /**
     * <code>DataBatchInsertException</code>
     * Instantiates a new data batch insert exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataBatchInsertException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchInsertException</code>
     * Instantiates a new data batch insert exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataBatchInsertException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchInsertException</code>
     * Instantiates a new data batch insert exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchInsertException(String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, message);
    }

    /**
     * <code>DataBatchInsertException</code>
     * Instantiates a new data batch insert exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchInsertException(String resource, String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, resource, message);
    }

    /**
     * <code>DataBatchInsertException</code>
     * Instantiates a new data batch insert exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataBatchInsertException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_INSERT_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchInsertException get() {
        return new DataBatchInsertException();
    }
}
