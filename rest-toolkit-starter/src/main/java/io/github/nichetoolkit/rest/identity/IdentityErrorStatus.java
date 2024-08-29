package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>IdentityErrorStatus</code>
 * <p>The type identity error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum IdentityErrorStatus implements RestStatus {
    /**
     * <code>WORKER_ID_INVALID</code>
     * <p>the Worker id invalid identity error status field.</p>
     */
    WORKER_ID_INVALID(10530,"workerId无效"),
    /**
     * <code>CENTER_ID_INVALID</code>
     * <p>the Center id invalid identity error status field.</p>
     */
    CENTER_ID_INVALID(10531,"centerId无效"),
    /**
     * <code>IDENTITY_WORKER_ERROR</code>
     * <p>the Identity worker error identity error status field.</p>
     */
    IDENTITY_WORKER_ERROR(10532,"worker错误"),
    /**
     * <code>IDENTITY_WORKER_TIME_ERROR</code>
     * <p>the Identity worker time error identity error status field.</p>
     */
    IDENTITY_WORKER_TIME_ERROR(10533,"centerId无效"),
    /**
     * <code>WORKER_TYPE_IS_NULL</code>
     * <p>the Worker type is null identity error status field.</p>
     */
    WORKER_TYPE_IS_NULL(10534,"WorkerType为空"),
    /**
     * <code>IDENTITY_WORKER_UNAVAILABLE</code>
     * <p>the Identity worker unavailable identity error status field.</p>
     */
    IDENTITY_WORKER_UNAVAILABLE(10535,"IdentityWorker不可用"),
    ;
    private final Integer status;
    private final String message;

    IdentityErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>getName</code>
     * <p>the name getter method.</p>
     * @return {@link java.lang.String} <p>the name return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getName() {
        return this.name().toLowerCase().replace("_", " ");
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
