package io.github.nichetoolkit.rest.stream;


import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.ConsumerActuator;

import java.util.Objects;


interface DefaultSink<T> extends ConsumerActuator<T> {
    default void begin(long size) throws RestException {}

    default void end() throws RestException {}

    default boolean cancellationRequested() {
        return false;
    }

    abstract class ChainedReference<T, E_OUT> implements DefaultSink<T> {
        protected final DefaultSink<? super E_OUT> downstream;

        public ChainedReference(DefaultSink<? super E_OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }

        @Override
        public void begin(long size) throws RestException {
            downstream.begin(size);
        }

        @Override
        public void end() throws RestException {
            downstream.end();
        }

        @Override
        public boolean cancellationRequested() {
            return downstream.cancellationRequested();
        }
    }
}
