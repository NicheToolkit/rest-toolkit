package io.github.nichetoolkit.rest.stream;

import org.springframework.lang.NonNull;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

abstract class StreamReferencePipeline<P_IN, P_OUT>
        extends StreamAbstractPipeline<P_IN, P_OUT, Stream<P_OUT>>
        implements Stream<P_OUT>  {

    StreamReferencePipeline(Supplier<? extends Spliterator<?>> source,
                            int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    StreamReferencePipeline(Spliterator<?> source,
                            int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    StreamReferencePipeline(StreamAbstractPipeline<?, P_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    // Shape-specific methods

    @Override
    final DefaultStreamShape getOutputShape() {
        return DefaultStreamShape.REFERENCE;
    }

    @Override
    final <P_IN> DefaultNode<P_OUT> evaluateToNode(DefaultPipelineHelper<P_OUT> helper,
                                            Spliterator<P_IN> spliterator,
                                            boolean flattenTree,
                                            IntFunction<P_OUT[]> generator) {
        return DefaultNodes.collect(helper, spliterator, flattenTree, generator);
    }

    @Override
    final <P_IN> Spliterator<P_OUT> wrap(DefaultPipelineHelper<P_OUT> ph,
                                     Supplier<Spliterator<P_IN>> supplier,
                                     boolean isParallel) {
        return new DefaultStreamSpliterators.WrappingSpliterator<>(ph, supplier, isParallel);
    }

    @Override
    final Spliterator<P_OUT> lazySpliterator(Supplier<? extends Spliterator<P_OUT>> supplier) {
        return new DefaultStreamSpliterators.DelegatingSpliterator<>(supplier);
    }

    @Override
    final void forEachWithCancel(Spliterator<P_OUT> spliterator, DefaultSink<P_OUT> sink) {
        do { } while (!sink.cancellationRequested() && spliterator.tryAdvance(sink));
    }

    @Override
    final DefaultNode.Builder<P_OUT> makeNodeBuilder(long exactSizeIfKnown, IntFunction<P_OUT[]> generator) {
        return DefaultNodes.builder(exactSizeIfKnown, generator);
    }


    // BaseStream

    @NonNull
    @Override
    public final Iterator<P_OUT> iterator() {
        return Spliterators.iterator(spliterator());
    }


    // Stream

    // Stateless intermediate operations from Stream

    @NonNull
    @Override
    public Stream<P_OUT> unordered() {
        if (!isOrdered())
            return this;
        return new StatelessOp<P_OUT, P_OUT>(this, DefaultStreamShape.REFERENCE, DefaultStreamOpFlag.NOT_ORDERED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<P_OUT> sink) {
                return sink;
            }
        };
    }

    @Override
    public final Stream<P_OUT> filter(Predicate<? super P_OUT> predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<P_OUT, P_OUT>(this, DefaultStreamShape.REFERENCE,
                                     DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<P_OUT> sink) {
                return new DefaultSink.ChainedReference<P_OUT, P_OUT>(sink) {
                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(P_OUT u) {
                        if (predicate.test(u))
                            downstream.accept(u);
                    }
                };
            }
        };
    }

    @Override
    public final <R> Stream<R> map(Function<? super P_OUT, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<P_OUT, R>(this, DefaultStreamShape.REFERENCE,
                                     DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<R> sink) {
                return new DefaultSink.ChainedReference<P_OUT, R>(sink) {
                    @Override
                    public void accept(P_OUT u) {
                        downstream.accept(mapper.apply(u));
                    }
                };
            }
        };
    }

    @Override
    public final IntStream mapToInt(ToIntFunction<? super P_OUT> mapper) {
        Objects.requireNonNull(mapper);
        return new StreamIntPipeline.StatelessOp<P_OUT>(this, DefaultStreamShape.REFERENCE,
                                              DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedReference<P_OUT, Integer>(sink) {
                    @Override
                    public void accept(P_OUT u) {
                        downstream.accept(mapper.applyAsInt(u));
                    }
                };
            }
        };
    }

    @Override
    public final LongStream mapToLong(ToLongFunction<? super P_OUT> mapper) {
        Objects.requireNonNull(mapper);
        return new StreamLongPipeline.StatelessOp<P_OUT>(this, DefaultStreamShape.REFERENCE,
                                      DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<Long> sink) {
                return new DefaultSink.ChainedReference<P_OUT, Long>(sink) {
                    @Override
                    public void accept(P_OUT u) {
                        downstream.accept(mapper.applyAsLong(u));
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream mapToDouble(ToDoubleFunction<? super P_OUT> mapper) {
        Objects.requireNonNull(mapper);
        return new StreamDoublePipeline.StatelessOp<P_OUT>(this, DefaultStreamShape.REFERENCE,
                                        DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedReference<P_OUT, Double>(sink) {
                    @Override
                    public void accept(P_OUT u) {
                        downstream.accept(mapper.applyAsDouble(u));
                    }
                };
            }
        };
    }

    @Override
    public final <R> Stream<R> flatMap(Function<? super P_OUT, ? extends Stream<? extends R>> mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<P_OUT, R>(this, DefaultStreamShape.REFERENCE,
                                     DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<R> sink) {
                return new DefaultSink.ChainedReference<P_OUT, R>(sink) {
                    // true if cancellationRequested() has been called
                    boolean cancellationRequestedCalled;

                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(P_OUT u) {
                        try (Stream<? extends R> result = mapper.apply(u)) {
                            if (result != null) {
                                if (!cancellationRequestedCalled) {
                                    result.sequential().forEach(downstream);
                                }
                                else {
                                    Spliterator<? extends R> s = result.sequential().spliterator();
                                    do { } while (!downstream.cancellationRequested() && s.tryAdvance(downstream));
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
    public final IntStream flatMapToInt(Function<? super P_OUT, ? extends IntStream> mapper) {
        Objects.requireNonNull(mapper);
        return new StreamIntPipeline.StatelessOp<P_OUT>(this, DefaultStreamShape.REFERENCE,
                                              DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedReference<P_OUT, Integer>(sink) {
                    // true if cancellationRequested() has been called
                    boolean cancellationRequestedCalled;

                    // cache the consumer to avoid creation on every accepted element
                    IntConsumer downstreamAsInt = downstream::accept;

                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(P_OUT u) {
                        try (IntStream result = mapper.apply(u)) {
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
                        cancellationRequestedCalled = true;
                        return downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream flatMapToDouble(Function<? super P_OUT, ? extends DoubleStream> mapper) {
        Objects.requireNonNull(mapper);
        return new StreamDoublePipeline.StatelessOp<P_OUT>(this, DefaultStreamShape.REFERENCE,
                                                     DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedReference<P_OUT, Double>(sink) {
                    // true if cancellationRequested() has been called
                    boolean cancellationRequestedCalled;

                    // cache the consumer to avoid creation on every accepted element
                    DoubleConsumer downstreamAsDouble = downstream::accept;

                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(P_OUT u) {
                        try (DoubleStream result = mapper.apply(u)) {
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
                        cancellationRequestedCalled = true;
                        return downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public final LongStream flatMapToLong(Function<? super P_OUT, ? extends LongStream> mapper) {
        Objects.requireNonNull(mapper);
        // We can do better than this, by polling cancellationRequested when stream is infinite
        return new StreamLongPipeline.StatelessOp<P_OUT>(this, DefaultStreamShape.REFERENCE,
                                                   DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<Long> sink) {
                return new DefaultSink.ChainedReference<P_OUT, Long>(sink) {
                    // true if cancellationRequested() has been called
                    boolean cancellationRequestedCalled;

                    // cache the consumer to avoid creation on every accepted element
                    LongConsumer downstreamAsLong = downstream::accept;

                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(P_OUT u) {
                        try (LongStream result = mapper.apply(u)) {
                            if (result != null) {
                                if (!cancellationRequestedCalled) {
                                    result.sequential().forEach(downstreamAsLong);
                                }
                                else {
                                    Spliterator.OfLong s = result.sequential().spliterator();
                                    do { } while (!downstream.cancellationRequested() && s.tryAdvance(downstreamAsLong));
                                }
                            }
                        }
                    }

                    @Override
                    public boolean cancellationRequested() {
                        cancellationRequestedCalled = true;
                        return downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override
    public final Stream<P_OUT> peek(Consumer<? super P_OUT> action) {
        Objects.requireNonNull(action);
        return new StatelessOp<P_OUT, P_OUT>(this, DefaultStreamShape.REFERENCE,
                                     0) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<P_OUT> sink) {
                return new DefaultSink.ChainedReference<P_OUT, P_OUT>(sink) {
                    @Override
                    public void accept(P_OUT u) {
                        action.accept(u);
                        downstream.accept(u);
                    }
                };
            }
        };
    }

    // Stateful intermediate operations from Stream

    @Override
    public final Stream<P_OUT> distinct() {
        return StreamDistinctOps.makeRef(this);
    }

    @Override
    public final Stream<P_OUT> sorted() {
        return StreamDistinctOps.makeRef(this);
    }

    @Override
    public final Stream<P_OUT> sorted(Comparator<? super P_OUT> comparator) {
        return StreamSortedOps.makeRef(this, comparator);
    }

    @Override
    public final Stream<P_OUT> limit(long maxSize) {
        if (maxSize < 0)
            throw new IllegalArgumentException(Long.toString(maxSize));
        return StreamSliceOps.makeRef(this, 0, maxSize);
    }

    @Override
    public final Stream<P_OUT> skip(long n) {
        if (n < 0)
            throw new IllegalArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else
            return StreamSliceOps.makeRef(this, n, -1);
    }

    // Terminal operations from Stream

    @Override
    public void forEach(Consumer<? super P_OUT> action) {
        evaluate(DefaultForEachOps.makeRef(action, false));
    }

    @Override
    public void forEachOrdered(Consumer<? super P_OUT> action) {
        evaluate(DefaultForEachOps.makeRef(action, true));
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public final <A> A[] toArray(IntFunction<A[]> generator) {
        // Since A has no relation to U (not possible to declare that A is an upper bound of U)
        // there will be no static type checking.
        // Therefore use a raw type and assume A == U rather than propagating the separation of A and U
        // throughout the code-base.
        // The runtime type of U is never checked for equality with the component type of the runtime type of A[].
        // Runtime checking will be performed when an element is stored in A[], thus if A is not a
        // super type of U an ArrayStoreException will be thrown.
        @SuppressWarnings("rawtypes")
        IntFunction rawGenerator = (IntFunction) generator;
        return (A[]) DefaultNodes.flatten(evaluateToArrayNode(rawGenerator), rawGenerator)
                              .asArray(rawGenerator);
    }

    @NonNull
    @Override
    public final Object[] toArray() {
        return toArray(Object[]::new);
    }

    @Override
    public final boolean anyMatch(Predicate<? super P_OUT> predicate) {
        return evaluate(DefaultMatchOps.makeRef(predicate, DefaultMatchOps.MatchKind.ANY));
    }

    @Override
    public final boolean allMatch(Predicate<? super P_OUT> predicate) {
        return evaluate(DefaultMatchOps.makeRef(predicate, DefaultMatchOps.MatchKind.ALL));
    }

    @Override
    public final boolean noneMatch(Predicate<? super P_OUT> predicate) {
        return evaluate(DefaultMatchOps.makeRef(predicate, DefaultMatchOps.MatchKind.NONE));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> findFirst() {
        return evaluate(DefaultFindOps.makeRef(true));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> findAny() {
        return evaluate(DefaultFindOps.makeRef(false));
    }

    @Override
    public final P_OUT reduce(final P_OUT identity, final BinaryOperator<P_OUT> accumulator) {
        return evaluate(DefaultReduceOps.makeRef(identity, accumulator, accumulator));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> reduce(BinaryOperator<P_OUT> accumulator) {
        return evaluate(DefaultReduceOps.makeRef(accumulator));
    }

    @Override
    public final <R> R reduce(R identity, BiFunction<R, ? super P_OUT, R> accumulator, BinaryOperator<R> combiner) {
        return evaluate(DefaultReduceOps.makeRef(identity, accumulator, combiner));
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <R, A> R collect(Collector<? super P_OUT, A, R> collector) {
        A container;
        if (isParallel()
                && (collector.characteristics().contains(Collector.Characteristics.CONCURRENT))
                && (!isOrdered() || collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            container = collector.supplier().get();
            BiConsumer<A, ? super P_OUT> accumulator = collector.accumulator();
            forEach(u -> accumulator.accept(container, u));
        }
        else {
            container = evaluate(DefaultReduceOps.makeRef(collector));
        }
        return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
               ? (R) container
               : collector.finisher().apply(container);
    }

    @Override
    public final <R> R collect(Supplier<R> supplier,
                               BiConsumer<R, ? super P_OUT> accumulator,
                               BiConsumer<R, R> combiner) {
        return evaluate(DefaultReduceOps.makeRef(supplier, accumulator, combiner));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> max(Comparator<? super P_OUT> comparator) {
        return reduce(BinaryOperator.maxBy(comparator));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> min(Comparator<? super P_OUT> comparator) {
        return reduce(BinaryOperator.minBy(comparator));

    }

    @Override
    public final long count() {
        return mapToLong(e -> 1L).sum();
    }


    //

    static class Head<E_IN, E_OUT> extends StreamReferencePipeline<E_IN, E_OUT> {
        Head(Supplier<? extends Spliterator<?>> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        Head(Spliterator<?> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override
        final DefaultSink<E_IN> opWrapSink(int flags, DefaultSink<E_OUT> sink) {
            throw new UnsupportedOperationException();
        }

        // Optimized sequential terminal operations for the head of the pipeline

        @Override
        public void forEach(Consumer<? super E_OUT> action) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            }
            else {
                super.forEach(action);
            }
        }

        @Override
        public void forEachOrdered(Consumer<? super E_OUT> action) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            }
            else {
                super.forEachOrdered(action);
            }
        }
    }

    abstract static class StatelessOp<E_IN, E_OUT>
            extends StreamReferencePipeline<E_IN, E_OUT> {
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

    abstract static class StatefulOp<E_IN, E_OUT>
            extends StreamReferencePipeline<E_IN, E_OUT> {
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
        abstract <P_IN> DefaultNode<E_OUT> opEvaluateParallel(DefaultPipelineHelper<E_OUT> helper,
                                                       Spliterator<P_IN> spliterator,
                                                       IntFunction<E_OUT[]> generator);
    }
}
