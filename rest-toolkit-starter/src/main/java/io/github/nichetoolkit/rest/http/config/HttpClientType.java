package io.github.nichetoolkit.rest.http.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <code>HttpClientType</code>
 * <p>The type http client type enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestValue
 * @since Jdk1.8
 */
public enum HttpClientType implements RestValue<String,String> {
    /**
     * <code>DEFAULT</code>
     * <p>the Default http client type field.</p>
     */
    DEFAULT("default",HttpClientType.DEFAULT_BEAN),
    /**
     * <code>HTTP_CLIENT</code>
     * <p>the Http client http client type field.</p>
     */
    HTTP_CLIENT("httpClient",HttpClientType.HTTPCLIENT_BEAN),
    /**
     * <code>OK_HTTP_CLIENT</code>
     * <p>the Ok http client http client type field.</p>
     */
    OK_HTTP_CLIENT("okHttp3",HttpClientType.OKHTTP3_BEAN),
    ;
    private final String key;
    private final String value;

    /**
     * <code>DEFAULT_BEAN</code>
     * {@link java.lang.String} <p>the constant <code>DEFAULT_BEAN</code> field.</p>
     * @see java.lang.String
     */
    public static final String DEFAULT_BEAN = "restTemplate";
    /**
     * <code>HTTPCLIENT_BEAN</code>
     * {@link java.lang.String} <p>the constant <code>HTTPCLIENT_BEAN</code> field.</p>
     * @see java.lang.String
     */
    public static final String HTTPCLIENT_BEAN = "httpTemplate";
    /**
     * <code>OKHTTP3_BEAN</code>
     * {@link java.lang.String} <p>the constant <code>OKHTTP3_BEAN</code> field.</p>
     * @see java.lang.String
     */
    public static final String OKHTTP3_BEAN = "okHttpTemplate";

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
     * <p>the key method.</p>
     * @param key {@link java.lang.String} <p>the key parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.config.HttpClientType} <p>the key return object is <code>HttpClientType</code> type.</p>
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
     * <p>the value method.</p>
     * @param value {@link java.lang.String} <p>the value parameter is <code>String</code> type.</p>
     * @return {@link io.github.nichetoolkit.rest.http.config.HttpClientType} <p>the value return object is <code>HttpClientType</code> type.</p>
     * @see java.lang.String
     */
    public static HttpClientType parseValue(String value) {
        HttpClientType sortTypeEnum = RestValue.parseValue(HttpClientType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(HttpClientType.DEFAULT);
    }

}
