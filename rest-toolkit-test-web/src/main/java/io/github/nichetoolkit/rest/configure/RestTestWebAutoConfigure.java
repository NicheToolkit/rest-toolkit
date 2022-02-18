package io.github.nichetoolkit.rest.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestTestWebAutoConfigure {
    public RestTestWebAutoConfigure() {
        log.debug("================= rice-toolkit-test-web initiated ！ ===================");
    }
}
