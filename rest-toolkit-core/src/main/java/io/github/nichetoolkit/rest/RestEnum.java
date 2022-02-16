package io.github.nichetoolkit.rest;

import java.util.Objects;

/**
 * <p>RestEnum</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
public class RestEnum {
    private String name;
    private Object value;

    public RestEnum() {
    }

    public RestEnum(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static RestEnum mapKey(RestValue entry) {
        return new RestEnum(entry.name(),entry.getKey());
    }

    public static RestEnum mapValue(RestValue entry) {
        return new RestEnum(entry.name(),entry.getValue());
    }

    public static RestEnum mapBean(RestValue entry) {
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
