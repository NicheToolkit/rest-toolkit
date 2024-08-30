package io.github.nichetoolkit.rest.configure;


import ch.qos.logback.classic.pattern.MessageConverter;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestLoggingKeyAdvice;
import io.github.nichetoolkit.rest.RestLoggingKeyGenerator;
import io.github.nichetoolkit.rest.logback.DefaultMessageConverter;
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
 * @since Jdk1.8
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@ComponentScan(basePackages = {"io.github.nichetoolkit.rest"})
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
            public String doAccessTokenHandle(RestHttpRequest requestWrapper) {
                return null;
            }
        };
    }

    /**
     * <code>messageConverter</code>
     * <p>the converter method.</p>
     * @return {@link io.github.nichetoolkit.rest.logback.DefaultMessageConverter} <p>the converter return object is <code>DefaultMessageConverter</code> type.</p>
     * @see io.github.nichetoolkit.rest.logback.DefaultMessageConverter
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(MessageConverter.class)
    public DefaultMessageConverter messageConverter() {
        return new DefaultMessageConverter(logbackProperties);
    }


}
