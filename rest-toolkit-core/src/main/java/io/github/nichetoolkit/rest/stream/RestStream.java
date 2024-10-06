package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestOptional;
import io.github.nichetoolkit.rest.actuator.*;
import org.springframework.lang.NonNull;

import java.util.*;
import java.util.function.*;

/**
 * <code>RestStream</code>
 * <p>The type rest stream interface.</p>
 * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.stream.DefaultBaseStream
 * @since Jdk1.8
 */
public interface RestStream<T> extends DefaultBaseStream<T, RestStream<T>> {

    /**
     * <code>filter</code>
     * <p>The method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestStream<T> filter(PredicateActuator<? super T> predicate) throws RestException;

    /**
     * <code>map</code>
     * <p>The method.</p>
     * @param <R>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    <R> RestStream<R> map(FunctionActuator<? super T, ? extends R> mapper) throws RestException;

    /**
     * <code>flatMap</code>
     * <p>The map method.</p>
     * @param <R>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param mapper {@link io.github.nichetoolkit.rest.actuator.FunctionActuator} <p>The mapper parameter is <code>FunctionActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The map return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.FunctionActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    <R> RestStream<R> flatMap(FunctionActuator<? super T, ? extends RestStream<? extends R>> mapper) throws RestException;

    /**
     * <code>distinct</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestStream<T> distinct() throws RestException;

    /**
     * <code>sorted</code>
     * <p>The method.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestStream<T> sorted() throws RestException;

    /**
     * <code>sorted</code>
     * <p>The method.</p>
     * @param comparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestStream<T> sorted(ComparatorActuator<? super T> comparator) throws RestException;

    /**
     * <code>peek</code>
     * <p>The method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestStream<T> peek(ConsumerActuator<? super T> action) throws RestException;

    /**
     * <code>limit</code>
     * <p>The method.</p>
     * @param maxSize long <p>The max size parameter is <code>long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestStream<T> limit(long maxSize) throws RestException;

    /**
     * <code>skip</code>
     * <p>The method.</p>
     * @param n long <p>The n parameter is <code>long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    RestStream<T> skip(long n) throws RestException;

    /**
     * <code>forEach</code>
     * <p>The each method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    void forEach(ConsumerActuator<? super T> action) throws RestException;

    /**
     * <code>forEachOrdered</code>
     * <p>The each ordered method.</p>
     * @param action {@link io.github.nichetoolkit.rest.actuator.ConsumerActuator} <p>The action parameter is <code>ConsumerActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    void forEachOrdered(ConsumerActuator<? super T> action) throws RestException;

    /**
     * <code>toArray</code>
     * <p>The array method.</p>
     * @return {@link java.lang.Object} <p>The array return object is <code>Object</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.lang.Object
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    Object[] toArray() throws RestException;

    /**
     * <code>toArray</code>
     * <p>The array method.</p>
     * @param <A>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param generator {@link java.util.function.IntFunction} <p>The generator parameter is <code>IntFunction</code> type.</p>
     * @return A <p>The array return object is <code>A</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.IntFunction
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    <A> A[] toArray(IntFunction<A[]> generator) throws RestException;

    /**
     * <code>reduce</code>
     * <p>The method.</p>
     * @param identity    T <p>The identity parameter is <code>T</code> type.</p>
     * @param accumulator {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The accumulator parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return T <p>The return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    T reduce(T identity, BinaryOperatorActuator<T> accumulator) throws RestException;

    /**
     * <code>reduce</code>
     * <p>The method.</p>
     * @param accumulator {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The accumulator parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestOptional<T> reduce(BinaryOperatorActuator<T> accumulator) throws RestException;

    /**
     * <code>reduce</code>
     * <p>The method.</p>
     * @param <U>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param identity    U <p>The identity parameter is <code>U</code> type.</p>
     * @param accumulator {@link io.github.nichetoolkit.rest.actuator.BiFunctionActuator} <p>The accumulator parameter is <code>BiFunctionActuator</code> type.</p>
     * @param combiner    {@link io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator} <p>The combiner parameter is <code>BinaryOperatorActuator</code> type.</p>
     * @return U <p>The return object is <code>U</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.BiFunctionActuator
     * @see io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    <U> U reduce(U identity,
                 BiFunctionActuator<U, ? super T, U> accumulator,
                 BinaryOperatorActuator<U> combiner) throws RestException;

    /**
     * <code>collect</code>
     * <p>The method.</p>
     * @param <R>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param supplier    {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param accumulator {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The accumulator parameter is <code>BiConsumerActuator</code> type.</p>
     * @param combiner    {@link io.github.nichetoolkit.rest.actuator.BiConsumerActuator} <p>The combiner parameter is <code>BiConsumerActuator</code> type.</p>
     * @return R <p>The return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.actuator.BiConsumerActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    <R> R collect(SupplierActuator<R> supplier,
                  BiConsumerActuator<R, ? super T> accumulator,
                  BiConsumerActuator<R, R> combiner) throws RestException;

    /**
     * <code>collect</code>
     * <p>The method.</p>
     * @param <R>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param <A>       {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param collector {@link io.github.nichetoolkit.rest.stream.RestCollector} <p>The collector parameter is <code>RestCollector</code> type.</p>
     * @return R <p>The return object is <code>R</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.RestCollector
     * @see io.github.nichetoolkit.rest.RestException
     */
    <R, A> R collect(RestCollector<? super T, A, R> collector) throws RestException;

