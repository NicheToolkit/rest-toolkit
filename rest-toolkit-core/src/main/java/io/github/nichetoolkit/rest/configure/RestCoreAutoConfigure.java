package io.github.nichetoolkit.rest.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nichetoolkit.rest.advance.ObjectMapperHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <code>RestCoreAutoConfigure</code>
 * <p>The type rest core auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestCoreAutoConfigure {
    /**
     * <code>RestCoreAutoConfigure</code>
     * <p>Instantiates a new rest core auto configure.</p>
     */
    public RestCoreAutoConfigure() {
        log.debug("The auto configuration for [rest-core] initiated");
    }

    /**
     * <code>objectMapperHolder</code>
     * <p>The mapper holder method.</p>
     * @param objectMapper {@link com.fasterxml.jackson.databind.ObjectMapper} <p>The object mapper parameter is <code>ObjectMapper</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.advance.ObjectMapperHolder} <p>The mapper holder return object is <code>ObjectMapperHolder</code> type.</p>
     * @see com.fasterxml.jackson.databind.ObjectMapper
     * @see io.github.nichetoolkit.rest.advance.ObjectMapperHolder
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.beans.factory.annotation.Autowired
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see java.lang.SuppressWarnings
     */
    @Bean
    @Primary
    @Autowired(required = false)
    @ConditionalOnMissingBean(ObjectMapperHolder.class)
    @SuppressWarnings("InstantiationOfUtilityClass")
    public ObjectMapperHolder objectMapperHolder(ObjectMapper objectMapper) {
        return new ObjectMapperHolder(objectMapper);
    }
}
