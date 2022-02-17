package io.github.nichetoolkit.rest.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nichetoolkit.rest.holder.ObjectMapperHolder;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import io.github.nichetoolkit.rest.worker.Md5Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired(required = false)
    @ConditionalOnMissingBean(ObjectMapperHolder.class)
    public ObjectMapperHolder objectMapperHolder(ObjectMapper objectMapper) {
        return new ObjectMapperHolder(objectMapper);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(JwtWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.jwt.enabled", havingValue = "true")
    public JwtWorker jwtWorker(RestJwtProperties jwtProperties) {
        return new JwtWorker(jwtProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(Md5Worker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.md5.enabled", havingValue = "true")
    public Md5Worker md5Worker(RestMd5Properties md5Properties) {
        return new Md5Worker(md5Properties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(RadixWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.radix.enabled", havingValue = "true")
    public RadixWorker radixWorker(RestRadixProperties radixProperties) {
        return new RadixWorker(radixProperties);
    }


}
