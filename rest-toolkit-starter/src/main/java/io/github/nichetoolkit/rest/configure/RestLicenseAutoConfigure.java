package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.DefaultLicenseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties({RestLicenseProperties.class})
@ImportAutoConfiguration({RestLicenseAutoConfigure.DefaultLicenseAutoConfigure.class})
public class RestLicenseAutoConfigure {

    private final RestLicenseProperties licenseProperties;

    @Autowired
    public RestLicenseAutoConfigure(RestLicenseProperties licenseProperties) {
        this.licenseProperties = licenseProperties;
        log.debug("The auto configuration for [rest-license] initiated");
    }

    @Bean
    public DefaultLicenseInterceptor licenseInterceptor() {
        return new DefaultLicenseInterceptor(licenseProperties);
    }

    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.license.listener.enabled", havingValue = "true")
    public class DefaultLicenseAutoConfigure implements WebMvcConfigurer {

        @Override
        public void addInterceptors(@NonNull InterceptorRegistry registry) {
            registry.addInterceptor(licenseInterceptor());
        }
    }


}
