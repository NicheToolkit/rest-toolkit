package io.github.nichetoolkit.rest.error.natives;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorException;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>IoStreamErrorException</code>
 * <p>The type io stream error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestErrorException
 * @since Jdk1.8
 */
public class IoStreamErrorException extends RestErrorException {

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     */
    public IoStreamErrorException() {
        super(RestErrorStatus.IO_STREAM_ERROR);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestErrorStatus} <p>The status parameter is <code>RestErrorStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestErrorStatus
     */
    public IoStreamErrorException(RestErrorStatus status) {
        super(status);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamErrorException(String error) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(RestErrorStatus.IO_STREAM_ERROR, error));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param error {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(String error, Throwable cause) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(RestErrorStatus.IO_STREAM_ERROR, error, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public IoStreamErrorException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(RestStatus status, Throwable cause) {
        super(status, RestError.error(status, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     */
    public IoStreamErrorException(RestStatus status, RestError error) {
        super(status, error);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param error  {@link io.github.nichetoolkit.rest.RestError} <p>The error parameter is <code>RestError</code> type.</p>
     * @param cause  {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see io.github.nichetoolkit.rest.RestError
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(RestStatus status, RestError error, Throwable cause) {
        super(status, error, cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public IoStreamErrorException(RestStatus status, String message) {
        super(status, RestError.error(status, message));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status  {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(RestStatus status, String message, Throwable cause) {
        super(status, RestError.error(status, message, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public IoStreamErrorException(RestStatus status, String resource, String message) {
        super(status, RestError.error(resource, status, message));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(RestStatus status, String resource, String message, Throwable cause) {
        super(status, RestError.error(resource, status, message, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     */
    public IoStreamErrorException(RestStatus status, String resource, String filed, String message) {
        super(status, RestError.error(resource, filed, status, message));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(RestStatus status, String resource, String filed, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, status, message, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     */
    public IoStreamErrorException(RestStatus status, String resource, String filed, Object value, String message) {
        super(status, RestError.error(resource, filed, value, status, message));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param status   {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(RestStatus status, String resource, String filed, Object value, String message, Throwable cause) {
        super(status, RestError.error(resource, filed, value, status, message, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamErrorException(String resource, String error) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(resource, RestErrorStatus.IO_STREAM_ERROR, error));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(String resource, String error, Throwable cause) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(resource, RestErrorStatus.IO_STREAM_ERROR, error, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IoStreamErrorException(String resource, String filed, String error) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(resource, filed, RestErrorStatus.IO_STREAM_ERROR, error));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(String resource, String filed, String error, Throwable cause) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(resource, filed, RestErrorStatus.IO_STREAM_ERROR, error, cause), cause);
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public IoStreamErrorException(String resource, String filed, Object value, String error) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(resource, filed, value, RestErrorStatus.IO_STREAM_ERROR, error));
    }

    /**
     * <code>IoStreamErrorException</code>
     * <p>Instantiates a new io stream error exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param filed    {@link java.lang.String} <p>The filed parameter is <code>String</code> type.</p>
     * @param value    {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
     * @param error    {@link java.lang.String} <p>The error parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     * @see java.lang.Throwable
     */
    public IoStreamErrorException(String resource, String filed, Object value, String error, Throwable cause) {
        super(RestErrorStatus.IO_STREAM_ERROR, RestError.error(resource, filed, value, RestErrorStatus.IO_STREAM_ERROR, error, cause));
    }

    @Override
    public IoStreamErrorException get() {
        return new IoStreamErrorException();
    }
}

