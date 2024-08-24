package io.github.nichetoolkit.rest.http.config;

import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Data;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * <p>ProxyConfig</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Data
public class ProxyConfig {
    private ProxyType type = ProxyType.SOCKS;
    private String hostname;
    private Integer port;

    public ProxyConfig() {
    }

    public Proxy toProxy() {
        if (GeneralUtils.isNotEmpty(this.hostname) && GeneralUtils.isNotEmpty(this.port)) {
            return new Proxy(this.type.getValue(), new InetSocketAddress(this.hostname, this.port));
        }
        return null;
    }
}
