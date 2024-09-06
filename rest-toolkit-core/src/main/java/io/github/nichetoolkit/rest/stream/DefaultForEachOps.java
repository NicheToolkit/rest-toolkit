
package io.github.nichetoolkit.rest.stream;

import java.util.Objects;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountedCompleter;
import java.util.function.*;

final class DefaultForEachOps {

    private DefaultForEachOps() { }

    public static <T> DefaultTerminalOp<T, Void> makeRef(Consumer<? super T> action,
                                                  boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfRef<>(action, ordered);
    }

    public static DefaultTerminalOp<Integer, Void> makeInt(IntConsumer action,
                                                    boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfInt(action, ordered);
    }

    public static DefaultTerminalOp<Long, Void> makeLong(LongConsumer action,
                                                  boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfLong(action, ordered);
    }

    public static DefaultTerminalOp<Double, Void> makeDouble(DoubleConsumer action,
                                                      boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfDouble(action, ordered);
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
                                           Spliterator<S> spliterator) {
            return helper.wrapAndCopyInto(this, spliterator).get();
        }

        @Override
        public <S> Void evaluateParallel(DefaultPipelineHelper<T> helper,
                                         Spliterator<S> spliterator) {
            if (ordered)
                new ForEachOrderedTask<>(helper, spliterator, this).invoke();
            else
                new ForEachTask<>(helper, spliterator, helper.wrapSink(this)).invoke();
            return null;
        }

        // TerminalDefaultSink

        @Override
        public Void get() {
            return null;
        }

        // Implementations

        static final class OfRef<T> extends ForEachOp<T> {
            final Consumer<? super T> consumer;

            OfRef(Consumer<? super T> consumer, boolean ordered) {
                super(ordered);
                this.consumer = consumer;
            }

            @Override
            public void accept(T t) {
                consumer.accept(t);
            }
        }

        static final class OfInt extends ForEachOp<Integer>
                implements DefaultSink.OfInt {
            final IntConsumer consumer;

            OfInt(IntConsumer consumer, boolean ordered) {
                super(ordered);
                this.consumer = consumer;
            }

            @Override
            public DefaultStreamShape inputShape() {
                return DefaultStreamShape.INT_VALUE;
            }

            @Override
            public void accept(int t) {
                consumer.accept(t);
            }
        }

        static final class OfLong extends ForEachOp<Long>
                implements DefaultSink.OfLong {
            final LongConsumer consumer;

            OfLong(LongConsumer consumer, boolean ordered) {
                super(ordered);
                this.consumer = consumer;
            }

            @Override
            public DefaultStreamShape inputShape() {
                return DefaultStreamShape.LONG_VALUE;
            }

            @Override
            public void accept(long t) {
                consumer.accept(t);
            }
        }

