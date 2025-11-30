package io.github.nichetoolkit.rest.http.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.*;
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
 * @see lombok.NoArgsConstructor
 * @see lombok.AllArgsConstructor
 * @see com.fasterxml.jackson.annotation.JsonInclude
 * @see com.fasterxml.jackson.annotation.JsonIgnoreProperties
 * @since Jdk17
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProxyConfig implements Serializable {
    /**
     * <code>type</code>
     * {@link io.github.nichetoolkit.rest.http.config.ProxyType} <p>The <code>type</code> field.</p>
     * @see io.github.nichetoolkit.rest.http.config.ProxyType
     * @see lombok.Builder.Default
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
