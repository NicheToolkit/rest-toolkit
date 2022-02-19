package io.github.nichetoolkit.rest.http.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.net.Proxy;
import java.util.Optional;

/**
 * <p>ProxyType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum ProxyType implements RestValue<String, Proxy.Type> {
    DIRECT("direct",Proxy.Type.DIRECT),
    HTTP("http",Proxy.Type.HTTP),
    SOCKS("socks",Proxy.Type.SOCKS),
    ;
    private final String key;
    private final Proxy.Type value;

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

    @JsonCreator
    public static ProxyType parseKey(String key) {
        ProxyType sortTypeEnum = RestValue.parseKey(ProxyType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(ProxyType.SOCKS);
    }

    public static ProxyType parseValue(Proxy.Type value) {
        ProxyType sortTypeEnum = RestValue.parseValue(ProxyType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(ProxyType.SOCKS);
    }
}
