package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.rsa.RsaKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RestRsaProperties</code>
 * <p>The type rest rsa properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.rsa")
public class RestRsaProperties {
    /**
     * <code>enabled</code>
     * <p>The <code>enabled</code> field.</p>
     */
    private boolean enabled;
    /**
     * <code>keySize</code>
     * {@link java.lang.Integer} <p>The <code>keySize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer keySize = 1024;
    /**
     * <code>publicKey</code>
     * {@link java.lang.String} <p>The <code>publicKey</code> field.</p>
     * @see java.lang.String
     */
    private String publicKey;
    /**
     * <code>privateKey</code>
     * {@link java.lang.String} <p>The <code>privateKey</code> field.</p>
     * @see java.lang.String
     */
    private String privateKey;
    /**
     * <code>autoVerify</code>
     * <p>The <code>autoVerify</code> field.</p>
     */
    private boolean autoVerify = true;

    /**
     * <code>RestRsaProperties</code>
     * <p>Instantiates a new rest rsa properties.</p>
     */
    public RestRsaProperties() {
    }

    /**
     * <code>toRsaKey</code>
     * <p>The rsa key method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaKey} <p>The rsa key return object is <code>RsaKey</code> type.</p>
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
