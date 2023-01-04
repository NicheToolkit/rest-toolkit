package io.github.nichetoolkit.rest.worker.sha;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestAlgorithm;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>ShaAlgorithm</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum ShaAlgorithm implements RestAlgorithm<String> {
    SHA1(1,"SHA1","SHA"),
    SHA224(2,"SHA224","SHA-224"),
    SHA256(3,"SHA256","SHA-256"),
    SHA384(4,"SHA384","SHA-384"),
    SHA512(5,"SHA512","SHA-512"),
    ;

    private final Integer key;
    private final String value;
    private final String algorithm;

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


    @JsonCreator
    public static ShaAlgorithm parseKey(@NonNull Integer key) {
        ShaAlgorithm typeEnum = RestValue.parseKey(ShaAlgorithm.class, key);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }

    public static ShaAlgorithm parseValue(@NonNull String value) {
        ShaAlgorithm typeEnum = RestValue.parseValue(ShaAlgorithm.class, value);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }

    public static ShaAlgorithm parseAlgorithm(@NonNull String algorithm) {
        ShaAlgorithm typeEnum = RestAlgorithm.parseAlgorithm(ShaAlgorithm.class, algorithm);
        return Optional.ofNullable(typeEnum).orElse(ShaAlgorithm.SHA256);
    }
}
