package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>IdentityErrorStatus</code>
 * <p>The identity error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum IdentityErrorStatus implements RestStatus {
    /**
     * <code>WORKER_ID_INVALID</code>
     * <p>The worker id invalid identity error status field.</p>
     */
    WORKER_ID_INVALID(10530,"workerId无效"),
    /**
     * <code>CENTER_ID_INVALID</code>
     * <p>The center id invalid identity error status field.</p>
     */
    CENTER_ID_INVALID(10531,"centerId无效"),
    /**
     * <code>IDENTITY_WORKER_ERROR</code>
     * <p>The identity worker error identity error status field.</p>
     */
    IDENTITY_WORKER_ERROR(10532,"worker错误"),
    /**
     * <code>IDENTITY_WORKER_TIME_ERROR</code>
     * <p>The identity worker time error identity error status field.</p>
     */
    IDENTITY_WORKER_TIME_ERROR(10533,"centerId无效"),
    /**
     * <code>WORKER_TYPE_IS_NULL</code>
     * <p>The worker type is null identity error status field.</p>
     */
    WORKER_TYPE_IS_NULL(10534,"WorkerType为空"),
    /**
     * <code>IDENTITY_WORKER_UNAVAILABLE</code>
     * <p>The identity worker unavailable identity error status field.</p>
     */
    IDENTITY_WORKER_UNAVAILABLE(10535,"IdentityWorker不可用"),
    ;
    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    private final String message;

    /**
     * <code>IdentityErrorStatus</code>
     * <p>Instantiates a new identity error status.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    IdentityErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * <code>getName</code>
     * <p>The get name getter method.</p>
     * @return {@link java.lang.String} <p>The get name return object is <code>String</code> type.</p>
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
