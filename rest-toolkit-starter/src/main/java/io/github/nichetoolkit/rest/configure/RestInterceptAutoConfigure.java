package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <code>RestInterceptAutoConfigure</code>
 * <p>The rest intercept auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.intercept.enabled", havingValue = "true", matchIfMissing = true)
public class RestInterceptAutoConfigure implements WebMvcConfigurer {
    /**
     * <code>handlerInterceptor</code>
     * {@link io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor} <p>The <code>handlerInterceptor</code> field.</p>
     * @see io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor
     */
    private final DefaultLoggingInterceptor handlerInterceptor;
    /**
     * <code>interceptProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>The <code>interceptProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    private final RestInterceptProperties interceptProperties;

    /**
     * <code>RestInterceptAutoConfigure</code>
     * <p>Instantiates a new rest intercept auto configure.</p>
     * @param handlerInterceptor  {@link io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor} <p>The handler interceptor parameter is <code>DefaultLoggingInterceptor</code> type.</p>
     * @param interceptProperties {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>The intercept properties parameter is <code>RestInterceptProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestInterceptAutoConfigure(DefaultLoggingInterceptor handlerInterceptor,
                                      RestInterceptProperties interceptProperties) {
        this.handlerInterceptor = handlerInterceptor;
        this.interceptProperties = interceptProperties;
        log.debug("The auto configuration for [rest-intercept] initiated");
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (interceptProperties.getEnabled()) {
            registry.addInterceptor(this.handlerInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/error");
        }
    }




}
