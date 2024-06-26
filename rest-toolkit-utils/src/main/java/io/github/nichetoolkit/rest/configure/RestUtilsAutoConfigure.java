package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import io.github.nichetoolkit.rest.worker.rsa.RsaWorker;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <p>RestUtilsAutoConfigure</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestUtilsAutoConfigure {
    public RestUtilsAutoConfigure() {
        log.debug("================= rest-toolkit-utils initiated ！ ===================");
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(RadixWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.radix.enabled", havingValue = "true", matchIfMissing = true)
    public RadixWorker radixWorker(RestRadixProperties radixProperties) {
        return new RadixWorker(radixProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(JwtWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.jwt.enabled", havingValue = "true", matchIfMissing = true)
    public JwtWorker jwtWorker(RestJwtProperties jwtProperties) {
        return new JwtWorker(jwtProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ShaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.sha.enabled", havingValue = "true", matchIfMissing = true)
    public ShaWorker shaWorker(RestShaProperties shaProperties) {
        return new ShaWorker(shaProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(RsaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.rsa.enabled", havingValue = "true", matchIfMissing = true)
    public RsaWorker rsaWorker(RestRsaProperties rsaProperties) {
        return new RsaWorker(rsaProperties);
    }

}
