package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.DefaultLoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties({RestInterceptProperties.class})
@ConditionalOnProperty(value = "nichetoolkit.rest.intercept.enabled", havingValue = "true")
public class RestInterceptAutoConfigure implements WebMvcConfigurer {
    private final RestInterceptProperties interceptProperties;

    @Autowired
    public RestInterceptAutoConfigure(RestInterceptProperties interceptProperties) {
        this.interceptProperties = interceptProperties;
        log.debug("The auto configuration for [rest-intercept] initiated");
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (this.interceptProperties.getEnabled()) {
            registry.addInterceptor(new DefaultLoggingInterceptor(this.interceptProperties))
                    .addPathPatterns("/**")
                    .excludePathPatterns("/error");
        }
    }




}
