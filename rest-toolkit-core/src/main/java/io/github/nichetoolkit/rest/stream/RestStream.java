/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package io.github.nichetoolkit.rest.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public interface RestStream<T> extends BaseStream<T, RestStream<T>> {

    /* copy form jdk Stream  */

    RestStream<T> filter(Predicate<? super T> predicate);

    <R> RestStream<R> map(Function<? super T, ? extends R> mapper);

//    IntStream mapToInt(ToIntFunction<? super T> mapper);
//
//    LongStream mapToLong(ToLongFunction<? super T> mapper);
//
//    DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);

    <R> RestStream<R> flatMap(Function<? super T, ? extends RestStream<? extends R>> mapper);

//    IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper);
//
//    LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper);
//
//    DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper);

    RestStream<T> distinct();

    RestStream<T> sorted();

    RestStream<T> sorted(Comparator<? super T> comparator);

    RestStream<T> peek(Consumer<? super T> action);

    RestStream<T> limit(long maxSize);

    RestStream<T> skip(long n);

    void forEach(Consumer<? super T> action);

    void forEachOrdered(Consumer<? super T> action);

    Object[] toArray();

    <A> A[] toArray(IntFunction<A[]> generator);

    T reduce(T identity, BinaryOperator<T> accumulator);

    Optional<T> reduce(BinaryOperator<T> accumulator);

    <U> U reduce(U identity,
                 BiFunction<U, ? super T, U> accumulator,
                 BinaryOperator<U> combiner);

    <R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> accumulator,
                  BiConsumer<R, R> combiner);

    <R, A> R collect(Collector<? super T, A, R> collector);

    Optional<T> min(Comparator<? super T> comparator);

    Optional<T> max(Comparator<? super T> comparator);

//    long count();

    boolean anyMatch(Predicate<? super T> predicate);

    boolean allMatch(Predicate<? super T> predicate);

    boolean noneMatch(Predicate<? super T> predicate);

    Optional<T> findFirst();

    Optional<T> findAny();

    // Static factories

    static<T> Builder<T> builder() {
        return new DefaultStreams.StreamBuilderImpl<>();
    }

    static<T> RestStream<T> empty() {
        return RestStreamSupport.stream(Spliterators.<T>emptySpliterator(), false);
    }

    static<T> RestStream<T> of(T t) {
        return RestStreamSupport.stream(new DefaultStreams.StreamBuilderImpl<>(t), false);
    }

    @SafeVarargs
    @SuppressWarnings("varargs") // Creating a stream from an array is safe
    static<T> RestStream<T> of(T... values) {
        return stream(values);
    }

    static <T> RestStream<T> stream(T[] array) {
        return stream(array, 0, array.length);
    }

    static <T> RestStream<T> stream(T[] array, int startInclusive, int endExclusive) {
        return RestStreamSupport.stream(spliterator(array, startInclusive, endExclusive), false);
    }

    static <T> Spliterator<T> spliterator(T[] array, int startInclusive, int endExclusive) {
        return Spliterators.spliterator(array, startInclusive, endExclusive,
                Spliterator.ORDERED | Spliterator.IMMUTABLE);
    }

    static<T> RestStream<T> iterate(final T seed, final UnaryOperator<T> f) {
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
        return RestStreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator,
                Spliterator.ORDERED | Spliterator.IMMUTABLE), false);
    }

    static<T> RestStream<T> generate(Supplier<T> s) {
        Objects.requireNonNull(s);
        return RestStreamSupport.stream(
                new DefaultStreamSpliterators.InfiniteSupplyingSpliterator.OfRef<>(Long.MAX_VALUE, s), false);
    }

    static <T> RestStream<T> concat(RestStream<? extends T> a, RestStream<? extends T> b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        @SuppressWarnings("unchecked")
        Spliterator<T> split = new DefaultStreams.ConcatSpliterator.OfRef<>(
                (Spliterator<T>) a.spliterator(), (Spliterator<T>) b.spliterator());
        RestStream<T> stream = RestStreamSupport.stream(split, a.isParallel() || b.isParallel());
        return stream.onClose(DefaultStreams.composedClose(a, b));
    }

    interface Builder<T> extends Consumer<T> {

        @Override
        void accept(T t);

        default Builder<T> add(T t) {
            accept(t);
            return this;
        }

        RestStream<T> build();

    }
}
