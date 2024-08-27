package io.github.nichetoolkit.rest;

import lombok.Data;

import java.util.Objects;

/**
 * <code>RestEnum</code>
 * <p>The type rest enum class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class RestEnum {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>the <code>name</code> field.</p>
     * @see java.lang.String
     */
    private String name;
    /**
     * <code>value</code>
     * {@link java.lang.Object} <p>the <code>value</code> field.</p>
     * @see java.lang.Object
     */
    private Object value;

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
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public RestEnum(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * <code>mapKey</code>
     * <p>the key method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEnum} <p>the key return object is <code>RestEnum</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestEnum mapKey(RestValue<?,?> entry) {
        return new RestEnum(entry.name(),entry.getKey());
    }

    /**
     * <code>mapValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEnum} <p>the value return object is <code>RestEnum</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestEnum mapValue(RestValue<?,?> entry) {
        return new RestEnum(entry.name(),entry.getValue());
    }

    /**
     * <code>mapBean</code>
     * <p>the bean method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestEnum} <p>the bean return object is <code>RestEnum</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestEnum mapBean(RestValue<?,?> entry) {
        return new RestEnum(String.valueOf(entry.getValue()),entry.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestEnum)) return false;
        RestEnum ogsEnum = (RestEnum) o;
        return Objects.equals(name, ogsEnum.name) &&
                Objects.equals(value, ogsEnum.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
