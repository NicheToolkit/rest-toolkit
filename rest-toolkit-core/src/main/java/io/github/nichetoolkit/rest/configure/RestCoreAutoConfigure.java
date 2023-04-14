package io.github.nichetoolkit.rest.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <p>RestCoreAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestCoreAutoConfigure {
    public RestCoreAutoConfigure() {
        log.debug("================= rest-toolkit-core initiated ！ ===================");
    }

    @Bean
    @Primary
    @Autowired(required = false)
    @ConditionalOnMissingBean(ObjectMapperHolder.class)
    public ObjectMapperHolder objectMapperHolder(ObjectMapper objectMapper) {
        return new ObjectMapperHolder(objectMapper);
    }
}
