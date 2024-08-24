package io.github.nichetoolkit.rest.http.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;

import java.util.Optional;

/**
 * <p>HttpClientType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum HttpClientType implements RestValue<String,String> {
    DEFAULT("default",HttpClientType.DEFAULT_BEAN),
    HTTP_CLIENT("httpClient",HttpClientType.HTTPCLIENT_BEAN),
    OK_HTTP_CLIENT("okHttp3",HttpClientType.OKHTTP3_BEAN),
    ;
    private final String key;
    private final String value;

    public static final String DEFAULT_BEAN = "restTemplate";
    public static final String HTTPCLIENT_BEAN = "httpTemplate";
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

    @JsonCreator
    public static HttpClientType parseKey(String key) {
        HttpClientType sortTypeEnum = RestValue.parseKey(HttpClientType.class, key);
        return Optional.ofNullable(sortTypeEnum).orElse(HttpClientType.DEFAULT);
    }

    public static HttpClientType parseValue(String value) {
        HttpClientType sortTypeEnum = RestValue.parseValue(HttpClientType.class, value);
        return Optional.ofNullable(sortTypeEnum).orElse(HttpClientType.DEFAULT);
    }

}
