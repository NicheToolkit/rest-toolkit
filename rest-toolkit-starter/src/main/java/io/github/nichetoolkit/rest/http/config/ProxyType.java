package io.github.nichetoolkit.rest.http.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.net.Proxy;
import java.util.Optional;

/**
 * <code>ProxyType</code>
 * <p>The type proxy type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum ProxyType implements RestValue<String, Proxy.Type> {
    /**
     * <code>DIRECT</code>
     * <p>the Direct proxy type field.</p>
     */
    DIRECT("direct",Proxy.Type.DIRECT),
    /**
     * <code>HTTP</code>
     * <p>the Http proxy type field.</p>
     */
    HTTP("http",Proxy.Type.HTTP),
    /**
     * <code>SOCKS</code>
     * <p>the Socks proxy type field.</p>
     */
    SOCKS("socks",Proxy.Type.SOCKS),
    ;
    /**
     * <code>key</code>
     * {@link java.lang.String} <p>the <code>key</code> field.</p>
     * @see java.lang.String
     */
    private final String key;
    /**
     * <code>value</code>
     * {@link java.net.Proxy.Type} <p>the <code>value</code> field.</p>
     * @see java.net.Proxy.Type
     */
    private final Proxy.Type value;

    /**
     * <code>ProxyType</code>
     * Instantiates a new proxy type.
     * @param key   {@link java.lang.String} <p>the key parameter is <code>String</code> type.</p>
     * @param value {@link java.net.Proxy.Type} <p>the value parameter is <code>Type</code> type.</p>
     * @see java.lang.String
     * @see java.net.Proxy.Type
     */
    ProxyType(String key, Proxy.Type value) {
        this.key = key;
        this.value = value;
    }

    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Proxy.Type getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>the key method.</p>
     * @param key {@link java.lang.String} <p>the key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.config.ProxyType} <p>the key return object is <code>ProxyType</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static ProxyType parseKey(String key) {
        ProxyType sortTypeEnum = RestValue.parseKey(ProxyType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(ProxyType.SOCKS);
    }

    /**
     * <code>parseValue</code>
     * <p>the value method.</p>
     * @param value {@link java.net.Proxy.Type} <p>the value parameter is <code>Type</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.config.ProxyType} <p>the value return object is <code>ProxyType</code> type.</p>
     * @see java.net.Proxy.Type
     */
    public static ProxyType parseValue(Proxy.Type value) {
        ProxyType sortTypeEnum = RestValue.parseValue(ProxyType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(ProxyType.SOCKS);
    }
}
