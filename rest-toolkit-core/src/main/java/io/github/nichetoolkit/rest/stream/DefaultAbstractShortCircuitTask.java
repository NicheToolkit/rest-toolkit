package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <code>DefaultAbstractShortCircuitTask</code>
 * <p>The type default abstract short circuit task class.</p>
 * @param <P_IN>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <P_OUT> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <R>     {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @param <K>     {@link io.github.nichetoolkit.rest.stream.DefaultAbstractShortCircuitTask} <p>The generic parameter is <code>DefaultAbstractShortCircuitTask</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.stream.DefaultAbstractTask
 * @since Jdk1.8
 */
abstract class DefaultAbstractShortCircuitTask<P_IN, P_OUT, R,
        K extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, R, K>>
        extends DefaultAbstractTask<P_IN, P_OUT, R, K> {
    /**
     * <code>sharedResult</code>
     * {@link java.util.concurrent.atomic.AtomicReference} <p>The <code>sharedResult</code> field.</p>
     * @see java.util.concurrent.atomic.AtomicReference
     */
    protected final AtomicReference<R> sharedResult;

    /**
     * <code>canceled</code>
     * <p>The <code>canceled</code> field.</p>
     */
    protected volatile boolean canceled;

    /**
     * <code>DefaultAbstractShortCircuitTask</code>
     * <p>Instantiates a new default abstract short circuit task.</p>
     * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>The helper parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    protected DefaultAbstractShortCircuitTask(DefaultPipelineHelper<P_OUT> helper,
                                              DefaultSpliterator<P_IN> spliterator) {
        super(helper, spliterator);
        sharedResult = new AtomicReference<>(null);
    }

    /**
     * <code>DefaultAbstractShortCircuitTask</code>
     * <p>Instantiates a new default abstract short circuit task.</p>
     * @param parent      K <p>The parent parameter is <code>K</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    protected DefaultAbstractShortCircuitTask(K parent,
                                              DefaultSpliterator<P_IN> spliterator) {
        super(parent, spliterator);
        sharedResult = parent.sharedResult;
    }

    /**
     * <code>getEmptyResult</code>
     * <p>The empty result getter method.</p>
     * @return R <p>The empty result return object is <code>R</code> type.</p>
     */
    protected abstract R getEmptyResult();

    @Override
    @SuppressWarnings("Duplicates")
    public void computes() throws RestException {
        DefaultSpliterator<P_IN> rs = spliterator, ls;
        long sizeEstimate = rs.estimateSize();
        long sizeThreshold = getTargetSize(sizeEstimate);
        boolean forkRight = false;
        @SuppressWarnings("unchecked") K task = (K) this;
        R result;
        while ((result = sharedResult.get()) == null) {
            if (task.taskCanceled()) {
                result = task.getEmptyResult();
                break;
            }
            if (sizeEstimate <= sizeThreshold || (ls = rs.trySplit()) == null) {
                result = task.doLeaf();
                break;
            }
            K leftChild, rightChild, taskToFork;
            task.leftChild = leftChild = task.makeChild(ls);
            task.rightChild = rightChild = task.makeChild(rs);
            task.setPendingCount(1);
            if (forkRight) {
                forkRight = false;
                rs = ls;
                task = leftChild;
                taskToFork = rightChild;
            } else {
                forkRight = true;
                task = rightChild;
                taskToFork = leftChild;
            }
            taskToFork.fork();
            sizeEstimate = rs.estimateSize();
        }
        task.setLocalResult(result);
        task.tryComplete();
    }


    /**
     * <code>shortCircuit</code>
     * <p>The circuit method.</p>
     * @param result R <p>The result parameter is <code>R</code> type.</p>
     */
    protected void shortCircuit(R result) {
        if (result != null)
            sharedResult.compareAndSet(null, result);
    }

    @Override
    protected void setLocalResult(R localResult) {
        if (isRoot()) {
            if (localResult != null)
                sharedResult.compareAndSet(null, localResult);
        } else
            super.setLocalResult(localResult);
    }

    @Override
    public R getRawResult() {
        return getLocalResult();
    }

    @Override
    public R getLocalResult() {
        if (isRoot()) {
            R answer = sharedResult.get();
            return (answer == null) ? getEmptyResult() : answer;
        } else
            return super.getLocalResult();
    }

    /**
     * <code>cancel</code>
     * <p>The method.</p>
     */
    protected void cancel() {
        canceled = true;
    }

    /**
     * <code>taskCanceled</code>
     * <p>The canceled method.</p>
     * @return boolean <p>The canceled return object is <code>boolean</code> type.</p>
     */
    protected boolean taskCanceled() {
        boolean cancel = canceled;
        if (!cancel) {
            for (K parent = getParent(); !cancel && parent != null; parent = parent.getParent())
                cancel = parent.canceled;
        }

        return cancel;
    }

    /**
     * <code>cancelLaterNodes</code>
     * <p>The later nodes method.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    protected void cancelLaterNodes() {
        // Go up the tree, cancel right siblings of this node and all parents
        for (K parent = getParent(), node = (K) this;
             parent != null;
             node = parent, parent = parent.getParent()) {
            // If node is a left child of parent, then has a right sibling
            if (parent.leftChild == node) {
                K rightSibling = parent.rightChild;
                if (!rightSibling.canceled)
                    rightSibling.cancel();
            }
        }
    }
}
