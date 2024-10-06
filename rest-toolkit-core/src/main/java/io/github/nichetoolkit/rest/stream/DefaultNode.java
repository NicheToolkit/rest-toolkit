package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.function.*;

/**
 * <code>DefaultNode</code>
 * <p>The type default node interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface DefaultNode<T> {

    /**
     * <code>spliterator</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.RestException
     */
    DefaultSpliterator<T> spliterator() throws RestException;

    /**
     * <code>forEach</code>
     * <p>The each method.</p>
     * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    void forEach(ConsumerActuator<? super T> consumer) throws RestException;

    /**
     * <code>getChildCount</code>
     * <p>The child count getter method.</p>
     * @return int <p>The child count return object is <code>int</code> type.</p>
     */
    default int getChildCount() {
        return 0;
    }

    /**
     * <code>getChild</code>
     * <p>The child getter method.</p>
     * @param i int <p>The parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The child return object is <code>DefaultNode</code> type.</p>
     */
    default DefaultNode<T> getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * <code>truncate</code>
     * <p>The method.</p>
     * @param from      long <p>The from parameter is <code>long</code> type.</p>
     * @param to        long <p>The to parameter is <code>long</code> type.</p>
     * @param generator {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The return object is <code>DefaultNode</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.IntFunction
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @SuppressWarnings("StatementWithEmptyBody")
    default DefaultNode<T> truncate(long from, long to, IntFunction<T[]> generator) throws RestException {
        if (from == 0 && to == count())
            return this;
        DefaultSpliterator<T> spliterator = spliterator();
        long size = to - from;
        DefaultNode.Builder<T> nodeBuilder = DefaultNodes.builder(size, generator);
        nodeBuilder.begin(size);
        for (int i = 0; i < from && spliterator.tryAdvance(e -> {
        }); i++) {
        }
        for (int i = 0; (i < size) && spliterator.tryAdvance(nodeBuilder); i++) {
        }
        nodeBuilder.end();
        return nodeBuilder.build();
    }

    /**
     * <code>asArray</code>
     * <p>The array method.</p>
     * @param generator {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return T <p>The array return object is <code>T</code> type.</p>
     * @see java.util.function.IntFunction
     */
    T[] asArray(IntFunction<T[]> generator);

    /**
     * <code>copyInto</code>
     * <p>The into method.</p>
     * @param array  T <p>The array parameter is <code>T</code> type.</p>
     * @param offset int <p>The offset parameter is <code>int</code> type.</p>
     */
    void copyInto(T[] array, int offset);

    /**
     * <code>getShape</code>
     * <p>The shape getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>The shape return object is <code>DefaultStreamShape</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
     */
    default DefaultStreamShape getShape() {
        return DefaultStreamShape.REFERENCE;
    }

    /**
     * <code>count</code>
     * <p>The method.</p>
     * @return long <p>The return object is <code>long</code> type.</p>
     */
    long count();

    /**
     * <code>Builder</code>
     * <p>The type builder interface.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @since Jdk1.8
     */
    interface Builder<T> extends DefaultSink<T> {

        /**
         * <code>build</code>
         * <p>The method.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The return object is <code>DefaultNode</code> type.</p>
         */
        DefaultNode<T> build();

    }

    /**
     * <code>OfPrimitive</code>
     * <p>The type of primitive interface.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_CONS>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_ARR>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
     * @param <T_NODE>   {@link io.github.nichetoolkit.rest.stream.DefaultNode.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive
     * @since Jdk1.8
     */
    interface OfPrimitive<T, T_CONS, T_ARR,
            T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
            T_NODE extends OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
            extends DefaultNode<T> {

        @Override
        T_SPLITR spliterator();

        /**
         * <code>forEach</code>
         * <p>The each method.</p>
         * @param action T_CONS <p>The action parameter is <code>T_CONS</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see java.lang.SuppressWarnings
         * @see io.github.nichetoolkit.rest.RestException
         */
        @SuppressWarnings("overloads")
        void forEach(T_CONS action) throws RestException;

        @Override
        default T_NODE getChild(int i) {
            throw new IndexOutOfBoundsException();
        }

        T_NODE truncate(long from, long to, IntFunction<T[]> generator);

        @Override
        default T[] asArray(IntFunction<T[]> generator) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Node.OfPrimitive.asArray");

            long size = count();
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            T[] boxed = generator.apply((int) count());
            copyInto(boxed, 0);
            return boxed;
        }

        /**
         * <code>asPrimitiveArray</code>
         * <p>The primitive array method.</p>
         * @return T_ARR <p>The primitive array return object is <code>T_ARR</code> type.</p>
         */
        T_ARR asPrimitiveArray();

        /**
         * <code>newArray</code>
         * <p>The array method.</p>
         * @param count int <p>The count parameter is <code>int</code> type.</p>
         * @return T_ARR <p>The array return object is <code>T_ARR</code> type.</p>
         */
        T_ARR newArray(int count);

        /**
         * <code>copyInto</code>
         * <p>The into method.</p>
         * @param array  T_ARR <p>The array parameter is <code>T_ARR</code> type.</p>
         * @param offset int <p>The offset parameter is <code>int</code> type.</p>
         */
        void copyInto(T_ARR array, int offset);
    }
}
