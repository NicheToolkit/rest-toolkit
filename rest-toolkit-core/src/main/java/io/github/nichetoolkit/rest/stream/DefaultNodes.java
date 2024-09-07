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
import io.github.nichetoolkit.rest.actuator.BinaryOperatorActuator;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.*;
import java.util.function.*;

final class DefaultNodes {

    private DefaultNodes() {
        throw new Error("no instances");
    }

    static final long MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    // IllegalArgumentException messages
    static final String BAD_SIZE = "Stream size exceeds max array size";

    @SuppressWarnings("rawtypes")
    private static final DefaultNode EMPTY_NODE = new EmptyDefaultNode.OfRef();
//    private static final DefaultNode.OfInt EMPTY_INT_NODE = new EmptyDefaultNode.OfInt();
//    private static final DefaultNode.OfLong EMPTY_LONG_NODE = new EmptyDefaultNode.OfLong();
//    private static final DefaultNode.OfDouble EMPTY_DOUBLE_NODE = new EmptyDefaultNode.OfDouble();

    // General shape-based node creation methods

    @SuppressWarnings("unchecked")
    static <T> DefaultNode<T> emptyDefaultNode(DefaultStreamShape shape) {
        if (Objects.requireNonNull(shape) == DefaultStreamShape.REFERENCE) {
            return (DefaultNode<T>) EMPTY_NODE;
        }
        throw new IllegalStateException("Unknown shape " + shape);
    }

    static <T> DefaultNode<T> conc(DefaultStreamShape shape, DefaultNode<T> left, DefaultNode<T> right) {
        if (Objects.requireNonNull(shape) == DefaultStreamShape.REFERENCE) {
            return new ConcDefaultNode<>(left, right);
        }
        throw new IllegalStateException("Unknown shape " + shape);
    }

    static <T> DefaultNode<T> node(T[] array) {
        return new ArrayDefaultNode<>(array);
    }

    static <T> DefaultNode<T> node(Collection<T> c) {
        return new CollectionDefaultNode<>(c);
    }

    static <T> DefaultNode.Builder<T> builder(long exactSizeIfKnown, IntFunction<T[]> generator) {
        return (exactSizeIfKnown >= 0 && exactSizeIfKnown < MAX_ARRAY_SIZE)
                ? new FixedDefaultNodeBuilder<>(exactSizeIfKnown, generator)
                : builder();
    }

    static <T> DefaultNode.Builder<T> builder() {
        return new SpinedDefaultNodeBuilder<>();
    }

    // Parallel evaluation of pipelines to nodes

