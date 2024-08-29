package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.DefaultHandlerInterceptor;
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
 * <p>The type rest intercept auto configure class.</p>
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
    private final DefaultHandlerInterceptor handlerInterceptor;
    private final RestInterceptProperties interceptProperties;

    /**
     * <code>RestInterceptAutoConfigure</code>
     * Instantiates a new rest intercept auto configure.
     * @param handlerInterceptor  {@link io.github.nichetoolkit.rest.interceptor.DefaultHandlerInterceptor} <p>the handler interceptor parameter is <code>DefaultHandlerInterceptor</code> type.</p>
     * @param interceptProperties {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the intercept properties parameter is <code>RestInterceptProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.interceptor.DefaultHandlerInterceptor
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestInterceptAutoConfigure(DefaultHandlerInterceptor handlerInterceptor,
                                      RestInterceptProperties interceptProperties) {
        log.debug("================= intercept-auto-config initiated ！ ===================");
        this.handlerInterceptor = handlerInterceptor;
        this.interceptProperties = interceptProperties;
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
