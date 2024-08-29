package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.identity.IdentityType;
import io.github.nichetoolkit.rest.error.supply.ParamMissingException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <code>RestIdentityProperties</code>
 * <p>The type rest identity properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
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

    /**
     * <code>Server</code>
     * <p>The type server class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Data
     * @since Jdk1.8
     */
    @Data
    public static class Server {
        private Long sequence = 1L;
        private String url;
        private String api;

        /**
         * <code>uri</code>
         * <p>the method.</p>
         * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
         * @throws ParamMissingException {@link io.github.nichetoolkit.rest.error.supply.ParamMissingException} <p>the param missing exception is <code>ParamMissingException</code> type.</p>
         * @see java.lang.String
         * @see io.github.nichetoolkit.rest.error.supply.ParamMissingException
         */
        public String uri() throws ParamMissingException {
            if (GeneralUtils.isNotEmpty(url)) {
                return url.concat(api);
            } else {
                throw new ParamMissingException("rest.identity.server.url");
            }

        }
    }

    /**
     * <code>Config</code>
     * <p>The type config class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Data
     * @since Jdk1.8
     */
    @Data
    public static class Config {
        private Long workerId = 1L;
        private Long centerId = 2L;
        private Long sequence = 1L;
    }
}
