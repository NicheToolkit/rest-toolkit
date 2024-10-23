package io.github.nichetoolkit.rest.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>StereoValue</code>
 * <p>The stereo value interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @see io.github.nichetoolkit.rest.stereotype.StereoEnum
 * @since Jdk1.8
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@StereoEnum
public @interface StereoValue {

    /**
     * <code>name</code>
     * <p>The name method.</p>
     * @return {@link java.lang.String} <p>The name return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = StereoEnum.class,
            attribute = "name"
    )
    String name() default "";

    /**
     * <code>ordinal</code>
     * <p>The ordinal method.</p>
     * @return int <p>The ordinal return object is <code>int</code> type.</p>
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = StereoEnum.class,
            attribute = "ordinal"
    )
    int ordinal() default 0;

    /**
     * <code>key</code>
     * <p>The key method.</p>
     * @return {@link java.lang.String} <p>The key return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String key() default "";

    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return long <p>The value return object is <code>long</code> type.</p>
     */
    long value() default 0;
}
