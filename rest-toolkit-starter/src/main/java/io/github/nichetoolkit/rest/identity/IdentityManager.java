package io.github.nichetoolkit.rest.identity;

import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.http.RestTemplates;
import io.github.nichetoolkit.rest.configure.RestIdentityProperties;
import io.github.nichetoolkit.rest.identity.error.IdentityWorkerException;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;
import io.github.nichetoolkit.rest.identity.worker.WorkerConfig;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.OptionalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;

/**
 * <code>IdentityManager</code>
 * <p>The type identity manager class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.boot.ApplicationRunner
 * @see lombok.extern.slf4j.Slf4j
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
public class IdentityManager implements ApplicationRunner {

    /**
     * <code>identityProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>The <code>identityProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     */
    private final RestIdentityProperties identityProperties;

    /**
     * <code>environment</code>
     * {@link org.springframework.core.env.Environment} <p>The <code>environment</code> field.</p>
     * @see org.springframework.core.env.Environment
     */
    private final Environment environment;

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityManager} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static IdentityManager INSTANCE = null;

    /**
     * <code>IdentityManager</code>
     * <p>Instantiates a new identity manager.</p>
     * @param identityProperties {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>The identity properties parameter is <code>RestIdentityProperties</code> type.</p>
     * @param environment        {@link org.springframework.core.env.Environment} <p>The environment parameter is <code>Environment</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     * @see org.springframework.core.env.Environment
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public IdentityManager(RestIdentityProperties identityProperties, Environment environment) {
        this.identityProperties = identityProperties;
        this.environment = environment;
    }

    /**
     * <code>getInstance</code>
     * <p>The instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.IdentityManager} <p>The instance return object is <code>IdentityManager</code> type.</p>
     */
    public static IdentityManager getInstance() {
        return INSTANCE;
    }

    /**
     * <code>getEnvironment</code>
     * <p>The environment getter method.</p>
     * @return {@link org.springframework.core.env.Environment} <p>The environment return object is <code>Environment</code> type.</p>
     * @see org.springframework.core.env.Environment
     */
    public static Environment getEnvironment() {
        return IdentityManager.getInstance().environment;
    }

    /**
     * <code>identityManagerInit</code>
     * <p>The manager init method.</p>
     * @see javax.annotation.PostConstruct
     */
    @PostConstruct
    public void identityManagerInit() {
        INSTANCE = this;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (IdentityType.SERVER == identityProperties.getType()) {
            WorkerConfig workerConfig = workerConfig();
            OptionalUtils.ofNull(workerConfig, "The worker config is parse error", log, IdentityWorkerException::new);
            log.debug("The worker   configuration: {}", JsonUtils.parseJson(workerConfig));
            IdentityManager.config(workerConfig);
        }
    }

    /**
     * <code>config</code>
     * <p>The method.</p>
     * @param workerId {@link java.lang.Long} <p>The worker id parameter is <code>Long</code> type.</p>
     * @param centerId {@link java.lang.Long} <p>The center id parameter is <code>Long</code> type.</p>
     * @see java.lang.Long
     * @see org.springframework.lang.NonNull
     */
    public static void config(@NonNull Long workerId, @NonNull Long centerId) {
        IdentityWorker.get(workerId, centerId);
    }

    /**
     * <code>config</code>
     * <p>The method.</p>
     * @param config {@link io.github.nichetoolkit.rest.identity.worker.WorkerConfig} <p>The config parameter is <code>WorkerConfig</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerConfig
     */
    public static void config(WorkerConfig config) {
        IdentityManager.config(config.getWorkerId(), config.getCenterId());
    }

    /**
     * <code>serverConfig</code>
     * <p>The config method.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.ServerConfig} <p>The config return object is <code>ServerConfig</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.ServerConfig
     */
    public static ServerConfig serverConfig() {
        String ip = IdentityManager.getEnvironment().getProperty(ServerConfig.IP_ADDRESS);
        String port = IdentityManager.getEnvironment().getProperty(ServerConfig.SERVER_PORT);
        String name = IdentityManager.getEnvironment().getProperty(ServerConfig.APP_NAME);
        return new ServerConfig(ip, port, name);
    }

    /**
     * <code>workerConfig</code>
     * <p>The config method.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.worker.WorkerConfig} <p>The config return object is <code>WorkerConfig</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     * @see io.github.nichetoolkit.rest.identity.worker.WorkerConfig
     * @see io.github.nichetoolkit.rest.RestException
     */
    public static WorkerConfig workerConfig() throws RestException {
        String server = IdentityManager.serverConfig().toServer();
        MultiValueMap<String, String> serverId = RestTemplates.singletonMap("serverId", server);
        return RestTemplates.getObject(IdentityManager.getInstance().identityProperties.getServer().uri(), serverId, WorkerConfig.class);
    }


}
