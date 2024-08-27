package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.rsa.RsaKey;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RestRsaProperties</code>
 * <p>The type rest rsa properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.rsa")
public class RestRsaProperties {
    /**
     * <code>enabled</code>
     * {@link boolean} <p>the <code>enabled</code> field.</p>
     */
    private boolean enabled;
    /**
     * <code>keySize</code>
     * {@link java.lang.Integer} <p>the <code>keySize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer keySize = 2048;
    /**
     * <code>publicKey</code>
     * {@link java.lang.String} <p>the <code>publicKey</code> field.</p>
     * @see java.lang.String
     */
    private String publicKey;
    /**
     * <code>privateKey</code>
     * {@link java.lang.String} <p>the <code>privateKey</code> field.</p>
     * @see java.lang.String
     */
    private String privateKey;
    /**
     * <code>autoVerify</code>
     * {@link boolean} <p>the <code>autoVerify</code> field.</p>
     */
    private boolean autoVerify = true;

    /**
     * <code>RestRsaProperties</code>
     * Instantiates a new rest rsa properties.
     */
    public RestRsaProperties() {
    }

    /**
     * <code>toRsaKey</code>
     * <p>the rsa key method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>the rsa key return object is <code>RsaKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaKey
     */
    public RsaKey toRsaKey() {
        RsaKey rsaKey = new RsaKey();
        if (GeneralUtils.isEmpty(this.publicKey) && GeneralUtils.isEmpty(this.privateKey)) {
            rsaKey = null;
        } else {
            rsaKey.setPublicKey(this.publicKey);
            rsaKey.setPrivateKey(this.privateKey);
            rsaKey.setKeySize(this.keySize);
        }
        return rsaKey;
    }
}
