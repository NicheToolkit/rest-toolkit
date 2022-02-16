package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * <p>CloseableHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class CloseableHelper {

    public static void close(Closeable... closeables) {
        if (GeneralUtils.isNotEmpty(closeables)) {
            for (Closeable closeable : closeables) {
                close(closeable);
            }
        }
    }

    public static void close(Closeable closeable) {
        if (GeneralUtils.isNotEmpty(closeable)) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void interrupt(Thread... threads) {
        if (GeneralUtils.isNotEmpty(threads)) {
            for (Thread thread : threads) {
                if (thread != null) {
                    if (thread.isAlive()) {
                        thread.interrupt();
                    }
                }
            }
        }
    }
}
