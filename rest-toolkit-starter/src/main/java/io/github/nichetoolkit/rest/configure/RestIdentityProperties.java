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
    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>the <code>enabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean enabled = false;

    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rest.identity.IdentityType} <p>the <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rest.identity.IdentityType
     */
    private IdentityType type = IdentityType.AUTO;
    /**
     * <code>config</code>
     * {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties.Config} <p>the <code>config</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties.Config
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private Config config = new Config();
    /**
     * <code>server</code>
     * {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties.Server} <p>the <code>server</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties.Server
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
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
        /**
         * <code>sequence</code>
         * {@link java.lang.Long} <p>the <code>sequence</code> field.</p>
         * @see java.lang.Long
         */
        private Long sequence = 1L;
        /**
         * <code>url</code>
         * {@link java.lang.String} <p>the <code>url</code> field.</p>
         * @see java.lang.String
         */
        private String url;
        /**
         * <code>api</code>
         * {@link java.lang.String} <p>the <code>api</code> field.</p>
         * @see java.lang.String
         */
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
        /**
         * <code>workerId</code>
         * {@link java.lang.Long} <p>the <code>workerId</code> field.</p>
         * @see java.lang.Long
         */
        private Long workerId = 1L;
        /**
         * <code>centerId</code>
         * {@link java.lang.Long} <p>the <code>centerId</code> field.</p>
         * @see java.lang.Long
         */
        private Long centerId = 2L;
        /**
         * <code>sequence</code>
         * {@link java.lang.Long} <p>the <code>sequence</code> field.</p>
         * @see java.lang.Long
         */
        private Long sequence = 1L;
    }
}
