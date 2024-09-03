package io.github.nichetoolkit.rest.identity.worker;

import io.github.nichetoolkit.rest.actuator.SupplierActuator;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * <code>IdentityWorkerTime</code>
 * <p>The type identity worker time class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
 * @see lombok.Data
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Data
@SuppressWarnings("SameNameButDifferent")
class IdentityWorkerTime implements SupplierActuator {
    /**
     * <code>EPOCH</code>
     * {@link java.lang.Long} <p>the constant <code>EPOCH</code> field.</p>
     * @see java.lang.Long
     */
    public static final Long EPOCH = 1288834974657L;
    /**
     * <code>time</code>
     * {@link java.lang.Long} <p>the <code>time</code> field.</p>
     * @see java.lang.Long
     */
    private Long time;

    /**
     * <code>IdentityWorkerTime</code>
     * Instantiates a new identity worker time.
     */
    public IdentityWorkerTime() {
        this.time = System.currentTimeMillis() - EPOCH;
    }

    /**
     * <code>IdentityWorkerTime</code>
     * Instantiates a new identity worker time.
     * @param time {@link java.lang.Long} <p>the time parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    public IdentityWorkerTime(@NonNull Long time) {
        this.time = time - EPOCH;
    }

    /**
     * <code>sequence</code>
     * <p>the method.</p>
     * @param sequence {@link java.lang.Long} <p>the sequence parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>the return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public Long sequence(Long sequence) {
        if (sequence > IdentityWorkerConfig.SEQUENCE) {
            return time + sequence;
        } else {
            return time;
        }
    }

    /**
     * <code>next</code>
     * <p>the method.</p>
     * @param lastTime {@link java.lang.Long} <p>the last time parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>the return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public static Long next(Long lastTime) {
        Long time = new IdentityWorkerTime().getTime();
        while (time <= lastTime) {
            time = new IdentityWorkerTime().getTime();
        }
        return time;
    }

    @Override
    public IdentityWorkerTime get() {
        return this;
    }
}
