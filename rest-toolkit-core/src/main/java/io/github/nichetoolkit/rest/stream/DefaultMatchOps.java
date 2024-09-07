
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.PredicateActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Objects;

final class DefaultMatchOps {

    private DefaultMatchOps() {
    }

    enum MatchKind {
        ANY(true, true),

        ALL(false, false),

        NONE(true, false);

        private final boolean stopOnPredicateMatches;
        private final boolean shortCircuitResult;

        MatchKind(boolean stopOnPredicateMatches,
                  boolean shortCircuitResult) {
            this.stopOnPredicateMatches = stopOnPredicateMatches;
            this.shortCircuitResult = shortCircuitResult;
        }
    }

    public static <T> DefaultTerminalOp<T, Boolean> makeRef(PredicateActuator<? super T> predicate,
                                                            MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        class MatchSink extends BooleanTerminalSink<T> {
            MatchSink() {
                super(matchKind);
            }

            @Override
            public void actuate(T t) throws RestException {
                if (!stop && predicate.actuate(t) == matchKind.stopOnPredicateMatches) {
                    stop = true;
                    value = matchKind.shortCircuitResult;
                }
            }
        }

        return new MatchOp<>(DefaultStreamShape.REFERENCE, matchKind, MatchSink::new);
    }

    private static final class MatchOp<T> implements DefaultTerminalOp<T, Boolean> {
        private final DefaultStreamShape inputShape;
        final MatchKind matchKind;
        final SupplierActuator<BooleanTerminalSink<T>> sinkSupplier;

        MatchOp(DefaultStreamShape shape,
                MatchKind matchKind,
                SupplierActuator<BooleanTerminalSink<T>> sinkSupplier) {
            this.inputShape = shape;
            this.matchKind = matchKind;
            this.sinkSupplier = sinkSupplier;
        }

        @Override
        public int getOpFlags() {
            return DefaultStreamOpFlag.IS_SHORT_CIRCUIT | DefaultStreamOpFlag.NOT_ORDERED;
        }

        @Override
        public DefaultStreamShape inputShape() {
            return inputShape;
        }

        @Override
        public <S> Boolean evaluateSequential(DefaultPipelineHelper<T> helper,
                                              DefaultSpliterator<S> spliterator) throws RestException {
            return helper.wrapAndCopyInto(sinkSupplier.actuate(), spliterator).getAndClearState();
        }

        @Override
        public <S> Boolean evaluateParallel(DefaultPipelineHelper<T> helper,
                                            DefaultSpliterator<S> spliterator) {
            return new MatchTask<>(this, helper, spliterator).invoke();
        }
    }

    private static abstract class BooleanTerminalSink<T> implements DefaultSink<T> {
        boolean stop;
        boolean value;

        BooleanTerminalSink(MatchKind matchKind) {
            value = !matchKind.shortCircuitResult;
        }

        public boolean getAndClearState() {
            return value;
        }

        @Override
        public boolean cancellationRequested() {
            return stop;
        }
    }

    private static final class MatchTask<P_IN, P_OUT>
            extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, Boolean, MatchTask<P_IN, P_OUT>> {
        private final MatchOp<P_OUT> op;

        MatchTask(MatchOp<P_OUT> op, DefaultPipelineHelper<P_OUT> helper,
                  DefaultSpliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

        MatchTask(MatchTask<P_IN, P_OUT> parent, DefaultSpliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        @Override
        protected MatchTask<P_IN, P_OUT> makeChild(DefaultSpliterator<P_IN> spliterator) {
            return new MatchTask<>(this, spliterator);
        }

        @Override
        protected Boolean doLeaf() throws RestException {
            boolean b = helper.wrapAndCopyInto(op.sinkSupplier.actuate(), spliterator).getAndClearState();
            if (b == op.matchKind.shortCircuitResult)
                shortCircuit(b);
            return null;
        }

        @Override
        protected Boolean getEmptyResult() {
            return !op.matchKind.shortCircuitResult;
        }
    }
}

