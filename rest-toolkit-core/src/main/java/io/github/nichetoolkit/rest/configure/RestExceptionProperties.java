package io.github.nichetoolkit.rest.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <p>RestExceptionProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.error")
public class RestExceptionProperties {
    @NestedConfigurationProperty
    private ConsoleLog consoleLog = new ConsoleLog();

    public ConsoleLog getConsoleLog() {
        return consoleLog;
    }

    public void setConsoleLog(ConsoleLog consoleLog) {
        this.consoleLog = consoleLog;
    }

    public static class ConsoleLog {
        private Boolean restExceptionEnabled = false;
        private Boolean commonExceptionEnabled = false;

        public Boolean getRestExceptionEnabled() {
            return restExceptionEnabled;
        }

        public void setRestExceptionEnabled(Boolean restExceptionEnabled) {
            this.restExceptionEnabled = restExceptionEnabled;
        }

        public Boolean getCommonExceptionEnabled() {
            return commonExceptionEnabled;
        }

        public void setCommonExceptionEnabled(Boolean commonExceptionEnabled) {
            this.commonExceptionEnabled = commonExceptionEnabled;
        }
    }
}
