package io.github.nichetoolkit.rest.stream;

import java.util.*;
import java.util.concurrent.CountedCompleter;
import java.util.function.*;
import java.util.stream.*;

final class DefaultReduceOps {

    private DefaultReduceOps() { }

    public static <T, U> DefaultTerminalOp<T, U>
    makeRef(U seed, BiFunction<U, ? super T, U> reducer, BinaryOperator<U> combiner) {
        Objects.requireNonNull(reducer);
        Objects.requireNonNull(combiner);
        class ReducingSink extends Box<U> implements AccumulatingSink<T, U, ReducingSink> {
            @Override
            public void begin(long size) {
                state = seed;
            }

            @Override
            public void accept(T t) {
                state = reducer.apply(state, t);
            }

            @Override
            public void combine(ReducingSink other) {
                state = combiner.apply(state, other.state);
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
    makeRef(BinaryOperator<T> operator) {
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
            public void accept(T t) {
                if (empty) {
                    empty = false;
                    state = t;
                } else {
                    state = operator.apply(state, t);
                }
            }

            @Override
            public Optional<T> get() {
                return empty ? Optional.empty() : Optional.of(state);
            }

            @Override
            public void combine(ReducingSink other) {
                if (!other.empty)
                    accept(other.state);
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
    makeRef(Collector<? super T, I, ?> collector) {
        Supplier<I> supplier = Objects.requireNonNull(collector).supplier();
        BiConsumer<I, ? super T> accumulator = collector.accumulator();
        BinaryOperator<I> combiner = collector.combiner();
        class ReducingSink extends Box<I>
                implements AccumulatingSink<T, I, ReducingSink> {
            @Override
            public void begin(long size) {
                state = supplier.get();
            }

            @Override
            public void accept(T t) {
                accumulator.accept(state, t);
            }

            @Override
            public void combine(ReducingSink other) {
                state = combiner.apply(state, other.state);
            }
        }
        return new ReduceOp<T, I, ReducingSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }

            @Override
            public int getOpFlags() {
                return collector.characteristics().contains(Collector.Characteristics.UNORDERED)
                       ? DefaultStreamOpFlag.NOT_ORDERED
                       : 0;
            }
        };
    }

    public static <T, R> DefaultTerminalOp<T, R>
    makeRef(Supplier<R> seedFactory,
            BiConsumer<R, ? super T> accumulator,
            BiConsumer<R,R> reducer) {
        Objects.requireNonNull(seedFactory);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(reducer);
        class ReducingSink extends Box<R>
                implements AccumulatingSink<T, R, ReducingSink> {
            @Override
            public void begin(long size) {
                state = seedFactory.get();
            }

            @Override
            public void accept(T t) {
                accumulator.accept(state, t);
            }

            @Override
            public void combine(ReducingSink other) {
                reducer.accept(state, other.state);
            }
        }
        return new ReduceOp<T, R, ReducingSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static DefaultTerminalOp<Integer, Integer>
    makeInt(int identity, IntBinaryOperator operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<Integer, Integer, ReducingSink>, DefaultSink.OfInt {
            private int state;

            @Override
            public void begin(long size) {
                state = identity;
            }

            @Override
            public void accept(int t) {
                state = operator.applyAsInt(state, t);
            }

            @Override
            public Integer get() {
                return state;
            }

            @Override
            public void combine(ReducingSink other) {
                accept(other.state);
            }
        }
        return new ReduceOp<Integer, Integer, ReducingSink>(DefaultStreamShape.INT_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static DefaultTerminalOp<Integer, OptionalInt>
    makeInt(IntBinaryOperator operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<Integer, OptionalInt, ReducingSink>, DefaultSink.OfInt {
            private boolean empty;
            private int state;

            public void begin(long size) {
                empty = true;
                state = 0;
            }

            @Override
            public void accept(int t) {
                if (empty) {
                    empty = false;
                    state = t;
                }
                else {
                    state = operator.applyAsInt(state, t);
                }
            }

            @Override
            public OptionalInt get() {
                return empty ? OptionalInt.empty() : OptionalInt.of(state);
            }

            @Override
            public void combine(ReducingSink other) {
                if (!other.empty)
                    accept(other.state);
            }
        }
        return new ReduceOp<Integer, OptionalInt, ReducingSink>(DefaultStreamShape.INT_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static <R> DefaultTerminalOp<Integer, R>
    makeInt(Supplier<R> supplier,
            ObjIntConsumer<R> accumulator,
            BinaryOperator<R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        class ReducingSink extends Box<R>
                implements AccumulatingSink<Integer, R, ReducingSink>, DefaultSink.OfInt {
            @Override
            public void begin(long size) {
                state = supplier.get();
            }

            @Override
            public void accept(int t) {
                accumulator.accept(state, t);
            }

            @Override
            public void combine(ReducingSink other) {
                state = combiner.apply(state, other.state);
            }
        }
        return new ReduceOp<Integer, R, ReducingSink>(DefaultStreamShape.INT_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static DefaultTerminalOp<Long, Long>
    makeLong(long identity, LongBinaryOperator operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<Long, Long, ReducingSink>, DefaultSink.OfLong {
            private long state;

            @Override
            public void begin(long size) {
                state = identity;
            }

            @Override
            public void accept(long t) {
                state = operator.applyAsLong(state, t);
            }

            @Override
            public Long get() {
                return state;
            }

            @Override
            public void combine(ReducingSink other) {
                accept(other.state);
            }
        }
        return new ReduceOp<Long, Long, ReducingSink>(DefaultStreamShape.LONG_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static DefaultTerminalOp<Long, OptionalLong>
    makeLong(LongBinaryOperator operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<Long, OptionalLong, ReducingSink>, DefaultSink.OfLong {
            private boolean empty;
            private long state;

            public void begin(long size) {
                empty = true;
                state = 0;
            }

            @Override
            public void accept(long t) {
                if (empty) {
                    empty = false;
                    state = t;
                }
                else {
                    state = operator.applyAsLong(state, t);
                }
            }

            @Override
            public OptionalLong get() {
                return empty ? OptionalLong.empty() : OptionalLong.of(state);
            }

            @Override
            public void combine(ReducingSink other) {
                if (!other.empty)
                    accept(other.state);
            }
        }
        return new ReduceOp<Long, OptionalLong, ReducingSink>(DefaultStreamShape.LONG_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static <R> DefaultTerminalOp<Long, R>
    makeLong(Supplier<R> supplier,
             ObjLongConsumer<R> accumulator,
             BinaryOperator<R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        class ReducingSink extends Box<R>
                implements AccumulatingSink<Long, R, ReducingSink>, DefaultSink.OfLong {
            @Override
            public void begin(long size) {
                state = supplier.get();
            }

            @Override
            public void accept(long t) {
                accumulator.accept(state, t);
            }

            @Override
            public void combine(ReducingSink other) {
                state = combiner.apply(state, other.state);
            }
        }
        return new ReduceOp<Long, R, ReducingSink>(DefaultStreamShape.LONG_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static DefaultTerminalOp<Double, Double>
    makeDouble(double identity, DoubleBinaryOperator operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<Double, Double, ReducingSink>, DefaultSink.OfDouble {
            private double state;

            @Override
            public void begin(long size) {
                state = identity;
            }

            @Override
            public void accept(double t) {
                state = operator.applyAsDouble(state, t);
            }

            @Override
            public Double get() {
                return state;
            }

            @Override
            public void combine(ReducingSink other) {
                accept(other.state);
            }
        }
        return new ReduceOp<Double, Double, ReducingSink>(DefaultStreamShape.DOUBLE_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static DefaultTerminalOp<Double, OptionalDouble>
    makeDouble(DoubleBinaryOperator operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<Double, OptionalDouble, ReducingSink>, DefaultSink.OfDouble {
            private boolean empty;
            private double state;

            public void begin(long size) {
                empty = true;
                state = 0;
            }

            @Override
            public void accept(double t) {
                if (empty) {
                    empty = false;
                    state = t;
                }
                else {
                    state = operator.applyAsDouble(state, t);
                }
            }

            @Override
            public OptionalDouble get() {
                return empty ? OptionalDouble.empty() : OptionalDouble.of(state);
            }

            @Override
            public void combine(ReducingSink other) {
                if (!other.empty)
                    accept(other.state);
            }
        }
        return new ReduceOp<Double, OptionalDouble, ReducingSink>(DefaultStreamShape.DOUBLE_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static <R> DefaultTerminalOp<Double, R>
    makeDouble(Supplier<R> supplier,
               ObjDoubleConsumer<R> accumulator,
               BinaryOperator<R> combiner) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        class ReducingSink extends Box<R>
                implements AccumulatingSink<Double, R, ReducingSink>, DefaultSink.OfDouble {
            @Override
            public void begin(long size) {
                state = supplier.get();
            }

            @Override
            public void accept(double t) {
                accumulator.accept(state, t);
            }

            @Override
            public void combine(ReducingSink other) {
                state = combiner.apply(state, other.state);
            }
        }
        return new ReduceOp<Double, R, ReducingSink>(DefaultStreamShape.DOUBLE_VALUE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    private interface AccumulatingSink<T, R, K extends AccumulatingSink<T, R, K>>
            extends DefaultTerminalSink<T, R> {
        void combine(K other);
    }

    private static abstract class Box<U> {
        U state;

        Box() {} // Avoid creation of special accessor

        public U get() {
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
                                           Spliterator<P_IN> spliterator) {
            return helper.wrapAndCopyInto(makeSink(), spliterator).get();
        }

        @Override
        public <P_IN> R evaluateParallel(DefaultPipelineHelper<T> helper,
                                         Spliterator<P_IN> spliterator) {
            return new ReduceTask<>(this, helper, spliterator).invoke().get();
        }
    }

    private static final class ReduceTask<P_IN, P_OUT, R,
                                          S extends AccumulatingSink<P_OUT, R, S>>
            extends DefaultAbstractTask<P_IN, P_OUT, S, ReduceTask<P_IN, P_OUT, R, S>> {
        private final ReduceOp<P_OUT, R, S> op;

        ReduceTask(ReduceOp<P_OUT, R, S> op,
                   DefaultPipelineHelper<P_OUT> helper,
                   Spliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

        ReduceTask(ReduceTask<P_IN, P_OUT, R, S> parent,
                   Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        @Override
        protected ReduceTask<P_IN, P_OUT, R, S> makeChild(Spliterator<P_IN> spliterator) {
            return new ReduceTask<>(this, spliterator);
        }

        @Override
        protected S doLeaf() {
            return helper.wrapAndCopyInto(op.makeSink(), spliterator);
        }

        @Override
        public void onCompletion(CountedCompleter<?> caller) {
            if (!isLeaf()) {
                S leftResult = leftChild.getLocalResult();
                leftResult.combine(rightChild.getLocalResult());
                setLocalResult(leftResult);
            }
            // GC spliterator, left and right child
            super.onCompletion(caller);
        }
    }
}
