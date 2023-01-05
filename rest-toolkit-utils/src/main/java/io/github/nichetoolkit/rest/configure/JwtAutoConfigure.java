package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

/**
 * <p>JwtAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@ConditionalOnClass({JwtWorker.class})
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@AutoConfiguration(after = {RadixAutoConfigure.class})
@ConditionalOnProperty(value = "nichetoolkit.rest.jwt.enabled", havingValue = "true")
public class JwtAutoConfigure {

    public JwtAutoConfigure() {
        log.debug("================= jwt-auto-configure initiated ！ ===================");
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(JwtWorker.class)
    public JwtWorker jwtWorker(RestJwtProperties jwtProperties) {
        return new JwtWorker(jwtProperties);
    }
}
