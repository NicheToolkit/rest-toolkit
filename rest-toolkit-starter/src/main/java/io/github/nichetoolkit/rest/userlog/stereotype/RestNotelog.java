package io.github.nichetoolkit.rest.userlog.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestNotelog</code>
 * <p>The rest notelog interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestLogging
 * @since Jdk1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@RestLogging
public @interface RestNotelog {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(attribute = "notelog")
    String value() default "";

    /**
     * <code>notelog</code>
     * <p>The notelog method.</p>
     * @return {@link java.lang.String} <p>The notelog return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String notelog() default "";

    /**
     * <code>loggingKey</code>
     * <p>The logging key method.</p>
     * @return {@link java.lang.String} <p>The logging key return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = RestLogging.class,
            attribute = "loggingKey"
    )
    String loggingKey() default "";
}
