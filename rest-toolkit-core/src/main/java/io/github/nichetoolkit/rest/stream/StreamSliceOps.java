package io.github.nichetoolkit.rest.stream;

import java.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.function.IntFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Factory for instances of a short-circuiting stateful intermediate operations
 * that produce subsequences of their input stream.
 *
 * @since 1.8
 */
final class StreamSliceOps {

    // No instances
    private StreamSliceOps() { }

    /**
     * Calculates the sliced size given the current size, number of elements
     * skip, and the number of elements to limit.
     *
     * @param size the current size
     * @param skip the number of elements to skip, assumed to be >= 0
     * @param limit the number of elements to limit, assumed to be >= 0, with
     *        a value of {@code Long.MAX_VALUE} if there is no limit
     * @return the sliced size
     */
    private static long calcSize(long size, long skip, long limit) {
        return size >= 0 ? Math.max(-1, Math.min(size - skip, limit)) : -1;
    }

    /**
     * Calculates the slice fence, which is one past the index of the slice
     * range
     * @param skip the number of elements to skip, assumed to be >= 0
     * @param limit the number of elements to limit, assumed to be >= 0, with
     *        a value of {@code Long.MAX_VALUE} if there is no limit
     * @return the slice fence.
     */
    private static long calcSliceFence(long skip, long limit) {
        long sliceFence = limit >= 0 ? skip + limit : Long.MAX_VALUE;
        // Check for overflow
        return (sliceFence >= 0) ? sliceFence : Long.MAX_VALUE;
    }

