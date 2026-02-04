package io.github.nichetoolkit.rest.parsing;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>JsonParsingNestedField</code>
 * <p>The json parsing nested field interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface JsonParsingNestedField {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.Class} <p>The value return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("type")
    Class<?> value() default Object.class;

    /**
     * <code>type</code>
     * <p>The type method.</p>
     * @return  {@link java.lang.Class} <p>The type return object is <code>Class</code> type.</p>
     * @see  java.lang.Class
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    Class<?> type() default Object.class;

    /**
     * <code>prefix</code>
     * <p>The prefix method.</p>
     * @return  {@link java.lang.String} <p>The prefix return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String prefix() default "";

}
