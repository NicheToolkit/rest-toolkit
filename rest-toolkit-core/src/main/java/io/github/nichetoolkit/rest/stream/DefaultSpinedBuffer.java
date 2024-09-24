package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.function.*;

/**
 * <code>DefaultSpinedBuffer</code>
 * <p>The type default spined buffer class.</p>
 * @param <E> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.stream.DefaultAbstractSpinedBuffer
 * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
 * @see io.github.nichetoolkit.rest.stream.DefaultIterable
 * @since Jdk1.8
 */
class DefaultSpinedBuffer<E>
        extends DefaultAbstractSpinedBuffer
        implements ConsumerActuator<E>, DefaultIterable<E> {

    /**
     * <code>curChunk</code>
     * <p>the <code>curChunk</code> field.</p>
     */
    protected E[] curChunk;

    /**
     * <code>spine</code>
     * <p>the <code>spine</code> field.</p>
     */
    protected E[][] spine;

    /**
     * <code>DefaultSpinedBuffer</code>
     * Instantiates a new default spined buffer.
     * @param initialCapacity int <p>the initial capacity parameter is <code>int</code> type.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    DefaultSpinedBuffer(int initialCapacity) {
        super(initialCapacity);
        curChunk = (E[]) new Object[1 << initialChunkPower];
    }

    /**
     * <code>DefaultSpinedBuffer</code>
     * Instantiates a new default spined buffer.
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    DefaultSpinedBuffer() {
        super();
        curChunk = (E[]) new Object[1 << initialChunkPower];
    }

    /**
     * <code>capacity</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     */
    protected long capacity() {
        return (spineIndex == 0)
               ? curChunk.length
               : priorElementCount[spineIndex] + spine[spineIndex].length;
    }

    /**
     * <code>inflateSpine</code>
     * <p>the spine method.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    private void inflateSpine() {
        if (spine == null) {
            spine = (E[][]) new Object[MIN_SPINE_SIZE][];
            priorElementCount = new long[MIN_SPINE_SIZE];
            spine[0] = curChunk;
        }
    }

    /**
     * <code>ensureCapacity</code>
     * <p>the capacity method.</p>
     * @param targetSize long <p>the target size parameter is <code>long</code> type.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    protected final void ensureCapacity(long targetSize) {
        long capacity = capacity();
        if (targetSize > capacity) {
            inflateSpine();
            for (int i=spineIndex+1; targetSize > capacity; i++) {
                if (i >= spine.length) {
                    int newSpineSize = spine.length * 2;
                    spine = Arrays.copyOf(spine, newSpineSize);
                    priorElementCount = Arrays.copyOf(priorElementCount, newSpineSize);
                }
                int nextChunkSize = chunkSize(i);
                spine[i] = (E[]) new Object[nextChunkSize];
                priorElementCount[i] = priorElementCount[i-1] + spine[i-1].length;
                capacity += nextChunkSize;
            }
        }
    }

    /**
     * <code>increaseCapacity</code>
     * <p>the capacity method.</p>
     */
    protected void increaseCapacity() {
        ensureCapacity(capacity() + 1);
    }

    /**
     * <code>get</code>
     * <p>the method.</p>
     * @param index long <p>the index parameter is <code>long</code> type.</p>
     * @return E <p>the return object is <code>E</code> type.</p>
     */
    public E get(long index) {
        if (spineIndex == 0) {
            if (index < elementIndex)
                return curChunk[((int) index)];
            else
                throw new IndexOutOfBoundsException(Long.toString(index));
        }
        if (index >= count())
            throw new IndexOutOfBoundsException(Long.toString(index));
        for (int j=0; j <= spineIndex; j++)
            if (index < priorElementCount[j] + spine[j].length)
                return spine[j][((int) (index - priorElementCount[j]))];

        throw new IndexOutOfBoundsException(Long.toString(index));
    }

    /**
     * <code>copyInto</code>
     * <p>the into method.</p>
     * @param array  E <p>the array parameter is <code>E</code> type.</p>
     * @param offset int <p>the offset parameter is <code>int</code> type.</p>
     */
    public void copyInto(E[] array, int offset) {
        long finalOffset = offset + count();
        if (finalOffset > array.length || finalOffset < offset) {
            throw new IndexOutOfBoundsException("does not fit");
        }
        if (spineIndex == 0)
            System.arraycopy(curChunk, 0, array, offset, elementIndex);
        else {
            for (int i=0; i < spineIndex; i++) {
                System.arraycopy(spine[i], 0, array, offset, spine[i].length);
                offset += spine[i].length;
            }
            if (elementIndex > 0)
                System.arraycopy(curChunk, 0, array, offset, elementIndex);
        }
    }

    /**
     * <code>asArray</code>
     * <p>the array method.</p>
     * @param arrayFactory {@link java.util.function.IntFunction} <p>the array factory parameter is <code>IntFunction</code> type.</p>
     * @return E <p>the array return object is <code>E</code> type.</p>
     * @see java.util.function.IntFunction
     */
    public E[] asArray(IntFunction<E[]> arrayFactory) {
        long size = count();
        if (size >= DefaultNodes.MAX_ARRAY_SIZE)
            throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
        E[] result = arrayFactory.apply((int) size);
        copyInto(result, 0);
        return result;
    }

    @Override
    public void clear() {
        if (spine != null) {
            curChunk = spine[0];
            Arrays.fill(curChunk, null);
            spine = null;
            priorElementCount = null;
        }
        else {
            for (int i=0; i<elementIndex; i++)
                curChunk[i] = null;
        }
        elementIndex = 0;
        spineIndex = 0;
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return DefaultSpliterators.iterator(spliterator());
    }

    @Override
    public void forEach(ConsumerActuator<? super E> consumer) throws RestException {
        for (int j = 0; j < spineIndex; j++)
            for (E t : spine[j])
                consumer.actuate(t);
        for (int i=0; i<elementIndex; i++)
            consumer.actuate(curChunk[i]);
    }

    @Override
    public void actuate(E e) {
        if (elementIndex == curChunk.length) {
            inflateSpine();
            if (spineIndex+1 >= spine.length || spine[spineIndex+1] == null)
                increaseCapacity();
            elementIndex = 0;
            ++spineIndex;
            curChunk = spine[spineIndex];
        }
        curChunk[elementIndex++] = e;
    }

    @Override
    public String toString() {
        List<E> list = new ArrayList<>();
        try {
            forEach(list::add);
        } catch (RestException ignored) {
        }
        return "SpinedBuffer:" + list;
    }

    /**
     * <code>SPLITERATOR_CHARACTERISTICS</code>
     * <p>the constant <code>SPLITERATOR_CHARACTERISTICS</code> field.</p>
     */
    private static final int SPLITERATOR_CHARACTERISTICS
            = DefaultSpliterator.SIZED | DefaultSpliterator.ORDERED | DefaultSpliterator.SUBSIZED;

    public DefaultSpliterator<E> spliterator() {
        class Splitr implements DefaultSpliterator<E> {
            int splSpineIndex;
            final int lastSpineIndex;
            int splElementIndex;
            final int lastSpineElementFence;
            E[] splChunk;

            @SuppressWarnings("Duplicates")
            Splitr(int firstSpineIndex, int lastSpineIndex,
                   int firstSpineElementIndex, int lastSpineElementFence) {
                this.splSpineIndex = firstSpineIndex;
                this.lastSpineIndex = lastSpineIndex;
                this.splElementIndex = firstSpineElementIndex;
                this.lastSpineElementFence = lastSpineElementFence;
                assert spine != null || firstSpineIndex == 0 && lastSpineIndex == 0;
                splChunk = (spine == null) ? curChunk : spine[firstSpineIndex];
            }

            @Override
            public long estimateSize() {
                return (splSpineIndex == lastSpineIndex)
                       ? (long) lastSpineElementFence - splElementIndex
                       :
                       priorElementCount[lastSpineIndex] + lastSpineElementFence -
                       priorElementCount[splSpineIndex] - splElementIndex;
            }

            @Override
            public int characteristics() {
                return SPLITERATOR_CHARACTERISTICS;
            }

            @Override
            public boolean tryAdvance(ConsumerActuator<? super E> consumer) throws RestException {
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lastSpineIndex
                    || (splSpineIndex == lastSpineIndex && splElementIndex < lastSpineElementFence)) {
                    consumer.actuate(splChunk[splElementIndex++]);

                    if (splElementIndex == splChunk.length) {
                        splElementIndex = 0;
                        ++splSpineIndex;
                        if (spine != null && splSpineIndex <= lastSpineIndex)
                            splChunk = spine[splSpineIndex];
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void forEachRemaining(ConsumerActuator<? super E> consumer) throws RestException {
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lastSpineIndex
                    || (splSpineIndex == lastSpineIndex && splElementIndex < lastSpineElementFence)) {
                    int i = splElementIndex;
                    for (int sp = splSpineIndex; sp < lastSpineIndex; sp++) {
                        E[] chunk = spine[sp];
                        for (; i < chunk.length; i++) {
                            consumer.actuate(chunk[i]);
                        }
                        i = 0;
                    }
                    E[] chunk = (splSpineIndex == lastSpineIndex) ? splChunk : spine[lastSpineIndex];
                    for (; i < lastSpineElementFence; i++) {
                        consumer.actuate(chunk[i]);
                    }
                    splSpineIndex = lastSpineIndex;
                    splElementIndex = lastSpineElementFence;
                }
            }

            @Override
            public DefaultSpliterator<E> trySplit() throws RestException {
                if (splSpineIndex < lastSpineIndex) {
                    DefaultSpliterator<E> ret = new Splitr(splSpineIndex, lastSpineIndex - 1,
                                                    splElementIndex, spine[lastSpineIndex-1].length);
                    splSpineIndex = lastSpineIndex;
                    splElementIndex = 0;
                    splChunk = spine[splSpineIndex];
                    return ret;
                }
                else if (splSpineIndex == lastSpineIndex) {
                    int t = (lastSpineElementFence - splElementIndex) / 2;
                    if (t == 0)
                        return null;
                    else {
                        DefaultSpliterator<E> ret = DefaultSpliterators.spliterator(splChunk, splElementIndex, splElementIndex + t);
                        splElementIndex += t;
                        return ret;
                    }
                }
                else {
                    return null;
                }
            }
        }
        return new Splitr(0, spineIndex, 0, elementIndex);
    }

    /**
     * <code>OfPrimitive</code>
     * <p>The type of primitive class.</p>
     * @param <E>      {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T_ARR>  {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <T_CONS> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.stream.DefaultAbstractSpinedBuffer
     * @see io.github.nichetoolkit.rest.stream.DefaultIterable
     * @since Jdk1.8
     */
    abstract static class OfPrimitive<E, T_ARR, T_CONS>
            extends DefaultAbstractSpinedBuffer implements DefaultIterable<E> {

        /**
         * <code>curChunk</code>
         * <p>the <code>curChunk</code> field.</p>
         */
        T_ARR curChunk;

        /**
         * <code>spine</code>
         * <p>the Spine field.</p>
         */
        T_ARR[] spine;

        /**
         * <code>OfPrimitive</code>
         * Instantiates a new of primitive.
         * @param initialCapacity int <p>the initial capacity parameter is <code>int</code> type.</p>
         */
        OfPrimitive(int initialCapacity) {
            super(initialCapacity);
            curChunk = newArray(1 << initialChunkPower);
        }

        /**
         * <code>OfPrimitive</code>
         * Instantiates a new of primitive.
         */
        OfPrimitive() {
            super();
            curChunk = newArray(1 << initialChunkPower);
        }

        @NonNull
        @Override
        public abstract Iterator<E> iterator();

        @Override
        public abstract void forEach(ConsumerActuator<? super E> consumer) throws RestException;

        /**
         * <code>newArrayArray</code>
         * <p>the array array method.</p>
         * @param size int <p>the size parameter is <code>int</code> type.</p>
         * @return T_ARR <p>the array array return object is <code>T_ARR</code> type.</p>
         * @see java.lang.SuppressWarnings
         */
        @SuppressWarnings("SameParameterValue")
        protected abstract T_ARR[] newArrayArray(int size);

        /**
         * <code>newArray</code>
         * <p>the array method.</p>
         * @param size int <p>the size parameter is <code>int</code> type.</p>
         * @return T_ARR <p>the array return object is <code>T_ARR</code> type.</p>
         */
        public abstract T_ARR newArray(int size);

        /**
         * <code>arrayLength</code>
         * <p>the length method.</p>
         * @param array T_ARR <p>the array parameter is <code>T_ARR</code> type.</p>
         * @return int <p>the length return object is <code>int</code> type.</p>
         */
        protected abstract int arrayLength(T_ARR array);

        /**
         * <code>arrayForEach</code>
         * <p>the for each method.</p>
         * @param array    T_ARR <p>the array parameter is <code>T_ARR</code> type.</p>
         * @param from     int <p>the from parameter is <code>int</code> type.</p>
         * @param to       int <p>the to parameter is <code>int</code> type.</p>
         * @param consumer T_CONS <p>the consumer parameter is <code>T_CONS</code> type.</p>
         */
        protected abstract void arrayForEach(T_ARR array, int from, int to,
                                             T_CONS consumer);

        /**
         * <code>capacity</code>
         * <p>the method.</p>
         * @return long <p>the return object is <code>long</code> type.</p>
         */
        protected long capacity() {
            return (spineIndex == 0)
                   ? arrayLength(curChunk)
                   : priorElementCount[spineIndex] + arrayLength(spine[spineIndex]);
        }

        /**
         * <code>inflateSpine</code>
         * <p>the spine method.</p>
         */
        private void inflateSpine() {
            if (spine == null) {
                spine = newArrayArray(MIN_SPINE_SIZE);
                priorElementCount = new long[MIN_SPINE_SIZE];
                spine[0] = curChunk;
            }
        }

        /**
         * <code>ensureCapacity</code>
         * <p>the capacity method.</p>
         * @param targetSize long <p>the target size parameter is <code>long</code> type.</p>
         */
        protected final void ensureCapacity(long targetSize) {
            long capacity = capacity();
            if (targetSize > capacity) {
                inflateSpine();
                for (int i=spineIndex+1; targetSize > capacity; i++) {
                    if (i >= spine.length) {
                        int newSpineSize = spine.length * 2;
                        spine = Arrays.copyOf(spine, newSpineSize);
                        priorElementCount = Arrays.copyOf(priorElementCount, newSpineSize);
                    }
                    int nextChunkSize = chunkSize(i);
                    spine[i] = newArray(nextChunkSize);
                    priorElementCount[i] = priorElementCount[i-1] + arrayLength(spine[i - 1]);
                    capacity += nextChunkSize;
                }
            }
        }

        /**
         * <code>increaseCapacity</code>
         * <p>the capacity method.</p>
         */
        protected void increaseCapacity() {
            ensureCapacity(capacity() + 1);
        }

        /**
         * <code>chunkFor</code>
         * <p>the for method.</p>
         * @param index long <p>the index parameter is <code>long</code> type.</p>
         * @return int <p>the for return object is <code>int</code> type.</p>
         */
        protected int chunkFor(long index) {
            if (spineIndex == 0) {
                if (index < elementIndex)
                    return 0;
                else
                    throw new IndexOutOfBoundsException(Long.toString(index));
            }

            if (index >= count())
                throw new IndexOutOfBoundsException(Long.toString(index));

            for (int j=0; j <= spineIndex; j++)
                if (index < priorElementCount[j] + arrayLength(spine[j]))
                    return j;

            throw new IndexOutOfBoundsException(Long.toString(index));
        }

        /**
         * <code>copyInto</code>
         * <p>the into method.</p>
         * @param array  T_ARR <p>the array parameter is <code>T_ARR</code> type.</p>
         * @param offset int <p>the offset parameter is <code>int</code> type.</p>
         * @see java.lang.SuppressWarnings
         */
        @SuppressWarnings("all")
        public void copyInto(T_ARR array, int offset) {
            long finalOffset = offset + count();
            if (finalOffset > arrayLength(array) || finalOffset < offset) {
                throw new IndexOutOfBoundsException("does not fit");
            }

            if (spineIndex == 0)
                System.arraycopy(curChunk, 0, array, offset, elementIndex);
            else {
                for (int i=0; i < spineIndex; i++) {
                    System.arraycopy(spine[i], 0, array, offset, arrayLength(spine[i]));
                    offset += arrayLength(spine[i]);
                }
                if (elementIndex > 0)
                    System.arraycopy(curChunk, 0, array, offset, elementIndex);
            }
        }

        /**
         * <code>asPrimitiveArray</code>
         * <p>the primitive array method.</p>
         * @return T_ARR <p>the primitive array return object is <code>T_ARR</code> type.</p>
         */
        public T_ARR asPrimitiveArray() {
            long size = count();
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            T_ARR result = newArray((int) size);
            copyInto(result, 0);
            return result;
        }

        /**
         * <code>preAccept</code>
         * <p>the accept method.</p>
         */
        protected void preAccept() {
            if (elementIndex == arrayLength(curChunk)) {
                inflateSpine();
                if (spineIndex+1 >= spine.length || spine[spineIndex+1] == null)
                    increaseCapacity();
                elementIndex = 0;
                ++spineIndex;
                curChunk = spine[spineIndex];
            }
        }

        public void clear() {
            if (spine != null) {
                curChunk = spine[0];
                spine = null;
                priorElementCount = null;
            }
            elementIndex = 0;
            spineIndex = 0;
        }

        /**
         * <code>forEach</code>
         * <p>the each method.</p>
         * @param consumer T_CONS <p>the consumer parameter is <code>T_CONS</code> type.</p>
         * @see java.lang.SuppressWarnings
         */
        @SuppressWarnings("overloads")
        public void forEach(T_CONS consumer) {
            for (int j = 0; j < spineIndex; j++)
                arrayForEach(spine[j], 0, arrayLength(spine[j]), consumer);
            arrayForEach(curChunk, 0, elementIndex, consumer);
        }

        /**
         * <code>BaseSpliterator</code>
         * <p>The type base spliterator class.</p>
         * @param <T_SPLITR> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive} <p>the generic parameter is <code>OfPrimitive</code> type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator.OfPrimitive
         * @since Jdk1.8
         */
        abstract class BaseSpliterator<T_SPLITR extends DefaultSpliterator.OfPrimitive<E, T_CONS, T_SPLITR>>
                implements DefaultSpliterator.OfPrimitive<E, T_CONS, T_SPLITR> {
            /**
             * <code>splSpineIndex</code>
             * <p>the <code>splSpineIndex</code> field.</p>
             */
            int splSpineIndex;
            /**
             * <code>lastSpineIndex</code>
             * <p>the <code>lastSpineIndex</code> field.</p>
             */
            final int lastSpineIndex;
            /**
             * <code>splElementIndex</code>
             * <p>the <code>splElementIndex</code> field.</p>
             */
            int splElementIndex;
            /**
             * <code>lastSpineElementFence</code>
             * <p>the <code>lastSpineElementFence</code> field.</p>
             */
            final int lastSpineElementFence;
            /**
             * <code>splChunk</code>
             * <p>the <code>splChunk</code> field.</p>
             */
            T_ARR splChunk;

            /**
             * <code>BaseSpliterator</code>
             * Instantiates a new base spliterator.
             * @param firstSpineIndex        int <p>the first spine index parameter is <code>int</code> type.</p>
             * @param lastSpineIndex         int <p>the last spine index parameter is <code>int</code> type.</p>
             * @param firstSpineElementIndex int <p>the first spine element index parameter is <code>int</code> type.</p>
             * @param lastSpineElementFence  int <p>the last spine element fence parameter is <code>int</code> type.</p>
             * @see java.lang.SuppressWarnings
             */
            @SuppressWarnings("Duplicates")
            BaseSpliterator(int firstSpineIndex, int lastSpineIndex,
                            int firstSpineElementIndex, int lastSpineElementFence) {
                this.splSpineIndex = firstSpineIndex;
                this.lastSpineIndex = lastSpineIndex;
                this.splElementIndex = firstSpineElementIndex;
                this.lastSpineElementFence = lastSpineElementFence;
                assert spine != null || firstSpineIndex == 0 && lastSpineIndex == 0;
                splChunk = (spine == null) ? curChunk : spine[firstSpineIndex];
            }

            /**
             * <code>newSpliterator</code>
             * <p>the spliterator method.</p>
             * @param firstSpineIndex        int <p>the first spine index parameter is <code>int</code> type.</p>
             * @param lastSpineIndex         int <p>the last spine index parameter is <code>int</code> type.</p>
             * @param firstSpineElementIndex int <p>the first spine element index parameter is <code>int</code> type.</p>
             * @param lastSpineElementFence  int <p>the last spine element fence parameter is <code>int</code> type.</p>
             * @return T_SPLITR <p>the spliterator return object is <code>T_SPLITR</code> type.</p>
             */
            abstract T_SPLITR newSpliterator(int firstSpineIndex, int lastSpineIndex,
                                             int firstSpineElementIndex, int lastSpineElementFence);

            /**
             * <code>arrayForOne</code>
             * <p>the for one method.</p>
             * @param array    T_ARR <p>the array parameter is <code>T_ARR</code> type.</p>
             * @param index    int <p>the index parameter is <code>int</code> type.</p>
             * @param consumer T_CONS <p>the consumer parameter is <code>T_CONS</code> type.</p>
             */
            abstract void arrayForOne(T_ARR array, int index, T_CONS consumer);

            /**
             * <code>arraySpliterator</code>
             * <p>the spliterator method.</p>
             * @param array  T_ARR <p>the array parameter is <code>T_ARR</code> type.</p>
             * @param offset int <p>the offset parameter is <code>int</code> type.</p>
             * @param len    int <p>the len parameter is <code>int</code> type.</p>
             * @return T_SPLITR <p>the spliterator return object is <code>T_SPLITR</code> type.</p>
             */
            abstract T_SPLITR arraySpliterator(T_ARR array, int offset, int len);

            @Override
            public long estimateSize() {
                return (splSpineIndex == lastSpineIndex)
                       ? (long) lastSpineElementFence - splElementIndex
                       :
                       priorElementCount[lastSpineIndex] + lastSpineElementFence -
                       priorElementCount[splSpineIndex] - splElementIndex;
            }

            @Override
            public int characteristics() {
                return SPLITERATOR_CHARACTERISTICS;
            }

            @Override
            public boolean tryAdvance(T_CONS consumer) throws RestException {
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lastSpineIndex
                    || (splSpineIndex == lastSpineIndex && splElementIndex < lastSpineElementFence)) {
                    arrayForOne(splChunk, splElementIndex++, consumer);

                    if (splElementIndex == arrayLength(splChunk)) {
                        splElementIndex = 0;
                        ++splSpineIndex;
                        if (spine != null && splSpineIndex <= lastSpineIndex)
                            splChunk = spine[splSpineIndex];
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void forEachRemaining(T_CONS consumer) throws RestException{
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lastSpineIndex
                    || (splSpineIndex == lastSpineIndex && splElementIndex < lastSpineElementFence)) {
                    int i = splElementIndex;
                    for (int sp = splSpineIndex; sp < lastSpineIndex; sp++) {
                        T_ARR chunk = spine[sp];
                        arrayForEach(chunk, i, arrayLength(chunk), consumer);
                        i = 0;
                    }
                    T_ARR chunk = (splSpineIndex == lastSpineIndex) ? splChunk : spine[lastSpineIndex];
                    arrayForEach(chunk, i, lastSpineElementFence, consumer);
                    splSpineIndex = lastSpineIndex;
                    splElementIndex = lastSpineElementFence;
                }
            }

            @Override
            public T_SPLITR trySplit() {
                if (splSpineIndex < lastSpineIndex) {
                    T_SPLITR ret = newSpliterator(splSpineIndex, lastSpineIndex - 1,
                                                  splElementIndex, arrayLength(spine[lastSpineIndex - 1]));
                    splSpineIndex = lastSpineIndex;
                    splElementIndex = 0;
                    splChunk = spine[splSpineIndex];
                    return ret;
                }
                else if (splSpineIndex == lastSpineIndex) {
                    int t = (lastSpineElementFence - splElementIndex) / 2;
                    if (t == 0)
                        return null;
                    else {
                        T_SPLITR ret = arraySpliterator(splChunk, splElementIndex, t);
                        splElementIndex += t;
                        return ret;
                    }
                }
                else {
                    return null;
                }
            }
        }
    }
}

