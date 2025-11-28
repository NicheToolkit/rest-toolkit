package io.github.nichetoolkit.rest.http.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestKey;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>HttpClientType</code>
 * <p>The http client type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk17
 */
public enum HttpClientType implements RestValue<String,String> {
    /**
     * <code>DEFAULT_CLIENT</code>
     * <p>The default client http client type field.</p>
     */
    DEFAULT_CLIENT("default_client",HttpClientType.DEFAULT_TEMPLATE),

    /**
     * <code>HTTP_CLIENT</code>
     * <p>The http client http client type field.</p>
     */
    HTTP_CLIENT("http_client",HttpClientType.HTTP_TEMPLATE),

    /**
     * <code>REACTOR_CLIENT</code>
     * <p>The reactor client http client type field.</p>
     */
    REACTOR_CLIENT("reactor_client",HttpClientType.REACTOR_TEMPLATE),

    /**
     * <code>JDK_CLIENT</code>
     * <p>The jdk client http client type field.</p>
     */
    JDK_CLIENT("jdk_client",HttpClientType.JDK_TEMPLATE),

    /**
     * <code>JETTY_CLIENT</code>
     * <p>The jetty client http client type field.</p>
     */
    JETTY_CLIENT("jetty_client",HttpClientType.JETTY_TEMPLATE),

    /**
     * <code>SIMPLE_CLIENT</code>
     * <p>The simple client http client type field.</p>
     */
    SIMPLE_CLIENT("simple_client",HttpClientType.SIMPLE_TEMPLATE),

    ;
    /**
     * <code>key</code>
     * {@link java.lang.String} <p>The <code>key</code> field.</p>
     * @see java.lang.String
     */
    private final String key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see java.lang.String
     */
    private final String value;

    /**
     * <code>DEFAULT_TEMPLATE</code>
     * {@link java.lang.String} <p>The constant <code>DEFAULT_TEMPLATE</code> field.</p>
     * @see java.lang.String
     */
    public static final String DEFAULT_TEMPLATE = "restTemplate";

    /**
     * <code>HTTP_TEMPLATE</code>
     * {@link java.lang.String} <p>The constant <code>HTTP_TEMPLATE</code> field.</p>
     * @see java.lang.String
     */
    public static final String HTTP_TEMPLATE = "httpTemplate";

    /**
     * <code>REACTOR_TEMPLATE</code>
     * {@link java.lang.String} <p>The constant <code>REACTOR_TEMPLATE</code> field.</p>
     * @see java.lang.String
     */
    public static final String REACTOR_TEMPLATE = "reactorTemplate";
    /**
     * <code>JDK_TEMPLATE</code>
     * {@link java.lang.String} <p>The constant <code>JDK_TEMPLATE</code> field.</p>
     * @see java.lang.String
     */
    public static final String JDK_TEMPLATE = "jdkTemplate";

    /**
     * <code>JETTY_TEMPLATE</code>
     * {@link java.lang.String} <p>The constant <code>JETTY_TEMPLATE</code> field.</p>
     * @see java.lang.String
     */
    public static final String JETTY_TEMPLATE = "jettyTemplate";
    /**
     * <code>SIMPLE_TEMPLATE</code>
     * {@link java.lang.String} <p>The constant <code>SIMPLE_TEMPLATE</code> field.</p>
     * @see java.lang.String
     */
    public static final String SIMPLE_TEMPLATE = "simpleTemplate";

    /**
     * <code>HttpClientType</code>
     * <p>Instantiates a new http client type.</p>
     * @param key   {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    HttpClientType(String key, String value) {
        this.key = key;
        this.value = value;
    }



    @JsonValue
    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.String} <p>The key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.config.HttpClientType} <p>The parse key return object is <code>HttpClientType</code> type.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonCreator
     */
    @JsonCreator
    public static HttpClientType parseKey(String key) {
        HttpClientType sortTypeEnum = RestKey.parseKey(HttpClientType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(HttpClientType.DEFAULT_CLIENT);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.config.HttpClientType} <p>The parse value return object is <code>HttpClientType</code> type.</p>
     * @see java.lang.String
     */
    public static HttpClientType parseValue(String value) {
        HttpClientType sortTypeEnum = RestValue.parseValue(HttpClientType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(HttpClientType.DEFAULT_CLIENT);
    }

}
