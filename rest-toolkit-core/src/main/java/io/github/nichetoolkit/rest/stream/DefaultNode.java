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

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.function.*;

interface DefaultNode<T> {

    DefaultSpliterator<T> spliterator() throws RestException;

    void forEach(ConsumerActuator<? super T> consumer) throws RestException;

    default int getChildCount() {
        return 0;
    }

    default DefaultNode<T> getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    default DefaultNode<T> truncate(long from, long to, IntFunction<T[]> generator) throws RestException {
        if (from == 0 && to == count())
            return this;
        DefaultSpliterator<T> spliterator = spliterator();
        long size = to - from;
        DefaultNode.Builder<T> nodeBuilder = DefaultNodes.builder(size, generator);
        nodeBuilder.begin(size);
        for (int i = 0; i < from && spliterator.tryAdvance(e -> {
        }); i++) {
        }
        for (int i = 0; (i < size) && spliterator.tryAdvance(nodeBuilder); i++) {
        }
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

    }

    interface OfPrimitive<T, T_CONS, T_ARR,
            T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
            T_NODE extends OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
            extends DefaultNode<T> {

        @Override
        T_SPLITR spliterator();

        @SuppressWarnings("overloads")
        void forEach(T_CONS action) throws RestException;

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
}
