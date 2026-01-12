package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.identity.IdentityFactory;
import io.github.nichetoolkit.rest.identity.IdentityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * <code>RestIdentityAutoConfigure</code>
 * <p>The rest identity auto configure class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.boot.autoconfigure.AutoConfiguration
 * @see  java.lang.SuppressWarnings
 * @see  org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties(RestIdentityProperties.class)
@ConditionalOnProperty(value = "nichetoolkit.rest.identity.enabled", havingValue = "true")
public class RestIdentityAutoConfigure {

    /**
     * <code>identityProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>The <code>identityProperties</code> field.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestIdentityProperties
     */
    private final RestIdentityProperties identityProperties;

    /**
     * <code>RestIdentityAutoConfigure</code>
     * <p>Instantiates a new rest identity auto configure.</p>
     * @param identityProperties {@link io.github.nichetoolkit.rest.configure.RestIdentityProperties} <p>The identity properties parameter is <code>RestIdentityProperties</code> type.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestIdentityProperties
     */
    public RestIdentityAutoConfigure(RestIdentityProperties identityProperties) {
        this.identityProperties = identityProperties;
        log.debug("The auto configuration for [rest-identity] initiated");
    }

    /**
     * <code>identityFactory</code>
     * <p>The identity factory method.</p>
     * @return  {@link io.github.nichetoolkit.rest.identity.IdentityFactory} <p>The identity factory return object is <code>IdentityFactory</code> type.</p>
     * @see  io.github.nichetoolkit.rest.identity.IdentityFactory
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.context.annotation.Primary
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(IdentityFactory.class)
    public IdentityFactory identityFactory() {
        return new IdentityFactory(this.identityProperties);
    }

    /**
     * <code>IdentityManager</code>
     * <p>The identity manager method.</p>
     * @param environment {@link org.springframework.core.env.Environment} <p>The environment parameter is <code>Environment</code> type.</p>
     * @see  org.springframework.core.env.Environment
     * @see  io.github.nichetoolkit.rest.identity.IdentityManager
     * @see  org.springframework.context.annotation.Bean
     * @see  org.springframework.context.annotation.Primary
     * @see  org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @return  {@link io.github.nichetoolkit.rest.identity.IdentityManager} <p>The identity manager return object is <code>IdentityManager</code> type.</p>
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(IdentityFactory.class)
    public IdentityManager IdentityManager(Environment environment) {
        return new IdentityManager(this.identityProperties,environment);
    }
}
