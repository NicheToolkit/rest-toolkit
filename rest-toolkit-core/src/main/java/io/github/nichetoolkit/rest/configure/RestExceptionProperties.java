package io.github.nichetoolkit.rest.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * <code>RestExceptionProperties</code>
 * <p>The rest exception properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Component
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
     * <code>getConsoleLog</code>
     * <p>The get console log getter method.</p>
     * @return {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog} <p>The get console log return object is <code>ConsoleLog</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog
     */
    public ConsoleLog getConsoleLog() {
        return consoleLog;
    }

    /**
     * <code>setConsoleLog</code>
     * <p>The set console log setter method.</p>
     * @param consoleLog {@link io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog} <p>The console log parameter is <code>ConsoleLog</code> type.</p>
     * @see io.github.nichetoolkit.rest.configure.RestExceptionProperties.ConsoleLog
     */
    public void setConsoleLog(ConsoleLog consoleLog) {
        this.consoleLog = consoleLog;
    }

    /**
     * <code>ConsoleLog</code>
     * <p>The console log class.</p>
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
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

        /**
         * <code>getRestExceptionEnabled</code>
         * <p>The get rest exception enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get rest exception enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getRestExceptionEnabled() {
            return restExceptionEnabled;
        }

        /**
         * <code>setRestExceptionEnabled</code>
         * <p>The set rest exception enabled setter method.</p>
         * @param restExceptionEnabled {@link java.lang.Boolean} <p>The rest exception enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setRestExceptionEnabled(Boolean restExceptionEnabled) {
            this.restExceptionEnabled = restExceptionEnabled;
        }

        /**
         * <code>getCommonExceptionEnabled</code>
         * <p>The get common exception enabled getter method.</p>
         * @return {@link java.lang.Boolean} <p>The get common exception enabled return object is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public Boolean getCommonExceptionEnabled() {
            return commonExceptionEnabled;
        }

        /**
         * <code>setCommonExceptionEnabled</code>
         * <p>The set common exception enabled setter method.</p>
         * @param commonExceptionEnabled {@link java.lang.Boolean} <p>The common exception enabled parameter is <code>Boolean</code> type.</p>
         * @see java.lang.Boolean
         */
        public void setCommonExceptionEnabled(Boolean commonExceptionEnabled) {
            this.commonExceptionEnabled = commonExceptionEnabled;
        }
    }
}
