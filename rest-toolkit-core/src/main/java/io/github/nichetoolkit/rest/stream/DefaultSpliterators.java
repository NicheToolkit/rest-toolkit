
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.*;

/**
 * <code>DefaultSpliterators</code>
 * <p>The type default spliterators class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public final class DefaultSpliterators {

    /**
     * <code>DefaultSpliterators</code>
     * Instantiates a new default spliterators.
     */
    private DefaultSpliterators() {}

    /**
     * <code>emptySpliterator</code>
     * <p>the spliterator method.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator return object is <code>DefaultSpliterator</code> type.</p>
     * @see java.lang.SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    public static <T> DefaultSpliterator<T> emptySpliterator() {
        return (DefaultSpliterator<T>) EMPTY_SPLITERATOR;
    }

    /**
     * <code>EMPTY_SPLITERATOR</code>
     * {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the constant <code>EMPTY_SPLITERATOR</code> field.</p>
     */
    private static final DefaultSpliterator<Object> EMPTY_SPLITERATOR =
            new EmptySpliterator.OfRef<>();

    /**
     * <code>spliterator</code>
     * <p>the method.</p>
     * @param <T>            {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param array          T <p>the array parameter is <code>T</code> type.</p>
     * @param startInclusive int <p>the start inclusive parameter is <code>int</code> type.</p>
     * @param endExclusive   int <p>the end exclusive parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the return object is <code>DefaultSpliterator</code> type.</p>
     */
    public static <T> DefaultSpliterator<T> spliterator(T[] array, int startInclusive, int endExclusive) {
        return spliterator(array, startInclusive, endExclusive,
                DefaultSpliterator.ORDERED | DefaultSpliterator.IMMUTABLE);
    }

    /**
     * <code>spliterator</code>
     * <p>the method.</p>
     * @param <T>                       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param array                     {@link java.lang.Object} <p>the array parameter is <code>Object</code> type.</p>
     * @param additionalCharacteristics int <p>the additional characteristics parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the return object is <code>DefaultSpliterator</code> type.</p>
     * @see java.lang.Object
     */
    public static <T> DefaultSpliterator<T> spliterator(Object[] array, int additionalCharacteristics) {
        return new ArraySpliterator<>(Objects.requireNonNull(array), additionalCharacteristics);
    }

    /**
     * <code>spliterator</code>
     * <p>the method.</p>
     * @param <T>                       {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param array                     {@link java.lang.Object} <p>the array parameter is <code>Object</code> type.</p>
     * @param fromIndex                 int <p>the from index parameter is <code>int</code> type.</p>
     * @param toIndex                   int <p>the to index parameter is <code>int</code> type.</p>
     * @param additionalCharacteristics int <p>the additional characteristics parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the return object is <code>DefaultSpliterator</code> type.</p>
     * @see java.lang.Object
     */
    public static <T> DefaultSpliterator<T> spliterator(Object[] array, int fromIndex, int toIndex, int additionalCharacteristics) {
        checkFromToBounds(Objects.requireNonNull(array).length, fromIndex, toIndex);
        return new ArraySpliterator<>(array, fromIndex, toIndex, additionalCharacteristics);
    }

    /**
     * <code>checkFromToBounds</code>
     * <p>the from to bounds method.</p>
     * @param arrayLength int <p>the array length parameter is <code>int</code> type.</p>
     * @param origin      int <p>the origin parameter is <code>int</code> type.</p>
     * @param fence       int <p>the fence parameter is <code>int</code> type.</p>
     */
    private static void checkFromToBounds(int arrayLength, int origin, int fence) {
        if (origin > fence) {
            throw new ArrayIndexOutOfBoundsException("origin(" + origin + ") > fence(" + fence + ")");
        }
        if (origin < 0) {
            throw new ArrayIndexOutOfBoundsException(origin);
        }
        if (fence > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(fence);
        }
    }

    /**
     * <code>spliterator</code>
     * <p>the method.</p>
     * @param <T>        {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param collection {@link java.util.Collection} <p>the collection parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the return object is <code>DefaultSpliterator</code> type.</p>
     * @see java.util.Collection
     */
    public static <T> DefaultSpliterator<T> spliterator(Collection<T> collection) {
        return DefaultSpliterators.spliterator(collection, 0);
    }

    /**
     * <code>spliterator</code>
     * <p>the method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param c               {@link java.util.Collection} <p>the c parameter is <code>Collection</code> type.</p>
     * @param characteristics int <p>the characteristics parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the return object is <code>DefaultSpliterator</code> type.</p>
     * @see java.util.Collection
     */
    public static <T> DefaultSpliterator<T> spliterator(Collection<? extends T> c,
                                                 int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(c),
                                         characteristics);
    }

    /**
     * <code>spliterator</code>
     * <p>the method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param iterator        {@link java.util.Iterator} <p>the iterator parameter is <code>Iterator</code> type.</p>
     * @param size            long <p>the size parameter is <code>long</code> type.</p>
     * @param characteristics int <p>the characteristics parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the return object is <code>DefaultSpliterator</code> type.</p>
     * @see java.util.Iterator
     */
    public static <T> DefaultSpliterator<T> spliterator(Iterator<? extends T> iterator,
                                                 long size,
                                                 int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(iterator), size,
                                         characteristics);
    }

    /**
     * <code>spliteratorUnknownSize</code>
     * <p>the unknown size method.</p>
     * @param <T>             {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param iterator        {@link java.util.Iterator} <p>the iterator parameter is <code>Iterator</code> type.</p>
     * @param characteristics int <p>the characteristics parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the unknown size return object is <code>DefaultSpliterator</code> type.</p>
     * @see java.util.Iterator
     */
    public static <T> DefaultSpliterator<T> spliteratorUnknownSize(Iterator<? extends T> iterator,
                                                            int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(iterator), characteristics);
    }

    /**
     * <code>iterator</code>
     * <p>the method.</p>
     * @param <T>         {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @return {@link java.util.Iterator} <p>the return object is <code>Iterator</code> type.</p>
     * @see java.util.Iterator
     */
    public static<T> Iterator<T> iterator(DefaultSpliterator<? extends T> spliterator) {
        Objects.requireNonNull(spliterator);

        class Adapter implements Iterator<T>, ConsumerActuator<T> {
            boolean valueReady = false;
            T nextElement;

            @Override
            public void actuate(T t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNext() {
                if (!valueReady) {
                    try {
                        spliterator.tryAdvance(this);
                    } catch (RestException e) {
                        throw new RestError(e);
                    }
                }
                return valueReady;
            }

            @Override
            public T next() {
                if (!valueReady && !hasNext())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new Adapter();
    }


    /**
     * <code>EmptySpliterator</code>
     * <p>The type empty spliterator class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @param <S> {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>the generic parameter is <code>DefaultSpliterator</code> type.</p>
     * @param <C> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    private static abstract class EmptySpliterator<T, S extends DefaultSpliterator<T>, C> {

        /**
         * <code>EmptySpliterator</code>
         * Instantiates a new empty spliterator.
         */
        EmptySpliterator() { }

        /**
         * <code>trySplit</code>
         * <p>the split method.</p>
         * @return S <p>the split return object is <code>S</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        public S trySplit() throws RestException {
            return null;
        }

        /**
         * <code>tryAdvance</code>
         * <p>the advance method.</p>
         * @param consumer C <p>the consumer parameter is <code>C</code> type.</p>
         * @return boolean <p>the advance return object is <code>boolean</code> type.</p>
         */
        public boolean tryAdvance(C consumer) {
            Objects.requireNonNull(consumer);
            return false;
        }

        /**
         * <code>forEachRemaining</code>
         * <p>the each remaining method.</p>
         * @param consumer C <p>the consumer parameter is <code>C</code> type.</p>
         */
        public void forEachRemaining(C consumer) {
            Objects.requireNonNull(consumer);
        }

        /**
         * <code>estimateSize</code>
         * <p>the size method.</p>
         * @return long <p>the size return object is <code>long</code> type.</p>
         */
        public long estimateSize() {
            return 0;
        }

        /**
         * <code>characteristics</code>
         * <p>the method.</p>
         * @return int <p>the return object is <code>int</code> type.</p>
         */
        public int characteristics() {
            return DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED;
        }

        /**
         * <code>OfRef</code>
         * <p>The type of ref class.</p>
         * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @since Jdk1.8
         */
        private static final class OfRef<T>
                extends DefaultSpliterators.EmptySpliterator<T, DefaultSpliterator<T>, ConsumerActuator<? super T>>
                implements DefaultSpliterator<T> {
            /**
             * <code>OfRef</code>
             * Instantiates a new of ref.
             */
            OfRef() { }
        }
    }

    // Array-based spliterators

    /**
     * <code>ArraySpliterator</code>
     * <p>The type array spliterator class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    static final class ArraySpliterator<T> implements DefaultSpliterator<T> {
        /**
         * <code>array</code>
         * {@link java.lang.Object} <p>the <code>array</code> field.</p>
         * @see java.lang.Object
         */
        private final Object[] array;
        /**
         * <code>index</code>
         * <p>the <code>index</code> field.</p>
         */
        private int index;        // current index, modified on advance/split
        /**
         * <code>fence</code>
         * <p>the <code>fence</code> field.</p>
         */
        private final int fence;  // one past last index
        /**
         * <code>characteristics</code>
         * <p>the <code>characteristics</code> field.</p>
         */
        private final int characteristics;

        /**
         * <code>ArraySpliterator</code>
         * Instantiates a new array spliterator.
         * @param array                     {@link java.lang.Object} <p>the array parameter is <code>Object</code> type.</p>
         * @param additionalCharacteristics int <p>the additional characteristics parameter is <code>int</code> type.</p>
         * @see java.lang.Object
         */
        public ArraySpliterator(Object[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        /**
         * <code>ArraySpliterator</code>
         * Instantiates a new array spliterator.
         * @param array                     {@link java.lang.Object} <p>the array parameter is <code>Object</code> type.</p>
         * @param origin                    int <p>the origin parameter is <code>int</code> type.</p>
         * @param fence                     int <p>the fence parameter is <code>int</code> type.</p>
         * @param additionalCharacteristics int <p>the additional characteristics parameter is <code>int</code> type.</p>
         * @see java.lang.Object
         */
        public ArraySpliterator(Object[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED;
        }

        @Override
        public DefaultSpliterator<T> trySplit() throws RestException {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                   ? null
                   : new ArraySpliterator<>(array, lo, index = mid, characteristics);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void forEachRemaining(ConsumerActuator<? super T> action) throws RestException {
            Object[] a; int i, hi; // hoist accesses and checks from loop
            if (action == null)
                throw new NullPointerException();
            if ((a = array).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
                do { action.actuate((T)a[i]); } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                @SuppressWarnings("unchecked") T e = (T) array[index++];
                action.actuate(e);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() { return fence - index; }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super T> getComparator() throws RestException {
            if (hasCharacteristics(DefaultSpliterator.SORTED))
                return null;
            throw new IllegalStateException();
        }
    }

    /**
     * <code>AbstractSpliterator</code>
     * <p>The type abstract spliterator class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    public static abstract class AbstractSpliterator<T> implements DefaultSpliterator<T> {
        /**
         * <code>BATCH_UNIT</code>
         * <p>the <code>BATCH_UNIT</code> field.</p>
         */
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        /**
         * <code>MAX_BATCH</code>
         * <p>the <code>MAX_BATCH</code> field.</p>
         */
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        /**
         * <code>characteristics</code>
         * <p>the <code>characteristics</code> field.</p>
         */
        private final int characteristics;
        /**
         * <code>est</code>
         * <p>the <code>est</code> field.</p>
         */
        private long est;             // size estimate
        /**
         * <code>batch</code>
         * <p>the <code>batch</code> field.</p>
         */
        private int batch;            // batch size for splits

        /**
         * <code>AbstractSpliterator</code>
         * Instantiates a new abstract spliterator.
         * @param est                       long <p>the est parameter is <code>long</code> type.</p>
         * @param additionalCharacteristics int <p>the additional characteristics parameter is <code>int</code> type.</p>
         */
        protected AbstractSpliterator(long est, int additionalCharacteristics) {
            this.est = est;
            this.characteristics = ((additionalCharacteristics & DefaultSpliterator.SIZED) != 0)
                                   ? additionalCharacteristics | DefaultSpliterator.SUBSIZED
                                   : additionalCharacteristics;
        }

        /**
         * <code>HoldingConsumer</code>
         * <p>The type holding consumer class.</p>
         * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
         * @author Cyan (snow22314@outlook.com)
         * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
         * @since Jdk1.8
         */
        static final class HoldingConsumer<T> implements ConsumerActuator<T> {
            /**
             * <code>value</code>
             * {@link java.lang.Object} <p>the <code>value</code> field.</p>
             * @see java.lang.Object
             */
            Object value;

            @Override
            public void actuate(T value) {
                this.value = value;
            }
        }

        @Override
        public DefaultSpliterator<T> trySplit() throws RestException {
            HoldingConsumer<T> holder = new HoldingConsumer<>();
            long s = est;
            if (s > 1 && tryAdvance(holder)) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Object[] a = new Object[n];
                int j = 0;
                do { a[j] = holder.value; } while (++j < n && tryAdvance(holder));
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new ArraySpliterator<>(a, 0, j, characteristics());
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return est;
        }

        @Override
        public int characteristics() {
            return characteristics;
        }
    }

    // Iterator-based Spliterators

    /**
     * <code>IteratorSpliterator</code>
     * <p>The type iterator spliterator class.</p>
     * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    static class IteratorSpliterator<T> implements DefaultSpliterator<T> {
        /**
         * <code>BATCH_UNIT</code>
         * <p>the <code>BATCH_UNIT</code> field.</p>
         */
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        /**
         * <code>MAX_BATCH</code>
         * <p>the <code>MAX_BATCH</code> field.</p>
         */
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        /**
         * <code>collection</code>
         * {@link java.util.Collection} <p>the <code>collection</code> field.</p>
         * @see java.util.Collection
         */
        private final Collection<? extends T> collection; // null OK
        /**
         * <code>it</code>
         * {@link java.util.Iterator} <p>the <code>it</code> field.</p>
         * @see java.util.Iterator
         */
        private Iterator<? extends T> it;
        /**
         * <code>characteristics</code>
         * <p>the <code>characteristics</code> field.</p>
         */
        private final int characteristics;
        /**
         * <code>est</code>
         * <p>the <code>est</code> field.</p>
         */
        private long est;             // size estimate
        /**
         * <code>batch</code>
         * <p>the <code>batch</code> field.</p>
         */
        private int batch;            // batch size for splits

        /**
         * <code>IteratorSpliterator</code>
         * Instantiates a new iterator spliterator.
         * @param collection      {@link java.util.Collection} <p>the collection parameter is <code>Collection</code> type.</p>
         * @param characteristics int <p>the characteristics parameter is <code>int</code> type.</p>
         * @see java.util.Collection
         */
        public IteratorSpliterator(Collection<? extends T> collection, int characteristics) {
            this.collection = collection;
            this.it = null;
            this.characteristics = (characteristics & DefaultSpliterator.CONCURRENT) == 0
                                   ? characteristics | DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED
                                   : characteristics;
        }

        /**
         * <code>IteratorSpliterator</code>
         * Instantiates a new iterator spliterator.
         * @param iterator        {@link java.util.Iterator} <p>the iterator parameter is <code>Iterator</code> type.</p>
         * @param size            long <p>the size parameter is <code>long</code> type.</p>
         * @param characteristics int <p>the characteristics parameter is <code>int</code> type.</p>
         * @see java.util.Iterator
         */
        public IteratorSpliterator(Iterator<? extends T> iterator, long size, int characteristics) {
            this.collection = null;
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & DefaultSpliterator.CONCURRENT) == 0
                                   ? characteristics | DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED
                                   : characteristics;
        }

        /**
         * <code>IteratorSpliterator</code>
         * Instantiates a new iterator spliterator.
         * @param iterator        {@link java.util.Iterator} <p>the iterator parameter is <code>Iterator</code> type.</p>
         * @param characteristics int <p>the characteristics parameter is <code>int</code> type.</p>
         * @see java.util.Iterator
         */
        public IteratorSpliterator(Iterator<? extends T> iterator, int characteristics) {
            this.collection = null;
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED);
        }

        @Override
        public DefaultSpliterator<T> trySplit() throws RestException {
            Iterator<? extends T> i;
            long s;
            if ((i = it) == null) {
                assert collection != null;
                i = it = collection.iterator();
                s = est = collection.size();
            }
            else
                s = est;
            if (s > 1 && i.hasNext()) {
                int n = batch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Object[] a = new Object[n];
                int j = 0;
                do { a[j] = i.next(); } while (++j < n && i.hasNext());
                batch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new ArraySpliterator<>(a, 0, j, characteristics);
            }
            return null;
        }

        @Override
        public void forEachRemaining(ConsumerActuator<? super T> action) {
            if (action == null) throw new NullPointerException();
            Iterator<? extends T> i;
            if ((i = it) == null) {
                assert collection != null;
                i = it = collection.iterator();
                est = collection.size();
            }
            i.forEachRemaining(action);
        }

        @Override
        public boolean tryAdvance(ConsumerActuator<? super T> action) throws RestException {
            if (action == null) throw new NullPointerException();
            if (it == null) {
                assert collection != null;
                it = collection.iterator();
                est = collection.size();
            }
            if (it.hasNext()) {
                action.actuate(it.next());
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            if (it == null) {
                assert collection != null;
                it = collection.iterator();
                return est = collection.size();
            }
            return est;
        }

        @Override
        public int characteristics() { return characteristics; }

        @Override
        public Comparator<? super T> getComparator() throws RestException {
            if (hasCharacteristics(DefaultSpliterator.SORTED))
                return null;
            throw new IllegalStateException();
        }
    }
}
