package io.github.nichetoolkit.rest.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestValue;
import lombok.Data;

import java.util.Objects;

/**
 * <code>EnumPack</code>
 * <p>The type enum pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.pack.ViewPack
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnumPack extends ViewPack {

    /**
     * <code>ordinal</code>
     * {@link java.lang.Integer} <p>the <code>ordinal</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer ordinal;
    /**
     * <code>key</code>
     * {@link java.lang.Object} <p>the <code>key</code> field.</p>
     * @see java.lang.Object
     */
    private Object key;

    /**
     * <code>EnumPack</code>
     * Instantiates a new enum pack.
     */
    public EnumPack() {
    }

    /**
     * <code>EnumPack</code>
     * Instantiates a new enum pack.
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param key   {@link java.lang.Object} <p>the key parameter is <code>Object</code> type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public EnumPack(String name, Object key, Object value) {
        super(name, value);
        this.ordinal = null;
        this.key = key;
    }

    /**
     * <code>EnumPack</code>
     * Instantiates a new enum pack.
     * @param name    {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param ordinal {@link java.lang.Integer} <p>the ordinal parameter is <code>Integer</code> type.</p>
     * @param key     {@link java.lang.Object} <p>the key parameter is <code>Object</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     * @see java.lang.Object
     */
    public EnumPack(String name, Integer ordinal, Object key, Object value) {
        super(name, value);
        this.ordinal = ordinal;
        this.key = key;
    }

    /**
     * <code>fromValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.EnumPack} <p>the value return object is <code>EnumPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static EnumPack fromValue(RestValue<?, ?> entry) {
        return new EnumPack(entry.name(), entry.getKey(), entry.getValue());
    }

    /**
     * <code>fromEnum</code>
     * <p>the enum method.</p>
     * @param entry {@link java.lang.Enum} <p>the entry parameter is <code>Enum</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.EnumPack} <p>the enum return object is <code>EnumPack</code> type.</p>
     * @see java.lang.Enum
     */
    public static EnumPack fromEnum(Enum<?> entry) {
        return new EnumPack(entry.name(), entry.ordinal(), entry.name().toLowerCase(), entry.ordinal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EnumPack restEnum = (EnumPack) o;
        return Objects.equals(key, restEnum.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key);
    }
}
