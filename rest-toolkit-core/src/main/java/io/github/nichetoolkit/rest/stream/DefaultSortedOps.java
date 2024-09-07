package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.*;
import java.util.function.IntFunction;


/**
 * <code>DefaultSortedOps</code>
 * <p>The type default sorted ops class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
final class DefaultSortedOps {

    /**
     * <code>DefaultSortedOps</code>
     * Instantiates a new default sorted ops.
     */
    private DefaultSortedOps() { }

    /**
     * <code>makeRef</code>
     * <p>the ref method.</p>
     * @param <T>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param upstream {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>the upstream parameter is <code>DefaultAbstractPipeline</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>the ref return object is <code>RestStream</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline
     * @see io.github.nichetoolkit.rest.stream.RestStream
     */
    static <T> RestStream<T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream) {
        return new OfRef<>(upstream);
    }

    /**
     * <code>makeRef</code>
     * <p>the ref method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param upstream   {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>the upstream parameter is <code>DefaultAbstractPipeline</code> type.</p>
     * @param comparator {@link java.util.Comparator} <p>the comparator parameter is <code>Comparator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>the ref return object is <code>RestStream</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline
     * @see java.util.Comparator
     * @see io.github.nichetoolkit.rest.stream.RestStream
     */
    static <T> RestStream<T> makeRef(DefaultAbstractPipeline<?, T, ?> upstream,
                                Comparator<? super T> comparator) {
        return new OfRef<>(upstream, comparator);
    }


    /**
     * <code>OfRef</code>
     * <p>The type of ref class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultReferencePipeline.StatefulOp
     * @since Jdk1.8
     */
    private static final class OfRef<T> extends  DefaultReferencePipeline.StatefulOp<T, T> {
        /**
         * <code>isNaturalSort</code>
         * <p>the <code>isNaturalSort</code> field.</p>
         */
        private final boolean isNaturalSort;
        /**
         * <code>comparator</code>
         * {@link java.util.Comparator} <p>the <code>comparator</code> field.</p>
         * @see java.util.Comparator
         */
        private final Comparator<? super T> comparator;

        /**
         * <code>OfRef</code>
         * Instantiates a new of ref.
         * @param upstream {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>the upstream parameter is <code>DefaultAbstractPipeline</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline
         */
        OfRef(DefaultAbstractPipeline<?, T, ?> upstream) {
            super(upstream, DefaultStreamShape.REFERENCE,
                  DefaultStreamOpFlag.IS_ORDERED | DefaultStreamOpFlag.IS_SORTED);
            this.isNaturalSort = true;
            @SuppressWarnings("unchecked")
            Comparator<? super T> comp = (Comparator<? super T>) Comparator.naturalOrder();
            this.comparator = comp;
        }

        /**
         * <code>OfRef</code>
         * Instantiates a new of ref.
         * @param upstream   {@link io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline} <p>the upstream parameter is <code>DefaultAbstractPipeline</code> type.</p>
         * @param comparator {@link java.util.Comparator} <p>the comparator parameter is <code>Comparator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultAbstractPipeline
         * @see java.util.Comparator
         */
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

    /**
     * <code>AbstractRefSortingSink</code>
     * <p>The type abstract ref sorting sink class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSink.ChainedReference
     * @since Jdk1.8
     */
    private static abstract class AbstractRefSortingSink<T> extends DefaultSink.ChainedReference<T, T> {
        /**
         * <code>comparator</code>
         * {@link java.util.Comparator} <p>the <code>comparator</code> field.</p>
         * @see java.util.Comparator
         */
        protected final Comparator<? super T> comparator;
        /**
         * <code>cancellationRequestedCalled</code>
         * <p>the <code>cancellationRequestedCalled</code> field.</p>
         */
// @@@ could be a lazy final value, if/when support is added
        // true if cancellationRequested() has been called
        protected boolean cancellationRequestedCalled;

        /**
         * <code>AbstractRefSortingSink</code>
         * Instantiates a new abstract ref sorting sink.
         * @param downstream {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the downstream parameter is <code>DefaultSink</code> type.</p>
         * @param comparator {@link java.util.Comparator} <p>the comparator parameter is <code>Comparator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         * @see java.util.Comparator
         */
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

    /**
     * <code>SizedRefSortingSink</code>
     * <p>The type sized ref sorting sink class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSortedOps.AbstractRefSortingSink
     * @since Jdk1.8
     */
    private static final class SizedRefSortingSink<T> extends AbstractRefSortingSink<T> {
        /**
         * <code>array</code>
         * <p>the <code>array</code> field.</p>
         */
        private T[] array;
        /**
         * <code>offset</code>
         * <p>the <code>offset</code> field.</p>
         */
        private int offset;

        /**
         * <code>SizedRefSortingSink</code>
         * Instantiates a new sized ref sorting sink.
         * @param sink       {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the sink parameter is <code>DefaultSink</code> type.</p>
         * @param comparator {@link java.util.Comparator} <p>the comparator parameter is <code>Comparator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         * @see java.util.Comparator
         */
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

    /**
     * <code>RefSortingSink</code>
     * <p>The type ref sorting sink class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultSortedOps.AbstractRefSortingSink
     * @since Jdk1.8
     */
    private static final class RefSortingSink<T> extends AbstractRefSortingSink<T> {
        /**
         * <code>list</code>
         * {@link java.util.ArrayList} <p>the <code>list</code> field.</p>
         * @see java.util.ArrayList
         */
        private ArrayList<T> list;

        /**
         * <code>RefSortingSink</code>
         * Instantiates a new ref sorting sink.
         * @param sink       {@link io.github.nichetoolkit.rest.stream.DefaultSink} <p>the sink parameter is <code>DefaultSink</code> type.</p>
         * @param comparator {@link java.util.Comparator} <p>the comparator parameter is <code>Comparator</code> type.</p>
         * @see io.github.nichetoolkit.rest.stream.DefaultSink
         * @see java.util.Comparator
         */
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
