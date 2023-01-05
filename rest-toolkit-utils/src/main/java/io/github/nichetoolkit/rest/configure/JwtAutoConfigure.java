package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * <p>JwtAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@ConditionalOnClass({JwtWorker.class})
@AutoConfiguration(after = {RestUtilsAutoConfigure.class,RadixAutoConfigure.class})
@ConditionalOnProperty(value = "nichetoolkit.rest.radix.enabled", havingValue = "true")
@EnableConfigurationProperties({RestJwtProperties.class})
public class JwtAutoConfigure {

    @Bean
    @Primary
    @ConditionalOnMissingBean(JwtWorker.class)
    public JwtWorker jwtWorker(RestJwtProperties jwtProperties) {
        return new JwtWorker(jwtProperties);
    }
}
