package io.github.nichetoolkit.rest.stream;

import java.util.*;
import java.util.function.IntFunction;


/**
 * Factory methods for transforming streams into sorted streams.
 *
 * @since 1.8
 */
final class DefaultSortedOps {

    private DefaultSortedOps() { }

    /**
     * Appends a "sorted" operation to the provided stream.
     *
     * @param <T> the type of both input and output elements
     * @param upstream a reference stream with element type T
     */
    static <T> RestStream<T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream) {
        return new OfRef<>(upstream);
    }

    /**
     * Appends a "sorted" operation to the provided stream.
     *
     * @param <T> the type of both input and output elements
     * @param upstream a reference stream with element type T
     * @param comparator the comparator to order elements by
     */
    static <T> RestStream<T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream,
                                Comparator<? super T> comparator) {
        return new OfRef<>(upstream, comparator);
    }

//    /**
//     * Appends a "sorted" operation to the provided stream.
//     *
//     * @param <T> the type of both input and output elements
//     * @param upstream a reference stream with element type T
//     */
//    static <T> IntStream makeInt(DefaultAbstractPipeline<?, Integer, ?> upstream) {
//        return new OfInt(upstream);
//    }
//
//    /**
//     * Appends a "sorted" operation to the provided stream.
//     *
//     * @param <T> the type of both input and output elements
//     * @param upstream a reference stream with element type T
//     */
//    static <T> LongStream makeLong(DefaultAbstractPipeline<?, Long, ?> upstream) {
//        return new OfLong(upstream);
//    }
//
//    /**
//     * Appends a "sorted" operation to the provided stream.
//     *
//     * @param <T> the type of both input and output elements
//     * @param upstream a reference stream with element type T
//     */
//    static <T> DoubleStream makeDouble(DefaultAbstractPipeline<?, Double, ?> upstream) {
//        return new OfDouble(upstream);
//    }

    /**
     * Specialized subtype for sorting reference streams
     */
    private static final class OfRef<T> extends  DefaultReferencePipeline.StatefulOp<T, T> {
        /**
         * Comparator used for sorting
         */
        private final boolean isNaturalSort;
        private final Comparator<? super T> comparator;

        /**
         * Sort using natural order of {@literal <T>} which must be
         * {@code Comparable}.
         */
        OfRef(DefaultAbstractPipeline<?, T, ?> upstream) {
            super(upstream, DefaultStreamShape.REFERENCE,
                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.IS_SORTED);
            this.isNaturalSort = true;
            // Will throw CCE when we try to sort if T is not Comparable
            @SuppressWarnings("unchecked")
            Comparator<? super T> comp = (Comparator<? super T>) Comparator.naturalOrder();
            this.comparator = comp;
        }

        /**
         * Sort using the provided comparator.
         *
         * @param comparator The comparator to be used to evaluate ordering.
         */
        OfRef(DefaultAbstractPipeline<?, T, ?> upstream, Comparator<? super T> comparator) {
            super(upstream, DefaultStreamShape.REFERENCE,
                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.NOT_SORTED);
            this.isNaturalSort = false;
            this.comparator = Objects.requireNonNull(comparator);
        }

        @Override
        public DefaultSink<T> opWrapSink(int flags, DefaultSink<T> sink) {
            Objects.requireNonNull(sink);

            // If the input is already naturally sorted and this operation
            // also naturally sorted then this is a no-op
            if (DefaultStreamOpFlag.SORTED.isKnown(flags) && isNaturalSort)
                return sink;
            else if (DefaultStreamOpFlag.SIZED.isKnown(flags))
                return new SizedRefSortingSink<>(sink, comparator);
            else
                return new RefSortingSink<>(sink, comparator);
        }

        @Override
        public <P_IN> DefaultNode<T> opEvaluateParallel(DefaultPipelineHelper<T> helper,
                                                 Spliterator<P_IN> spliterator,
                                                 IntFunction<T[]> generator) {
            // If the input is already naturally sorted and this operation
            // naturally sorts then collect the output
            if (DefaultStreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags()) && isNaturalSort) {
                return helper.evaluate(spliterator, false, generator);
            }
            else {
                // @@@ Weak two-pass parallel implementation; parallel collect, parallel sort
                T[] flattenedData = helper.evaluate(spliterator, true, generator).asArray(generator);
                Arrays.parallelSort(flattenedData, comparator);
                return DefaultNodes.node(flattenedData);
            }
        }
    }

