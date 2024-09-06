package io.github.nichetoolkit.rest;

import lombok.Data;

import java.util.Objects;

/**
 * <code>RestEnum</code>
 * <p>The type rest enum class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestPack
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class RestEnum extends RestPack {

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
     * <code>RestEnum</code>
     * Instantiates a new rest enum.
     */
    public RestEnum() {
    }

    /**
     * <code>RestEnum</code>
     * Instantiates a new rest enum.
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param key   {@link java.lang.Object} <p>the key parameter is <code>Object</code> type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public RestEnum(String name, Object key, Object value) {
        super(name, value);
        this.ordinal = null;
        this.key = key;
    }

    /**
     * <code>RestEnum</code>
     * Instantiates a new rest enum.
     * @param name    {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param ordinal {@link java.lang.Integer} <p>the ordinal parameter is <code>Integer</code> type.</p>
     * @param key     {@link java.lang.Object} <p>the key parameter is <code>Object</code> type.</p>
     * @param value   {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     * @see java.lang.Object
     */
    public RestEnum(String name, Integer ordinal, Object key, Object value) {
        super(name, value);
        this.ordinal = ordinal;
        this.key = key;
    }

    /**
     * <code>fromValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEnum} <p>the value return object is <code>RestEnum</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestEnum fromValue(RestValue<?, ?> entry) {
        return new RestEnum(entry.name(), entry.getKey(), entry.getValue());
    }

    /**
     * <code>fromStereo</code>
     * <p>the stereo method.</p>
     * @param stereo {@link io.github.nichetoolkit.rest.RestStereo} <p>the stereo parameter is <code>RestStereo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEnum} <p>the stereo return object is <code>RestEnum</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStereo
     */
    public static RestEnum fromStereo(RestStereo stereo) {
        return new RestEnum(stereo.name(), stereo.type(), stereo.getKey(), stereo.getValue());
    }

    /**
     * <code>fromEnum</code>
     * <p>the enum method.</p>
     * @param entry {@link java.lang.Enum} <p>the entry parameter is <code>Enum</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEnum} <p>the enum return object is <code>RestEnum</code> type.</p>
     * @see java.lang.Enum
     */
    public static RestEnum fromEnum(Enum<?> entry) {
        return new RestEnum(entry.name(), entry.ordinal(), entry.name().toLowerCase(), entry.ordinal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RestEnum restEnum = (RestEnum) o;
        return Objects.equals(key, restEnum.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key);
    }
}
