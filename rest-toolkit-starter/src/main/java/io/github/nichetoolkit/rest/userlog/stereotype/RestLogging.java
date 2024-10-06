package io.github.nichetoolkit.rest.userlog.stereotype;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestLogging</code>
 * <p>The type rest logging interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @since Jdk1.8
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestLogging {
    /**
     * <code>loggingKey</code>
     * <p>The key method.</p>
     * @return {@link java.lang.String} <p>The key return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String loggingKey() default "";

    /**
     * <code>loggingValue</code>
     * <p>The value method.</p>
     * @return {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String loggingValue() default "";
}
