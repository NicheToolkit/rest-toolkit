package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataBatchDeleteException</code>
 * <p>The type data batch delete exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataBatchDeleteException extends DataErrorException {
    /**
     * <code>DataBatchDeleteException</code>
     * Instantiates a new data batch delete exception.
     */
    public DataBatchDeleteException() {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED);
    }

    /**
     * <code>DataBatchDeleteException</code>
     * Instantiates a new data batch delete exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataBatchDeleteException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchDeleteException</code>
     * Instantiates a new data batch delete exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataBatchDeleteException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchDeleteException</code>
     * Instantiates a new data batch delete exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchDeleteException(String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, message);
    }

    /**
     * <code>DataBatchDeleteException</code>
     * Instantiates a new data batch delete exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchDeleteException(String resource, String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, resource, message);
    }

    /**
     * <code>DataBatchDeleteException</code>
     * Instantiates a new data batch delete exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataBatchDeleteException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_DELETE_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchDeleteException get() {
        return new DataBatchDeleteException();
    }
}

