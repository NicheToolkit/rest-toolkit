package io.github.nichetoolkit.rest.identity.worker;


import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.identity.IdentityErrorStatus;
import io.github.nichetoolkit.rest.identity.error.IdentityWorkerError;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * <code>IdentityWorkerMachine</code>
 * <p>The type identity worker machine class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
class IdentityWorkerMachine implements IdentityWorker{
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>the <code>name</code> field.</p>
     * @see java.lang.String
     */
    private final String name;
    /**
     * <code>lastTime</code>
     * {@link java.lang.Long} <p>the <code>lastTime</code> field.</p>
     * @see java.lang.Long
     */
    private Long lastTime = IdentityWorkerConfig.TIMESTAMP;
    /**
     * <code>sequence</code>
     * {@link java.lang.Long} <p>the <code>sequence</code> field.</p>
     * @see java.lang.Long
     */
    private Long sequence = IdentityWorkerConfig.SEQUENCE;
    /**
     * <code>workerId</code>
     * {@link java.lang.Long} <p>the <code>workerId</code> field.</p>
     * @see java.lang.Long
     */
    private final Long workerId;
    /**
     * <code>centerId</code>
     * {@link java.lang.Long} <p>the <code>centerId</code> field.</p>
     * @see java.lang.Long
     */
    private final Long centerId;
    /**
     * <code>isOffset</code>
     * {@link boolean} <p>the <code>isOffset</code> field.</p>
     */
    private boolean isOffset;

    /**
     * <code>IdentityWorkerMachine</code>
     * Instantiates a new identity worker machine.
     * @param workerId {@link java.lang.Long} <p>the worker id parameter is <code>Long</code> type.</p>
     * @param centerId {@link java.lang.Long} <p>the center id parameter is <code>Long</code> type.</p>
     * @param sequence {@link java.lang.Long} <p>the sequence parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    public IdentityWorkerMachine(@NonNull Long workerId, @NonNull Long centerId, Long sequence) {
        this(RestConstants.MACHINE_WORKER_NAME,workerId,centerId,sequence);
    }

    /**
     * <code>IdentityWorkerMachine</code>
     * Instantiates a new identity worker machine.
     * @param mame     {@link java.lang.String} <p>the mame parameter is <code>String</code> type.</p>
     * @param workerId {@link java.lang.Long} <p>the worker id parameter is <code>Long</code> type.</p>
     * @param centerId {@link java.lang.Long} <p>the center id parameter is <code>Long</code> type.</p>
     * @param sequence {@link java.lang.Long} <p>the sequence parameter is <code>Long</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    public IdentityWorkerMachine(String mame, @NonNull Long workerId, @NonNull Long centerId, Long sequence) {
        this.name = mame;
        if (workerId > IdentityWorkerConfig.MAX_WORKER_ID || workerId < IdentityWorkerConfig.MIN_WORKER_ID) {
            log.error("worker Id can't be greater than {} or less than {}",IdentityWorkerConfig.MAX_WORKER_ID,IdentityWorkerConfig.MIN_WORKER_ID);
            throw new IdentityWorkerError(IdentityErrorStatus.WORKER_ID_INVALID);
        }
        if (centerId > IdentityWorkerConfig.MAX_CENTER_ID || centerId < IdentityWorkerConfig.MIN_CENTER_ID) {
            log.error("center Id can't be greater than {} or less than {}",IdentityWorkerConfig.MAX_CENTER_ID,IdentityWorkerConfig.MIN_CENTER_ID);
            throw new IdentityWorkerError(IdentityErrorStatus.CENTER_ID_INVALID);
        }
        this.workerId = workerId;
        this.centerId = centerId;
        if (sequence != null && sequence >= IdentityWorkerConfig.SEQUENCE) {
            this.sequence = sequence;
            IDENTITY_WORKER_MAP.put(WorkerType.SEQUENCE_WORKER,this);
        }
        IDENTITY_WORKER_MAP.put(WorkerType.COMMON_WORKER,this);
    }

    /**
     * <code>IdentityWorkerMachine</code>
     * Instantiates a new identity worker machine.
     * @param workerId {@link java.lang.Long} <p>the worker id parameter is <code>Long</code> type.</p>
     * @param centerId {@link java.lang.Long} <p>the center id parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    public IdentityWorkerMachine(@NonNull Long workerId, @NonNull Long centerId) {
        this(workerId,centerId,null);
    }

    @Override
    public synchronized Long generate() {
        if (IdentityWorkerConfig.CACHE_SET.size() >= IdentityWorkerConfig.CACHE_SIZE) {
            IdentityWorkerConfig.CACHE_SET.clear();
            isOffset = true;
        }
        Long time = new IdentityWorkerTime().getTime();
        if (GeneralUtils.isNotEmpty(this.sequence)) {
            time = new IdentityWorkerTime().sequence(sequence);
        }
        if (time < this.lastTime) {
            this.sequence ++;
            int offset = 0;
            if (isOffset) {
                offset = IdentityWorkerConfig.CACHE_SIZE + 1;
                isOffset = false;
            }
            time = new IdentityWorkerTime().sequence(Math.abs(this.lastTime - time) + this.sequence + offset);
            log.warn("clock is moving backwards. Rejecting requests until {}", this.lastTime);
        }
        if (this.lastTime.equals(time)) {
            this.sequence = (this.sequence + IdentityWorkerConfig.DEFAULT_TAG) & IdentityWorkerConfig.SEQUENCE_MASK;
            if (this.sequence.equals(IdentityWorkerConfig.SEQUENCE)) {
                time = IdentityWorkerTime.next(this.lastTime);
            }
        } else {
            this.sequence = IdentityWorkerConfig.SEQUENCE;
        }
        this.lastTime = time;

        long generateId = (time << IdentityWorkerConfig.TIMESTAMP_SHIFT)
                | (centerId << IdentityWorkerConfig.CENTER_ID_SHIFT)
                | (workerId << IdentityWorkerConfig.WORKER_ID_SHIFT)
                | sequence;
        if (IdentityWorkerConfig.CACHE_SET.contains(generateId)) {
            return generate();
        } else {
            IdentityWorkerConfig.CACHE_SET.add(generateId);
            return generateId;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (! (o instanceof IdentityWorkerMachine)) return false;
        IdentityWorkerMachine that = (IdentityWorkerMachine) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "IdentityWorkerMachine{" +
                "name='" + name + '\'' +
                '}';
    }
}
