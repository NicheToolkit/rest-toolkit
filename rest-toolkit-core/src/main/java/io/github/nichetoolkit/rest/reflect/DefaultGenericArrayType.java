package io.github.nichetoolkit.rest.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/**
 * <code>DefaultGenericArrayType</code>
 * <p>The default generic array type class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.reflect.GenericArrayType
 * @since Jdk1.8
 */
public class DefaultGenericArrayType implements GenericArrayType {
    /**
     * <code>genericComponentType</code>
     * {@link java.lang.reflect.Type} <p>The <code>genericComponentType</code> field.</p>
     * @see java.lang.reflect.Type
     */
    private final Type genericComponentType;

    /**
     * <code>DefaultGenericArrayType</code>
     * <p>Instantiates a new default generic array type.</p>
     * @param genericComponentType {@link java.lang.reflect.Type} <p>The generic component type parameter is <code>Type</code> type.</p>
     * @see java.lang.reflect.Type
     */
    DefaultGenericArrayType(Type genericComponentType) {
        super();
        this.genericComponentType = genericComponentType;
    }

    @Override
    public Type getGenericComponentType() {
        return genericComponentType;
    }
}