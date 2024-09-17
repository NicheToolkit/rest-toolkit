package io.github.nichetoolkit.rest.resolver;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public class DefaultWildcardType implements WildcardType {
    private final Type[] lowerBounds;

    private final Type[] upperBounds;

    DefaultWildcardType(Type[] lowerBounds, Type[] upperBounds) {
        super();
        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;
    }

    @Override
    public Type[] getLowerBounds() {
        return lowerBounds;
    }

    @Override
    public Type[] getUpperBounds() {
        return upperBounds;
    }
}
