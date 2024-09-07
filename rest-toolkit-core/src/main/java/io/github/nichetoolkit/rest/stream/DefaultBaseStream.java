package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Iterator;

interface DefaultBaseStream<T, S extends DefaultBaseStream<T, S>> extends AutoCloseable {

    Iterator<T> iterator() throws RestException;


    DefaultSpliterator<T> spliterator() throws RestException;


    boolean isParallel() throws RestException;


    S sequential() throws RestException;


    S parallel() throws RestException;


    S unordered() throws RestException;


    S onClose(Runnable closeHandler) throws RestException;


    void closed() throws RestException;

    @Override
    default void close() {
        try {
            closed();
        } catch (RestException e) {
            throw new RestError(e);
        }
    }
}
