package io.github.nichetoolkit.rest.identity.worker;

import io.github.nichetoolkit.rest.constant.RestConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * <code>IdentityWorkerArtificial</code>
 * <p>The identity worker artificial class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
class IdentityWorkerArtificial implements IdentityWorker {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see java.lang.String
     */
    private final String name;
    /**
     * <code>lastTime</code>
     * {@link java.lang.Long} <p>The <code>lastTime</code> field.</p>
     * @see java.lang.Long
     */
    private Long lastTime = IdentityWorkerConfig.TIMESTAMP;
    /**
     * <code>lastTag</code>
     * {@link java.lang.Long} <p>The <code>lastTag</code> field.</p>
     * @see java.lang.Long
     */
    private Long lastTag = IdentityWorkerConfig.DEFAULT_TAG;
    /**
     * <code>sequence</code>
     * {@link java.lang.Long} <p>The <code>sequence</code> field.</p>
     * @see java.lang.Long
     */
    private Long sequence = IdentityWorkerConfig.SEQUENCE;
    /**
     * <code>offset</code>
     * {@link java.lang.Long} <p>The <code>offset</code> field.</p>
     * @see java.lang.Long
     */
    private Long offset = IdentityWorkerConfig.OFFSET;

    /**
     * <code>IdentityWorkerArtificial</code>
     * <p>Instantiates a new identity worker artificial.</p>
     */
    public IdentityWorkerArtificial() {
        this.name = RestConstants.ARTIFICIAL_WORKER_NAME;
        IDENTITY_WORKER_MAP.put(WorkerType.BASE_WORKER, this);
    }

    /**
     * <code>IdentityWorkerArtificial</code>
     * <p>Instantiates a new identity worker artificial.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityWorkerArtificial(String name) {
        this.name = name;
        IDENTITY_WORKER_MAP.put(WorkerType.BASE_WORKER, this);
    }

    /**
     * <code>IdentityWorkerArtificial</code>
     * <p>Instantiates a new identity worker artificial.</p>
     * @param offset {@link java.lang.Long} <p>The offset parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    public IdentityWorkerArtificial(Long offset) {
        this.name = RestConstants.ARTIFICIAL_WORKER_NAME;
        if (offset > IdentityWorkerConfig.SEQUENCE) {
            this.sequence = offset;
        }
        if (offset > IdentityWorkerConfig.OFFSET) {
            this.offset = offset;
        }
        IDENTITY_WORKER_MAP.put(WorkerType.OFFSET_WORKER, this);
    }

    @Override
    public synchronized Long generate() {
        long time = new IdentityWorkerTime().getTime();
        if (this.offset < IdentityWorkerConfig.SEQUENCE) {
            offset = -offset;
        }
        if (time < IdentityWorkerConfig.TIMESTAMP) {
            time = IdentityWorkerTime.next(this.lastTime + IdentityWorkerConfig.DEFAULT_STEP);
            log.warn("clock is moving backwards. rejecting requests until {}", this.lastTime);
        }
        if (this.lastTime == time) {
            this.sequence = (this.sequence + IdentityWorkerConfig.DEFAULT_TAG) & IdentityWorkerConfig.SEQUENCE_MASK;
            if (this.sequence.equals(IdentityWorkerConfig.SEQUENCE)) {
                time = IdentityWorkerTime.next(this.lastTime);
            }
        } else {
            this.sequence = IdentityWorkerConfig.SEQUENCE;
        }
        if (offset.equals(lastTag)) {
            this.sequence = (this.sequence + IdentityWorkerConfig.DEFAULT_TAG) & IdentityWorkerConfig.SEQUENCE_MASK;
            if (this.sequence.equals(IdentityWorkerConfig.SEQUENCE)) {
                time = IdentityWorkerTime.next(this.lastTime);
            }
        }
        this.lastTag = offset;
        this.lastTime = time;
        return (time << (IdentityWorkerConfig.ALL_BIT_SIZE - IdentityWorkerConfig.TIMESTAMP_BIT_SIZE))
                | ((offset % IdentityWorkerConfig.MAX_REGION_SIZE) << (IdentityWorkerConfig.ALL_BIT_SIZE - IdentityWorkerConfig.TIMESTAMP_BIT_SIZE - IdentityWorkerConfig.THREAD_BIT_SIZE - IdentityWorkerConfig.REGION_BIT_SIZE))
                | sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof IdentityWorkerArtificial)) return false;
        IdentityWorkerArtificial that = (IdentityWorkerArtificial) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "IdentityWorkerArtificial{" +
                "name='" + name + '\'' +
                '}';
    }
}
