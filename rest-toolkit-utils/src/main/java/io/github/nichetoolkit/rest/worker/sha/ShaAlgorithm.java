package io.github.nichetoolkit.rest.worker.sha;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestAlgorithm;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>ShaAlgorithm</code>
 * <p>The sha algorithm enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestAlgorithm
 * @since Jdk1.8
 */
public enum ShaAlgorithm implements RestAlgorithm<String> {
    /**
     * <code>SHA1</code>
     * <p>The sha 1 sha algorithm field.</p>
     */
    SHA1(1,"SHA1","SHA"),
    /**
     * <code>SHA224</code>
     * <p>The sha 224 sha algorithm field.</p>
     */
    SHA224(2,"SHA224","SHA-224"),
    /**
     * <code>SHA256</code>
     * <p>The sha 256 sha algorithm field.</p>
     */
    SHA256(3,"SHA256","SHA-256"),
    /**
     * <code>SHA384</code>
     * <p>The sha 384 sha algorithm field.</p>
     */
    SHA384(4,"SHA384","SHA-384"),
    /**
     * <code>SHA512</code>
     * <p>The sha 512 sha algorithm field.</p>
     */
    SHA512(5,"SHA512","SHA-512"),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;
    /**
     * <code>algorithm</code>
     * {@link java.lang.String} <p>The <code>algorithm</code> field.</p>
     * @see java.lang.String
     */
    private final String algorithm;

    /**
     * <code>ShaAlgorithm</code>
     * <p>Instantiates a new sha algorithm.</p>
     * @param key       {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value     {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @param algorithm {@link java.lang.String} <p>The algorithm parameter is <code>String</code> type.</p>
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
     * <p>The parse key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>The parse key return object is <code>ShaAlgorithm</code> type.</p>
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
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>The parse value return object is <code>ShaAlgorithm</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ShaAlgorithm parseValue(@NonNull String value) {
        ShaAlgorithm typeEnum = RestValue.parseValue(ShaAlgorithm.class, value);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }

    /**
     * <code>parseAlgorithm</code>
     * <p>The parse algorithm method.</p>
     * @param algorithm {@link java.lang.String} <p>The algorithm parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaAlgorithm} <p>The parse algorithm return object is <code>ShaAlgorithm</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.lang.NonNull
     */
    public static ShaAlgorithm parseAlgorithm(@NonNull String algorithm) {
        ShaAlgorithm typeEnum = RestAlgorithm.parseAlgorithm(ShaAlgorithm.class, algorithm);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }
}
