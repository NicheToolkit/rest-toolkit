package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestStatus;

import java.util.function.Supplier;

/**
 * <code>SupplierActuator</code>
 * <p>The type supplier actuator interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface SupplierActuator<T> extends Supplier<T> {

    /**
     * <code>get</code>
     * <p>the method.</p>
     * @return T <p>the return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    T actuate() throws RestException;

    @Override
    default T get() {
        try {
            return actuate();
        } catch (RestException exception) {
            throw new RestError(exception);
        }
    }
}
