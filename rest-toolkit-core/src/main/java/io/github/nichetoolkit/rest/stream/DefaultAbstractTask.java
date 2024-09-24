package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * <code>DefaultAbstractTask</code>
 * <p>The type default abstract task class.</p>
 * @param <P_IN>  {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <P_OUT> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <R>     {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <K>     {@link io.github.nichetoolkit.rest.stream.DefaultAbstractTask} <p>the generic parameter is <code>DefaultAbstractTask</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.stream.DefaultCountedCompleter
 * @since Jdk1.8
 */
abstract class DefaultAbstractTask<P_IN, P_OUT, R,
        K extends DefaultAbstractTask<P_IN, P_OUT, R, K>>
        extends DefaultCountedCompleter<R> {

    /**
     * <code>LEAF_TARGET</code>
     * <p>the constant <code>LEAF_TARGET</code> field.</p>
     */
    private static final int LEAF_TARGET = ForkJoinPool.getCommonPoolParallelism() << 2;

    /**
     * <code>helper</code>
     * {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>the <code>helper</code> field.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     */
    protected final DefaultPipelineHelper<P_OUT> helper;

    /**
     * <code>spliterator</code>
     * {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the <code>spliterator</code> field.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    protected DefaultSpliterator<P_IN> spliterator;

    /**
     * <code>targetSize</code>
     * <p>the <code>targetSize</code> field.</p>
     */
    protected long targetSize;

    /**
     * <code>leftChild</code>
     * <p>the <code>leftChild</code> field.</p>
     */
    protected K leftChild;

    /**
     * <code>rightChild</code>
     * <p>the <code>rightChild</code> field.</p>
     */
    protected K rightChild;

    /**
     * <code>localResult</code>
     * <p>the <code>localResult</code> field.</p>
     */
    private R localResult;

    /**
     * <code>DefaultAbstractTask</code>
     * Instantiates a new default abstract task.
     * @param helper      {@link io.github.nichetoolkit.rest.stream.DefaultPipelineHelper} <p>the helper parameter is <code>DefaultPipelineHelper</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultPipelineHelper
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    protected DefaultAbstractTask(DefaultPipelineHelper<P_OUT> helper,
                                  DefaultSpliterator<P_IN> spliterator) {
        super(null);
        this.helper = helper;
        this.spliterator = spliterator;
        this.targetSize = 0L;
    }

    /**
     * <code>DefaultAbstractTask</code>
     * Instantiates a new default abstract task.
     * @param parent      K <p>the parent parameter is <code>K</code> type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    protected DefaultAbstractTask(K parent,
                                  DefaultSpliterator<P_IN> spliterator) {
        super(parent);
        this.spliterator = spliterator;
        this.helper = parent.helper;
        this.targetSize = parent.targetSize;
    }

    /**
     * <code>getLeafTarget</code>
     * <p>the leaf target getter method.</p>
     * @return int <p>the leaf target return object is <code>int</code> type.</p>
     */
    public static int getLeafTarget() {
        Thread t = Thread.currentThread();
        if (t instanceof ForkJoinWorkerThread) {
            return ((ForkJoinWorkerThread) t).getPool().getParallelism() << 2;
        } else {
            return LEAF_TARGET;
        }
    }

    /**
     * <code>makeChild</code>
     * <p>the child method.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return K <p>the child return object is <code>K</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     */
    protected abstract K makeChild(DefaultSpliterator<P_IN> spliterator);

    /**
     * <code>doLeaf</code>
     * <p>the leaf method.</p>
     * @return R <p>the leaf return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    protected abstract R doLeaf() throws RestException;

    /**
     * <code>suggestTargetSize</code>
     * <p>the target size method.</p>
     * @param sizeEstimate long <p>the size estimate parameter is <code>long</code> type.</p>
     * @return long <p>the target size return object is <code>long</code> type.</p>
     */
    public static long suggestTargetSize(long sizeEstimate) {
        long est = sizeEstimate / getLeafTarget();
        return est > 0L ? est : 1L;
    }

    /**
     * <code>getTargetSize</code>
     * <p>the target size getter method.</p>
     * @param sizeEstimate long <p>the size estimate parameter is <code>long</code> type.</p>
     * @return long <p>the target size return object is <code>long</code> type.</p>
     */
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

    /**
     * <code>getLocalResult</code>
     * <p>the local result getter method.</p>
     * @return R <p>the local result return object is <code>R</code> type.</p>
     */
    protected R getLocalResult() {
        return localResult;
    }

    /**
     * <code>setLocalResult</code>
     * <p>the local result setter method.</p>
     * @param localResult R <p>the local result parameter is <code>R</code> type.</p>
     */
    protected void setLocalResult(R localResult) {
        this.localResult = localResult;
    }

    /**
     * <code>isLeaf</code>
     * <p>the leaf method.</p>
     * @return boolean <p>the leaf return object is <code>boolean</code> type.</p>
     */
    protected boolean isLeaf() {
        return leftChild != null;
    }

    /**
     * <code>isRoot</code>
     * <p>the root method.</p>
     * @return boolean <p>the root return object is <code>boolean</code> type.</p>
     */
    protected boolean isRoot() {
        return getParent() == null;
    }

    /**
     * <code>getParent</code>
     * <p>the parent getter method.</p>
     * @return K <p>the parent return object is <code>K</code> type.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    protected K getParent() {
        return (K) getCompleter();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void computes() throws RestException {
        DefaultSpliterator<P_IN> rs = spliterator, ls;
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

    /**
     * <code>isLeftmostNode</code>
     * <p>the leftmost node method.</p>
     * @return boolean <p>the leftmost node return object is <code>boolean</code> type.</p>
     */
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
