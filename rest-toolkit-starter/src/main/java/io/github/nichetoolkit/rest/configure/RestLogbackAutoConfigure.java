package io.github.nichetoolkit.rest.configure;


import io.github.nichetoolkit.rest.RestLogKey;
import io.github.nichetoolkit.rest.logback.DefaultLogKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

    /**
     * <code>logbackProperties</code>
     * {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the <code>logbackProperties</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    private final RestLogbackProperties logbackProperties;

    /**
     * <code>RestLogbackAutoConfigure</code>
     * Instantiates a new rest logback auto configure.
     * @param logbackProperties {@link io.github.nichetoolkit.rest.configure.RestLogbackProperties} <p>the logback properties parameter is <code>RestLogbackProperties</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestLogbackProperties
     */
    public RestLogbackAutoConfigure(RestLogbackProperties logbackProperties) {
        this.logbackProperties = logbackProperties;
    }

    /**
     * <code>restLogKey</code>
     * <p>the log key method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestLogKey} <p>the log key return object is <code>RestLogKey</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestLogKey
     * @see org.springframework.context.annotation.Bean
     * @see org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
     */
    @Bean
    @ConditionalOnMissingBean(RestLogKey.class)
    public RestLogKey restLogKey() {
        return new DefaultLogKeyGenerator(logbackProperties);
    }


}
