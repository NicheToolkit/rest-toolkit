
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.actuator.PredicateActuator;

import java.util.*;
import java.util.concurrent.CountedCompleter;

import java.util.function.Predicate;
import java.util.function.Supplier;

final class DefaultFindOps {

    private DefaultFindOps() { }

    public static <T> DefaultTerminalOp<T, Optional<T>> makeRef(boolean mustFindFirst) {
        return new FindOp<>(mustFindFirst, DefaultStreamShape.REFERENCE, Optional.empty(),
                            Optional::isPresent, FindSink.OfRef::new);
    }

    public static DefaultTerminalOp<Integer, OptionalInt> makeInt(boolean mustFindFirst) {
        return new FindOp<>(mustFindFirst, DefaultStreamShape.INT_VALUE, OptionalInt.empty(),
                            OptionalInt::isPresent, FindSink.OfInt::new);
    }

    public static DefaultTerminalOp<Long, OptionalLong> makeLong(boolean mustFindFirst) {
        return new FindOp<>(mustFindFirst, DefaultStreamShape.LONG_VALUE, OptionalLong.empty(),
                            OptionalLong::isPresent, FindSink.OfLong::new);
    }

    public static DefaultTerminalOp<Double, OptionalDouble> makeDouble(boolean mustFindFirst) {
        return new FindOp<>(mustFindFirst, DefaultStreamShape.DOUBLE_VALUE, OptionalDouble.empty(),
                            OptionalDouble::isPresent, FindSink.OfDouble::new);
    }

    private static final class FindOp<T, O> implements DefaultTerminalOp<T, O> {
        private final DefaultStreamShape shape;
        final boolean mustFindFirst;
        final O emptyValue;
        final Predicate<O> presentPredicate;
        final Supplier<DefaultTerminalSink<T, O>> sinkSupplier;

        FindOp(boolean mustFindFirst,
                       DefaultStreamShape shape,
                       O emptyValue,
                       Predicate<O> presentPredicate,
               Supplier<DefaultTerminalSink<T, O>> sinkSupplier) {
            this.mustFindFirst = mustFindFirst;
            this.shape = shape;
            this.emptyValue = emptyValue;
            this.presentPredicate = presentPredicate;
            this.sinkSupplier = sinkSupplier;
        }

        @Override
        public int getOpFlags() {
            return DefaultStreamOpFlag.IS_SHORT_CIRCUIT | (mustFindFirst ? 0 : DefaultStreamOpFlag.NOT_ORDERED);
        }

        @Override
        public DefaultStreamShape inputShape() {
            return shape;
        }

        @Override
        public <S> O evaluateSequential(DefaultPipelineHelper<T> helper,
                                        Spliterator<S> spliterator) {
            O result = helper.wrapAndCopyInto(sinkSupplier.get(), spliterator).get();
            return result != null ? result : emptyValue;
        }

        @Override
        public <P_IN> O evaluateParallel(DefaultPipelineHelper<T> helper,
                                         Spliterator<P_IN> spliterator) {
            return new FindTask<>(this, helper, spliterator).invoke();
        }
    }

    private static abstract class FindSink<T, O> implements DefaultTerminalSink<T, O> {
        boolean hasValue;
        T value;

        FindSink() {} // Avoid creation of special accessor

        @Override
        public void accept(T value) {
            if (!hasValue) {
                hasValue = true;
                this.value = value;
            }
        }

        @Override
        public boolean cancellationRequested() {
            return hasValue;
        }

        static final class OfRef<T> extends DefaultFindOps.FindSink<T, Optional<T>> {
            @Override
            public Optional<T> get() {
                return hasValue ? Optional.of(value) : Optional.empty();
            }
        }

        static final class OfInt extends DefaultFindOps.FindSink<Integer, OptionalInt>
                implements DefaultSink.OfInt {
            @Override
            public void accept(int value) {
                // Boxing is OK here, since few values will actually flow into the sink
                accept((Integer) value);
            }

            @Override
            public OptionalInt get() {
                return hasValue ? OptionalInt.of(value) : OptionalInt.empty();
            }
        }

        static final class OfLong extends DefaultFindOps.FindSink<Long, OptionalLong>
                implements DefaultSink.OfLong {
            @Override
            public void accept(long value) {
                // Boxing is OK here, since few values will actually flow into the sink
                accept((Long) value);
            }

            @Override
            public OptionalLong get() {
                return hasValue ? OptionalLong.of(value) : OptionalLong.empty();
            }
        }

        static final class OfDouble extends DefaultFindOps.FindSink<Double, OptionalDouble>
                implements DefaultSink.OfDouble {
            @Override
            public void accept(double value) {
                // Boxing is OK here, since few values will actually flow into the sink
                accept((Double) value);
            }

            @Override
            public OptionalDouble get() {
                return hasValue ? OptionalDouble.of(value) : OptionalDouble.empty();
            }
        }
    }

    private static final class FindTask<P_IN, P_OUT, O>
            extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, O, FindTask<P_IN, P_OUT, O>> {
        private final FindOp<P_OUT, O> op;

        FindTask(FindOp<P_OUT, O> op,
                 DefaultPipelineHelper<P_OUT> helper,
                 Spliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

        FindTask(FindTask<P_IN, P_OUT, O> parent, Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        @Override
        protected FindTask<P_IN, P_OUT, O> makeChild(Spliterator<P_IN> spliterator) {
            return new FindTask<>(this, spliterator);
        }

        @Override
        protected O getEmptyResult() {
            return op.emptyValue;
        }

        private void foundResult(O answer) {
            if (isLeftmostNode())
                shortCircuit(answer);
            else
                cancelLaterNodes();
        }

        @Override
        protected O doLeaf() {
            O result = helper.wrapAndCopyInto(op.sinkSupplier.get(), spliterator).get();
            if (!op.mustFindFirst) {
                if (result != null)
                    shortCircuit(result);
                return null;
            }
            else {
                if (result != null) {
                    foundResult(result);
                    return result;
                }
                else
                    return null;
            }
        }

        @Override
        public void onCompletion(CountedCompleter<?> caller) {
            if (op.mustFindFirst) {
                    for (FindTask<P_IN, P_OUT, O> child = leftChild, p = null; child != p;
                         p = child, child = rightChild) {
                    O result = child.getLocalResult();
                    if (result != null && op.presentPredicate.test(result)) {
                        setLocalResult(result);
                        foundResult(result);
                        break;
                    }
                }
            }
            super.onCompletion(caller);
        }
    }
}

