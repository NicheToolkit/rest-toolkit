package io.github.nichetoolkit.rest.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>IdentityProperties</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.intercept")
public class RestInterceptProperties {
    private Boolean enabled = false;
    private Boolean logEnabled = false;
    private Boolean userlogEnabled = false;
    /** instead of userlogEnabled */
    @Deprecated
    private Boolean beanEnabled = false;
    private Integer bodyLength = 1000;
    private Integer errorLength = 1000;
    private Integer messageLength = 1000;
    private Integer resultLength = 1000;

    public RestInterceptProperties() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(Boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public Boolean getUserlogEnabled() {
        return userlogEnabled;
    }

    public void setUserlogEnabled(Boolean userlogEnabled) {
        this.userlogEnabled = userlogEnabled;
    }

    /** instead of getUserlogEnabled */
    @Deprecated
    public Boolean getBeanEnabled() {
        return beanEnabled;
    }

    /** instead of setUserlogEnabled */
    @Deprecated
    public void setBeanEnabled(Boolean beanEnabled) {
        this.beanEnabled = beanEnabled;
    }

    public Integer getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(Integer bodyLength) {
        this.bodyLength = bodyLength;
    }

    public Integer getErrorLength() {
        return errorLength;
    }

    public void setErrorLength(Integer errorLength) {
        this.errorLength = errorLength;
    }

    public Integer getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(Integer messageLength) {
        this.messageLength = messageLength;
    }

    public Integer getResultLength() {
        return resultLength;
    }

    public void setResultLength(Integer resultLength) {
        this.resultLength = resultLength;
    }
}
