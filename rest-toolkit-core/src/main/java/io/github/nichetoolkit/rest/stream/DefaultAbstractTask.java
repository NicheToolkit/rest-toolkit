package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

abstract class DefaultAbstractTask<P_IN, P_OUT, R,
        K extends DefaultAbstractTask<P_IN, P_OUT, R, K>>
        extends DefaultCountedCompleter<R> {

    private static final int LEAF_TARGET = ForkJoinPool.getCommonPoolParallelism() << 2;

    protected final DefaultPipelineHelper<P_OUT> helper;

    protected DefaultSpliterator<P_IN> spliterator;

    protected long targetSize;

    protected K leftChild;

    protected K rightChild;

    private R localResult;

    protected DefaultAbstractTask(DefaultPipelineHelper<P_OUT> helper,
                                  DefaultSpliterator<P_IN> spliterator) {
        super(null);
        this.helper = helper;
        this.spliterator = spliterator;
        this.targetSize = 0L;
    }

    protected DefaultAbstractTask(K parent,
                                  DefaultSpliterator<P_IN> spliterator) {
        super(parent);
        this.spliterator = spliterator;
        this.helper = parent.helper;
        this.targetSize = parent.targetSize;
    }

    public static int getLeafTarget() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) t).getPool().getParallelism() << 2;
        } else {
            return LEAF_TARGET;
        }
    }

    protected abstract K makeChild(DefaultSpliterator<P_IN> spliterator);

    protected abstract R doLeaf() throws RestException;

    public static long suggestTargetSize(long sizeEstimate) {
        long est = sizeEstimate / getLeafTarget();
        return est > 0L ? est : 1L;
    }

    protected final long getTargetSize(long sizeEstimate) {
        long s;
        return ((s = targetSize) != 0 ? s :
                (targetSize = suggestTargetSize(sizeEstimate)));
    }

    @Override
    public R getRawResult() {
        return localResult;
    }

    @Override
    protected void setRawResult(R result) {
        if (result != null)
            throw new IllegalStateException();
    }

    protected R getLocalResult() {
        return localResult;
    }

    protected void setLocalResult(R localResult) {
        this.localResult = localResult;
    }

    protected boolean isLeaf() {
        return leftChild == null;
    }

    protected boolean isRoot() {
        return getParent() == null;
    }

    @SuppressWarnings("unchecked")
    protected K getParent() {
        return (K) getCompleter();
    }

    @Override
    public void computes() throws RestException {
        DefaultSpliterator<P_IN> rs = spliterator, ls; // right, left spliterators
        long sizeEstimate = rs.estimateSize();
        long sizeThreshold = getTargetSize(sizeEstimate);
        boolean forkRight = false;
        @SuppressWarnings("unchecked") K task = (K) this;
        while (sizeEstimate > sizeThreshold && (ls = rs.trySplit()) != null) {
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
        task.setLocalResult(task.doLeaf());
        task.tryComplete();
    }

    @Override
    public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {
        spliterator = null;
        leftChild = rightChild = null;
    }

    protected boolean isLeftmostNode() {
        @SuppressWarnings("unchecked")
        K node = (K) this;
        while (node != null) {
            K parent = node.getParent();
            if (parent != null && parent.leftChild != node)
                return false;
            node = parent;
        }
        return true;
    }
}
