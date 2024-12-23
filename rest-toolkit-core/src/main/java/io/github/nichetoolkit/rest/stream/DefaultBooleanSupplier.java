package io.github.nichetoolkit.rest.stream;


import io.github.nichetoolkit.rest.RestException;

/**
 * <code>DefaultBooleanSupplier</code>
 * <p>The default boolean supplier interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.FunctionalInterface
 * @since Jdk1.8
 */
@FunctionalInterface
interface DefaultBooleanSupplier {

    /**
     * <code>actuate</code>
     * <p>The actuate method.</p>
     * @return boolean <p>The actuate return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestException
     */
    boolean actuate() throws RestException;
}
