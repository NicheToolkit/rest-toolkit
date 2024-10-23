package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <code>DefaultStreamSpliterators</code>
 * <p>The default stream spliterators class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
class DefaultStreamSpliterators {

    /**
     * <code>AbstractWrappingSpliterator</code>
     * <p>The abstract wrapping spliterator class.</p>
     * @param <P_IN>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <P_OUT>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_BUFFER> {@link io.github.nichetoolkit.rest.stream.DefaultAbstractSpinedBuffer} <p>The generic parameter is <code>DefaultAbstractSpinedBuffer</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultAbstractSpinedBuffer
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @since Jdk1.8
     */
    private static abstract class AbstractWrappingSpliterator<P_IN, P_OUT,
                                                              T_BUFFER extends DefaultAbstractSpinedBuffer>
            implements DefaultSpliterator<P_OUT> {
        /**
         * <code>isParallel</code>
         * <p>The <code>isParallel</code> field.</p>
         */
        final boolean isParallel;

        /**
         * <code>ph</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The <code>ph</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         */
        final DefaultPipelineHelper<P_OUT> ph;

        /**
         * <code>spliteratorSupplier</code>
         * {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The <code>spliteratorSupplier</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        private SupplierActuator<DefaultSpliterator<P_IN>> spliteratorSupplier;

        /**
         * <code>spliterator</code>
         * <p>The spliterator field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        DefaultSpliterator<P_IN> spliterator;

        /**
         * <code>bufferDefaultSink</code>
         * <p>The buffer default sink field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         */
        DefaultSink<P_IN> bufferDefaultSink;

        /**
         * <code>pusher</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultBooleanSupplier} <p>The <code>pusher</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultBooleanSupplier
         */
        DefaultBooleanSupplier pusher;

        /**
         * <code>nextToConsume</code>
         * <p>The <code>nextToConsume</code> field.</p>
         */
        long nextToConsume;

        /**
         * <code>buffer</code>
         * <p>The <code>buffer</code> field.</p>
         */
        T_BUFFER buffer;

        /**
         * <code>finished</code>
         * <p>The <code>finished</code> field.</p>
         */
        boolean finished;

        /**
         * <code>AbstractWrappingSpliterator</code>
         * <p>Instantiates a new abstract wrapping spliterator.</p>
         * @param ph                  {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The ph parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliteratorSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The spliterator supplier parameter is <code>SupplierActuator</code> type.</p>
         * @param parallel            boolean <p>The parallel parameter is <code>boolean</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        AbstractWrappingSpliterator(DefaultPipelineHelper<P_OUT> ph,
                                    SupplierActuator<DefaultSpliterator<P_IN>> spliteratorSupplier,
                                    boolean parallel) {
            this.ph = ph;
            this.spliteratorSupplier = spliteratorSupplier;
            this.spliterator = null;
            this.isParallel = parallel;
        }

        /**
         * <code>AbstractWrappingSpliterator</code>
         * <p>Instantiates a new abstract wrapping spliterator.</p>
         * @param ph          {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The ph parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @param parallel    boolean <p>The parallel parameter is <code>boolean</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        AbstractWrappingSpliterator(DefaultPipelineHelper<P_OUT> ph,
                                    DefaultSpliterator<P_IN> spliterator,
                                    boolean parallel) {
            this.ph = ph;
            this.spliteratorSupplier = null;
            this.spliterator = spliterator;
            this.isParallel = parallel;
        }

        /**
         * <code>init</code>
         * <p>The init method.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        final void init() throws RestException {
            if (spliterator == null) {
                spliterator = spliteratorSupplier.actuate();
                spliteratorSupplier = null;
            }
        }

        /**
         * <code>doAdvance</code>
         * <p>The do advance method.</p>
         * @return boolean <p>The do advance return object is <code>boolean</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
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

        /**
         * <code>wrap</code>
         * <p>The wrap method.</p>
         * @param s {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The s parameter is <code>DefaultSpliterator</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.AbstractWrappingSpliterator} <p>The wrap return object is <code>AbstractWrappingSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        abstract AbstractWrappingSpliterator<P_IN, P_OUT, ?> wrap(DefaultSpliterator<P_IN> s);

        /**
         * <code>initPartialTraversalState</code>
         * <p>The init partial traversal state method.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
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

        /**
         * <code>fillBuffer</code>
         * <p>The fill buffer method.</p>
         * @return boolean <p>The fill buffer return object is <code>boolean</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
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

    /**
     * <code>WrappingSpliterator</code>
     * <p>The wrapping spliterator class.</p>
     * @param <P_IN>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <P_OUT> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.AbstractWrappingSpliterator
     * @since Jdk1.8
     */
    static final class WrappingSpliterator<P_IN, P_OUT>
            extends AbstractWrappingSpliterator<P_IN, P_OUT, DefaultSpinedBuffer<P_OUT>> {

        /**
         * <code>WrappingSpliterator</code>
         * <p>Instantiates a new wrapping spliterator.</p>
         * @param ph       {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The ph parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
         * @param parallel boolean <p>The parallel parameter is <code>boolean</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        WrappingSpliterator(DefaultPipelineHelper<P_OUT> ph,
                            SupplierActuator<DefaultSpliterator<P_IN>> supplier,
                            boolean parallel) {
            super(ph, supplier, parallel);
        }

        /**
         * <code>WrappingSpliterator</code>
         * <p>Instantiates a new wrapping spliterator.</p>
         * @param ph          {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The ph parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @param parallel    boolean <p>The parallel parameter is <code>boolean</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
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
        @SuppressWarnings("StatementWithEmptyBody")
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

    /**
     * <code>DelegatingSpliterator</code>
     * <p>The delegating spliterator class.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The generic parameter is <code>DefaultSpliterator</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @since Jdk1.8
     */
    static class DelegatingSpliterator<T, T_SPLITR extends DefaultSpliterator<T>>
            implements DefaultSpliterator<T> {
        /**
         * <code>supplier</code>
         * {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The <code>supplier</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        private final SupplierActuator<? extends T_SPLITR> supplier;

        /**
         * <code>s</code>
         * <p>The <code>s</code> field.</p>
         */
        private T_SPLITR s;

        /**
         * <code>DelegatingSpliterator</code>
         * <p>Instantiates a new delegating spliterator.</p>
         * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        DelegatingSpliterator(SupplierActuator<? extends T_SPLITR> supplier) {
            this.supplier = supplier;
        }

        /**
         * <code>actuate</code>
         * <p>The actuate method.</p>
         * @return T_SPLITR <p>The actuate return object is <code>T_SPLITR</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
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

        /**
         * <code>OfPrimitive</code>
         * <p>The of primitive class.</p>
         * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T_CONS>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive
         * @since Jdk1.8
         */
        static class OfPrimitive<T, T_CONS, T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
            extends DelegatingSpliterator<T, T_SPLITR>
            implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            /**
             * <code>OfPrimitive</code>
             * <p>Instantiates a new of primitive.</p>
             * @param supplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
             * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
             */
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

    /**
     * <code>SliceSpliterator</code>
     * <p>The slice spliterator class.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The generic parameter is <code>DefaultSpliterator</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @since Jdk1.8
     */
    static abstract class SliceSpliterator<T, T_SPLITR extends DefaultSpliterator<T>> {
        /**
         * <code>sliceOrigin</code>
         * <p>The <code>sliceOrigin</code> field.</p>
         */
        final long sliceOrigin;
        /**
         * <code>sliceFence</code>
         * <p>The <code>sliceFence</code> field.</p>
         */
        final long sliceFence;

        /**
         * <code>s</code>
         * <p>The <code>s</code> field.</p>
         */
        T_SPLITR s;
        /**
         * <code>index</code>
         * <p>The <code>index</code> field.</p>
         */
        long index;
        /**
         * <code>fence</code>
         * <p>The <code>fence</code> field.</p>
         */
        long fence;

        /**
         * <code>SliceSpliterator</code>
         * <p>Instantiates a new slice spliterator.</p>
         * @param s           T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
         * @param sliceOrigin long <p>The slice origin parameter is <code>long</code> type.</p>
         * @param sliceFence  long <p>The slice fence parameter is <code>long</code> type.</p>
         * @param origin      long <p>The origin parameter is <code>long</code> type.</p>
         * @param fence       long <p>The fence parameter is <code>long</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        SliceSpliterator(T_SPLITR s, long sliceOrigin, long sliceFence, long origin, long fence) throws RestException {
            assert s.hasCharacteristics(DefaultSpliterator.SUBSIZED);
            this.s = s;
            this.sliceOrigin = sliceOrigin;
            this.sliceFence = sliceFence;
            this.index = origin;
            this.fence = fence;
        }

        /**
         * <code>makeSpliterator</code>
         * <p>The make spliterator method.</p>
         * @param s           T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
         * @param sliceOrigin long <p>The slice origin parameter is <code>long</code> type.</p>
         * @param sliceFence  long <p>The slice fence parameter is <code>long</code> type.</p>
         * @param origin      long <p>The origin parameter is <code>long</code> type.</p>
         * @param fence       long <p>The fence parameter is <code>long</code> type.</p>
         * @return T_SPLITR <p>The make spliterator return object is <code>T_SPLITR</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        protected abstract T_SPLITR makeSpliterator(T_SPLITR s, long sliceOrigin, long sliceFence, long origin, long fence) throws RestException;

        /**
         * <code>trySplit</code>
         * <p>The try split method.</p>
         * @return T_SPLITR <p>The try split return object is <code>T_SPLITR</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
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

        /**
         * <code>estimateSize</code>
         * <p>The estimate size method.</p>
         * @return long <p>The estimate size return object is <code>long</code> type.</p>
         */
        public long estimateSize() {
            return (sliceOrigin < fence)
                   ? fence - Math.max(sliceOrigin, index) : 0;
        }

        /**
         * <code>characteristics</code>
         * <p>The characteristics method.</p>
         * @return int <p>The characteristics return object is <code>int</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        public int characteristics() throws RestException {
            return s.characteristics();
        }

        /**
         * <code>OfRef</code>
         * <p>The of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         * @since Jdk1.8
         */
        static final class OfRef<T>
                extends SliceSpliterator<T, DefaultSpliterator<T>>
                implements DefaultSpliterator<T> {

            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param s           {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The s parameter is <code>DefaultSpliterator</code> type.</p>
             * @param sliceOrigin long <p>The slice origin parameter is <code>long</code> type.</p>
             * @param sliceFence  long <p>The slice fence parameter is <code>long</code> type.</p>
             * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
             * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
             * @see io.github.nichetoolkit.rest.RestException
             */
            OfRef(DefaultSpliterator<T> s, long sliceOrigin, long sliceFence) throws RestException {
                this(s, sliceOrigin, sliceFence, 0, Math.min(s.estimateSize(), sliceFence));
            }

            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param s           {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The s parameter is <code>DefaultSpliterator</code> type.</p>
             * @param sliceOrigin long <p>The slice origin parameter is <code>long</code> type.</p>
             * @param sliceFence  long <p>The slice fence parameter is <code>long</code> type.</p>
             * @param origin      long <p>The origin parameter is <code>long</code> type.</p>
             * @param fence       long <p>The fence parameter is <code>long</code> type.</p>
             * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
             * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
             * @see io.github.nichetoolkit.rest.RestException
             */
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

        /**
         * <code>OfPrimitive</code>
         * <p>The of primitive class.</p>
         * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
         * @param <T_CONS>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive
         * @since Jdk1.8
         */
        static abstract class OfPrimitive<T,
                T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
                T_CONS>
                extends SliceSpliterator<T, T_SPLITR>
                implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {

            /**
             * <code>OfPrimitive</code>
             * <p>Instantiates a new of primitive.</p>
             * @param s           T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
             * @param sliceOrigin long <p>The slice origin parameter is <code>long</code> type.</p>
             * @param sliceFence  long <p>The slice fence parameter is <code>long</code> type.</p>
             * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
             * @see io.github.nichetoolkit.rest.RestException
             */
            OfPrimitive(T_SPLITR s, long sliceOrigin, long sliceFence) throws RestException {
                this(s, sliceOrigin, sliceFence, 0, Math.min(s.estimateSize(), sliceFence));
            }

            /**
             * <code>OfPrimitive</code>
             * <p>Instantiates a new of primitive.</p>
             * @param s           T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
             * @param sliceOrigin long <p>The slice origin parameter is <code>long</code> type.</p>
             * @param sliceFence  long <p>The slice fence parameter is <code>long</code> type.</p>
             * @param origin      long <p>The origin parameter is <code>long</code> type.</p>
             * @param fence       long <p>The fence parameter is <code>long</code> type.</p>
             * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
             * @see io.github.nichetoolkit.rest.RestException
             */
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

            /**
             * <code>emptyConsumer</code>
             * <p>The empty consumer method.</p>
             * @return T_CONS <p>The empty consumer return object is <code>T_CONS</code> type.</p>
             */
            protected abstract T_CONS emptyConsumer();
        }
    }

    /**
     * <code>UnorderedSliceSpliterator</code>
     * <p>The unordered slice spliterator class.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The generic parameter is <code>DefaultSpliterator</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @since Jdk1.8
     */
    static abstract class UnorderedSliceSpliterator<T, T_SPLITR extends DefaultSpliterator<T>> {
        /**
         * <code>CHUNK_SIZE</code>
         * <p>The <code>CHUNK_SIZE</code> field.</p>
         */
        static final int CHUNK_SIZE = 1 << 7;
        /**
         * <code>s</code>
         * <p>The <code>s</code> field.</p>
         */
        protected final T_SPLITR s;
        /**
         * <code>unlimited</code>
         * <p>The <code>unlimited</code> field.</p>
         */
        protected final boolean unlimited;
        /**
         * <code>chunkSize</code>
         * <p>The <code>chunkSize</code> field.</p>
         */
        protected final int chunkSize;
        /**
         * <code>skipThreshold</code>
         * <p>The <code>skipThreshold</code> field.</p>
         */
        private final long skipThreshold;
        /**
         * <code>permits</code>
         * {@link java.util.concurrent.atomic.AtomicLong} <p>The <code>permits</code> field.</p>
         * @see java.util.concurrent.atomic.AtomicLong
         */
        private final AtomicLong permits;

        /**
         * <code>UnorderedSliceSpliterator</code>
         * <p>Instantiates a new unordered slice spliterator.</p>
         * @param s     T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
         * @param skip  long <p>The skip parameter is <code>long</code> type.</p>
         * @param limit long <p>The limit parameter is <code>long</code> type.</p>
         */
        UnorderedSliceSpliterator(T_SPLITR s, long skip, long limit) {
            this.s = s;
            this.unlimited = limit < 0;
            this.skipThreshold = limit >= 0 ? limit : 0;
            this.chunkSize = limit >= 0 ? (int)Math.min(CHUNK_SIZE,
                                                        ((skip + limit) / DefaultAbstractTask.getLeafTarget()) + 1) : CHUNK_SIZE;
            this.permits = new AtomicLong(limit >= 0 ? skip + limit : skip);
        }

        /**
         * <code>UnorderedSliceSpliterator</code>
         * <p>Instantiates a new unordered slice spliterator.</p>
         * @param s      T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
         * @param parent {@link io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.UnorderedSliceSpliterator} <p>The parent parameter is <code>UnorderedSliceSpliterator</code> type.</p>
         */
        UnorderedSliceSpliterator(T_SPLITR s,
                                  UnorderedSliceSpliterator<T, T_SPLITR> parent) {
            this.s = s;
            this.unlimited = parent.unlimited;
            this.permits = parent.permits;
            this.skipThreshold = parent.skipThreshold;
            this.chunkSize = parent.chunkSize;
        }

        /**
         * <code>acquirePermits</code>
         * <p>The acquire permits method.</p>
         * @param numElements long <p>The num elements parameter is <code>long</code> type.</p>
         * @return long <p>The acquire permits return object is <code>long</code> type.</p>
         */
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

        /**
         * <code>PermitStatus</code>
         * <p>The permit status enumeration.</p>
         * @author Cyan (snow22314@outlook.com)
         * @since Jdk1.8
         */
        enum PermitStatus {
            /**
             * <code>NO_MORE</code>
             * <p>The no more permit status field.</p>
             */
            NO_MORE,
            /**
             * <code>MAYBE_MORE</code>
             * <p>The maybe more permit status field.</p>
             */
            MAYBE_MORE,
            /**
             * <code>UNLIMITED</code>
             * <p>The unlimited permit status field.</p>
             */
            UNLIMITED }

        /**
         * <code>permitStatus</code>
         * <p>The permit status method.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus} <p>The permit status return object is <code>PermitStatus</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.UnorderedSliceSpliterator.PermitStatus
         */
        protected final PermitStatus permitStatus() {
            if (permits.get() > 0)
                return PermitStatus.MAYBE_MORE;
            else
                return unlimited ?  PermitStatus.UNLIMITED : PermitStatus.NO_MORE;
        }

        /**
         * <code>trySplit</code>
         * <p>The try split method.</p>
         * @return T_SPLITR <p>The try split return object is <code>T_SPLITR</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        public final T_SPLITR trySplit() throws RestException {
            if (permits.get() == 0)
                return null;
            @SuppressWarnings("unchecked")
            T_SPLITR split = (T_SPLITR) s.trySplit();
            return split == null ? null : makeSpliterator(split);
        }

        /**
         * <code>makeSpliterator</code>
         * <p>The make spliterator method.</p>
         * @param s T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
         * @return T_SPLITR <p>The make spliterator return object is <code>T_SPLITR</code> type.</p>
         */
        protected abstract T_SPLITR makeSpliterator(T_SPLITR s);

        /**
         * <code>estimateSize</code>
         * <p>The estimate size method.</p>
         * @return long <p>The estimate size return object is <code>long</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        public final long estimateSize() throws RestException {
            return s.estimateSize();
        }

        /**
         * <code>characteristics</code>
         * <p>The characteristics method.</p>
         * @return int <p>The characteristics return object is <code>int</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        public final int characteristics() throws RestException {
            return s.characteristics() &
                   ~(DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED | DefaultSpliterator.ORDERED);
        }

        /**
         * <code>OfRef</code>
         * <p>The of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
         * @since Jdk1.8
         */
        static final class OfRef<T> extends UnorderedSliceSpliterator<T, DefaultSpliterator<T>>
                implements DefaultSpliterator<T>, ConsumerActuator<T> {
            /**
             * <code>tmpSlot</code>
             * <p>The <code>tmpSlot</code> field.</p>
             */
            T tmpSlot;

            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param s     {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The s parameter is <code>DefaultSpliterator</code> type.</p>
             * @param skip  long <p>The skip parameter is <code>long</code> type.</p>
             * @param limit long <p>The limit parameter is <code>long</code> type.</p>
             * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
             */
            OfRef(DefaultSpliterator<T> s, long skip, long limit) {
                super(s, skip, limit);
            }

            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param s      {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The s parameter is <code>DefaultSpliterator</code> type.</p>
             * @param parent {@link io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.UnorderedSliceSpliterator.OfRef} <p>The parent parameter is <code>OfRef</code> type.</p>
             * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
             */
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
            @SuppressWarnings("StatementWithEmptyBody")
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

        /**
         * <code>OfPrimitive</code>
         * <p>The of primitive class.</p>
         * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T_CONS>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T_BUFF>   {@link io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.ArrayBuffer.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
         * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive} <p>The generic parameter is <code>OfPrimitive</code> type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.ArrayBuffer.OfPrimitive
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive
         * @since Jdk1.8
         */
        static abstract class OfPrimitive<
                T,
                T_CONS,
                T_BUFF extends ArrayBuffer.OfPrimitive<T_CONS>,
                T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>>
                extends UnorderedSliceSpliterator<T, T_SPLITR>
                implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            /**
             * <code>OfPrimitive</code>
             * <p>Instantiates a new of primitive.</p>
             * @param s     T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
             * @param skip  long <p>The skip parameter is <code>long</code> type.</p>
             * @param limit long <p>The limit parameter is <code>long</code> type.</p>
             */
            OfPrimitive(T_SPLITR s, long skip, long limit) {
                super(s, skip, limit);
            }

            /**
             * <code>OfPrimitive</code>
             * <p>Instantiates a new of primitive.</p>
             * @param s      T_SPLITR <p>The s parameter is <code>T_SPLITR</code> type.</p>
             * @param parent {@link io.github.nichetoolkit.rest.stream.DefaultStreamSpliterators.UnorderedSliceSpliterator.OfPrimitive} <p>The parent parameter is <code>OfPrimitive</code> type.</p>
             */
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

            /**
             * <code>actuateConsumed</code>
             * <p>The actuate consumed method.</p>
             * @param action T_CONS <p>The action parameter is <code>T_CONS</code> type.</p>
             */
            protected abstract void actuateConsumed(T_CONS action);

            @Override
            @SuppressWarnings("StatementWithEmptyBody")
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

            /**
             * <code>bufferCreate</code>
             * <p>The buffer create method.</p>
             * @param initialCapacity int <p>The initial capacity parameter is <code>int</code> type.</p>
             * @return T_BUFF <p>The buffer create return object is <code>T_BUFF</code> type.</p>
             */
            protected abstract T_BUFF bufferCreate(int initialCapacity);
        }
    }

    /**
     * <code>DistinctSpliterator</code>
     * <p>The distinct spliterator class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @since Jdk1.8
     */
    static final class DistinctSpliterator<T> implements DefaultSpliterator<T>, ConsumerActuator<T> {
        /**
         * <code>NULL_VALUE</code>
         * {@link java.lang.Object} <p>The constant <code>NULL_VALUE</code> field.</p>
         * @see java.lang.Object
         */
        private static final Object NULL_VALUE = new Object();
        /**
         * <code>s</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The <code>s</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        private final DefaultSpliterator<T> s;
        /**
         * <code>seen</code>
         * {@link java.util.concurrent.ConcurrentHashMap} <p>The <code>seen</code> field.</p>
         * @see java.util.concurrent.ConcurrentHashMap
         */
        private final ConcurrentHashMap<T, Boolean> seen;
        /**
         * <code>tmpSlot</code>
         * <p>The <code>tmpSlot</code> field.</p>
         */
        private T tmpSlot;

        /**
         * <code>DistinctSpliterator</code>
         * <p>Instantiates a new distinct spliterator.</p>
         * @param s {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The s parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        DistinctSpliterator(DefaultSpliterator<T> s) {
            this(s, new ConcurrentHashMap<>());
        }

        /**
         * <code>DistinctSpliterator</code>
         * <p>Instantiates a new distinct spliterator.</p>
         * @param s    {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The s parameter is <code>DefaultSpliterator</code> type.</p>
         * @param seen {@link java.util.concurrent.ConcurrentHashMap} <p>The seen parameter is <code>ConcurrentHashMap</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         * @see java.util.concurrent.ConcurrentHashMap
         */
        private DistinctSpliterator(DefaultSpliterator<T> s, ConcurrentHashMap<T, Boolean> seen) {
            this.s = s;
            this.seen = seen;
        }

        @Override
        public void actuate(T t) {
            this.tmpSlot = t;
        }

        /**
         * <code>mapNull</code>
         * <p>The map null method.</p>
         * @param t T <p>The t parameter is <code>T</code> type.</p>
         * @return T <p>The map null return object is <code>T</code> type.</p>
         * @see java.lang.SuppressWarnings
         */
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

    /**
     * <code>InfiniteSupplyingSpliterator</code>
     * <p>The infinite supplying spliterator class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @since Jdk1.8
     */
    static abstract class InfiniteSupplyingSpliterator<T> implements DefaultSpliterator<T> {
        /**
         * <code>estimate</code>
         * <p>The <code>estimate</code> field.</p>
         */
        long estimate;

        /**
         * <code>InfiniteSupplyingSpliterator</code>
         * <p>Instantiates a new infinite supplying spliterator.</p>
         * @param estimate long <p>The estimate parameter is <code>long</code> type.</p>
         */
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

        /**
         * <code>OfRef</code>
         * <p>The of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @since Jdk1.8
         */
        static final class OfRef<T> extends InfiniteSupplyingSpliterator<T> {
            /**
             * <code>s</code>
             * {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The <code>s</code> field.</p>
             * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
             */
            final SupplierActuator<T> s;

            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param size long <p>The size parameter is <code>long</code> type.</p>
             * @param s    {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The s parameter is <code>SupplierActuator</code> type.</p>
             * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
             */
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

    /**
     * <code>ArrayBuffer</code>
     * <p>The array buffer class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
// @@@ Consolidate with Node.Builder
    static abstract class ArrayBuffer {
        /**
         * <code>index</code>
         * <p>The <code>index</code> field.</p>
         */
        int index;

        /**
         * <code>reset</code>
         * <p>The reset method.</p>
         */
        void reset() {
            index = 0;
        }

        /**
         * <code>OfRef</code>
         * <p>The of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
         * @since Jdk1.8
         */
        static final class OfRef<T> extends ArrayBuffer implements ConsumerActuator<T> {
            /**
             * <code>array</code>
             * {@link java.lang.Object} <p>The <code>array</code> field.</p>
             * @see java.lang.Object
             */
            final Object[] array;

            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param size int <p>The size parameter is <code>int</code> type.</p>
             */
            OfRef(int size) {
                this.array = new Object[size];
            }

            @Override
            public void actuate(T t) {
                array[index++] = t;
            }

            /**
             * <code>forEach</code>
             * <p>The for each method.</p>
             * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
             * @param fence  long <p>The fence parameter is <code>long</code> type.</p>
             * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
             * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
             * @see io.github.nichetoolkit.rest.RestException
             */
            public void forEach(ConsumerActuator<? super T> action, long fence) throws RestException {
                for (int i = 0; i < fence; i++) {
                    @SuppressWarnings("unchecked")
                    T t = (T) array[i];
                    action.actuate(t);
                }
            }
        }

        /**
         * <code>OfPrimitive</code>
         * <p>The of primitive class.</p>
         * @param <T_CONS> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @since Jdk1.8
         */
        static abstract class OfPrimitive<T_CONS> extends ArrayBuffer {
            /**
             * <code>index</code>
             * <p>The <code>index</code> field.</p>
             */
            int index;

            @Override
            void reset() {
                index = 0;
            }

            /**
             * <code>forEach</code>
             * <p>The for each method.</p>
             * @param action T_CONS <p>The action parameter is <code>T_CONS</code> type.</p>
             * @param fence  long <p>The fence parameter is <code>long</code> type.</p>
             */
            abstract void forEach(T_CONS action, long fence);
        }
    }
}

