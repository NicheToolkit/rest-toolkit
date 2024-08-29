package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.HttpRequestWrapper;
import io.github.nichetoolkit.rest.RestLoggingKeyAdvice;
import io.github.nichetoolkit.rest.RestLoggingKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * <code>RestLogbackAutoConfigure</code>
 * <p>The type rest logback auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
@ConditionalOnProperty(value = "nichetoolkit.rest.logback.enabled", havingValue = "true", matchIfMissing = true)
public class RestLogbackAutoConfigure {

    private final RestLogbackProperties logbackProperties;

    /**
     * <code>RestLogbackAutoConfigure</code>
     * Instantiates a new rest logback auto configure.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestLogbackAutoConfigure(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    /**
     * <code>loggingKeyGenerator</code>
     * <p>the key generator method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestLoggingKeyAdvice} <p>the key generator return object is <code>RestLoggingKeyAdvice</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestLoggingKeyAdvice
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(RestLoggingKeyAdvice.class)
    public RestLoggingKeyAdvice loggingKeyGenerator() {
        return new RestLoggingKeyGenerator(logbackProperties) {
            @Override
            public String doAccessTokenHandle(HttpRequestWrapper requestWrapper) {
                return null;
            }
        };
    }


}
