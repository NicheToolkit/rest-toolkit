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
 * <code>RestUtilsAutoConfigure</code>
 * <p>The type rest utils auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ComponentScan
 * @since Jdk1.8
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
public class RestUtilsAutoConfigure {
    /**
     * <code>RestUtilsAutoConfigure</code>
     * Instantiates a new rest utils auto configure.
     */
    public RestUtilsAutoConfigure() {
        log.debug("the auto configuration for [rest-Utils] initiated");
    }

    /**
     * <code>radixWorker</code>
     * <p>the worker method.</p>
     * @param radixProperties {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>the radix properties parameter is <code>RestRadixProperties</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>the worker return object is <code>RadixWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     * @see io.github.nichetoolkit.rest.worker.RadixWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(RadixWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.radix.enabled", havingValue = "true", matchIfMissing = true)
    public RadixWorker radixWorker(RestRadixProperties radixProperties) {
        return new RadixWorker(radixProperties);
    }

    /**
     * <code>jwtWorker</code>
     * <p>the worker method.</p>
     * @param jwtProperties {@link io.github.nichetoolkit.rest.configure.RestJwtProperties} <p>the jwt properties parameter is <code>RestJwtProperties</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtWorker} <p>the worker return object is <code>JwtWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestJwtProperties
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(JwtWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.jwt.enabled", havingValue = "true", matchIfMissing = true)
    public JwtWorker jwtWorker(RestJwtProperties jwtProperties) {
        return new JwtWorker(jwtProperties);
    }

    /**
     * <code>shaWorker</code>
     * <p>the worker method.</p>
     * @param shaProperties {@link io.github.nichetoolkit.rest.configure.RestShaProperties} <p>the sha properties parameter is <code>RestShaProperties</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>the worker return object is <code>ShaWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestShaProperties
     * @see io.github.nichetoolkit.rest.worker.sha.ShaWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ShaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.sha.enabled", havingValue = "true", matchIfMissing = true)
    public ShaWorker shaWorker(RestShaProperties shaProperties) {
        return new ShaWorker(shaProperties);
    }

    /**
     * <code>rsaWorker</code>
     * <p>the worker method.</p>
     * @param rsaProperties {@link io.github.nichetoolkit.rest.configure.RestRsaProperties} <p>the rsa properties parameter is <code>RestRsaProperties</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaWorker} <p>the worker return object is <code>RsaWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRsaProperties
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(RsaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.rsa.enabled", havingValue = "true", matchIfMissing = true)
    public RsaWorker rsaWorker(RestRsaProperties rsaProperties) {
        return new RsaWorker(rsaProperties);
    }

}
