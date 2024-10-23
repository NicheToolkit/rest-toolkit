package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * <code>CloseableHelper</code>
 * <p>The closeable helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class CloseableHelper {

    /**
     * <code>close</code>
     * <p>The close method.</p>
     * @param close {@link java.io.Closeable} <p>The close parameter is <code>Closeable</code> type.</p>
     * @see java.io.Closeable
     */
    public static void close(Closeable... close) {
        if (GeneralUtils.isNotEmpty(close)) {
            for (Closeable closeable : close) {
                close(closeable);
            }
        }
    }

    /**
     * <code>close</code>
     * <p>The close method.</p>
     * @param closeable {@link java.io.Closeable} <p>The closeable parameter is <code>Closeable</code> type.</p>
     * @see java.io.Closeable
     */
    public static void close(Closeable closeable) {
        if (GeneralUtils.isNotEmpty(closeable)) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * <code>interrupt</code>
     * <p>The interrupt method.</p>
     * @param threads {@link java.lang.Thread} <p>The threads parameter is <code>Thread</code> type.</p>
     * @see java.lang.Thread
     */
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
