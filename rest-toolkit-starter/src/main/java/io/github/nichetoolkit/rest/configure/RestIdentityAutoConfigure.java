package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.identity.IdentityFactory;
import io.github.nichetoolkit.rest.identity.IdentityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ConditionalOnProperty(value = "nichetoolkit.rest.identity.enabled", havingValue = "true")
public class RestIdentityAutoConfigure {

    public RestIdentityAutoConfigure() {
        log.debug("The auto configuration for [rest-identity] initiated");
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
