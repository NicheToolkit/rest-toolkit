package io.github.nichetoolkit.rest.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.RestValue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public enum CharsetType implements RestValue<String, Charset> {
    US_ASCII("US-ASCII", StandardCharsets.US_ASCII),
    ISO_8859_1("ISO-8859-1", StandardCharsets.ISO_8859_1),
    UTF_8("UTF-8", StandardCharsets.UTF_8),
    UTF_16BE("UTF-16BE", StandardCharsets.UTF_16BE),
    UTF_16LE("UTF-16LE", StandardCharsets.UTF_16LE),
    UTF_16("UTF-16", StandardCharsets.UTF_16),
    ;

    private final String key;
    private final Charset value;

    CharsetType(String key, Charset value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @JsonValue
    @Override
    public Charset getValue() {
        return this.value;
    }

    @JsonCreator
    public static CharsetType parseKey(String key) {
        CharsetType sortTypeEnum = RestKey.parseKey(CharsetType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(CharsetType.UTF_8);
    }

    public static CharsetType parseValue(Charset value) {
        CharsetType sortTypeEnum = RestValue.parseValue(CharsetType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(CharsetType.UTF_8);
    }

}
