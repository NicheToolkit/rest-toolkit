package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.parsing.DefaultRequestParsingArgumentResolver;
import io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;

/**
 * <code>RestParsingAutoConfigure</code>
 * <p>The rest parsing auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  java.lang.SuppressWarnings
 * @see  org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @see  org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties(RestParsingProperties.class)
@ConditionalOnProperty(value = "nichetoolkit.rest.parsing.enabled", havingValue = "true")
@ImportAutoConfiguration({RestParsingAutoConfigure.RequestParsingAutoConfigure.class})
public class RestParsingAutoConfigure {

    /**
     * <code>parsingProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestParsingProperties} <p>The <code>parsingProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestParsingProperties
     */
    private final RestParsingProperties parsingProperties;

    /**
     * <code>RestParsingAutoConfigure</code>
     * <p>Instantiates a new rest parsing auto configure.</p>
     * @param parsingProperties {@link io.github.nichetoolkit.rest.configure.RestParsingProperties} <p>The parsing properties parameter is <code>RestParsingProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestParsingProperties
     */
    public RestParsingAutoConfigure(RestParsingProperties parsingProperties) {
        this.parsingProperties = parsingProperties;
        log.debug("The auto configuration for [rest-parsing] initiated");
    }

    /**
     * <code>requestParsingArgumentResolver</code>
     * <p>The request parsing argument resolver method.</p>
     * @return  {@link io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver} <p>The request parsing argument resolver return object is <code>RequestParsingArgumentResolver</code> type.</p>
     * @see  io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean({RequestParsingArgumentResolver.class})
    public RequestParsingArgumentResolver requestParsingArgumentResolver() {
        return new DefaultRequestParsingArgumentResolver(parsingProperties);
    }


    /**
     * <code>RequestParsingAutoConfigure</code>
     * <p>The request parsing auto configure class.</p>
     * @see  org.springframework.web.servlet.config.annotation.WebMvcConfigurer
     * @see  org.springframework.boot.autoconfigure.AutoConfiguration
     * @see  org.springframework.boot.autoconfigure.AutoConfigureAfter
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @AutoConfiguration
    @AutoConfigureAfter(RestParsingAutoConfigure.class)
    public static class RequestParsingAutoConfigure implements WebMvcConfigurer {

        /**
         * <code>requestParsingArgumentResolver</code>
         * {@link io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver} <p>The <code>requestParsingArgumentResolver</code> field.</p>
         * @see  io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver
         */
        private final RequestParsingArgumentResolver requestParsingArgumentResolver;

        /**
         * <code>RequestParsingAutoConfigure</code>
         * <p>Instantiates a new request parsing auto configure.</p>
         * @param requestParsingArgumentResolver {@link io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver} <p>The request parsing argument resolver parameter is <code>RequestParsingArgumentResolver</code> type.</p>
         * @see  io.github.nichetoolkit.rest.parsing.RequestParsingArgumentResolver
         */
        public RequestParsingAutoConfigure(RequestParsingArgumentResolver requestParsingArgumentResolver) {
            this.requestParsingArgumentResolver = requestParsingArgumentResolver;
        }

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            resolvers.add(this.requestParsingArgumentResolver);
        }
    }

}
