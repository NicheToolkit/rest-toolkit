package io.github.nichetoolkit.rest.parsing;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>JsonParsingField</code>
 * <p>The json parsing field interface.</p>
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
public @interface JsonParsingField {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return  {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("name")
    String value() default "";

    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return  {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.core.annotation.AliasFor
     */
    @AliasFor("value")
    String name() default "";

}
