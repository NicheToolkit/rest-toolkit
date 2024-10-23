package io.github.nichetoolkit.rest.stereotype;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>StereoLong</code>
 * <p>The stereo long interface.</p>
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
public @interface StereoLong {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return long <p>The value return object is <code>long</code> type.</p>
     */
    long value() default 0;
}
