package io.github.nichetoolkit.rest.error.thread;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ThreadErrorException;

/**
 * <code>ThreadCancellationException</code>
 * <p>The thread cancellation exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ThreadErrorException
 * @since Jdk1.8
 */
public class ThreadCancellationException extends ThreadErrorException {

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     */
    public ThreadCancellationException() {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR);
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ThreadCancellationException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadCancellationException(String message) {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR, RestError.error(RestErrorStatus.THREAD_CANCELLATION_ERROR,message));
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ThreadCancellationException(Throwable cause) {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR, RestError.error(RestErrorStatus.THREAD_CANCELLATION_ERROR, cause), cause);
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadCancellationException(String message, Throwable cause) {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR, RestError.error(RestErrorStatus.THREAD_CANCELLATION_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadCancellationException(String field, String message) {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR, RestError.error(field, RestErrorStatus.THREAD_CANCELLATION_ERROR, message));
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadCancellationException(String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR, RestError.error(field, RestErrorStatus.THREAD_CANCELLATION_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadCancellationException(String resource, String field, String message) {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_CANCELLATION_ERROR, message));
    }

    /**
     * <code>ThreadCancellationException</code>
     * <p>Instantiates a new thread cancellation exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadCancellationException(String resource, String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_CANCELLATION_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_CANCELLATION_ERROR, message, cause), cause);
    }

    @Override
    public ThreadCancellationException get() {
        return new ThreadCancellationException();
    }
}
