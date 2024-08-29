package io.github.nichetoolkit.rest.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RestInterceptProperties</code>
 * <p>The type rest intercept properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.intercept")
public class RestInterceptProperties {
    private Boolean enabled = false;
    private Boolean loggingEnabled = false;
    private Boolean userlogEnabled = false;
    private Integer bodyLength = 1024;
    private Integer errorLength = 1024;
    private Integer messageLength = 1024;
    private Integer resultLength = 1024;
}