//    /**
//     * Specialized subtype for sorting int streams.
//     */
//    private static final class OfInt extends StreamIntPipeline.StatefulOp<Integer> {
//        OfInt(DefaultAbstractPipeline<?, Integer, ?> upstream) {
//            super(upstream, DefaultStreamShape.INT_VALUE,
//                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.IS_SORTED);
//        }
//
//        @Override
//        public DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Integer> sink) {
//            Objects.requireNonNull(sink);
//
//            if (DefaultStreamOpFlag.SORTED.isKnown(flags))
//                return sink;
//            else if (DefaultStreamOpFlag.SIZED.isKnown(flags))
//                return new SizedIntSortingSink(sink);
//            else
//                return new IntSortingSink(sink);
//        }
//
//        @Override
//        public <P_IN> DefaultNode<Integer> opEvaluateParallel(DefaultPipelineHelper<Integer> helper,
//                                                       Spliterator<P_IN> spliterator,
//                                                       IntFunction<Integer[]> generator) {
//            if (DefaultStreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags())) {
//                return helper.evaluate(spliterator, false, generator);
//            }
//            else {
//                DefaultNode.OfInt n = (DefaultNode.OfInt) helper.evaluate(spliterator, true, generator);
//
//                int[] content = n.asPrimitiveArray();
//                Arrays.parallelSort(content);
//
//                return DefaultNodes.node(content);
//            }
//        }
//    }
//
//    /**
//     * Specialized subtype for sorting long streams.
//     */
//    private static final class OfLong extends StreamLongPipeline.StatefulOp<Long> {
//        OfLong(DefaultAbstractPipeline<?, Long, ?> upstream) {
//            super(upstream, DefaultStreamShape.LONG_VALUE,
//                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.IS_SORTED);
//        }
//
//        @Override
//        public DefaultSink<Long> opWrapSink(int flags, DefaultSink<Long> sink) {
//            Objects.requireNonNull(sink);
//
//            if (DefaultStreamOpFlag.SORTED.isKnown(flags))
//                return sink;
//            else if (DefaultStreamOpFlag.SIZED.isKnown(flags))
//                return new SizedLongSortingSink(sink);
//            else
//                return new LongSortingSink(sink);
//        }
//
//        @Override
//        public <P_IN> DefaultNode<Long> opEvaluateParallel(DefaultPipelineHelper<Long> helper,
//                                                    Spliterator<P_IN> spliterator,
//                                                    IntFunction<Long[]> generator) {
//            if (DefaultStreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags())) {
//                return helper.evaluate(spliterator, false, generator);
//            }
//            else {
//                DefaultNode.OfLong n = (DefaultNode.OfLong) helper.evaluate(spliterator, true, generator);
//
//                long[] content = n.asPrimitiveArray();
//                Arrays.parallelSort(content);
//
//                return DefaultNodes.node(content);
//            }
//        }
//    }
//
//    /**
//     * Specialized subtype for sorting double streams.
//     */
//    private static final class OfDouble extends StreamDoublePipeline.StatefulOp<Double> {
//        OfDouble(DefaultAbstractPipeline<?, Double, ?> upstream) {
//            super(upstream, DefaultStreamShape.DOUBLE_VALUE,
//                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.IS_SORTED);
//        }
//
//        @Override
//        public DefaultSink<Double> opWrapSink(int flags, DefaultSink<Double> sink) {
//            Objects.requireNonNull(sink);
//
//            if (DefaultStreamOpFlag.SORTED.isKnown(flags))
//                return sink;
//            else if (DefaultStreamOpFlag.SIZED.isKnown(flags))
//                return new SizedDoubleSortingSink(sink);
//            else
//                return new DoubleSortingSink(sink);
//        }
//
//        @Override
//        public <P_IN> DefaultNode<Double> opEvaluateParallel(DefaultPipelineHelper<Double> helper,
//                                                      Spliterator<P_IN> spliterator,
//                                                      IntFunction<Double[]> generator) {
//            if (DefaultStreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags())) {
//                return helper.evaluate(spliterator, false, generator);
//            }
//            else {
//                DefaultNode.OfDouble n = (DefaultNode.OfDouble) helper.evaluate(spliterator, true, generator);
//
//                double[] content = n.asPrimitiveArray();
//                Arrays.parallelSort(content);
//
//                return DefaultNodes.node(content);
//            }
//        }
//    }

    /**
     * Abstract {@link DefaultSink} for implementing sort on reference streams.
     *
     * <p>
     * Note: documentation below applies to reference and all primitive sinks.
     * <p>
     * Sorting sinks first accept all elements, buffering then into an array
     * or a re-sizable data structure, if the size of the pipeline is known or
     * unknown respectively.  At the end of the sink protocol those elements are
     * sorted and then pushed downstream.
     * This class records if {@link #cancellationRequested} is called.  If so it
     * can be inferred that the source pushing source elements into the pipeline
     * knows that the pipeline is short-circuiting.  In such cases sub-classes
     * pushing elements downstream will preserve the short-circuiting protocol
     * by calling {@code downstream.cancellationRequested()} and checking the
     * result is {@code false} before an element is pushed.
     * <p>
     * Note that the above behaviour is an optimization for sorting with
     * sequential streams.  It is not an error that more elements, than strictly
     * required to produce a result, may flow through the pipeline.  This can
     * occur, in general (not restricted to just sorting), for short-circuiting
     * parallel pipelines.
     */
    private static abstract class AbstractRefSortingSink<T> extends DefaultSink.ChainedReference<T, T> {
        protected final Comparator<? super T> comparator;
        // @@@ could be a lazy final value, if/when support is added
        // true if cancellationRequested() has been called
        protected boolean cancellationRequestedCalled;

        AbstractRefSortingSink(DefaultSink<? super T> downstream, Comparator<? super T> comparator) {
            super(downstream);
            this.comparator = comparator;
        }

        /**
         * Records is cancellation is requested so short-circuiting behaviour
         * can be preserved when the sorted elements are pushed downstream.
         *
         * @return false, as this sink never short-circuits.
         */
        @Override
        public final boolean cancellationRequested() {
            // If this method is called then an operation within the stream
            // pipeline is short-circuiting (see DefaultAbstractPipeline.copyInto).
            // Note that we cannot differentiate between an upstream or
            // downstream operation
            cancellationRequestedCalled = true;
            return false;
        }
    }

    /**
     * {@link DefaultSink} for implementing sort on SIZED reference streams.
     */
    private static final class SizedRefSortingSink<T> extends AbstractRefSortingSink<T> {
        private T[] array;
        private int offset;

        SizedRefSortingSink(DefaultSink<? super T> sink, Comparator<? super T> comparator) {
            super(sink, comparator);
        }

        @Override
        @SuppressWarnings("unchecked")
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            array = (T[]) new Object[(int) size];
        }

        @Override
        public void end() {
            Arrays.sort(array, 0, offset, comparator);
            downstream.begin(offset);
            if (!cancellationRequestedCalled) {
                for (int i = 0; i < offset; i++)
                    downstream.accept(array[i]);
            }
            else {
                for (int i = 0; i < offset && !downstream.cancellationRequested(); i++)
                    downstream.accept(array[i]);
            }
            downstream.end();
            array = null;
        }

        @Override
        public void accept(T t) {
            array[offset++] = t;
        }
    }

    /**
     * {@link DefaultSink} for implementing sort on reference streams.
     */
    private static final class RefSortingSink<T> extends AbstractRefSortingSink<T> {
        private ArrayList<T> list;

        RefSortingSink(DefaultSink<? super T> sink, Comparator<? super T> comparator) {
            super(sink, comparator);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            list = (size >= 0) ? new ArrayList<>((int) size) : new ArrayList<>();
        }

        @Override
        public void end() {
            list.sort(comparator);
            downstream.begin(list.size());
            if (!cancellationRequestedCalled) {
                list.forEach(downstream);
            }
            else {
                for (T t : list) {
                    if (downstream.cancellationRequested()) break;
                    downstream.accept(t);
                }
            }
            downstream.end();
            list = null;
        }

        @Override
        public void accept(T t) {
            list.add(t);
        }
    }

    /**
     * Abstract {@link DefaultSink} for implementing sort on int streams.
     */
    private static abstract class AbstractIntSortingSink extends DefaultSink.ChainedInt<Integer> {
        // true if cancellationRequested() has been called
        protected boolean cancellationRequestedCalled;

        AbstractIntSortingSink(DefaultSink<? super Integer> downstream) {
            super(downstream);
        }

        @Override
        public final boolean cancellationRequested() {
            cancellationRequestedCalled = true;
            return false;
        }
    }

    /**
     * {@link DefaultSink} for implementing sort on SIZED int streams.
     */
    private static final class SizedIntSortingSink extends AbstractIntSortingSink {
        private int[] array;
        private int offset;

        SizedIntSortingSink(DefaultSink<? super Integer> downstream) {
            super(downstream);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            array = new int[(int) size];
        }

        @Override
        public void end() {
            Arrays.sort(array, 0, offset);
            downstream.begin(offset);
            if (!cancellationRequestedCalled) {
                for (int i = 0; i < offset; i++)
                    downstream.accept(array[i]);
            }
            else {
                for (int i = 0; i < offset && !downstream.cancellationRequested(); i++)
                    downstream.accept(array[i]);
            }
            downstream.end();
            array = null;
        }

        @Override
        public void accept(int t) {
            array[offset++] = t;
        }
    }

    /**
     * {@link DefaultSink} for implementing sort on int streams.
     */
    private static final class IntSortingSink extends AbstractIntSortingSink {
        private DefaultSpinedBuffer.OfInt b;

        IntSortingSink(DefaultSink<? super Integer> sink) {
            super(sink);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            b = (size > 0) ? new DefaultSpinedBuffer.OfInt((int) size) : new DefaultSpinedBuffer.OfInt();
        }

        @Override
        public void end() {
            int[] ints = b.asPrimitiveArray();
            Arrays.sort(ints);
            downstream.begin(ints.length);
            if (!cancellationRequestedCalled) {
                for (int anInt : ints)
                    downstream.accept(anInt);
            }
            else {
                for (int anInt : ints) {
                    if (downstream.cancellationRequested()) break;
                    downstream.accept(anInt);
                }
            }
            downstream.end();
        }

        @Override
        public void accept(int t) {
            b.accept(t);
        }
    }

    /**
     * Abstract {@link DefaultSink} for implementing sort on long streams.
     */
    private static abstract class AbstractLongSortingSink extends DefaultSink.ChainedLong<Long> {
        // true if cancellationRequested() has been called
        protected boolean cancellationRequestedCalled;

        AbstractLongSortingSink(DefaultSink<? super Long> downstream) {
            super(downstream);
        }

        @Override
        public final boolean cancellationRequested() {
            cancellationRequestedCalled = true;
            return false;
        }
    }

    /**
     * {@link DefaultSink} for implementing sort on SIZED long streams.
     */
    private static final class SizedLongSortingSink extends AbstractLongSortingSink {
        private long[] array;
        private int offset;

        SizedLongSortingSink(DefaultSink<? super Long> downstream) {
            super(downstream);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            array = new long[(int) size];
        }

        @Override
        public void end() {
            Arrays.sort(array, 0, offset);
            downstream.begin(offset);
            if (!cancellationRequestedCalled) {
                for (int i = 0; i < offset; i++)
                    downstream.accept(array[i]);
            }
            else {
                for (int i = 0; i < offset && !downstream.cancellationRequested(); i++)
                    downstream.accept(array[i]);
            }
            downstream.end();
            array = null;
        }

        @Override
        public void accept(long t) {
            array[offset++] = t;
        }
    }

    /**
     * {@link DefaultSink} for implementing sort on long streams.
     */
    private static final class LongSortingSink extends AbstractLongSortingSink {
        private DefaultSpinedBuffer.OfLong b;

        LongSortingSink(DefaultSink<? super Long> sink) {
            super(sink);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            b = (size > 0) ? new DefaultSpinedBuffer.OfLong((int) size) : new DefaultSpinedBuffer.OfLong();
        }

        @Override
        public void end() {
            long[] longs = b.asPrimitiveArray();
            Arrays.sort(longs);
            downstream.begin(longs.length);
            if (!cancellationRequestedCalled) {
                for (long aLong : longs)
                    downstream.accept(aLong);
            }
            else {
                for (long aLong : longs) {
                    if (downstream.cancellationRequested()) break;
                    downstream.accept(aLong);
                }
            }
            downstream.end();
        }

        @Override
        public void accept(long t) {
            b.accept(t);
        }
    }

    /**
     * Abstract {@link DefaultSink} for implementing sort on long streams.
     */
    private static abstract class AbstractDoubleSortingSink extends DefaultSink.ChainedDouble<Double> {
        // true if cancellationRequested() has been called
        protected boolean cancellationRequestedCalled;

        AbstractDoubleSortingSink(DefaultSink<? super Double> downstream) {
            super(downstream);
        }

        @Override
        public final boolean cancellationRequested() {
            cancellationRequestedCalled = true;
            return false;
        }
    }

    /**
     * {@link DefaultSink} for implementing sort on SIZED double streams.
     */
    private static final class SizedDoubleSortingSink extends AbstractDoubleSortingSink {
        private double[] array;
        private int offset;

        SizedDoubleSortingSink(DefaultSink<? super Double> downstream) {
            super(downstream);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            array = new double[(int) size];
        }

        @Override
        public void end() {
            Arrays.sort(array, 0, offset);
            downstream.begin(offset);
            if (!cancellationRequestedCalled) {
                for (int i = 0; i < offset; i++)
                    downstream.accept(array[i]);
            }
            else {
                for (int i = 0; i < offset && !downstream.cancellationRequested(); i++)
                    downstream.accept(array[i]);
            }
            downstream.end();
            array = null;
        }

        @Override
        public void accept(double t) {
            array[offset++] = t;
        }
    }

    /**
     * {@link AbstractDoubleSortingSink} for implementing sort on double streams.
     */
    private static final class DoubleSortingSink extends AbstractDoubleSortingSink {
        private DefaultSpinedBuffer.OfDouble b;

        DoubleSortingSink(DefaultSink<? super Double> sink) {
            super(sink);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            b = (size > 0) ? new DefaultSpinedBuffer.OfDouble((int) size) : new DefaultSpinedBuffer.OfDouble();
        }

        @Override
        public void end() {
            double[] doubles = b.asPrimitiveArray();
            Arrays.sort(doubles);
            downstream.begin(doubles.length);
            if (!cancellationRequestedCalled) {
                for (double aDouble : doubles)
                    downstream.accept(aDouble);
            }
            else {
                for (double aDouble : doubles) {
                    if (downstream.cancellationRequested()) break;
                    downstream.accept(aDouble);
                }
            }
            downstream.end();
        }

        @Override
        public void accept(double t) {
            b.accept(t);
        }
    }
}
