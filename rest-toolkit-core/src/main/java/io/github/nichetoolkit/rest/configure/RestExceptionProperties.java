package io.github.nichetoolkit.rest.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * <code>RestExceptionProperties</code>
 * <p>The rest exception properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk17
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "nichetoolkit.rest.error")
public class RestExceptionProperties {
    /**
     * <code>consoleLog</code>
     * {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog} <p>The <code>consoleLog</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private ConsoleLog consoleLog = new ConsoleLog();

    /**
     * <code>messageI18n</code>
     * {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties.MessageI18n} <p>The <code>messageI18n</code> field.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties.MessageI18n
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private MessageI18n messageI18n = new MessageI18n();

    /**
     * <code>ConsoleLog</code>
     * <p>The console log class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk17
     */
    @Getter
    @Setter
    public static class ConsoleLog {
        /**
         * <code>restExceptionEnabled</code>
         * {@link java.lang.Boolean} <p>The <code>restExceptionEnabled</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean restExceptionEnabled = false;
        /**
         * <code>commonExceptionEnabled</code>
         * {@link java.lang.Boolean} <p>The <code>commonExceptionEnabled</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean commonExceptionEnabled = true;
    }


    /**
     * <code>MessageI18n</code>
     * <p>The message i 18 n class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk17
     */
    @Getter
    @Setter
    public static class MessageI18n {
        /**
         * <code>transformEnabled</code>
         * {@link java.lang.Boolean} <p>The <code>transformEnabled</code> field.</p>
         * @see java.lang.Boolean
         */
        private Boolean transformEnabled = false;
        /**
         * <code>messagePrefix</code>
         * {@link java.lang.String} <p>The <code>messagePrefix</code> field.</p>
         * @see java.lang.String
         */
        private String messagePrefix = "i18n@";
    }
}
