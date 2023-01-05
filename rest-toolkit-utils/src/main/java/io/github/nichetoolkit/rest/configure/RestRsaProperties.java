package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.rsa.RsaKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>RestRsaProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.rsa")
public class RestRsaProperties {
    /** 是否启用 */
    private boolean enabled;
    /** 密钥长度 */
    private Integer keySize = 2048;
    /** 公钥 */
    private String publicKey;
    /** 私钥 */
    private String privateKey;
    /** 自动校验公钥和私钥 */
    private boolean autoVerify = true;

    public RestRsaProperties() {
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getKeySize() {
        return keySize;
    }

    public void setKeySize(Integer keySize) {
        this.keySize = keySize;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public boolean isAutoVerify() {
        return autoVerify;
    }

    public void setAutoVerify(boolean autoVerify) {
        this.autoVerify = autoVerify;
    }
}
