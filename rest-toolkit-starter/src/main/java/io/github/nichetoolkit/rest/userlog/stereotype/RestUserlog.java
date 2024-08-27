package io.github.nichetoolkit.rest.userlog.stereotype;

import io.github.nichetoolkit.rest.userlog.LogType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <code>RestUserlog</code>
 * <p>The type rest userlog interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.lang.annotation.Annotation
 * @see java.lang.annotation.Target
 * @see java.lang.annotation.Retention
 * @see io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @since Jdk1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RestNotelog
public @interface RestUserlog {
    /**
     * <code>value</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(attribute = "userlog")
    String value() default "";

    /**
     * <code>notelog</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "notelog"
    )
    String notelog() default "";

    /**
     * <code>userlog</code>
     * <p>the method.</p>
     * @return {@link java.lang.String} <p>the return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String userlog() default "";

    /**
     * <code>logKey</code>
     * <p>the key method.</p>
     * @return {@link int} <p>the key return object is <code>int</code> type.</p>
     * @see org.springframework.core.annotation.AliasFor
     */
    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "logKey"
    )
    int logKey() default 0;

    /**
     * <code>logValue</code>
     * <p>the value method.</p>
     * @return {@link java.lang.String} <p>the value return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    String logValue() default "";

    /**
     * <code>logType</code>
     * <p>the type method.</p>
     * @return {@link io.github.nichetoolkit.rest.userlog.LogType} <p>the type return object is <code>LogType</code> type.</p>
     * @see io.github.nichetoolkit.rest.userlog.LogType
     */
    LogType logType() default LogType.NONE;

}
