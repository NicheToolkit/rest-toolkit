package io.github.nichetoolkit.rest.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Abstract base class for an intermediate pipeline stage or pipeline source
 * stage implementing whose elements are of type {@code int}.
 *
 * @param <E_IN> type of elements in the upstream source
 * @since 1.8
 */
abstract class StreamIntPipeline<E_IN>
        extends StreamAbstractPipeline<E_IN, Integer, IntStream>
        implements IntStream {

    /**
     * Constructor for the head of a stream pipeline.
     *
     * @param source {@code Supplier<Spliterator>} describing the stream source
     * @param sourceFlags The source flags for the stream source, described in
     *        {@link DefaultStreamOpFlag}
     * @param parallel {@code true} if the pipeline is parallel
     */
    StreamIntPipeline(Supplier<? extends Spliterator<Integer>> source,
                      int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    /**
     * Constructor for the head of a stream pipeline.
     *
     * @param source {@code Spliterator} describing the stream source
     * @param sourceFlags The source flags for the stream source, described in
     *        {@link DefaultStreamOpFlag}
     * @param parallel {@code true} if the pipeline is parallel
     */
    StreamIntPipeline(Spliterator<Integer> source,
                      int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    /**
     * Constructor for appending an intermediate operation onto an existing
     * pipeline.
     *
     * @param upstream the upstream element source
     * @param opFlags the operation flags for the new operation
     */
    StreamIntPipeline(StreamAbstractPipeline<?, E_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    /**
     * Adapt a {@code DefaultSink<Integer> to an {@code IntConsumer}, ideally simply
     * by casting.
     */
    private static IntConsumer adapt(DefaultSink<Integer> sink) {
        if (sink instanceof IntConsumer) {
            return (IntConsumer) sink;
        }
        else {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(StreamAbstractPipeline.class,
                              "using IntStream.adapt(DefaultSink<Integer> s)");
            return sink::accept;
        }
    }

    /**
     * Adapt a {@code Spliterator<Integer>} to a {@code Spliterator.OfInt}.
     *
     * @implNote
     * The implementation attempts to cast to a Spliterator.OfInt, and throws an
     * exception if this cast is not possible.
     */
    private static Spliterator.OfInt adapt(Spliterator<Integer> s) {
        if (s instanceof Spliterator.OfInt) {
            return (Spliterator.OfInt) s;
        }
        else {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(StreamAbstractPipeline.class,
                              "using IntStream.adapt(Spliterator<Integer> s)");
            throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
        }
    }


    // Shape-specific methods

    @Override
    final DefaultStreamShape getOutputShape() {
        return DefaultStreamShape.INT_VALUE;
    }

    @Override
    final <P_IN> DefaultNode<Integer> evaluateToNode(DefaultPipelineHelper<Integer> helper,
                                              Spliterator<P_IN> spliterator,
                                              boolean flattenTree,
                                              IntFunction<Integer[]> generator) {
        return DefaultNodes.collectInt(helper, spliterator, flattenTree);
    }

    @Override
    final <P_IN> Spliterator<Integer> wrap(DefaultPipelineHelper<Integer> ph,
                                           Supplier<Spliterator<P_IN>> supplier,
                                           boolean isParallel) {
        return new DefaultStreamSpliterators.IntWrappingSpliterator<>(ph, supplier, isParallel);
    }

    @Override
    @SuppressWarnings("unchecked")
    final Spliterator.OfInt lazySpliterator(Supplier<? extends Spliterator<Integer>> supplier) {
        return new DefaultStreamSpliterators.DelegatingSpliterator.OfInt((Supplier<Spliterator.OfInt>) supplier);
    }

    @Override
    final void forEachWithCancel(Spliterator<Integer> spliterator, DefaultSink<Integer> sink) {
        Spliterator.OfInt spl = adapt(spliterator);
        IntConsumer adaptedDefaultSink = adapt(sink);
        do { } while (!sink.cancellationRequested() && spl.tryAdvance(adaptedDefaultSink));
    }

    @Override
    final DefaultNode.Builder<Integer> makeNodeBuilder(long exactSizeIfKnown,
                                                IntFunction<Integer[]> generator) {
        return DefaultNodes.intBuilder(exactSizeIfKnown);
    }


    // IntStream

    @Override
    public final PrimitiveIterator.OfInt iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override
    public final Spliterator.OfInt spliterator() {
        return adapt(super.spliterator());
    }

    // Stateless intermediate ops from IntStream

    @Override
    public final LongStream asLongStream() {
        return new StreamLongPipeline.StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                                     DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Long> sink) {
                return new DefaultSink.ChainedInt<Long>(sink) {
                    @Override
                    public void accept(int t) {
                        downstream.accept((long) t);
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream asDoubleStream() {
        return new StreamDoublePipeline.StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                                       DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedInt<Double>(sink) {
                    @Override
                    public void accept(int t) {
                        downstream.accept((double) t);
                    }
                };
            }
        };
    }

    @Override
    public final Stream<Integer> boxed() {
        return mapToObj(Integer::valueOf);
    }

    @Override
    public final IntStream map(IntUnaryOperator mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                        DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedInt<Integer>(sink) {
                    @Override
                    public void accept(int t) {
                        downstream.accept(mapper.applyAsInt(t));
                    }
                };
            }
        };
    }

    @Override
    public final <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return new StreamReferencePipeline.StatelessOp<Integer, U>(this, DefaultStreamShape.INT_VALUE,
                                                             DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<U> sink) {
                return new DefaultSink.ChainedInt<U>(sink) {
                    @Override
                    public void accept(int t) {
                        downstream.accept(mapper.apply(t));
                    }
                };
            }
        };
    }

    @Override
    public final LongStream mapToLong(IntToLongFunction mapper) {
        Objects.requireNonNull(mapper);
        return new StreamLongPipeline.StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                                     DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Long> sink) {
                return new DefaultSink.ChainedInt<Long>(sink) {
                    @Override
                    public void accept(int t) {
                        downstream.accept(mapper.applyAsLong(t));
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream mapToDouble(IntToDoubleFunction mapper) {
        Objects.requireNonNull(mapper);
        return new StreamDoublePipeline.StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                                       DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedInt<Double>(sink) {
                    @Override
                    public void accept(int t) {
                        downstream.accept(mapper.applyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override
    public final IntStream flatMap(IntFunction<? extends IntStream> mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                        DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedInt<Integer>(sink) {
                    // true if cancellationRequested() has been called
                    boolean cancellationRequestedCalled;

                    // cache the consumer to avoid creation on every accepted element
                    IntConsumer downstreamAsInt = downstream::accept;

                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(int t) {
                        try (IntStream result = mapper.apply(t)) {
                            if (result != null) {
                                if (!cancellationRequestedCalled) {
                                    result.sequential().forEach(downstreamAsInt);
                                }
                                else {
                                    Spliterator.OfInt s = result.sequential().spliterator();
                                    do { } while (!downstream.cancellationRequested() && s.tryAdvance(downstreamAsInt));
                                }
                            }
                        }
                    }

                    @Override
                    public boolean cancellationRequested() {
                        // If this method is called then an operation within the stream
                        // pipeline is short-circuiting (see StreamAbstractPipeline.copyInto).
                        // Note that we cannot differentiate between an upstream or
                        // downstream operation
                        cancellationRequestedCalled = true;
                        return downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public IntStream unordered() {
        if (!isOrdered())
            return this;
        return new StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE, DefaultStreamOpFlag.NOT_ORDERED) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return sink;
            }
        };
    }

    @Override
    public final IntStream filter(IntPredicate predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                        DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedInt<Integer>(sink) {
                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(int t) {
                        if (predicate.test(t))
                            downstream.accept(t);
                    }
                };
            }
        };
    }

    @Override
    public final IntStream peek(IntConsumer action) {
        Objects.requireNonNull(action);
        return new StatelessOp<Integer>(this, DefaultStreamShape.INT_VALUE,
                                        0) {
            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedInt<Integer>(sink) {
                    @Override
                    public void accept(int t) {
                        action.accept(t);
                        downstream.accept(t);
                    }
                };
            }
        };
    }

    // Stateful intermediate ops from IntStream

    @Override
    public final IntStream limit(long maxSize) {
        if (maxSize < 0)
            throw new IllegalArgumentException(Long.toString(maxSize));
        return StreamSliceOps.makeInt(this, 0, maxSize);
    }

    @Override
    public final IntStream skip(long n) {
        if (n < 0)
            throw new IllegalArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else
            return StreamSliceOps.makeInt(this, n, -1);
    }

    @Override
    public final IntStream sorted() {
        return StreamSortedOps.makeInt(this);
    }

    @Override
    public final IntStream distinct() {
        // While functional and quick to implement, this approach is not very efficient.
        // An efficient version requires an int-specific map/set implementation.
        return boxed().distinct().mapToInt(i -> i);
    }

    // Terminal ops from IntStream

    @Override
    public void forEach(IntConsumer action) {
        evaluate(DefaultForEachOps.makeInt(action, false));
    }

    @Override
    public void forEachOrdered(IntConsumer action) {
        evaluate(DefaultForEachOps.makeInt(action, true));
    }

    @Override
    public final int sum() {
        return reduce(0, Integer::sum);
    }

    @Override
    public final OptionalInt min() {
        return reduce(Math::min);
    }

    @Override
    public final OptionalInt max() {
        return reduce(Math::max);
    }

    @Override
    public final long count() {
        return mapToLong(e -> 1L).sum();
    }

    @Override
    public final OptionalDouble average() {
        long[] avg = collect(() -> new long[2],
                             (ll, i) -> {
                                 ll[0]++;
                                 ll[1] += i;
                             },
                             (ll, rr) -> {
                                 ll[0] += rr[0];
                                 ll[1] += rr[1];
                             });
        return avg[0] > 0
               ? OptionalDouble.of((double) avg[1] / avg[0])
               : OptionalDouble.empty();
    }

    @Override
    public final IntSummaryStatistics summaryStatistics() {
        return collect(IntSummaryStatistics::new, IntSummaryStatistics::accept,
                       IntSummaryStatistics::combine);
    }

    @Override
    public final int reduce(int identity, IntBinaryOperator op) {
        return evaluate(DefaultReduceOps.makeInt(identity, op));
    }

    @Override
    public final OptionalInt reduce(IntBinaryOperator op) {
        return evaluate(DefaultReduceOps.makeInt(op));
    }

    @Override
    public final <R> R collect(Supplier<R> supplier,
                               ObjIntConsumer<R> accumulator,
                               BiConsumer<R, R> combiner) {
        Objects.requireNonNull(combiner);
        BinaryOperator<R> operator = (left, right) -> {
            combiner.accept(left, right);
            return left;
        };
        return evaluate(DefaultReduceOps.makeInt(supplier, accumulator, operator));
    }

    @Override
    public final boolean anyMatch(IntPredicate predicate) {
        return evaluate(DefaultMatchOps.makeInt(predicate, DefaultMatchOps.MatchKind.ANY));
    }

    @Override
    public final boolean allMatch(IntPredicate predicate) {
        return evaluate(DefaultMatchOps.makeInt(predicate, DefaultMatchOps.MatchKind.ALL));
    }

    @Override
    public final boolean noneMatch(IntPredicate predicate) {
        return evaluate(DefaultMatchOps.makeInt(predicate, DefaultMatchOps.MatchKind.NONE));
    }

    @Override
    public final OptionalInt findFirst() {
        return evaluate(DefaultFindOps.makeInt(true));
    }

    @Override
    public final OptionalInt findAny() {
        return evaluate(DefaultFindOps.makeInt(false));
    }

    @Override
    public final int[] toArray() {
        return DefaultNodes.flattenInt((DefaultNode.OfInt) evaluateToArrayNode(Integer[]::new))
                        .asPrimitiveArray();
    }

    //

    /**
     * Source stage of an IntStream.
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    static class Head<E_IN> extends StreamIntPipeline<E_IN> {
        /**
         * Constructor for the source stage of an IntStream.
         *
         * @param source {@code Supplier<Spliterator>} describing the stream
         *               source
         * @param sourceFlags the source flags for the stream source, described
         *                    in {@link DefaultStreamOpFlag}
         * @param parallel {@code true} if the pipeline is parallel
         */
        Head(Supplier<? extends Spliterator<Integer>> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        /**
         * Constructor for the source stage of an IntStream.
         *
         * @param source {@code Spliterator} describing the stream source
         * @param sourceFlags the source flags for the stream source, described
         *                    in {@link DefaultStreamOpFlag}
         * @param parallel {@code true} if the pipeline is parallel
         */
        Head(Spliterator<Integer> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override
        final DefaultSink<E_IN> opWrapSink(int flags, DefaultSink<Integer> sink) {
            throw new UnsupportedOperationException();
        }

        // Optimized sequential terminal operations for the head of the pipeline

        @Override
        public void forEach(IntConsumer action) {
            if (!isParallel()) {
                adapt(sourceStageSpliterator()).forEachRemaining(action);
            }
            else {
                super.forEach(action);
            }
        }

        @Override
        public void forEachOrdered(IntConsumer action) {
            if (!isParallel()) {
                adapt(sourceStageSpliterator()).forEachRemaining(action);
            }
            else {
                super.forEachOrdered(action);
            }
        }
    }

    /**
     * Base class for a stateless intermediate stage of an IntStream
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    abstract static class StatelessOp<E_IN> extends StreamIntPipeline<E_IN> {
        /**
         * Construct a new IntStream by appending a stateless intermediate
         * operation to an existing stream.
         * @param upstream The upstream pipeline stage
         * @param inputShape The stream shape for the upstream pipeline stage
         * @param opFlags Operation flags for the new stage
         */
        StatelessOp(StreamAbstractPipeline<?, E_IN, ?> upstream,
                    DefaultStreamShape inputShape,
                    int opFlags) {
            super(upstream, opFlags);
            assert upstream.getOutputShape() == inputShape;
        }

        @Override
        final boolean opIsStateful() {
            return false;
        }
    }

    /**
     * Base class for a stateful intermediate stage of an IntStream.
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    abstract static class StatefulOp<E_IN> extends StreamIntPipeline<E_IN> {
        /**
         * Construct a new IntStream by appending a stateful intermediate
         * operation to an existing stream.
         * @param upstream The upstream pipeline stage
         * @param inputShape The stream shape for the upstream pipeline stage
         * @param opFlags Operation flags for the new stage
         */
        StatefulOp(StreamAbstractPipeline<?, E_IN, ?> upstream,
                   DefaultStreamShape inputShape,
                   int opFlags) {
            super(upstream, opFlags);
            assert upstream.getOutputShape() == inputShape;
        }

        @Override
        final boolean opIsStateful() {
            return true;
        }

        @Override
        abstract <P_IN> DefaultNode<Integer> opEvaluateParallel(DefaultPipelineHelper<Integer> helper,
                                                         Spliterator<P_IN> spliterator,
                                                         IntFunction<Integer[]> generator);
    }
}
