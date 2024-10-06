package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Comparator;
import java.util.Objects;

/**
 * <code>DefaultStreams</code>
 * <p>The type default streams class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public final class DefaultStreams {
    /**
     * <code>DefaultStreams</code>
     * <p>Instantiates a new default streams.</p>
     */
    private DefaultStreams() {
        throw new Error("no instances");
    }

    /**
     * <code>NONE</code>
     * {@link java.lang.Object} <p>The <code>NONE</code> field.</p>
     * @see java.lang.Object
     */
    static final Object NONE = new Object();

    /**
     * <code>AbstractStreamBuilderImpl</code>
     * <p>The type abstract stream builder class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <S> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The generic parameter is <code>DefaultSpliterator</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @since Jdk1.8
     */
    private static abstract class AbstractStreamBuilderImpl<T, S extends DefaultSpliterator<T>> implements DefaultSpliterator<T> {

        /**
         * <code>count</code>
         * <p>The <code>count</code> field.</p>
         */
        int count;

        @Override
        public S trySplit() throws RestException {
            return null;
        }

        @Override
        public long estimateSize() {
            return -count - 1;
        }

        @Override
        public int characteristics() {
            return DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED |
                    DefaultSpliterator.ORDERED | DefaultSpliterator.IMMUTABLE;
        }
    }

    /**
     * <code>StreamBuilderImpl</code>
     * <p>The type stream builder class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultStreams.AbstractStreamBuilderImpl
     * @see io.github.nichetoolkit.rest.stream.RestStream.Builder
     * @since Jdk1.8
     */
    static final class StreamBuilderImpl<T>
            extends AbstractStreamBuilderImpl<T, DefaultSpliterator<T>>
            implements RestStream.Builder<T> {

        /**
         * <code>first</code>
         * <p>The <code>first</code> field.</p>
         */
        T first;

        /**
         * <code>buffer</code>
         * <p>The buffer field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpinedBuffer
         */
        DefaultSpinedBuffer<T> buffer;

        /**
         * <code>StreamBuilderImpl</code>
         * <p>Instantiates a new stream builder.</p>
         */
        StreamBuilderImpl() {
        }

        /**
         * <code>StreamBuilderImpl</code>
         * <p>Instantiates a new stream builder.</p>
         * @param t T <p>The t parameter is <code>T</code> type.</p>
         */
        StreamBuilderImpl(T t) {
            first = t;
            count = -2;
        }

        @Override
        public void actuate(T t) {
            if (count == 0) {
                first = t;
                count++;
            } else if (count > 0) {
                if (buffer == null) {
                    buffer = new DefaultSpinedBuffer<>();
                    buffer.actuate(first);
                    count++;
                }

                buffer.actuate(t);
            } else {
                throw new IllegalStateException();
            }
        }

        public RestStream.Builder<T> add(T t) {
            actuate(t);
            return this;
        }

        @Override
        public RestStream<T> build() throws RestException {
            int c = count;
            if (c >= 0) {
                count = -count - 1;
                return (c < 2) ? DefaultStreamSupport.stream(this, false) : DefaultStreamSupport.stream(buffer.spliterator(), false);
            }

            throw new IllegalStateException();
        }

        @Override
        public boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException {
            Objects.requireNonNull(action);

            if (count == -2) {
                action.actuate(first);
                count = -1;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void forEachRemaining(ConsumerActuator<? super T> action) throws RestException {
            Objects.requireNonNull(action);

            if (count == -2) {
                action.actuate(first);
                count = -1;
            }
        }
    }

    /**
     * <code>ConcatSpliterator</code>
     * <p>The type concat spliterator class.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The generic parameter is <code>DefaultSpliterator</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @since Jdk1.8
     */
    abstract static class ConcatSpliterator<T, T_SPLITR extends DefaultSpliterator<T>>
            implements DefaultSpliterator<T> {
        /**
         * <code>aSpliterator</code>
         * <p>The <code>aSpliterator</code> field.</p>
         */
        protected final T_SPLITR aSpliterator;
        /**
         * <code>bSpliterator</code>
         * <p>The <code>bSpliterator</code> field.</p>
         */
        protected final T_SPLITR bSpliterator;
        /**
         * <code>beforeSplit</code>
         * <p>The <code>beforeSplit</code> field.</p>
         */
// True when no split has occurred, otherwise false
        boolean beforeSplit;
        /**
         * <code>unsized</code>
         * <p>The <code>unsized</code> field.</p>
         */
// Never read after splitting
        final boolean unsized;

        /**
         * <code>ConcatSpliterator</code>
         * <p>Instantiates a new concat spliterator.</p>
         * @param aSpliterator T_SPLITR <p>The a spliterator parameter is <code>T_SPLITR</code> type.</p>
         * @param bSpliterator T_SPLITR <p>The b spliterator parameter is <code>T_SPLITR</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        public ConcatSpliterator(T_SPLITR aSpliterator, T_SPLITR bSpliterator) throws RestException {
            this.aSpliterator = aSpliterator;
            this.bSpliterator = bSpliterator;
            beforeSplit = true;
            // The spliterator is known to be unsized before splitting if the
            // sum of the estimates overflows.
            unsized = aSpliterator.estimateSize() + bSpliterator.estimateSize() < 0;
        }

        @Override
        public T_SPLITR trySplit() throws RestException {
            @SuppressWarnings("unchecked")
            T_SPLITR ret = beforeSplit ? aSpliterator : (T_SPLITR) bSpliterator.trySplit();
            beforeSplit = false;
            return ret;
        }

        @Override
        @SuppressWarnings("Duplicates")
        public boolean tryAdvance(ConsumerActuator<? super T> consumer) throws RestException {
            boolean hasNext;
            if (beforeSplit) {
                hasNext = aSpliterator.tryAdvance(consumer);
                if (!hasNext) {
                    beforeSplit = false;
                    hasNext = bSpliterator.tryAdvance(consumer);
                }
            } else
                hasNext = bSpliterator.tryAdvance(consumer);
            return hasNext;
        }

        @Override
        public void forEachRemaining(ConsumerActuator<? super T> consumer) throws RestException {
            if (beforeSplit)
                aSpliterator.forEachRemaining(consumer);
            bSpliterator.forEachRemaining(consumer);
        }

        @Override
        public long estimateSize() throws RestException {
            if (beforeSplit) {
                long size = aSpliterator.estimateSize() + bSpliterator.estimateSize();
                return (size >= 0) ? size : Long.MAX_VALUE;
            } else {
                return bSpliterator.estimateSize();
            }
        }

        @Override
        public int characteristics() throws RestException {
            if (beforeSplit) {
                return aSpliterator.characteristics() & bSpliterator.characteristics()
                        & ~(DefaultSpliterator.DISTINCT | DefaultSpliterator.SORTED
                        | (unsized ? DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED : 0));
            } else {
                return bSpliterator.characteristics();
            }
        }

        @Override
        public Comparator<? super T> getComparator() throws RestException {
            if (beforeSplit)
                throw new IllegalStateException();
            return bSpliterator.getComparator();
        }

        /**
         * <code>OfRef</code>
         * <p>The type of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @since Jdk1.8
         */
        static class OfRef<T> extends ConcatSpliterator<T, DefaultSpliterator<T>> {
            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param aSpliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The a spliterator parameter is <code>DefaultSpliterator</code> type.</p>
             * @param bSpliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The b spliterator parameter is <code>DefaultSpliterator</code> type.</p>
             * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
             * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
             * @see io.github.nichetoolkit.rest.RestException
             */
            OfRef(DefaultSpliterator<T> aSpliterator, DefaultSpliterator<T> bSpliterator) throws RestException {
                super(aSpliterator, bSpliterator);
            }
        }

        /**
         * <code>OfPrimitive</code>
         * <p>The type of primitive class.</p>
         * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T_CONS>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive
         * @since Jdk1.8
         */
        private static abstract class OfPrimitive<T, T_CONS, T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
                extends ConcatSpliterator<T, T_SPLITR>
                implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            /**
             * <code>OfPrimitive</code>
             * <p>Instantiates a new of primitive.</p>
             * @param aSpliterator T_SPLITR <p>The a spliterator parameter is <code>T_SPLITR</code> type.</p>
             * @param bSpliterator T_SPLITR <p>The b spliterator parameter is <code>T_SPLITR</code> type.</p>
             * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
             * @see io.github.nichetoolkit.rest.RestException
             */
            private OfPrimitive(T_SPLITR aSpliterator, T_SPLITR bSpliterator) throws RestException {
                super(aSpliterator, bSpliterator);
            }

            @Override
            @SuppressWarnings("Duplicates")
            public boolean tryAdvance(T_CONS action) throws RestException {
                boolean hasNext;
                if (beforeSplit) {
                    hasNext = aSpliterator.tryAdvance(action);
                    if (!hasNext) {
                        beforeSplit = false;
                        hasNext = bSpliterator.tryAdvance(action);
                    }
                } else
                    hasNext = bSpliterator.tryAdvance(action);
                return hasNext;
            }

            @Override
            public void forEachRemaining(T_CONS action) throws RestException {
                if (beforeSplit)
                    aSpliterator.forEachRemaining(action);
                bSpliterator.forEachRemaining(action);
            }
        }

    }

    /**
     * <code>composeWithExceptions</code>
     * <p>The with exceptions method.</p>
     * @param a {@link java.lang.Runnable} <p>The a parameter is <code>Runnable</code> type.</p>
     * @param b {@link java.lang.Runnable} <p>The b parameter is <code>Runnable</code> type.</p>
     * @return {@link java.lang.Runnable} <p>The with exceptions return object is <code>Runnable</code> type.</p>
     * @see java.lang.Runnable
     */
    static Runnable composeWithExceptions(Runnable a, Runnable b) {
        return () -> {
            try {
                a.run();
            } catch (Throwable e1) {
                try {
                    b.run();
                } catch (Throwable e2) {
                    try {
                        e1.addSuppressed(e2);
                    } catch (Throwable ignore) {
                    }
                }
                throw e1;
            }
            b.run();
        };
    }

    /**
     * <code>composedClose</code>
     * <p>The close method.</p>
     * @param a {@link io.github.nichetoolkit.rest.stream.DefaultBaseStream} <p>The a parameter is <code>DefaultBaseStream</code> type.</p>
     * @param b {@link io.github.nichetoolkit.rest.stream.DefaultBaseStream} <p>The b parameter is <code>DefaultBaseStream</code> type.</p>
     * @return {@link java.lang.Runnable} <p>The close return object is <code>Runnable</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultBaseStream
     * @see java.lang.Runnable
     * @see io.github.nichetoolkit.rest.RestException
     */
    static Runnable composedClose(DefaultBaseStream<?, ?> a, DefaultBaseStream<?, ?> b) throws RestException {
        return () -> {
            try {
                a.close();
            } catch (Throwable e1) {
                try {
                    b.close();
                } catch (Throwable e2) {
                    try {
                        e1.addSuppressed(e2);
                    } catch (Throwable ignore) {
                    }
                }
                throw e1;
            }
            b.close();
        };
    }
}
