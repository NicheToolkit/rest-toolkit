package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataSaveException</code>
 * <p>The type data save exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataSaveException extends DataErrorException {
    /**
     * <code>DataSaveException</code>
     * <p>Instantiates a new data save exception.</p>
     */
    public DataSaveException() {
        super(RestErrorStatus.DATA_SAVE_FAILED);
    }

    /**
     * <code>DataSaveException</code>
     * <p>Instantiates a new data save exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataSaveException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataSaveException</code>
     * <p>Instantiates a new data save exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataSaveException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>DataSaveException</code>
     * <p>Instantiates a new data save exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataSaveException(String resource) {
        super(RestErrorStatus.DATA_SAVE_FAILED, RestError.error(resource, RestErrorStatus.DATA_SAVE_FAILED));
    }

    /**
     * <code>DataSaveException</code>
     * <p>Instantiates a new data save exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataSaveException(String resource, String message) {
        super(RestErrorStatus.DATA_SAVE_FAILED, RestError.error(resource, RestErrorStatus.DATA_SAVE_FAILED, message));
    }

    @Override
    public DataSaveException get() {
        return new DataSaveException();
    }
}
