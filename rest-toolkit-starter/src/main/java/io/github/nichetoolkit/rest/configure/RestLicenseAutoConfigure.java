package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.interceptor.DefaultLicenseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.jspecify.annotations.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <code>RestLicenseAutoConfigure</code>
 * <p>The rest license auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @see org.springframework.boot.autoconfigure.ImportAutoConfiguration
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties({RestLicenseProperties.class})
@ImportAutoConfiguration({RestLicenseAutoConfigure.DefaultLicenseAutoConfigure.class})
public class RestLicenseAutoConfigure {

    /**
     * <code>licenseProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The <code>licenseProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    private final RestLicenseProperties licenseProperties;

    /**
     * <code>RestLicenseAutoConfigure</code>
     * <p>Instantiates a new rest license auto configure.</p>
     * @param licenseProperties {@link io.github.nichetoolkit.rest.configure.RestLicenseProperties} <p>The license properties parameter is <code>RestLicenseProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLicenseProperties
     */
    public RestLicenseAutoConfigure(RestLicenseProperties licenseProperties) {
        this.licenseProperties = licenseProperties;
        log.debug("The auto configuration for [rest-license] initiated");
    }

    /**
     * <code>DefaultLicenseAutoConfigure</code>
     * <p>The default license auto configure class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     * @since Jdk1.8
     */
    @Configuration
    @ConditionalOnProperty(value = "nichetoolkit.rest.license.listener.intercept", havingValue = "true")
    public class DefaultLicenseAutoConfigure implements WebMvcConfigurer {

        @Override
        public void addInterceptors(@NonNull InterceptorRegistry registry) {
            registry.addInterceptor(new DefaultLicenseInterceptor(licenseProperties));
        }
    }


}
