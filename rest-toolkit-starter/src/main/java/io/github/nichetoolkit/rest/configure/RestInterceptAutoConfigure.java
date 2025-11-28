package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.jspecify.annotations.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <code>RestInterceptAutoConfigure</code>
 * <p>The rest intercept auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties({RestInterceptProperties.class})
@ImportAutoConfiguration({RestInterceptAutoConfigure.DefaultLoggingAutoConfigure.class})
public class RestInterceptAutoConfigure {

    /**
     * <code>interceptProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>The <code>interceptProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    private final RestInterceptProperties interceptProperties;

    /**
     * <code>RestInterceptAutoConfigure</code>
     * <p>Instantiates a new rest intercept auto configure.</p>
     * @param interceptProperties {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>The intercept properties parameter is <code>RestInterceptProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    public RestInterceptAutoConfigure(RestInterceptProperties interceptProperties) {
        this.interceptProperties = interceptProperties;
        log.debug("The auto configuration for [rest-intercept] initiated");
    }

    /**
     * <code>loggingInterceptor</code>
     * <p>The logging interceptor method.</p>
     * @return {@link io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor} <p>The logging interceptor return object is <code>DefaultLoggingInterceptor</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor
     * @see org.springframework.context.annotation.Bean
     */
    @Bean
    public DefaultLoggingInterceptor loggingInterceptor() {
        return new DefaultLoggingInterceptor(this.interceptProperties);
    }

    /**
     * <code>DefaultLoggingAutoConfigure</code>
     * <p>The default logging auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk1.8
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.intercept.enabled", havingValue = "true")
    public class DefaultLoggingAutoConfigure implements WebMvcConfigurer {

        /**
         * <code>loggingInterceptor</code>
         * {@link io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor} <p>The <code>loggingInterceptor</code> field.</p>
         * @see io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor
         */
        private final DefaultLoggingInterceptor loggingInterceptor;

        /**
         * <code>DefaultLoggingAutoConfigure</code>
         * <p>Instantiates a new default logging auto configure.</p>
         * @param loggingInterceptor {@link io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor} <p>The logging interceptor parameter is <code>DefaultLoggingInterceptor</code> type.</p>
         * @see io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor
         */
        public DefaultLoggingAutoConfigure(DefaultLoggingInterceptor loggingInterceptor) {
            this.loggingInterceptor = loggingInterceptor;
        }

        @Override
        public void addInterceptors(@NonNull InterceptorRegistry registry) {
            if (interceptProperties.getEnabled()) {
                registry.addInterceptor(loggingInterceptor)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/error");
            }
        }
    }


}
