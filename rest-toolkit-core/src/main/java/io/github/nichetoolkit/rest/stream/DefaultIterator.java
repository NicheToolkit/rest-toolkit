
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.*;

public interface DefaultIterator<E> extends Iterator<E> {

    boolean hasNexts() throws RestException;

    default boolean hasNext() {
        try {
            return hasNexts();
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    E nexts() throws RestException;

    default E next() {
        try {
            return nexts();
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(ConsumerActuator<? super E> action) throws RestException {
        Objects.requireNonNull(action);
        while (hasNexts())
            action.actuate(nexts());
    }
}
