package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.*;

interface DefaultSpliterator<T> {

    boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException;

    default void forEachRemaining(ConsumerActuator<? super T> action) throws RestException {
        do {
        } while (tryAdvance(action));
    }

    DefaultSpliterator<T> trySplit() throws RestException;

    long estimateSize() throws RestException;

    default long getExactSizeIfKnown() throws RestException {
        return (characteristics() & SIZED) == 0 ? -1L : estimateSize();
    }

    int characteristics() throws RestException;

    default boolean hasCharacteristics(int characteristics) throws RestException {
        return (characteristics() & characteristics) == characteristics;
    }

    default Comparator<? super T> getComparator() throws RestException {
        throw new IllegalStateException();
    }

    int ORDERED = 0x00000010;

    int DISTINCT = 0x00000001;

    int SORTED = 0x00000004;

    int SIZED = 0x00000040;

    int NONNULL = 0x00000100;

    int IMMUTABLE = 0x00000400;

    int CONCURRENT = 0x00001000;

    int SUBSIZED = 0x00004000;

    interface OfPrimitive<T, T_CONS, T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
            extends DefaultSpliterator<T> {
        @Override
        T_SPLITR trySplit() throws RestException;

        @SuppressWarnings("overloads")
        boolean tryAdvance(T_CONS action) throws RestException;

        @SuppressWarnings("overloads")
        default void forEachRemaining(T_CONS action) throws RestException {
            do {
            } while (tryAdvance(action));
        }
    }
}
