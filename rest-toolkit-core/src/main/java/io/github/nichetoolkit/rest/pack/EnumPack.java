package io.github.nichetoolkit.rest.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestValue;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * <code>EnumPack</code>
 * <p>The enum pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.pack.ViewPack
 * @see lombok.Getter
 * @see lombok.Setter
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnumPack extends ViewPack {

    /**
     * <code>ordinal</code>
     * {@link java.lang.Integer} <p>The <code>ordinal</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer ordinal;
    /**
     * <code>key</code>
     * {@link java.lang.Object} <p>The <code>key</code> field.</p>
     * @see java.lang.Object
     */
    private Object key;

    /**
     * <code>EnumPack</code>
     * <p>Instantiates a new enum pack.</p>
     */
    public EnumPack() {
    }

    /**
     * <code>EnumPack</code>
     * <p>Instantiates a new enum pack.</p>
     * @param name  {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param key   {@link java.lang.Object} <p>The key parameter is <code>Object</code> type.</p>
     * @param value {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
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
     * <p>Instantiates a new enum pack.</p>
     * @param name    {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param ordinal {@link java.lang.Integer} <p>The ordinal parameter is <code>Integer</code> type.</p>
     * @param key     {@link java.lang.Object} <p>The key parameter is <code>Object</code> type.</p>
     * @param value   {@link java.lang.Object} <p>The value parameter is <code>Object</code> type.</p>
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
     * <p>The from value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>The entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.EnumPack} <p>The from value return object is <code>EnumPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static EnumPack fromValue(RestValue<?, ?> entry) {
        return new EnumPack(entry.name(), entry.getKey(), entry.getValue());
    }

    /**
     * <code>fromEnum</code>
     * <p>The from enum method.</p>
     * @param entry {@link java.lang.Enum} <p>The entry parameter is <code>Enum</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.EnumPack} <p>The from enum return object is <code>EnumPack</code> type.</p>
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
