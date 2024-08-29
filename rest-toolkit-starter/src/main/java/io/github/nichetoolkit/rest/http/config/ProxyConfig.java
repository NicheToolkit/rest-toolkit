package io.github.nichetoolkit.rest.http.config;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * <code>ProxyConfig</code>
 * <p>The type proxy config class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @since Jdk1.8
 */
@Data
public class ProxyConfig {
    private ProxyType type = ProxyType.SOCKS;
    private String hostname;
    private Integer port;

    /**
     * <code>ProxyConfig</code>
     * Instantiates a new proxy config.
     */
    public ProxyConfig() {
    }

    /**
     * <code>toProxy</code>
     * <p>the proxy method.</p>
     * @return {@link java.net.Proxy} <p>the proxy return object is <code>Proxy</code> type.</p>
     * @see java.net.Proxy
     */
    public Proxy toProxy() {
        if (GeneralUtils.isNotEmpty(this.hostname) && GeneralUtils.isNotEmpty(this.port)) {
            return new Proxy(this.type.getValue(), new InetSocketAddress(this.hostname, this.port));
        }
        return null;
    }
}
