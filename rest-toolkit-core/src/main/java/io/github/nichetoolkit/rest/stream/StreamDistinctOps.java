package io.github.nichetoolkit.rest.stream;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntFunction;

/**
 * Factory methods for transforming streams into duplicate-free streams, using
 * {@link Object#equals(Object)} to determine equality.
 *
 * @since 1.8
 */
final class StreamDistinctOps {

    private StreamDistinctOps() { }

    /**
     * Appends a "distinct" operation to the provided stream, and returns the
     * new stream.
     *
     * @param <T> the type of both input and output elements
     * @param upstream a reference stream with element type T
     * @return the new stream
     */
    static <T> StreamReferencePipeline<T, T> makeRef(StreamAbstractPipeline<?, T, ?> upstream) {
        return new StreamReferencePipeline.StatefulOp<T, T>(upstream, DefaultStreamShape.REFERENCE,
                                                      DefaultStreamOpFlag.IS_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {

            <P_IN> DefaultNode<T> reduce(DefaultPipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
                // If the stream is SORTED then it should also be ORDERED so the following will also
                // preserve the sort order
                DefaultTerminalOp<T, LinkedHashSet<T>> reduceOp
                        = DefaultReduceOps.<T, LinkedHashSet<T>>makeRef(LinkedHashSet::new, LinkedHashSet::add,
                                                                 LinkedHashSet::addAll);
                return DefaultNodes.node(reduceOp.evaluateParallel(helper, spliterator));
            }

            @Override
            <P_IN> DefaultNode<T> opEvaluateParallel(DefaultPipelineHelper<T> helper,
                                              Spliterator<P_IN> spliterator,
                                              IntFunction<T[]> generator) {
                if (DefaultStreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
                    // No-op
                    return helper.evaluate(spliterator, false, generator);
                }
                else if (DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return reduce(helper, spliterator);
                }
                else {
                    // Holder of null state since ConcurrentHashMap does not support null values
                    AtomicBoolean seenNull = new AtomicBoolean(false);
                    ConcurrentHashMap<T, Boolean> map = new ConcurrentHashMap<>();
                    DefaultTerminalOp<T, Void> forEachOp = DefaultForEachOps.makeRef(t -> {
                        if (t == null)
                            seenNull.set(true);
                        else
                            map.putIfAbsent(t, Boolean.TRUE);
                    }, false);
                    forEachOp.evaluateParallel(helper, spliterator);

                    // If null has been seen then copy the key set into a HashSet that supports null values
                    // and add null
                    Set<T> keys = map.keySet();
                    if (seenNull.get()) {
                        // TODO Implement a more efficient set-union view, rather than copying
                        keys = new HashSet<>(keys);
                        keys.add(null);
                    }
                    return DefaultNodes.node(keys);
                }
            }

            @Override
            <P_IN> Spliterator<T> opEvaluateParallelLazy(DefaultPipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
                if (DefaultStreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
                    // No-op
                    return helper.wrapSpliterator(spliterator);
                }
                else if (DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    // Not lazy, barrier required to preserve order
                    return reduce(helper, spliterator).spliterator();
                }
                else {
                    // Lazy
                    return new DefaultStreamSpliterators.DistinctSpliterator<>(helper.wrapSpliterator(spliterator));
                }
            }

            @Override
            DefaultSink<T> opWrapSink(int flags, DefaultSink<T> sink) {
                Objects.requireNonNull(sink);

                if (DefaultStreamOpFlag.DISTINCT.isKnown(flags)) {
                    return sink;
                } else if (DefaultStreamOpFlag.SORTED.isKnown(flags)) {
                    return new DefaultSink.ChainedReference<T, T>(sink) {
                        boolean seenNull;
                        T lastSeen;

                        @Override
                        public void begin(long size) {
                            seenNull = false;
                            lastSeen = null;
                            downstream.begin(-1);
                        }

                        @Override
                        public void end() {
                            seenNull = false;
                            lastSeen = null;
                            downstream.end();
                        }

                        @Override
                        public void accept(T t) {
                            if (t == null) {
                                if (!seenNull) {
                                    seenNull = true;
                                    downstream.accept(lastSeen = null);
                                }
                            } else if (!t.equals(lastSeen)) {
                                downstream.accept(lastSeen = t);
                            }
                        }
                    };
                } else {
                    return new DefaultSink.ChainedReference<T, T>(sink) {
                        Set<T> seen;

                        @Override
                        public void begin(long size) {
                            seen = new HashSet<>();
                            downstream.begin(-1);
                        }

                        @Override
                        public void end() {
                            seen = null;
                            downstream.end();
                        }

                        @Override
                        public void accept(T t) {
                            if (!seen.contains(t)) {
                                seen.add(t);
                                downstream.accept(t);
                            }
                        }
                    };
                }
            }
        };
    }
}
