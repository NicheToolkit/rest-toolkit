package io.github.nichetoolkit.rest.stereotype;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface StereoByte {
    byte value() default 0;
}
