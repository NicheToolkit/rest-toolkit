package io.github.nichetoolkit.rest.worker.sha;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestAlgorithm;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>ShaAlgorithm</code>
 * <p>The type sha algorithm enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestAlgorithm
 * @since Jdk1.8
 */
public enum ShaAlgorithm implements RestAlgorithm<String> {
    /**
     * <code>SHA1</code>
     * <p>the Sha 1 sha algorithm field.</p>
     */
    SHA1(1,"SHA1","SHA"),
    /**
     * <code>SHA224</code>
     * <p>the Sha 224 sha algorithm field.</p>
     */
    SHA224(2,"SHA224","SHA-224"),
    /**
     * <code>SHA256</code>
     * <p>the Sha 256 sha algorithm field.</p>
     */
    SHA256(3,"SHA256","SHA-256"),
    /**
     * <code>SHA384</code>
     * <p>the Sha 384 sha algorithm field.</p>
     */
    SHA384(4,"SHA384","SHA-384"),
    /**
     * <code>SHA512</code>
     * <p>the Sha 512 sha algorithm field.</p>
     */
    SHA512(5,"SHA512","SHA-512"),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>the <code>key</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>the <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;
    /**
     * <code>algorithm</code>
     * {@link java.lang.String} <p>the <code>algorithm</code> field.</p>
     * @see java.lang.String
     */
    private final String algorithm;

    /**
     * <code>ShaAlgorithm</code>
     * Instantiates a new sha algorithm.
     * @param key       {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @param value     {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @param algorithm {@link java.lang.String} <p>the algorithm parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    ShaAlgorithm(Integer key,String value,String algorithm) {
        this.key = key;
        this.value = value;
        this.algorithm = algorithm;
    }

    @JsonValue
    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }


    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param key {@link java.lang.Integer} <p>the key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>the key return object is <code>ShaAlgorithm</code> type.</p>
     * @see java.lang.Integer
     * @see org.springframework.lang.NonNull
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static ShaAlgorithm parseKey(@NonNull Integer key) {
        ShaAlgorithm typeEnum = RestValue.parseKey(ShaAlgorithm.class, key);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>the value return object is <code>ShaAlgorithm</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ShaAlgorithm parseValue(@NonNull String value) {
        ShaAlgorithm typeEnum = RestValue.parseValue(ShaAlgorithm.class, value);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }

    /**
     * <code>parseAlgorithm</code>
     * <p>the algorithm method.</p>
     * @param algorithm {@link java.lang.String} <p>the algorithm parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>the algorithm return object is <code>ShaAlgorithm</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ShaAlgorithm parseAlgorithm(@NonNull String algorithm) {
        ShaAlgorithm typeEnum = RestAlgorithm.parseAlgorithm(ShaAlgorithm.class, algorithm);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }
}
