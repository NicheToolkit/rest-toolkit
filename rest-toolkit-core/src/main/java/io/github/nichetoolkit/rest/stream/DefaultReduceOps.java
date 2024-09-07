package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.BiFunctionActuator;
import io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.*;

final class DefaultReduceOps {

    private DefaultReduceOps() { }

    public static <T, U> DefaultTerminalOp<T, U>
    makeRef(U seed, BiFunctionActuator<U, ? super T, U> reducer, BinaryOperatorActuator<U> combiner) {
        Objects.requireNonNull(reducer);
        Objects.requireNonNull(combiner);
        class ReducingSink extends Box<U> implements AccumulatingSink<T, U, ReducingSink> {
            @Override
            public void begin(long size) {
                state = seed;
            }

            @Override
            public void actuate(T t) throws RestException {
                state = reducer.actuate(state, t);
            }

            @Override
            public void combine(ReducingSink other) throws RestException {
                state = combiner.actuate(state, other.state);
            }
        }
        return new ReduceOp<T, U, ReducingSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static <T> DefaultTerminalOp<T, Optional<T>>
    makeRef(BinaryOperatorActuator<T> operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<T, Optional<T>, ReducingSink> {
            private boolean empty;
            private T state;

            public void begin(long size) {
                empty = true;
                state = null;
            }

            @Override
            public void actuate(T t) throws RestException {
                if (empty) {
                    empty = false;
                    state = t;
                } else {
                    state = operator.actuate(state, t);
                }
            }

            @Override
            public Optional<T> actuate() {
                return empty ? Optional.empty() : Optional.of(state);
            }

            @Override
            public void combine(ReducingSink other) throws RestException {
                if (!other.empty)
                    actuate(other.state);
            }
        }
        return new ReduceOp<T, Optional<T>, ReducingSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static <T, I> DefaultTerminalOp<T, I>
    makeRef(RestCollector<? super T, I, ?> collector) {
        SupplierActuator<I> supplier = Objects.requireNonNull(collector).supplier();
        BiConsumerActuator<I, ? super T> accumulator = collector.accumulator();
        BinaryOperatorActuator<I> combiner = collector.combiner();
        class ReducingSink extends Box<I>
                implements AccumulatingSink<T, I, ReducingSink> {
            @Override
            public void begin(long size) throws RestException {
                state = supplier.actuate();
            }

            @Override
            public void actuate(T t) throws RestException {
                accumulator.actuate(state, t);
            }

            @Override
            public void combine(ReducingSink other) throws RestException {
                state = combiner.actuate(state, other.state);
            }
        }
        return new ReduceOp<T, I, ReducingSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }

            @Override
            public int getOpFlags() {
                return collector.characteristics().contains(RestCollector.Characteristics.UNORDERED)
                       ? DefaultStreamOpFlag.NOT_ORDERED
                       : 0;
            }
        };
    }

    public static <T, R> DefaultTerminalOp<T, R>
    makeRef(SupplierActuator<R> seedFactory,
            BiConsumerActuator<R, ? super T> accumulator,
            BiConsumerActuator<R,R> reducer) {
        Objects.requireNonNull(seedFactory);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(reducer);
        class ReducingSink extends Box<R>
                implements AccumulatingSink<T, R, ReducingSink> {
            @Override
            public void begin(long size) throws RestException {
                state = seedFactory.actuate();
            }

            @Override
            public void actuate(T t) throws RestException {
                accumulator.actuate(state, t);
            }

            @Override
            public void combine(ReducingSink other) throws RestException {
                reducer.actuate(state, other.state);
            }
        }
        return new ReduceOp<T, R, ReducingSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    private interface AccumulatingSink<T, R, K extends AccumulatingSink<T, R, K>>
            extends DefaultTerminalSink<T, R> {
        void combine(K other) throws RestException;
    }

    private static abstract class Box<U> {
        U state;

        Box() {} // Avoid creation of special accessor

        public U actuate() {
            return state;
        }
    }

    private static abstract class ReduceOp<T, R, S extends AccumulatingSink<T, R, S>>
            implements DefaultTerminalOp<T, R> {
        private final DefaultStreamShape inputShape;

        ReduceOp(DefaultStreamShape shape) {
            inputShape = shape;
        }

        public abstract S makeSink();

        @Override
        public DefaultStreamShape inputShape() {
            return inputShape;
        }

        @Override
        public <P_IN> R evaluateSequential(DefaultPipelineHelper<T> helper,
                                           DefaultSpliterator<P_IN> spliterator) throws RestException {
            return helper.wrapAndCopyInto(makeSink(), spliterator).actuate();
        }

        @Override
        public <P_IN> R evaluateParallel(DefaultPipelineHelper<T> helper,
                                         DefaultSpliterator<P_IN> spliterator) throws RestException {
            return new ReduceTask<>(this, helper, spliterator).invoke().actuate();
        }
    }

    private static final class ReduceTask<P_IN, P_OUT, R,
                                          S extends AccumulatingSink<P_OUT, R, S>>
            extends DefaultAbstractTask<P_IN, P_OUT, S, ReduceTask<P_IN, P_OUT, R, S>> {
        private final ReduceOp<P_OUT, R, S> op;

        ReduceTask(ReduceOp<P_OUT, R, S> op,
                   DefaultPipelineHelper<P_OUT> helper,
                   DefaultSpliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

        ReduceTask(ReduceTask<P_IN, P_OUT, R, S> parent,
                   DefaultSpliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        @Override
        protected ReduceTask<P_IN, P_OUT, R, S> makeChild(DefaultSpliterator<P_IN> spliterator) {
            return new ReduceTask<>(this, spliterator);
        }

        @Override
        protected S doLeaf() throws RestException {
            return helper.wrapAndCopyInto(op.makeSink(), spliterator);
        }

        @Override
        public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {
            if (isLeaf()) {
                S leftResult = leftChild.getLocalResult();
                leftResult.combine(rightChild.getLocalResult());
                setLocalResult(leftResult);
            }
            // GC spliterator, left and right child
            super.onCompletion(caller);
        }
    }
}
