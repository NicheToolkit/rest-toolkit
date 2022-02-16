package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.RestNoteHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>RestInterceptConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.intercept.enabled", havingValue = "true")
public class RestInterceptAutoConfigure implements WebMvcConfigurer {
    private final RestNoteHandlerInterceptor handlerInterceptor;
    private final RestInterceptProperties interceptProperties;

    @Autowired
    public RestInterceptAutoConfigure(RestNoteHandlerInterceptor handlerInterceptor, RestInterceptProperties interceptProperties) {
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
