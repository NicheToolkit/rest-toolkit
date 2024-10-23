package io.github.nichetoolkit.rest.stereotype;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>StereoBoolean</code>
 * <p>The stereo boolean interface.</p>
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
public @interface StereoBoolean {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return boolean <p>The value return object is <code>boolean</code> type.</p>
     */
    boolean value() default false;

    /**
     * <code>getKey</code>
     * <p>The get key getter method.</p>
     * @return boolean <p>The get key return object is <code>boolean</code> type.</p>
     */
    boolean getKey() default false;

    /**
     * <code>getValue</code>
     * <p>The get value getter method.</p>
     * @return boolean <p>The get value return object is <code>boolean</code> type.</p>
     */
    boolean getValue() default false;
}
