package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>AnchorActuator</code>
 * <p>The type anchor actuator interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface AnchorActuator {
    /**
     * <code>actuate</code>
     * <p>the method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>the rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void actuate() throws RestException;


    /**
     * <code>andThen</code>
     * <p>the then method.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>the after parameter is <code>AnchorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>the then return object is <code>AnchorActuator</code> type.</p>
     */
    default AnchorActuator andThen(AnchorActuator after) {
        Objects.requireNonNull(after);
        return () -> { actuate(); after.actuate(); };
    }
}
