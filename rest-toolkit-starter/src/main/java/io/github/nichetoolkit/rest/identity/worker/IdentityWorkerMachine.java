package io.github.nichetoolkit.rest.identity.worker;


import io.github.nichetoolkit.rest.constant.RestConstants;
import io.github.nichetoolkit.rest.identity.IdentityErrorStatus;
import io.github.nichetoolkit.rest.identity.error.IdentityWorkerError;
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
class IdentityWorkerMachine implements IdentityWorker {
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
     * <code>offset</code>
     * {@link java.lang.Long} <p>the <code>offset</code> field.</p>
     * @see java.lang.Long
     */
    private Long offset = IdentityWorkerConfig.SEQUENCE;

    /**
     * <code>step</code>
     * {@link java.lang.Long} <p>the <code>step</code> field.</p>
     * @see java.lang.Long
     */
    private Long step = IdentityWorkerConfig.DEFAULT_STEP;
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
     * <code>lastThreadId</code>
     * {@link java.lang.Long} <p>the <code>lastThreadId</code> field.</p>
     * @see java.lang.Long
     */
    private Long lastThreadId = IdentityWorkerConfig.SEQUENCE;
    /**
     * <code>isOffset</code>
     * <p>the <code>isOffset</code> field.</p>
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
        this(RestConstants.MACHINE_WORKER_NAME, workerId, centerId, sequence);
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
            log.error("worker Id can't be greater than {} or less than {}", IdentityWorkerConfig.MAX_WORKER_ID, IdentityWorkerConfig.MIN_WORKER_ID);
            throw new IdentityWorkerError(IdentityErrorStatus.WORKER_ID_INVALID);
        }
        if (centerId > IdentityWorkerConfig.MAX_CENTER_ID || centerId < IdentityWorkerConfig.MIN_CENTER_ID) {
            log.error("center Id can't be greater than {} or less than {}", IdentityWorkerConfig.MAX_CENTER_ID, IdentityWorkerConfig.MIN_CENTER_ID);
            throw new IdentityWorkerError(IdentityErrorStatus.CENTER_ID_INVALID);
        }
        this.workerId = workerId;
        this.centerId = centerId;
        if (sequence != null && sequence >= IdentityWorkerConfig.SEQUENCE) {
            this.sequence = sequence;
            IDENTITY_WORKER_MAP.put(WorkerType.SEQUENCE_WORKER, this);
        }
        IDENTITY_WORKER_MAP.put(WorkerType.COMMON_WORKER, this);
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
        this(workerId, centerId, null);
    }

    @Override
    public synchronized Long generate() {
        /*
         * the thread is got from <code>Thread.currentThread()</code>
         * the max thread id must be less than 512 (2^9), due to
         * the difference between threads id will be used to wait
         * on <code>IdentityWorkerTime().next()</code> method, the
         * most time will be 512 milliseconds on worker time.
         */
        long threadId = Thread.currentThread().getId();
        threadId = threadId & IdentityWorkerConfig.THREAD_ID_MASK;
        if (threadId == IdentityWorkerConfig.SEQUENCE) {
            threadId = Math.abs(IdentityWorkerConfig.MAX_THREAD_ID - threadId);
        }
        /*
         * the sequence bit will be 12 ~ 14 bit, default 14 bit
         * the center id bit will be 4 ~ 5 bit, default 4 bit
         * the worker id bit will be 4 ~ 5 bit, default 4 bit
         */
        long centerIdShift = IdentityWorkerConfig.CENTER_ID_SHIFT;
        long workerIdShift = IdentityWorkerConfig.WORKER_ID_SHIFT;
        long sequenceBit = IdentityWorkerConfig.SEQUENCE_BIT;
        boolean centerIdComparer = centerId > IdentityWorkerConfig.MAX_CENTER_ID;
        boolean workerIdComparer = workerId > IdentityWorkerConfig.MAX_WORKER_ID;
        if (centerIdComparer && workerIdComparer) {
            centerIdShift = centerIdShift - 1L;
            workerIdShift = workerIdShift - 2L;
            sequenceBit = sequenceBit - 2L;
        } else if (centerIdComparer) {
            centerIdShift = centerIdShift - 1L;
            workerIdShift = workerIdShift - 1L;
            sequenceBit = sequenceBit - 1L;
        } else if (workerIdComparer) {
            workerIdShift = workerIdShift - 1L;
            sequenceBit = sequenceBit - 1L;
        }
        long time = new IdentityWorkerTime().getTime();
        long sequenceMask = ~(IdentityWorkerConfig.TIMESTAMP << sequenceBit);
        /* the time clock fix to lastTime */
        if (time < this.lastTime) {
            time = IdentityWorkerTime.next(this.lastTime + IdentityWorkerConfig.DEFAULT_STEP);
            log.warn("clock is moving backwards. rejecting requests until {}", this.lastTime);
        }
        /* the time equal last time on after time clock fix */
        if (this.lastTime.equals(time)) {
            this.sequence = (this.sequence + IdentityWorkerConfig.DEFAULT_STEP) & sequenceMask;
            if (this.sequence.equals(IdentityWorkerConfig.SEQUENCE)) {
                time = IdentityWorkerTime.next(this.lastTime + IdentityWorkerConfig.DEFAULT_STEP);
            }
        } else {
            this.sequence = IdentityWorkerConfig.SEQUENCE;
        }
        this.lastTime = time;
        return (time << IdentityWorkerConfig.TIMESTAMP_SHIFT)
                | (this.centerId << centerIdShift)
                | (this.workerId << workerIdShift)
                | this.sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof IdentityWorkerMachine)) return false;
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
