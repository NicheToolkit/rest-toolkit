package io.github.nichetoolkit.rest.error.data;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.DataErrorException;

/**
 * <code>DataTransformException</code>
 * <p>The type data transform exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.DataErrorException
 * @since Jdk1.8
 */
public class DataTransformException extends DataErrorException {
    /**
     * <code>DataTransformException</code>
     * Instantiates a new data transform exception.
     */
    public DataTransformException() {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED);
    }

    /**
     * <code>DataTransformException</code>
     * Instantiates a new data transform exception.
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>the status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public DataTransformException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>DataTransformException</code>
     * Instantiates a new data transform exception.
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>the status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public DataTransformException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataTransformException</code>
     * Instantiates a new data transform exception.
     * @param message {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataTransformException(String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, message);
    }

    /**
     * <code>DataTransformException</code>
     * Instantiates a new data transform exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public DataTransformException(String resource, String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, resource, message);
    }

    /**
     * <code>DataTransformException</code>
     * Instantiates a new data transform exception.
     * @param resource {@link java.lang.String} <p>the resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>the field parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>the message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public DataTransformException(String resource, String field, Object value, String message) {
        super(RestErrorStatus.DATA_TRANSFORM_FAILED, resource, field, value, message);
    }

    @Override
    public DataTransformException get() {
        return new DataTransformException();
    }
}