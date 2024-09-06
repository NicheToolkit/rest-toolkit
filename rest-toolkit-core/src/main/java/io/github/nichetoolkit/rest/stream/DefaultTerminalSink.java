
package io.github.nichetoolkit.rest.stream;

import java.util.function.Supplier;

/**
 * A {@link DefaultSink} which accumulates state as elements are accepted, and allows
 * a result to be retrieved after the computation is finished.
 *
 * @param <T> the type of elements to be accepted
 * @param <R> the type of the result
 *
 * @since 1.8
 */
interface DefaultTerminalSink<T, R> extends DefaultSink<T>, Supplier<R> { }
