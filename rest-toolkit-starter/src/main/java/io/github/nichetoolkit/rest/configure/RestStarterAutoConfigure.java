package io.github.nichetoolkit.rest.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * <code>RestStarterAutoConfigure</code>
 * <p>The rest starter auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@AutoConfigureAfter(RestUtilsAutoConfigure.class)
@EnableConfigurationProperties(RestExceptionProperties.class)
public class RestStarterAutoConfigure {
    /**
     * <code>RestStarterAutoConfigure</code>
     * <p>Instantiates a new rest starter auto configure.</p>
     */
    public RestStarterAutoConfigure() {
        log.debug("The auto configuration for [rest-starter] initiated");
    }
}
