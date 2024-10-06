package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.actuator.PredicateActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

/**
 * <code>DefaultFindOps</code>
 * <p>The type default find ops class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
final class DefaultFindOps {

    /**
     * <code>DefaultFindOps</code>
     * <p>Instantiates a new default find ops.</p>
     */
    private DefaultFindOps() {
    }

    /**
     * <code>makeRef</code>
     * <p>The ref method.</p>
     * @param <T>           {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mustFindFirst boolean <p>The must find first parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>The ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> DefaultTerminalOp<T, RestOptional<T>> makeRef(boolean mustFindFirst) throws RestException {
        return new FindOp<>(mustFindFirst, DefaultStreamShape.REFERENCE, RestOptional.empty(),
                RestOptional::isNullPresent, FindSink.OfRef::new);
    }

    /**
     * <code>makeRef</code>
     * <p>The ref method.</p>
     * @param <T>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>The ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> DefaultTerminalOp<T, RestOptional<T>> makeRef(PredicateActuator<T> predicate) throws RestException {

        class MatchSink extends DefaultFindOps.FindSink<T, RestOptional<T>> {

            MatchSink() {
                super();
            }

            @Override
            public void actuate(T value) throws RestException {
                if (!hasValue && predicate.actuate(value)) {
                    hasValue = true;
                    this.value = value;
                }
            }


            @Override
            public RestOptional<T> actuate() throws RestException {
                return hasValue ? RestOptional.of(value) : RestOptional.empty();
            }
        }

        return new FindOp<>(false, DefaultStreamShape.REFERENCE, RestOptional.empty(),
                (RestOptional<T> t) -> {
                    if (t.isNullPresent()) {
                        return predicate.actuate(t.get());
                    } else {
                        return false;
                    }
                }, MatchSink::new);
    }

    /**
     * <code>FindOp</code>
     * <p>The type find op class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <O> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     * @since Jdk1.8
     */
    private static final class FindOp<T, O> implements DefaultTerminalOp<T, O> {
        /**
         * <code>shape</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>The <code>shape</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
         */
        private final DefaultStreamShape shape;
        /**
         * <code>mustFindFirst</code>
         * <p>The <code>mustFindFirst</code> field.</p>
         */
        final boolean mustFindFirst;
        /**
         * <code>emptyValue</code>
         * <p>The <code>emptyValue</code> field.</p>
         */
        final O emptyValue;
        /**
         * <code>presentPredicate</code>
         * {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The <code>presentPredicate</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
         */
        final PredicateActuator<O> presentPredicate;
        /**
         * <code>sinkSupplier</code>
         * {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The <code>sinkSupplier</code> field.</p>
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        final SupplierActuator<DefaultTerminalSink<T, O>> sinkSupplier;

        /**
         * <code>FindOp</code>
         * <p>Instantiates a new find op.</p>
         * @param mustFindFirst    boolean <p>The must find first parameter is <code>boolean</code> type.</p>
         * @param shape            {@link io.github.nichetoolkit.rest.stream.DefaultStreamShape} <p>The shape parameter is <code>DefaultStreamShape</code> type.</p>
         * @param emptyValue       O <p>The empty value parameter is <code>O</code> type.</p>
         * @param presentPredicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The present predicate parameter is <code>PredicateActuator</code> type.</p>
         * @param sinkSupplier     {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The sink supplier parameter is <code>SupplierActuator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultStreamShape
         * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
         * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
         */
        FindOp(boolean mustFindFirst,
               DefaultStreamShape shape,
               O emptyValue,
               PredicateActuator<O> presentPredicate,
               SupplierActuator<DefaultTerminalSink<T, O>> sinkSupplier) {
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
                                        DefaultSpliterator<S> spliterator) throws RestException {
            O result = helper.wrapAndCopyInto(sinkSupplier.actuate(), spliterator).actuate();
            return result != null ? result : emptyValue;
        }

        @Override
        public <P_IN> O evaluateParallel(DefaultPipelineHelper<T> helper,
                                         DefaultSpliterator<P_IN> spliterator) {
            return new FindTask<>(this, helper, spliterator).invoke();
        }
    }

    /**
     * <code>FindSink</code>
     * <p>The type find sink class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <O> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalSink
     * @since Jdk1.8
     */
    private static abstract class FindSink<T, O> implements DefaultTerminalSink<T, O> {
        /**
         * <code>hasValue</code>
         * <p>The <code>hasValue</code> field.</p>
         */
        boolean hasValue;
        /**
         * <code>value</code>
         * <p>The <code>value</code> field.</p>
         */
        T value;

        /**
         * <code>FindSink</code>
         * <p>Instantiates a new find sink.</p>
         */
        FindSink() {
        } // Avoid creation of special accessor

        @Override
        public void actuate(T value) throws RestException {
            if (!hasValue) {
                hasValue = true;
                this.value = value;
            }
        }

        @Override
        public boolean cancellationRequested() {
            return hasValue;
        }

        /**
         * <code>OfRef</code>
         * <p>The type of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @since Jdk1.8
         */
        static final class OfRef<T> extends DefaultFindOps.FindSink<T, RestOptional<T>> {

            @Override
            public RestOptional<T> actuate() {
                return hasValue ? RestOptional.of(value) : RestOptional.empty();
            }
        }
    }

    /**
     * <code>FindTask</code>
     * <p>The type find task class.</p>
     * @param <P_IN>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <P_OUT> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <O>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultAbstractShortCircuitTask
     * @since Jdk1.8
     */
    private static final class FindTask<P_IN, P_OUT, O>
            extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, O, FindTask<P_IN, P_OUT, O>> {
        /**
         * <code>op</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultFindOps.FindOp} <p>The <code>op</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultFindOps.FindOp
         */
        private final FindOp<P_OUT, O> op;

        /**
         * <code>FindTask</code>
         * <p>Instantiates a new find task.</p>
         * @param op          {@link io.github.nichetoolkit.rest.stream.DefaultFindOps.FindOp} <p>The op parameter is <code>FindOp</code> type.</p>
         * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultFindOps.FindOp
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        FindTask(FindOp<P_OUT, O> op,
                 DefaultPipelineHelper<P_OUT> helper,
                 DefaultSpliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

        /**
         * <code>FindTask</code>
         * <p>Instantiates a new find task.</p>
         * @param parent      {@link io.github.nichetoolkit.rest.stream.DefaultFindOps.FindTask} <p>The parent parameter is <code>FindTask</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        FindTask(FindTask<P_IN, P_OUT, O> parent, DefaultSpliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
        }

        @Override
        protected FindTask<P_IN, P_OUT, O> makeChild(DefaultSpliterator<P_IN> spliterator) {
            return new FindTask<>(this, spliterator);
        }

        @Override
        protected O getEmptyResult() {
            return op.emptyValue;
        }

        /**
         * <code>foundResult</code>
         * <p>The result method.</p>
         * @param answer O <p>The answer parameter is <code>O</code> type.</p>
         */
        private void foundResult(O answer) {
            if (isLeftmostNode())
                shortCircuit(answer);
            else
                cancelLaterNodes();
        }

        @Override
        protected O doLeaf() throws RestException {
            O result = helper.wrapAndCopyInto(op.sinkSupplier.actuate(), spliterator).actuate();
            if (!op.mustFindFirst) {
                if (result != null)
                    shortCircuit(result);
                return null;
            } else {
                if (result != null) {
                    foundResult(result);
                    return result;
                } else
                    return null;
            }
        }

        @Override
        public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {
            if (op.mustFindFirst) {
                for (FindTask<P_IN, P_OUT, O> child = leftChild, p = null; child != p;
                     p = child, child = rightChild) {
                    O result = child.getLocalResult();
                    if (result != null && op.presentPredicate.actuate(result)) {
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

