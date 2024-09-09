package io.github.nichetoolkit.rest.identity.worker;

import io.github.nichetoolkit.rest.constant.RestConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * <code>IdentityWorkerArtificial</code>
 * <p>The type identity worker artificial class.</p>
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
     * <code>lastTag</code>
     * {@link java.lang.Long} <p>the <code>lastTag</code> field.</p>
     * @see java.lang.Long
     */
    private Long lastTag = IdentityWorkerConfig.DEFAULT_TAG;
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
    private Long offset = IdentityWorkerConfig.OFFSET;

    /**
     * <code>isOffset</code>
     * <p>the <code>isOffset</code> field.</p>
     */
    private boolean isOffset;

    /**
     * <code>IdentityWorkerArtificial</code>
     * Instantiates a new identity worker artificial.
     */
    public IdentityWorkerArtificial() {
        this.name = RestConstants.ARTIFICIAL_WORKER_NAME;
        IDENTITY_WORKER_MAP.put(WorkerType.BASE_WORKER, this);
    }

    /**
     * <code>IdentityWorkerArtificial</code>
     * Instantiates a new identity worker artificial.
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public IdentityWorkerArtificial(String name) {
        this.name = name;
        IDENTITY_WORKER_MAP.put(WorkerType.BASE_WORKER, this);
    }

    /**
     * <code>IdentityWorkerArtificial</code>
     * Instantiates a new identity worker artificial.
     * @param offset {@link java.lang.Long} <p>the offset parameter is <code>Long</code> type.</p>
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
        if (IdentityWorkerConfig.ARTIFICIAL_CACHE_SET.size() >= IdentityWorkerConfig.CACHE_SIZE) {
            IdentityWorkerConfig.ARTIFICIAL_CACHE_SET.clear();
            isOffset = true;
        }
        long time = new IdentityWorkerTime().getTime();
        if (this.offset < IdentityWorkerConfig.SEQUENCE) {
            offset = -offset;
        }
        if (time < IdentityWorkerConfig.TIMESTAMP) {
            this.sequence++;
            int offset = 0;
            if (isOffset) {
                offset = IdentityWorkerConfig.CACHE_SIZE + 1;
                isOffset = false;
            }
            time = new IdentityWorkerTime().sequence((Math.abs(this.lastTime - time) * this.sequence) + this.offset + offset);
//            log.warn("clock is moving backwards. Rejecting requests until {}", this.lastTime);
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
        long generateId = getGenerateId(time);
        if (IdentityWorkerConfig.ARTIFICIAL_CACHE_SET.contains(generateId)) {
            return generate();
        } else {
            IdentityWorkerConfig.ARTIFICIAL_CACHE_SET.add(generateId);
            return generateId;
        }
    }

    /**
     * <code>getGenerateId</code>
     * <p>the generate id getter method.</p>
     * @param time long <p>the time parameter is <code>long</code> type.</p>
     * @return long <p>the generate id return object is <code>long</code> type.</p>
     */
    private long getGenerateId(long time) {
        long threadId = Thread.currentThread().getId();
        return (time << (IdentityWorkerConfig.ALL_BIT_SIZE - IdentityWorkerConfig.TIMESTAMP_BIT_SIZE))
                | (threadId << (IdentityWorkerConfig.ALL_BIT_SIZE - IdentityWorkerConfig.TIMESTAMP_BIT_SIZE - IdentityWorkerConfig.THREAD_ID_SHIFT))
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
