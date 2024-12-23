package io.github.nichetoolkit.rest.identity.worker;



import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>IdentityWorker</code>
 * <p>The identity worker interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface IdentityWorker {
    /**
     * <code>IDENTITY_WORKER_MAP</code>
     * {@link java.util.concurrent.ConcurrentHashMap} <p>The constant <code>IDENTITY_WORKER_MAP</code> field.</p>
     * @see java.util.concurrent.ConcurrentHashMap
     */
    ConcurrentHashMap<WorkerType,IdentityWorker> IDENTITY_WORKER_MAP = new ConcurrentHashMap<>();

    /**
     * <code>generate</code>
     * <p>The generate method.</p>
     * @return {@link java.lang.Long} <p>The generate return object is <code>Long</code> type.</p>
     * @see java.lang.Long
     */
    Long generate();

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>The worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.worker.IdentityWorker} <p>The get return object is <code>IdentityWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     */
    static IdentityWorker get(WorkerType workerType) {
       return IDENTITY_WORKER_MAP.get(workerType);
    }

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.worker.IdentityWorker} <p>The get return object is <code>IdentityWorker</code> type.</p>
     */
    static IdentityWorker get() {
        if (IDENTITY_WORKER_MAP.containsKey(WorkerType.BASE_WORKER)) {
            return IDENTITY_WORKER_MAP.get(WorkerType.BASE_WORKER);
        } else {
            return new IdentityWorkerArtificial();
        }
    }

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @param offset {@link java.lang.Long} <p>The offset parameter is <code>Long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.worker.IdentityWorker} <p>The get return object is <code>IdentityWorker</code> type.</p>
     * @see java.lang.Long
     */
    static IdentityWorker get(Long offset) {
        if (IDENTITY_WORKER_MAP.containsKey(WorkerType.OFFSET_WORKER)) {
            return IDENTITY_WORKER_MAP.get(WorkerType.OFFSET_WORKER);
        } else {
            return new IdentityWorkerArtificial(offset);
        }
    }

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @param workerId {@link java.lang.Long} <p>The worker id parameter is <code>Long</code> type.</p>
     * @param centerId {@link java.lang.Long} <p>The center id parameter is <code>Long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.worker.IdentityWorker} <p>The get return object is <code>IdentityWorker</code> type.</p>
     * @see java.lang.Long
     */
    static IdentityWorker get(Long workerId, Long centerId) {
        if (IDENTITY_WORKER_MAP.containsKey(WorkerType.COMMON_WORKER)) {
            return IDENTITY_WORKER_MAP.get(WorkerType.COMMON_WORKER);
        } else {
            return new IdentityWorkerMachine(workerId, centerId);
        }
    }

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @param workerId {@link java.lang.Long} <p>The worker id parameter is <code>Long</code> type.</p>
     * @param centerId {@link java.lang.Long} <p>The center id parameter is <code>Long</code> type.</p>
     * @param sequence {@link java.lang.Long} <p>The sequence parameter is <code>Long</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.worker.IdentityWorker} <p>The get return object is <code>IdentityWorker</code> type.</p>
     * @see java.lang.Long
     */
    static IdentityWorker get(Long workerId, Long centerId, Long sequence) {
        if (IDENTITY_WORKER_MAP.containsKey(WorkerType.SEQUENCE_WORKER)) {
            return IDENTITY_WORKER_MAP.get(WorkerType.SEQUENCE_WORKER);
        } else {
            return new IdentityWorkerMachine(workerId, centerId, sequence);
        }
    }


}
