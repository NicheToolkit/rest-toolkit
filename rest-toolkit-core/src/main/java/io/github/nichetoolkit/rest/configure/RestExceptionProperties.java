package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.type.CharsetType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <code>RestExceptionProperties</code>
 * <p>The rest exception properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.error")
public class RestExceptionProperties {
    @NestedConfigurationProperty
    private ConsoleLog consoleLog = new ConsoleLog();

    @NestedConfigurationProperty
    private MessageI18n messageI18n = new MessageI18n();

    /**
     * <code>Console</code>
     * <p>The console class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Getter
    @Setter
    public static class ConsoleLog {
        private Boolean restExceptionEnabled = false;
        private Boolean commonExceptionEnabled = true;
    }


    /**
     * <code>Message</code>
     * <p>The message class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @see lombok.Getter
     * @see lombok.Setter
     * @since Jdk1.8
     */
    @Getter
    @Setter
    public static class MessageI18n {
        private Boolean transformEnabled = false;
        private String messagePrefix = "i18n@";
    }
}
