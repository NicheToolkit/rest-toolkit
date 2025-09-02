package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.worker.AesWorker;
import io.github.nichetoolkit.rest.worker.RadixWorker;
import io.github.nichetoolkit.rest.worker.jwt.JwtWorker;
import io.github.nichetoolkit.rest.worker.rsa.RsaWorker;
import io.github.nichetoolkit.rest.worker.sha.ShaWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * <code>RestWorkerAutoConfigure</code>
 * <p>The rest worker auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see org.springframework.boot.autoconfigure.AutoConfigureAfter
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RestCoreAutoConfigure.class)
@EnableConfigurationProperties({RestJwtProperties.class,RestRadixProperties.class,RestRsaProperties.class,RestShaProperties.class,RestAesProperties.class})
public class RestWorkerAutoConfigure {

    /**
     * <code>jwtProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestJwtProperties} <p>The <code>jwtProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestJwtProperties
     */
    private final RestJwtProperties jwtProperties;
    /**
     * <code>radixProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>The <code>radixProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     */
    private final RestRadixProperties radixProperties;
    /**
     * <code>shaProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestShaProperties} <p>The <code>shaProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestShaProperties
     */
    private final RestShaProperties shaProperties;
    /**
     * <code>rsaProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestRsaProperties} <p>The <code>rsaProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestRsaProperties
     */
    private final RestRsaProperties rsaProperties;
    /**
     * <code>aesProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestAesProperties} <p>The <code>aesProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestAesProperties
     */
    private final RestAesProperties aesProperties;

    /**
     * <code>RestWorkerAutoConfigure</code>
     * <p>Instantiates a new rest worker auto configure.</p>
     * @param jwtProperties   {@link io.github.nichetoolkit.rest.configure.RestJwtProperties} <p>The jwt properties parameter is <code>RestJwtProperties</code> type.</p>
     * @param radixProperties {@link io.github.nichetoolkit.rest.configure.RestRadixProperties} <p>The radix properties parameter is <code>RestRadixProperties</code> type.</p>
     * @param shaProperties   {@link io.github.nichetoolkit.rest.configure.RestShaProperties} <p>The sha properties parameter is <code>RestShaProperties</code> type.</p>
     * @param rsaProperties   {@link io.github.nichetoolkit.rest.configure.RestRsaProperties} <p>The rsa properties parameter is <code>RestRsaProperties</code> type.</p>
     * @param aesProperties   {@link io.github.nichetoolkit.rest.configure.RestAesProperties} <p>The aes properties parameter is <code>RestAesProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestJwtProperties
     * @see io.github.nichetoolkit.rest.configure.RestRadixProperties
     * @see io.github.nichetoolkit.rest.configure.RestShaProperties
     * @see io.github.nichetoolkit.rest.configure.RestRsaProperties
     * @see io.github.nichetoolkit.rest.configure.RestAesProperties
     */
    public RestWorkerAutoConfigure(RestJwtProperties jwtProperties, RestRadixProperties radixProperties, RestShaProperties shaProperties, RestRsaProperties rsaProperties, RestAesProperties aesProperties) {
        this.jwtProperties = jwtProperties;
        this.radixProperties = radixProperties;
        this.shaProperties = shaProperties;
        this.rsaProperties = rsaProperties;
        this.aesProperties = aesProperties;
        log.debug("The auto configuration for [rest-worker] initiated");
    }

    /**
     * <code>radixWorker</code>
     * <p>The radix worker method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.RadixWorker} <p>The radix worker return object is <code>RadixWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.RadixWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(RadixWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.radix.enabled", havingValue = "true")
    public RadixWorker radixWorker() {
        return new RadixWorker(this.radixProperties);
    }

    /**
     * <code>jwtWorker</code>
     * <p>The jwt worker method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.jwt.JwtWorker} <p>The jwt worker return object is <code>JwtWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.jwt.JwtWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(JwtWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.jwt.enabled", havingValue = "true")
    public JwtWorker jwtWorker() {
        return new JwtWorker(this.jwtProperties);
    }

    /**
     * <code>shaWorker</code>
     * <p>The sha worker method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.sha.ShaWorker} <p>The sha worker return object is <code>ShaWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.sha.ShaWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ShaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.sha.enabled", havingValue = "true")
    public ShaWorker shaWorker() {
        return new ShaWorker(this.shaProperties);
    }

    /**
     * <code>rsaWorker</code>
     * <p>The rsa worker method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.rsa.RsaWorker} <p>The rsa worker return object is <code>RsaWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.rsa.RsaWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(RsaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.rsa.enabled", havingValue = "true")
    public RsaWorker rsaWorker() {
        return new RsaWorker(this.rsaProperties);
    }

    /**
     * <code>aesWorker</code>
     * <p>The aes worker method.</p>
     * @return {@link io.github.nichetoolkit.rest.worker.AesWorker} <p>The aes worker return object is <code>AesWorker</code> type.</p>
     * @see io.github.nichetoolkit.rest.worker.AesWorker
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.context.annotation.Primary
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(AesWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.aes.enabled", havingValue = "true")
    public AesWorker aesWorker() {
        return new AesWorker(this.aesProperties);
    }


}
