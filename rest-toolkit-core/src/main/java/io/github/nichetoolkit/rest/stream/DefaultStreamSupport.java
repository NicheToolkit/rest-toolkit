
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Objects;

public final class DefaultStreamSupport {
    private DefaultStreamSupport() {
    }

    public static <T> RestStream<T> stream(DefaultSpliterator<T> spliterator, boolean parallel) throws RestException {
        Objects.requireNonNull(spliterator);
        return new DefaultReferencePipeline.Head<>(spliterator, DefaultStreamOpFlag.fromCharacteristics(spliterator), parallel);
    }

    public static <T> RestStream<T> stream(SupplierActuator<? extends DefaultSpliterator<T>> supplier, int characteristics, boolean parallel) {
        Objects.requireNonNull(supplier);
        return new DefaultReferencePipeline.Head<>(supplier, DefaultStreamOpFlag.fromCharacteristics(characteristics), parallel);
    }
}