    /**
     * <code>min</code>
     * <p>The method.</p>
     * @param comparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestOptional<T> min(ComparatorActuator<? super T> comparator) throws RestException;

    /**
     * <code>max</code>
     * <p>The method.</p>
     * @param comparator {@link io.github.nichetoolkit.rest.actuator.ComparatorActuator} <p>The comparator parameter is <code>ComparatorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.ComparatorActuator
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestOptional<T> max(ComparatorActuator<? super T> comparator) throws RestException;

    /**
     * <code>anyMatch</code>
     * <p>The match method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return boolean <p>The match return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean anyMatch(PredicateActuator<? super T> predicate) throws RestException;

    /**
     * <code>allMatch</code>
     * <p>The match method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return boolean <p>The match return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean allMatch(PredicateActuator<? super T> predicate) throws RestException;

    /**
     * <code>noneMatch</code>
     * <p>The match method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return boolean <p>The match return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean noneMatch(PredicateActuator<? super T> predicate) throws RestException;

    /**
     * <code>findFirst</code>
     * <p>The first method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The first return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestOptional<T> findFirst() throws RestException;

    /**
     * <code>findAny</code>
     * <p>The any method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The any return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestOptional<T> findAny() throws RestException;

    /**
     * <code>findAny</code>
     * <p>The any method.</p>
     * @param predicate {@link io.github.nichetoolkit.rest.actuator.PredicateActuator} <p>The predicate parameter is <code>PredicateActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestOptional} <p>The any return object is <code>RestOptional</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.PredicateActuator
     * @see io.github.nichetoolkit.rest.RestOptional
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    RestOptional<T> findAny(PredicateActuator<T> predicate) throws RestException;

    /**
     * <code>builder</code>
     * <p>The method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream.Builder} <p>The return object is <code>Builder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.RestStream.Builder
     * @see io.github.nichetoolkit.rest.RestException
     */
    static <T> Builder<T> builder() throws RestException {
        return new DefaultStreams.StreamBuilderImpl<>();
    }

