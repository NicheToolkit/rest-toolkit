package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>RestShaProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.sha")
public class RestShaProperties {

    private boolean enabled;

    private String secret;

    private ShaAlgorithm algorithm = ShaAlgorithm.SHA256;

    public RestShaProperties() {
    }

    public String getSecret() {
        if (GeneralUtils.isEmpty(this.secret)) {
            return secret = RadixWorker.encrypts(System.currentTimeMillis());
        }
        return secret;
    }
}
