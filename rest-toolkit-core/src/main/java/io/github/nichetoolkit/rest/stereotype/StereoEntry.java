package io.github.nichetoolkit.rest.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>StereoEntry</code>
 * <p>The type stereo entry interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @see io.github.nichetoolkit.rest.stereotype.StereoValue
 * @since Jdk1.8
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@StereoValue
public @interface StereoEntry {
    /**
     * <code>key</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = StereoValue.class,
            attribute = "key"
    )
    String key() default "";

    /**
     * <code>type</code>
     * <p>the method.</p>
     * @return int <p>the return object is <code>int</code> type.</p>
     */
    int type() default 0;

    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return long <p>the return object is <code>long</code> type.</p>
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = StereoValue.class,
            attribute = "value"
    )
    long value() default 0;
}
