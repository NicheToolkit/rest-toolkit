package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.license.LicenseCreatorWorker;
import io.github.nichetoolkit.rest.license.LicenseVerifierWorker;
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

@Slf4j
@AutoConfiguration
@AutoConfigureAfter(RestCoreAutoConfigure.class)
@EnableConfigurationProperties({RestLicenseProperties.class,RestJwtProperties.class,RestRadixProperties.class,RestRsaProperties.class,RestShaProperties.class,RestAesProperties.class})
public class RestWorkerAutoConfigure {

    private final RestLicenseProperties licenseProperties;
    private final RestJwtProperties jwtProperties;
    private final RestRadixProperties radixProperties;
    private final RestShaProperties shaProperties;
    private final RestRsaProperties rsaProperties;
    private final RestAesProperties aesProperties;

    public RestWorkerAutoConfigure(RestLicenseProperties licenseProperties,RestJwtProperties jwtProperties, RestRadixProperties radixProperties, RestShaProperties shaProperties, RestRsaProperties rsaProperties, RestAesProperties aesProperties) {
        this.licenseProperties = licenseProperties;
        this.jwtProperties = jwtProperties;
        this.radixProperties = radixProperties;
        this.shaProperties = shaProperties;
        this.rsaProperties = rsaProperties;
        this.aesProperties = aesProperties;
        log.debug("The auto configuration for [rest-worker] initiated");
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(RadixWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.radix.enabled", havingValue = "true")
    public RadixWorker radixWorker() {
        return new RadixWorker(this.radixProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(JwtWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.jwt.enabled", havingValue = "true")
    public JwtWorker jwtWorker() {
        return new JwtWorker(this.jwtProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ShaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.sha.enabled", havingValue = "true")
    public ShaWorker shaWorker() {
        return new ShaWorker(this.shaProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(RsaWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.rsa.enabled", havingValue = "true")
    public RsaWorker rsaWorker() {
        return new RsaWorker(this.rsaProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(AesWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.aes.enabled", havingValue = "true")
    public AesWorker aesWorker() {
        return new AesWorker(this.aesProperties);
    }


    @Bean
    @Primary
    @ConditionalOnMissingBean(LicenseCreatorWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.license.creator.enabled", havingValue = "true")
    public LicenseCreatorWorker licenseCreatorWorker() {
        return new LicenseCreatorWorker(this.licenseProperties);
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(LicenseVerifierWorker.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.license.verifier.enabled", havingValue = "true")
    public LicenseVerifierWorker licenseVerifierWorker() {
        return new LicenseVerifierWorker(this.licenseProperties);
    }

}
