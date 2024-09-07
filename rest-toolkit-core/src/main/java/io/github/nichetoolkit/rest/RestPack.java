package io.github.nichetoolkit.rest;

import lombok.Data;

import java.util.Objects;

/**
 * <code>RestPack</code>
 * <p>The type rest pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class RestPack {
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
     * <code>RestPack</code>
     * Instantiates a new rest pack.
     */
    public RestPack() {
    }

    /**
     * <code>RestPack</code>
     * Instantiates a new rest pack.
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public RestPack(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * <code>nameKey</code>
     * <p>the key method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the key return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestPack nameKey(RestValue<?,?> entry) {
        return new RestPack(entry.name(),entry.getKey());
    }

    /**
     * <code>nameType</code>
     * <p>the type method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestStereo} <p>the entry parameter is <code>RestStereo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the type return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStereo
     */
    public static RestPack nameType(RestStereo entry) {
        return new RestPack(entry.name(), entry.type());
    }

    /**
     * <code>nameValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the value return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestPack nameValue(RestValue<?,?> entry) {
        return new RestPack(entry.name(),entry.getValue());
    }

    /**
     * <code>keyName</code>
     * <p>the name method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the name return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestPack keyName(RestValue<?,?> entry) {
        return new RestPack(String.valueOf(entry.getKey()),entry.name());
    }

    /**
     * <code>keyType</code>
     * <p>the type method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestStereo} <p>the entry parameter is <code>RestStereo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the type return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStereo
     */
    public static RestPack keyType(RestStereo entry) {
        return new RestPack(String.valueOf(entry.key()), entry.value());
    }

    /**
     * <code>keyValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the value return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestPack keyValue(RestValue<?,?> entry) {
        return new RestPack(String.valueOf(entry.getKey()),entry.getValue());
    }

    /**
     * <code>valueName</code>
     * <p>the name method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the name return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestPack valueName(RestValue<?,?> entry) {
        return new RestPack(String.valueOf(entry.getValue()),entry.name());
    }

    /**
     * <code>valueKey</code>
     * <p>the key method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the key return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static RestPack valueKey(RestValue<?,?> entry) {
        return new RestPack(String.valueOf(entry.getValue()),entry.getKey());
    }

    /**
     * <code>valueType</code>
     * <p>the type method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestStereo} <p>the entry parameter is <code>RestStereo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the type return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStereo
     */
    public static RestPack valueType(RestStereo entry) {
        return new RestPack(String.valueOf(entry.getValue()), entry.type());
    }

    /**
     * <code>typeName</code>
     * <p>the name method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestStereo} <p>the entry parameter is <code>RestStereo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the name return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStereo
     */
    public static RestPack typeName(RestStereo entry) {
        return new RestPack(String.valueOf(entry.type()), entry.name());
    }

    /**
     * <code>typeKey</code>
     * <p>the key method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestStereo} <p>the entry parameter is <code>RestStereo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the key return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStereo
     */
    public static RestPack typeKey(RestStereo entry) {
        return new RestPack(String.valueOf(entry.type()), entry.key());
    }


    /**
     * <code>typeValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestStereo} <p>the entry parameter is <code>RestStereo</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.RestPack} <p>the value return object is <code>RestPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStereo
     */
    public static RestPack typeValue(RestStereo entry) {
        return new RestPack(String.valueOf(entry.type()), entry.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestPack)) return false;
        RestPack ogsEnum = (RestPack) o;
        return Objects.equals(name, ogsEnum.name) &&
                Objects.equals(value, ogsEnum.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
