package io.github.nichetoolkit.rest.http.config;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * <code>ProxyConfig</code>
 * <p>The type proxy config class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Getter
 * @see lombok.Setter
 * @since Jdk1.8
 */
@Getter
@Setter
public class ProxyConfig {
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rest.http.config.ProxyType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rest.http.config.ProxyType
     */
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
     * <p>The proxy method.</p>
     * @return {@link java.net.Proxy} <p>The proxy return object is <code>Proxy</code> type.</p>
     * @see java.net.Proxy
     */
    public Proxy toProxy() {
        if (GeneralUtils.isNotEmpty(this.hostname) && GeneralUtils.isNotEmpty(this.port)) {
            return new Proxy(this.type.getValue(), new InetSocketAddress(this.hostname, this.port));
        }
        return null;
    }
}
