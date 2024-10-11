package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.http.config.HttpClientType;
import io.github.nichetoolkit.rest.http.config.ProxyConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <code>RestHttpProperties</code>
 * <p>The type rest http properties class.</p>
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
@ConfigurationProperties(prefix = "nichetoolkit.rest.http")
public class RestHttpProperties {
    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean enabled = false;

    /**
     * <code>connectTimeout</code>
     * {@link java.lang.Integer} <p>The <code>connectTimeout</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer connectTimeout = 2000;
    /**
     * <code>readTimeout</code>
     * {@link java.lang.Integer} <p>The <code>readTimeout</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer readTimeout = 30000;
    /**
     * <code>requestTimeout</code>
     * {@link java.lang.Integer} <p>The <code>requestTimeout</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer requestTimeout= 200;

    /**
     * <code>maxCoreSize</code>
     * {@link java.lang.Integer} <p>The <code>maxCoreSize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer maxCoreSize = 5;
    /**
     * <code>maxIdleSize</code>
     * {@link java.lang.Integer} <p>The <code>maxIdleSize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer maxIdleSize = 10;

    /**
     * <code>httpType</code>
     * {@link io.github.nichetoolkit.rest.http.config.HttpClientType} <p>The <code>httpType</code> field.</p>
     * @see io.github.nichetoolkit.rest.http.config.HttpClientType
     */
    private HttpClientType httpType = HttpClientType.DEFAULT;

    /**
     * <code>charset</code>
     * {@link java.nio.charset.Charset} <p>The <code>charset</code> field.</p>
     * @see java.nio.charset.Charset
     */
    private Charset charset = StandardCharsets.UTF_8;

    /**
     * <code>encodingMode</code>
     * {@link org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode} <p>The <code>encodingMode</code> field.</p>
     * @see org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode
     */
    private DefaultUriBuilderFactory.EncodingMode encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE;

    /**
     * <code>retryTimes</code>
     * {@link java.lang.Integer} <p>The <code>retryTimes</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer retryTimes = 0;

    /**
     * <code>keepAliveHosts</code>
     * {@link java.util.Map} <p>The <code>keepAliveHosts</code> field.</p>
     * @see java.util.Map
     */
    private Map<String,Integer> keepAliveHosts;

    /**
     * <code>keepAliveTime</code>
     * {@link java.lang.Long} <p>The <code>keepAliveTime</code> field.</p>
     * @see java.lang.Long
     */
    private Long keepAliveTime = 600000L;

    /**
     * <code>proxy</code>
     * {@link io.github.nichetoolkit.rest.http.config.ProxyConfig} <p>The <code>proxy</code> field.</p>
     * @see io.github.nichetoolkit.rest.http.config.ProxyConfig
     * @see org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private ProxyConfig proxy = new ProxyConfig();

}
