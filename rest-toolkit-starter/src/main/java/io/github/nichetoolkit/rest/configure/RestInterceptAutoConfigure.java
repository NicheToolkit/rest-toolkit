package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.RestHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>RestInterceptConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.intercept.enabled", havingValue = "true", matchIfMissing = true)
public class RestInterceptAutoConfigure implements WebMvcConfigurer {
    private final RestHandlerInterceptor handlerInterceptor;
    private final RestInterceptProperties interceptProperties;

    @Autowired
    public RestInterceptAutoConfigure(RestHandlerInterceptor handlerInterceptor,
                                      RestInterceptProperties interceptProperties) {
        log.debug("================= intercept-auto-config initiated ！ ===================");
        this.handlerInterceptor = handlerInterceptor;
        this.interceptProperties = interceptProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (interceptProperties.getEnabled()) {
            registry.addInterceptor(this.handlerInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/error");
        }
    }




}
