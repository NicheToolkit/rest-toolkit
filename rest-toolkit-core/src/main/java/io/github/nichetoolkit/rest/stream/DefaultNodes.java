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
import java.util.concurrent.CountedCompleter;
import java.util.function.*;

/**
 * Factory methods for constructing implementations of {@link DefaultNode} and
 * {@link DefaultNode.Builder} and their primitive specializations.  Fork/Join tasks
 * for collecting output from a {@link DefaultPipelineHelper} to a {@link DefaultNode} and
 * flattening {@link DefaultNode}s.
 *
 * @since 1.8
 */
final class DefaultNodes {

    private DefaultNodes() {
        throw new Error("no instances");
    }

    /**
     * The maximum size of an array that can be allocated.
     */
    static final long MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    // IllegalArgumentException messages
    static final String BAD_SIZE = "Stream size exceeds max array size";

    @SuppressWarnings("rawtypes")
    private static final DefaultNode EMPTY_NODE = new EmptyDefaultNode.OfRef();
    private static final DefaultNode.OfInt EMPTY_INT_NODE = new EmptyDefaultNode.OfInt();
    private static final DefaultNode.OfLong EMPTY_LONG_NODE = new EmptyDefaultNode.OfLong();
    private static final DefaultNode.OfDouble EMPTY_DOUBLE_NODE = new EmptyDefaultNode.OfDouble();

    // General shape-based node creation methods

    /**
     * Produces an empty node whose count is zero, has no children and no content.
     *
     * @param <T> the type of elements of the created node
     * @param shape the shape of the node to be created
     * @return an empty node.
     */
    @SuppressWarnings("unchecked")
    static <T> DefaultNode<T> emptyDefaultNode(DefaultStreamShape shape) {
        switch (shape) {
            case REFERENCE:    return (DefaultNode<T>) EMPTY_NODE;
            case INT_VALUE:    return (DefaultNode<T>) EMPTY_INT_NODE;
            case LONG_VALUE:   return (DefaultNode<T>) EMPTY_LONG_NODE;
            case DOUBLE_VALUE: return (DefaultNode<T>) EMPTY_DOUBLE_NODE;
            default:
                throw new IllegalStateException("Unknown shape " + shape);
        }
    }

    /**
     * Produces a concatenated {@link DefaultNode} that has two or more children.
     * <p>The count of the concatenated node is equal to the sum of the count
     * of each child. Traversal of the concatenated node traverses the content
     * of each child in encounter order of the list of children. Splitting a
     * spliterator obtained from the concatenated node preserves the encounter
     * order of the list of children.
     *
     * <p>The result may be a concatenated node, the input sole node if the size
     * of the list is 1, or an empty node.
     *
     * @param <T> the type of elements of the concatenated node
     * @param shape the shape of the concatenated node to be created
     * @param left the left input node
     * @param right the right input node
     * @return a {@code DefaultNode} covering the elements of the input nodes
     * @throws IllegalStateException if all {@link DefaultNode} elements of the list
     * are an not instance of type supported by this factory.
     */
    @SuppressWarnings("unchecked")
    static <T> DefaultNode<T> conc(DefaultStreamShape shape, DefaultNode<T> left, DefaultNode<T> right) {
        switch (shape) {
            case REFERENCE:
                return new ConcDefaultNode<>(left, right);
            case INT_VALUE:
                return (DefaultNode<T>) new ConcDefaultNode.OfInt((DefaultNode.OfInt) left, (DefaultNode.OfInt) right);
            case LONG_VALUE:
                return (DefaultNode<T>) new ConcDefaultNode.OfLong((DefaultNode.OfLong) left, (DefaultNode.OfLong) right);
            case DOUBLE_VALUE:
                return (DefaultNode<T>) new ConcDefaultNode.OfDouble((DefaultNode.OfDouble) left, (DefaultNode.OfDouble) right);
            default:
                throw new IllegalStateException("Unknown shape " + shape);
        }
    }

    // Reference-based node methods

    /**
     * Produces a {@link DefaultNode} describing an array.
     *
     * <p>The node will hold a reference to the array and will not make a copy.
     *
     * @param <T> the type of elements held by the node
     * @param array the array
     * @return a node holding an array
     */
    static <T> DefaultNode<T> node(T[] array) {
        return new ArrayDefaultNode<>(array);
    }

    /**
     * Produces a {@link DefaultNode} describing a {@link Collection}.
     * <p>
     * The node will hold a reference to the collection and will not make a copy.
     *
     * @param <T> the type of elements held by the node
     * @param c the collection
     * @return a node holding a collection
     */
    static <T> DefaultNode<T> node(Collection<T> c) {
        return new CollectionDefaultNode<>(c);
    }

    /**
     * Produces a {@link DefaultNode.Builder}.
     *
     * @param exactSizeIfKnown -1 if a variable size builder is requested,
     * otherwise the exact capacity desired.  A fixed capacity builder will
     * fail if the wrong number of elements are added to the builder.
     * @param generator the array factory
     * @param <T> the type of elements of the node builder
     * @return a {@code DefaultNode.Builder}
     */
    static <T> DefaultNode.Builder<T> builder(long exactSizeIfKnown, IntFunction<T[]> generator) {
        return (exactSizeIfKnown >= 0 && exactSizeIfKnown < MAX_ARRAY_SIZE)
               ? new FixedDefaultNodeBuilder<>(exactSizeIfKnown, generator)
               : builder();
    }

    /**
     * Produces a variable size @{link DefaultNode.Builder}.
     *
     * @param <T> the type of elements of the node builder
     * @return a {@code DefaultNode.Builder}
     */
    static <T> DefaultNode.Builder<T> builder() {
        return new SpinedDefaultNodeBuilder<>();
    }

    // Int nodes

    /**
     * Produces a {@link DefaultNode.OfInt} describing an int[] array.
     *
     * <p>The node will hold a reference to the array and will not make a copy.
     *
     * @param array the array
     * @return a node holding an array
     */
    static DefaultNode.OfInt node(int[] array) {
        return new IntArrayDefaultNode(array);
    }

    /**
     * Produces a {@link DefaultNode.Builder.OfInt}.
     *
     * @param exactSizeIfKnown -1 if a variable size builder is requested,
     * otherwise the exact capacity desired.  A fixed capacity builder will
     * fail if the wrong number of elements are added to the builder.
     * @return a {@code DefaultNode.Builder.OfInt}
     */
    static DefaultNode.Builder.OfInt intBuilder(long exactSizeIfKnown) {
        return (exactSizeIfKnown >= 0 && exactSizeIfKnown < MAX_ARRAY_SIZE)
               ? new IntFixedDefaultNodeBuilder(exactSizeIfKnown)
               : intBuilder();
    }

    /**
     * Produces a variable size @{link DefaultNode.Builder.OfInt}.
     *
     * @return a {@code DefaultNode.Builder.OfInt}
     */
    static DefaultNode.Builder.OfInt intBuilder() {
        return new IntSpinedDefaultNodeBuilder();
    }

    // Long nodes

    /**
     * Produces a {@link DefaultNode.OfLong} describing a long[] array.
     * <p>
     * The node will hold a reference to the array and will not make a copy.
     *
     * @param array the array
     * @return a node holding an array
     */
    static DefaultNode.OfLong node(final long[] array) {
        return new LongArrayDefaultNode(array);
    }

    /**
     * Produces a {@link DefaultNode.Builder.OfLong}.
     *
     * @param exactSizeIfKnown -1 if a variable size builder is requested,
     * otherwise the exact capacity desired.  A fixed capacity builder will
     * fail if the wrong number of elements are added to the builder.
     * @return a {@code DefaultNode.Builder.OfLong}
     */
    static DefaultNode.Builder.OfLong longBuilder(long exactSizeIfKnown) {
        return (exactSizeIfKnown >= 0 && exactSizeIfKnown < MAX_ARRAY_SIZE)
               ? new LongFixedDefaultNodeBuilder(exactSizeIfKnown)
               : longBuilder();
    }

