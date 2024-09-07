
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.*;

public final class DefaultSpliterators {

    // Suppresses default constructor, ensuring non-instantiability.
    private DefaultSpliterators() {}

    // Empty spliterators

    @SuppressWarnings("unchecked")
    public static <T> DefaultSpliterator<T> emptySpliterator() {
        return (DefaultSpliterator<T>) EMPTY_SPLITERATOR;
    }

    private static final DefaultSpliterator<Object> EMPTY_SPLITERATOR =
            new EmptySpliterator.OfRef<>();



    public static <T> DefaultSpliterator<T> spliterator(T[] array, int startInclusive, int endExclusive) {
        return spliterator(array, startInclusive, endExclusive,
                DefaultSpliterator.ORDERED | DefaultSpliterator.IMMUTABLE);
    }

    // Array-based spliterators


    public static <T> DefaultSpliterator<T> spliterator(Object[] array,
                                                 int additionalCharacteristics) {
        return new ArraySpliterator<>(Objects.requireNonNull(array),
                                      additionalCharacteristics);
    }

    public static <T> DefaultSpliterator<T> spliterator(Object[] array, int fromIndex, int toIndex,
                                                 int additionalCharacteristics) {
        checkFromToBounds(Objects.requireNonNull(array).length, fromIndex, toIndex);
        return new ArraySpliterator<>(array, fromIndex, toIndex, additionalCharacteristics);
    }

    private static void checkFromToBounds(int arrayLength, int origin, int fence) {
        if (origin > fence) {
            throw new ArrayIndexOutOfBoundsException(
                    "origin(" + origin + ") > fence(" + fence + ")");
        }
        if (origin < 0) {
            throw new ArrayIndexOutOfBoundsException(origin);
        }
        if (fence > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(fence);
        }
    }


    public static <T> DefaultSpliterator<T> spliterator(Collection<T> collection) {
        return DefaultSpliterators.spliterator(collection, 0);
    }

    // Iterator-based spliterators

    public static <T> DefaultSpliterator<T> spliterator(Collection<? extends T> c,
                                                 int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(c),
                                         characteristics);
    }

    public static <T> DefaultSpliterator<T> spliterator(Iterator<? extends T> iterator,
                                                 long size,
                                                 int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(iterator), size,
                                         characteristics);
    }

    public static <T> DefaultSpliterator<T> spliteratorUnknownSize(Iterator<? extends T> iterator,
                                                            int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(iterator), characteristics);
    }

    // Iterators from Spliterators

    public static<T> Iterator<T> iterator(DefaultSpliterator<? extends T> spliterator) {
        Objects.requireNonNull(spliterator);
        class Adapter implements DefaultIterator<T>, ConsumerActuator<T> {
            boolean valueReady = false;
            T nextElement;

            @Override
            public void actuate(T t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNexts() throws RestException {
                if (!valueReady)
                    spliterator.tryAdvance(this);
                return valueReady;
            }

            @Override
            public T nexts() throws RestException {
                if (!valueReady && !hasNexts())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new Adapter();
    }


    private static abstract class EmptySpliterator<T, S extends DefaultSpliterator<T>, C> {

        EmptySpliterator() { }

        public S trySplit() throws RestException {
            return null;
        }

        public boolean tryAdvance(C consumer) {
            Objects.requireNonNull(consumer);
            return false;
        }

        public void forEachRemaining(C consumer) {
            Objects.requireNonNull(consumer);
        }

        public long estimateSize() {
            return 0;
        }

        public int characteristics() {
            return DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED;
        }

        private static final class OfRef<T>
                extends DefaultSpliterators.EmptySpliterator<T, DefaultSpliterator<T>, ConsumerActuator<? super T>>
                implements DefaultSpliterator<T> {
            OfRef() { }
        }
    }

    // Array-based spliterators

    static final class ArraySpliterator<T> implements DefaultSpliterator<T> {
        private final Object[] array;
        private int index;        // current index, modified on advance/split
        private final int fence;  // one past last index
        private final int characteristics;

        public ArraySpliterator(Object[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

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

    public static abstract class AbstractSpliterator<T> implements DefaultSpliterator<T> {
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        private final int characteristics;
        private long est;             // size estimate
        private int batch;            // batch size for splits

        protected AbstractSpliterator(long est, int additionalCharacteristics) {
            this.est = est;
            this.characteristics = ((additionalCharacteristics & DefaultSpliterator.SIZED) != 0)
                                   ? additionalCharacteristics | DefaultSpliterator.SUBSIZED
                                   : additionalCharacteristics;
        }

        static final class HoldingConsumer<T> implements ConsumerActuator<T> {
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

    static class IteratorSpliterator<T> implements DefaultSpliterator<T> {
        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        private final Collection<? extends T> collection; // null OK
        private Iterator<? extends T> it;
        private final int characteristics;
        private long est;             // size estimate
        private int batch;            // batch size for splits

        public IteratorSpliterator(Collection<? extends T> collection, int characteristics) {
            this.collection = collection;
            this.it = null;
            this.characteristics = (characteristics & DefaultSpliterator.CONCURRENT) == 0
                                   ? characteristics | DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED
                                   : characteristics;
        }

        public IteratorSpliterator(Iterator<? extends T> iterator, long size, int characteristics) {
            this.collection = null;
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & DefaultSpliterator.CONCURRENT) == 0
                                   ? characteristics | DefaultSpliterator.SIZED | DefaultSpliterator.SUBSIZED
                                   : characteristics;
        }

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
