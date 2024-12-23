package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataBatchUpdateException</code>
 * <p>The data batch update exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataBatchUpdateException extends DataErrorException {
    /**
     * <code>DataBatchUpdateException</code>
     * <p>Instantiates a new data batch update exception.</p>
     */
    public DataBatchUpdateException() {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED);
    }

    /**
     * <code>DataBatchUpdateException</code>
     * <p>Instantiates a new data batch update exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataBatchUpdateException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchUpdateException</code>
     * <p>Instantiates a new data batch update exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataBatchUpdateException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataBatchUpdateException</code>
     * <p>Instantiates a new data batch update exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchUpdateException(String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, message);
    }

    /**
     * <code>DataBatchUpdateException</code>
     * <p>Instantiates a new data batch update exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataBatchUpdateException(String resource, String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, resource, message);
    }

    /**
     * <code>DataBatchUpdateException</code>
     * <p>Instantiates a new data batch update exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataBatchUpdateException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_UPDATE_ALL_FAILED, resource, field, value, message);
    }

    @Override
    public DataBatchUpdateException get() {
        return new DataBatchUpdateException();
    }
}
