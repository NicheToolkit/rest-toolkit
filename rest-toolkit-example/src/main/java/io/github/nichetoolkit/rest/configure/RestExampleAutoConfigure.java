package io.github.nichetoolkit.rest.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

/**
 * <code>RestExampleAutoConfigure</code>
 * <p>The rest example auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @since Jdk17
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@AutoConfigureAfter(RestStarterAutoConfigure.class)
public class RestExampleAutoConfigure {
    /**
     * <code>RestExampleAutoConfigure</code>
     * <p>Instantiates a new rest example auto configure.</p>
     */
    public RestExampleAutoConfigure() {
        log.debug("The auto configuration for [rest-example] initiated");
    }
}
