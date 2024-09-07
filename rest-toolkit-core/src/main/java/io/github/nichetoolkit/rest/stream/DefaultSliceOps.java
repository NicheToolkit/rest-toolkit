package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;
import java.util.function.IntFunction;

final class DefaultSliceOps {

    private DefaultSliceOps() { }

    private static long calcSize(long size, long skip, long limit) {
        return size >= 0 ? Math.max(-1, Math.min(size - skip, limit)) : -1;
    }

    private static long calcSliceFence(long skip, long limit) {
        long sliceFence = limit >= 0 ? skip + limit : Long.MAX_VALUE;
        return (sliceFence >= 0) ? sliceFence : Long.MAX_VALUE;
    }

    private static <P_IN> DefaultSpliterator<P_IN> sliceSpliterator(DefaultStreamShape shape,
                                                                    DefaultSpliterator<P_IN> s,
                                                             long skip, long limit) throws RestException {
        assert s.hasCharacteristics(DefaultSpliterator.SUBSIZED);
        long sliceFence = calcSliceFence(skip, limit);
        if (Objects.requireNonNull(shape) == DefaultStreamShape.REFERENCE) {
            return new DefaultStreamSpliterators
                    .SliceSpliterator.OfRef<>(s, skip, sliceFence);
        }
        throw new IllegalStateException("Unknown shape " + shape);
    }

    @SuppressWarnings("unchecked")
    private static <T> IntFunction<T[]> castingArray() {
        return size -> (T[]) new Object[size];
    }