    /**
     * Produces a variable size @{link DefaultNode.Builder.OfLong}.
     *
     * @return a {@code DefaultNode.Builder.OfLong}
     */
    static DefaultNode.Builder.OfLong longBuilder() {
        return new LongSpinedDefaultNodeBuilder();
    }

    // Double nodes

    /**
     * Produces a {@link DefaultNode.OfDouble} describing a double[] array.
     *
     * <p>The node will hold a reference to the array and will not make a copy.
     *
     * @param array the array
     * @return a node holding an array
     */
    static DefaultNode.OfDouble node(final double[] array) {
        return new DoubleArrayDefaultNode(array);
    }

    /**
     * Produces a {@link DefaultNode.Builder.OfDouble}.
     *
     * @param exactSizeIfKnown -1 if a variable size builder is requested,
     * otherwise the exact capacity desired.  A fixed capacity builder will
     * fail if the wrong number of elements are added to the builder.
     * @return a {@code DefaultNode.Builder.OfDouble}
     */
    static DefaultNode.Builder.OfDouble doubleBuilder(long exactSizeIfKnown) {
        return (exactSizeIfKnown >= 0 && exactSizeIfKnown < MAX_ARRAY_SIZE)
               ? new DoubleFixedDefaultNodeBuilder(exactSizeIfKnown)
               : doubleBuilder();
    }

    /**
     * Produces a variable size @{link DefaultNode.Builder.OfDouble}.
     *
     * @return a {@code DefaultNode.Builder.OfDouble}
     */
    static DefaultNode.Builder.OfDouble doubleBuilder() {
        return new DoubleSpinedDefaultNodeBuilder();
    }

    // Parallel evaluation of pipelines to nodes

    /**
     * Collect, in parallel, elements output from a pipeline and describe those
     * elements with a {@link DefaultNode}.
     *
     * @implSpec
     * If the exact size of the output from the pipeline is known and the source
     * {@link Spliterator} has the {@link Spliterator#SUBSIZED} characteristic,
     * then a flat {@link DefaultNode} will be returned whose content is an array,
     * since the size is known the array can be constructed in advance and
     * output elements can be placed into the array concurrently by leaf
     * tasks at the correct offsets.  If the exact size is not known, output
     * elements are collected into a conc-node whose shape mirrors that
     * of the computation. This conc-node can then be flattened in
     * parallel to produce a flat {@code DefaultNode} if desired.
     *
     * @param helper the pipeline helper describing the pipeline
     * @param flattenTree whether a conc node should be flattened into a node
     *                    describing an array before returning
     * @param generator the array generator
     * @return a {@link DefaultNode} describing the output elements
     */
    public static <P_IN, P_OUT> DefaultNode<P_OUT> collect(DefaultPipelineHelper<P_OUT> helper,
                                                           Spliterator<P_IN> spliterator,
                                                           boolean flattenTree,
                                                           IntFunction<P_OUT[]> generator) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size >= 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            P_OUT[] array = generator.apply((int) size);
            new SizedCollectorTask.OfRef<>(spliterator, helper, array).invoke();
            return node(array);
        } else {
            DefaultNode<P_OUT> node = new CollectorTask.OfRef<>(helper, generator, spliterator).invoke();
            return flattenTree ? flatten(node, generator) : node;
        }
    }

    /**
     * Collect, in parallel, elements output from an int-valued pipeline and
     * describe those elements with a {@link DefaultNode.OfInt}.
     *
     * @implSpec
     * If the exact size of the output from the pipeline is known and the source
     * {@link Spliterator} has the {@link Spliterator#SUBSIZED} characteristic,
     * then a flat {@link DefaultNode} will be returned whose content is an array,
     * since the size is known the array can be constructed in advance and
     * output elements can be placed into the array concurrently by leaf
     * tasks at the correct offsets.  If the exact size is not known, output
     * elements are collected into a conc-node whose shape mirrors that
     * of the computation. This conc-node can then be flattened in
     * parallel to produce a flat {@code DefaultNode.OfInt} if desired.
     *
     * @param <P_IN> the type of elements from the source Spliterator
     * @param helper the pipeline helper describing the pipeline
     * @param flattenTree whether a conc node should be flattened into a node
     *                    describing an array before returning
     * @return a {@link DefaultNode.OfInt} describing the output elements
     */
    public static <P_IN> DefaultNode.OfInt collectInt(DefaultPipelineHelper<Integer> helper,
                                                      Spliterator<P_IN> spliterator,
                                                      boolean flattenTree) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size >= 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            int[] array = new int[(int) size];
            new SizedCollectorTask.OfInt<>(spliterator, helper, array).invoke();
            return node(array);
        }
        else {
            DefaultNode.OfInt node = new CollectorTask.OfInt<>(helper, spliterator).invoke();
            return flattenTree ? flattenInt(node) : node;
        }
    }

    /**
     * Collect, in parallel, elements output from a long-valued pipeline and
     * describe those elements with a {@link DefaultNode.OfLong}.
     *
     * @implSpec
     * If the exact size of the output from the pipeline is known and the source
     * {@link Spliterator} has the {@link Spliterator#SUBSIZED} characteristic,
     * then a flat {@link DefaultNode} will be returned whose content is an array,
     * since the size is known the array can be constructed in advance and
     * output elements can be placed into the array concurrently by leaf
     * tasks at the correct offsets.  If the exact size is not known, output
     * elements are collected into a conc-node whose shape mirrors that
     * of the computation. This conc-node can then be flattened in
     * parallel to produce a flat {@code DefaultNode.OfLong} if desired.
     *
     * @param <P_IN> the type of elements from the source Spliterator
     * @param helper the pipeline helper describing the pipeline
     * @param flattenTree whether a conc node should be flattened into a node
     *                    describing an array before returning
     * @return a {@link DefaultNode.OfLong} describing the output elements
     */
    public static <P_IN> DefaultNode.OfLong collectLong(DefaultPipelineHelper<Long> helper,
                                                        Spliterator<P_IN> spliterator,
                                                        boolean flattenTree) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size >= 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            long[] array = new long[(int) size];
            new SizedCollectorTask.OfLong<>(spliterator, helper, array).invoke();
            return node(array);
        }
        else {
            DefaultNode.OfLong node = new CollectorTask.OfLong<>(helper, spliterator).invoke();
            return flattenTree ? flattenLong(node) : node;
        }
    }

    /**
     * Collect, in parallel, elements output from n double-valued pipeline and
     * describe those elements with a {@link DefaultNode.OfDouble}.
     *
     * @implSpec
     * If the exact size of the output from the pipeline is known and the source
     * {@link Spliterator} has the {@link Spliterator#SUBSIZED} characteristic,
     * then a flat {@link DefaultNode} will be returned whose content is an array,
     * since the size is known the array can be constructed in advance and
     * output elements can be placed into the array concurrently by leaf
     * tasks at the correct offsets.  If the exact size is not known, output
     * elements are collected into a conc-node whose shape mirrors that
     * of the computation. This conc-node can then be flattened in
     * parallel to produce a flat {@code DefaultNode.OfDouble} if desired.
     *
     * @param <P_IN> the type of elements from the source Spliterator
     * @param helper the pipeline helper describing the pipeline
     * @param flattenTree whether a conc node should be flattened into a node
     *                    describing an array before returning
     * @return a {@link DefaultNode.OfDouble} describing the output elements
     */
    public static <P_IN> DefaultNode.OfDouble collectDouble(DefaultPipelineHelper<Double> helper,
                                                            Spliterator<P_IN> spliterator,
                                                            boolean flattenTree) {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size >= 0 && spliterator.hasCharacteristics(Spliterator.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            double[] array = new double[(int) size];
            new SizedCollectorTask.OfDouble<>(spliterator, helper, array).invoke();
            return node(array);
        }
        else {
            DefaultNode.OfDouble node = new CollectorTask.OfDouble<>(helper, spliterator).invoke();
            return flattenTree ? flattenDouble(node) : node;
        }
    }

