package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataBatchQueryException</code>
 * <p>The data batch query exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataBatchQueryException extends DataErrorException {
    /**
     * <code>DataBatchQueryException</code>
     * <p>Instantiates a new data batch query exception.</p>
     */
    public DataBatchQueryException() {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED);
    }

    /**
     * <code>DataBatchQueryException</code>
     * <p>Instantiates a new data batch query exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataBatchQueryException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchQueryException</code>
     * <p>Instantiates a new data batch query exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataBatchQueryException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchQueryException</code>
     * <p>Instantiates a new data batch query exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchQueryException(String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, message);
    }

    /**
     * <code>DataBatchQueryException</code>
     * <p>Instantiates a new data batch query exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchQueryException(String resource, String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, resource, message);
    }

    /**
     * <code>DataBatchQueryException</code>
     * <p>Instantiates a new data batch query exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataBatchQueryException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_QUERY_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchQueryException get() {
        return new DataBatchQueryException();
    }
}
