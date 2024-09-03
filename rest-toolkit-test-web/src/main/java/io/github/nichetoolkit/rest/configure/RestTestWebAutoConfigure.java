package io.github.nichetoolkit.rest.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <code>RestTestWebAutoConfigure</code>
 * <p>The type rest test web auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see Slf4j
 * @see Configuration
 * @see SuppressWarnings
 * @see ComponentScan
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestTestWebAutoConfigure {
    /**
     * <code>RestTestWebAutoConfigure</code>
     * Instantiates a new rest test web auto configure.
     */
    public RestTestWebAutoConfigure() {
        log.debug("================= rice-toolkit-test-web initiated ！ ===================");
    }
}
