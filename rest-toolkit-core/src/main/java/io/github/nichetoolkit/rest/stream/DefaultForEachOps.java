package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;

/**
 * <code>DefaultForEachOps</code>
 * <p>The type default for each ops class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
final class DefaultForEachOps {

    /**
     * <code>DefaultForEachOps</code>
     * <p>Instantiates a new default for each ops.</p>
     */
    private DefaultForEachOps() {
    }

    /**
     * <code>makeRef</code>
     * <p>The ref method.</p>
     * @param <T>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param action  {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @param ordered boolean <p>The ordered parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultTerminalOp} <p>The ref return object is <code>DefaultTerminalOp</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     */
    public static <T> DefaultTerminalOp<T, Void> makeRef(ConsumerActuator<? super T> action,
                                                         boolean ordered) {
        Objects.requireNonNull(action);
        return new ForEachOp.OfRef<>(action, ordered);
    }

    /**
     * <code>ForEachOp</code>
     * <p>The type for each op class.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalOp
     * @see io.github.nichetoolkit.rest.stream.DefaultTerminalSink
     * @since Jdk1.8
     */
    static abstract class ForEachOp<T>
            implements DefaultTerminalOp<T, Void>, DefaultTerminalSink<T, Void> {
        /**
         * <code>ordered</code>
         * <p>The <code>ordered</code> field.</p>
         */
        private final boolean ordered;

        /**
         * <code>ForEachOp</code>
         * <p>Instantiates a new for each op.</p>
         * @param ordered boolean <p>The ordered parameter is <code>boolean</code> type.</p>
         */
        protected ForEachOp(boolean ordered) {
            this.ordered = ordered;
        }

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

        @Override
        public Void actuate() {
            return null;
        }

        /**
         * <code>OfRef</code>
         * <p>The type of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @since Jdk1.8
         */
        static final class OfRef<T> extends ForEachOp<T> {
            /**
             * <code>consumer</code>
             * {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The <code>consumer</code> field.</p>
             * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
             */
            final ConsumerActuator<? super T> consumer;

            /**
             * <code>OfRef</code>
             * <p>Instantiates a new of ref.</p>
             * @param consumer {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The consumer parameter is <code>ConsumerActuator</code> type.</p>
             * @param ordered  boolean <p>The ordered parameter is <code>boolean</code> type.</p>
             * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
             */
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

    /**
     * <code>ForEachTask</code>
     * <p>The type for each task class.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultCountedCompleter
     * @since Jdk1.8
     */
    static final class ForEachTask<S, T> extends DefaultCountedCompleter<Void> {
        /**
         * <code>spliterator</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The <code>spliterator</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        private DefaultSpliterator<S> spliterator;
        /**
         * <code>sink</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The <code>sink</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         */
        private final DefaultSink<S> sink;
        /**
         * <code>helper</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The <code>helper</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         */
        private final DefaultPipelineHelper<T> helper;
        /**
         * <code>targetSize</code>
         * <p>The <code>targetSize</code> field.</p>
         */
        private long targetSize;

        /**
         * <code>ForEachTask</code>
         * <p>Instantiates a new for each task.</p>
         * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @param sink        {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The sink parameter is <code>DefaultSink</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         */
        ForEachTask(DefaultPipelineHelper<T> helper,
                    DefaultSpliterator<S> spliterator,
                    DefaultSink<S> sink) {
            super(null);
            this.sink = sink;
            this.helper = helper;
            this.spliterator = spliterator;
            this.targetSize = 0L;
        }

        /**
         * <code>ForEachTask</code>
         * <p>Instantiates a new for each task.</p>
         * @param parent      {@link io.github.nichetoolkit.rest.stream.DefaultForEachOps.ForEachTask} <p>The parent parameter is <code>ForEachTask</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
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

    /**
     * <code>ForEachOrderedTask</code>
     * <p>The type for each ordered task class.</p>
     * @param <S> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultCountedCompleter
     * @since Jdk1.8
     */
    static final class ForEachOrderedTask<S, T> extends DefaultCountedCompleter<Void> {

        /**
         * <code>helper</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The <code>helper</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         */
        private final DefaultPipelineHelper<T> helper;
        /**
         * <code>spliterator</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The <code>spliterator</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
        private DefaultSpliterator<S> spliterator;
        /**
         * <code>targetSize</code>
         * <p>The <code>targetSize</code> field.</p>
         */
        private final long targetSize;
        /**
         * <code>completionMap</code>
         * {@link java.util.concurrent.ConcurrentHashMap} <p>The <code>completionMap</code> field.</p>
         * @see java.util.concurrent.ConcurrentHashMap
         */
        private final ConcurrentHashMap<ForEachOrderedTask<S, T>, ForEachOrderedTask<S, T>> completionMap;
        /**
         * <code>action</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The <code>action</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         */
        private final DefaultSink<T> action;
        /**
         * <code>leftPredecessor</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultForEachOps.ForEachOrderedTask} <p>The <code>leftPredecessor</code> field.</p>
         */
        private final ForEachOrderedTask<S, T> leftPredecessor;
        /**
         * <code>node</code>
         * {@link io.github.nichetoolkit.rest.stream.DefaultNode} <p>The <code>node</code> field.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultNode
         */
        private DefaultNode<T> node;

        /**
         * <code>ForEachOrderedTask</code>
         * <p>Instantiates a new for each ordered task.</p>
         * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
         * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @param action      {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>The action parameter is <code>DefaultSink</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         * @see io.github.nichetoolkit.rest.RestException
         */
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

        /**
         * <code>ForEachOrderedTask</code>
         * <p>Instantiates a new for each ordered task.</p>
         * @param parent          {@link io.github.nichetoolkit.rest.stream.DefaultForEachOps.ForEachOrderedTask} <p>The parent parameter is <code>ForEachOrderedTask</code> type.</p>
         * @param spliterator     {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
         * @param leftPredecessor {@link io.github.nichetoolkit.rest.stream.DefaultForEachOps.ForEachOrderedTask} <p>The left predecessor parameter is <code>ForEachOrderedTask</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
         */
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

        /**
         * <code>doCompute</code>
         * <p>The compute method.</p>
         * @param <S>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
         * @param task {@link io.github.nichetoolkit.rest.stream.DefaultForEachOps.ForEachOrderedTask} <p>The task parameter is <code>ForEachOrderedTask</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see java.lang.SuppressWarnings
         * @see io.github.nichetoolkit.rest.RestException
         */
        @SuppressWarnings("Duplicates")
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
                task.addToPendingCount(1);
                rightChild.addToPendingCount(1);
                task.completionMap.put(leftChild, rightChild);
                if (task.leftPredecessor != null) {
                    leftChild.addToPendingCount(1);
                    if (task.completionMap.replace(task.leftPredecessor, task, leftChild)) {
                        task.addToPendingCount(-1);
                    } else {
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
                node.forEach(action);
                node = null;
            } else if (spliterator != null) {
                helper.wrapAndCopyInto(action, spliterator);
                spliterator = null;
            }
            ForEachOrderedTask<S, T> leftDescendant = completionMap.remove(this);
            if (leftDescendant != null)
                leftDescendant.tryComplete();
        }
    }
}