    /**
     * Creates a slice spliterator given a stream shape governing the
     * spliterator type.  Requires that the underlying Spliterator
     * be SUBSIZED.
     */
    @SuppressWarnings("unchecked")
    private static <P_IN> Spliterator<P_IN> sliceSpliterator(DefaultStreamShape shape,
                                                             Spliterator<P_IN> s,
                                                             long skip, long limit) {
        assert s.hasCharacteristics(Spliterator.SUBSIZED);
        long sliceFence = calcSliceFence(skip, limit);
        switch (shape) {
            case REFERENCE:
                return new DefaultStreamSpliterators
                        .SliceSpliterator.OfRef<>(s, skip, sliceFence);
            case INT_VALUE:
                return (Spliterator<P_IN>) new DefaultStreamSpliterators
                        .SliceSpliterator.OfInt((Spliterator.OfInt) s, skip, sliceFence);
            case LONG_VALUE:
                return (Spliterator<P_IN>) new DefaultStreamSpliterators
                        .SliceSpliterator.OfLong((Spliterator.OfLong) s, skip, sliceFence);
            case DOUBLE_VALUE:
                return (Spliterator<P_IN>) new DefaultStreamSpliterators
                        .SliceSpliterator.OfDouble((Spliterator.OfDouble) s, skip, sliceFence);
            default:
                throw new IllegalStateException("Unknown shape " + shape);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> IntFunction<T[]> castingArray() {
        return size -> (T[]) new Object[size];
    }

    /**
     * Appends a "slice" operation to the provided stream.  The slice operation
     * may be skip-only, limit-only, or skip-and-limit.
     *
     * @param <T> the type of both input and output elements
     * @param upstream a reference stream with element type T
     * @param skip the number of elements to skip.  Must be >= 0.
     * @param limit the maximum size of the resulting stream, or -1 if no limit
     *        is to be imposed
     */
    public static <T> Stream<T> makeRef(StreamAbstractPipeline<?, T, ?> upstream,
                                        long skip, long limit) {
        if (skip < 0)
            throw new IllegalArgumentException("Skip must be non-negative: " + skip);

        return new StreamReferencePipeline.StatefulOp<T, T>(upstream, DefaultStreamShape.REFERENCE,
                                                      flags(limit)) {
            Spliterator<T> unorderedSkipLimitSpliterator(Spliterator<T> s,
                                                         long skip, long limit, long sizeIfKnown) {
                if (skip <= sizeIfKnown) {
                    // Use just the limit if the number of elements
                    // to skip is <= the known pipeline size
                    limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                    skip = 0;
                }
                return new DefaultStreamSpliterators.UnorderedSliceSpliterator.OfRef<>(s, skip, limit);
            }

            @Override
            <P_IN> Spliterator<T> opEvaluateParallelLazy(DefaultPipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
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
                                              Spliterator<P_IN> spliterator,
                                              IntFunction<T[]> generator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
                    // Because the pipeline is SIZED the slice spliterator
                    // can be created from the source, this requires matching
                    // to shape of the source, and is potentially more efficient
                    // than creating the slice spliterator from the pipeline
                    // wrapping spliterator
                    Spliterator<P_IN> s = sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit);
                    return DefaultNodes.collect(helper, s, true, generator);
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    Spliterator<T> s =  unorderedSkipLimitSpliterator(
                            helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                    // Collect using this pipeline, which is empty and therefore
                    // can be used with the pipeline wrapping spliterator
                    // Note that we cannot create a slice spliterator from
                    // the source spliterator if the pipeline is not SIZED
                    return DefaultNodes.collect(this, s, true, generator);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, generator, skip, limit).
                            invoke();
                }
            }

            @Override
            DefaultSink<T> opWrapSink(int flags, DefaultSink<T> sink) {
                return new DefaultSink.ChainedReference<T, T>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Override
                    public void begin(long size) {
                        downstream.begin(calcSize(size, skip, m));
                    }

                    @Override
                    public void accept(T t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstream.accept(t);
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

    /**
     * Appends a "slice" operation to the provided IntStream.  The slice
     * operation may be skip-only, limit-only, or skip-and-limit.
     *
     * @param upstream An IntStream
     * @param skip The number of elements to skip.  Must be >= 0.
     * @param limit The maximum size of the resulting stream, or -1 if no limit
     *        is to be imposed
     */
    public static IntStream makeInt(StreamAbstractPipeline<?, Integer, ?> upstream,
                                    long skip, long limit) {
        if (skip < 0)
            throw new IllegalArgumentException("Skip must be non-negative: " + skip);

        return new StreamIntPipeline.StatefulOp<Integer>(upstream, DefaultStreamShape.INT_VALUE,
                                                   flags(limit)) {
            Spliterator.OfInt unorderedSkipLimitSpliterator(
                    Spliterator.OfInt s, long skip, long limit, long sizeIfKnown) {
                if (skip <= sizeIfKnown) {
                    // Use just the limit if the number of elements
                    // to skip is <= the known pipeline size
                    limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                    skip = 0;
                }
                return new DefaultStreamSpliterators.UnorderedSliceSpliterator.OfInt(s, skip, limit);
            }

            @Override
            <P_IN> Spliterator<Integer> opEvaluateParallelLazy(DefaultPipelineHelper<Integer> helper,
                                                               Spliterator<P_IN> spliterator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
                    return new DefaultStreamSpliterators.SliceSpliterator.OfInt(
                            (Spliterator.OfInt) helper.wrapSpliterator(spliterator),
                            skip,
                            calcSliceFence(skip, limit));
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator(
                            (Spliterator.OfInt) helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, Integer[]::new, skip, limit).
                            invoke().spliterator();
                }
            }

            @Override
            <P_IN> DefaultNode<Integer> opEvaluateParallel(DefaultPipelineHelper<Integer> helper,
                                                    Spliterator<P_IN> spliterator,
                                                    IntFunction<Integer[]> generator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
                    // Because the pipeline is SIZED the slice spliterator
                    // can be created from the source, this requires matching
                    // to shape of the source, and is potentially more efficient
                    // than creating the slice spliterator from the pipeline
                    // wrapping spliterator
                    Spliterator<P_IN> s = sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit);
                    return DefaultNodes.collectInt(helper, s, true);
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    Spliterator.OfInt s =  unorderedSkipLimitSpliterator(
                            (Spliterator.OfInt) helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                    // Collect using this pipeline, which is empty and therefore
                    // can be used with the pipeline wrapping spliterator
                    // Note that we cannot create a slice spliterator from
                    // the source spliterator if the pipeline is not SIZED
                    return DefaultNodes.collectInt(this, s, true);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, generator, skip, limit).
                            invoke();
                }
            }

            @Override
            DefaultSink<Integer> opWrapSink(int flags, DefaultSink<Integer> sink) {
                return new DefaultSink.ChainedInt<Integer>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Override
                    public void begin(long size) {
                        downstream.begin(calcSize(size, skip, m));
                    }

                    @Override
                    public void accept(int t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstream.accept(t);
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

    /**
     * Appends a "slice" operation to the provided LongStream.  The slice
     * operation may be skip-only, limit-only, or skip-and-limit.
     *
     * @param upstream A LongStream
     * @param skip The number of elements to skip.  Must be >= 0.
     * @param limit The maximum size of the resulting stream, or -1 if no limit
     *        is to be imposed
     */
    public static LongStream makeLong(StreamAbstractPipeline<?, Long, ?> upstream,
                                      long skip, long limit) {
        if (skip < 0)
            throw new IllegalArgumentException("Skip must be non-negative: " + skip);

        return new StreamLongPipeline.StatefulOp<Long>(upstream, DefaultStreamShape.LONG_VALUE,
                                                 flags(limit)) {
            Spliterator.OfLong unorderedSkipLimitSpliterator(
                    Spliterator.OfLong s, long skip, long limit, long sizeIfKnown) {
                if (skip <= sizeIfKnown) {
                    // Use just the limit if the number of elements
                    // to skip is <= the known pipeline size
                    limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                    skip = 0;
                }
                return new DefaultStreamSpliterators.UnorderedSliceSpliterator.OfLong(s, skip, limit);
            }

            @Override
            <P_IN> Spliterator<Long> opEvaluateParallelLazy(DefaultPipelineHelper<Long> helper,
                                                            Spliterator<P_IN> spliterator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
                    return new DefaultStreamSpliterators.SliceSpliterator.OfLong(
                            (Spliterator.OfLong) helper.wrapSpliterator(spliterator),
                            skip,
                            calcSliceFence(skip, limit));
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator(
                            (Spliterator.OfLong) helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, Long[]::new, skip, limit).
                            invoke().spliterator();
                }
            }

            @Override
            <P_IN> DefaultNode<Long> opEvaluateParallel(DefaultPipelineHelper<Long> helper,
                                                 Spliterator<P_IN> spliterator,
                                                 IntFunction<Long[]> generator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
                    // Because the pipeline is SIZED the slice spliterator
                    // can be created from the source, this requires matching
                    // to shape of the source, and is potentially more efficient
                    // than creating the slice spliterator from the pipeline
                    // wrapping spliterator
                    Spliterator<P_IN> s = sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit);
                    return DefaultNodes.collectLong(helper, s, true);
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    Spliterator.OfLong s =  unorderedSkipLimitSpliterator(
                            (Spliterator.OfLong) helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                    // Collect using this pipeline, which is empty and therefore
                    // can be used with the pipeline wrapping spliterator
                    // Note that we cannot create a slice spliterator from
                    // the source spliterator if the pipeline is not SIZED
                    return DefaultNodes.collectLong(this, s, true);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, generator, skip, limit).
                            invoke();
                }
            }

            @Override
            DefaultSink<Long> opWrapSink(int flags, DefaultSink<Long> sink) {
                return new DefaultSink.ChainedLong<Long>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Override
                    public void begin(long size) {
                        downstream.begin(calcSize(size, skip, m));
                    }

                    @Override
                    public void accept(long t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstream.accept(t);
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

    /**
     * Appends a "slice" operation to the provided DoubleStream.  The slice
     * operation may be skip-only, limit-only, or skip-and-limit.
     *
     * @param upstream A DoubleStream
     * @param skip The number of elements to skip.  Must be >= 0.
     * @param limit The maximum size of the resulting stream, or -1 if no limit
     *        is to be imposed
     */
    public static DoubleStream makeDouble(StreamAbstractPipeline<?, Double, ?> upstream,
                                          long skip, long limit) {
        if (skip < 0)
            throw new IllegalArgumentException("Skip must be non-negative: " + skip);

        return new StreamDoublePipeline.StatefulOp<Double>(upstream, DefaultStreamShape.DOUBLE_VALUE,
                                                     flags(limit)) {
            Spliterator.OfDouble unorderedSkipLimitSpliterator(
                    Spliterator.OfDouble s, long skip, long limit, long sizeIfKnown) {
                if (skip <= sizeIfKnown) {
                    // Use just the limit if the number of elements
                    // to skip is <= the known pipeline size
                    limit = limit >= 0 ? Math.min(limit, sizeIfKnown - skip) : sizeIfKnown - skip;
                    skip = 0;
                }
                return new DefaultStreamSpliterators.UnorderedSliceSpliterator.OfDouble(s, skip, limit);
            }

            @Override
            <P_IN> Spliterator<Double> opEvaluateParallelLazy(DefaultPipelineHelper<Double> helper,
                                                              Spliterator<P_IN> spliterator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
                    return new DefaultStreamSpliterators.SliceSpliterator.OfDouble(
                            (Spliterator.OfDouble) helper.wrapSpliterator(spliterator),
                            skip,
                            calcSliceFence(skip, limit));
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator(
                            (Spliterator.OfDouble) helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, Double[]::new, skip, limit).
                            invoke().spliterator();
                }
            }

            @Override
            <P_IN> DefaultNode<Double> opEvaluateParallel(DefaultPipelineHelper<Double> helper,
                                                   Spliterator<P_IN> spliterator,
                                                   IntFunction<Double[]> generator) {
                long size = helper.exactOutputSizeIfKnown(spliterator);
                if (size > 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
                    // Because the pipeline is SIZED the slice spliterator
                    // can be created from the source, this requires matching
                    // to shape of the source, and is potentially more efficient
                    // than creating the slice spliterator from the pipeline
                    // wrapping spliterator
                    Spliterator<P_IN> s = sliceSpliterator(helper.getSourceShape(), spliterator, skip, limit);
                    return DefaultNodes.collectDouble(helper, s, true);
                } else if (!DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    Spliterator.OfDouble s =  unorderedSkipLimitSpliterator(
                            (Spliterator.OfDouble) helper.wrapSpliterator(spliterator),
                            skip, limit, size);
                    // Collect using this pipeline, which is empty and therefore
                    // can be used with the pipeline wrapping spliterator
                    // Note that we cannot create a slice spliterator from
                    // the source spliterator if the pipeline is not SIZED
                    return DefaultNodes.collectDouble(this, s, true);
                }
                else {
                    return new SliceTask<>(this, helper, spliterator, generator, skip, limit).
                            invoke();
                }
            }

            @Override
            DefaultSink<Double> opWrapSink(int flags, DefaultSink<Double> sink) {
                return new DefaultSink.ChainedDouble<Double>(sink) {
                    long n = skip;
                    long m = limit >= 0 ? limit : Long.MAX_VALUE;

                    @Override
                    public void begin(long size) {
                        downstream.begin(calcSize(size, skip, m));
                    }

                    @Override
                    public void accept(double t) {
                        if (n == 0) {
                            if (m > 0) {
                                m--;
                                downstream.accept(t);
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

    /**
     * {@code ForkJoinTask} implementing slice computation.
     *
     * @param <P_IN> Input element type to the stream pipeline
     * @param <P_OUT> Output element type from the stream pipeline
     */
    private static final class SliceTask<P_IN, P_OUT>
            extends DefaultAbstractShortCircuitTask<P_IN, P_OUT, DefaultNode<P_OUT>, SliceTask<P_IN, P_OUT>> {
        private final StreamAbstractPipeline<P_OUT, P_OUT, ?> op;
        private final IntFunction<P_OUT[]> generator;
        private final long targetOffset, targetSize;
        private long thisDefaultNodeSize;

        private volatile boolean completed;

        SliceTask(StreamAbstractPipeline<P_OUT, P_OUT, ?> op,
                  DefaultPipelineHelper<P_OUT> helper,
                  Spliterator<P_IN> spliterator,
                  IntFunction<P_OUT[]> generator,
                  long offset, long size) {
            super(helper, spliterator);
            this.op = op;
            this.generator = generator;
            this.targetOffset = offset;
            this.targetSize = size;
        }

        SliceTask(SliceTask<P_IN, P_OUT> parent, Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            this.op = parent.op;
            this.generator = parent.generator;
            this.targetOffset = parent.targetOffset;
            this.targetSize = parent.targetSize;
        }

        @Override
        protected SliceTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
            return new SliceTask<>(this, spliterator);
        }

        @Override
        protected DefaultNode<P_OUT> getEmptyResult() {
            return DefaultNodes.emptyDefaultNode(op.getOutputShape());
        }

        @Override
        protected DefaultNode<P_OUT> doLeaf() {
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
        public void onCompletion(CountedCompleter<?> caller) {
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

        private DefaultNode<P_OUT> doTruncate(DefaultNode<P_OUT> input) {
            long to = targetSize >= 0 ? Math.min(input.count(), targetOffset + targetSize) : thisDefaultNodeSize;
            return input.truncate(targetOffset, to, generator);
        }

        /**
         * Determine if the number of completed elements in this node and nodes
         * to the left of this node is greater than or equal to the target size.
         *
         * @param target the target size
         * @return true if the number of elements is greater than or equal to
         *         the target size, otherwise false.
         */
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
            return size >= target;
        }

        /**
         * Compute the number of completed elements in this node.
         * <p>
         * Computation terminates if all nodes have been processed or the
         * number of completed elements is greater than or equal to the target
         * size.
         *
         * @param target the target size
         * @return the number of completed elements
         */
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
