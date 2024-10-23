package io.github.nichetoolkit.rest.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>StereoFloat</code>
 * <p>The stereo float interface.</p>
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
public @interface StereoFloat {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return float <p>The value return object is <code>float</code> type.</p>
     */
    float value() default 0;
}
