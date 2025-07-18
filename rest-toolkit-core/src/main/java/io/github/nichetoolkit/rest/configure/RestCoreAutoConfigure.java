package io.github.nichetoolkit.rest.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class RestCoreAutoConfigure {
    public RestCoreAutoConfigure() {
        log.debug("The auto configuration for [rest-core] initiated");
    }

    @Bean
    @Primary
    @Autowired(required = false)
    @ConditionalOnBean(ObjectMapper.class)
    @ConditionalOnMissingBean(ObjectMapperHolder.class)
    @SuppressWarnings("InstantiationOfUtilityClass")
    public ObjectMapperHolder objectMapperHolder(ObjectMapper objectMapper) {
        return new ObjectMapperHolder(objectMapper);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean({ObjectMapperHolder.class, ObjectMapper.class})
    @SuppressWarnings("InstantiationOfUtilityClass")
    public ObjectMapperHolder objectMapperHolder() {
        return new ObjectMapperHolder();
    }
}
