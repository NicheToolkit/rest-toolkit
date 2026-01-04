package io.github.nichetoolkit.rest.error.thread;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ThreadErrorException;

/**
 * <code>ThreadExecutionException</code>
 * <p>The thread execution exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ThreadErrorException
 * @since Jdk1.8
 */
public class ThreadExecutionException extends ThreadErrorException {

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     */
    public ThreadExecutionException() {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR);
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ThreadExecutionException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadExecutionException(String message) {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR, RestError.error(RestErrorStatus.THREAD_EXECUTION_ERROR,message));
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ThreadExecutionException(Throwable cause) {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR, RestError.error(RestErrorStatus.THREAD_EXECUTION_ERROR, cause), cause);
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadExecutionException(String message, Throwable cause) {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR, RestError.error(RestErrorStatus.THREAD_EXECUTION_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadExecutionException(String field, String message) {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR, RestError.error(field, RestErrorStatus.THREAD_EXECUTION_ERROR, message));
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadExecutionException(String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR, RestError.error(field, RestErrorStatus.THREAD_EXECUTION_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadExecutionException(String resource, String field, String message) {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_EXECUTION_ERROR, message));
    }

    /**
     * <code>ThreadExecutionException</code>
     * <p>Instantiates a new thread execution exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadExecutionException(String resource, String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_EXECUTION_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_EXECUTION_ERROR, message, cause), cause);
    }

    @Override
    public ThreadExecutionException get() {
        return new ThreadExecutionException();
    }
}
