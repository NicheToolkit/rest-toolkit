package io.github.nichetoolkit.rest.log;

import io.github.nichetoolkit.rest.RestNote;
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
@RestNote
public @interface RestLogMessage {

    String title() default "";

    String message() default "";

    int key() default 0;

    String value() default "";

    LogType logType() default LogType.NONE;

}
