package io.github.nichetoolkit.rest.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.RestValue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * <code>CharsetType</code>
 * <p>The charset type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum CharsetType implements RestValue<String, Charset> {
    /**
     * <code>US_ASCII</code>
     * <p>The us ascii charset type field.</p>
     */
    US_ASCII("US-ASCII", StandardCharsets.US_ASCII),
    /**
     * <code>ISO_8859_1</code>
     * <p>The iso 8859 1 charset type field.</p>
     */
    ISO_8859_1("ISO-8859-1", StandardCharsets.ISO_8859_1),
    /**
     * <code>UTF_8</code>
     * <p>The utf 8 charset type field.</p>
     */
    UTF_8("UTF-8", StandardCharsets.UTF_8),
    /**
     * <code>UTF_16BE</code>
     * <p>The utf 16 be charset type field.</p>
     */
    UTF_16BE("UTF-16BE", StandardCharsets.UTF_16BE),
    /**
     * <code>UTF_16LE</code>
     * <p>The utf 16 le charset type field.</p>
     */
    UTF_16LE("UTF-16LE", StandardCharsets.UTF_16LE),
    /**
     * <code>UTF_16</code>
     * <p>The utf 16 charset type field.</p>
     */
    UTF_16("UTF-16", StandardCharsets.UTF_16),
    ;

    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see java.lang.String
     */
    private final String key;
    /**
     * <code>value</code>
     * {@link java.nio.charset.Charset} <p>The <code>value</code> field.</p>
     * @see java.nio.charset.Charset
     */
    private final Charset value;

    /**
     * <code>CharsetType</code>
     * <p>Instantiates a new charset type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.nio.charset.Charset} <p>The value parameter is <code>Charset</code> type.</p>
     * @see java.lang.String
     * @see java.nio.charset.Charset
     */
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

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.type.CharsetType} <p>The parse key return object is <code>CharsetType</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static CharsetType parseKey(String key) {
        CharsetType sortTypeEnum = RestKey.parseKey(CharsetType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(CharsetType.UTF_8);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.nio.charset.Charset} <p>The value parameter is <code>Charset</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.type.CharsetType} <p>The parse value return object is <code>CharsetType</code> type.</p>
     * @see java.nio.charset.Charset
     */
    public static CharsetType parseValue(Charset value) {
        CharsetType sortTypeEnum = RestValue.parseValue(CharsetType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(CharsetType.UTF_8);
    }

}