        static final class OfDouble extends ForEachOp<Double>
                implements DefaultSink.OfDouble {
            final DoubleConsumer consumer;

            OfDouble(DoubleConsumer consumer, boolean ordered) {
                super(ordered);
                this.consumer = consumer;
            }

            @Override
            public DefaultStreamShape inputShape() {
                return DefaultStreamShape.DOUBLE_VALUE;
            }

            @Override
            public void accept(double t) {
                consumer.accept(t);
            }
        }
    }

    static final class ForEachTask<S, T> extends CountedCompleter<Void> {
        private Spliterator<S> spliterator;
        private final DefaultSink<S> sink;
        private final DefaultPipelineHelper<T> helper;
        private long targetSize;

        ForEachTask(DefaultPipelineHelper<T> helper,
                    Spliterator<S> spliterator,
                    DefaultSink<S> sink) {
            super(null);
            this.sink = sink;
            this.helper = helper;
            this.spliterator = spliterator;
            this.targetSize = 0L;
        }

        ForEachTask(ForEachTask<S, T> parent, Spliterator<S> spliterator) {
            super(parent);
            this.spliterator = spliterator;
            this.sink = parent.sink;
            this.targetSize = parent.targetSize;
            this.helper = parent.helper;
        }

        // Similar to DefaultAbstractTask but doesn't need to track child tasks
        public void compute() {
            Spliterator<S> rightSplit = spliterator, leftSplit;
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
                }
                else {
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

    static final class ForEachOrderedTask<S, T> extends CountedCompleter<Void> {
        /*
         * Our goal is to ensure that the elements associated with a task are
         * processed according to an in-order traversal of the computation tree.
         * We use completion counts for representing these dependencies, so that
         * a task does not complete until all the tasks preceding it in this
         * order complete.  We use the "completion map" to associate the next
         * task in this order for any left child.  We increase the pending count
         * of any node on the right side of such a mapping by one to indicate
         * its dependency, and when a node on the left side of such a mapping
         * completes, it decrements the pending count of its corresponding right
         * side.  As the computation tree is expanded by splitting, we must
         * atomically update the mappings to maintain the invariant that the
         * completion map maps left children to the next node in the in-order
         * traversal.
         *
         * Take, for example, the following computation tree of tasks:
         *
         *       a
         *      / \
         *     b   c
         *    / \ / \
         *   d  e f  g
         *
         * The complete map will contain (not necessarily all at the same time)
         * the following associations:
         *
         *   d -> e
         *   b -> f
         *   f -> g
         *
         * Tasks e, f, g will have their pending counts increased by 1.
         *
         * The following relationships hold:
         *
         *   - completion of d "happens-before" e;
         *   - completion of d and e "happens-before b;
         *   - completion of b "happens-before" f; and
         *   - completion of f "happens-before" g
         *
         * Thus overall the "happens-before" relationship holds for the
         * reporting of elements, covered by tasks d, e, f and g, as specified
         * by the forEachOrdered operation.
         */

        private final DefaultPipelineHelper<T> helper;
        private Spliterator<S> spliterator;
        private final long targetSize;
        private final ConcurrentHashMap<ForEachOrderedTask<S, T>, ForEachOrderedTask<S, T>> completionMap;
        private final DefaultSink<T> action;
        private final ForEachOrderedTask<S, T> leftPredecessor;
        private DefaultNode<T> node;

        protected ForEachOrderedTask(DefaultPipelineHelper<T> helper,
                                     Spliterator<S> spliterator,
                                     DefaultSink<T> action) {
            super(null);
            this.helper = helper;
            this.spliterator = spliterator;
            this.targetSize = DefaultAbstractTask.suggestTargetSize(spliterator.estimateSize());
            // Size map to avoid concurrent re-sizes
            this.completionMap = new ConcurrentHashMap<>(Math.max(16, DefaultAbstractTask.getLeafTarget() << 1));
            this.action = action;
            this.leftPredecessor = null;
        }

        ForEachOrderedTask(ForEachOrderedTask<S, T> parent,
                           Spliterator<S> spliterator,
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
        public final void compute() {
            doCompute(this);
        }

        private static <S, T> void doCompute(ForEachOrderedTask<S, T> task) {
            Spliterator<S> rightSplit = task.spliterator, leftSplit;
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
                }
                else {
                    forkRight = true;
                    task = rightChild;
                    taskToFork = leftChild;
                }
                taskToFork.fork();
            }

            /*
             * Task's pending count is either 0 or 1.  If 1 then the completion
             * map will contain a value that is task, and two calls to
             * tryComplete are required for completion, one below and one
             * triggered by the completion of task's left-predecessor in
             * onCompletion.  Therefore there is no data race within the if
             * block.
             */
            if (task.getPendingCount() > 0) {
                // Cannot complete just yet so buffer elements into a DefaultNode
                // for use when completion occurs
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
        public void onCompletion(CountedCompleter<?> caller) {
            if (node != null) {
                // Dump buffered elements from this leaf into the sink
                node.forEach(action);
                node = null;
            }
            else if (spliterator != null) {
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
