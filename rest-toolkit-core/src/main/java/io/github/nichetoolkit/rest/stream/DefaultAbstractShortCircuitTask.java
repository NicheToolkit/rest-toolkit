package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.concurrent.atomic.AtomicReference;

abstract class DefaultAbstractShortCircuitTask<P_IN, P_OUT, R,
        K extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, R, K>>
        extends DefaultAbstractTask<P_IN, P_OUT, R, K> {
    protected final AtomicReference<R> sharedResult;

    protected volatile boolean canceled;

    protected DefaultAbstractShortCircuitTask(DefaultPipelineHelper<P_OUT> helper,
                                              DefaultSpliterator<P_IN> spliterator) {
        super(helper, spliterator);
        sharedResult = new AtomicReference<>(null);
    }

    protected DefaultAbstractShortCircuitTask(K parent,
                                              DefaultSpliterator<P_IN> spliterator) {
        super(parent, spliterator);
        sharedResult = parent.sharedResult;
    }

    protected abstract R getEmptyResult();

    @Override
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

    protected void cancel() {
        canceled = true;
    }

    protected boolean taskCanceled() {
        boolean cancel = canceled;
        if (!cancel) {
            for (K parent = getParent(); !cancel && parent != null; parent = parent.getParent())
                cancel = parent.canceled;
        }

        return cancel;
    }

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
