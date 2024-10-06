package io.github.nichetoolkit.rest.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>RestExampleAutoConfigure</code>
 * <p>The type rest example auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.context.annotation.ComponentScan
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestExampleAutoConfigure {
    /**
     * <code>RestExampleAutoConfigure</code>
     * Instantiates a new rest example auto configure.
     */
    public RestExampleAutoConfigure() {
        log.debug("The auto configuration for [rest-example] initiated");
    }
}
