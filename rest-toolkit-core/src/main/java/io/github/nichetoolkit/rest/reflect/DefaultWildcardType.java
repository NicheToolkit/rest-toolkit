package io.github.nichetoolkit.rest.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * <code>DefaultWildcardType</code>
 * <p>The type default wildcard type class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.reflect.WildcardType
 * @since Jdk1.8
 */
public class DefaultWildcardType implements WildcardType {
    /**
     * <code>lowerBounds</code>
     * {@link java.lang.reflect.Type} <p>The <code>lowerBounds</code> field.</p>
     * @see java.lang.reflect.Type
     */
    private final Type[] lowerBounds;

    /**
     * <code>upperBounds</code>
     * {@link java.lang.reflect.Type} <p>The <code>upperBounds</code> field.</p>
     * @see java.lang.reflect.Type
     */
    private final Type[] upperBounds;

    /**
     * <code>DefaultWildcardType</code>
     * <p>Instantiates a new default wildcard type.</p>
     * @param lowerBounds {@link java.lang.reflect.Type} <p>The lower bounds parameter is <code>Type</code> type.</p>
     * @param upperBounds {@link java.lang.reflect.Type} <p>The upper bounds parameter is <code>Type</code> type.</p>
     * @see java.lang.reflect.Type
     */
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
