package io.github.nichetoolkit.rest.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>RestStarterAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestStarterAutoConfigure {
    public RestStarterAutoConfigure() {
        log.debug("================= rest-toolkit-starter initiated ！ ===================");
    }

}
