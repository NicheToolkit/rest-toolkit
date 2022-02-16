package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.identity.IdentityType;
import io.github.nichetoolkit.rest.error.supply.ParamMissingException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <p>IdentityProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.identity")
public class RestIdentityProperties {
    private Boolean enabled = false;

    private IdentityType type = IdentityType.AUTO;
    @NestedConfigurationProperty
    private Config config = new Config();
    @NestedConfigurationProperty
    private Server server = new Server();

    public RestIdentityProperties() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public IdentityType getType() {
        return type;
    }

    public void setType(IdentityType type) {
        this.type = type;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public static class Server {
        private Long sequence = 1L;
        private String url;
        private String api;

        public Server() {
        }

        public Long getSequence() {
            return sequence;
        }

        public void setSequence(Long sequence) {
            this.sequence = sequence;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public String uri() throws ParamMissingException {
            if (GeneralUtils.isNotEmpty(url)) {
                return url.concat(api);
            } else {
                throw new ParamMissingException("rest.identity.server.url");
            }

        }
    }

    public static class Config {
        private Long workerId = 1L;
        private Long centerId = 2L;
        private Long sequence = 1L;
        public Config() {
        }

        public Long getWorkerId() {
            return workerId;
        }

        public void setWorkerId(Long workerId) {
            this.workerId = workerId;
        }

        public Long getCenterId() {
            return centerId;
        }

        public void setCenterId(Long centerId) {
            this.centerId = centerId;
        }

        public Long getSequence() {
            return sequence;
        }

        public void setSequence(Long sequence) {
            this.sequence = sequence;
        }
    }
}
