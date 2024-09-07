
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.concurrent.*;

public abstract class DefaultCountedCompleter<T> extends CountedCompleter<T> {
    private static final long serialVersionUID = 5232453752276485070L;

    protected DefaultCountedCompleter(DefaultCountedCompleter<?> completer,
                                      int initialPendingCount) {
       super(completer,initialPendingCount);
    }

    protected DefaultCountedCompleter(DefaultCountedCompleter<?> completer) {
        super(completer);
    }

    protected DefaultCountedCompleter() {
        super();
    }

    public abstract void computes() throws RestException;

    public void compute() {
        try {
            computes();
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    public void onCompletion(DefaultCountedCompleter<?> caller) {
        try {
            onComputes(caller);
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {}

}
