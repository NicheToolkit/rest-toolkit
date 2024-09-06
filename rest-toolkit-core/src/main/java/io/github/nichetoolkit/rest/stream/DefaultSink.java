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


import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;


interface DefaultSink<T> extends Consumer<T> {
    default void begin(long size) {}

    default void end() {}

    default boolean cancellationRequested() {
        return false;
    }

    default void accept(int value) {
        throw new IllegalStateException("called wrong accept method");
    }

    default void accept(long value) {
        throw new IllegalStateException("called wrong accept method");
    }

    default void accept(double value) {
        throw new IllegalStateException("called wrong accept method");
    }

    interface OfInt extends DefaultSink<Integer>, IntConsumer {
        @Override
        void accept(int value);

        @Override
        default void accept (Integer i) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Sink.OfInt.accept(Integer)");
            accept(i.intValue());
        }
    }

    interface OfLong extends DefaultSink<Long>, LongConsumer {
        @Override
        void accept(long value);

        @Override
        default void accept(Long i) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Sink.OfLong.accept(Long)");
            accept(i.longValue());
        }
    }

    interface OfDouble extends DefaultSink<Double>, DoubleConsumer {
        @Override
        void accept(double value);

        @Override
        default void accept(Double i) {
            if (DefaultTripwire.ENABLED)
                DefaultTripwire.trip(getClass(), "{0} calling Sink.OfDouble.accept(Double)");
            accept(i.doubleValue());
        }
    }

    abstract class ChainedReference<T, E_OUT> implements DefaultSink<T> {
        protected final DefaultSink<? super E_OUT> downstream;

        public ChainedReference(DefaultSink<? super E_OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }

        @Override
        public void begin(long size) {
            downstream.begin(size);
        }

        @Override
        public void end() {
            downstream.end();
        }

        @Override
        public boolean cancellationRequested() {
            return downstream.cancellationRequested();
        }
    }

    abstract class ChainedInt<E_OUT> implements DefaultSink.OfInt {
        protected final DefaultSink<? super E_OUT> downstream;

        public ChainedInt(DefaultSink<? super E_OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }

        @Override
        public void begin(long size) {
            downstream.begin(size);
        }

        @Override
        public void end() {
            downstream.end();
        }

        @Override
        public boolean cancellationRequested() {
            return downstream.cancellationRequested();
        }
    }

    static abstract class ChainedLong<E_OUT> implements DefaultSink.OfLong {
        protected final DefaultSink<? super E_OUT> downstream;

        public ChainedLong(DefaultSink<? super E_OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }

        @Override
        public void begin(long size) {
            downstream.begin(size);
        }

        @Override
        public void end() {
            downstream.end();
        }

        @Override
        public boolean cancellationRequested() {
            return downstream.cancellationRequested();
        }
    }

    static abstract class ChainedDouble<E_OUT> implements DefaultSink.OfDouble {
        protected final DefaultSink<? super E_OUT> downstream;

        public ChainedDouble(DefaultSink<? super E_OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }

        @Override
        public void begin(long size) {
            downstream.begin(size);
        }

        @Override
        public void end() {
            downstream.end();
        }

        @Override
        public boolean cancellationRequested() {
            return downstream.cancellationRequested();
        }
    }
}
