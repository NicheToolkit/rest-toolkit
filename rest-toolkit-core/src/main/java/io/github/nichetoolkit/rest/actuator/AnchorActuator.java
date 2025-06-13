package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <code>AnchorActuator</code>
 * <p>The anchor actuator interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.actuator.AnchorFunction
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface AnchorActuator extends AnchorFunction {
    /**
     * <code>actuate</code>
     * <p>The actuate method.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    void actuate() throws RestException;

    @Override
    default void apply() {
        try {
            actuate();
        } catch (RestException e) {
            throw new RestError(e);
        }
    }

    /**
     * <code>andThen</code>
     * <p>The and then method.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The after parameter is <code>AnchorActuator</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The and then return object is <code>AnchorActuator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    default AnchorActuator andThen(AnchorActuator after) throws RestException {
        Objects.requireNonNull(after);
        return () -> { actuate(); after.actuate(); };
    }
}
