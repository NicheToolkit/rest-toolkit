package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

import java.util.function.IntFunction;

abstract class DefaultPipelineHelper<P_OUT> {

    abstract DefaultStreamShape getSourceShape();

    abstract int getStreamAndOpFlags();

    abstract <P_IN> long exactOutputSizeIfKnown(DefaultSpliterator<P_IN> spliterator) throws RestException;

    abstract <P_IN, S extends DefaultSink<P_OUT>> S wrapAndCopyInto(S sink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    abstract <P_IN> void copyInto(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    abstract <P_IN> void copyIntoWithCancel(DefaultSink<P_IN> wrappedDefaultSink, DefaultSpliterator<P_IN> spliterator) throws RestException;

    abstract <P_IN> DefaultSink<P_IN> wrapSink(DefaultSink<P_OUT> sink) throws RestException;

    abstract <P_IN> DefaultSpliterator<P_OUT> wrapSpliterator(DefaultSpliterator<P_IN> spliterator) throws RestException;

    abstract DefaultNode.Builder<P_OUT> makeNodeBuilder(long exactSizeIfKnown,
                                                        IntFunction<P_OUT[]> generator);

    abstract <P_IN> DefaultNode<P_OUT> evaluate(DefaultSpliterator<P_IN> spliterator,
                                                boolean flatten,
                                                IntFunction<P_OUT[]> generator) throws RestException;
}
