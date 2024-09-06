package io.github.nichetoolkit.rest.stream;

import lombok.extern.slf4j.Slf4j;

import java.security.AccessController;
import java.security.PrivilegedAction;

@Slf4j
final class DefaultTripwire {
    private static final String TRIPWIRE_PROPERTY = "org.openjdk.java.util.stream.tripwire";

    static final boolean ENABLED = AccessController.doPrivileged(
            (PrivilegedAction<Boolean>) () -> Boolean.getBoolean(TRIPWIRE_PROPERTY));

    private DefaultTripwire() { }

    static void trip(Class<?> trippingClass, String msg) {
        log.warn(msg,trippingClass.getName());
    }
}
