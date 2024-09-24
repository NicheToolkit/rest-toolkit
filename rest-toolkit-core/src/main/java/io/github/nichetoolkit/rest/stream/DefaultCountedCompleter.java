package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.concurrent.*;

/**
 * <code>DefaultCountedCompleter</code>
 * <p>The type default counted completer class.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.concurrent.CountedCompleter
 * @since Jdk1.8
 */
public abstract class DefaultCountedCompleter<T> extends CountedCompleter<T> {
    /**
     * <code>serialVersionUID</code>
     * <p>the constant <code>serialVersionUID</code> field.</p>
     */
    private static final long serialVersionUID = 5232453752276485070L;

    /**
     * <code>DefaultCountedCompleter</code>
     * Instantiates a new default counted completer.
     * @param completer           {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>the completer parameter is <code>DefaultCountedCompleter</code> type.</p>
     * @param initialPendingCount int <p>the initial pending count parameter is <code>int</code> type.</p>
     */
    protected DefaultCountedCompleter(DefaultCountedCompleter<?> completer,
                                      int initialPendingCount) {
       super(completer,initialPendingCount);
    }

    /**
     * <code>DefaultCountedCompleter</code>
     * Instantiates a new default counted completer.
     * @param completer {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>the completer parameter is <code>DefaultCountedCompleter</code> type.</p>
     */
    protected DefaultCountedCompleter(DefaultCountedCompleter<?> completer) {
        super(completer);
    }

    /**
     * <code>DefaultCountedCompleter</code>
     * Instantiates a new default counted completer.
     */
    protected DefaultCountedCompleter() {
        super();
    }

    /**
     * <code>computes</code>
     * <p>the method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public abstract void computes() throws RestException;

    public void compute() {
        try {
            computes();
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>onCompletion</code>
     * <p>the completion method.</p>
     * @param caller {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>the caller parameter is <code>DefaultCountedCompleter</code> type.</p>
     */
    public void onCompletion(DefaultCountedCompleter<?> caller) {
        try {
            onComputes(caller);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>onComputes</code>
     * <p>the computes method.</p>
     * @param caller {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>the caller parameter is <code>DefaultCountedCompleter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {}

}
