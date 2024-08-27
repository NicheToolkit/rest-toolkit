package io.github.nichetoolkit.rest.worker.rsa;

import lombok.Data;

import java.io.Serializable;

/**
 * <code>RsaKey</code>
 * <p>The type rsa key class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class RsaKey implements Serializable {
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
     * <code>keySize</code>
     * {@link int} <p>the <code>keySize</code> field.</p>
     */
    private int keySize;

    /**
     * <code>RsaKey</code>
     * Instantiates a new rsa key.
     */
    public RsaKey() {
    }

    /**
     * <code>RsaKey</code>
     * Instantiates a new rsa key.
     * @param publicKey  {@link java.lang.String} <p>the public key parameter is <code>String</code> type.</p>
     * @param privateKey {@link java.lang.String} <p>the private key parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RsaKey(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

}
