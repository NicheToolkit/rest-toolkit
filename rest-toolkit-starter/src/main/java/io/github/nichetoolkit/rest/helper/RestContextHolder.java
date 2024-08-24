package io.github.nichetoolkit.rest.helper;

import io.github.nichetoolkit.rest.configure.RestHttpProperties;
import io.github.nichetoolkit.rest.configure.RestIdentityProperties;
import io.github.nichetoolkit.rest.configure.RestInterceptProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * <p>RestContextHolder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class RestContextHolder implements InitializingBean {

    private static RestContextHolder INSTANCE = null;

    private final ApplicationContext applicationContext;
    private final RestHttpProperties httpProperties;
    private final RestIdentityProperties identityProperties;
    private final RestInterceptProperties interceptProperties;

    @Autowired
    public RestContextHolder(ApplicationContext applicationContext, RestHttpProperties httpProperties, RestIdentityProperties identityProperties, RestInterceptProperties interceptProperties) {
        this.applicationContext = applicationContext;
        this.httpProperties = httpProperties;
        this.identityProperties = identityProperties;
        this.interceptProperties = interceptProperties;
    }

    public static RestContextHolder getInstance() {
        return INSTANCE;
    }

    @Override
    public void afterPropertiesSet() {
        INSTANCE = this;
    }

    public static ApplicationContext applicationContext() {
        return INSTANCE.applicationContext;
    }

    public static RestHttpProperties httpProperties() {
        return INSTANCE.httpProperties;
    }

    public static RestIdentityProperties identityProperties() {
        return INSTANCE.identityProperties;
    }

    public static RestInterceptProperties interceptProperties() {
        return INSTANCE.interceptProperties;
    }
}
