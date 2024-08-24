package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.identity.IdentityType;
import io.github.nichetoolkit.rest.error.supply.ParamMissingException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <p>IdentityProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Data
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

    @Data
    public static class Server {
        private Long sequence = 1L;
        private String url;
        private String api;

        public Server() {
        }

        public String uri() throws ParamMissingException {
            if (GeneralUtils.isNotEmpty(url)) {
                return url.concat(api);
            } else {
                throw new ParamMissingException("rest.identity.server.url");
            }

        }
    }

    @Data
    public static class Config {
        private Long workerId = 1L;
        private Long centerId = 2L;
        private Long sequence = 1L;

        public Config() {
        }
    }
}
