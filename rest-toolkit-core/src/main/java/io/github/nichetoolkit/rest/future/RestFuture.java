package io.github.nichetoolkit.rest.future;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.thread.ThreadInterruptedException;
import io.github.nichetoolkit.rest.error.thread.ThreadPointerEmptyException;
import io.github.nichetoolkit.rest.error.thread.ThreadTimeoutException;
import org.springframework.lang.NonNull;

import java.util.concurrent.*;

/**
 * <code>RestFuture</code>
 * <p>The rest future interface.</p>
 * @param <V> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.concurrent.Future
 * @since Jdk1.8
 */
public interface RestFuture<V> extends Future<V> {

    @Override
    default V get() throws InterruptedException, ExecutionException {
        try {
            return ofGet();
        } catch (RestException e) {
            if (e instanceof ThreadPointerEmptyException) {
                throw new NullPointerException(e.getMessage());
            } else if (e instanceof ThreadInterruptedException) {
                throw new InterruptedException(e.getMessage());
            }
            throw new ExecutionException(e.getMessage(), e.getCause());
        }
    }

    @Override
    default boolean cancel(boolean mayInterruptIfRunning) {
        try {
            return ofCancel(mayInterruptIfRunning);
        } catch (RestException e) {
            if (e instanceof ThreadPointerEmptyException) {
                throw new NullPointerException(e.getMessage());
            }
            throw new RestError(e);
        }
    }

    /**
     * <code>ofCancel</code>
     * <p>The of cancel method.</p>
     * @param mayInterruptIfRunning boolean <p>The may interrupt if running parameter is <code>boolean</code> type.</p>
     * @return boolean <p>The of cancel return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean ofCancel(boolean mayInterruptIfRunning) throws RestException;

    @Override
    default V get(long timeout, @NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            return ofGet(timeout, unit);
        } catch (RestException e) {
            if (e instanceof ThreadPointerEmptyException) {
                throw new NullPointerException(e.getMessage());
            } else if (e instanceof ThreadTimeoutException) {
                throw new TimeoutException(e.getMessage());
            } else if (e instanceof ThreadInterruptedException) {
                throw new InterruptedException(e.getMessage());
            }
            throw new ExecutionException(e.getMessage(), e.getCause());
        }
    }

    /**
     * <code>ofGet</code>
     * <p>The of get method.</p>
     * @return V <p>The of get return object is <code>V</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    V ofGet() throws RestException;

    /**
     * <code>ofGet</code>
     * <p>The of get method.</p>
     * @param timeout long <p>The timeout parameter is <code>long</code> type.</p>
     * @param unit    {@link java.util.concurrent.TimeUnit} <p>The unit parameter is <code>TimeUnit</code> type.</p>
     * @return V <p>The of get return object is <code>V</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.concurrent.TimeUnit
     * @see io.github.nichetoolkit.rest.RestException
     */
    V ofGet(long timeout, TimeUnit unit) throws RestException;
}
