package io.github.nichetoolkit.rest.userlog.stereotype;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <code>RestNotelog</code>
 * <p>The type rest notelog interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestLogging
 * @since Jdk1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RestLogging
public @interface RestNotelog {
    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(attribute = "notelog")
    String value() default "";

    /**
     * <code>notelog</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String notelog() default "";

    /**
     * <code>loggingKey</code>
     * <p>the key method.</p>
     * @return int <p>the key return object is <code>int</code> type.</p>
     */
    int loggingKey() default 0;
}
