package io.github.nichetoolkit.rest.pack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.RestValue;
import lombok.Data;

import java.util.Objects;

/**
 * <code>ViewPack</code>
 * <p>The type view pack class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk1.8
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewPack {
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
     * <code>ViewPack</code>
     * Instantiates a new view pack.
     */
    public ViewPack() {
    }

    /**
     * <code>ViewPack</code>
     * Instantiates a new view pack.
     * @param name  {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.Object} <p>the value parameter is <code>Object</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Object
     */
    public ViewPack(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * <code>nameKey</code>
     * <p>the key method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.ViewPack} <p>the key return object is <code>ViewPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static ViewPack nameKey(RestValue<?,?> entry) {
        return new ViewPack(entry.name(),entry.getKey());
    }

    /**
     * <code>nameValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.ViewPack} <p>the value return object is <code>ViewPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static ViewPack nameValue(RestValue<?,?> entry) {
        return new ViewPack(entry.name(),entry.getValue());
    }

    /**
     * <code>keyName</code>
     * <p>the name method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.ViewPack} <p>the name return object is <code>ViewPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static ViewPack keyName(RestValue<?,?> entry) {
        return new ViewPack(String.valueOf(entry.getKey()),entry.name());
    }

    /**
     * <code>keyValue</code>
     * <p>the value method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.ViewPack} <p>the value return object is <code>ViewPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static ViewPack keyValue(RestValue<?,?> entry) {
        return new ViewPack(String.valueOf(entry.getKey()),entry.getValue());
    }

    /**
     * <code>valueName</code>
     * <p>the name method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.ViewPack} <p>the name return object is <code>ViewPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static ViewPack valueName(RestValue<?,?> entry) {
        return new ViewPack(String.valueOf(entry.getValue()),entry.name());
    }

    /**
     * <code>valueKey</code>
     * <p>the key method.</p>
     * @param entry {@link io.github.nichetoolkit.rest.RestValue} <p>the entry parameter is <code>RestValue</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.pack.ViewPack} <p>the key return object is <code>ViewPack</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestValue
     */
    public static ViewPack valueKey(RestValue<?,?> entry) {
        return new ViewPack(String.valueOf(entry.getValue()),entry.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewPack)) return false;
        ViewPack ogsEnum = (ViewPack) o;
        return Objects.equals(name, ogsEnum.name) &&
                Objects.equals(value, ogsEnum.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
