package io.github.nichetoolkit.rest.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>IdentityProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.intercept")
public class RestInterceptProperties {
    private Boolean enabled = false;
    private Boolean logEnabled = false;
    private Boolean userlogEnabled = false;
    private Integer bodyLength = 1024;
    private Integer errorLength = 1024;
    private Integer messageLength = 1024;
    private Integer resultLength = 1024;

    public RestInterceptProperties() {
    }
}
