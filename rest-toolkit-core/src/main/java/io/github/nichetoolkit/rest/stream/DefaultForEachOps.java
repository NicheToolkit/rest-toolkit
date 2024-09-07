
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;

final class DefaultForEachOps {

    private DefaultForEachOps() {
    }

    public static <T> DefaultTerminalOp<T, Void> makeRef(ConsumerActuator<? super T> action,
                                                         boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfRef<>(action, ordered);
    }

    static abstract class ForEachOp<T>
            implements DefaultTerminalOp<T, Void>, DefaultTerminalSink<T, Void> {
        private final boolean ordered;

        protected ForEachOp(boolean ordered) {
            this.ordered = ordered;
        }

        // DefaultTerminalOp

        @Override
        public int getOpFlags() {
            return ordered ? 0 : DefaultStreamOpFlag.NOT_ORDERED;
        }

        @Override
        public <S> Void evaluateSequential(DefaultPipelineHelper<T> helper,
                                           DefaultSpliterator<S> spliterator) throws RestException {
            return helper.wrapAndCopyInto(this, spliterator).actuate();
        }

        @Override
        public <S> Void evaluateParallel(DefaultPipelineHelper<T> helper,
                                         DefaultSpliterator<S> spliterator) throws RestException {
            if (ordered)
                new ForEachOrderedTask<>(helper, spliterator, this).invoke();
            else
                new ForEachTask<>(helper, spliterator, helper.wrapSink(this)).invoke();
            return null;
        }

        // TerminalDefaultSink

        @Override
        public Void actuate() {
            return null;
        }

        // Implementations

        static final class OfRef<T> extends ForEachOp<T> {
            final ConsumerActuator<? super T> consumer;

            OfRef(ConsumerActuator<? super T> consumer, boolean ordered) {
                super(ordered);
                this.consumer = consumer;
            }

            @Override
            public void actuate(T t) throws RestException {
                consumer.actuate(t);
            }
        }
    }

    static final class ForEachTask<S, T> extends DefaultCountedCompleter<Void> {
        private DefaultSpliterator<S> spliterator;
        private final DefaultSink<S> sink;
        private final DefaultPipelineHelper<T> helper;
        private long targetSize;

        ForEachTask(DefaultPipelineHelper<T> helper,
                    DefaultSpliterator<S> spliterator,
                    DefaultSink<S> sink) {
            super(null);
            this.sink = sink;
            this.helper = helper;
            this.spliterator = spliterator;
            this.targetSize = 0L;
        }

        ForEachTask(ForEachTask<S, T> parent, DefaultSpliterator<S> spliterator) {
            super(parent);
            this.spliterator = spliterator;
            this.sink = parent.sink;
            this.targetSize = parent.targetSize;
            this.helper = parent.helper;
        }

        public void computes() throws RestException {
            DefaultSpliterator<S> rightSplit = spliterator, leftSplit;
            long sizeEstimate = rightSplit.estimateSize(), sizeThreshold;
            if ((sizeThreshold = targetSize) == 0L)
                targetSize = sizeThreshold = DefaultAbstractTask.suggestTargetSize(sizeEstimate);
            boolean isShortCircuit = DefaultStreamOpFlag.SHORT_CIRCUIT.isKnown(helper.getStreamAndOpFlags());
            boolean forkRight = false;
            DefaultSink<S> taskDefaultSink = sink;
            ForEachTask<S, T> task = this;
            while (!isShortCircuit || !taskDefaultSink.cancellationRequested()) {
                if (sizeEstimate <= sizeThreshold ||
                        (leftSplit = rightSplit.trySplit()) == null) {
                    task.helper.copyInto(taskDefaultSink, rightSplit);
                    break;
                }
                ForEachTask<S, T> leftTask = new ForEachTask<>(task, leftSplit);
                task.addToPendingCount(1);
                ForEachTask<S, T> taskToFork;
                if (forkRight) {
                    forkRight = false;
                    rightSplit = leftSplit;
                    taskToFork = task;
                    task = leftTask;
                } else {
                    forkRight = true;
                    taskToFork = leftTask;
                }
                taskToFork.fork();
                sizeEstimate = rightSplit.estimateSize();
            }
            task.spliterator = null;
            task.propagateCompletion();
        }
    }

    static final class ForEachOrderedTask<S, T> extends DefaultCountedCompleter<Void> {

        private final DefaultPipelineHelper<T> helper;
        private DefaultSpliterator<S> spliterator;
        private final long targetSize;
        private final ConcurrentHashMap<ForEachOrderedTask<S, T>, ForEachOrderedTask<S, T>> completionMap;
        private final DefaultSink<T> action;
        private final ForEachOrderedTask<S, T> leftPredecessor;
        private DefaultNode<T> node;

