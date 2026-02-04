package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.RestExceptionResolver;
import io.github.nichetoolkit.rest.interceptor.DefaultExceptionResultResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * <code>RestStarterAutoConfigure</code>
 * <p>The rest starter auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.boot.autoconfigure.AutoConfiguration
 * @see  java.lang.SuppressWarnings
 * @see  org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see  org.springframework.context.annotation.ComponentScan
 * @see  org.springframework.boot.context.properties.EnableConfigurationProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@AutoConfigureAfter(RestUtilsAutoConfigure.class)
@ComponentScan(basePackages = "io.github.nichetoolkit.rest.handler")
@EnableConfigurationProperties(RestExceptionProperties.class)
public class RestStarterAutoConfigure {
    /**
     * <code>RestStarterAutoConfigure</code>
     * <p>Instantiates a new rest starter auto configure.</p>
     */
    public RestStarterAutoConfigure() {
        log.debug("The auto configuration for [rest-starter] initiated");
    }

    /**
     * <code>exceptionResultResolver</code>
     * <p>The exception result resolver method.</p>
     * @return  {@link io.github.nichetoolkit.rest.RestExceptionResolver} <p>The exception result resolver return object is <code>RestExceptionResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestExceptionResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean({RestExceptionResolver.class})
    public RestExceptionResolver exceptionResultResolver() {
        return new DefaultExceptionResultResolver();
    }

}
