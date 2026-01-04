package io.github.nichetoolkit.rest.error.thread;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ThreadErrorException;

/**
 * <code>ThreadTimeoutException</code>
 * <p>The thread timeout exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ThreadErrorException
 * @since Jdk1.8
 */
public class ThreadTimeoutException extends ThreadErrorException {

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     */
    public ThreadTimeoutException() {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR);
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ThreadTimeoutException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadTimeoutException(String message) {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR, RestError.error(RestErrorStatus.THREAD_TIMEOUT_ERROR,message));
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.Throwable
     */
    public ThreadTimeoutException(Throwable cause) {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR, RestError.error(RestErrorStatus.THREAD_TIMEOUT_ERROR, cause), cause);
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadTimeoutException(String message, Throwable cause) {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR, RestError.error(RestErrorStatus.THREAD_TIMEOUT_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadTimeoutException(String field, String message) {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR, RestError.error(field, RestErrorStatus.THREAD_TIMEOUT_ERROR, message));
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param field   {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause   {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadTimeoutException(String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR, RestError.error(field, RestErrorStatus.THREAD_TIMEOUT_ERROR, message, cause), cause);
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ThreadTimeoutException(String resource, String field, String message) {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_TIMEOUT_ERROR, message));
    }

    /**
     * <code>ThreadTimeoutException</code>
     * <p>Instantiates a new thread timeout exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param field    {@link java.lang.String} <p>The field parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause    {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Throwable
     */
    public ThreadTimeoutException(String resource, String field, String message, Throwable cause) {
        super(RestErrorStatus.THREAD_TIMEOUT_ERROR, RestError.error(resource, field, RestErrorStatus.THREAD_TIMEOUT_ERROR, message, cause), cause);
    }

    @Override
    public ThreadTimeoutException get() {
        return new ThreadTimeoutException();
    }
}
