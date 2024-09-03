package io.github.nichetoolkit.rest.identity.worker;

import lombok.Data;

import java.util.Objects;

/**
 * <code>WorkerConfig</code>
 * <p>The type worker config class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class WorkerConfig {
    /**
     * <code>workerId</code>
     * {@link java.lang.Long} <p>the <code>workerId</code> field.</p>
     * @see java.lang.Long
     */
    private Long workerId;
    /**
     * <code>centerId</code>
     * {@link java.lang.Long} <p>the <code>centerId</code> field.</p>
     * @see java.lang.Long
     */
    private Long centerId;

    /**
     * <code>WorkerConfig</code>
     * Instantiates a new worker config.
     */
    public WorkerConfig() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkerConfig)) return false;
        WorkerConfig that = (WorkerConfig) o;
        return Objects.equals(workerId, that.workerId) &&
                Objects.equals(centerId, that.centerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workerId, centerId);
    }
}
