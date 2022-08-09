package io.github.nichetoolkit.rest.userlog.stereotype;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>RestNotelog</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RestNotelog {
    @AliasFor(attribute = "notelog")
    String value() default "";

    String notelog() default "";

    int logKey() default 0;
}