    /**
     * <code>empty</code>
     * <p>The method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> RestStream<T> empty() throws RestException {
        return DefaultStreamSupport.stream(DefaultSpliterators.emptySpliterator(), false);
    }

    /**
     * <code>of</code>
     * <p>The method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param t   T <p>The t parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> RestStream<T> of(T t) throws RestException {
        return DefaultStreamSupport.stream(new DefaultStreams.StreamBuilderImpl<>(t), false);
    }

    /**
     * <code>of</code>
     * <p>The method.</p>
     * @param <T>    {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param values T <p>The values parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see java.lang.SafeVarargs
     * @see java.lang.SuppressWarnings
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    @SafeVarargs
    @SuppressWarnings("varargs")
    static <T> RestStream<T> of(T... values) throws RestException {
        return stream(values);
    }

    /**
     * <code>stream</code>
     * <p>The method.</p>
     * @param <T>        {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param collection {@link java.util.Collection} <p>The collection parameter is <code>Collection</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.Collection
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> RestStream<T> stream(Collection<T> collection) throws RestException {
        DefaultSpliterator<T> spliterator = DefaultSpliterators.spliterator(collection, 0);
        return DefaultStreamSupport.stream(spliterator, false);
    }

    /**
     * <code>stream</code>
     * <p>The method.</p>
     * @param <T>   {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param array T <p>The array parameter is <code>T</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> RestStream<T> stream(T[] array) throws RestException {
        return stream(array, 0, array.length);
    }

    /**
     * <code>stream</code>
     * <p>The method.</p>
     * @param <T>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param array          T <p>The array parameter is <code>T</code> type.</p>
     * @param startInclusive int <p>The start inclusive parameter is <code>int</code> type.</p>
     * @param endExclusive   int <p>The end exclusive parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> RestStream<T> stream(T[] array, int startInclusive, int endExclusive) throws RestException {
        return DefaultStreamSupport.stream(spliterator(array, startInclusive, endExclusive), false);
    }

    /**
     * <code>spliterator</code>
     * <p>The method.</p>
     * @param <T>            {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param array          T <p>The array parameter is <code>T</code> type.</p>
     * @param startInclusive int <p>The start inclusive parameter is <code>int</code> type.</p>
     * @param endExclusive   int <p>The end exclusive parameter is <code>int</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The return object is <code>DefaultSpliterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> DefaultSpliterator<T> spliterator(T[] array, int startInclusive, int endExclusive) throws RestException {
        return DefaultSpliterators.spliterator(array, startInclusive, endExclusive,
                DefaultSpliterator.ORDERED | DefaultSpliterator.IMMUTABLE);
    }

    /**
     * <code>iterate</code>
     * <p>The method.</p>
     * @param <T>  {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param seed T <p>The seed parameter is <code>T</code> type.</p>
     * @param f    {@link java.util.function.UnaryOperator} <p>The f parameter is <code>UnaryOperator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see java.util.function.UnaryOperator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
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

    /**
     * <code>generate</code>
     * <p>The method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param s   {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The s parameter is <code>SupplierActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> RestStream<T> generate(SupplierActuator<T> s) throws RestException {
        Objects.requireNonNull(s);
        return DefaultStreamSupport.stream(
                new DefaultStreamSpliterators.InfiniteSupplyingSpliterator.OfRef<>(Long.MAX_VALUE, s), false);
    }

    /**
     * <code>concat</code>
     * <p>The method.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param a   {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The a parameter is <code>RestStream</code> type.</p>
     * @param b   {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The b parameter is <code>RestStream</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see org.springframework.lang.NonNull
     * @see io.github.nichetoolkit.rest.RestException
     */
    @NonNull
    static <T> RestStream<T> concat(RestStream<? extends T> a, RestStream<? extends T> b) throws RestException {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        @SuppressWarnings("unchecked")
        DefaultSpliterator<T> split = new DefaultStreams.ConcatSpliterator.OfRef<>(
                (DefaultSpliterator<T>) a.spliterator(), (DefaultSpliterator<T>) b.spliterator());
        RestStream<T> stream = DefaultStreamSupport.stream(split, a.isParallel() || b.isParallel());
        return stream.onClose(DefaultStreams.composedClose(a, b));
    }

    /**
     * <code>Builder</code>
     * <p>The type builder interface.</p>
     * @param <T> {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see io.github.nichetoolkit.rest.actuator.ConsumerActuator
     * @since Jdk1.8
     */
    interface Builder<T> extends ConsumerActuator<T> {

        @Override
        void actuate(T t);

        /**
         * <code>add</code>
         * <p>The method.</p>
         * @param t T <p>The t parameter is <code>T</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.RestStream.Builder} <p>The return object is <code>Builder</code> type.</p>
         */
        default Builder<T> add(T t) {
            accept(t);
            return this;
        }

        /**
         * <code>apd</code>
         * <p>The method.</p>
         * @param t T <p>The t parameter is <code>T</code> type.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.RestStream.Builder} <p>The return object is <code>Builder</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        default Builder<T> apd(T t) throws RestException {
            actuate(t);
            return this;
        }

        /**
         * <code>build</code>
         * <p>The method.</p>
         * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The return object is <code>RestStream</code> type.</p>
         * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
         * @see io.github.nichetoolkit.rest.RestException
         */
        RestStream<T> build() throws RestException;

    }
}