        private ForEachOrderedTask(DefaultPipelineHelper<T> helper,
                                   DefaultSpliterator<S> spliterator,
                                   DefaultSink<T> action) throws RestException {
            super(null);
            this.helper = helper;
            this.spliterator = spliterator;
            this.targetSize = DefaultAbstractTask.suggestTargetSize(spliterator.estimateSize());
            this.completionMap = new ConcurrentHashMap<>(Math.max(16, DefaultAbstractTask.getLeafTarget() << 1));
            this.action = action;
            this.leftPredecessor = null;
        }

        ForEachOrderedTask(ForEachOrderedTask<S, T> parent,
                           DefaultSpliterator<S> spliterator,
                           ForEachOrderedTask<S, T> leftPredecessor) {
            super(parent);
            this.helper = parent.helper;
            this.spliterator = spliterator;
            this.targetSize = parent.targetSize;
            this.completionMap = parent.completionMap;
            this.action = parent.action;
            this.leftPredecessor = leftPredecessor;
        }

        @Override
        public void computes() throws RestException {
            doCompute(this);
        }

        private static <S, T> void doCompute(ForEachOrderedTask<S, T> task) throws RestException {
            DefaultSpliterator<S> rightSplit = task.spliterator, leftSplit;
            long sizeThreshold = task.targetSize;
            boolean forkRight = false;
            while (rightSplit.estimateSize() > sizeThreshold &&
                    (leftSplit = rightSplit.trySplit()) != null) {
                ForEachOrderedTask<S, T> leftChild =
                        new ForEachOrderedTask<>(task, leftSplit, task.leftPredecessor);
                ForEachOrderedTask<S, T> rightChild =
                        new ForEachOrderedTask<>(task, rightSplit, leftChild);

                // Fork the parent task
                // Completion of the left and right children "happens-before"
                // completion of the parent
                task.addToPendingCount(1);
                // Completion of the left child "happens-before" completion of
                // the right child
                rightChild.addToPendingCount(1);
                task.completionMap.put(leftChild, rightChild);

                // If task is not on the left spine
                if (task.leftPredecessor != null) {
                    /*
                     * Completion of left-predecessor, or left subtree,
                     * "happens-before" completion of left-most leaf node of
                     * right subtree.
                     * The left child's pending count needs to be updated before
                     * it is associated in the completion map, otherwise the
                     * left child can complete prematurely and violate the
                     * "happens-before" constraint.
                     */
                    leftChild.addToPendingCount(1);
                    // Update association of left-predecessor to left-most
                    // leaf node of right subtree
                    if (task.completionMap.replace(task.leftPredecessor, task, leftChild)) {
                        // If replaced, adjust the pending count of the parent
                        // to complete when its children complete
                        task.addToPendingCount(-1);
                    } else {
                        // Left-predecessor has already completed, parent's
                        // pending count is adjusted by left-predecessor;
                        // left child is ready to complete
                        leftChild.addToPendingCount(-1);
                    }
                }

                ForEachOrderedTask<S, T> taskToFork;
                if (forkRight) {
                    forkRight = false;
                    rightSplit = leftSplit;
                    task = leftChild;
                    taskToFork = rightChild;
                } else {
                    forkRight = true;
                    task = rightChild;
                    taskToFork = leftChild;
                }
                taskToFork.fork();
            }

            if (task.getPendingCount() > 0) {
                @SuppressWarnings("unchecked")
                IntFunction<T[]> generator = size -> (T[]) new Object[size];
                DefaultNode.Builder<T> nb = task.helper.makeNodeBuilder(
                        task.helper.exactOutputSizeIfKnown(rightSplit),
                        generator);
                task.node = task.helper.wrapAndCopyInto(nb, rightSplit).build();
                task.spliterator = null;
            }
            task.tryComplete();
        }

        @Override
        public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {
            if (node != null) {
                // Dump buffered elements from this leaf into the sink
                node.forEach(action);
                node = null;
            } else if (spliterator != null) {
                // Dump elements output from this leaf's pipeline into the sink
                helper.wrapAndCopyInto(action, spliterator);
                spliterator = null;
            }

            // The completion of this task *and* the dumping of elements
            // "happens-before" completion of the associated left-most leaf task
            // of right subtree (if any, which can be this task's right sibling)
            //
            ForEachOrderedTask<S, T> leftDescendant = completionMap.remove(this);
            if (leftDescendant != null)
                leftDescendant.tryComplete();
        }
    }
}
