package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.identity.IdentityFactory;
import io.github.nichetoolkit.rest.identity.IdentityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * <code>RestIdentityAutoConfigure</code>
 * <p>The type rest identity auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
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
@ConditionalOnProperty(value = "nichetoolkit.rest.identity.enabled", havingValue = "true", matchIfMissing = true)
public class RestIdentityAutoConfigure {

    /**
     * <code>RestIdentityAutoConfigure</code>
     * Instantiates a new rest identity auto configure.
     */
    public RestIdentityAutoConfigure() {
        log.debug("The auto configuration for [rest-identity] initiated");
    }

    /**
     * <code>identityFactory</code>
     * <p>the factory method.</p>
     * @param identityProperties {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>the identity properties parameter is <code>RestIdentityProperties</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.IdentityFactory} <p>the factory return object is <code>IdentityFactory</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     * @see io.github.nichetoolkit.rest.identity.IdentityFactory
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(IdentityFactory.class)
    public IdentityFactory identityFactory(RestIdentityProperties identityProperties) {
        return new IdentityFactory(identityProperties);
    }

    /**
     * <code>IdentityManager</code>
     * <p>the manager method.</p>
     * @param identityProperties {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>the identity properties parameter is <code>RestIdentityProperties</code> type.</p>
     * @param environment        {@link org.springframework.core.env.Environment} <p>the environment parameter is <code>Environment</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.identity.IdentityManager} <p>the manager return object is <code>IdentityManager</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestIdentityProperties
     * @see org.springframework.core.env.Environment
     * @see io.github.nichetoolkit.rest.identity.IdentityManager
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(IdentityFactory.class)
    public IdentityManager IdentityManager(RestIdentityProperties identityProperties, Environment environment) {
        return new IdentityManager(identityProperties,environment);
    }
}
