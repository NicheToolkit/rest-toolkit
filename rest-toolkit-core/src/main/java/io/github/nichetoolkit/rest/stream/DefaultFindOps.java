
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.PredicateActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.*;

final class DefaultFindOps {

    private DefaultFindOps() {
    }

    public static <T> DefaultTerminalOp<T, Optional<T>> makeRef(boolean mustFindFirst) {
        return new FindOp<>(mustFindFirst, DefaultStreamShape.REFERENCE, Optional.empty(),
                Optional::isPresent, FindSink.OfRef::new);
    }


    private static final class FindOp<T, O> implements DefaultTerminalOp<T, O> {
        private final DefaultStreamShape shape;
        final boolean mustFindFirst;
        final O emptyValue;
        final PredicateActuator<O> presentPredicate;
        final SupplierActuator<DefaultTerminalSink<T, O>> sinkSupplier;

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

    private static abstract class FindSink<T, O> implements DefaultTerminalSink<T, O> {
        boolean hasValue;
        T value;

        FindSink() {
        } // Avoid creation of special accessor

        @Override
        public void actuate(T value) {
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
            public Optional<T> actuate() {
                return hasValue ? Optional.of(value) : Optional.empty();
            }
        }
    }

    private static final class FindTask<P_IN, P_OUT, O>
            extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, O, FindTask<P_IN, P_OUT, O>> {
        private final FindOp<P_OUT, O> op;

        FindTask(FindOp<P_OUT, O> op,
                 DefaultPipelineHelper<P_OUT> helper,
                 DefaultSpliterator<P_IN> spliterator) {
            super(helper, spliterator);
            this.op = op;
        }

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

