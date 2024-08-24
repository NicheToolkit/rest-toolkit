package io.github.nichetoolkit.rest.identity.worker;

import lombok.Data;

import java.util.Objects;

/**
 * <p>WorkerConfig</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Data
public class WorkerConfig {
    private Long workerId;
    private Long centerId;

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
