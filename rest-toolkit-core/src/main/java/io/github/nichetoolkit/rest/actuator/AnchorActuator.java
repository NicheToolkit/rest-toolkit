package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <p>AnchorActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface AnchorActuator {
    /**
     * 函数执行器
     * @throws RestException RestException异常
     */
    void actuate() throws RestException;


    default AnchorActuator andThen(AnchorActuator after) {
        Objects.requireNonNull(after);
        return () -> { actuate(); after.actuate(); };
    }
}
