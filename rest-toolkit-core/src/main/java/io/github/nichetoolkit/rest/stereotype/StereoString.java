package io.github.nichetoolkit.rest.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>StereoString</code>
 * <p>The type stereo string interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk1.8
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface StereoString {
    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String value() default "";
}