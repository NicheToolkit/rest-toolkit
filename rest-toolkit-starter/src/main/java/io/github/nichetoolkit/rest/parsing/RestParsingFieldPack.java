package io.github.nichetoolkit.rest.parsing;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * <code>RestParsingFieldPack</code>
 * <p>The rest parsing field pack class.</p>
 * @see  java.io.Serializable
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  lombok.Builder
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@Builder
public class RestParsingFieldPack implements Serializable {
    /**
     * <code>name</code>
     * {@link java.lang.String} <p>The <code>name</code> field.</p>
     * @see  java.lang.String
     */
    private String name;

    /**
     * <code>fieldName</code>
     * {@link java.lang.String} <p>The <code>fieldName</code> field.</p>
     * @see  java.lang.String
     */
    private String fieldName;
    /**
     * <code>parent</code>
     * {@link io.github.nichetoolkit.rest.parsing.RestParsingFieldPack} <p>The <code>parent</code> field.</p>
     */
    private RestParsingFieldPack parent;
    /**
     * <code>field</code>
     * {@link java.lang.reflect.Field} <p>The <code>field</code> field.</p>
     * @see  java.lang.reflect.Field
     */
    private Field field;
    /**
     * <code>declaringType</code>
     * {@link java.lang.Class} <p>The <code>declaringType</code> field.</p>
     * @see  java.lang.Class
     */
    private Class<?> declaringType;
    /**
     * <code>underline</code>
     * <p>The <code>underline</code> field.</p>
     */
    private boolean underline;
    /**
     * <code>nested</code>
     * <p>The <code>nested</code> field.</p>
     */
    private boolean nested;
    /**
     * <code>nestedField</code>
     * <p>The <code>nestedField</code> field.</p>
     */
    private boolean nestedField;
    /**
     * <code>ignored</code>
     * <p>The <code>ignored</code> field.</p>
     */
    private boolean ignored;
    /**
     * <code>multiple</code>
     * <p>The <code>multiple</code> field.</p>
     */
    private boolean multiple;

}
