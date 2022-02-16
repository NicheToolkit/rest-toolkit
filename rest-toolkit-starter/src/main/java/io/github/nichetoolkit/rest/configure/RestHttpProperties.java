package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.http.config.HttpClientType;
import io.github.nichetoolkit.rest.http.config.ProxyConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>RestTemplateProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.http")
public class RestHttpProperties {
    private Boolean enabled = false;

    private Integer connectTimeout = 2000;
    private Integer readTimeout = 30000;
    private Integer requestTimeout= 200;

    private Integer maxCoreSize = 5;
    private Integer maxIdleSize = 10;

    private HttpClientType httpType = HttpClientType.DEFAULT;

    private Charset charset = StandardCharsets.UTF_8;

    private Integer retryTimes = 0;

    private Map<String,Integer> keepAliveHosts;

    private Long keepAliveTime = 600000L;

    @NestedConfigurationProperty
    private ProxyConfig proxy = new ProxyConfig();

    public RestHttpProperties() {
    }

    public Integer getMaxCoreSize() {
        return maxCoreSize;
    }

    public void setMaxCoreSize(Integer maxCoreSize) {
        this.maxCoreSize = maxCoreSize;
    }

    public Integer getMaxIdleSize() {
        return maxIdleSize;
    }

    public void setMaxIdleSize(Integer maxIdleSize) {
        this.maxIdleSize = maxIdleSize;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Map<String, Integer> getKeepAliveHosts() {
        return keepAliveHosts;
    }

    public void setKeepAliveHosts(Map<String, Integer> keepAliveHosts) {
        this.keepAliveHosts = keepAliveHosts;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public ProxyConfig getProxy() {
        return proxy;
    }

    public void setProxy(ProxyConfig proxy) {
        this.proxy = proxy;
    }

    public HttpClientType getHttpType() {
        return httpType;
    }

    public void setHttpType(HttpClientType httpType) {
        this.httpType = httpType;
    }
}
