package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ConditionalOnProperty(value = "nichetoolkit.rest.intercept.enabled", havingValue = "true")
public class RestInterceptAutoConfigure implements WebMvcConfigurer {
    private final DefaultLoggingInterceptor handlerInterceptor;
    private final RestInterceptProperties interceptProperties;

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
