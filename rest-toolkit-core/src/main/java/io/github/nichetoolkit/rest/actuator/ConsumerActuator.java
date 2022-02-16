package io.github.nichetoolkit.rest.actuator;

import io.github.nichetoolkit.rest.RestException;

import java.util.Objects;

/**
 * <p>ConsumerActuator</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@FunctionalInterface
public interface ConsumerActuator<T>{
    /**
     * 函数执行器
     * @param t 入参
     * @throws RestException RestException异常
     */
    void actuate(T t) throws RestException;


    default ConsumerActuator<T> andThen(ConsumerActuator<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { actuate(t); after.actuate(t); };
    }
}
