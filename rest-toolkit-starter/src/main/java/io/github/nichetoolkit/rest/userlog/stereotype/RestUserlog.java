package io.github.nichetoolkit.rest.userlog.stereotype;

import io.github.nichetoolkit.rest.userlog.LoggingType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestUserlog</code>
 * <p>The rest userlog interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see java.lang.annotation.Documented
 * @see org.springframework.stereotype.Indexed
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @since Jdk1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@RestNotelog
public @interface RestUserlog {
    /**
     * <code>value</code>
     * <p>The value method.</p>
     * @return {@link java.lang.String} <p>The value return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(attribute = "userlog")
    String value() default "";

    /**
     * <code>notelog</code>
     * <p>The notelog method.</p>
     * @return {@link java.lang.String} <p>The notelog return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = RestNotelog.class,
            attribute = "notelog"
    )
    String notelog() default "";

    /**
     * <code>userlog</code>
     * <p>The userlog method.</p>
     * @return {@link java.lang.String} <p>The userlog return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String userlog() default "";

    /**
     * <code>loggingKey</code>
     * <p>The logging key method.</p>
     * @return {@link java.lang.String} <p>The logging key return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = RestNotelog.class,
            attribute = "loggingKey"
    )
    String loggingKey() default "";

    /**
     * <code>loggingValue</code>
     * <p>The logging value method.</p>
     * @return {@link java.lang.String} <p>The logging value return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = RestLogging.class,
            attribute = "loggingValue"
    )
    String loggingValue() default "";


    /**
     * <code>loggingType</code>
     * <p>The logging type method.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.LoggingType} <p>The logging type return object is <code>LoggingType</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.LoggingType
     */
    LoggingType loggingType() default LoggingType.TEST;

}
