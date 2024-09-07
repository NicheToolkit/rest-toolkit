
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class DefaultStreamSpliterators {

    private static abstract class AbstractWrappingSpliterator<P_IN, P_OUT,
                                                              T_BUFFER extends DefaultAbstractSpinedBuffer>
            implements DefaultSpliterator<P_OUT> {
        final boolean isParallel;

        final DefaultPipelineHelper<P_OUT> ph;

        private SupplierActuator<DefaultSpliterator<P_IN>> spliteratorSupplier;

        DefaultSpliterator<P_IN> spliterator;

        DefaultSink<P_IN> bufferDefaultSink;

        DefaultBooleanSupplier pusher;

        long nextToConsume;

        T_BUFFER buffer;

        boolean finished;

        AbstractWrappingSpliterator(DefaultPipelineHelper<P_OUT> ph,
                                    SupplierActuator<DefaultSpliterator<P_IN>> spliteratorSupplier,
                                    boolean parallel) {
            this.ph = ph;
            this.spliteratorSupplier = spliteratorSupplier;
            this.spliterator = null;
            this.isParallel = parallel;
        }

        AbstractWrappingSpliterator(DefaultPipelineHelper<P_OUT> ph,
                                    DefaultSpliterator<P_IN> spliterator,
                                    boolean parallel) {
            this.ph = ph;
            this.spliteratorSupplier = null;
            this.spliterator = spliterator;
            this.isParallel = parallel;
        }

        final void init() throws RestException {
            if (spliterator == null) {
                spliterator = spliteratorSupplier.actuate();
                spliteratorSupplier = null;
            }
        }

        final boolean doAdvance() throws RestException {
            if (buffer == null) {
                if (finished)
                    return false;

                init();
                initPartialTraversalState();
                nextToConsume = 0;
                bufferDefaultSink.begin(spliterator.getExactSizeIfKnown());
                return fillBuffer();
            }
            else {
                ++nextToConsume;
                boolean hasNext = nextToConsume < buffer.count();
                if (!hasNext) {
                    nextToConsume = 0;
                    buffer.clear();
                    hasNext = fillBuffer();
                }
                return hasNext;
            }
        }

        abstract AbstractWrappingSpliterator<P_IN, P_OUT, ?> wrap(DefaultSpliterator<P_IN> s);

        abstract void initPartialTraversalState() throws RestException;

        @Override
        public DefaultSpliterator<P_OUT> trySplit() throws RestException {
            if (isParallel && !finished) {
                init();

                DefaultSpliterator<P_IN> split = spliterator.trySplit();
                return (split == null) ? null : wrap(split);
            }
            else
                return null;
        }

        private boolean fillBuffer() throws RestException {
            while (buffer.count() == 0) {
                if (bufferDefaultSink.cancellationRequested() || !pusher.actuate()) {
                    if (finished)
                        return false;
                    else {
                        bufferDefaultSink.end(); // might trigger more elements
                        finished = true;
                    }
                }
            }
            return true;
        }

        @Override
        public final long estimateSize() throws RestException {
            init();
            // Use the estimate of the wrapped spliterator
            // Note this may not be accurate if there are filter/flatMap
            // operations filtering or adding elements to the stream
            return spliterator.estimateSize();
        }

        @Override
        public final long getExactSizeIfKnown() throws RestException {
            init();
            return DefaultStreamOpFlag.SIZED.isKnown(ph.getStreamAndOpFlags())
                   ? spliterator.getExactSizeIfKnown()
                   : -1;
        }

        @Override
        public final int characteristics() throws RestException {
            init();

            // Get the characteristics from the pipeline
            int c = DefaultStreamOpFlag.toCharacteristics(DefaultStreamOpFlag.toStreamFlags(ph.getStreamAndOpFlags()));

            // Mask off the size and uniform characteristics and replace with
            // those of the spliterator
            // Note that a non-uniform spliterator can change from something
            // with an exact size to an estimate for a sub-split, for example
            // with HashSet where the size is known at the top level spliterator
            // but for sub-splits only an estimate is known
            if ((c & DefaultSpliterator.SIZED) != 0) {
                c &= ~(DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED);
                c |= (spliterator.characteristics() & (DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED));
            }

            return c;
        }

        @Override
        public Comparator<? super P_OUT> getComparator() throws RestException {
            if (!hasCharacteristics(DefaultSpliterator.SORTED))
                throw new IllegalStateException();
            return null;
        }

        @Override
        public final String toString() {
            return String.format("%s[%s]", getClass().getName(), spliterator);
        }
    }

    static final class WrappingSpliterator<P_IN, P_OUT>
            extends AbstractWrappingSpliterator<P_IN, P_OUT, DefaultSpinedBuffer<P_OUT>> {

        WrappingSpliterator(DefaultPipelineHelper<P_OUT> ph,
                            SupplierActuator<DefaultSpliterator<P_IN>> supplier,
                            boolean parallel) {
            super(ph, supplier, parallel);
        }

        WrappingSpliterator(DefaultPipelineHelper<P_OUT> ph,
                            DefaultSpliterator<P_IN> spliterator,
                            boolean parallel) {
            super(ph, spliterator, parallel);
        }

        @Override
        WrappingSpliterator<P_IN, P_OUT> wrap(DefaultSpliterator<P_IN> s) {
            return new WrappingSpliterator<>(ph, s, isParallel);
        }

        @Override
        void initPartialTraversalState() throws RestException {
            DefaultSpinedBuffer<P_OUT> b = new DefaultSpinedBuffer<>();
            buffer = b;
            bufferDefaultSink = ph.wrapSink(b::actuate);
            pusher = () -> spliterator.tryAdvance(bufferDefaultSink);
        }

        @Override
        public boolean tryAdvance(ConsumerActuator<? super P_OUT> consumer) throws RestException {
            Objects.requireNonNull(consumer);
            boolean hasNext = doAdvance();
            if (hasNext)
                consumer.actuate(buffer.get(nextToConsume));
            return hasNext;
        }

        @Override
        public void forEachRemaining(ConsumerActuator<? super P_OUT> consumer) throws RestException {
            if (buffer == null && !finished) {
                Objects.requireNonNull(consumer);
                init();
                ph.wrapAndCopyInto(consumer::actuate, spliterator);
                finished = true;
            }
            else {
                do { } while (tryAdvance(consumer));
            }
        }
    }

    static class DelegatingSpliterator<T, T_SPLITR extends DefaultSpliterator<T>>
            implements DefaultSpliterator<T> {
        private final SupplierActuator<? extends T_SPLITR> supplier;

        private T_SPLITR s;

        DelegatingSpliterator(SupplierActuator<? extends T_SPLITR> supplier) {
            this.supplier = supplier;
        }

        T_SPLITR actuate() throws RestException {
            if (s == null) {
                s = supplier.actuate();
            }
            return s;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T_SPLITR trySplit() throws RestException {
            return (T_SPLITR) actuate().trySplit();
        }

        @Override
        public boolean tryAdvance(ConsumerActuator<? super T> consumer) throws RestException {
            return actuate().tryAdvance(consumer);
        }

        @Override
        public void forEachRemaining(ConsumerActuator<? super T> consumer) throws RestException {
            actuate().forEachRemaining(consumer);
        }

        @Override
        public long estimateSize() throws RestException {
            return actuate().estimateSize();
        }

        @Override
        public int characteristics() throws RestException {
            return actuate().characteristics();
        }

        @Override
        public Comparator<? super T> getComparator() throws RestException {
            return actuate().getComparator();
        }

        @Override
        public long getExactSizeIfKnown() throws RestException {
            return actuate().getExactSizeIfKnown();
        }

        @Override
        public String toString() {
            try {
                return getClass().getName() + "[" + actuate() + "]";
            } catch (RestException e) {
                throw new RestError(e);
            }
        }

        static class OfPrimitive<T, T_CONS, T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
            extends DelegatingSpliterator<T, T_SPLITR>
            implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            OfPrimitive(SupplierActuator<? extends T_SPLITR> supplier) {
                super(supplier);
            }

            @Override
            public boolean tryAdvance(T_CONS consumer) throws RestException {
                return actuate().tryAdvance(consumer);
            }

            @Override
            public void forEachRemaining(T_CONS consumer) throws RestException {
                actuate().forEachRemaining(consumer);
            }
        }
    }

    static abstract class SliceSpliterator<T, T_SPLITR extends DefaultSpliterator<T>> {
        final long sliceOrigin;
        final long sliceFence;

        T_SPLITR s;
        long index;
        long fence;

        SliceSpliterator(T_SPLITR s, long sliceOrigin, long sliceFence, long origin, long fence) throws RestException {
            assert s.hasCharacteristics(DefaultSpliterator.SUBSIZED);
            this.s = s;
            this.sliceOrigin = sliceOrigin;
            this.sliceFence = sliceFence;
            this.index = origin;
            this.fence = fence;
        }

        protected abstract T_SPLITR makeSpliterator(T_SPLITR s, long sliceOrigin, long sliceFence, long origin, long fence) throws RestException;

        public T_SPLITR trySplit() throws RestException {
            if (sliceOrigin >= fence)
                return null;

            if (index >= fence)
                return null;
            while (true) {
                @SuppressWarnings("unchecked")
                T_SPLITR leftSplit = (T_SPLITR) s.trySplit();
                if (leftSplit == null)
                    return null;

                long leftSplitFenceUnbounded = index + leftSplit.estimateSize();
                long leftSplitFence = Math.min(leftSplitFenceUnbounded, sliceFence);
                if (sliceOrigin >= leftSplitFence) {
                    index = leftSplitFence;
                }
                else if (leftSplitFence >= sliceFence) {
                    s = leftSplit;
                    fence = leftSplitFence;
                }
                else if (index >= sliceOrigin) {
                    index = leftSplitFence;
                    return leftSplit;
                } else {
                    return makeSpliterator(leftSplit, sliceOrigin, sliceFence, index, index = leftSplitFence);
                }
            }
        }

        public long estimateSize() {
            return (sliceOrigin < fence)
                   ? fence - Math.max(sliceOrigin, index) : 0;
        }

        public int characteristics() throws RestException {
            return s.characteristics();
        }

        static final class OfRef<T>
                extends SliceSpliterator<T, DefaultSpliterator<T>>
                implements DefaultSpliterator<T> {

            OfRef(DefaultSpliterator<T> s, long sliceOrigin, long sliceFence) throws RestException {
                this(s, sliceOrigin, sliceFence, 0, Math.min(s.estimateSize(), sliceFence));
            }

            private OfRef(DefaultSpliterator<T> s,
                          long sliceOrigin, long sliceFence, long origin, long fence) throws RestException {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected DefaultSpliterator<T> makeSpliterator(DefaultSpliterator<T> s,
                                                     long sliceOrigin, long sliceFence,
                                                     long origin, long fence) throws RestException {
                return new DefaultStreamSpliterators.SliceSpliterator.OfRef<>(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            public boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException {
                Objects.requireNonNull(action);

                if (sliceOrigin >= fence)
                    return false;

                while (sliceOrigin > index) {
                    s.tryAdvance(e -> {});
                    index++;
                }

                if (index >= fence)
                    return false;

                index++;
                return s.tryAdvance(action);
            }

            @Override
            public void forEachRemaining(ConsumerActuator<? super T> action) throws RestException {
                Objects.requireNonNull(action);

                if (sliceOrigin >= fence)
                    return;

                if (index >= fence)
                    return;

                if (index >= sliceOrigin && (index + s.estimateSize()) <= sliceFence) {
                    // The spliterator is contained within the slice
                    s.forEachRemaining(action);
                    index = fence;
                } else {
                    // The spliterator intersects with the slice
                    while (sliceOrigin > index) {
                        s.tryAdvance(e -> {});
                        index++;
                    }
                    // Traverse elements up to the fence
                    for (;index < fence; index++) {
                        s.tryAdvance(action);
                    }
                }
            }
        }

        static abstract class OfPrimitive<T,
                T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
                T_CONS>
                extends SliceSpliterator<T, T_SPLITR>
                implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {

            OfPrimitive(T_SPLITR s, long sliceOrigin, long sliceFence) throws RestException {
                this(s, sliceOrigin, sliceFence, 0, Math.min(s.estimateSize(), sliceFence));
            }

            private OfPrimitive(T_SPLITR s,
                                long sliceOrigin, long sliceFence, long origin, long fence) throws RestException {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            public boolean tryAdvance(T_CONS action) throws RestException {
                Objects.requireNonNull(action);

                if (sliceOrigin >= fence)
                    return false;

                while (sliceOrigin > index) {
                    s.tryAdvance(emptyConsumer());
                    index++;
                }

                if (index >= fence)
                    return false;

                index++;
                return s.tryAdvance(action);
            }

            @Override
            public void forEachRemaining(T_CONS action) throws RestException {
                Objects.requireNonNull(action);

                if (sliceOrigin >= fence)
                    return;

                if (index >= fence)
                    return;

                if (index >= sliceOrigin && (index + s.estimateSize()) <= sliceFence) {
                    // The spliterator is contained within the slice
                    s.forEachRemaining(action);
                    index = fence;
                } else {
                    // The spliterator intersects with the slice
                    while (sliceOrigin > index) {
                        s.tryAdvance(emptyConsumer());
                        index++;
                    }
                    // Traverse elements up to the fence
                    for (;index < fence; index++) {
                        s.tryAdvance(action);
                    }
                }
            }

            protected abstract T_CONS emptyConsumer();
        }
    }

    static abstract class UnorderedSliceSpliterator<T, T_SPLITR extends DefaultSpliterator<T>> {
        static final int CHUNK_SIZE = 1 << 7;
        protected final T_SPLITR s;
        protected final boolean unlimited;
        protected final int chunkSize;
        private final long skipThreshold;
        private final AtomicLong permits;

        UnorderedSliceSpliterator(T_SPLITR s, long skip, long limit) {
            this.s = s;
            this.unlimited = limit < 0;
            this.skipThreshold = limit >= 0 ? limit : 0;
            this.chunkSize = limit >= 0 ? (int)Math.min(CHUNK_SIZE,
                                                        ((skip + limit) / DefaultAbstractTask.getLeafTarget()) + 1) : CHUNK_SIZE;
            this.permits = new AtomicLong(limit >= 0 ? skip + limit : skip);
        }

        UnorderedSliceSpliterator(T_SPLITR s,
                                  UnorderedSliceSpliterator<T, T_SPLITR> parent) {
            this.s = s;
            this.unlimited = parent.unlimited;
            this.permits = parent.permits;
            this.skipThreshold = parent.skipThreshold;
            this.chunkSize = parent.chunkSize;
        }

        protected final long acquirePermits(long numElements) {
            long remainingPermits;
            long grabbing;
            assert numElements > 0;
            do {
                remainingPermits = permits.get();
                if (remainingPermits == 0)
                    return unlimited ? numElements : 0;
                grabbing = Math.min(remainingPermits, numElements);
            } while (grabbing > 0 &&
                     !permits.compareAndSet(remainingPermits, remainingPermits - grabbing));

            if (unlimited)
                return Math.max(numElements - grabbing, 0);
            else if (remainingPermits > skipThreshold)
                return Math.max(grabbing - (remainingPermits - skipThreshold), 0);
            else
                return grabbing;
        }

        enum PermitStatus { NO_MORE, MAYBE_MORE, UNLIMITED }

        protected final PermitStatus permitStatus() {
            if (permits.get() > 0)
                return PermitStatus.MAYBE_MORE;
            else
                return unlimited ?  PermitStatus.UNLIMITED : PermitStatus.NO_MORE;
        }

        public final T_SPLITR trySplit() throws RestException {
            if (permits.get() == 0)
                return null;
            @SuppressWarnings("unchecked")
            T_SPLITR split = (T_SPLITR) s.trySplit();
            return split == null ? null : makeSpliterator(split);
        }

        protected abstract T_SPLITR makeSpliterator(T_SPLITR s);

        public final long estimateSize() throws RestException {
            return s.estimateSize();
        }

        public final int characteristics() throws RestException {
            return s.characteristics() &
                   ~(DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED | DefaultSpliterator.ORDERED);
        }

        static final class OfRef<T> extends UnorderedSliceSpliterator<T, DefaultSpliterator<T>>
                implements DefaultSpliterator<T>, ConsumerActuator<T> {
            T tmpSlot;

            OfRef(DefaultSpliterator<T> s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfRef(DefaultSpliterator<T> s, DefaultStreamSpliterators.UnorderedSliceSpliterator.OfRef<T> parent) {
                super(s, parent);
            }

            @Override
            public void actuate(T t) {
                tmpSlot = t;
            }

            @Override
            public boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException {
                Objects.requireNonNull(action);

                while (permitStatus() != DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus.NO_MORE) {
                    if (!s.tryAdvance(this))
                        return false;
                    else if (acquirePermits(1) == 1) {
                        action.actuate(tmpSlot);
                        tmpSlot = null;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void forEachRemaining(ConsumerActuator<? super T> action) throws RestException {
                Objects.requireNonNull(action);

                ArrayBuffer.OfRef<T> sb = null;
                DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus permitStatus;
                while ((permitStatus = permitStatus()) != DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus.NO_MORE) {
                    if (permitStatus == DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus.MAYBE_MORE) {
                        if (sb == null)
                            sb = new ArrayBuffer.OfRef<>(chunkSize);
                        else
                            sb.reset();
                        long permitsRequested = 0;
                        do { } while (s.tryAdvance(sb) && ++permitsRequested < chunkSize);
                        if (permitsRequested == 0)
                            return;
                        sb.forEach(action, acquirePermits(permitsRequested));
                    }
                    else {
                        s.forEachRemaining(action);
                        return;
                    }
                }
            }

            @Override
            protected DefaultSpliterator<T> makeSpliterator(DefaultSpliterator<T> s) {
                return new OfRef<>(s, this);
            }
        }

        static abstract class OfPrimitive<
                T,
                T_CONS,
                T_BUFF extends ArrayBuffer.OfPrimitive<T_CONS>,
                T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
                extends UnorderedSliceSpliterator<T, T_SPLITR>
                implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            OfPrimitive(T_SPLITR s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfPrimitive(T_SPLITR s, UnorderedSliceSpliterator.OfPrimitive<T, T_CONS, T_BUFF, T_SPLITR> parent) {
                super(s, parent);
            }

            @Override
            public boolean tryAdvance(T_CONS action) throws RestException {
                Objects.requireNonNull(action);
                @SuppressWarnings("unchecked")
                T_CONS consumer = (T_CONS) this;

                while (permitStatus() != DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus.NO_MORE) {
                    if (!s.tryAdvance(consumer))
                        return false;
                    else if (acquirePermits(1) == 1) {
                        actuateConsumed(action);
                        return true;
                    }
                }
                return false;
            }

            protected abstract void actuateConsumed(T_CONS action);

            @Override
            public void forEachRemaining(T_CONS action) throws RestException {
                Objects.requireNonNull(action);

                T_BUFF sb = null;
                DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus permitStatus;
                while ((permitStatus = permitStatus()) != DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus.NO_MORE) {
                    if (permitStatus == DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus.MAYBE_MORE) {
                        if (sb == null)
                            sb = bufferCreate(chunkSize);
                        else
                            sb.reset();
                        @SuppressWarnings("unchecked")
                        T_CONS sbc = (T_CONS) sb;
                        long permitsRequested = 0;
                        do { } while (s.tryAdvance(sbc) && ++permitsRequested < chunkSize);
                        if (permitsRequested == 0)
                            return;
                        sb.forEach(action, acquirePermits(permitsRequested));
                    }
                    else {
                        s.forEachRemaining(action);
                        return;
                    }
                }
            }

            protected abstract T_BUFF bufferCreate(int initialCapacity);
        }
    }

    static final class DistinctSpliterator<T> implements DefaultSpliterator<T>, ConsumerActuator<T> {
        private static final Object NULL_VALUE = new Object();
        private final DefaultSpliterator<T> s;
        private final ConcurrentHashMap<T, Boolean> seen;
        private T tmpSlot;

        DistinctSpliterator(DefaultSpliterator<T> s) {
            this(s, new ConcurrentHashMap<>());
        }

        private DistinctSpliterator(DefaultSpliterator<T> s, ConcurrentHashMap<T, Boolean> seen) {
            this.s = s;
            this.seen = seen;
        }

        @Override
        public void actuate(T t) {
            this.tmpSlot = t;
        }

        @SuppressWarnings("unchecked")
        private T mapNull(T t) {
            return t != null ? t : (T) NULL_VALUE;
        }

        @Override
        public boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException {
            while (s.tryAdvance(this)) {
                if (seen.putIfAbsent(mapNull(tmpSlot), Boolean.TRUE) == null) {
                    action.actuate(tmpSlot);
                    tmpSlot = null;
                    return true;
                }
            }
            return false;
        }

        @Override
        public void forEachRemaining(ConsumerActuator<? super T> action) throws RestException {
            s.forEachRemaining(t -> {
                if (seen.putIfAbsent(mapNull(t), Boolean.TRUE) == null) {
                    action.actuate(t);
                }
            });
        }

        @Override
        public DefaultSpliterator<T> trySplit() throws RestException {
            DefaultSpliterator<T> split = s.trySplit();
            return (split != null) ? new DistinctSpliterator<>(split, seen) : null;
        }

        @Override
        public long estimateSize() throws RestException {
            return s.estimateSize();
        }

        @Override
        public int characteristics() throws RestException {
            return (s.characteristics() & ~(DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED |
                    DefaultSpliterator.SORTED | DefaultSpliterator.ORDERED))
                   | DefaultSpliterator.DISTINCT;
        }

        @Override
        public Comparator<? super T> getComparator() throws RestException {
            return s.getComparator();
        }
    }

    static abstract class InfiniteSupplyingSpliterator<T> implements DefaultSpliterator<T> {
        long estimate;

        protected InfiniteSupplyingSpliterator(long estimate) {
            this.estimate = estimate;
        }

        @Override
        public long estimateSize() {
            return estimate;
        }

        @Override
        public int characteristics() {
            return IMMUTABLE;
        }

        static final class OfRef<T> extends InfiniteSupplyingSpliterator<T> {
            final SupplierActuator<T> s;

            OfRef(long size, SupplierActuator<T> s) {
                super(size);
                this.s = s;
            }

            @Override
            public boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException {
                Objects.requireNonNull(action);

                action.actuate(s.actuate());
                return true;
            }

            @Override
            public DefaultSpliterator<T> trySplit() throws RestException {
                if (estimate == 0)
                    return null;
                return new OfRef<>(estimate >>>= 1, s);
            }
        }
    }

    // @@@ Consolidate with Node.Builder
    static abstract class ArrayBuffer {
        int index;

        void reset() {
            index = 0;
        }

        static final class OfRef<T> extends ArrayBuffer implements ConsumerActuator<T> {
            final Object[] array;

            OfRef(int size) {
                this.array = new Object[size];
            }

            @Override
            public void actuate(T t) {
                array[index++] = t;
            }

            public void forEach(ConsumerActuator<? super T> action, long fence) throws RestException {
                for (int i = 0; i < fence; i++) {
                    @SuppressWarnings("unchecked")
                    T t = (T) array[i];
                    action.actuate(t);
                }
            }
        }

        static abstract class OfPrimitive<T_CONS> extends ArrayBuffer {
            int index;

            @Override
            void reset() {
                index = 0;
            }

            abstract void forEach(T_CONS action, long fence);
        }
    }
}

