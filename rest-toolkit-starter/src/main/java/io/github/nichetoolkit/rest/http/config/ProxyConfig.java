package io.github.nichetoolkit.rest.http.config;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * <code>ProxyConfig</code>
 * <p>The proxy config class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @since Jdk17
 */
@Getter
@Setter
@SuperBuilder
public class ProxyConfig implements Serializable {
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rest.http.config.ProxyType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rest.http.config.ProxyType
     */
    @Builder.Default
    private ProxyType type = ProxyType.SOCKS;
    /**
     * <code>hostname</code>
     * {@link java.lang.String} <p>The <code>hostname</code> field.</p>
     * @see java.lang.String
     */
    private String hostname;
    /**
     * <code>port</code>
     * {@link java.lang.Integer} <p>The <code>port</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer port;

    /**
     * <code>ProxyConfig</code>
     * <p>Instantiates a new proxy config.</p>
     */
    public ProxyConfig() {
    }

    /**
     * <code>toProxy</code>
     * <p>The to proxy method.</p>
     * @return {@link java.net.Proxy} <p>The to proxy return object is <code>Proxy</code> type.</p>
     * @see java.net.Proxy
     */
    public Proxy toProxy() {
        if (GeneralUtils.isNotEmpty(this.hostname) && GeneralUtils.isNotEmpty(this.port)) {
            return new Proxy(this.type.getValue(), new InetSocketAddress(this.hostname, this.port));
        }
        return null;
    }
}
