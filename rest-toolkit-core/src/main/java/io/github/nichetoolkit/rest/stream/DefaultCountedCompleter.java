package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.concurrent.*;

/**
 * <code>DefaultCountedCompleter</code>
 * <p>The default counted completer class.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.util.concurrent.CountedCompleter
 * @since Jdk1.8
 */
public abstract class DefaultCountedCompleter<T> extends CountedCompleter<T> {
    /**
     * <code>serialVersionUID</code>
     * <p>The constant <code>serialVersionUID</code> field.</p>
     */
    private static final long serialVersionUID = 5232453752276485070L;

    /**
     * <code>DefaultCountedCompleter</code>
     * <p>Instantiates a new default counted completer.</p>
     * @param completer           {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>The completer parameter is <code>DefaultCountedCompleter</code> type.</p>
     * @param initialPendingCount int <p>The initial pending count parameter is <code>int</code> type.</p>
     */
    protected DefaultCountedCompleter(DefaultCountedCompleter<?> completer,
                                      int initialPendingCount) {
       super(completer,initialPendingCount);
    }

    /**
     * <code>DefaultCountedCompleter</code>
     * <p>Instantiates a new default counted completer.</p>
     * @param completer {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>The completer parameter is <code>DefaultCountedCompleter</code> type.</p>
     */
    protected DefaultCountedCompleter(DefaultCountedCompleter<?> completer) {
        super(completer);
    }

    /**
     * <code>DefaultCountedCompleter</code>
     * <p>Instantiates a new default counted completer.</p>
     */
    protected DefaultCountedCompleter() {
        super();
    }

    /**
     * <code>computes</code>
     * <p>The computes method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
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
     * <p>The on completion method.</p>
     * @param caller {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>The caller parameter is <code>DefaultCountedCompleter</code> type.</p>
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
     * <p>The on computes method.</p>
     * @param caller {@link io.github.nichetoolkit.rest.stream.DefaultCountedCompleter} <p>The caller parameter is <code>DefaultCountedCompleter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {}

}
