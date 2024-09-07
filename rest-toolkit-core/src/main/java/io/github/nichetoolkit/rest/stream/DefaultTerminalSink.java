
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.actuator.SupplierActuator;

/**
 * <code>DefaultTerminalSink</code>
 * <p>The type default terminal sink interface.</p>
 * @param <T> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @param <R> {@link java.lang.Object} <p>the parameter can be of any type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.stream.DefaultSink
 * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
 * @since Jdk1.8
 */
interface DefaultTerminalSink<T, R> extends DefaultSink<T>, SupplierActuator<R> { }
