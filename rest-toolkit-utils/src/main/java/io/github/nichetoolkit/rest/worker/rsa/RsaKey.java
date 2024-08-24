package io.github.nichetoolkit.rest.worker.rsa;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>RsaKey</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
public class RsaKey implements Serializable {
    private String publicKey;
    private String privateKey;
    private int keySize;

    public RsaKey() {
    }

    public RsaKey(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

}
