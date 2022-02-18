package io.github.nichetoolkit.rest.identity.worker;

import io.github.nichetoolkit.rest.actuator.SupplierActuator;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * <p>IdentityWorkerTime</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
class IdentityWorkerTime implements SupplierActuator {
    public static final Long EPOCH = 1288834974657L;
    private Long time;

    public IdentityWorkerTime() {
        this.time = System.currentTimeMillis() - EPOCH;
    }

    public IdentityWorkerTime(@NonNull Long time) {
        this.time = time - EPOCH;
    }

    public Long sequence(Long sequence) {
        if (sequence > IdentityWorkerConfig.SEQUENCE) {
            return time + sequence;
        } else {
            return time;
        }
    }

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
