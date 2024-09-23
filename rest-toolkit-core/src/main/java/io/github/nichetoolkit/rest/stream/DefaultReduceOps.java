package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.BiFunctionActuator;
import io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.*;

/**
 * <code>DefaultReduceOps</code>
 * <p>The type default reduce ops class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
final class DefaultReduceOps {

    /**
     * <code>DefaultReduceOps</code>
     * Instantiates a new default reduce ops.
     */
    private DefaultReduceOps() { }

    /**
     * <code>makeRef</code>
     * <p>the ref method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <U>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param seed     U <p>the seed parameter is <code>U</code> type.</p>
     * @param reducer  {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>the reducer parameter is <code>BiFunctionActuator</code> type.</p>
     * @param combiner {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>the combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>the ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     */
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

    /**
     * <code>makeRef</code>
     * <p>the ref method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param operator {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>the operator parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>the ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     */
    public static <T> DefaultTerminalOp<T, RestOptional<T>>
    makeRef(BinaryOperatorActuator<T> operator) {
        Objects.requireNonNull(operator);
        class ReducingSink
                implements AccumulatingSink<T, RestOptional<T>, ReducingSink> {
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
            public RestOptional<T> actuate() {
                return empty ? RestOptional.empty() : RestOptional.of(state);
            }

            @Override
            public void combine(ReducingSink other) throws RestException {
                if (!other.empty)
                    actuate(other.state);
            }
        }
        return new ReduceOp<T, RestOptional<T>, ReducingSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public ReducingSink makeSink() {
                return new ReducingSink();
            }
        };
    }

    public static DefaultTerminalOp<Boolean, Boolean>
    makeRef(BinaryOperatorActuator<Boolean> operator, Boolean defaultValue) {
        Objects.requireNonNull(operator);
        class BooleanSink
                implements AccumulatingSink<Boolean, Boolean, BooleanSink> {
            private boolean empty;
            private Boolean state;

            public void begin(long size) {
                empty = true;
                state = null;
            }

            @Override
            public void actuate(Boolean t) throws RestException {
                if (empty) {
                    empty = false;
                    state = t;
                } else {
                    state = operator.actuate(state, t);
                }
            }

            @Override
            public Boolean actuate() {
                return empty ? defaultValue : state;
            }

            @Override
            public void combine(BooleanSink other) throws RestException {
                if (!other.empty)
                    actuate(other.state);
            }
        }
        return new ReduceOp<Boolean, Boolean, BooleanSink>(DefaultStreamShape.REFERENCE) {
            @Override
            public BooleanSink makeSink() {
                return new BooleanSink();
            }
        };
    }

    /**
     * <code>makeRef</code>
     * <p>the ref method.</p>
     * @param <T>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <I>       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param collector {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>the collector parameter is <code>RestCollector</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>the ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.RestCollector
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     */
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

    /**
     * <code>makeRef</code>
     * <p>the ref method.</p>
     * @param <T>         {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <R>         {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param seedFactory {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>the seed factory parameter is <code>SupplierActuator</code> type.</p>
     * @param accumulator {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the accumulator parameter is <code>BiConsumerActuator</code> type.</p>
     * @param reducer     {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>the reducer parameter is <code>BiConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>the ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     */
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

    /**
     * <code>AccumulatingSink</code>
     * <p>The type accumulating sink interface.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <R> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <K> {@link io.github.nichetoolkit.rest.stream.DefaultReduceOps.AccumulatingSink} <p>the generic parameter is <code>AccumulatingSink</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalSink
     * @since Jdk1.8
     */
    private interface AccumulatingSink<T, R, K extends AccumulatingSink<T, R, K>>
            extends DefaultTerminalSink<T, R> {
        /**
         * <code>combine</code>
         * <p>the method.</p>
         * @param other K <p>the other parameter is <code>K</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        void combine(K other) throws RestException;
    }

    /**
     * <code>Box</code>
     * <p>The type box class.</p>
     * @param <U> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    private static abstract class Box<U> {
        /**
         * <code>state</code>
         * <p>the <code>state</code> field.</p>
         */
        U state;

        /**
         * <code>Box</code>
         * Instantiates a new box.
         */
        Box() {} // Avoid creation of special accessor

        /**
         * <code>actuate</code>
         * <p>the method.</p>
         * @return U <p>the return object is <code>U</code> type.</p>
         */
        public U actuate() {
            return state;
        }
    }

    /**
     * <code>ReduceOp</code>
     * <p>The type reduce op class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <R> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <S> {@link io.github.nichetoolkit.rest.stream.DefaultReduceOps.AccumulatingSink} <p>the generic parameter is <code>AccumulatingSink</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultReduceOps.AccumulatingSink
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     * @since Jdk1.8
     */
    private static abstract class ReduceOp<T, R, S extends AccumulatingSink<T, R, S>>
            implements DefaultTerminalOp<T, R> {
        /**
         * <code>inputShape</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>the <code>inputShape</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
         */
        private final DefaultStreamShape inputShape;

        /**
         * <code>ReduceOp</code>
         * Instantiates a new reduce op.
         * @param shape {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>the shape parameter is <code>DefaultStreamShape</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
         */
        ReduceOp(DefaultStreamShape shape) {
            inputShape = shape;
        }

        /**
         * <code>makeSink</code>
         * <p>the sink method.</p>
         * @return S <p>the sink return object is <code>S</code> type.</p>
         */
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

    /**
     * <code>ReduceTask</code>
     * <p>The type reduce task class.</p>
     * @param <P_IN>  {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <P_OUT> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <R>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <S>     {@link io.github.nichetoolkit.rest.stream.DefaultReduceOps.AccumulatingSink} <p>the generic parameter is <code>AccumulatingSink</code> type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultReduceOps.AccumulatingSink
     * @see io.github.nichetoolkit.rest.stream.DefaultAbstractTask
     * @since Jdk1.8
     */
    private static final class ReduceTask<P_IN, P_OUT, R,
                                          S extends AccumulatingSink<P_OUT, R, S>>
            extends DefaultAbstractTask<P_IN, P_OUT, S, ReduceTask<P_IN, P_OUT, R, S>> {
        /**
         * <code>op</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultReduceOps.ReduceOp} <p>the <code>op</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultReduceOps.ReduceOp
         */
        private final ReduceOp<P_OUT, R, S> op;

        /**
         * <code>ReduceTask</code>
         * Instantiates a new reduce task.
         * @param op          {@link io.github.nichetoolkit.rest.stream.DefaultReduceOps.ReduceOp} <p>the op parameter is <code>ReduceOp</code> type.</p>
         * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>the helper parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultReduceOps.ReduceOp
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        ReduceTask(ReduceOp<P_OUT, R, S> op,
                   DefaultPipelineHelper<P_OUT> helper,
                   DefaultSpliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

        /**
         * <code>ReduceTask</code>
         * Instantiates a new reduce task.
         * @param parent      {@link io.github.nichetoolkit.rest.stream.DefaultReduceOps.ReduceTask} <p>the parent parameter is <code>ReduceTask</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
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
