package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.*;

/**
 * <code>DefaultSpliterator</code>
 * <p>The type default spliterator interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface DefaultSpliterator<T> {

    /**
     * <code>tryAdvance</code>
     * <p>The advance method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @return boolean <p>The advance return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException;

    /**
     * <code>forEachRemaining</code>
     * <p>The each remaining method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("StatementWithEmptyBody")
    default void forEachRemaining(ConsumerActuator<? super T> action) throws RestException {
        do {
        } while (tryAdvance(action));
    }

    /**
     * <code>trySplit</code>
     * <p>The split method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The split return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    DefaultSpliterator<T> trySplit() throws RestException;

    /**
     * <code>estimateSize</code>
     * <p>The size method.</p>
     * @return long <p>The size return object is <code>long</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    long estimateSize() throws RestException;

    /**
     * <code>getExactSizeIfKnown</code>
     * <p>The exact size if known getter method.</p>
     * @return long <p>The exact size if known return object is <code>long</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default long getExactSizeIfKnown() throws RestException {
        return (characteristics() & SIZED) == 0 ? -1L : estimateSize();
    }

    /**
     * <code>characteristics</code>
     * <p>The method.</p>
     * @return int <p>The return object is <code>int</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    int characteristics() throws RestException;

    /**
     * <code>hasCharacteristics</code>
     * <p>The characteristics method.</p>
     * @param characteristics int <p>The characteristics parameter is <code>int</code> type.</p>
     * @return boolean <p>The characteristics return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default boolean hasCharacteristics(int characteristics) throws RestException {
        return (characteristics() & characteristics) == characteristics;
    }

    /**
     * <code>getComparator</code>
     * <p>The comparator getter method.</p>
     * @return {@link java.util.Comparator} <p>The comparator return object is <code>Comparator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Comparator
     * @see io.github.nichetoolkit.rest.RestException
     */
    default Comparator<? super T> getComparator() throws RestException {
        throw new IllegalStateException();
    }

    /**
     * <code>ORDERED</code>
     * <p>The constant <code>ORDERED</code> field.</p>
     */
    int ORDERED = 0x00000010;

    /**
     * <code>DISTINCT</code>
     * <p>The constant <code>DISTINCT</code> field.</p>
     */
    int DISTINCT = 0x00000001;

    /**
     * <code>SORTED</code>
     * <p>The constant <code>SORTED</code> field.</p>
     */
    int SORTED = 0x00000004;

    /**
     * <code>SIZED</code>
     * <p>The constant <code>SIZED</code> field.</p>
     */
    int SIZED = 0x00000040;

    /**
     * <code>NONNULL</code>
     * <p>The constant <code>NONNULL</code> field.</p>
     */
    int NONNULL = 0x00000100;

    /**
     * <code>IMMUTABLE</code>
     * <p>The constant <code>IMMUTABLE</code> field.</p>
     */
    int IMMUTABLE = 0x00000400;

    /**
     * <code>CONCURRENT</code>
     * <p>The constant <code>CONCURRENT</code> field.</p>
     */
    int CONCURRENT = 0x00001000;

    /**
     * <code>SUBSIZED</code>
     * <p>The constant <code>SUBSIZED</code> field.</p>
     */
    int SUBSIZED = 0x00004000;

    /**
     * <code>OfPrimitive</code>
     * <p>The type of primitive interface.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_CONS>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    interface OfPrimitive<T, T_CONS, T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
            extends DefaultSpliterator<T> {
        @Override
        T_SPLITR trySplit() throws RestException;

        /**
         * <code>tryAdvance</code>
         * <p>The advance method.</p>
         * @param action T_CONS <p>The action parameter is <code>T_CONS</code> type.</p>
         * @return boolean <p>The advance return object is <code>boolean</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see java.lang.SuppressWarnings
         * @see io.github.nichetoolkit.rest.RestException
         */
        @SuppressWarnings("overloads")
        boolean tryAdvance(T_CONS action) throws RestException;

        /**
         * <code>forEachRemaining</code>
         * <p>The each remaining method.</p>
         * @param action T_CONS <p>The action parameter is <code>T_CONS</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see java.lang.SuppressWarnings
         * @see io.github.nichetoolkit.rest.RestException
         */
        @SuppressWarnings({"overloads","StatementWithEmptyBody"})
        default void forEachRemaining(T_CONS action) throws RestException {
            do {
            } while (tryAdvance(action));
        }
    }
}
