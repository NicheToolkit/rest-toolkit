package io.github.nichetoolkit.rest.worker.rsa;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <code>RsaKey</code>
 * <p>The type rsa key class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @since Jdk1.8
 */
@Getter
@Setter
public class RsaKey implements Serializable {
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
     * <code>keySize</code>
     * <p>The <code>keySize</code> field.</p>
     */
    private int keySize;

    /**
     * <code>RsaKey</code>
     * <p>Instantiates a new rsa key.</p>
     */
    public RsaKey() {
    }

    /**
     * <code>RsaKey</code>
     * <p>Instantiates a new rsa key.</p>
     * @param publicKey  {@link java.lang.String} <p>The public key parameter is <code>String</code> type.</p>
     * @param privateKey {@link java.lang.String} <p>The private key parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public RsaKey(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

}
