package io.github.nichetoolkit.rest.actuator;

import java.util.Objects;

/**
 * <code>AnchorFunction</code>
 * <p>The anchor function interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
public interface AnchorFunction {
    /**
     * <code>apply</code>
     * <p>The apply method.</p>
     */
    void apply();


    /**
     * <code>andThen</code>
     * <p>The and then method.</p>
     * @param after {@link io.github.nichetoolkit.rest.actuator.AnchorFunction} <p>The after parameter is <code>AnchorFunction</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.actuator.AnchorActuator} <p>The and then return object is <code>AnchorActuator</code> type.</p>
     * @see io.github.nichetoolkit.rest.actuator.AnchorActuator
     */
    default AnchorActuator andThen(AnchorFunction after) {
        Objects.requireNonNull(after);
        return () -> {
            apply();
            after.apply();
        };
    }
}