    public static <P_IN, P_OUT> DefaultNode<P_OUT> collect(DefaultPipelineHelper<P_OUT> helper,
                                                           DefaultSpliterator<P_IN> spliterator,
                                                           boolean flattenTree,
                                                           IntFunction<P_OUT[]> generator) throws RestException {
        long size = helper.exactOutputSizeIfKnown(spliterator);
        if (size >= 0 && spliterator.hasCharacteristics(DefaultSpliterator.SUBSIZED)) {
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

//     Parallel flattening of nodes

    public static <T> DefaultNode<T> flatten(DefaultNode<T> node, IntFunction<T[]> generator) throws RestException {
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

//     Implementations

    private static abstract class EmptyDefaultNode<T, T_ARR, T_CONS> implements DefaultNode<T> {
        EmptyDefaultNode() {
        }

        @Override
        public T[] asArray(IntFunction<T[]> generator) {
            return generator.apply(0);
        }

        public void copyInto(T_ARR array, int offset) {
        }

        @Override
        public long count() {
            return 0;
        }

        public void forEach(T_CONS consumer) {
        }

        private static class OfRef<T> extends DefaultNodes.EmptyDefaultNode<T, T[], ConsumerActuator<? super T>> {
            private OfRef() {
                super();
            }

            @Override
            public DefaultSpliterator<T> spliterator() {
                return DefaultSpliterators.emptySpliterator();
            }
        }
    }

    private static class ArrayDefaultNode<T> implements DefaultNode<T> {
        final T[] array;
        int curSize;

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
        public DefaultSpliterator<T> spliterator() {
            return DefaultSpliterators.spliterator(array, 0, curSize);
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
        public void forEach(ConsumerActuator<? super T> consumer) throws RestException {
            for (int i = 0; i < curSize; i++) {
                consumer.actuate(array[i]);
            }
        }

        //

        @Override
        public String toString() {
            return String.format("ArrayDefaultNode[%d][%s]",
                    array.length - curSize, Arrays.toString(array));
        }
    }

    private static final class CollectionDefaultNode<T> implements DefaultNode<T> {
        private final Collection<T> c;

        CollectionDefaultNode(Collection<T> c) {
            this.c = c;
        }

        // DefaultNode

        @Override
        public DefaultSpliterator<T> spliterator() throws RestException {
            DefaultSpliterator<T> spliterator = DefaultSpliterators.spliterator(c, 0);
            return DefaultStreamSupport.stream(spliterator, false).spliterator();
        }

        @Override
        public void copyInto(T[] array, int offset) {
            for (T t : c)
                array[offset++] = t;
        }

        @Override
        public T[] asArray(IntFunction<T[]> generator) {
            return c.toArray(generator.apply(c.size()));
        }

        @Override
        public long count() {
            return c.size();
        }

        @Override
        public void forEach(ConsumerActuator<? super T> consumer) {
            c.forEach(consumer);
        }

        //

        @Override
        public String toString() {
            return String.format("CollectionDefaultNode[%d][%s]", c.size(), c);
        }
    }

    private static abstract class AbstractConcDefaultNode<T, T_NODE extends DefaultNode<T>> implements DefaultNode<T> {
        protected final T_NODE left;
        protected final T_NODE right;
        private final long size;

        AbstractConcDefaultNode(T_NODE left, T_NODE right) {
            this.left = left;
            this.right = right;
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
        public DefaultSpliterator<T> spliterator() {
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
        public void forEach(ConsumerActuator<? super T> consumer) throws RestException {
            left.forEach(consumer);
            right.forEach(consumer);
        }

        @Override
        public DefaultNode<T> truncate(long from, long to, IntFunction<T[]> generator) throws RestException {
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
                T_SPLITR extends DefaultSpliterator.OfPrimitive<E, T_CONS, T_SPLITR>,
                T_NODE extends DefaultNode.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE>>
                extends AbstractConcDefaultNode<E, T_NODE>
                implements DefaultNode.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE> {

            OfPrimitive(T_NODE left, T_NODE right) {
                super(left, right);
            }

            @Override
            public void forEach(T_CONS consumer) throws RestException {
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
    }

    private static abstract class InternalDefaultNodeSpliterator<T,
            S extends DefaultSpliterator<T>,
            N extends DefaultNode<T>>
            implements DefaultSpliterator<T> {
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

        @SuppressWarnings("unchecked")
        protected final Deque<N> initStack() throws RestException {
            // Bias size to the case where leaf nodes are close to this node
            // 8 is the minimum initial capacity for the ArrayDeque implementation
            Deque<N> stack = new ArrayDeque<>(8);
            for (int i = curDefaultNode.getChildCount() - 1; i >= curChildIndex; i--)
                stack.addFirst((N) curDefaultNode.getChild(i));
            return stack;
        }

        @SuppressWarnings("unchecked")
        protected final N findNextLeafDefaultNode(Deque<N> stack) throws RestException {
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
        protected final boolean initTryAdvance() throws RestException {
            if (curDefaultNode == null)
                return true;

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
                        return true;
                    }
                } else
                    tryAdvanceSpliterator = lastDefaultNodeSpliterator;
            }
            return false;
        }

        @Override
        @SuppressWarnings("unchecked")
        public final S trySplit() throws RestException {
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
                } else {
                    curChildIndex = 0;
                    return (S) curDefaultNode.getChild(curChildIndex++).spliterator();
                }
            }
        }

        @Override
        public final long estimateSize() throws RestException {
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
            return DefaultSpliterator.SIZED;
        }

        private static final class OfRef<T>
                extends DefaultNodes.InternalDefaultNodeSpliterator<T, DefaultSpliterator<T>, DefaultNode<T>> {

            OfRef(DefaultNode<T> curDefaultNode) {
                super(curDefaultNode);
            }

            @Override
            public boolean tryAdvance(ConsumerActuator<? super T> consumer) throws RestException {
                if (initTryAdvance())
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
            public void forEachRemaining(ConsumerActuator<? super T> consumer) throws RestException {
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
                    } else
                        lastDefaultNodeSpliterator.forEachRemaining(consumer);
                } else
                    while (tryAdvance(consumer)) {
                    }
            }
        }

        private static abstract class OfPrimitive<T, T_CONS, T_ARR,
                T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
                N extends DefaultNode.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, N>>
                extends DefaultNodes.InternalDefaultNodeSpliterator<T, T_SPLITR, N>
                implements DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR> {

            OfPrimitive(N cur) {
                super(cur);
            }

            @Override
            public boolean tryAdvance(T_CONS consumer) throws RestException {
                if (initTryAdvance())
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
            public void forEachRemaining(T_CONS consumer) throws RestException {
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
                    } else
                        lastDefaultNodeSpliterator.forEachRemaining(consumer);
                } else
                    while (tryAdvance(consumer)) {
                    }
            }
        }

    }

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
        public void actuate(T t) {
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

    private static final class SpinedDefaultNodeBuilder<T>
            extends DefaultSpinedBuffer<T>
            implements DefaultNode<T>, DefaultNode.Builder<T> {
        private boolean building = false;

        SpinedDefaultNodeBuilder() {
        } // Avoid creation of special accessor

        @Override
        public DefaultSpliterator<T> spliterator() {
            assert !building : "during building";
            return super.spliterator();
        }

        @Override
        public void forEach(ConsumerActuator<? super T> consumer) throws RestException {
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
        public void actuate(T t) {
            assert building : "not building";
            super.actuate(t);
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
    
    private static abstract class SizedCollectorTask<P_IN, P_OUT, T_SINK extends DefaultSink<P_OUT>,
            K extends DefaultNodes.SizedCollectorTask<P_IN, P_OUT, T_SINK, K>>
            extends DefaultCountedCompleter<Void>
            implements DefaultSink<P_OUT> {
        protected final DefaultSpliterator<P_IN> spliterator;
        protected final DefaultPipelineHelper<P_OUT> helper;
        protected final long targetSize;
        protected long offset;
        protected long length;
        // For DefaultSink implementation
        protected int index, fence;

        SizedCollectorTask(DefaultSpliterator<P_IN> spliterator,
                           DefaultPipelineHelper<P_OUT> helper,
                           int arrayLength) throws RestException {
            assert spliterator.hasCharacteristics(DefaultSpliterator.SUBSIZED);
            this.spliterator = spliterator;
            this.helper = helper;
            this.targetSize = DefaultAbstractTask.suggestTargetSize(spliterator.estimateSize());
            this.offset = 0;
            this.length = arrayLength;
        }

        SizedCollectorTask(K parent, DefaultSpliterator<P_IN> spliterator,
                           long offset, long length, int arrayLength) throws RestException {
            super(parent);
            assert spliterator.hasCharacteristics(DefaultSpliterator.SUBSIZED);
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
        public void computes() throws RestException {
            SizedCollectorTask<P_IN, P_OUT, T_SINK, K> task = this;
            DefaultSpliterator<P_IN> rightSplit = spliterator, leftSplit;
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

        abstract K makeChild(DefaultSpliterator<P_IN> spliterator, long offset, long size) throws RestException;

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

            OfRef(DefaultSpliterator<P_IN> spliterator, DefaultPipelineHelper<P_OUT> helper, P_OUT[] array) throws RestException {
                super(spliterator, helper, array.length);
                this.array = array;
            }

            OfRef(DefaultNodes.SizedCollectorTask.OfRef<P_IN, P_OUT> parent, DefaultSpliterator<P_IN> spliterator,
                  long offset, long length) throws RestException {
                super(parent, spliterator, offset, length, parent.array.length);
                this.array = parent.array;
            }

            @Override
            DefaultNodes.SizedCollectorTask.OfRef<P_IN, P_OUT> makeChild(DefaultSpliterator<P_IN> spliterator,
                                                                         long offset, long size) throws RestException {
                return new DefaultNodes.SizedCollectorTask.OfRef<>(this, spliterator, offset, size);
            }

            @Override
            public void actuate(P_OUT value) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                array[index++] = value;
            }
        }
    }

    private static abstract class ToArrayTask<T, T_NODE extends DefaultNode<T>,
            K extends DefaultNodes.ToArrayTask<T, T_NODE, K>>
            extends DefaultCountedCompleter<Void> {
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
        public void computes() {
            ToArrayTask<T, T_NODE, K> task = this;
            while (true) {
                if (task.node.getChildCount() == 0) {
                    task.copyDefaultNodeToArray();
                    task.propagateCompletion();
                    return;
                } else {
                    task.setPendingCount(task.node.getChildCount() - 1);

                    int size = 0;
                    int i = 0;
                    for (; i < task.node.getChildCount() - 1; i++) {
                        K leftTask = task.makeChild(i, task.offset + size);
                        size += (int) leftTask.node.count();
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
                T_SPLITR extends DefaultSpliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
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
    }

    private static class CollectorTask<P_IN, P_OUT, T_NODE extends DefaultNode<P_OUT>, T_BUILDER extends DefaultNode.Builder<P_OUT>>
            extends DefaultAbstractTask<P_IN, P_OUT, T_NODE, CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER>> {
        protected final DefaultPipelineHelper<P_OUT> helper;
        protected final LongFunction<T_BUILDER> builderFactory;
        protected final BinaryOperatorActuator<T_NODE> concFactory;

        CollectorTask(DefaultPipelineHelper<P_OUT> helper,
                      DefaultSpliterator<P_IN> spliterator,
                      LongFunction<T_BUILDER> builderFactory,
                      BinaryOperatorActuator<T_NODE> concFactory) {
            super(helper, spliterator);
            this.helper = helper;
            this.builderFactory = builderFactory;
            this.concFactory = concFactory;
        }

        CollectorTask(CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> parent,
                      DefaultSpliterator<P_IN> spliterator) {
            super(parent, spliterator);
            helper = parent.helper;
            builderFactory = parent.builderFactory;
            concFactory = parent.concFactory;
        }

        @Override
        protected CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> makeChild(DefaultSpliterator<P_IN> spliterator) {
            return new CollectorTask<>(this, spliterator);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected T_NODE doLeaf() throws RestException {
            T_BUILDER builder = builderFactory.apply(helper.exactOutputSizeIfKnown(spliterator));
            return (T_NODE) helper.wrapAndCopyInto(builder, spliterator).build();
        }

        @Override
        public void onComputes(DefaultCountedCompleter<?> caller) throws RestException {
            if (isLeaf())
                setLocalResult(concFactory.actuate(leftChild.getLocalResult(), rightChild.getLocalResult()));
            super.onCompletion(caller);
        }

        private static final class OfRef<P_IN, P_OUT>
                extends DefaultNodes.CollectorTask<P_IN, P_OUT, DefaultNode<P_OUT>, DefaultNode.Builder<P_OUT>> {
            OfRef(DefaultPipelineHelper<P_OUT> helper,
                  IntFunction<P_OUT[]> generator,
                  DefaultSpliterator<P_IN> spliterator) {
                super(helper, spliterator, s -> builder(s, generator), ConcDefaultNode::new);
            }
        }

    }
}
