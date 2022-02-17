package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>RestMd5Properties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.md5")
public class RestMd5Properties {

    private Boolean enabled = false;

    private String cipher;

    public RestMd5Properties() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCipher() {
        if (GeneralUtils.isEmpty(this.cipher)) {
            return cipher = RadixWorker.encrypts(System.currentTimeMillis());
        }
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }
}
