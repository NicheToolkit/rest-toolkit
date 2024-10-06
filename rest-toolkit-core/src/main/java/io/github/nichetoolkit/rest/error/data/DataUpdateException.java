package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataUpdateException</code>
 * <p>The type data update exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataUpdateException extends DataErrorException {
    /**
     * <code>DataUpdateException</code>
     * <p>Instantiates a new data update exception.</p>
     */
    public DataUpdateException() {
        super(RestErrorStatus.DATA_UPDATE_FAILED);
    }

    /**
     * <code>DataUpdateException</code>
     * <p>Instantiates a new data update exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataUpdateException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataUpdateException</code>
     * <p>Instantiates a new data update exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataUpdateException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataUpdateException</code>
     * <p>Instantiates a new data update exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataUpdateException(String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, message);
    }

    /**
     * <code>DataUpdateException</code>
     * <p>Instantiates a new data update exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataUpdateException(String resource, String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, resource, message);
    }

    /**
     * <code>DataUpdateException</code>
     * <p>Instantiates a new data update exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataUpdateException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_UPDATE_FAILED, resource, field, value, message);
    }

    @Override
    public DataUpdateException get() {
        return new DataUpdateException();
    }
}