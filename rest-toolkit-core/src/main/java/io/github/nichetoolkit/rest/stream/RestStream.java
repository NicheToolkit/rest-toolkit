package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.*;

import java.util.*;
import java.util.function.*;

public interface RestStream<T> extends DefaultBaseStream<T, RestStream<T>> {

    /* copy form jdk Stream  */

    RestStream<T> filter(PredicateActuator<? super T> predicate) throws RestException;

    <R> RestStream<R> map(FunctionActuator<? super T, ? extends R> mapper) throws RestException;

    <R> RestStream<R> flatMap(FunctionActuator<? super T, ? extends RestStream<? extends R>> mapper) throws RestException;

    RestStream<T> distinct() throws RestException;

    RestStream<T> sorted() throws RestException;

    RestStream<T> sorted(ComparatorActuator<? super T> comparator) throws RestException;

    RestStream<T> peek(ConsumerActuator<? super T> action) throws RestException;

    RestStream<T> limit(long maxSize) throws RestException;

    RestStream<T> skip(long n) throws RestException;

    void forEach(ConsumerActuator<? super T> action) throws RestException;

    void forEachOrdered(ConsumerActuator<? super T> action) throws RestException;

    Object[] toArray() throws RestException;

    <A> A[] toArray(IntFunction<A[]> generator) throws RestException;

    T reduce(T identity, BinaryOperatorActuator<T> accumulator) throws RestException;

    Optional<T> reduce(BinaryOperatorActuator<T> accumulator) throws RestException;

    <U> U reduce(U identity,
                 BiFunctionActuator<U, ? super T, U> accumulator,
                 BinaryOperatorActuator<U> combiner) throws RestException;

    <R> R collect(SupplierActuator<R> supplier,
                  BiConsumerActuator<R, ? super T> accumulator,
                  BiConsumerActuator<R, R> combiner) throws RestException;

    <R, A> R collect(RestCollector<? super T, A, R> collector) throws RestException;

    Optional<T> min(ComparatorActuator<? super T> comparator) throws RestException;

    Optional<T> max(ComparatorActuator<? super T> comparator) throws RestException;

    boolean anyMatch(PredicateActuator<? super T> predicate) throws RestException;

    boolean allMatch(PredicateActuator<? super T> predicate) throws RestException;

    boolean noneMatch(PredicateActuator<? super T> predicate) throws RestException;

    Optional<T> findFirst() throws RestException;

    Optional<T> findAny() throws RestException;

    static <T> Builder<T> builder() throws RestException {
        return new DefaultStreams.StreamBuilderImpl<>();
    }

    static <T> RestStream<T> empty() throws RestException {
        return DefaultStreamSupport.stream(DefaultSpliterators.emptySpliterator(), false);
    }

    static <T> RestStream<T> of(T t) throws RestException {
        return DefaultStreamSupport.stream(new DefaultStreams.StreamBuilderImpl<>(t), false);
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    static <T> RestStream<T> of(T... values) throws RestException {
        return stream(values);
    }

    static <T> RestStream<T> stream(Collection<T> collection) throws RestException {
        DefaultSpliterator<T> spliterator = DefaultSpliterators.spliterator(collection, 0);
        return DefaultStreamSupport.stream(spliterator, false);
    }

    static <T> RestStream<T> stream(T[] array) throws RestException {
        return stream(array, 0, array.length);
    }

    static <T> RestStream<T> stream(T[] array, int startInclusive, int endExclusive) throws RestException {
        return DefaultStreamSupport.stream(spliterator(array, startInclusive, endExclusive), false);
    }

    static <T> DefaultSpliterator<T> spliterator(T[] array, int startInclusive, int endExclusive) throws RestException {
        return DefaultSpliterators.spliterator(array, startInclusive, endExclusive,
                DefaultSpliterator.ORDERED | DefaultSpliterator.IMMUTABLE);
    }

    static <T> RestStream<T> iterate(final T seed, final UnaryOperator<T> f) throws RestException {
        Objects.requireNonNull(f);
        final Iterator<T> iterator = new Iterator<T>() {
            @SuppressWarnings("unchecked")
            T t = (T) DefaultStreams.NONE;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return t = (t == DefaultStreams.NONE) ? seed : f.apply(t);
            }
        };
        return DefaultStreamSupport.stream(DefaultSpliterators.spliteratorUnknownSize(
                iterator,
                DefaultSpliterator.ORDERED | DefaultSpliterator.IMMUTABLE), false);
    }

    static <T> RestStream<T> generate(SupplierActuator<T> s) throws RestException {
        Objects.requireNonNull(s);
        return DefaultStreamSupport.stream(
                new DefaultStreamSpliterators.InfiniteSupplyingSpliterator.OfRef<>(Long.MAX_VALUE, s), false);
    }

    static <T> RestStream<T> concat(RestStream<? extends T> a, RestStream<? extends T> b) throws RestException {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        @SuppressWarnings("unchecked")
        DefaultSpliterator<T> split = new DefaultStreams.ConcatSpliterator.OfRef<>(
                (DefaultSpliterator<T>) a.spliterator(), (DefaultSpliterator<T>) b.spliterator());
        RestStream<T> stream = DefaultStreamSupport.stream(split, a.isParallel() || b.isParallel());
        return stream.onClose(DefaultStreams.composedClose(a, b));
    }

    interface Builder<T> extends ConsumerActuator<T> {

        default Builder<T> add(T t) {
            accept(t);
            return this;
        }

        default Builder<T> apd(T t) throws RestException {
            actuate(t);
            return this;
        }

        RestStream<T> build() throws RestException;

    }
}
