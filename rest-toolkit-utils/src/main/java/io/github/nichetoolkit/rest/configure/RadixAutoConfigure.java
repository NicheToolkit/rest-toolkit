package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.worker.RadixWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

/**
 * <p>RadixAutoConfigure</p>
 *
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@AutoConfiguration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@AutoConfigureAfter(value = {JwtAutoConfigure.class})
@ConditionalOnProperty(value = "nichetoolkit.rest.radix.enabled", havingValue = "true")
public class RadixAutoConfigure {

    @Bean
    @Primary
    @ConditionalOnMissingBean(RadixWorker.class)
    public RadixWorker radixWorker(RestRadixProperties radixProperties) {
        return new RadixWorker(radixProperties);
    }
}