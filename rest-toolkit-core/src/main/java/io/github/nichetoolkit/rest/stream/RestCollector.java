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

import io.github.nichetoolkit.rest.actuator.BiConsumerActuator;
import io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator;
import io.github.nichetoolkit.rest.actuator.FunctionActuator;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public interface RestCollector<T, A, R> {

    SupplierActuator<A> supplier();

    BiConsumerActuator<A, T> accumulator();

    BinaryOperatorActuator<A> combiner();

    FunctionActuator<A, R> finisher();

    Set<Characteristics> characteristics();

    static <T, R> RestCollector<T, R, R> of(SupplierActuator<R> supplier,
                                            BiConsumerActuator<R, T> accumulator,
                                            BinaryOperatorActuator<R> combiner,
                                            Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = (characteristics.length == 0)
                ? RestCollectors.CH_ID
                : Collections.unmodifiableSet(EnumSet.of(RestCollector.Characteristics.IDENTITY_FINISH,
                characteristics));
        return new RestCollectors.CollectorImpl<>(supplier, accumulator, combiner, cs);
    }

    static <T, A, R> RestCollector<T, A, R> of(SupplierActuator<A> supplier,
                                               BiConsumerActuator<A, T> accumulator,
                                               BinaryOperatorActuator<A> combiner,
                                               FunctionActuator<A, R> finisher,
                                               Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(finisher);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = RestCollectors.CH_NOID;
        if (characteristics.length > 0) {
            cs = EnumSet.noneOf(Characteristics.class);
            Collections.addAll(cs, characteristics);
            cs = Collections.unmodifiableSet(cs);
        }
        return new RestCollectors.CollectorImpl<>(supplier, accumulator, combiner, finisher, cs);
    }

    enum Characteristics {

        CONCURRENT,

        UNORDERED,

        IDENTITY_FINISH
    }
}