    public static <T> RestStream<T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream,
                                        long skip, long limit) throws RestException {
        if (skip < 0)
            throw new IllegalArgumentException("Skip must be non-negative: " + skip);

        return new DefaultReferencePipeline.StatefulOp<T, T>(upstream, DefaultStreamShape.REFERENCE,
                                                      flags(limit)) {
            DefaultSpliterator<T> unorderedSkipLimitSpliterator(DefaultSpliterator<T> s,
                                                         long skip, long limit, long sizeIfKnown) {
                if (skip <= sizeIfKnown) {
                    limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                    skip = 0;
                }
                return new DefaultStreamSpliterators.UnorderedSliceSpliterator.OfRef<>(s, skip, limit);
            }

            @Override
            <P_IN> DefaultSpliterator<T> opEvaluateParallelLazy(DefaultPipelineHelper<T> helper, DefaultSpliterator<P_IN> spliterator) throws RestException {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(DefaultSpliterator.SUBSIZED)) {
                    return new DefaultStreamSpliterators.SliceSpliterator.OfRef<>(
                            helper.wrapSpliterator(spliterator),
                            skip,
                            calcSliceFence(skip, limit));
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator(
                            helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                }
                else {
                    // @@@ OOMEs will occur for LongStream.longs().filter(i -> true).limit(n)
                    //     regardless of the value of n
                    //     Need to adjust the target size of splitting for the
                    //     SliceTask from say (size / k) to say min(size / k, 1 << 14)
                    //     This will limit the size of the buffers created at the leaf nodes
                    //     cancellation will be more aggressive cancelling later tasks
                    //     if the target slice size has been reached from a given task,
                    //     cancellation should also clear local results if any
                    return new SliceTask<>(this, helper, spliterator, castingArray(), skip, limit).
                            invoke().spliterator();
                }
            }

            @Override
            <P_IN> DefaultNode<T> opEvaluateParallel(DefaultPipelineHelper<T> helper,
                                                     DefaultSpliterator<P_IN> spliterator,
                                              IntFunction<T[]> generator) throws RestException {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(DefaultSpliterator.SUBSIZED)) {
                    // Because the pipeline is SIZED the slice spliterator
                    // can be created from the source, this requires matching
                    // to shape of the source, and is potentially more efficient
                    // than creating the slice spliterator from the pipeline
                    // wrapping spliterator
                    DefaultSpliterator<P_IN> s = sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit);
                    return DefaultNodes.collect(helper, s, true, generator);
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    DefaultSpliterator<T> s =  unorderedSkipLimitSpliterator(
                            helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                    return DefaultNodes.collect(this, s, true, generator);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, generator, skip, limit).
                            invoke();
                }
            }

            @Override
            DefaultSink<T> opWrapSink(int flags, DefaultSink<T> sink) throws RestException {
                return new DefaultSink.ChainedReference<T, T>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Override
                    public void begin(long size) throws RestException {
                        downstream.begin(calcSize(size, skip, m));
                    }

                    @Override
                    public void actuate(T t) throws RestException {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstream.actuate(t);
                            }
                        }
                        else {
                            n--;
                        }
                    }

                    @Override
                    public boolean cancellationRequested() {
                        return m == 0 || downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    private static int flags(long limit) {
        return DefaultStreamOpFlag.NOT_SIZED | ((limit != -1) ? DefaultStreamOpFlag.IS_SHORT_CIRCUIT : 0);
    }

    private static final class SliceTask<P_IN, P_OUT>
            extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, DefaultNode<P_OUT>, SliceTask<P_IN, P_OUT>> {
        private final DefaultAbstractPipeline<P_OUT, P_OUT, ?> op;
        private final IntFunction<P_OUT[]> generator;
        private final long targetOffset, targetSize;
        private long thisDefaultNodeSize;

        private volatile boolean completed;

        SliceTask(DefaultAbstractPipeline<P_OUT, P_OUT, ?> op,
                  DefaultPipelineHelper<P_OUT> helper,
                  DefaultSpliterator<P_IN> spliterator,
                  IntFunction<P_OUT[]> generator,
                  long offset, long size) {
            super(helper, spliterator);
            this.op = op;
            this.generator = generator;
            this.targetOffset = offset;
            this.targetSize = size;
        }

        SliceTask(SliceTask<P_IN, P_OUT> parent, DefaultSpliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
            this.generator = parent.generator;
            this.targetOffset = parent.targetOffset;
            this.targetSize = parent.targetSize;
        }

        @Override
        protected SliceTask<P_IN, P_OUT> makeChild(DefaultSpliterator<P_IN> spliterator) {
            return new SliceTask<>(this, spliterator);
        }

        @Override
        protected DefaultNode<P_OUT> getEmptyResult() {
            return DefaultNodes.emptyDefaultNode(op.getOutputShape());
        }

        @Override
        protected DefaultNode<P_OUT> doLeaf() throws RestException {
            if (isRoot()) {
                long sizeIfKnown = DefaultStreamOpFlag.SIZED.isPreserved(op.sourceOrOpFlags)
                                   ? op.exactOutputSizeIfKnown(spliterator)
                                   : -1;
                final DefaultNode.Builder<P_OUT> nb = op.makeNodeBuilder(sizeIfKnown, generator);
                DefaultSink<P_OUT> opDefaultSink = op.opWrapSink(helper.getStreamAndOpFlags(), nb);
                helper.copyIntoWithCancel(helper.wrapSink(opDefaultSink), spliterator);
                // There is no need to truncate since the op performs the
                // skipping and limiting of elements
                return nb.build();
            }
            else {
                DefaultNode<P_OUT> node = helper.wrapAndCopyInto(helper.makeNodeBuilder(-1, generator),
                                                          spliterator).build();
                thisDefaultNodeSize = node.count();
                completed = true;
                spliterator = null;
                return node;
            }
        }

        @Override
        public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {
            if (!isLeaf()) {
                DefaultNode<P_OUT> result;
                thisDefaultNodeSize = leftChild.thisDefaultNodeSize + rightChild.thisDefaultNodeSize;
                if (canceled) {
                    thisDefaultNodeSize = 0;
                    result = getEmptyResult();
                }
                else if (thisDefaultNodeSize == 0)
                    result = getEmptyResult();
                else if (leftChild.thisDefaultNodeSize == 0)
                    result = rightChild.getLocalResult();
                else {
                    result = DefaultNodes.conc(op.getOutputShape(),
                                        leftChild.getLocalResult(), rightChild.getLocalResult());
                }
                setLocalResult(isRoot() ? doTruncate(result) : result);
                completed = true;
            }
            if (targetSize >= 0
                && !isRoot()
                && isLeftCompleted(targetOffset + targetSize))
                    cancelLaterNodes();

            super.onCompletion(caller);
        }

        @Override
        protected void cancel() {
            super.cancel();
            if (completed)
                setLocalResult(getEmptyResult());
        }

        private DefaultNode<P_OUT> doTruncate(DefaultNode<P_OUT> input) throws RestException {
            long to = targetSize >= 0 ? Math.min(input.count(), targetOffset + targetSize) : thisDefaultNodeSize;
            return input.truncate(targetOffset, to, generator);
        }

        private boolean isLeftCompleted(long target) {
            long size = completed ? thisDefaultNodeSize : completedSize(target);
            if (size >= target)
                return true;
            for (SliceTask<P_IN, P_OUT> parent = getParent(), node = this;
                 parent != null;
                 node = parent, parent = parent.getParent()) {
                if (node == parent.rightChild) {
                    SliceTask<P_IN, P_OUT> left = parent.leftChild;
                    if (left != null) {
                        size += left.completedSize(target);
                        if (size >= target)
                            return true;
                    }
                }
            }
            return false;
        }

        private long completedSize(long target) {
            if (completed)
                return thisDefaultNodeSize;
            else {
                SliceTask<P_IN, P_OUT> left = leftChild;
                SliceTask<P_IN, P_OUT> right = rightChild;
                if (left == null || right == null) {
                    // must be completed
                    return thisDefaultNodeSize;
                }
                else {
                    long leftSize = left.completedSize(target);
                    return (leftSize >= target) ? leftSize : leftSize + right.completedSize(target);
                }
            }
        }
    }
}