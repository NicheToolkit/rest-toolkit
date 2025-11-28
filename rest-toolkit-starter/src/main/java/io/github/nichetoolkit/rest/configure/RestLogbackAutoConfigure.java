package io.github.nichetoolkit.rest.configure;


import ch.qos.logback.classic.pattern.MessageConverter;
import io.github.nichetoolkit.rest.*;
import io.github.nichetoolkit.rest.logback.DefaultLogbackFilter;
import io.github.nichetoolkit.rest.logback.DefaultMessageConverter;
import io.github.nichetoolkit.rest.logback.DefaultThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <code>RestLogbackAutoConfigure</code>
 * <p>The rest logback auto configure class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.extern.slf4j.Slf4j
 * @see org.springframework.boot.autoconfigure.AutoConfiguration
 * @see java.lang.SuppressWarnings
 * @see org.springframework.boot.context.properties.EnableConfigurationProperties
 * @since Jdk17
 */
@Slf4j
@AutoConfiguration
@SuppressWarnings("SameNameButDifferent")
@EnableConfigurationProperties(RestLogbackProperties.class)
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
     */
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
    @ConditionalOnProperty(value = "nichetoolkit.rest.logback.enabled", havingValue = "true")
    public RestLoggingKey loggingKeyGenerator() {
        return new RestLoggingKeyGenerator(this.logbackProperties) {
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
        return new DefaultMessageConverter(this.logbackProperties);
    }


    /**
     * <code>defaultLogbackFilter</code>
     * <p>The default logback filter method.</p>
     * @param loggingKey {@link io.github.nichetoolkit.rest.RestLoggingKey} <p>The logging key parameter is <code>RestLoggingKey</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.logback.DefaultLogbackFilter} <p>The default logback filter return object is <code>DefaultLogbackFilter</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestLoggingKey
     * @see io.github.nichetoolkit.rest.logback.DefaultLogbackFilter
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnBean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
    @ConditionalOnBean(RestLoggingKey.class)
    @ConditionalOnMissingBean(DefaultLogbackFilter.class)
    public DefaultLogbackFilter defaultLogbackFilter(RestLoggingKey loggingKey) {
        return new DefaultLogbackFilter(this.logbackProperties, loggingKey);
    }

    /**
     * <code>autoLogbackFilter</code>
     * <p>The auto logback filter method.</p>
     * @return {@link io.github.nichetoolkit.rest.logback.DefaultLogbackFilter} <p>The auto logback filter return object is <code>DefaultLogbackFilter</code> type.</p>
     * @see io.github.nichetoolkit.rest.logback.DefaultLogbackFilter
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.core.annotation.Order
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
    @ConditionalOnMissingBean({DefaultLogbackFilter.class, RestLoggingKey.class})
    public DefaultLogbackFilter autoLogbackFilter() {
        return new DefaultLogbackFilter(this.logbackProperties);
    }

    /**
     * <code>threadPoolTaskExecutor</code>
     * <p>The thread pool task executor method.</p>
     * @return {@link org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor} <p>The thread pool task executor return object is <code>ThreadPoolTaskExecutor</code> type.</p>
     * @see org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean({ThreadPoolTaskExecutor.class})
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new DefaultThreadPoolTaskExecutor();
    }
}
