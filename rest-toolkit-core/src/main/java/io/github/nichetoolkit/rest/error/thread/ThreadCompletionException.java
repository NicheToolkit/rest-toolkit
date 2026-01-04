package io.github.nichetoolkit.rest.error.thread;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ThreadErrorException;

/**
 * <code>ThreadCompletionException</code>
 * <p>The thread completion exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ThreadErrorException
 * @since Jdk1.8
 */
public class ThreadCompletionException extends ThreadErrorException {

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     */
    public ThreadCompletionException() {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR);
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ThreadCompletionException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadCompletionException(String message) {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR, RestError.error(RestErrorStatus.THREAD_COMPLETION_ERROR,message));
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ThreadCompletionException(Throwable cause) {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR, RestError.error(RestErrorStatus.THREAD_COMPLETION_ERROR, cause), cause);
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadCompletionException(String message, Throwable cause) {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR, RestError.error(RestErrorStatus.THREAD_COMPLETION_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadCompletionException(String field, String message) {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR, RestError.error(field, RestErrorStatus.THREAD_COMPLETION_ERROR, message));
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadCompletionException(String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR, RestError.error(field, RestErrorStatus.THREAD_COMPLETION_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadCompletionException(String resource, String field, String message) {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_COMPLETION_ERROR, message));
    }

    /**
     * <code>ThreadCompletionException</code>
     * <p>Instantiates a new thread completion exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadCompletionException(String resource, String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_COMPLETION_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_COMPLETION_ERROR, message, cause), cause);
    }

    @Override
    public ThreadCompletionException get() {
        return new ThreadCompletionException();
    }
}
