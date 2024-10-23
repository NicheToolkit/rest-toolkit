package io.github.nichetoolkit.rest.identity.worker;

import io.github.nichetoolkit.rest.actuator.SupplierActuator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

/**
 * <code>IdentityWorkerTime</code>
 * <p>The identity worker time class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.actuator.SupplierActuator
 * @see lombok.Getter
 * @see lombok.Setter
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Getter
@Setter
@SuppressWarnings("SameNameButDifferent")
class IdentityWorkerTime implements SupplierActuator<IdentityWorkerTime> {
    /**
     * <code>EPOCH</code>
     * {@link java.lang.Long} <p>The constant <code>EPOCH</code> field.</p>
     * @see java.lang.Long
     */
    public static final Long EPOCH = 1288834974657L;
    /**
     * <code>time</code>
     * {@link java.lang.Long} <p>The <code>time</code> field.</p>
     * @see java.lang.Long
     */
    private Long time;

    /**
     * <code>IdentityWorkerTime</code>
     * <p>Instantiates a new identity worker time.</p>
     */
    public IdentityWorkerTime() {
        this.time = System.currentTimeMillis() - EPOCH;
    }

    /**
     * <code>IdentityWorkerTime</code>
     * <p>Instantiates a new identity worker time.</p>
     * @param time {@link java.lang.Long} <p>The time parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    public IdentityWorkerTime(@NonNull Long time) {
        this.time = time - EPOCH;
    }

    /**
     * <code>sequence</code>
     * <p>The sequence method.</p>
     * @param sequence {@link java.lang.Long} <p>The sequence parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>The sequence return object is <code>Long</code> type.</p>
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
     * <p>The next method.</p>
     * @param lastTime {@link java.lang.Long} <p>The last time parameter is <code>Long</code> type.</p>
     * @return {@link java.lang.Long} <p>The next return object is <code>Long</code> type.</p>
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
    public IdentityWorkerTime actuate() {
        return this;
    }
}
