package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;

interface DefaultTerminalOp<E_IN, R> {
    default DefaultStreamShape inputShape() {
        return DefaultStreamShape.REFERENCE;
    }

    default int getOpFlags() {
        return 0;
    }

    default <P_IN> R evaluateParallel(DefaultPipelineHelper<E_IN> helper,
                                      DefaultSpliterator<P_IN> spliterator) throws RestException {
        if (DefaultTripwire.ENABLED)
            DefaultTripwire.trip(getClass(), "{0} triggering TerminalOp.evaluateParallel serial default");
        return evaluateSequential(helper, spliterator);
    }

    <P_IN> R evaluateSequential(DefaultPipelineHelper<E_IN> helper,
                                DefaultSpliterator<P_IN> spliterator) throws RestException;
}
