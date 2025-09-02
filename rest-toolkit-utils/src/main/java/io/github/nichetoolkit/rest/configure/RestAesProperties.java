package io.github.nichetoolkit.rest.configure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <code>RestAesProperties</code>
 * <p>The rest aes properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "nichetoolkit.rest.aes")
public class RestAesProperties {
    /**
     * <code>enabled</code>
     * <p>The <code>enabled</code> field.</p>
     */
    private boolean enabled;
    /**
     * <code>secretKey</code>
     * {@link java.lang.String} <p>The <code>secretKey</code> field.</p>
     * @see java.lang.String
     */
    private String secretKey = "ZbqWif8ZSnjWQS5iznqXcUoKEjAV4sqJ";
    /**
     * <code>secretIv</code>
     * {@link java.lang.String} <p>The <code>secretIv</code> field.</p>
     * @see java.lang.String
     */
    private String secretIv = "ICBwpv3gDQLfdrTQ";
}
