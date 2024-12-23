package io.github.nichetoolkit.rest.configure;


import ch.qos.logback.classic.pattern.MessageConverter;
import io.github.nichetoolkit.rest.RestHttpRequest;
import io.github.nichetoolkit.rest.RestLoggingKey;
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
 * <p>The rest logback auto configure class.</p>
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

    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    private final RestLogbackProperties logbackProperties;

    /**
     * <code>RestLogbackAutoConfigure</code>
     * <p>Instantiates a new rest logback auto configure.</p>
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>The logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     * @see org.springframework.beans.factory.annotation.Autowired
     */
    @Autowired
    public RestLogbackAutoConfigure(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
        log.debug("The auto configuration for [rest-logback] initiated");
    }

    /**
     * <code>loggingKeyGenerator</code>
     * <p>The logging key generator method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>The logging key generator return object is <code>RestLoggingKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestLoggingKey
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
     */
    @Bean
    @ConditionalOnMissingBean(RestLoggingKey.class)
    @ConditionalOnProperty(value = "nichetoolkit.rest.logback.enabled", havingValue = "true", matchIfMissing = true)
    public RestLoggingKey loggingKeyGenerator() {
        return new RestLoggingKeyGenerator(logbackProperties) {
            @Override
            public String doAccessTokenHandle(RestHttpRequest requestWrapper) {
                return null;
            }

            @Override
            public String doAccessAuthHandle(RestHttpRequest httpRequest) {
                return null;
            }
        };
    }

    /**
     * <code>messageConverter</code>
     * <p>The message converter method.</p>
     * @return {@link io.github.nichetoolkit.rest.logback.DefaultMessageConverter} <p>The message converter return object is <code>DefaultMessageConverter</code> type.</p>
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
