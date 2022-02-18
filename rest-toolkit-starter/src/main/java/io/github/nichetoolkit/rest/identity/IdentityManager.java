package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.http.RestTemplates;
import io.github.nichetoolkit.rest.configure.RestIdentityProperties;
import io.github.nichetoolkit.rest.helper.OptionalHelper;
import io.github.nichetoolkit.rest.identity.error.IdentityWorkerException;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;
import io.github.nichetoolkit.rest.identity.worker.WorkerConfig;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;

/**
 * <p>IdentityManager</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class IdentityManager implements ApplicationRunner {

    private final RestIdentityProperties identityProperties;

    private final Environment environment;

    private static IdentityManager INSTANCE = null;

    @Autowired
    public IdentityManager(RestIdentityProperties identityProperties, Environment environment) {
        this.identityProperties = identityProperties;
        this.environment = environment;
    }

    public static IdentityManager getInstance() {
        return INSTANCE;
    }

    public static Environment getEnvironment() {
        return IdentityManager.getInstance().environment;
    }

    @PostConstruct
    public void identityManagerInit() {
        INSTANCE = this;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (IdentityType.SERVER == identityProperties.getType()) {
            WorkerConfig workerConfig = workerConfig();
            OptionalHelper.nullable(workerConfig,"the worker config is parse error",IdentityWorkerException::new);
            log.debug("worker config: {}", JsonUtils.parseJson(workerConfig));
            IdentityManager.config(workerConfig);
        }
    }

    public static void config(@NonNull Long workerId, @NonNull Long centerId) {
        IdentityWorker.get(workerId, centerId);
    }

    public static void config(WorkerConfig config) {
        IdentityManager.config(config.getWorkerId(), config.getCenterId());
    }

    public static ServerConfig serverConfig() {
        String ip = IdentityManager.getEnvironment().getProperty(ServerConfig.IP_ADDRESS);
        String port = IdentityManager.getEnvironment().getProperty(ServerConfig.SERVER_PORT);
        String name = IdentityManager.getEnvironment().getProperty(ServerConfig.APP_NAME);
        return new ServerConfig(ip,port,name);
    }

    public static WorkerConfig workerConfig() throws RestException {
        String server = IdentityManager.serverConfig().toServer();
        MultiValueMap<String, String> serverId = RestTemplates.singletonMap("serverId", server);
        return RestTemplates.getObject(IdentityManager.getInstance().identityProperties.getServer().uri(), serverId, WorkerConfig.class);
    }


}
