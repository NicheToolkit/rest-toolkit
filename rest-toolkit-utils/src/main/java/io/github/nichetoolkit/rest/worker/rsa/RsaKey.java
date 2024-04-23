package io.github.nichetoolkit.rest.worker.rsa;

import java.io.Serializable;

/**
 * <p>RsaKey</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
