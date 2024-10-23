
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.actuator.SupplierActuator;

import java.util.Objects;

/**
 * <code>DefaultStreamSupport</code>
 * <p>The default stream support class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public final class DefaultStreamSupport {
    /**
     * <code>DefaultStreamSupport</code>
     * <p>Instantiates a new default stream support.</p>
     */
    private DefaultStreamSupport() {
    }

    /**
     * <code>stream</code>
     * <p>The stream method.</p>
     * @param <T>         {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param spliterator {@link io.github.nichetoolkit.rest.stream.DefaultSpliterator} <p>The spliterator parameter is <code>DefaultSpliterator</code> type.</p>
     * @param parallel    boolean <p>The parallel parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The stream return object is <code>RestStream</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.stream.DefaultSpliterator
     * @see io.github.nichetoolkit.rest.stream.RestStream
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static <T> RestStream<T> stream(DefaultSpliterator<T> spliterator, boolean parallel) throws RestException {
        Objects.requireNonNull(spliterator);
        return new DefaultReferencePipeline.Head<>(spliterator, DefaultStreamOpFlag.fromCharacteristics(spliterator), parallel);
    }

    /**
     * <code>stream</code>
     * <p>The stream method.</p>
     * @param <T>             {@link java.lang.Object} <p>The parameter can be of any type.</p>
     * @param supplier        {@link io.github.nichetoolkit.rest.actuator.SupplierActuator} <p>The supplier parameter is <code>SupplierActuator</code> type.</p>
     * @param characteristics int <p>The characteristics parameter is <code>int</code> type.</p>
     * @param parallel        boolean <p>The parallel parameter is <code>boolean</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.stream.RestStream} <p>The stream return object is <code>RestStream</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
     * @see io.github.nichetoolkit.rest.stream.RestStream
     */
    public static <T> RestStream<T> stream(SupplierActuator<? extends DefaultSpliterator<T>> supplier, int characteristics, boolean parallel) {
        Objects.requireNonNull(supplier);
        return new DefaultReferencePipeline.Head<>(supplier, DefaultStreamOpFlag.fromCharacteristics(characteristics), parallel);
    }
}
