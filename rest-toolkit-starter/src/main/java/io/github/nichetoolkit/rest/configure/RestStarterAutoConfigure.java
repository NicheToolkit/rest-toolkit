package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.RestExceptionResolver;
import io.github.nichetoolkit.rest.interceptor.DefaultExceptionResultResolver;
import io.github.nichetoolkit.rest.shadow.RequestShadowArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <code>RestStarterAutoConfigure</code>
 * <p>The rest starter auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.boot.autoconfigure.AutoConfiguration
 * @see  java.lang.SuppressWarnings
 * @see  org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see  org.springframework.context.annotation.ComponentScan
 * @see  org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see  org.springframework.context.annotation.Import
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@AutoConfigureAfter(RestUtilsAutoConfigure.class)
@ComponentScan(basePackages = "io.github.nichetoolkit.rest.handler")
@EnableConfigurationProperties(RestExceptionProperties.class)
@Import({RestStarterAutoConfigure.RequestShadowAutoConfigure.class})
public class RestStarterAutoConfigure {
    /**
     * <code>RestStarterAutoConfigure</code>
     * <p>Instantiates a new rest starter auto configure.</p>
     */
    public RestStarterAutoConfigure() {
        log.debug("The auto configuration for [rest-starter] initiated");
    }

    /**
     * <code>requestShadowArgumentResolver</code>
     * <p>The request shadow argument resolver method.</p>
     * @return  {@link io.github.nichetoolkit.rest.shadow.RequestShadowArgumentResolver} <p>The request shadow argument resolver return object is <code>RequestShadowArgumentResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rest.shadow.RequestShadowArgumentResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean({RequestShadowArgumentResolver.class})
    public RequestShadowArgumentResolver requestShadowArgumentResolver() {
        return new RequestShadowArgumentResolver();
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


    /**
     * <code>RequestShadowAutoConfigure</code>
     * <p>The request shadow auto configure class.</p>
     * @see  org.springframework.web.servlet.config.annotation.WebMvcConfigurer
     * @see  org.springframework.context.annotation.Configuration
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @Configuration
    public static class RequestShadowAutoConfigure implements WebMvcConfigurer {

        /**
         * <code>requestShadowArgumentResolver</code>
         * {@link io.github.nichetoolkit.rest.shadow.RequestShadowArgumentResolver} <p>The <code>requestShadowArgumentResolver</code> field.</p>
         * @see  io.github.nichetoolkit.rest.shadow.RequestShadowArgumentResolver
         */
        private final RequestShadowArgumentResolver requestShadowArgumentResolver;

        /**
         * <code>RequestShadowAutoConfigure</code>
         * <p>Instantiates a new request shadow auto configure.</p>
         * @param requestShadowArgumentResolver {@link io.github.nichetoolkit.rest.shadow.RequestShadowArgumentResolver} <p>The request shadow argument resolver parameter is <code>RequestShadowArgumentResolver</code> type.</p>
         * @see  io.github.nichetoolkit.rest.shadow.RequestShadowArgumentResolver
         */
        public RequestShadowAutoConfigure(RequestShadowArgumentResolver requestShadowArgumentResolver) {
            this.requestShadowArgumentResolver = requestShadowArgumentResolver;
        }

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            resolvers.add(0,this.requestShadowArgumentResolver);
        }
    }
}
