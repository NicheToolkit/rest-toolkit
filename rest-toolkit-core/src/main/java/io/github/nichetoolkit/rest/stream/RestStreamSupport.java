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
import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.stream.*;

public final class RestStreamSupport {

    // Suppresses default constructor, ensuring non-instantiability.
    private RestStreamSupport() {}

    
    public static <T> RestStream<T> stream(Spliterator<T> spliterator, boolean parallel) {
        Objects.requireNonNull(spliterator);
        return new DefaultReferencePipeline.Head<>(spliterator,
                                            DefaultStreamOpFlag.fromCharacteristics(spliterator),
                                            parallel);
    }

    public static <T> RestStream<T> stream(Supplier<? extends Spliterator<T>> supplier,
                                       int characteristics,
                                       boolean parallel) {
        Objects.requireNonNull(supplier);
        return new DefaultReferencePipeline.Head<>(supplier,
                                            DefaultStreamOpFlag.fromCharacteristics(characteristics),
                                            parallel);
    }

//    public static IntStream intStream(Spliterator.OfInt spliterator, boolean parallel) {
//        return new DefaultIntPipeline.Head<>(spliterator,
//                                      DefaultStreamOpFlag.fromCharacteristics(spliterator),
//                                      parallel);
//    }
//
//    public static IntStream intStream(Supplier<? extends Spliterator.OfInt> supplier,
//                                      int characteristics,
//                                      boolean parallel) {
//        return new DefaultIntPipeline.Head<>(supplier,
//                                      DefaultStreamOpFlag.fromCharacteristics(characteristics),
//                                      parallel);
//    }
//
//    public static LongStream longStream(Spliterator.OfLong spliterator,
//                                        boolean parallel) {
//        return new DefaultLongPipeline.Head<>(spliterator,
//                                       DefaultStreamOpFlag.fromCharacteristics(spliterator),
//                                       parallel);
//    }
//
//    public static LongStream longStream(Supplier<? extends Spliterator.OfLong> supplier,
//                                        int characteristics,
//                                        boolean parallel) {
//        return new DefaultLongPipeline.Head<>(supplier,
//                                       DefaultStreamOpFlag.fromCharacteristics(characteristics),
//                                       parallel);
//    }
//
//    public static DoubleStream doubleStream(Spliterator.OfDouble spliterator,
//                                            boolean parallel) {
//        return new DefaultDoublePipeline.Head<>(spliterator,
//                                         DefaultStreamOpFlag.fromCharacteristics(spliterator),
//                                         parallel);
//    }
//
//    public static DoubleStream doubleStream(Supplier<? extends Spliterator.OfDouble> supplier,
//                                            int characteristics,
//                                            boolean parallel) {
//        return new DefaultDoublePipeline.Head<>(supplier,
//                                         DefaultStreamOpFlag.fromCharacteristics(characteristics),
//                                         parallel);
//    }
}
