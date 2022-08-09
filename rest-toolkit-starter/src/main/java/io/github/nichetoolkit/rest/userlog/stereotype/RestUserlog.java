package io.github.nichetoolkit.rest.userlog.stereotype;

import io.github.nichetoolkit.rest.userlog.LogType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>RestRemark</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RestLog
@RestNotelog
public @interface RestUserlog {

    @AliasFor(attribute = "userlog")
    String value() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "notelog"
    )
    String notelog() default "";

    String userlog() default "";

    @AliasFor(
            annotation = io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog.class,
            attribute = "logKey"
    )
    int logKey() default 0;

    String logValue() default "";

    LogType logType() default LogType.NONE;

}
