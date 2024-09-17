package io.github.nichetoolkit.rest.resolver;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class DefaultGenericArrayType implements GenericArrayType {
    private final Type genericComponentType;

    DefaultGenericArrayType(Type genericComponentType) {
        super();
        this.genericComponentType = genericComponentType;
    }

    @Override
    public Type getGenericComponentType() {
        return genericComponentType;
    }
}