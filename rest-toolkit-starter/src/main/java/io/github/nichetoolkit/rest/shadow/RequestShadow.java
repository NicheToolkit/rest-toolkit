package io.github.nichetoolkit.rest.shadow;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RequestShadow</code>
 * <p>The request shadow interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RequestShadow {
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
     * <code>formData</code>
     * <p>The form data method.</p>
     * @return boolean <p>The form data return object is <code>boolean</code> type.</p>
     */
    boolean formData() default true;

    /**
     * <code>formFile</code>
     * <p>The form file method.</p>
     * @return boolean <p>The form file return object is <code>boolean</code> type.</p>
     */
    boolean formFile() default false;

    /**
     * <code>multiFile</code>
     * <p>The multi file method.</p>
     * @return boolean <p>The multi file return object is <code>boolean</code> type.</p>
     */
    boolean multiFile() default false;

    /**
     * <code>multiFields</code>
     * <p>The multi fields method.</p>
     * @return  {@link java.lang.String} <p>The multi fields return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    String[] multiFields() default {};

}
