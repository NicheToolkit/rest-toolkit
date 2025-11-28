package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.util.I18nUtils;
import lombok.Getter;

/**
 * <code>IdentityErrorStatus</code>
 * <p>The identity error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk17
 */
@Getter
public enum IdentityErrorStatus implements RestStatus {
    /**
     * <code>WORKER_ID_INVALID</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityErrorStatus} <p>The <code>WORKER_ID_INVALID</code> field.</p>
     */
    WORKER_ID_INVALID(10530,"The worker id is invalid"),
    /**
     * <code>CENTER_ID_INVALID</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityErrorStatus} <p>The <code>CENTER_ID_INVALID</code> field.</p>
     */
    CENTER_ID_INVALID(10531,"The center id is invalid"),
    /**
     * <code>IDENTITY_WORKER_ERROR</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityErrorStatus} <p>The <code>IDENTITY_WORKER_ERROR</code> field.</p>
     */
    IDENTITY_WORKER_ERROR(10532,"It has encountered a identity worker related error"),
    /**
     * <code>IDENTITY_WORKER_TIME_ERROR</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityErrorStatus} <p>The <code>IDENTITY_WORKER_TIME_ERROR</code> field.</p>
     */
    IDENTITY_WORKER_TIME_ERROR(10533,"The time of identity worker is invalid"),
    /**
     * <code>WORKER_TYPE_IS_NULL</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityErrorStatus} <p>The <code>WORKER_TYPE_IS_NULL</code> field.</p>
     */
    WORKER_TYPE_IS_NULL(10534,"The worker type is empty"),
    /**
     * <code>IDENTITY_WORKER_UNAVAILABLE</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityErrorStatus} <p>The <code>IDENTITY_WORKER_UNAVAILABLE</code> field.</p>
     */
    IDENTITY_WORKER_UNAVAILABLE(10535,"The identity worker is unavailable"),
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

    @Override
    public String getMessage() {
        return I18nUtils.message(name(), this.message);
    }
}
