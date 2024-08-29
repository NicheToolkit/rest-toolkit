package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.http.config.HttpClientType;
import io.github.nichetoolkit.rest.http.config.ProxyConfig;
import lombok.Data;
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
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
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

    private DefaultUriBuilderFactory.EncodingMode encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE;

    private Integer retryTimes = 0;

    private Map<String,Integer> keepAliveHosts;

    private Long keepAliveTime = 600000L;

    @NestedConfigurationProperty
    private ProxyConfig proxy = new ProxyConfig();

}
