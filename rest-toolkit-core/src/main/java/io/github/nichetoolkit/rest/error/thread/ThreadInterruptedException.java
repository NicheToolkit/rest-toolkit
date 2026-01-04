package io.github.nichetoolkit.rest.error.thread;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ThreadErrorException;

/**
 * <code>ThreadInterruptedException</code>
 * <p>The thread interrupted exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ThreadErrorException
 * @since Jdk1.8
 */
public class ThreadInterruptedException extends ThreadErrorException {

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     */
    public ThreadInterruptedException() {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR);
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ThreadInterruptedException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadInterruptedException(String message) {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR, RestError.error(RestErrorStatus.THREAD_INTERRUPTED_ERROR,message));
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ThreadInterruptedException(Throwable cause) {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR, RestError.error(RestErrorStatus.THREAD_INTERRUPTED_ERROR, cause), cause);
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadInterruptedException(String message, Throwable cause) {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR, RestError.error(RestErrorStatus.THREAD_INTERRUPTED_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadInterruptedException(String field, String message) {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR, RestError.error(field, RestErrorStatus.THREAD_INTERRUPTED_ERROR, message));
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadInterruptedException(String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR, RestError.error(field, RestErrorStatus.THREAD_INTERRUPTED_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadInterruptedException(String resource, String field, String message) {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_INTERRUPTED_ERROR, message));
    }

    /**
     * <code>ThreadInterruptedException</code>
     * <p>Instantiates a new thread interrupted exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadInterruptedException(String resource, String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_INTERRUPTED_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_INTERRUPTED_ERROR, message, cause), cause);
    }

    @Override
    public ThreadInterruptedException get() {
        return new ThreadInterruptedException();
    }
}
