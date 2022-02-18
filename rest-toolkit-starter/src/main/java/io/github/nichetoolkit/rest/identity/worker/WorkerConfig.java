package io.github.nichetoolkit.rest.identity.worker;

import java.util.Objects;

/**
 * <p>WorkerConfig</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class WorkerConfig {
    private Long workerId;
    private Long centerId;

    public WorkerConfig() {
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
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
