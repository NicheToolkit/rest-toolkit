package io.github.nichetoolkit.rest.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Abstract base class for an intermediate pipeline stage or pipeline source
 * stage implementing whose elements are of type {@code double}.
 *
 * @param <E_IN> type of elements in the upstream source
 *
 * @since 1.8
 */
abstract class StreamDoublePipeline<E_IN>
        extends StreamAbstractPipeline<E_IN, Double, DoubleStream>
        implements DoubleStream {

    /**
     * Constructor for the head of a stream pipeline.
     *
     * @param source {@code Supplier<Spliterator>} describing the stream source
     * @param sourceFlags the source flags for the stream source, described in
     * {@link DefaultStreamOpFlag}
     */
    StreamDoublePipeline(Supplier<? extends Spliterator<Double>> source,
                         int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    /**
     * Constructor for the head of a stream pipeline.
     *
     * @param source {@code Spliterator} describing the stream source
     * @param sourceFlags the source flags for the stream source, described in
     * {@link DefaultStreamOpFlag}
     */
    StreamDoublePipeline(Spliterator<Double> source,
                         int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    /**
     * Constructor for appending an intermediate operation onto an existing
     * pipeline.
     *
     * @param upstream the upstream element source.
     * @param opFlags the operation flags
     */
    StreamDoublePipeline(StreamAbstractPipeline<?, E_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    /**
     * Adapt a {@code DefaultSink<Double> to a {@code DoubleConsumer}, ideally simply
     * by casting.
     */
    private static DoubleConsumer adapt(DefaultSink<Double> sink) {
        if (sink instanceof DoubleConsumer) {
            return (DoubleConsumer) sink;
        } else {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(StreamAbstractPipeline.class,
                              "using DoubleStream.adapt(DefaultSink<Double> s)");
            return sink::accept;
        }
    }

    /**
     * Adapt a {@code Spliterator<Double>} to a {@code Spliterator.OfDouble}.
     *
     * @implNote
     * The implementation attempts to cast to a Spliterator.OfDouble, and throws
     * an exception if this cast is not possible.
     */
    private static Spliterator.OfDouble adapt(Spliterator<Double> s) {
        if (s instanceof Spliterator.OfDouble) {
            return (Spliterator.OfDouble) s;
        } else {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(StreamAbstractPipeline.class,
                              "using DoubleStream.adapt(Spliterator<Double> s)");
            throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
        }
    }


    // Shape-specific methods

    @Override
    final DefaultStreamShape getOutputShape() {
        return DefaultStreamShape.DOUBLE_VALUE;
    }

    @Override
    final <P_IN> DefaultNode<Double> evaluateToNode(DefaultPipelineHelper<Double> helper,
                                             Spliterator<P_IN> spliterator,
                                             boolean flattenTree,
                                             IntFunction<Double[]> generator) {
        return DefaultNodes.collectDouble(helper, spliterator, flattenTree);
    }

    @Override
    final <P_IN> Spliterator<Double> wrap(DefaultPipelineHelper<Double> ph,
                                          Supplier<Spliterator<P_IN>> supplier,
                                          boolean isParallel) {
        return new DefaultStreamSpliterators.DoubleWrappingSpliterator<>(ph, supplier, isParallel);
    }

    @Override
    @SuppressWarnings("unchecked")
    final Spliterator.OfDouble lazySpliterator(Supplier<? extends Spliterator<Double>> supplier) {
        return new DefaultStreamSpliterators.DelegatingSpliterator.OfDouble((Supplier<Spliterator.OfDouble>) supplier);
    }

    @Override
    final void forEachWithCancel(Spliterator<Double> spliterator, DefaultSink<Double> sink) {
        Spliterator.OfDouble spl = adapt(spliterator);
        DoubleConsumer adaptedDefaultSink = adapt(sink);
        do { } while (!sink.cancellationRequested() && spl.tryAdvance(adaptedDefaultSink));
    }

    @Override
    final  DefaultNode.Builder<Double> makeNodeBuilder(long exactSizeIfKnown, IntFunction<Double[]> generator) {
        return DefaultNodes.doubleBuilder(exactSizeIfKnown);
    }


    // DoubleStream

    @Override
    public final PrimitiveIterator.OfDouble iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override
    public final Spliterator.OfDouble spliterator() {
        return adapt(super.spliterator());
    }

    // Stateless intermediate ops from DoubleStream

    @Override
    public final Stream<Double> boxed() {
        return mapToObj(Double::valueOf);
    }

    @Override
    public final DoubleStream map(DoubleUnaryOperator mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Double>(this, DefaultStreamShape.DOUBLE_VALUE,
                                       DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedDouble<Double>(sink) {
                    @Override
                    public void accept(double t) {
                        downstream.accept(mapper.applyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override
    public final <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return new StreamReferencePipeline.StatelessOp<Double, U>(this, DefaultStreamShape.DOUBLE_VALUE,
                                                            DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<U> sink) {
                return new DefaultSink.ChainedDouble<U>(sink) {
                    @Override
                    public void accept(double t) {
                        downstream.accept(mapper.apply(t));
                    }
                };
            }
        };
    }

    @Override
    public final IntStream mapToInt(DoubleToIntFunction mapper) {
        Objects.requireNonNull(mapper);
        return new StreamIntPipeline.StatelessOp<Double>(this, DefaultStreamShape.DOUBLE_VALUE,
                                                   DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedDouble<Integer>(sink) {
                    @Override
                    public void accept(double t) {
                        downstream.accept(mapper.applyAsInt(t));
                    }
                };
            }
        };
    }

    @Override
    public final LongStream mapToLong(DoubleToLongFunction mapper) {
        Objects.requireNonNull(mapper);
        return new StreamLongPipeline.StatelessOp<Double>(this, DefaultStreamShape.DOUBLE_VALUE,
                                                    DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Long> sink) {
                return new DefaultSink.ChainedDouble<Long>(sink) {
                    @Override
                    public void accept(double t) {
                        downstream.accept(mapper.applyAsLong(t));
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Double>(this, DefaultStreamShape.DOUBLE_VALUE,
                                        DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedDouble<Double>(sink) {
                    // true if cancellationRequested() has been called
                    boolean cancellationRequestedCalled;

                    // cache the consumer to avoid creation on every accepted element
                    DoubleConsumer downstreamAsDouble = downstream::accept;

                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(double t) {
                        try (DoubleStream result = mapper.apply(t)) {
                            if (result != null) {
                                if (!cancellationRequestedCalled) {
                                    result.sequential().forEach(downstreamAsDouble);
                                }
                                else {
                                    Spliterator.OfDouble s = result.sequential().spliterator();
                                    do { } while (!downstream.cancellationRequested() && s.tryAdvance(downstreamAsDouble));
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
    public DoubleStream unordered() {
        if (!isOrdered())
            return this;
        return new StatelessOp<Double>(this, DefaultStreamShape.DOUBLE_VALUE, DefaultStreamOpFlag.NOT_ORDERED) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Double> sink) {
                return sink;
            }
        };
    }

    @Override
    public final DoubleStream filter(DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<Double>(this, DefaultStreamShape.DOUBLE_VALUE,
                                       DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedDouble<Double>(sink) {
                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(double t) {
                        if (predicate.test(t))
                            downstream.accept(t);
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream peek(DoubleConsumer action) {
        Objects.requireNonNull(action);
        return new StatelessOp<Double>(this, DefaultStreamShape.DOUBLE_VALUE,
                                       0) {
            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedDouble<Double>(sink) {
                    @Override
                    public void accept(double t) {
                        action.accept(t);
                        downstream.accept(t);
                    }
                };
            }
        };
    }

    // Stateful intermediate ops from DoubleStream

    @Override
    public final DoubleStream limit(long maxSize) {
        if (maxSize < 0)
            throw new IllegalArgumentException(Long.toString(maxSize));
        return StreamSliceOps.makeDouble(this, (long) 0, maxSize);
    }

    @Override
    public final DoubleStream skip(long n) {
        if (n < 0)
            throw new IllegalArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else {
            long limit = -1;
            return StreamSliceOps.makeDouble(this, n, limit);
        }
    }

    @Override
    public final DoubleStream sorted() {
        return StreamSortedOps.makeDouble(this);
    }

    @Override
    public final DoubleStream distinct() {
        // While functional and quick to implement, this approach is not very efficient.
        // An efficient version requires a double-specific map/set implementation.
        return boxed().distinct().mapToDouble(i -> (double) i);
    }

    // Terminal ops from DoubleStream

    @Override
    public void forEach(DoubleConsumer consumer) {
        evaluate(DefaultForEachOps.makeDouble(consumer, false));
    }

    @Override
    public void forEachOrdered(DoubleConsumer consumer) {
        evaluate(DefaultForEachOps.makeDouble(consumer, true));
    }

    @Override
    public final double sum() {
        /*
         * In the arrays allocated for the collect operation, index 0
         * holds the high-order bits of the running sum, index 1 holds
         * the low-order bits of the sum computed via compensated
         * summation, and index 2 holds the simple sum used to compute
         * the proper result if the stream contains infinite values of
         * the same sign.
         */
        double[] summation = collect(() -> new double[3],
                               (ll, d) -> {
                                   RestCollectors.sumWithCompensation(ll, d);
                                   ll[2] += d;
                               },
                               (ll, rr) -> {
                                   RestCollectors.sumWithCompensation(ll, rr[0]);
                                   RestCollectors.sumWithCompensation(ll, rr[1]);
                                   ll[2] += rr[2];
                               });

        return RestCollectors.computeFinalSum(summation);
    }

    @Override
    public final OptionalDouble min() {
        return reduce(Math::min);
    }

    @Override
    public final OptionalDouble max() {
        return reduce(Math::max);
    }

    /**
     * {@inheritDoc}
     *
     * @implNote The {@code double} format can represent all
     * consecutive integers in the range -2<sup>53</sup> to
     * 2<sup>53</sup>. If the pipeline has more than 2<sup>53</sup>
     * values, the divisor in the average computation will saturate at
     * 2<sup>53</sup>, leading to additional numerical errors.
     */
    @Override
    public final OptionalDouble average() {
        /*
         * In the arrays allocated for the collect operation, index 0
         * holds the high-order bits of the running sum, index 1 holds
         * the low-order bits of the sum computed via compensated
         * summation, index 2 holds the number of values seen, index 3
         * holds the simple sum.
         */
        double[] avg = collect(() -> new double[4],
                               (ll, d) -> {
                                   ll[2]++;
                                   RestCollectors.sumWithCompensation(ll, d);
                                   ll[3] += d;
                               },
                               (ll, rr) -> {
                                   RestCollectors.sumWithCompensation(ll, rr[0]);
                                   RestCollectors.sumWithCompensation(ll, rr[1]);
                                   ll[2] += rr[2];
                                   ll[3] += rr[3];
                               });
        return avg[2] > 0
            ? OptionalDouble.of(RestCollectors.computeFinalSum(avg) / avg[2])
            : OptionalDouble.empty();
    }

    @Override
    public final long count() {
        return mapToLong(e -> 1L).sum();
    }

    @Override
    public final DoubleSummaryStatistics summaryStatistics() {
        return collect(DoubleSummaryStatistics::new, DoubleSummaryStatistics::accept,
                       DoubleSummaryStatistics::combine);
    }

    @Override
    public final double reduce(double identity, DoubleBinaryOperator op) {
        return evaluate(DefaultReduceOps.makeDouble(identity, op));
    }

    @Override
    public final OptionalDouble reduce(DoubleBinaryOperator op) {
        return evaluate(DefaultReduceOps.makeDouble(op));
    }

    @Override
    public final <R> R collect(Supplier<R> supplier,
                               ObjDoubleConsumer<R> accumulator,
                               BiConsumer<R, R> combiner) {
        Objects.requireNonNull(combiner);
        BinaryOperator<R> operator = (left, right) -> {
            combiner.accept(left, right);
            return left;
        };
        return evaluate(DefaultReduceOps.makeDouble(supplier, accumulator, operator));
    }

    @Override
    public final boolean anyMatch(DoublePredicate predicate) {
        return evaluate(DefaultMatchOps.makeDouble(predicate, DefaultMatchOps.MatchKind.ANY));
    }

    @Override
    public final boolean allMatch(DoublePredicate predicate) {
        return evaluate(DefaultMatchOps.makeDouble(predicate, DefaultMatchOps.MatchKind.ALL));
    }

    @Override
    public final boolean noneMatch(DoublePredicate predicate) {
        return evaluate(DefaultMatchOps.makeDouble(predicate, DefaultMatchOps.MatchKind.NONE));
    }

    @Override
    public final OptionalDouble findFirst() {
        return evaluate(DefaultFindOps.makeDouble(true));
    }

    @Override
    public final OptionalDouble findAny() {
        return evaluate(DefaultFindOps.makeDouble(false));
    }

    @Override
    public final double[] toArray() {
        return DefaultNodes.flattenDouble((DefaultNode.OfDouble) evaluateToArrayNode(Double[]::new))
                        .asPrimitiveArray();
    }

    //

    /**
     * Source stage of a DoubleStream
     *
     * @param <E_IN> type of elements in the upstream source
     */
    static class Head<E_IN> extends StreamDoublePipeline<E_IN> {
        /**
         * Constructor for the source stage of a DoubleStream.
         *
         * @param source {@code Supplier<Spliterator>} describing the stream
         *               source
         * @param sourceFlags the source flags for the stream source, described
         *                    in {@link DefaultStreamOpFlag}
         * @param parallel {@code true} if the pipeline is parallel
         */
        Head(Supplier<? extends Spliterator<Double>> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        /**
         * Constructor for the source stage of a DoubleStream.
         *
         * @param source {@code Spliterator} describing the stream source
         * @param sourceFlags the source flags for the stream source, described
         *                    in {@link DefaultStreamOpFlag}
         * @param parallel {@code true} if the pipeline is parallel
         */
        Head(Spliterator<Double> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override
        final DefaultSink<E_IN> opWrapSink(int flags, DefaultSink<Double> sink) {
            throw new UnsupportedOperationException();
        }

        // Optimized sequential terminal operations for the head of the pipeline

        @Override
        public void forEach(DoubleConsumer consumer) {
            if (!isParallel()) {
                adapt(sourceStageSpliterator()).forEachRemaining(consumer);
            }
            else {
                super.forEach(consumer);
            }
        }

        @Override
        public void forEachOrdered(DoubleConsumer consumer) {
            if (!isParallel()) {
                adapt(sourceStageSpliterator()).forEachRemaining(consumer);
            }
            else {
                super.forEachOrdered(consumer);
            }
        }

    }

    /**
     * Base class for a stateless intermediate stage of a DoubleStream.
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    abstract static class StatelessOp<E_IN> extends StreamDoublePipeline<E_IN> {
        /**
         * Construct a new DoubleStream by appending a stateless intermediate
         * operation to an existing stream.
         *
         * @param upstream the upstream pipeline stage
         * @param inputShape the stream shape for the upstream pipeline stage
         * @param opFlags operation flags for the new stage
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
     * Base class for a stateful intermediate stage of a DoubleStream.
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    abstract static class StatefulOp<E_IN> extends StreamDoublePipeline<E_IN> {
        /**
         * Construct a new DoubleStream by appending a stateful intermediate
         * operation to an existing stream.
         *
         * @param upstream the upstream pipeline stage
         * @param inputShape the stream shape for the upstream pipeline stage
         * @param opFlags operation flags for the new stage
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
        abstract <P_IN> DefaultNode<Double> opEvaluateParallel(DefaultPipelineHelper<Double> helper,
                                                        Spliterator<P_IN> spliterator,
                                                        IntFunction<Double[]> generator);
    }
}
