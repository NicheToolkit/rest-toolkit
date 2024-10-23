package io.github.nichetoolkit.rest.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>StereoInteger</code>
 * <p>The stereo integer interface.</p>
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
public @interface StereoInteger {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return int <p>The value return object is <code>int</code> type.</p>
     */
    int value() default 0;
}
