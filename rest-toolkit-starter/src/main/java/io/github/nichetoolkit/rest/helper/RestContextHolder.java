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
 * <code>RestContextHolder</code>
 * <p>The type rest context holder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.beans.factory.InitializingBean
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.stereotype.Component
 * @since Jdk1.8
 */
@Slf4j
@Component
public class RestContextHolder implements InitializingBean {

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.rest.helper.RestContextHolder} <p>the constant <code>INSTANCE</code> field.</p>
     */
    private static RestContextHolder INSTANCE = null;

    /**
     * <code>applicationContext</code>
     * {@link org.springframework.context.ApplicationContext} <p>the <code>applicationContext</code> field.</p>
     * @see org.springframework.context.ApplicationContext
     */
    private final ApplicationContext applicationContext;
    /**
     * <code>httpProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestHttpProperties} <p>the <code>httpProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestHttpProperties
     */
    private final RestHttpProperties httpProperties;
    /**
     * <code>identityProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>the <code>identityProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     */
    private final RestIdentityProperties identityProperties;
    /**
     * <code>interceptProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the <code>interceptProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    private final RestInterceptProperties interceptProperties;

    /**
     * <code>RestContextHolder</code>
     * Instantiates a new rest context holder.
     * @param applicationContext  {@link org.springframework.context.ApplicationContext} <p>the application context parameter is <code>ApplicationContext</code> type.</p>
     * @param httpProperties      {@link io.github.nichetoolkit.rest.configure.RestHttpProperties} <p>the http properties parameter is <code>RestHttpProperties</code> type.</p>
     * @param identityProperties  {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>the identity properties parameter is <code>RestIdentityProperties</code> type.</p>
     * @param interceptProperties {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the intercept properties parameter is <code>RestInterceptProperties</code> type.</p>
     * @see org.springframework.context.ApplicationContext
     * @see io.github.nichetoolkit.rest.configure.RestHttpProperties
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestContextHolder(ApplicationContext applicationContext, RestHttpProperties httpProperties, RestIdentityProperties identityProperties, RestInterceptProperties interceptProperties) {
        this.applicationContext = applicationContext;
        this.httpProperties = httpProperties;
        this.identityProperties = identityProperties;
        this.interceptProperties = interceptProperties;
    }

    /**
     * <code>getInstance</code>
     * <p>the instance getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.helper.RestContextHolder} <p>the instance return object is <code>RestContextHolder</code> type.</p>
     */
    public static RestContextHolder getInstance() {
        return INSTANCE;
    }

    @Override
    public void afterPropertiesSet() {
        INSTANCE = this;
    }

    /**
     * <code>applicationContext</code>
     * <p>the context method.</p>
     * @return {@link org.springframework.context.ApplicationContext} <p>the context return object is <code>ApplicationContext</code> type.</p>
     * @see org.springframework.context.ApplicationContext
     */
    public static ApplicationContext applicationContext() {
        return INSTANCE.applicationContext;
    }

    /**
     * <code>httpProperties</code>
     * <p>the properties method.</p>
     * @return {@link io.github.nichetoolkit.rest.configure.RestHttpProperties} <p>the properties return object is <code>RestHttpProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestHttpProperties
     */
    public static RestHttpProperties httpProperties() {
        return INSTANCE.httpProperties;
    }

    /**
     * <code>identityProperties</code>
     * <p>the properties method.</p>
     * @return {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>the properties return object is <code>RestIdentityProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     */
    public static RestIdentityProperties identityProperties() {
        return INSTANCE.identityProperties;
    }

    /**
     * <code>interceptProperties</code>
     * <p>the properties method.</p>
     * @return {@link io.github.nichetoolkit.rest.configure.RestInterceptProperties} <p>the properties return object is <code>RestInterceptProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestInterceptProperties
     */
    public static RestInterceptProperties interceptProperties() {
        return INSTANCE.interceptProperties;
    }
}
