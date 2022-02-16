package io.github.nichetoolkit.rest.identity.worker;

import io.github.nichetoolkit.rest.constant.RestConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * <p>IdentityWorkerArtificial</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
class IdentityWorkerArtificial implements IdentityWorker {
    private final String name;
    private Long lastTime = IdentityWorkerConfig.TIMESTAMP;
    private Long lastTag = IdentityWorkerConfig.DEFAULT_TAG;
    private Long sequence = IdentityWorkerConfig.SEQUENCE;
    private Long offset = IdentityWorkerConfig.OFFSET;

    public IdentityWorkerArtificial() {
        this.name = RestConstants.ARTIFICIAL_WORKER_NAME;
        IDENTITY_WORKER_MAP.put(WorkerType.BASE_WORKER,this);
    }

    public IdentityWorkerArtificial(String name) {
        this.name = name;
        IDENTITY_WORKER_MAP.put(WorkerType.BASE_WORKER,this);
    }

    public IdentityWorkerArtificial(Long offset) {
        this.name = RestConstants.ARTIFICIAL_WORKER_NAME;
        if (offset > IdentityWorkerConfig.SEQUENCE) {
            this.sequence = offset;
        }
        if (offset > IdentityWorkerConfig.OFFSET) {
            this.offset = offset;
        }
        IDENTITY_WORKER_MAP.put(WorkerType.OFFSET_WORKER,this);
    }

    @Override
    public synchronized Long generate() {
        long time = new IdentityWorkerTime().getTime();
        if(this.offset < IdentityWorkerConfig.SEQUENCE){
            offset = -offset;
        }
        if (time < IdentityWorkerConfig.TIMESTAMP) {
            time = new IdentityWorkerTime().sequence((Math.abs(this.lastTime - time) * this.sequence) + this.offset);
            log.error("clock is moving backwards. Rejecting requests until {}", this.lastTime);
        }
        if (this.lastTime == time) {
            this.sequence = (this.sequence + IdentityWorkerConfig.DEFAULT_TAG) & IdentityWorkerConfig.SEQUENCE_MASK;
            if (this.sequence.equals(IdentityWorkerConfig.SEQUENCE)) {
                time = IdentityWorkerTime.next(this.lastTime);
            }
        } else {
            this.sequence = IdentityWorkerConfig.SEQUENCE;
        }
        if(offset.equals(lastTag)){
            this.sequence = (this.sequence + IdentityWorkerConfig.DEFAULT_TAG) & IdentityWorkerConfig.SEQUENCE_MASK;
            if (this.sequence.equals(IdentityWorkerConfig.SEQUENCE)) {
                time = IdentityWorkerTime.next(this.lastTime);
            }
        }
        this.lastTag = offset;
        this.lastTime = time;

        return (time << (IdentityWorkerConfig.ALL_BIT_SIZE - IdentityWorkerConfig.TIMESTAMP_BIT_SIZE))
                | ((offset % IdentityWorkerConfig.MAX_REGION_SIZE) << (IdentityWorkerConfig.ALL_BIT_SIZE - IdentityWorkerConfig.TIMESTAMP_BIT_SIZE - IdentityWorkerConfig.REGION_BIT_SIZE))
                | sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
