package io.github.nichetoolkit.rest.shadow;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * <code>RestMutiField</code>
 * <p>The rest muti field interface.</p>
 * @see  java.lang.annotation.Annotation
 * @see  java.lang.annotation.Target
 * @see  java.lang.annotation.Retention
 * @see  java.lang.annotation.Documented
 * @see  org.springframework.stereotype.Indexed
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface RestMutiField {
}
