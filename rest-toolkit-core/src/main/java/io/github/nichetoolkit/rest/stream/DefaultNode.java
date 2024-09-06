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

import java.util.Spliterator;
import java.util.function.*;

interface DefaultNode<T> {

    Spliterator<T> spliterator();

    void forEach(Consumer<? super T> consumer);

    default int getChildCount() {
        return 0;
    }

    default DefaultNode<T> getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    default DefaultNode<T> truncate(long from, long to, IntFunction<T[]> generator) {
        if (from == 0 && to == count())
            return this;
        Spliterator<T> spliterator = spliterator();
        long size = to - from;
        DefaultNode.Builder<T> nodeBuilder = DefaultNodes.builder(size, generator);
        nodeBuilder.begin(size);
        for (int i = 0; i < from && spliterator.tryAdvance(e -> { }); i++) { }
        for (int i = 0; (i < size) && spliterator.tryAdvance(nodeBuilder); i++) { }
        nodeBuilder.end();
        return nodeBuilder.build();
    }

    T[] asArray(IntFunction<T[]> generator);

    void copyInto(T[] array, int offset);

    default DefaultStreamShape getShape() {
        return DefaultStreamShape.REFERENCE;
    }

    long count();

    interface Builder<T> extends DefaultSink<T> {

        DefaultNode<T> build();

        interface OfInt extends DefaultNode.Builder<Integer>, DefaultSink.OfInt {
            @Override
            DefaultNode.OfInt build();
        }

        interface OfLong extends DefaultNode.Builder<Long>, DefaultSink.OfLong {
            @Override
            DefaultNode.OfLong build();
        }

        interface OfDouble extends DefaultNode.Builder<Double>, DefaultSink.OfDouble {
            @Override
            DefaultNode.OfDouble build();
        }
    }

    interface OfPrimitive<T, T_CONS, T_ARR,
                                 T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
                                 T_NODE extends OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
            extends DefaultNode<T> {

        @Override
        T_SPLITR spliterator();

        @SuppressWarnings("overloads")
        void forEach(T_CONS action);

        @Override
        default T_NODE getChild(int i) {
            throw new IndexOutOfBoundsException();
        }

        T_NODE truncate(long from, long to, IntFunction<T[]> generator);

        @Override
        default T[] asArray(IntFunction<T[]> generator) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Node.OfPrimitive.asArray");

            long size = count();
            if (size >= DefaultNodes.MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(DefaultNodes.BAD_SIZE);
            T[] boxed = generator.apply((int) count());
            copyInto(boxed, 0);
            return boxed;
        }

        T_ARR asPrimitiveArray();

        T_ARR newArray(int count);

        void copyInto(T_ARR array, int offset);
    }

    interface OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, OfInt> {

        @Override
        default void forEach(Consumer<? super Integer> consumer) {
            if (consumer instanceof IntConsumer) {
                forEach((IntConsumer) consumer);
            }
            else {
                if (DefaultTripwire.ENABLED)
                    DefaultTripwire.trip(getClass(), "{0} calling Node.OfInt.forEachRemaining(Consumer)");
                spliterator().forEachRemaining(consumer);
            }
        }

        @Override
        default void copyInto(Integer[] boxed, int offset) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Node.OfInt.copyInto(Integer[], int)");

            int[] array = asPrimitiveArray();
            for (int i = 0; i < array.length; i++) {
                boxed[offset + i] = array[i];
            }
        }

        @Override
        default OfInt truncate(long from, long to, IntFunction<Integer[]> generator) {
            if (from == 0 && to == count())
                return this;
            long size = to - from;
            Spliterator.OfInt spliterator = spliterator();
            Builder.OfInt nodeBuilder = DefaultNodes.intBuilder(size);
            nodeBuilder.begin(size);
            for (int i = 0; i < from && spliterator.tryAdvance((IntConsumer) e -> { }); i++) { }
            for (int i = 0; (i < size) && spliterator.tryAdvance((IntConsumer) nodeBuilder); i++) { }
            nodeBuilder.end();
            return nodeBuilder.build();
        }

        @Override
        default int[] newArray(int count) {
            return new int[count];
        }

        default DefaultStreamShape getShape() {
            return DefaultStreamShape.INT_VALUE;
        }
    }

    interface OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, OfLong> {

        @Override
        default void forEach(Consumer<? super Long> consumer) {
            if (consumer instanceof LongConsumer) {
                forEach((LongConsumer) consumer);
            }
            else {
                if (DefaultTripwire.ENABLED)
                    DefaultTripwire.trip(getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                spliterator().forEachRemaining(consumer);
            }
        }

        @Override
        default void copyInto(Long[] boxed, int offset) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Node.OfInt.copyInto(Long[], int)");

            long[] array = asPrimitiveArray();
            for (int i = 0; i < array.length; i++) {
                boxed[offset + i] = array[i];
            }
        }

        @Override
        default OfLong truncate(long from, long to, IntFunction<Long[]> generator) {
            if (from == 0 && to == count())
                return this;
            long size = to - from;
            Spliterator.OfLong spliterator = spliterator();
            Builder.OfLong nodeBuilder = DefaultNodes.longBuilder(size);
            nodeBuilder.begin(size);
            for (int i = 0; i < from && spliterator.tryAdvance((LongConsumer) e -> { }); i++) { }
            for (int i = 0; (i < size) && spliterator.tryAdvance((LongConsumer) nodeBuilder); i++) { }
            nodeBuilder.end();
            return nodeBuilder.build();
        }

        @Override
        default long[] newArray(int count) {
            return new long[count];
        }

        default DefaultStreamShape getShape() {
            return DefaultStreamShape.LONG_VALUE;
        }
    }

    interface OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, OfDouble> {

        @Override
        default void forEach(Consumer<? super Double> consumer) {
            if (consumer instanceof DoubleConsumer) {
                forEach((DoubleConsumer) consumer);
            }
            else {
                if (DefaultTripwire.ENABLED)
                    DefaultTripwire.trip(getClass(), "{0} calling Node.OfLong.forEachRemaining(Consumer)");
                spliterator().forEachRemaining(consumer);
            }
        }

        //

        @Override
        default void copyInto(Double[] boxed, int offset) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Node.OfDouble.copyInto(Double[], int)");

            double[] array = asPrimitiveArray();
            for (int i = 0; i < array.length; i++) {
                boxed[offset + i] = array[i];
            }
        }

        @Override
        default OfDouble truncate(long from, long to, IntFunction<Double[]> generator) {
            if (from == 0 && to == count())
                return this;
            long size = to - from;
            Spliterator.OfDouble spliterator = spliterator();
            Builder.OfDouble nodeBuilder = DefaultNodes.doubleBuilder(size);
            nodeBuilder.begin(size);
            for (int i = 0; i < from && spliterator.tryAdvance((DoubleConsumer) e -> { }); i++) { }
            for (int i = 0; (i < size) && spliterator.tryAdvance((DoubleConsumer) nodeBuilder); i++) { }
            nodeBuilder.end();
            return nodeBuilder.build();
        }

        @Override
        default double[] newArray(int count) {
            return new double[count];
        }

        default DefaultStreamShape getShape() {
            return DefaultStreamShape.DOUBLE_VALUE;
        }
    }
}
