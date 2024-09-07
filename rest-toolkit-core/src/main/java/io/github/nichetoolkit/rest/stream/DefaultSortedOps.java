package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.*;
import java.util.function.IntFunction;


final class DefaultSortedOps {

    private DefaultSortedOps() { }

    static <T> RestStream<T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream) {
        return new OfRef<>(upstream);
    }

    static <T> RestStream<T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream,
                                Comparator<? super T> comparator) {
        return new OfRef<>(upstream, comparator);
    }


    private static final class OfRef<T> extends  DefaultReferencePipeline.StatefulOp<T, T> {
        private final boolean isNaturalSort;
        private final Comparator<? super T> comparator;

        OfRef(DefaultAbstractPipeline<?, T, ?> upstream) {
            super(upstream, DefaultStreamShape.REFERENCE,
                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.IS_SORTED);
            this.isNaturalSort = true;
            @SuppressWarnings("unchecked")
            Comparator<? super T> comp = (Comparator<? super T>) Comparator.naturalOrder();
            this.comparator = comp;
        }

        OfRef(DefaultAbstractPipeline<?, T, ?> upstream, Comparator<? super T> comparator) {
            super(upstream, DefaultStreamShape.REFERENCE,
                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.NOT_SORTED);
            this.isNaturalSort = false;
            this.comparator = Objects.requireNonNull(comparator);
        }

        @Override
        public DefaultSink<T> opWrapSink(int flags, DefaultSink<T> sink) throws RestException {
            Objects.requireNonNull(sink);

            // If the input is already naturally sorted and this operation
            // also naturally sorted then this is a no-op
            if (DefaultStreamOpFlag.SORTED.isKnown(flags) && isNaturalSort)
                return sink;
            else if (DefaultStreamOpFlag.SIZED.isKnown(flags))
                return new SizedRefSortingSink<>(sink, comparator);
            else
                return new RefSortingSink<>(sink, comparator);
        }

        @Override
        public <P_IN> DefaultNode<T> opEvaluateParallel(DefaultPipelineHelper<T> helper,
                                                        DefaultSpliterator<P_IN> spliterator,
                                                 IntFunction<T[]> generator) throws RestException {
            // If the input is already naturally sorted and this operation
            // naturally sorts then collect the output
            if (DefaultStreamOpFlag.SORTED.isKnown(helper.getStreamAndOpFlags()) && isNaturalSort) {
                return helper.evaluate(spliterator, false, generator);
            }
            else {
                // @@@ Weak two-pass parallel implementation; parallel collect, parallel sort
                T[] flattenedData = helper.evaluate(spliterator, true, generator).asArray(generator);
                Arrays.parallelSort(flattenedData, comparator);
                return DefaultNodes.node(flattenedData);
            }
        }
    }

    private static abstract class AbstractRefSortingSink<T> extends DefaultSink.ChainedReference<T, T> {
        protected final Comparator<? super T> comparator;
        // @@@ could be a lazy final value, if/when support is added
        // true if cancellationRequested() has been called
        protected boolean cancellationRequestedCalled;

        AbstractRefSortingSink(DefaultSink<? super T> downstream, Comparator<? super T> comparator) {
            super(downstream);
            this.comparator = comparator;
        }

        @Override
        public final boolean cancellationRequested() {
            // If this method is called then an operation within the stream
            // pipeline is short-circuiting (see DefaultAbstractPipeline.copyInto).
            // Note that we cannot differentiate between an upstream or
            // downstream operation
            cancellationRequestedCalled = true;
            return false;
        }
    }

    private static final class SizedRefSortingSink<T> extends AbstractRefSortingSink<T> {
        private T[] array;
        private int offset;

        SizedRefSortingSink(DefaultSink<? super T> sink, Comparator<? super T> comparator) {
            super(sink, comparator);
        }

        @Override
        @SuppressWarnings("unchecked")
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            array = (T[]) new Object[(int) size];
        }

        @Override
        public void end() throws RestException {
            Arrays.sort(array, 0, offset, comparator);
            downstream.begin(offset);
            if (!cancellationRequestedCalled) {
                for (int i = 0; i < offset; i++)
                    downstream.actuate(array[i]);
            }
            else {
                for (int i = 0; i < offset && !downstream.cancellationRequested(); i++)
                    downstream.actuate(array[i]);
            }
            downstream.end();
            array = null;
        }

        @Override
        public void actuate(T t) {
            array[offset++] = t;
        }
    }

    private static final class RefSortingSink<T> extends AbstractRefSortingSink<T> {
        private ArrayList<T> list;

        RefSortingSink(DefaultSink<? super T> sink, Comparator<? super T> comparator) {
            super(sink, comparator);
        }

        @Override
        public void begin(long size) {
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            list = (size >= 0) ? new ArrayList<>((int) size) : new ArrayList<>();
        }

        @Override
        public void end() throws RestException {
            list.sort(comparator);
            downstream.begin(list.size());
            if (!cancellationRequestedCalled) {
                list.forEach(downstream);
            }
            else {
                for (T t : list) {
                    if (downstream.cancellationRequested()) break;
                    downstream.actuate(t);
                }
            }
            downstream.end();
            list = null;
        }

        @Override
        public void actuate(T t) {
            list.add(t);
        }
    }
}
