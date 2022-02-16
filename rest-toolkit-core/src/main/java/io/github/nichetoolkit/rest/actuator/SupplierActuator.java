package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

/**
 * <p>SupplierActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface SupplierActuator<T> {

    T get() throws RestException;
}
