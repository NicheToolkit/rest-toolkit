package io.github.nichetoolkit.rest.interceptor;

import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

/**
 * <p>HttpClientInterceptor</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@Component
public class RestHttpInterceptor implements ClientHttpRequestInterceptor {
    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest httpRequest, @NonNull byte[] bytes, @NonNull ClientHttpRequestExecution execution) throws IOException {
        String url = httpRequest.getURI().getPath();
        log.info(" HttpRequest -Url:     {}",url);
        HttpMethod method = httpRequest.getMethod();
        log.info(" HttpRequest -Method:  {}", JsonUtils.parseJson(method));
        String query = httpRequest.getURI().getQuery();
        UriComponents uriComponents = UriComponentsBuilder.newInstance().query(query).build();
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        Map<String, String> params = queryParams.toSingleValueMap();
        log.info(" HttpRequest -Params:  {}", JsonUtils.parseJson(params));
        HttpHeaders headers = httpRequest.getHeaders();
        log.info(" HttpRequest -Headers: {}",JsonUtils.parseJson(headers));
        String httpRequestBody = new String(bytes);
        log.info(" HttpRequest -Body:    {}", httpRequestBody);
        return execution.execute(httpRequest, bytes);
    }
}
