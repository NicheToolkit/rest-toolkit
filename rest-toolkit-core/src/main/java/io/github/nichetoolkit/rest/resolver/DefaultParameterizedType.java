package io.github.nichetoolkit.rest.resolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * <code>DefaultParameterizedType</code>
 * <p>The type default parameterized type class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.reflect.ParameterizedType
 * @since Jdk1.8
 */
public class DefaultParameterizedType implements ParameterizedType {
    /**
     * <code>rawType</code>
     * {@link java.lang.Class} <p>the <code>rawType</code> field.</p>
     * @see java.lang.Class
     */
    private final Class<?> rawType;

    /**
     * <code>ownerType</code>
     * {@link java.lang.reflect.Type} <p>the <code>ownerType</code> field.</p>
     * @see java.lang.reflect.Type
     */
    private final Type ownerType;

    /**
     * <code>actualTypeArguments</code>
     * {@link java.lang.reflect.Type} <p>the <code>actualTypeArguments</code> field.</p>
     * @see java.lang.reflect.Type
     */
    private final Type[] actualTypeArguments;

    /**
     * <code>DefaultParameterizedType</code>
     * Instantiates a new default parameterized type.
     * @param rawType             {@link java.lang.Class} <p>the raw type parameter is <code>Class</code> type.</p>
     * @param ownerType           {@link java.lang.reflect.Type} <p>the owner type parameter is <code>Type</code> type.</p>
     * @param actualTypeArguments {@link java.lang.reflect.Type} <p>the actual type arguments parameter is <code>Type</code> type.</p>
     * @see java.lang.Class
     * @see java.lang.reflect.Type
     */
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