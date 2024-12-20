package io.github.nichetoolkit.rest.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <code>RestExceptionProperties</code>
 * <p>The rest exception properties class.</p>
 * @see  lombok.Getter
 * @see  lombok.Getter
 * @see  lombok.Getter
 * @see  lombok.Getter
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.error")
public class RestExceptionProperties {
    /**
     * <code>consoleLog</code>
     * {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog} <p>The <code>consoleLog</code> field.</p>
     * @see  io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog
     * @see  io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog
     */
    @NestedConfigurationProperty
    private ConsoleLog consoleLog = new ConsoleLog();

    /**
     * <code>ConsoleLog</code>
     * <p>The console log class.</p>
     * @see  lombok.Getter
     * @see  lombok.Getter
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @Getter
    @Setter
    public static class ConsoleLog {
        /**
         * <code>restExceptionEnabled</code>
         * {@link java.lang.Boolean} <p>The <code>restExceptionEnabled</code> field.</p>
         * @see  java.lang.Boolean
         */
        private Boolean restExceptionEnabled = false;
        /**
         * <code>commonExceptionEnabled</code>
         * {@link java.lang.Boolean} <p>The <code>commonExceptionEnabled</code> field.</p>
         * @see  java.lang.Boolean
         */
        private Boolean commonExceptionEnabled = true;


    }
}
