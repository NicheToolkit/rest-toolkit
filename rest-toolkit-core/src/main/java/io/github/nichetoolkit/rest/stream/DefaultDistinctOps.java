package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntFunction;


final class DefaultDistinctOps {

    private DefaultDistinctOps() {
    }

    static <T> DefaultReferencePipeline<T, T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream) {
        return new DefaultReferencePipeline.StatefulOp<T, T>(upstream, DefaultStreamShape.REFERENCE,
                DefaultStreamOpFlag.IS_DISTINCT | DefaultStreamOpFlag.NOT_SIZED) {

            <P_IN> DefaultNode<T> reduce(DefaultPipelineHelper<T> helper, DefaultSpliterator<P_IN> spliterator) throws RestException {
                DefaultTerminalOp<T, LinkedHashSet<T>> reduceOp
                        = DefaultReduceOps.<T, LinkedHashSet<T>>makeRef(LinkedHashSet::new, LinkedHashSet::add,
                        LinkedHashSet::addAll);
                return DefaultNodes.node(reduceOp.evaluateParallel(helper, spliterator));
            }

            @Override
            <P_IN> DefaultNode<T> opEvaluateParallel(DefaultPipelineHelper<T> helper,
                                                     DefaultSpliterator<P_IN> spliterator,
                                                     IntFunction<T[]> generator) throws RestException {
                if (DefaultStreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
                    // No-op
                    return helper.evaluate(spliterator, false, generator);
                } else if (DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return reduce(helper, spliterator);
                } else {
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

                    Set<T> keys = map.keySet();
                    if (seenNull.get()) {
                        keys = new HashSet<>(keys);
                        keys.add(null);
                    }
                    return DefaultNodes.node(keys);
                }
            }

            @Override
            <P_IN> DefaultSpliterator<T> opEvaluateParallelLazy(DefaultPipelineHelper<T> helper, DefaultSpliterator<P_IN> spliterator) throws RestException {
                if (DefaultStreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
                    // No-op
                    return helper.wrapSpliterator(spliterator);
                } else if (DefaultStreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    // Not lazy, barrier required to preserve order
                    return reduce(helper, spliterator).spliterator();
                } else {
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
                        public void begin(long size) throws RestException {
                            seenNull = false;
                            lastSeen = null;
                            downstream.begin(-1);
                        }

                        @Override
                        public void end() throws RestException {
                            seenNull = false;
                            lastSeen = null;
                            downstream.end();
                        }

                        @Override
                        public void actuate(T t) throws RestException {
                            if (t == null) {
                                if (!seenNull) {
                                    seenNull = true;
                                    downstream.actuate(lastSeen = null);
                                }
                            } else if (!t.equals(lastSeen)) {
                                downstream.actuate(lastSeen = t);
                            }
                        }
                    };
                } else {
                    return new DefaultSink.ChainedReference<T, T>(sink) {
                        Set<T> seen;

                        @Override
                        public void begin(long size) throws RestException {
                            seen = new HashSet<>();
                            downstream.begin(-1);
                        }

                        @Override
                        public void end() throws RestException {
                            seen = null;
                            downstream.end();
                        }

                        @Override
                        public void actuate(T t) throws RestException {
                            if (!seen.contains(t)) {
                                seen.add(t);
                                downstream.actuate(t);
                            }
                        }
                    };
                }
            }
        };
    }
}
