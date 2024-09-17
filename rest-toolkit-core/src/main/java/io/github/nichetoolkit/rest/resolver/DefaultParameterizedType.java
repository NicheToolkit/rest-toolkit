package io.github.nichetoolkit.rest.resolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class DefaultParameterizedType implements ParameterizedType {
    private final Class<?> rawType;

    private final Type ownerType;

    private final Type[] actualTypeArguments;

    public DefaultParameterizedType(Class<?> rawType, Type ownerType, Type[] actualTypeArguments) {
        super();
        this.rawType = rawType;
        this.ownerType = ownerType;
        this.actualTypeArguments = actualTypeArguments;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @Override
    public Type getOwnerType() {
        return ownerType;
    }

    @Override
    public Type getRawType() {
        return rawType;
    }

    @Override
    public String toString() {
        return "ParameterizedTypeImpl [rawType=" + rawType + ", ownerType=" + ownerType + ", actualTypeArguments=" + Arrays.toString(actualTypeArguments) + "]";
    }
}