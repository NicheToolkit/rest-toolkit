package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataDeleteException</code>
 * <p>The data delete exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataDeleteException extends DataErrorException {
    /**
     * <code>DataDeleteException</code>
     * <p>Instantiates a new data delete exception.</p>
     */
    public DataDeleteException() {
        super(RestErrorStatus.DATA_DELETE_FAILED);
    }

    /**
     * <code>DataDeleteException</code>
     * <p>Instantiates a new data delete exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataDeleteException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataDeleteException</code>
     * <p>Instantiates a new data delete exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataDeleteException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataDeleteException</code>
     * <p>Instantiates a new data delete exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataDeleteException(String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, message);
    }

    /**
     * <code>DataDeleteException</code>
     * <p>Instantiates a new data delete exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataDeleteException(String resource, String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, resource, message);
    }

    /**
     * <code>DataDeleteException</code>
     * <p>Instantiates a new data delete exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataDeleteException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_DELETE_FAILED, resource, field, value, message);
    }


    @Override
    public DataDeleteException get() {
        return new DataDeleteException();
    }
}
