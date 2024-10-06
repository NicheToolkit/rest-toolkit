package io.github.nichetoolkit.rest.constant;

/**
 * <code>MillisecondConstants</code>
 * <p>The type millisecond constants interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface MillisecondConstants {
    /**
     * <code>SECOND</code>
     * {@link java.lang.Long} <p>The constant <code>SECOND</code> field.</p>
     * @see java.lang.Long
     */
    Long SECOND = 1000L;

    /**
     * <code>MINUTE</code>
     * {@link java.lang.Long} <p>The constant <code>MINUTE</code> field.</p>
     * @see java.lang.Long
     */
    Long MINUTE = SECOND * 60;

    /**
     * <code>HOUR</code>
     * {@link java.lang.Long} <p>The constant <code>HOUR</code> field.</p>
     * @see java.lang.Long
     */
    Long HOUR = MINUTE * 60;

    /**
     * <code>DAY</code>
     * {@link java.lang.Long} <p>The constant <code>DAY</code> field.</p>
     * @see java.lang.Long
     */
    Long DAY = HOUR * 24;
}
