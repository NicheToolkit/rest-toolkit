package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.*;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.function.*;

abstract class DefaultReferencePipeline<P_IN, P_OUT>
        extends DefaultAbstractPipeline<P_IN, P_OUT, RestStream<P_OUT>>
        implements RestStream<P_OUT> {

    DefaultReferencePipeline(SupplierActuator<? extends DefaultSpliterator<?>> source,
                             int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    DefaultReferencePipeline(DefaultSpliterator<?> source,
                             int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    DefaultReferencePipeline(DefaultAbstractPipeline<?, P_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    @Override
    final DefaultStreamShape getOutputShape() {
        return DefaultStreamShape.REFERENCE;
    }

    @Override
    final <P_INT> DefaultNode<P_OUT> evaluateToNode(DefaultPipelineHelper<P_OUT> helper,
                                                   DefaultSpliterator<P_INT> spliterator,
                                                   boolean flattenTree,
                                                   IntFunction<P_OUT[]> generator) throws RestException {
        return DefaultNodes.collect(helper, spliterator, flattenTree, generator);
    }

    @Override
    final <P_INT> DefaultSpliterator<P_OUT> wrap(DefaultPipelineHelper<P_OUT> ph,
                                         SupplierActuator<DefaultSpliterator<P_INT>> supplier,
                                         boolean isParallel) throws RestException  {
        return new DefaultStreamSpliterators.WrappingSpliterator<>(ph, supplier, isParallel);
    }

    @Override
    final DefaultSpliterator<P_OUT> lazySpliterator(SupplierActuator<? extends DefaultSpliterator<P_OUT>> supplier) throws RestException {
        return new DefaultStreamSpliterators.DelegatingSpliterator<>(supplier);
    }

    @Override
    final void forEachWithCancel(DefaultSpliterator<P_OUT> spliterator, DefaultSink<P_OUT> sink) throws RestException {
        do {
        } while (!sink.cancellationRequested() && spliterator.tryAdvance(sink));
    }

    @Override
    final DefaultNode.Builder<P_OUT> makeNodeBuilder(long exactSizeIfKnown, IntFunction<P_OUT[]> generator) {
        return DefaultNodes.builder(exactSizeIfKnown, generator);
    }

    @NonNull
    @Override
    public final Iterator<P_OUT> iterator() throws RestException {
        return DefaultSpliterators.iterator(spliterator());
    }

    @NonNull
    @Override
    public RestStream<P_OUT> unordered() throws RestException {
        if (isOrdered())
            return this;
        return new StatelessOp<P_OUT, P_OUT>(this, DefaultStreamShape.REFERENCE, DefaultStreamOpFlag.NOT_ORDERED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<P_OUT> sink) throws RestException {
                return sink;
            }
        };
    }

    @Override
    public final RestStream<P_OUT> filter(PredicateActuator<? super P_OUT> predicate) throws RestException {
        Objects.requireNonNull(predicate);
        return new StatelessOp<P_OUT, P_OUT>(this, DefaultStreamShape.REFERENCE,
                DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<P_OUT> sink) throws RestException {
                return new DefaultSink.ChainedReference<P_OUT, P_OUT>(sink) {
                    @Override
                    public void begin(long size) throws RestException {
                        downstream.begin(-1);
                    }

                    @Override
                    public void actuate(P_OUT u) throws RestException {
                        if (predicate.actuate(u))
                            downstream.actuate(u);
                    }
                };
            }
        };
    }

    @Override
    public final <R> RestStream<R> map(FunctionActuator<? super P_OUT, ? extends R> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        return new StatelessOp<P_OUT, R>(this, DefaultStreamShape.REFERENCE,
                DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<R> sink) throws RestException {
                return new DefaultSink.ChainedReference<P_OUT, R>(sink) {
                    @Override
                    public void actuate(P_OUT u) throws RestException {
                        downstream.actuate(mapper.actuate(u));
                    }
                };
            }
        };
    }

    @Override
    public final <R> RestStream<R> flatMap(FunctionActuator<? super P_OUT, ? extends RestStream<? extends R>> mapper) throws RestException {
        Objects.requireNonNull(mapper);
        return new StatelessOp<P_OUT, R>(this, DefaultStreamShape.REFERENCE,
                DefaultStreamOpFlag.NOT_SORTED | DefaultStreamOpFlag.NOT_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<R> sink) throws RestException {
                return new DefaultSink.ChainedReference<P_OUT, R>(sink) {
                    boolean cancellationRequestedCalled;

                    @Override
                    public void begin(long size) throws RestException {
                        downstream.begin(-1);
                    }

                    @Override
                    public void actuate(P_OUT u) throws RestException {
                        try (RestStream<? extends R> result = mapper.actuate(u)) {
                            if (result != null) {
                                if (!cancellationRequestedCalled) {
                                    result.sequential().forEach(downstream);
                                } else {
                                    DefaultSpliterator<? extends R> s = result.sequential().spliterator();
                                    do {
                                    } while (!downstream.cancellationRequested() && s.tryAdvance(downstream));
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
    public final RestStream<P_OUT> peek(ConsumerActuator<? super P_OUT> action) throws RestException {
        Objects.requireNonNull(action);
        return new StatelessOp<P_OUT, P_OUT>(this, DefaultStreamShape.REFERENCE,
                0) {
            @Override
            DefaultSink<P_OUT> opWrapSink(int flags, DefaultSink<P_OUT> sink) throws RestException {
                return new DefaultSink.ChainedReference<P_OUT, P_OUT>(sink) {
                    @Override
                    public void actuate(P_OUT u) throws RestException {
                        action.actuate(u);
                        downstream.actuate(u);
                    }
                };
            }
        };
    }

    @Override
    public final RestStream<P_OUT> distinct() throws RestException {
        return DefaultDistinctOps.makeRef(this);
    }

    @Override
    public final RestStream<P_OUT> sorted() throws RestException {
        return DefaultSortedOps.makeRef(this);
    }

    @Override
    public final RestStream<P_OUT> sorted(ComparatorActuator<? super P_OUT> comparator) throws RestException {
        return DefaultSortedOps.makeRef(this, comparator);
    }

    @Override
    public final RestStream<P_OUT> limit(long maxSize) throws RestException {
        if (maxSize < 0)
            throw new IllegalArgumentException(Long.toString(maxSize));
        return DefaultSliceOps.makeRef(this, 0, maxSize);
    }

    @Override
    public final RestStream<P_OUT> skip(long n) throws RestException {
        if (n < 0)
            throw new IllegalArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else
            return DefaultSliceOps.makeRef(this, n, -1);
    }

    @Override
    public void forEach(ConsumerActuator<? super P_OUT> action) throws RestException {
        evaluate(DefaultForEachOps.makeRef(action, false));
    }

    @Override
    public void forEachOrdered(ConsumerActuator<? super P_OUT> action) throws RestException {
        evaluate(DefaultForEachOps.makeRef(action, true));
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public final <A> A[] toArray(IntFunction<A[]> generator) throws RestException {
        @SuppressWarnings("rawtypes")
        IntFunction rawGenerator = generator;
        return (A[]) DefaultNodes.flatten(evaluateToArrayNode(rawGenerator), rawGenerator)
                .asArray(rawGenerator);
    }

    @NonNull
    @Override
    public final Object[] toArray() throws RestException {
        return toArray(Object[]::new);
    }

    @Override
    public final boolean anyMatch(PredicateActuator<? super P_OUT> predicate) throws RestException {
        return evaluate(DefaultMatchOps.makeRef(predicate, DefaultMatchOps.MatchKind.ANY));
    }

    @Override
    public final boolean allMatch(PredicateActuator<? super P_OUT> predicate) throws RestException {
        return evaluate(DefaultMatchOps.makeRef(predicate, DefaultMatchOps.MatchKind.ALL));
    }

    @Override
    public final boolean noneMatch(PredicateActuator<? super P_OUT> predicate) throws RestException {
        return evaluate(DefaultMatchOps.makeRef(predicate, DefaultMatchOps.MatchKind.NONE));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> findFirst() throws RestException {
        return evaluate(DefaultFindOps.makeRef(true));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> findAny() throws RestException {
        return evaluate(DefaultFindOps.makeRef(false));
    }

    @Override
    public final P_OUT reduce(final P_OUT identity, final BinaryOperatorActuator<P_OUT> accumulator) throws RestException {
        return evaluate(DefaultReduceOps.makeRef(identity, accumulator, accumulator));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> reduce(BinaryOperatorActuator<P_OUT> accumulator) throws RestException {
        return evaluate(DefaultReduceOps.makeRef(accumulator));
    }

    @Override
    public final <R> R reduce(R identity, BiFunctionActuator<R, ? super P_OUT, R> accumulator, BinaryOperatorActuator<R> combiner) throws RestException {
        return evaluate(DefaultReduceOps.makeRef(identity, accumulator, combiner));
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <R, A> R collect(RestCollector<? super P_OUT, A, R> collector) throws RestException {
        A container;
        if (isParallel()
                && (collector.characteristics().contains(RestCollector.Characteristics.CONCURRENT))
                && (isOrdered() || collector.characteristics().contains(RestCollector.Characteristics.UNORDERED))) {
            container = collector.supplier().actuate();
            BiConsumerActuator<A, ? super P_OUT> accumulator = collector.accumulator();
            forEach(u -> accumulator.actuate(container, u));
        } else {
            container = evaluate(DefaultReduceOps.makeRef(collector));
        }
        return collector.characteristics().contains(RestCollector.Characteristics.IDENTITY_FINISH)
                ? (R) container
                : collector.finisher().actuate(container);
    }

    @Override
    public final <R> R collect(SupplierActuator<R> supplier,
                               BiConsumerActuator<R, ? super P_OUT> accumulator,
                               BiConsumerActuator<R, R> combiner) throws RestException {
        return evaluate(DefaultReduceOps.makeRef(supplier, accumulator, combiner));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> max(ComparatorActuator<? super P_OUT> comparator) throws RestException {
        return reduce(BinaryOperatorActuator.maxBy(comparator));
    }

    @NonNull
    @Override
    public final Optional<P_OUT> min(ComparatorActuator<? super P_OUT> comparator) throws RestException {
        return reduce(BinaryOperatorActuator.minBy(comparator));

    }

    static class Head<E_IN, E_OUT> extends DefaultReferencePipeline<E_IN, E_OUT> {
        Head(SupplierActuator<? extends DefaultSpliterator<?>> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        Head(DefaultSpliterator<?> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override
        final DefaultSink<E_IN> opWrapSink(int flags, DefaultSink<E_OUT> sink) throws RestException {
            throw new UnsupportedOperationException();
        }

        // Optimized sequential terminal operations for the head of the pipeline

        @Override
        public void forEach(ConsumerActuator<? super E_OUT> action) throws RestException {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            } else {
                super.forEach(action);
            }
        }

        @Override
        public void forEachOrdered(ConsumerActuator<? super E_OUT> action) throws RestException {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(action);
            } else {
                super.forEachOrdered(action);
            }
        }
    }

    abstract static class StatelessOp<E_IN, E_OUT>
            extends DefaultReferencePipeline<E_IN, E_OUT> {
        StatelessOp(DefaultAbstractPipeline<?, E_IN, ?> upstream,
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
            extends DefaultReferencePipeline<E_IN, E_OUT> {
        StatefulOp(DefaultAbstractPipeline<?, E_IN, ?> upstream,
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
                                                              DefaultSpliterator<P_IN> spliterator,
                                                              IntFunction<E_OUT[]> generator) throws RestException ;
    }
}