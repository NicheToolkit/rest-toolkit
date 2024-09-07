
package io.github.nichetoolkit.rest.stream;

import io.github.nichetoolkit.rest.actuator.SupplierActuator;

interface DefaultTerminalSink<T, R> extends DefaultSink<T>, SupplierActuator<R> { }