//     Parallel flattening of nodes

    /**
     * Flatten, in parallel, a {@link DefaultNode}.  A flattened node is one that has
     * no children.  If the node is already flat, it is simply returned.
     *
     * @implSpec
     * If a new node is to be created, the generator is used to create an array
     * whose length is {@link DefaultNode#count()}.  Then the node tree is traversed
     * and leaf node elements are placed in the array concurrently by leaf tasks
     * at the correct offsets.
     *
     * @param <T> type of elements contained by the node
     * @param node the node to flatten
     * @param generator the array factory used to create array instances
     * @return a flat {@code DefaultNode}
     */
    public static <T> DefaultNode<T> flatten(DefaultNode<T> node, IntFunction<T[]> generator) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            T[] array = generator.apply((int) size);
            new ToArrayTask.OfRef<>(node, array, 0).invoke();
            return node(array);
        } else {
            return node;
        }
    }

    /**
     * Flatten, in parallel, a {@link DefaultNode.OfInt}.  A flattened node is one that
     * has no children.  If the node is already flat, it is simply returned.
     *
     * @implSpec
     * If a new node is to be created, a new int[] array is created whose length
     * is {@link DefaultNode#count()}.  Then the node tree is traversed and leaf node
     * elements are placed in the array concurrently by leaf tasks at the
     * correct offsets.
     *
     * @param node the node to flatten
     * @return a flat {@code DefaultNode.OfInt}
     */
    public static DefaultNode.OfInt flattenInt(DefaultNode.OfInt node) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            int[] array = new int[(int) size];
            new ToArrayTask.OfInt(node, array, 0).invoke();
            return node(array);
        } else {
            return node;
        }
    }

    /**
     * Flatten, in parallel, a {@link DefaultNode.OfLong}.  A flattened node is one that
     * has no children.  If the node is already flat, it is simply returned.
     *
     * @implSpec
     * If a new node is to be created, a new long[] array is created whose length
     * is {@link DefaultNode#count()}.  Then the node tree is traversed and leaf node
     * elements are placed in the array concurrently by leaf tasks at the
     * correct offsets.
     *
     * @param node the node to flatten
     * @return a flat {@code DefaultNode.OfLong}
     */
    public static DefaultNode.OfLong flattenLong(DefaultNode.OfLong node) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            long[] array = new long[(int) size];
            new ToArrayTask.OfLong(node, array, 0).invoke();
            return node(array);
        } else {
            return node;
        }
    }

    /**
     * Flatten, in parallel, a {@link DefaultNode.OfDouble}.  A flattened node is one that
     * has no children.  If the node is already flat, it is simply returned.
     *
     * @implSpec
     * If a new node is to be created, a new double[] array is created whose length
     * is {@link DefaultNode#count()}.  Then the node tree is traversed and leaf node
     * elements are placed in the array concurrently by leaf tasks at the
     * correct offsets.
     *
     * @param node the node to flatten
     * @return a flat {@code DefaultNode.OfDouble}
     */
    public static DefaultNode.OfDouble flattenDouble(DefaultNode.OfDouble node) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            double[] array = new double[(int) size];
            new ToArrayTask.OfDouble(node, array, 0).invoke();
            return node(array);
        } else {
            return node;
        }
    }

