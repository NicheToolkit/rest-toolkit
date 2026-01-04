package io.github.nichetoolkit.rest.error.thread;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ThreadErrorException;

/**
 * <code>ThreadPointerEmptyException</code>
 * <p>The thread pointer empty exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ThreadErrorException
 * @since Jdk1.8
 */
public class ThreadPointerEmptyException extends ThreadErrorException {

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     */
    public ThreadPointerEmptyException() {
        super(RestErrorStatus.THREAD_POINTER_EMPTY);
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ThreadPointerEmptyException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadPointerEmptyException(String message) {
        super(RestErrorStatus.THREAD_POINTER_EMPTY, RestError.error(RestErrorStatus.THREAD_POINTER_EMPTY,message));
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ThreadPointerEmptyException(Throwable cause) {
        super(RestErrorStatus.THREAD_POINTER_EMPTY, RestError.error(RestErrorStatus.THREAD_POINTER_EMPTY, cause), cause);
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadPointerEmptyException(String message, Throwable cause) {
        super(RestErrorStatus.THREAD_POINTER_EMPTY, RestError.error(RestErrorStatus.THREAD_POINTER_EMPTY, message, cause), cause);
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadPointerEmptyException(String field, String message) {
        super(RestErrorStatus.THREAD_POINTER_EMPTY, RestError.error(field, RestErrorStatus.THREAD_POINTER_EMPTY, message));
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadPointerEmptyException(String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_POINTER_EMPTY, RestError.error(field, RestErrorStatus.THREAD_POINTER_EMPTY, message, cause), cause);
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadPointerEmptyException(String resource, String field, String message) {
        super(RestErrorStatus.THREAD_POINTER_EMPTY, RestError.error(resource, field, RestErrorStatus.THREAD_POINTER_EMPTY, message));
    }

    /**
     * <code>ThreadPointerEmptyException</code>
     * <p>Instantiates a new thread pointer empty exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadPointerEmptyException(String resource, String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_POINTER_EMPTY, RestError.error(resource, field, RestErrorStatus.THREAD_POINTER_EMPTY, message, cause), cause);
    }

    @Override
    public ThreadPointerEmptyException get() {
        return new ThreadPointerEmptyException();
    }
}
