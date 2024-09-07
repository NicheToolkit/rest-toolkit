
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Iterator;
import java.util.Objects;

interface DefaultIterable<T> {

    Iterator<T> iterator() throws RestException;

    default void forEach(ConsumerActuator<? super T> action) throws RestException {
        Objects.requireNonNull(action);
        for (Iterator<T> it = iterator(); it.hasNext(); ) {
            T t = it.next();
            action.actuate(t);
        }
    }

    default DefaultSpliterator<T> spliterator() throws RestException {
        return DefaultSpliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
