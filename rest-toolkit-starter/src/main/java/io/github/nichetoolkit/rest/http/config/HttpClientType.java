package io.github.nichetoolkit.rest.http.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>HttpClientType</code>
 * <p>The http client type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum HttpClientType implements RestValue<String,String> {
    /**
     * <code>DEFAULT</code>
     * <p>The default http client type field.</p>
     */
    DEFAULT("default",HttpClientType.DEFAULT_BEAN),
    /**
     * <code>HTTP_CLIENT</code>
     * <p>The http client http client type field.</p>
     */
    HTTP_CLIENT("httpClient",HttpClientType.HTTPCLIENT_BEAN),
    /**
     * <code>OK_HTTP_CLIENT</code>
     * <p>The ok http client http client type field.</p>
     */
    OK_HTTP_CLIENT("okHttp3",HttpClientType.OKHTTP3_BEAN),
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
     * <code>DEFAULT_BEAN</code>
     * {@link java.lang.String} <p>The constant <code>DEFAULT_BEAN</code> field.</p>
     * @see java.lang.String
     */
    public static final String DEFAULT_BEAN = "restTemplate";
    /**
     * <code>HTTPCLIENT_BEAN</code>
     * {@link java.lang.String} <p>The constant <code>HTTPCLIENT_BEAN</code> field.</p>
     * @see java.lang.String
     */
    public static final String HTTPCLIENT_BEAN = "httpTemplate";
    /**
     * <code>OKHTTP3_BEAN</code>
     * {@link java.lang.String} <p>The constant <code>OKHTTP3_BEAN</code> field.</p>
     * @see java.lang.String
     */
    public static final String OKHTTP3_BEAN = "okHttpTemplate";

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
        HttpClientType sortTypeEnum = RestValue.parseKey(HttpClientType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(HttpClientType.DEFAULT);
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
        return Optional.ofNullable(sortTypeEnum).orElse(HttpClientType.DEFAULT);
    }

}
