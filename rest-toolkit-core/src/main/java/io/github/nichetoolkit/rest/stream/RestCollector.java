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
