package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.RestLogKey;
import io.github.nichetoolkit.rest.logback.DefaultLogKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>RestLogbackAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.logback.enabled", havingValue = "true", matchIfMissing = true)
public class RestLogbackAutoConfigure {

    private final RestLogbackProperties logbackProperties;

    public RestLogbackAutoConfigure(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    @Bean
    @ConditionalOnMissingBean(RestLogKey.class)
    public RestLogKey restLogKey() {
        return new DefaultLogKeyGenerator(logbackProperties);
    }


}
