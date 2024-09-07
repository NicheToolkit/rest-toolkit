package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Comparator;
import java.util.Objects;

final class DefaultStreams {
    /* copy form jdk Streams  */
    private DefaultStreams() {
        throw new Error("no instances");
    }

    static final Object NONE = new Object();

    private static abstract class AbstractStreamBuilderImpl<T, S extends DefaultSpliterator<T>> implements DefaultSpliterator<T> {

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

    static final class StreamBuilderImpl<T>
            extends AbstractStreamBuilderImpl<T, DefaultSpliterator<T>>
            implements RestStream.Builder<T> {

        T first;

        DefaultSpinedBuffer<T> buffer;

        StreamBuilderImpl() {
        }

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

    abstract static class ConcatSpliterator<T, T_SPLITR extends DefaultSpliterator<T>>
            implements DefaultSpliterator<T> {
        protected final T_SPLITR aSpliterator;
        protected final T_SPLITR bSpliterator;
        // True when no split has occurred, otherwise false
        boolean beforeSplit;
        // Never read after splitting
        final boolean unsized;

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
                // If one or both estimates are Long.MAX_VALUE then the sum
                // will either be Long.MAX_VALUE or overflow to a negative value
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

        static class OfRef<T> extends ConcatSpliterator<T, DefaultSpliterator<T>> {
            OfRef(DefaultSpliterator<T> aSpliterator, DefaultSpliterator<T> bSpliterator) throws RestException {
                super(aSpliterator, bSpliterator);
            }
        }

        private static abstract class OfPrimitive<T, T_CONS, T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
                extends ConcatSpliterator<T, T_SPLITR>
                implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            private OfPrimitive(T_SPLITR aSpliterator, T_SPLITR bSpliterator) throws RestException {
                super(aSpliterator, bSpliterator);
            }

            @Override
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