//     Implementations

    private static abstract class EmptyDefaultNode<T, T_ARR, T_CONS> implements DefaultNode<T> {
        EmptyDefaultNode() { }

        @Override
        public T[] asArray(IntFunction<T[]> generator) {
            return generator.apply(0);
        }

        public void copyInto(T_ARR array, int offset) { }

        @Override
        public long count() {
            return 0;
        }

        public void forEach(T_CONS consumer) { }

        private static class OfRef<T> extends DefaultNodes.EmptyDefaultNode<T, T[], Consumer<? super T>> {
            private OfRef() {
                super();
            }

            @Override
            public Spliterator<T> spliterator() {
                return Spliterators.emptySpliterator();
            }
        }

        private static final class OfInt
                extends DefaultNodes.EmptyDefaultNode<Integer, int[], IntConsumer>
                implements DefaultNode.OfInt {

            OfInt() { } // Avoid creation of special accessor

            @Override
            public Spliterator.OfInt spliterator() {
                return Spliterators.emptyIntSpliterator();
            }

            @Override
            public int[] asPrimitiveArray() {
                return EMPTY_INT_ARRAY;
            }
        }

        private static final class OfLong
                extends DefaultNodes.EmptyDefaultNode<Long, long[], LongConsumer>
                implements DefaultNode.OfLong {

            OfLong() { } // Avoid creation of special accessor

            @Override
            public Spliterator.OfLong spliterator() {
                return Spliterators.emptyLongSpliterator();
            }

            @Override
            public long[] asPrimitiveArray() {
                return EMPTY_LONG_ARRAY;
            }
        }

        private static final class OfDouble
                extends DefaultNodes.EmptyDefaultNode<Double, double[], DoubleConsumer>
                implements DefaultNode.OfDouble {

            OfDouble() { } // Avoid creation of special accessor

            @Override
            public Spliterator.OfDouble spliterator() {
                return Spliterators.emptyDoubleSpliterator();
            }

            @Override
            public double[] asPrimitiveArray() {
                return EMPTY_DOUBLE_ARRAY;
            }
        }
    }

    /** DefaultNode class for a reference array */
    private static class ArrayDefaultNode<T> implements DefaultNode<T> {
        final T[] array;
        int curSize;

        @SuppressWarnings("unchecked")
        ArrayDefaultNode(long size, IntFunction<T[]> generator) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            this.array = generator.apply((int) size);
            this.curSize = 0;
        }

        ArrayDefaultNode(T[] array) {
            this.array = array;
            this.curSize = array.length;
        }

        // DefaultNode

        @Override
        public Spliterator<T> spliterator() {
            return Arrays.spliterator(array, 0, curSize);
        }

        @Override
        public void copyInto(T[] dest, int destOffset) {
            System.arraycopy(array, 0, dest, destOffset, curSize);
        }

        @Override
        public T[] asArray(IntFunction<T[]> generator) {
            if (array.length == curSize) {
                return array;
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEach(Consumer<? super T> consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.accept(array[i]);
            }
        }

        //

        @Override
        public String toString() {
            return String.format("ArrayDefaultNode[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    /** DefaultNode class for a Collection */
    private static final class CollectionDefaultNode<T> implements DefaultNode<T> {
        private final Collection<T> c;

        CollectionDefaultNode(Collection<T> c) {
            this.c = c;
        }

        // DefaultNode

        @Override
        public Spliterator<T> spliterator() {
            return c.stream().spliterator();
        }

        @Override
        public void copyInto(T[] array, int offset) {
            for (T t : c)
                array[offset++] = t;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T[] asArray(IntFunction<T[]> generator) {
            return c.toArray(generator.apply(c.size()));
        }

        @Override
        public long count() {
            return c.size();
        }

        @Override
        public void forEach(Consumer<? super T> consumer) {
            c.forEach(consumer);
        }

        //

        @Override
        public String toString() {
            return String.format("CollectionDefaultNode[%d][%s]", c.size(), c);
        }
    }

    /**
     * DefaultNode class for an internal node with two or more children
     */
    private static abstract class AbstractConcDefaultNode<T, T_NODE extends DefaultNode<T>> implements DefaultNode<T> {
        protected final T_NODE left;
        protected final T_NODE right;
        private final long size;

        AbstractConcDefaultNode(T_NODE left, T_NODE right) {
            this.left = left;
            this.right = right;
            // The DefaultNode count will be required when the DefaultNode spliterator is
            // obtained and it is cheaper to aggressively calculate bottom up
            // as the tree is built rather than later on from the top down
            // traversing the tree
            this.size = left.count() + right.count();
        }

        @Override
        public int getChildCount() {
            return 2;
        }

        @Override
        public T_NODE getChild(int i) {
            if (i == 0) return left;
            if (i == 1) return right;
            throw new IndexOutOfBoundsException();
        }

        @Override
        public long count() {
            return size;
        }
    }

    static final class ConcDefaultNode<T>
            extends AbstractConcDefaultNode<T, DefaultNode<T>>
            implements DefaultNode<T> {

        ConcDefaultNode(DefaultNode<T> left, DefaultNode<T> right) {
            super(left, right);
        }

        @Override
        public Spliterator<T> spliterator() {
            return new DefaultNodes.InternalDefaultNodeSpliterator.OfRef<>(this);
        }

        @Override
        public void copyInto(T[] array, int offset) {
            Objects.requireNonNull(array);
            left.copyInto(array, offset);
            // Cast to int is safe since it is the callers responsibility to
            // ensure that there is sufficient room in the array
            right.copyInto(array, offset + (int) left.count());
        }

        @Override
        public T[] asArray(IntFunction<T[]> generator) {
            long size = count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            T[] array = generator.apply((int) size);
            copyInto(array, 0);
            return array;
        }

        @Override
        public void forEach(Consumer<? super T> consumer) {
            left.forEach(consumer);
            right.forEach(consumer);
        }

        @Override
        public DefaultNode<T> truncate(long from, long to, IntFunction<T[]> generator) {
            if (from == 0 && to == count())
                return this;
            long leftCount = left.count();
            if (from >= leftCount)
                return right.truncate(from - leftCount, to - leftCount, generator);
            else if (to <= leftCount)
                return left.truncate(from, to, generator);
            else {
                return DefaultNodes.conc(getShape(), left.truncate(from, leftCount, generator),
                                  right.truncate(0, to - leftCount, generator));
            }
        }

        @Override
        public String toString() {
            if (count() < 32) {
                return String.format("ConcDefaultNode[%s.%s]", left, right);
            } else {
                return String.format("ConcDefaultNode[size=%d]", count());
            }
        }

        private abstract static class OfPrimitive<E, T_CONS, T_ARR,
                                                  T_SPLITR extends Spliterator.OfPrimitive<E, T_CONS, T_SPLITR>,
                                                  T_NODE extends DefaultNode.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE>>
                extends AbstractConcDefaultNode<E, T_NODE>
                implements DefaultNode.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE> {

            OfPrimitive(T_NODE left, T_NODE right) {
                super(left, right);
            }

            @Override
            public void forEach(T_CONS consumer) {
                left.forEach(consumer);
                right.forEach(consumer);
            }

            @Override
            public void copyInto(T_ARR array, int offset) {
                left.copyInto(array, offset);
                // Cast to int is safe since it is the callers responsibility to
                // ensure that there is sufficient room in the array
                right.copyInto(array, offset + (int) left.count());
            }

            @Override
            public T_ARR asPrimitiveArray() {
                long size = count();
                if (size >= MAX_ARRAY_SIZE)
                    throw new IllegalArgumentException(BAD_SIZE);
                T_ARR array = newArray((int) size);
                copyInto(array, 0);
                return array;
            }

            @Override
            public String toString() {
                if (count() < 32)
                    return String.format("%s[%s.%s]", this.getClass().getName(), left, right);
                else
                    return String.format("%s[size=%d]", this.getClass().getName(), count());
            }
        }

        static final class OfInt
                extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, DefaultNode.OfInt>
                implements DefaultNode.OfInt {

            OfInt(OfInt left, OfInt right) {
                super(left, right);
            }

            @Override
            public Spliterator.OfInt spliterator() {
                return new InternalDefaultNodeSpliterator.OfInt(this);
            }
        }

        static final class OfLong
                extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, DefaultNode.OfLong>
                implements DefaultNode.OfLong {

            OfLong(OfLong left, OfLong right) {
                super(left, right);
            }

            @Override
            public Spliterator.OfLong spliterator() {
                return new InternalDefaultNodeSpliterator.OfLong(this);
            }
        }

        static final class OfDouble
                extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, DefaultNode.OfDouble>
                implements DefaultNode.OfDouble {

            OfDouble(OfDouble left, OfDouble right) {
                super(left, right);
            }

            @Override
            public Spliterator.OfDouble spliterator() {
                return new InternalDefaultNodeSpliterator.OfDouble(this);
            }
        }
    }

    /** Abstract class for spliterator for all internal node classes */
    private static abstract class InternalDefaultNodeSpliterator<T,
                                                          S extends Spliterator<T>,
                                                          N extends DefaultNode<T>>
            implements Spliterator<T> {
        // DefaultNode we are pointing to
        // null if full traversal has occurred
        N curDefaultNode;

        // next child of curDefaultNode to consume
        int curChildIndex;

        // The spliterator of the curDefaultNode if that node is last and has no children.
        // This spliterator will be delegated to for splitting and traversing.
        // null if curDefaultNode has children
        S lastDefaultNodeSpliterator;

        // spliterator used while traversing with tryAdvance
        // null if no partial traversal has occurred
        S tryAdvanceSpliterator;

        // node stack used when traversing to search and find leaf nodes
        // null if no partial traversal has occurred
        Deque<N> tryAdvanceStack;

        InternalDefaultNodeSpliterator(N curDefaultNode) {
            this.curDefaultNode = curDefaultNode;
        }

        /**
         * Initiate a stack containing, in left-to-right order, the child nodes
         * covered by this spliterator
         */
        @SuppressWarnings("unchecked")
        protected final Deque<N> initStack() {
            // Bias size to the case where leaf nodes are close to this node
            // 8 is the minimum initial capacity for the ArrayDeque implementation
            Deque<N> stack = new ArrayDeque<>(8);
            for (int i = curDefaultNode.getChildCount() - 1; i >= curChildIndex; i--)
                stack.addFirst((N) curDefaultNode.getChild(i));
            return stack;
        }

        /**
         * Depth first search, in left-to-right order, of the node tree, using
         * an explicit stack, to find the next non-empty leaf node.
         */
        @SuppressWarnings("unchecked")
        protected final N findNextLeafDefaultNode(Deque<N> stack) {
            N n;
            while ((n = stack.pollFirst()) != null) {
                if (n.getChildCount() == 0) {
                    if (n.count() > 0)
                        return n;
                } else {
                    for (int i = n.getChildCount() - 1; i >= 0; i--)
                        stack.addFirst((N) n.getChild(i));
                }
            }

            return null;
        }

        @SuppressWarnings("unchecked")
        protected final boolean initTryAdvance() {
            if (curDefaultNode == null)
                return false;

            if (tryAdvanceSpliterator == null) {
                if (lastDefaultNodeSpliterator == null) {
                    // Initiate the node stack
                    tryAdvanceStack = initStack();
                    N leaf = findNextLeafDefaultNode(tryAdvanceStack);
                    if (leaf != null)
                        tryAdvanceSpliterator = (S) leaf.spliterator();
                    else {
                        // A non-empty leaf node was not found
                        // No elements to traverse
                        curDefaultNode = null;
                        return false;
                    }
                }
                else
                    tryAdvanceSpliterator = lastDefaultNodeSpliterator;
            }
            return true;
        }

        @Override
        @SuppressWarnings("unchecked")
        public final S trySplit() {
            if (curDefaultNode == null || tryAdvanceSpliterator != null)
                return null; // Cannot split if fully or partially traversed
            else if (lastDefaultNodeSpliterator != null)
                return (S) lastDefaultNodeSpliterator.trySplit();
            else if (curChildIndex < curDefaultNode.getChildCount() - 1)
                return (S) curDefaultNode.getChild(curChildIndex++).spliterator();
            else {
                curDefaultNode = (N) curDefaultNode.getChild(curChildIndex);
                if (curDefaultNode.getChildCount() == 0) {
                    lastDefaultNodeSpliterator = (S) curDefaultNode.spliterator();
                    return (S) lastDefaultNodeSpliterator.trySplit();
                }
                else {
                    curChildIndex = 0;
                    return (S) curDefaultNode.getChild(curChildIndex++).spliterator();
                }
            }
        }

        @Override
        public final long estimateSize() {
            if (curDefaultNode == null)
                return 0;

            // Will not reflect the effects of partial traversal.
            // This is compliant with the specification
            if (lastDefaultNodeSpliterator != null)
                return lastDefaultNodeSpliterator.estimateSize();
            else {
                long size = 0;
                for (int i = curChildIndex; i < curDefaultNode.getChildCount(); i++)
                    size += curDefaultNode.getChild(i).count();
                return size;
            }
        }

        @Override
        public final int characteristics() {
            return Spliterator.SIZED;
        }

        private static final class OfRef<T>
                extends DefaultNodes.InternalDefaultNodeSpliterator<T, Spliterator<T>, DefaultNode<T>> {

            OfRef(DefaultNode<T> curDefaultNode) {
                super(curDefaultNode);
            }

            @Override
            public boolean tryAdvance(Consumer<? super T> consumer) {
                if (!initTryAdvance())
                    return false;

                boolean hasNext = tryAdvanceSpliterator.tryAdvance(consumer);
                if (!hasNext) {
                    if (lastDefaultNodeSpliterator == null) {
                        // Advance to the spliterator of the next non-empty leaf node
                        DefaultNode<T> leaf = findNextLeafDefaultNode(tryAdvanceStack);
                        if (leaf != null) {
                            tryAdvanceSpliterator = leaf.spliterator();
                            // Since the node is not-empty the spliterator can be advanced
                            return tryAdvanceSpliterator.tryAdvance(consumer);
                        }
                    }
                    // No more elements to traverse
                    curDefaultNode = null;
                }
                return hasNext;
            }

            @Override
            public void forEachRemaining(Consumer<? super T> consumer) {
                if (curDefaultNode == null)
                    return;

                if (tryAdvanceSpliterator == null) {
                    if (lastDefaultNodeSpliterator == null) {
                        Deque<DefaultNode<T>> stack = initStack();
                        DefaultNode<T> leaf;
                        while ((leaf = findNextLeafDefaultNode(stack)) != null) {
                            leaf.forEach(consumer);
                        }
                        curDefaultNode = null;
                    }
                    else
                        lastDefaultNodeSpliterator.forEachRemaining(consumer);
                }
                else
                    while(tryAdvance(consumer)) { }
            }
        }

        private static abstract class OfPrimitive<T, T_CONS, T_ARR,
                                                  T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
                                                  N extends DefaultNode.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, N>>
                extends DefaultNodes.InternalDefaultNodeSpliterator<T, T_SPLITR, N>
                implements Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> {

            OfPrimitive(N cur) {
                super(cur);
            }

            @Override
            public boolean tryAdvance(T_CONS consumer) {
                if (!initTryAdvance())
                    return false;

                boolean hasNext = tryAdvanceSpliterator.tryAdvance(consumer);
                if (!hasNext) {
                    if (lastDefaultNodeSpliterator == null) {
                        // Advance to the spliterator of the next non-empty leaf node
                        N leaf = findNextLeafDefaultNode(tryAdvanceStack);
                        if (leaf != null) {
                            tryAdvanceSpliterator = leaf.spliterator();
                            // Since the node is not-empty the spliterator can be advanced
                            return tryAdvanceSpliterator.tryAdvance(consumer);
                        }
                    }
                    // No more elements to traverse
                    curDefaultNode = null;
                }
                return hasNext;
            }

            @Override
            public void forEachRemaining(T_CONS consumer) {
                if (curDefaultNode == null)
                    return;

                if (tryAdvanceSpliterator == null) {
                    if (lastDefaultNodeSpliterator == null) {
                        Deque<N> stack = initStack();
                        N leaf;
                        while ((leaf = findNextLeafDefaultNode(stack)) != null) {
                            leaf.forEach(consumer);
                        }
                        curDefaultNode = null;
                    }
                    else
                        lastDefaultNodeSpliterator.forEachRemaining(consumer);
                }
                else
                    while(tryAdvance(consumer)) { }
            }
        }

        private static final class OfInt
                extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, DefaultNode.OfInt>
                implements Spliterator.OfInt {

            OfInt(DefaultNode.OfInt cur) {
                super(cur);
            }
        }

        private static final class OfLong
                extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, DefaultNode.OfLong>
                implements Spliterator.OfLong {

            OfLong(DefaultNode.OfLong cur) {
                super(cur);
            }
        }

        private static final class OfDouble
                extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, DefaultNode.OfDouble>
                implements Spliterator.OfDouble {

            OfDouble(DefaultNode.OfDouble cur) {
                super(cur);
            }
        }
    }

    /**
     * Fixed-sized builder class for reference nodes
     */
    private static final class FixedDefaultNodeBuilder<T>
            extends ArrayDefaultNode<T>
            implements DefaultNode.Builder<T> {

        FixedDefaultNodeBuilder(long size, IntFunction<T[]> generator) {
            super(size, generator);
            assert size < MAX_ARRAY_SIZE;
        }

        @Override
        public DefaultNode<T> build() {
            if (curSize < array.length)
                throw new IllegalStateException(String.format("Current size %d is less than fixed size %d",
                                                              curSize, array.length));
            return this;
        }

        @Override
        public void begin(long size) {
            if (size != array.length)
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d",
                                                              size, array.length));
            curSize = 0;
        }

        @Override
        public void accept(T t) {
            if (curSize < array.length) {
                array[curSize++] = t;
            } else {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d",
                                                              array.length));
            }
        }

        @Override
        public void end() {
            if (curSize < array.length)
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d",
                                                              curSize, array.length));
        }

        @Override
        public String toString() {
            return String.format("FixedDefaultNodeBuilder[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    /**
     * Variable-sized builder class for reference nodes
     */
    private static final class SpinedDefaultNodeBuilder<T>
            extends DefaultSpinedBuffer<T>
            implements DefaultNode<T>, DefaultNode.Builder<T> {
        private boolean building = false;

        SpinedDefaultNodeBuilder() {} // Avoid creation of special accessor

        @Override
        public Spliterator<T> spliterator() {
            assert !building : "during building";
            return super.spliterator();
        }

        @Override
        public void forEach(Consumer<? super T> consumer) {
            assert !building : "during building";
            super.forEach(consumer);
        }

        //
        @Override
        public void begin(long size) {
            assert !building : "was already building";
            building = true;
            clear();
            ensureCapacity(size);
        }

        @Override
        public void accept(T t) {
            assert building : "not building";
            super.accept(t);
        }

        @Override
        public void end() {
            assert building : "was not building";
            building = false;
            // @@@ check begin(size) and size
        }

        @Override
        public void copyInto(T[] array, int offset) {
            assert !building : "during building";
            super.copyInto(array, offset);
        }

        @Override
        public T[] asArray(IntFunction<T[]> arrayFactory) {
            assert !building : "during building";
            return super.asArray(arrayFactory);
        }

        @Override
        public DefaultNode<T> build() {
            assert !building : "during building";
            return this;
        }
    }

    //

    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

    private static class IntArrayDefaultNode implements DefaultNode.OfInt {
        final int[] array;
        int curSize;

        IntArrayDefaultNode(long size) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            this.array = new int[(int) size];
            this.curSize = 0;
        }

        IntArrayDefaultNode(int[] array) {
            this.array = array;
            this.curSize = array.length;
        }

        // DefaultNode

        @Override
        public Spliterator.OfInt spliterator() {
            return Arrays.spliterator(array, 0, curSize);
        }

        @Override
        public int[] asPrimitiveArray() {
            if (array.length == curSize) {
                return array;
            } else {
                return Arrays.copyOf(array, curSize);
            }
        }

        @Override
        public void copyInto(int[] dest, int destOffset) {
            System.arraycopy(array, 0, dest, destOffset, curSize);
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEach(IntConsumer consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.accept(array[i]);
            }
        }

        @Override
        public String toString() {
            return String.format("IntArrayDefaultNode[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    private static class LongArrayDefaultNode implements DefaultNode.OfLong {
        final long[] array;
        int curSize;

        LongArrayDefaultNode(long size) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            this.array = new long[(int) size];
            this.curSize = 0;
        }

        LongArrayDefaultNode(long[] array) {
            this.array = array;
            this.curSize = array.length;
        }

        @Override
        public Spliterator.OfLong spliterator() {
            return Arrays.spliterator(array, 0, curSize);
        }

        @Override
        public long[] asPrimitiveArray() {
            if (array.length == curSize) {
                return array;
            } else {
                return Arrays.copyOf(array, curSize);
            }
        }

        @Override
        public void copyInto(long[] dest, int destOffset) {
            System.arraycopy(array, 0, dest, destOffset, curSize);
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEach(LongConsumer consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.accept(array[i]);
            }
        }

        @Override
        public String toString() {
            return String.format("LongArrayDefaultNode[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    private static class DoubleArrayDefaultNode implements DefaultNode.OfDouble {
        final double[] array;
        int curSize;

        DoubleArrayDefaultNode(long size) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegalArgumentException(BAD_SIZE);
            this.array = new double[(int) size];
            this.curSize = 0;
        }

        DoubleArrayDefaultNode(double[] array) {
            this.array = array;
            this.curSize = array.length;
        }

        @Override
        public Spliterator.OfDouble spliterator() {
            return Arrays.spliterator(array, 0, curSize);
        }

        @Override
        public double[] asPrimitiveArray() {
            if (array.length == curSize) {
                return array;
            } else {
                return Arrays.copyOf(array, curSize);
            }
        }

        @Override
        public void copyInto(double[] dest, int destOffset) {
            System.arraycopy(array, 0, dest, destOffset, curSize);
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEach(DoubleConsumer consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.accept(array[i]);
            }
        }

        @Override
        public String toString() {
            return String.format("DoubleArrayDefaultNode[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    private static final class IntFixedDefaultNodeBuilder
            extends IntArrayDefaultNode
            implements DefaultNode.Builder.OfInt {

        IntFixedDefaultNodeBuilder(long size) {
            super(size);
            assert size < MAX_ARRAY_SIZE;
        }

        @Override
        public DefaultNode.OfInt build() {
            if (curSize < array.length) {
                throw new IllegalStateException(String.format("Current size %d is less than fixed size %d",
                                                              curSize, array.length));
            }

            return this;
        }

        @Override
        public void begin(long size) {
            if (size != array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d",
                                                              size, array.length));
            }

            curSize = 0;
        }

        @Override
        public void accept(int i) {
            if (curSize < array.length) {
                array[curSize++] = i;
            } else {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d",
                                                              array.length));
            }
        }

        @Override
        public void end() {
            if (curSize < array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d",
                                                              curSize, array.length));
            }
        }

        @Override
        public String toString() {
            return String.format("IntFixedDefaultNodeBuilder[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    private static final class LongFixedDefaultNodeBuilder
            extends LongArrayDefaultNode
            implements DefaultNode.Builder.OfLong {

        LongFixedDefaultNodeBuilder(long size) {
            super(size);
            assert size < MAX_ARRAY_SIZE;
        }

        @Override
        public DefaultNode.OfLong build() {
            if (curSize < array.length) {
                throw new IllegalStateException(String.format("Current size %d is less than fixed size %d",
                                                              curSize, array.length));
            }

            return this;
        }

        @Override
        public void begin(long size) {
            if (size != array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d",
                                                              size, array.length));
            }

            curSize = 0;
        }

        @Override
        public void accept(long i) {
            if (curSize < array.length) {
                array[curSize++] = i;
            } else {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d",
                                                              array.length));
            }
        }

        @Override
        public void end() {
            if (curSize < array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d",
                                                              curSize, array.length));
            }
        }

        @Override
        public String toString() {
            return String.format("LongFixedDefaultNodeBuilder[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    private static final class DoubleFixedDefaultNodeBuilder
            extends DoubleArrayDefaultNode
            implements DefaultNode.Builder.OfDouble {

        DoubleFixedDefaultNodeBuilder(long size) {
            super(size);
            assert size < MAX_ARRAY_SIZE;
        }

        @Override
        public DefaultNode.OfDouble build() {
            if (curSize < array.length) {
                throw new IllegalStateException(String.format("Current size %d is less than fixed size %d",
                                                              curSize, array.length));
            }

            return this;
        }

        @Override
        public void begin(long size) {
            if (size != array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d",
                                                              size, array.length));
            }

            curSize = 0;
        }

        @Override
        public void accept(double i) {
            if (curSize < array.length) {
                array[curSize++] = i;
            } else {
                throw new IllegalStateException(String.format("Accept exceeded fixed size of %d",
                                                              array.length));
            }
        }

        @Override
        public void end() {
            if (curSize < array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d",
                                                              curSize, array.length));
            }
        }

        @Override
        public String toString() {
            return String.format("DoubleFixedDefaultNodeBuilder[%d][%s]",
                                 array.length - curSize, Arrays.toString(array));
        }
    }

    private static final class IntSpinedDefaultNodeBuilder
            extends DefaultSpinedBuffer.OfInt
            implements DefaultNode.OfInt, DefaultNode.Builder.OfInt {
        private boolean building = false;

        IntSpinedDefaultNodeBuilder() {} // Avoid creation of special accessor

        @Override
        public Spliterator.OfInt spliterator() {
            assert !building : "during building";
            return super.spliterator();
        }

        @Override
        public void forEach(IntConsumer consumer) {
            assert !building : "during building";
            super.forEach(consumer);
        }

        //
        @Override
        public void begin(long size) {
            assert !building : "was already building";
            building = true;
            clear();
            ensureCapacity(size);
        }

        @Override
        public void accept(int i) {
            assert building : "not building";
            super.accept(i);
        }

        @Override
        public void end() {
            assert building : "was not building";
            building = false;
            // @@@ check begin(size) and size
        }

        @Override
        public void copyInto(int[] array, int offset) throws IndexOutOfBoundsException {
            assert !building : "during building";
            super.copyInto(array, offset);
        }

        @Override
        public int[] asPrimitiveArray() {
            assert !building : "during building";
            return super.asPrimitiveArray();
        }

        @Override
        public DefaultNode.OfInt build() {
            assert !building : "during building";
            return this;
        }
    }

    private static final class LongSpinedDefaultNodeBuilder
            extends DefaultSpinedBuffer.OfLong
            implements DefaultNode.OfLong, DefaultNode.Builder.OfLong {
        private boolean building = false;

        LongSpinedDefaultNodeBuilder() {} // Avoid creation of special accessor

        @Override
        public Spliterator.OfLong spliterator() {
            assert !building : "during building";
            return super.spliterator();
        }

        @Override
        public void forEach(LongConsumer consumer) {
            assert !building : "during building";
            super.forEach(consumer);
        }

        //
        @Override
        public void begin(long size) {
            assert !building : "was already building";
            building = true;
            clear();
            ensureCapacity(size);
        }

        @Override
        public void accept(long i) {
            assert building : "not building";
            super.accept(i);
        }

        @Override
        public void end() {
            assert building : "was not building";
            building = false;
            // @@@ check begin(size) and size
        }

        @Override
        public void copyInto(long[] array, int offset) {
            assert !building : "during building";
            super.copyInto(array, offset);
        }

        @Override
        public long[] asPrimitiveArray() {
            assert !building : "during building";
            return super.asPrimitiveArray();
        }

        @Override
        public DefaultNode.OfLong build() {
            assert !building : "during building";
            return this;
        }
    }

    private static final class DoubleSpinedDefaultNodeBuilder
            extends DefaultSpinedBuffer.OfDouble
            implements DefaultNode.OfDouble, DefaultNode.Builder.OfDouble {
        private boolean building = false;

        DoubleSpinedDefaultNodeBuilder() {} // Avoid creation of special accessor

        @Override
        public Spliterator.OfDouble spliterator() {
            assert !building : "during building";
            return super.spliterator();
        }

        @Override
        public void forEach(DoubleConsumer consumer) {
            assert !building : "during building";
            super.forEach(consumer);
        }

        //
        @Override
        public void begin(long size) {
            assert !building : "was already building";
            building = true;
            clear();
            ensureCapacity(size);
        }

        @Override
        public void accept(double i) {
            assert building : "not building";
            super.accept(i);
        }

        @Override
        public void end() {
            assert building : "was not building";
            building = false;
            // @@@ check begin(size) and size
        }

        @Override
        public void copyInto(double[] array, int offset) {
            assert !building : "during building";
            super.copyInto(array, offset);
        }

        @Override
        public double[] asPrimitiveArray() {
            assert !building : "during building";
            return super.asPrimitiveArray();
        }

        @Override
        public DefaultNode.OfDouble build() {
            assert !building : "during building";
            return this;
        }
    }

    /*
     * This and subclasses are not intended to be serializable
     */
    private static abstract class SizedCollectorTask<P_IN, P_OUT, T_SINK extends DefaultSink<P_OUT>,
                                                     K extends DefaultNodes.SizedCollectorTask<P_IN, P_OUT, T_SINK, K>>
            extends CountedCompleter<Void>
            implements DefaultSink<P_OUT> {
        protected final Spliterator<P_IN> spliterator;
        protected final DefaultPipelineHelper<P_OUT> helper;
        protected final long targetSize;
        protected long offset;
        protected long length;
        // For DefaultSink implementation
        protected int index, fence;

        SizedCollectorTask(Spliterator<P_IN> spliterator,
                           DefaultPipelineHelper<P_OUT> helper,
                           int arrayLength) {
            assert spliterator.hasCharacteristics(Spliterator.SUBSIZED);
            this.spliterator = spliterator;
            this.helper = helper;
            this.targetSize = DefaultAbstractTask.suggestTargetSize(spliterator.estimateSize());
            this.offset = 0;
            this.length = arrayLength;
        }

        SizedCollectorTask(K parent, Spliterator<P_IN> spliterator,
                           long offset, long length, int arrayLength) {
            super(parent);
            assert spliterator.hasCharacteristics(Spliterator.SUBSIZED);
            this.spliterator = spliterator;
            this.helper = parent.helper;
            this.targetSize = parent.targetSize;
            this.offset = offset;
            this.length = length;

            if (offset < 0 || length < 0 || (offset + length - 1 >= arrayLength)) {
                throw new IllegalArgumentException(
                        String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)",
                                      offset, offset, length, arrayLength));
            }
        }

        @Override
        public void compute() {
            SizedCollectorTask<P_IN, P_OUT, T_SINK, K> task = this;
            Spliterator<P_IN> rightSplit = spliterator, leftSplit;
            while (rightSplit.estimateSize() > task.targetSize &&
                   (leftSplit = rightSplit.trySplit()) != null) {
                task.setPendingCount(1);
                long leftSplitSize = leftSplit.estimateSize();
                task.makeChild(leftSplit, task.offset, leftSplitSize).fork();
                task = task.makeChild(rightSplit, task.offset + leftSplitSize,
                                      task.length - leftSplitSize);
            }

            assert task.offset + task.length < MAX_ARRAY_SIZE;
            @SuppressWarnings("unchecked")
            T_SINK sink = (T_SINK) task;
            task.helper.wrapAndCopyInto(sink, rightSplit);
            task.propagateCompletion();
        }

        abstract K makeChild(Spliterator<P_IN> spliterator, long offset, long size);

        @Override
        public void begin(long size) {
            if (size > length)
                throw new IllegalStateException("size passed to DefaultSink.begin exceeds array length");
            // Casts to int are safe since absolute size is verified to be within
            // bounds when the root concrete SizedCollectorTask is constructed
            // with the shared array
            index = (int) offset;
            fence = index + (int) length;
        }

        static final class OfRef<P_IN, P_OUT>
                extends DefaultNodes.SizedCollectorTask<P_IN, P_OUT, DefaultSink<P_OUT>, OfRef<P_IN, P_OUT>>
                implements DefaultSink<P_OUT> {
            private final P_OUT[] array;

            OfRef(Spliterator<P_IN> spliterator, DefaultPipelineHelper<P_OUT> helper, P_OUT[] array) {
                super(spliterator, helper, array.length);
                this.array = array;
            }

            OfRef(DefaultNodes.SizedCollectorTask.OfRef<P_IN, P_OUT> parent, Spliterator<P_IN> spliterator,
                  long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            @Override
            DefaultNodes.SizedCollectorTask.OfRef<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator,
                                                                         long offset, long size) {
                return new DefaultNodes.SizedCollectorTask.OfRef<>(this, spliterator, offset, size);
            }

            @Override
            public void accept(P_OUT value) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                array[index++] = value;
            }
        }

        static final class OfInt<P_IN>
                extends DefaultNodes.SizedCollectorTask<P_IN, Integer, DefaultSink.OfInt, OfInt<P_IN>>
                implements DefaultSink.OfInt {
            private final int[] array;

            OfInt(Spliterator<P_IN> spliterator, DefaultPipelineHelper<Integer> helper, int[] array) {
                super(spliterator, helper, array.length);
                this.array = array;
            }

            OfInt(DefaultNodes.SizedCollectorTask.OfInt<P_IN> parent, Spliterator<P_IN> spliterator,
                  long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            @Override
            DefaultNodes.SizedCollectorTask.OfInt<P_IN> makeChild(Spliterator<P_IN> spliterator,
                                                                  long offset, long size) {
                return new DefaultNodes.SizedCollectorTask.OfInt<>(this, spliterator, offset, size);
            }

            @Override
            public void accept(int value) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                array[index++] = value;
            }
        }

        static final class OfLong<P_IN>
                extends DefaultNodes.SizedCollectorTask<P_IN, Long, DefaultSink.OfLong, OfLong<P_IN>>
                implements DefaultSink.OfLong {
            private final long[] array;

            OfLong(Spliterator<P_IN> spliterator, DefaultPipelineHelper<Long> helper, long[] array) {
                super(spliterator, helper, array.length);
                this.array = array;
            }

            OfLong(DefaultNodes.SizedCollectorTask.OfLong<P_IN> parent, Spliterator<P_IN> spliterator,
                   long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            @Override
            DefaultNodes.SizedCollectorTask.OfLong<P_IN> makeChild(Spliterator<P_IN> spliterator,
                                                                   long offset, long size) {
                return new DefaultNodes.SizedCollectorTask.OfLong<>(this, spliterator, offset, size);
            }

            @Override
            public void accept(long value) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                array[index++] = value;
            }
        }

        static final class OfDouble<P_IN>
                extends DefaultNodes.SizedCollectorTask<P_IN, Double, DefaultSink.OfDouble, OfDouble<P_IN>>
                implements DefaultSink.OfDouble {
            private final double[] array;

            OfDouble(Spliterator<P_IN> spliterator, DefaultPipelineHelper<Double> helper, double[] array) {
                super(spliterator, helper, array.length);
                this.array = array;
            }

            OfDouble(DefaultNodes.SizedCollectorTask.OfDouble<P_IN> parent, Spliterator<P_IN> spliterator,
                     long offset, long length) {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            @Override
            DefaultNodes.SizedCollectorTask.OfDouble<P_IN> makeChild(Spliterator<P_IN> spliterator,
                                                                     long offset, long size) {
                return new DefaultNodes.SizedCollectorTask.OfDouble<>(this, spliterator, offset, size);
            }

            @Override
            public void accept(double value) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                array[index++] = value;
            }
        }
    }

    private static abstract class ToArrayTask<T, T_NODE extends DefaultNode<T>,
                                              K extends DefaultNodes.ToArrayTask<T, T_NODE, K>>
            extends CountedCompleter<Void> {
        protected final T_NODE node;
        protected final int offset;

        ToArrayTask(T_NODE node, int offset) {
            this.node = node;
            this.offset = offset;
        }

        ToArrayTask(K parent, T_NODE node, int offset) {
            super(parent);
            this.node = node;
            this.offset = offset;
        }

        abstract void copyDefaultNodeToArray();

        abstract K makeChild(int childIndex, int offset);

        @Override
        public void compute() {
            ToArrayTask<T, T_NODE, K> task = this;
            while (true) {
                if (task.node.getChildCount() == 0) {
                    task.copyDefaultNodeToArray();
                    task.propagateCompletion();
                    return;
                }
                else {
                    task.setPendingCount(task.node.getChildCount() - 1);

                    int size = 0;
                    int i = 0;
                    for (;i < task.node.getChildCount() - 1; i++) {
                        K leftTask = task.makeChild(i, task.offset + size);
                        size += leftTask.node.count();
                        leftTask.fork();
                    }
                    task = task.makeChild(i, task.offset + size);
                }
            }
        }

        private static final class OfRef<T>
                extends DefaultNodes.ToArrayTask<T, DefaultNode<T>, OfRef<T>> {
            private final T[] array;

            private OfRef(DefaultNode<T> node, T[] array, int offset) {
                super(node, offset);
                this.array = array;
            }

            private OfRef(OfRef<T> parent, DefaultNode<T> node, int offset) {
                super(parent, node, offset);
                this.array = parent.array;
            }

            @Override
            OfRef<T> makeChild(int childIndex, int offset) {
                return new OfRef<>(this, node.getChild(childIndex), offset);
            }

            @Override
            void copyDefaultNodeToArray() {
                node.copyInto(array, offset);
            }
        }

        private static class OfPrimitive<T, T_CONS, T_ARR,
                                         T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
                                         T_NODE extends DefaultNode.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
                extends DefaultNodes.ToArrayTask<T, T_NODE, OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> {
            private final T_ARR array;

            private OfPrimitive(T_NODE node, T_ARR array, int offset) {
                super(node, offset);
                this.array = array;
            }

            private OfPrimitive(OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> parent, T_NODE node, int offset) {
                super(parent, node, offset);
                this.array = parent.array;
            }

            @Override
            OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> makeChild(int childIndex, int offset) {
                return new OfPrimitive<>(this, node.getChild(childIndex), offset);
            }

            @Override
            void copyDefaultNodeToArray() {
                node.copyInto(array, offset);
            }
        }

        private static final class OfInt
                extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, DefaultNode.OfInt> {
            private OfInt(DefaultNode.OfInt node, int[] array, int offset) {
                super(node, array, offset);
            }
        }

        private static final class OfLong
                extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, DefaultNode.OfLong> {
            private OfLong(DefaultNode.OfLong node, long[] array, int offset) {
                super(node, array, offset);
            }
        }

        private static final class OfDouble
                extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, DefaultNode.OfDouble> {
            private OfDouble(DefaultNode.OfDouble node, double[] array, int offset) {
                super(node, array, offset);
            }
        }
    }

    private static class CollectorTask<P_IN, P_OUT, T_NODE extends DefaultNode<P_OUT>, T_BUILDER extends DefaultNode.Builder<P_OUT>>
            extends DefaultAbstractTask<P_IN, P_OUT, T_NODE, CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER>> {
        protected final DefaultPipelineHelper<P_OUT> helper;
        protected final LongFunction<T_BUILDER> builderFactory;
        protected final BinaryOperator<T_NODE> concFactory;

        CollectorTask(DefaultPipelineHelper<P_OUT> helper,
                      Spliterator<P_IN> spliterator,
                      LongFunction<T_BUILDER> builderFactory,
                      BinaryOperator<T_NODE> concFactory) {
            super(helper, spliterator);
            this.helper = helper;
            this.builderFactory = builderFactory;
            this.concFactory = concFactory;
        }

        CollectorTask(CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> parent,
                      Spliterator<P_IN> spliterator) {
            super(parent, spliterator);
            helper = parent.helper;
            builderFactory = parent.builderFactory;
            concFactory = parent.concFactory;
        }

        @Override
        protected CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> makeChild(Spliterator<P_IN> spliterator) {
            return new CollectorTask<>(this, spliterator);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected T_NODE doLeaf() {
            T_BUILDER builder = builderFactory.apply(helper.exactOutputSizeIfKnown(spliterator));
            return (T_NODE) helper.wrapAndCopyInto(builder, spliterator).build();
        }

        @Override
        public void onCompletion(CountedCompleter<?> caller) {
            if (!isLeaf())
                setLocalResult(concFactory.apply(leftChild.getLocalResult(), rightChild.getLocalResult()));
            super.onCompletion(caller);
        }

        private static final class OfRef<P_IN, P_OUT>
                extends DefaultNodes.CollectorTask<P_IN, P_OUT, DefaultNode<P_OUT>, DefaultNode.Builder<P_OUT>> {
            OfRef(DefaultPipelineHelper<P_OUT> helper,
                  IntFunction<P_OUT[]> generator,
                  Spliterator<P_IN> spliterator) {
                super(helper, spliterator, s -> builder(s, generator), ConcDefaultNode::new);
            }
        }

        private static final class OfInt<P_IN>
                extends DefaultNodes.CollectorTask<P_IN, Integer, DefaultNode.OfInt, DefaultNode.Builder.OfInt> {
            OfInt(DefaultPipelineHelper<Integer> helper, Spliterator<P_IN> spliterator) {
                super(helper, spliterator, DefaultNodes::intBuilder, ConcDefaultNode.OfInt::new);
            }
        }

        private static final class OfLong<P_IN>
                extends DefaultNodes.CollectorTask<P_IN, Long, DefaultNode.OfLong, DefaultNode.Builder.OfLong> {
            OfLong(DefaultPipelineHelper<Long> helper, Spliterator<P_IN> spliterator) {
                super(helper, spliterator, DefaultNodes::longBuilder, ConcDefaultNode.OfLong::new);
            }
        }

        private static final class OfDouble<P_IN>
                extends DefaultNodes.CollectorTask<P_IN, Double, DefaultNode.OfDouble, DefaultNode.Builder.OfDouble> {
            OfDouble(DefaultPipelineHelper<Double> helper, Spliterator<P_IN> spliterator) {
                super(helper, spliterator, DefaultNodes::doubleBuilder, ConcDefaultNode.OfDouble::new);
            }
        }
    }
}
