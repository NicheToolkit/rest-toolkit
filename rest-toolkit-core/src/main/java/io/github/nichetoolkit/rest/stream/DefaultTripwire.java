package io.github.nichetoolkit.rest.stream;

import lombok.extern.slf4j.Slf4j;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * <code>DefaultTripwire</code>
 * <p>The type default tripwire class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
final class DefaultTripwire {
    /**
     * <code>TRIPWIRE_PROPERTY</code>
     * {@link java.lang.String} <p>The constant <code>TRIPWIRE_PROPERTY</code> field.</p>
     * @see java.lang.String
     */
    private static final String TRIPWIRE_PROPERTY = "org.openjdk.java.util.stream.tripwire";

    /**
     * <code>ENABLED</code>
     * <p>The <code>ENABLED</code> field.</p>
     */
    static final boolean ENABLED = AccessController.doPrivileged(
            (PrivilegedAction<Boolean>) () -> Boolean.getBoolean(TRIPWIRE_PROPERTY));

    /**
     * <code>DefaultTripwire</code>
     * <p>Instantiates a new default tripwire.</p>
     */
    private DefaultTripwire() { }

    /**
     * <code>trip</code>
     * <p>The method.</p>
     * @param trippingClass {@link java.lang.Class} <p>The tripping class parameter is <code>Class</code> type.</p>
     * @param msg           {@link java.lang.String} <p>The msg parameter is <code>String</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.String
     */
    static void trip(Class<?> trippingClass, String msg) {
        log.warn(msg,trippingClass.getName());
    }
}
