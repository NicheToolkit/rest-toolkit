package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.identity.IdentityFactory;
import io.github.nichetoolkit.rest.identity.IdentityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * <p>RestIdentityAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.identity.enabled", havingValue = "true", matchIfMissing = true)
public class RestIdentityAutoConfigure {

    public RestIdentityAutoConfigure() {
        log.debug("================= identity-auto-config initiated ！ ===================");
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(IdentityFactory.class)
    public IdentityFactory identityFactory(RestIdentityProperties identityProperties) {
        return new IdentityFactory(identityProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(IdentityFactory.class)
    public IdentityManager IdentityManager(RestIdentityProperties identityProperties, Environment environment) {
        return new IdentityManager(identityProperties,environment);
    }
}
