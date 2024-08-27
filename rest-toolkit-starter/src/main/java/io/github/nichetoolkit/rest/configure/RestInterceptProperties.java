package io.github.nichetoolkit.rest.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <code>RestInterceptProperties</code>
 * <p>The type rest intercept properties class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see org.springframework.stereotype.Component
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 * @since Jdk1.8
 */
@Data
@Component
@ConfigurationProperties(prefix = "nichetoolkit.rest.intercept")
public class RestInterceptProperties {
    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>the <code>enabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean enabled = false;
    /**
     * <code>logEnabled</code>
     * {@link java.lang.Boolean} <p>the <code>logEnabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean logEnabled = false;
    /**
     * <code>userlogEnabled</code>
     * {@link java.lang.Boolean} <p>the <code>userlogEnabled</code> field.</p>
     * @see java.lang.Boolean
     */
    private Boolean userlogEnabled = false;
    /**
     * <code>bodyLength</code>
     * {@link java.lang.Integer} <p>the <code>bodyLength</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer bodyLength = 1024;
    /**
     * <code>errorLength</code>
     * {@link java.lang.Integer} <p>the <code>errorLength</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer errorLength = 1024;
    /**
     * <code>messageLength</code>
     * {@link java.lang.Integer} <p>the <code>messageLength</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer messageLength = 1024;
    /**
     * <code>resultLength</code>
     * {@link java.lang.Integer} <p>the <code>resultLength</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer resultLength = 1024;

    /**
     * <code>RestInterceptProperties</code>
     * Instantiates a new rest intercept properties.
     */
    public RestInterceptProperties() {
    }
}
