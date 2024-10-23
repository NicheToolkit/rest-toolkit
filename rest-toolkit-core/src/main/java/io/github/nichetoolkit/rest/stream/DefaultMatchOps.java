package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.PredicateActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Objects;

/**
 * <code>DefaultMatchOps</code>
 * <p>The default match ops class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
final class DefaultMatchOps {

    /**
     * <code>DefaultMatchOps</code>
     * <p>Instantiates a new default match ops.</p>
     */
    private DefaultMatchOps() {
    }

    /**
     * <code>MatchKind</code>
     * <p>The match kind enumeration.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    enum MatchKind {
        /**
         * <code>ANY</code>
         * <p>The any match kind field.</p>
         */
        ANY(true, true),

        /**
         * <code>ALL</code>
         * <p>The all match kind field.</p>
         */
        ALL(false, false),

        /**
         * <code>NONE</code>
         * <p>The none match kind field.</p>
         */
        NONE(true, false);

        /**
         * <code>stopOnPredicateMatches</code>
         * <p>The <code>stopOnPredicateMatches</code> field.</p>
         */
        private final boolean stopOnPredicateMatches;
        /**
         * <code>shortCircuitResult</code>
         * <p>The <code>shortCircuitResult</code> field.</p>
         */
        private final boolean shortCircuitResult;

        /**
         * <code>MatchKind</code>
         * <p>Instantiates a new match kind.</p>
         * @param stopOnPredicateMatches boolean <p>The stop on predicate matches parameter is <code>boolean</code> type.</p>
         * @param shortCircuitResult     boolean <p>The short circuit result parameter is <code>boolean</code> type.</p>
         */
        MatchKind(boolean stopOnPredicateMatches,
                  boolean shortCircuitResult) {
            this.stopOnPredicateMatches = stopOnPredicateMatches;
            this.shortCircuitResult = shortCircuitResult;
        }
    }

    /**
     * <code>makeRef</code>
     * <p>The make ref method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @param matchKind {@link io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind} <p>The match kind parameter is <code>MatchKind</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>The make ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     */
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

    /**
     * <code>MatchOp</code>
     * <p>The match op class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     * @since Jdk1.8
     */
    private static final class MatchOp<T> implements DefaultTerminalOp<T, Boolean> {
        /**
         * <code>inputShape</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>The <code>inputShape</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
         */
        private final DefaultStreamShape inputShape;
        /**
         * <code>matchKind</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind} <p>The <code>matchKind</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind
         */
        final MatchKind matchKind;
        /**
         * <code>sinkSupplier</code>
         * {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The <code>sinkSupplier</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        final SupplierActuator<BooleanTerminalSink<T>> sinkSupplier;

        /**
         * <code>MatchOp</code>
         * <p>Instantiates a new match op.</p>
         * @param shape        {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>The shape parameter is <code>DefaultStreamShape</code> type.</p>
         * @param matchKind    {@link io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind} <p>The match kind parameter is <code>MatchKind</code> type.</p>
         * @param sinkSupplier {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The sink supplier parameter is <code>SupplierActuator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
         * @see io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
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

    /**
     * <code>BooleanTerminalSink</code>
     * <p>The boolean terminal sink class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSink
     * @since Jdk1.8
     */
    private static abstract class BooleanTerminalSink<T> implements DefaultSink<T> {
        /**
         * <code>stop</code>
         * <p>The <code>stop</code> field.</p>
         */
        boolean stop;
        /**
         * <code>value</code>
         * <p>The <code>value</code> field.</p>
         */
        boolean value;

        /**
         * <code>BooleanTerminalSink</code>
         * <p>Instantiates a new boolean terminal sink.</p>
         * @param matchKind {@link io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind} <p>The match kind parameter is <code>MatchKind</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchKind
         */
        BooleanTerminalSink(MatchKind matchKind) {
            value = !matchKind.shortCircuitResult;
        }

        /**
         * <code>getAndClearState</code>
         * <p>The get and clear state getter method.</p>
         * @return boolean <p>The get and clear state return object is <code>boolean</code> type.</p>
         */
        public boolean getAndClearState() {
            return value;
        }

        @Override
        public boolean cancellationRequested() {
            return stop;
        }
    }

    /**
     * <code>MatchTask</code>
     * <p>The match task class.</p>
     * @param <P_IN>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <P_OUT> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultAbstractShortCircuitTask
     * @since Jdk1.8
     */
    private static final class MatchTask<P_IN, P_OUT>
            extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, Boolean, MatchTask<P_IN, P_OUT>> {
        /**
         * <code>op</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchOp} <p>The <code>op</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchOp
         */
        private final MatchOp<P_OUT> op;

        /**
         * <code>MatchTask</code>
         * <p>Instantiates a new match task.</p>
         * @param op          {@link io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchOp} <p>The op parameter is <code>MatchOp</code> type.</p>
         * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchOp
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        MatchTask(MatchOp<P_OUT> op, DefaultPipelineHelper<P_OUT> helper,
                  DefaultSpliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

        /**
         * <code>MatchTask</code>
         * <p>Instantiates a new match task.</p>
         * @param parent      {@link io.github.nichetoolkit.rest.stream.DefaultMatchOps.MatchTask} <p>The parent parameter is <code>MatchTask</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
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

