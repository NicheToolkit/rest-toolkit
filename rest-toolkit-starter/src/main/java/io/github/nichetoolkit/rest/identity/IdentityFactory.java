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
 * <p>IdentityFactory</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class IdentityFactory {

    private final RestIdentityProperties properties;

    private static IdentityFactory INSTANCE = null;

    @Autowired
    public IdentityFactory(RestIdentityProperties properties) {
        this.properties = properties;
    }

    public static IdentityFactory getInstance() {
        return INSTANCE;
    }

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

    @PostConstruct
    public void identityWorkerInit() {
        log.debug("identity properties: {}", JsonUtils.parseJson(properties));
        IdentityType type = properties.getType();

        if (IdentityType.AUTO == type) {
            IdentityWorker.get(1L);
            Long workerId = ((Double) (new SecureRandom().nextDouble() * 10 + 20)).longValue();
            Long centerId = ((Double) (new SecureRandom().nextDouble() * 20 + 10)).longValue();
            IdentityWorker.get(workerId,centerId);
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
        }
        IdentityWorker.get();
        INSTANCE = this;
    }
}
