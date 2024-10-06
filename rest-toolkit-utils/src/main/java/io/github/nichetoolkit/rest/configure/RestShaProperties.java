package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RestShaProperties</code>
 * <p>The type rest sha properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.sha")
public class RestShaProperties {

    /**
     * <code>enabled</code>
     * <p>The <code>enabled</code> field.</p>
     */
    private boolean enabled;

    /**
     * <code>secret</code>
     * {@link java.lang.String} <p>The <code>secret</code> field.</p>
     * @see java.lang.String
     */
    private String secret;

    /**
     * <code>algorithm</code>
     * {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>The <code>algorithm</code> field.</p>
     * @see io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm
     */
    private ShaAlgorithm algorithm = ShaAlgorithm.SHA256;

    /**
     * <code>RestShaProperties</code>
     * <p>Instantiates a new rest sha properties.</p>
     */
    public RestShaProperties() {
    }

    /**
     * <code>getSecret</code>
     * <p>The secret getter method.</p>
     * @return {@link java.lang.String} <p>The secret return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String getSecret() {
        if (GeneralUtils.isEmpty(this.secret)) {
            return secret = RadixWorker.encrypts(System.currentTimeMillis());
        }
        return secret;
    }
}
