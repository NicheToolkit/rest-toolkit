package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.configure.RestIdentityProperties;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;
import io.github.nichetoolkit.rest.identity.worker.WorkerType;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;

/**
 * <code>IdentityFactory</code>
 * <p>The identity factory class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class IdentityFactory {

    /**
     * <code>properties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>The <code>properties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     */
    private final RestIdentityProperties properties;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityFactory} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static IdentityFactory INSTANCE = null;

    /**
     * <code>IdentityFactory</code>
     * <p>Instantiates a new identity factory.</p>
     * @param properties {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>The properties parameter is <code>RestIdentityProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public IdentityFactory(RestIdentityProperties properties) {
        this.properties = properties;
    }

    /**
     * <code>getInstance</code>
     * <p>The get instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.IdentityFactory} <p>The get instance return object is <code>IdentityFactory</code> type.</p>
     */
    public static IdentityFactory getInstance() {
        return INSTANCE;
    }

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @param workerType {@link io.github.nichetoolkit.rest.identity.worker.WorkerType} <p>The worker type parameter is <code>WorkerType</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.worker.IdentityWorker} <p>The get return object is <code>IdentityWorker</code> type.</p>
     * @throws RestError {@link io.github.nichetoolkit.rest.RestError} <p>The rest error is <code>RestError</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerType
     * @see io.github.nichetoolkit.rest.identity.worker.IdentityWorker
     * @see io.github.nichetoolkit.rest.RestError
     */
    public IdentityWorker get(WorkerType workerType) throws RestError {
        if (workerType != null) {
            IdentityWorker identityWorker = IdentityWorker.get(workerType);
            if (identityWorker != null) {
                return identityWorker;
            } else {
                log.error("identity worker maybe haven't initiated!");
                throw new RestError(IdentityErrorStatus.IDENTITY_WORKER_UNAVAILABLE);
            }
        } else {
            log.error("workerType cannot be null when getting identityWorker from it's!");
            throw new RestError(IdentityErrorStatus.WORKER_TYPE_IS_NULL);
        }
    }

    /**
     * <code>identityWorkerInit</code>
     * <p>The identity worker init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void identityWorkerInit() {
        log.debug("The identity   properties: {}", JsonUtils.parseJson(properties));
        IdentityType type = properties.getType();

        if (IdentityType.AUTO == type) {
            IdentityWorker.get(1L);
            Long workerId = ((Double) (new SecureRandom().nextDouble() * 10 + 20)).longValue();
            Long centerId = ((Double) (new SecureRandom().nextDouble() * 20 + 10)).longValue();
            IdentityWorker.get(workerId,centerId);
            IdentityWorker.get(workerId,centerId,1L);
        } else if (IdentityType.SERVER == type) {
            Long sequence = properties.getServer().getSequence();
            IdentityWorker.get(sequence);
            log.warn("waiting for identity config to initiate!");
        } else {
            Long sequence = properties.getConfig().getSequence();
            IdentityWorker.get(sequence);
            Long workerId = properties.getConfig().getWorkerId();
            Long centerId = properties.getConfig().getCenterId();
            IdentityWorker.get(workerId, centerId);
            IdentityWorker.get(workerId, centerId, sequence);
        }
        IdentityWorker.get();
        INSTANCE = this;
    }
}
